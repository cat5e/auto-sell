package autosell.Vistas.Transacoes;

import autosell.CustomExceptions.CustomExeption;
import autosell.Enumeracoes.ClassesVeiculos;
import autosell.Enumeracoes.EstadoVeiculo;
import autosell.Enumeracoes.MesesAno;
import autosell.Enumeracoes.TipoCombustivel;
import autosell.Enumeracoes.TipoDetalheTransacao;
import autosell.Enumeracoes.TipoEntidade;
import autosell.Enumeracoes.TipoTransacao;
import autosell.Gestores.GestorArmazenamentoDados;
import autosell.Gestores.GestorEntidades;
import autosell.Gestores.GestorTransacoes;
import autosell.Gestores.GestorVeiculos;
import autosell.Modelos.*;
import autosell.Utils.AppLogger;
import autosell.Utils.TableModel;
import static autosell.Utils.ValidacoesUtils.isComponenteVazio;
import autosell.Vistas.JanelaSelecao;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class JanelaEditarTransacao extends javax.swing.JInternalFrame {

    private Transacao transacao;
    private Colaborador colaboradorAutenticado;
    private TipoTransacao tipoTransacao;
    private DefaultListModel listModelVeiculos;
    private DefaultListModel listModelVeiculosATroca;
    private final TableModel tableModelDetalhes;
    private final String[] columns;
    private LinkedList<DetalhesTransacao> detalhesTransacao;
    private Entidade entidade;
    private float totalVeiculos;
    private float totalVeiculosATroca;
    private float totalTransacao;

    public JanelaEditarTransacao(Transacao transacao, Colaborador colaboradorAutenticado, TipoTransacao tipoTransacao) {
        this.transacao = transacao;
        this.colaboradorAutenticado = colaboradorAutenticado;
        this.tipoTransacao = tipoTransacao;
        detalhesTransacao = new LinkedList<>();
        listModelVeiculos = new DefaultListModel();
        listModelVeiculosATroca = new DefaultListModel();
        initComponents();

        toolBarMenu.setFloatable(false);

        listModelVeiculos = new DefaultListModel();
        listVeiculos.setModel(listModelVeiculos);

        listModelVeiculosATroca = new DefaultListModel();
        listVeiculosATroca.setModel(listModelVeiculosATroca);

        loadTipoTransacao();

        if (transacao != null) {
            popularDados();
        }

        columns = new String[]{
            "Descrição",
            "Detalhe",
            "Valor",
            "Tipo",
            "Obj"};
        tableModelDetalhes = new TableModel(columns, getTableData());
        tableDetalhes.setModel(tableModelDetalhes);
        tableDetalhes.getColumnModel().getColumn(columns.length - 2).setMinWidth(0);
        tableDetalhes.getColumnModel().getColumn(columns.length - 2).setMaxWidth(0);
        tableDetalhes.getColumnModel().getColumn(columns.length - 2).setWidth(0);
        tableDetalhes.getColumnModel().getColumn(columns.length - 1).setMinWidth(0);
        tableDetalhes.getColumnModel().getColumn(columns.length - 1).setMaxWidth(0);
        tableDetalhes.getColumnModel().getColumn(columns.length - 1).setWidth(0);

        panelVeiculos.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                tipoTransacao == TipoTransacao.COMPRA ? "Veículos a comprar" : "Veiculos a vender",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 1, 12)));

        panelEntidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                tipoTransacao == TipoTransacao.COMPRA ? "Vendedor" : "Cliente",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 1, 12)));

        comboBoxTipo.setSelectedItem(tipoTransacao);

    }

    private void loadTipoTransacao() {
        comboBoxTipo.setModel(new DefaultComboBoxModel(TipoTransacao.values()));
    }

    private void popularDados() {
        comboBoxTipo.setSelectedItem(transacao.getTipo());
        textFieldData.setText(transacao.getData());
        entidade = transacao.getEntidade();
        textFieldNome.setText(entidade.getNome());
        textFieldDataNascimento.setText(entidade.getDataNascimento());
        textFieldNif.setText(entidade.getNif());
        textFieldNumeroTelefone.setText(entidade.getNumeroTelefone());
        textFieldEmail.setText(entidade.getEmail());
        textFieldMorada.setText(entidade.getMorada());
        textFieldTotal.setText(String.valueOf(transacao.getPrecoFinal()));

        var veiculosAssociados = transacao.getVeiculosAssociados();
        for (Veiculo veiculoAssociado : veiculosAssociados) {
            listModelVeiculos.addElement(veiculoAssociado);
        }

        atualizarTotalVeiculos();

        var veiculosATroca = transacao.getVeiculosTroca();
        for (Veiculo veiculo : veiculosATroca) {
            listModelVeiculosATroca.addElement(veiculo);
        }
        atualizarTotalVeiculosATroca();

        detalhesTransacao = transacao.getDetalhesTransacao();

        tableModelDetalhes.setData(getTableData());

        atualizarDetalhesTransacao();
        atualizarTotais();
    }

    private Object[][] getTableData() {
        int i = 0;
        var aux = new Object[detalhesTransacao.size()][columns.length];
        for (DetalhesTransacao detalhe : detalhesTransacao) {
            aux[i][0] = detalhe.getDescricao();
            aux[i][1] = detalhe.getDetalhes();
            aux[i][2] = detalhe.getValor();
            aux[i][3] = detalhe.getTipoDetalheTransacao();
            aux[i][4] = detalhe;
            i++;
        }
        return aux;
    }

    private void setEntidade(Entidade entidade) {
        this.entidade = entidade;

        textFieldNome.setText(entidade == null ? "" : entidade.getNome());
        textFieldDataNascimento.setText(entidade == null ? "" : entidade.getDataNascimento());
        textFieldEmail.setText(entidade == null ? "" : entidade.getEmail());
        textFieldNif.setText(entidade == null ? "" : entidade.getNif());
        textFieldMorada.setText(entidade == null ? "" : entidade.getMorada());
        textFieldNumeroTelefone.setText(entidade == null ? "" : entidade.getNumeroTelefone());
    }

    private void atualizarTotalVeiculos() {
        float sumVeiculos = 0;
        for (int i = 0; i < listModelVeiculos.getSize(); i++) {
            var veiculo = (Veiculo) listModelVeiculos.getElementAt(i);
            sumVeiculos += veiculo.getPreco();
        }

        totalVeiculos = sumVeiculos;
    }

    private void atualizarTotalVeiculosATroca() {
        float sumVeiculosATroca = 0;
        for (int i = 0; i < listModelVeiculosATroca.getSize(); i++) {
            var veiculo = (Veiculo) listModelVeiculosATroca.getElementAt(i);
            sumVeiculosATroca += -veiculo.getPreco();
        }

        totalVeiculosATroca = sumVeiculosATroca;
    }

    private void atualizarDetalhesTransacao() {

        var detalheVeiculos = getDetalheTransacao(TipoDetalheTransacao.TOTAL_VEICULOS);
        if (listModelVeiculos.getSize() > 0) {
            if (detalheVeiculos == null) {
                detalheVeiculos = new DetalhesTransacao(
                        tipoTransacao.equals(TipoTransacao.COMPRA) ? "Preço total de veículos a comprar"
                        : "Preço total de veículos a vender",
                        "",
                        totalVeiculos,
                        TipoDetalheTransacao.TOTAL_VEICULOS);
                detalhesTransacao.add(detalheVeiculos);
            } else {
                detalheVeiculos.setValor(totalVeiculos);
            }
        } else {
            detalhesTransacao.remove(detalheVeiculos);
        }

        var detalheVeiculosATroca = getDetalheTransacao(TipoDetalheTransacao.TOTAL_VEICULOS_TROCA);
        if (listModelVeiculosATroca.getSize() > 0) {
            if (detalheVeiculosATroca == null) {
                detalheVeiculosATroca = new DetalhesTransacao(
                        "Preço total de veículos dados em troca",
                        "",
                        totalVeiculosATroca,
                        TipoDetalheTransacao.TOTAL_VEICULOS_TROCA);
                detalhesTransacao.add(detalheVeiculosATroca);
            } else {
                detalheVeiculosATroca.setValor(totalVeiculosATroca);
            }
        } else {
            detalhesTransacao.remove(detalheVeiculosATroca);
        }

        tableModelDetalhes.setData(getTableData());

        atualizarTotais();
    }

    private void atualizarTotais() {
        float totalAmortizado = 0;
        for (DetalhesTransacao detalhe : detalhesTransacao) {
            totalAmortizado += detalhe.getValor();
        }

        totalTransacao = totalVeiculos - totalVeiculosATroca;
        textFieldTotal.setText(String.valueOf(totalTransacao));
        textFieldTotalPagar.setText(String.valueOf(totalAmortizado));

    }

    private DetalhesTransacao getDetalheTransacao(TipoDetalheTransacao tipo) {
        for (DetalhesTransacao detalheTransacao : detalhesTransacao) {
            if (detalheTransacao.getTipoDetalheTransacao().equals(tipo)) {
                return detalheTransacao;
            }
        }

        return null;
    }

    private void acaoGuardar() {
        try {
            if (!isDadosValidos()) {
                return;
            }

            if (transacao == null) {
                transacao = new Transacao(
                        tipoTransacao,
                        textFieldData.getText(),
                        colaboradorAutenticado
                );

                transacao.setEntidade(entidade);
                transacao.setPrecoFinal(totalTransacao);

                for (int i = 0; i < listModelVeiculos.getSize(); i++) {
                    var veiculo = (Veiculo) listModelVeiculos.getElementAt(i);
                    transacao.adicionarVeiculoAssociado(veiculo);
                }
                
                for (int i = 0; i < listModelVeiculosATroca.getSize(); i++) {
                    var veiculo = (Veiculo) listModelVeiculosATroca.getElementAt(i);
                    transacao.adicionarVeiculoTroca(veiculo);
                }
                
                for(DetalhesTransacao detalhe : detalhesTransacao) {
                    transacao.adicionarDetalhesTransacao(detalhe);
                }

                if (!GestorTransacoes.getInstance().adicionar(transacao)) {
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
            } else {

            }

            GestorArmazenamentoDados.INSTANCIA.escreverDados();

        } catch (CustomExeption e) {
            AppLogger.LOG.warning(this, e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            AppLogger.LOG.severe(this, e);
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao guardar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Os dados foram gravados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isDadosValidos() {
        JComponent componentesAValidar[] = {textFieldData};

        for (JComponent jComponent : componentesAValidar) {
            if (!isComponenteVazio(this, jComponent)) {
                return false;
            }
        }

        if (listModelVeiculos.getSize() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Não pode criar uma transação sem associar veículos para "
                    + (tipoTransacao.equals(TipoTransacao.COMPRA) ? "comprar." : "vender."),
                    "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (entidade == null) {
            JOptionPane.showMessageDialog(this,
                    "Não pode criar uma transação sem um "
                    + (tipoTransacao.equals(TipoTransacao.COMPRA) ? "vendedor." : "cliente."),
                    "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBarMenu = new javax.swing.JToolBar();
        buttonGuardar = new javax.swing.JButton();
        panelEdicao = new javax.swing.JPanel();
        labelTipo = new javax.swing.JLabel();
        comboBoxTipo = new javax.swing.JComboBox<>();
        labelData = new javax.swing.JLabel();
        textFieldData = new javax.swing.JTextField();
        panelVeiculos = new javax.swing.JPanel();
        buttonAdicionarVeiculo = new javax.swing.JButton();
        buttonRemoverVeiculo = new javax.swing.JButton();
        scroolPaneVeiculos = new javax.swing.JScrollPane();
        listVeiculos = new javax.swing.JList<>();
        panelVeiculosATroca = new javax.swing.JPanel();
        buttonAdicionarVeiculoATroca = new javax.swing.JButton();
        butonRemoverVeiculoATroca = new javax.swing.JButton();
        scrollPaneVeiculosATroca = new javax.swing.JScrollPane();
        listVeiculosATroca = new javax.swing.JList<>();
        panelEntidade = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textFieldMorada = new javax.swing.JTextField();
        labelDataNascimento = new javax.swing.JLabel();
        textFieldNif = new javax.swing.JTextField();
        labelNif = new javax.swing.JLabel();
        textFieldDataNascimento = new javax.swing.JTextField();
        textFieldEmail = new javax.swing.JTextField();
        textFieldNumeroTelefone = new javax.swing.JTextField();
        labelNumeroTelefone = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelMorada = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        buttonProcurarEntidade = new javax.swing.JButton();
        buttonRemoverEntidade = new javax.swing.JButton();
        panelTotais = new javax.swing.JPanel();
        textFieldTotal = new javax.swing.JTextField();
        labelTotal = new javax.swing.JLabel();
        labelTotal1 = new javax.swing.JLabel();
        textFieldTotalPagar = new javax.swing.JTextField();
        panelDetalhesTransacao = new javax.swing.JPanel();
        scrollPaneDetalhes = new javax.swing.JScrollPane();
        tableDetalhes = new javax.swing.JTable();
        buttonAdicionarDetalhe = new javax.swing.JButton();
        buttonRemoverDetalhe = new javax.swing.JButton();

        setClosable(true);

        buttonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/content-save.png"))); // NOI18N
        buttonGuardar.setText("Guardar");
        buttonGuardar.setMaximumSize(new java.awt.Dimension(80, 52));
        buttonGuardar.setMinimumSize(new java.awt.Dimension(80, 52));
        buttonGuardar.setPreferredSize(new java.awt.Dimension(80, 52));
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonGuardar);

        panelEdicao.setBackground(new java.awt.Color(255, 255, 255));

        labelTipo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTipo.setText("Tipo");

        comboBoxTipo.setEnabled(false);
        comboBoxTipo.setName("Tipo Transação"); // NOI18N

        labelData.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelData.setText("Data");

        textFieldData.setName("Data da Transação"); // NOI18N

        panelVeiculos.setBackground(new java.awt.Color(255, 255, 255));
        panelVeiculos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        buttonAdicionarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/plus.png"))); // NOI18N
        buttonAdicionarVeiculo.setToolTipText("Adicionar Veículo");
        buttonAdicionarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarVeiculoActionPerformed(evt);
            }
        });

        buttonRemoverVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        buttonRemoverVeiculo.setToolTipText("Remover Veículo");
        buttonRemoverVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverVeiculoActionPerformed(evt);
            }
        });

        listVeiculos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scroolPaneVeiculos.setViewportView(listVeiculos);

        javax.swing.GroupLayout panelVeiculosLayout = new javax.swing.GroupLayout(panelVeiculos);
        panelVeiculos.setLayout(panelVeiculosLayout);
        panelVeiculosLayout.setHorizontalGroup(
            panelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVeiculosLayout.createSequentialGroup()
                .addGroup(panelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVeiculosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonAdicionarVeiculo)
                        .addGap(4, 4, 4)
                        .addComponent(buttonRemoverVeiculo))
                    .addGroup(panelVeiculosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroolPaneVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelVeiculosLayout.setVerticalGroup(
            panelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVeiculosLayout.createSequentialGroup()
                .addGroup(panelVeiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdicionarVeiculo)
                    .addComponent(buttonRemoverVeiculo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroolPaneVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelVeiculosATroca.setBackground(new java.awt.Color(255, 255, 255));
        panelVeiculosATroca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veículos à troca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        buttonAdicionarVeiculoATroca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/plus.png"))); // NOI18N
        buttonAdicionarVeiculoATroca.setToolTipText("Adicionar veículo á troca");
        buttonAdicionarVeiculoATroca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarVeiculoATrocaActionPerformed(evt);
            }
        });

        butonRemoverVeiculoATroca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        butonRemoverVeiculoATroca.setToolTipText("Remover veículo à troca");
        butonRemoverVeiculoATroca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonRemoverVeiculoATrocaActionPerformed(evt);
            }
        });

        scrollPaneVeiculosATroca.setViewportView(listVeiculosATroca);

        javax.swing.GroupLayout panelVeiculosATrocaLayout = new javax.swing.GroupLayout(panelVeiculosATroca);
        panelVeiculosATroca.setLayout(panelVeiculosATrocaLayout);
        panelVeiculosATrocaLayout.setHorizontalGroup(
            panelVeiculosATrocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVeiculosATrocaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVeiculosATrocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVeiculosATrocaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonAdicionarVeiculoATroca)
                        .addGap(4, 4, 4)
                        .addComponent(butonRemoverVeiculoATroca))
                    .addComponent(scrollPaneVeiculosATroca, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panelVeiculosATrocaLayout.setVerticalGroup(
            panelVeiculosATrocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVeiculosATrocaLayout.createSequentialGroup()
                .addGroup(panelVeiculosATrocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdicionarVeiculoATroca)
                    .addComponent(butonRemoverVeiculoATroca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneVeiculosATroca, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        panelEntidade.setBackground(new java.awt.Color(255, 255, 255));
        panelEntidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNome.setText("Nome");

        textFieldMorada.setEditable(false);
        textFieldMorada.setEnabled(false);

        labelDataNascimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDataNascimento.setText("Data Nascimento");

        textFieldNif.setEditable(false);
        textFieldNif.setEnabled(false);

        labelNif.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNif.setText("NIF");

        textFieldDataNascimento.setEditable(false);
        textFieldDataNascimento.setEnabled(false);

        textFieldEmail.setEditable(false);
        textFieldEmail.setEnabled(false);

        textFieldNumeroTelefone.setEditable(false);
        textFieldNumeroTelefone.setEnabled(false);

        labelNumeroTelefone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNumeroTelefone.setText("Número Telefone");

        labelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEmail.setText("E-mail");

        labelMorada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMorada.setText("Morada");

        textFieldNome.setEditable(false);
        textFieldNome.setEnabled(false);

        buttonProcurarEntidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/magnify.png"))); // NOI18N
        buttonProcurarEntidade.setText("Procurar");
        buttonProcurarEntidade.setToolTipText("Procurar entidade");
        buttonProcurarEntidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProcurarEntidadeActionPerformed(evt);
            }
        });

        buttonRemoverEntidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        buttonRemoverEntidade.setText("Remover");
        buttonRemoverEntidade.setToolTipText("Remover entidade");
        buttonRemoverEntidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverEntidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEntidadeLayout = new javax.swing.GroupLayout(panelEntidade);
        panelEntidade.setLayout(panelEntidadeLayout);
        panelEntidadeLayout.setHorizontalGroup(
            panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldMorada, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEntidadeLayout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRemoverEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonProcurarEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEntidadeLayout.createSequentialGroup()
                        .addComponent(labelMorada)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEntidadeLayout.createSequentialGroup()
                        .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEntidadeLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(textFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldNif, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldNumeroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEntidadeLayout.createSequentialGroup()
                                .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelEntidadeLayout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(labelNif, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelDataNascimento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNumeroTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                    .addComponent(textFieldNome))
                .addContainerGap())
        );
        panelEntidadeLayout.setVerticalGroup(
            panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntidadeLayout.createSequentialGroup()
                .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNome)
                        .addComponent(buttonProcurarEntidade))
                    .addComponent(buttonRemoverEntidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataNascimento)
                    .addComponent(labelNif)
                    .addComponent(labelNumeroTelefone)
                    .addComponent(labelEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldNumeroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMorada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldMorada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelTotais.setBackground(new java.awt.Color(255, 255, 255));
        panelTotais.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        textFieldTotal.setEditable(false);
        textFieldTotal.setEnabled(false);

        labelTotal.setText("Total transção:");

        labelTotal1.setText("Total a pagar:");

        textFieldTotalPagar.setEditable(false);
        textFieldTotalPagar.setEnabled(false);

        javax.swing.GroupLayout panelTotaisLayout = new javax.swing.GroupLayout(panelTotais);
        panelTotais.setLayout(panelTotaisLayout);
        panelTotaisLayout.setHorizontalGroup(
            panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTotaisLayout.createSequentialGroup()
                        .addComponent(labelTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTotaisLayout.createSequentialGroup()
                        .addComponent(labelTotal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTotaisLayout.setVerticalGroup(
            panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotaisLayout.createSequentialGroup()
                .addGroup(panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotal)
                    .addComponent(textFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotal1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDetalhesTransacao.setBackground(new java.awt.Color(255, 255, 255));
        panelDetalhesTransacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalhes da Transação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tableDetalhes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPaneDetalhes.setViewportView(tableDetalhes);

        buttonAdicionarDetalhe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/plus.png"))); // NOI18N
        buttonAdicionarDetalhe.setToolTipText("Remover detalhes da transação");
        buttonAdicionarDetalhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarDetalheActionPerformed(evt);
            }
        });

        buttonRemoverDetalhe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        buttonRemoverDetalhe.setToolTipText("Adicionar detalhes da transação");
        buttonRemoverDetalhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverDetalheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDetalhesTransacaoLayout = new javax.swing.GroupLayout(panelDetalhesTransacao);
        panelDetalhesTransacao.setLayout(panelDetalhesTransacaoLayout);
        panelDetalhesTransacaoLayout.setHorizontalGroup(
            panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalhesTransacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetalhesTransacaoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonAdicionarDetalhe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoverDetalhe))
                    .addComponent(scrollPaneDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDetalhesTransacaoLayout.setVerticalGroup(
            panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalhesTransacaoLayout.createSequentialGroup()
                .addGroup(panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRemoverDetalhe)
                    .addComponent(buttonAdicionarDetalhe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelEdicaoLayout = new javax.swing.GroupLayout(panelEdicao);
        panelEdicao.setLayout(panelEdicaoLayout);
        panelEdicaoLayout.setHorizontalGroup(
            panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTipo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textFieldData, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(labelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(panelVeiculosATroca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelEntidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTotais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(panelDetalhesTransacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelEdicaoLayout.setVerticalGroup(
            panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(labelData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addComponent(panelVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelVeiculosATroca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addComponent(panelEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTotais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12)
                .addComponent(panelDetalhesTransacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        acaoGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonProcurarEntidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProcurarEntidadeActionPerformed

        try {
            var entidadeSelecionada = new JanelaSelecao<>(tipoTransacao == TipoTransacao.COMPRA ? "Selecionar Vendedor..." : "Selecionar Cliente...",
                    GestorEntidades.getInstance(),
                    (e) -> e.getTipoEntidade().equals(tipoTransacao == TipoTransacao.COMPRA ? TipoEntidade.VENDEDOR : TipoEntidade.CLIENTE),
                    false).showDialog().getFirst();

            setEntidade(entidadeSelecionada);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_buttonProcurarEntidadeActionPerformed

    private void buttonRemoverEntidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverEntidadeActionPerformed
        setEntidade(null);
    }//GEN-LAST:event_buttonRemoverEntidadeActionPerformed

    private void buttonAdicionarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarVeiculoActionPerformed
        try {
            var veiculoSelecionado = new JanelaSelecao<>(tipoTransacao == TipoTransacao.COMPRA ? "Selecionar veículo a comprar..." : "Selecionar veículo a vender...",
                    GestorVeiculos.getInstance(),
                    (veiculo) -> !listModelVeiculosATroca.contains(veiculo) && !listModelVeiculos.contains(veiculo),
                    false).showDialog().getFirst();

            listModelVeiculos.addElement(veiculoSelecionado);

            atualizarTotalVeiculos();
            atualizarDetalhesTransacao();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_buttonAdicionarVeiculoActionPerformed

    private void buttonRemoverVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverVeiculoActionPerformed

        var veiculoSelecionado = listVeiculos.getSelectedValue();

        if (veiculoSelecionado == null) {
            return;
        }

        listModelVeiculos.removeElement(veiculoSelecionado);

        atualizarTotalVeiculos();
        atualizarDetalhesTransacao();
    }//GEN-LAST:event_buttonRemoverVeiculoActionPerformed

    private void buttonAdicionarVeiculoATrocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarVeiculoATrocaActionPerformed
        try {
            var veiculoSelecionado = new JanelaSelecao<>("Selecionar veículo à troca...",
                    GestorVeiculos.getInstance(),
                    (veiculo) -> !listModelVeiculosATroca.contains(veiculo) && !listModelVeiculos.contains(veiculo),
                    false).showDialog().getFirst();

            listModelVeiculosATroca.addElement(veiculoSelecionado);

            atualizarTotalVeiculosATroca();
            atualizarDetalhesTransacao();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_buttonAdicionarVeiculoATrocaActionPerformed

    private void butonRemoverVeiculoATrocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonRemoverVeiculoATrocaActionPerformed
        var veiculoSelecionado = listVeiculosATroca.getSelectedValue();

        if (veiculoSelecionado == null) {
            return;
        }

        listModelVeiculosATroca.removeElement(veiculoSelecionado);

        atualizarTotalVeiculosATroca();
        atualizarDetalhesTransacao();
    }//GEN-LAST:event_butonRemoverVeiculoATrocaActionPerformed

    private void buttonRemoverDetalheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverDetalheActionPerformed
        int row = tableDetalhes.getSelectedRow();
        if (row < 0) {
            return;
        }

        var detalhe = (DetalhesTransacao) tableDetalhes.getValueAt(row, columns.length - 1);
        if (detalhe.getTipoDetalheTransacao().equals(TipoDetalheTransacao.TOTAL_VEICULOS)
                || detalhe.getTipoDetalheTransacao().equals(TipoDetalheTransacao.TOTAL_VEICULOS_TROCA)) {
            return;
        }

        detalhesTransacao.remove(detalhe);

        tableModelDetalhes.setData(getTableData());

        atualizarDetalhesTransacao();
    }//GEN-LAST:event_buttonRemoverDetalheActionPerformed

    private void buttonAdicionarDetalheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarDetalheActionPerformed
        var detalhe = new JanelaEditarDetalheTransacao().showDialog();

        detalhesTransacao.add(detalhe);

        atualizarDetalhesTransacao();
    }//GEN-LAST:event_buttonAdicionarDetalheActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonRemoverVeiculoATroca;
    private javax.swing.JButton buttonAdicionarDetalhe;
    private javax.swing.JButton buttonAdicionarVeiculo;
    private javax.swing.JButton buttonAdicionarVeiculoATroca;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonProcurarEntidade;
    private javax.swing.JButton buttonRemoverDetalhe;
    private javax.swing.JButton buttonRemoverEntidade;
    private javax.swing.JButton buttonRemoverVeiculo;
    private javax.swing.JComboBox<TipoTransacao> comboBoxTipo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelMorada;
    private javax.swing.JLabel labelNif;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNumeroTelefone;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotal1;
    private javax.swing.JList<Veiculo> listVeiculos;
    private javax.swing.JList<Veiculo> listVeiculosATroca;
    private javax.swing.JPanel panelDetalhesTransacao;
    private javax.swing.JPanel panelEdicao;
    private javax.swing.JPanel panelEntidade;
    private javax.swing.JPanel panelTotais;
    private javax.swing.JPanel panelVeiculos;
    private javax.swing.JPanel panelVeiculosATroca;
    private javax.swing.JScrollPane scrollPaneDetalhes;
    private javax.swing.JScrollPane scrollPaneVeiculosATroca;
    private javax.swing.JScrollPane scroolPaneVeiculos;
    private javax.swing.JTable tableDetalhes;
    private javax.swing.JTextField textFieldData;
    private javax.swing.JTextField textFieldDataNascimento;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldMorada;
    private javax.swing.JTextField textFieldNif;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldNumeroTelefone;
    private javax.swing.JTextField textFieldTotal;
    private javax.swing.JTextField textFieldTotalPagar;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
