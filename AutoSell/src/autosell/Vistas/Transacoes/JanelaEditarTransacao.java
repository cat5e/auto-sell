package autosell.Vistas.Transacoes;

import autosell.Enumeracoes.TipoTransacao;
import autosell.Modelos.*;
import autosell.Utils.TableModel;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class JanelaEditarTransacao extends javax.swing.JInternalFrame {

    private Transacao transacao;
    private Colaborador colaboradorAutenticado;
    private DefaultListModel listModelVeiculos;
    private DefaultListModel listModelVeiculosATroca;
    private final TableModel tableModelDetalhes;
    private String[] columns;
    private LinkedList<DetalhesTransacao> detalhesTransacao;
    
    
    public JanelaEditarTransacao(Transacao transacao, Colaborador colaboradorAutenticado) {
        this.transacao = transacao;
        this.colaboradorAutenticado = colaboradorAutenticado;
        detalhesTransacao = new LinkedList<>();
        initComponents();
        
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
            "Obj"};
        tableModelDetalhes = new TableModel(columns, getTableData());
        tableDetalhes.setModel(tableModelDetalhes);
        tableDetalhes.getColumnModel().getColumn(columns.length - 1).setMinWidth(0);
        tableDetalhes.getColumnModel().getColumn(columns.length - 1).setMaxWidth(0);
        tableDetalhes.getColumnModel().getColumn(columns.length - 1).setWidth(0);
    }

    private void loadTipoTransacao() {
        comboBoxTipo.setModel(new DefaultComboBoxModel(TipoTransacao.values()));
    }

    private void popularDados() {
        comboBoxTipo.setSelectedItem(transacao.getTipo());
        textFieldData.setText(transacao.getData());
        var entidade = transacao.getEntidade();
        textFieldNome.setText(entidade.getNome());
        textFieldDataNascimento.setText(entidade.getDataNascimento());
        textFieldNif.setText(entidade.getNif());
        textFieldNumeroTelefone.setText(entidade.getNumeroTelefone());
        textFieldEmail.setText(entidade.getEmail());
        textFieldMorada.setText(entidade.getMorada());
        textFieldTotal.setText(String.valueOf(transacao.getPrecoFinal()));
        
        
        var veiculosAssociados = transacao.getVeiculosAssociados();        
        for(Veiculo veiculoAssociado : veiculosAssociados) {
            listModelVeiculos.addElement(veiculoAssociado);
        }
        
        var veiculosATroca = transacao.getVeiculosTroca();
        for(Veiculo veiculo : veiculosATroca) {
            listModelVeiculosATroca.addElement(veiculo);
        }
    }

     private Object[][] getTableData() {
        int i = 0;
        var aux = new Object[detalhesTransacao.size()][columns.length];
        for (DetalhesTransacao detalhe : detalhesTransacao) {
            aux[i][0] = detalhe.getDescricao();
            aux[i][1] = detalhe.getDetalhes();
            aux[i][2] = detalhe.getValor();
            aux[i][5] = detalhe;
            i++;
        }
        return aux;
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
        try {
            listVeiculosATroca =(javax.swing.JList)java.beans.Beans.instantiate(getClass().getClassLoader(), "autosell/Vistas/Transacoes.JanelaEditarTransacao_listVeiculosATroca");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
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
        panelTotais = new javax.swing.JPanel();
        textFieldTotal = new javax.swing.JTextField();
        labelTotal = new javax.swing.JLabel();
        panelDetalhesTransacao = new javax.swing.JPanel();
        scrollPaneDetalhes = new javax.swing.JScrollPane();
        tableDetalhes = new javax.swing.JTable();
        buttonAdicionarDetalhe = new javax.swing.JButton();
        buttonRemoverDetalhe = new javax.swing.JButton();

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

        comboBoxTipo.setName("Tipo Transação"); // NOI18N

        labelData.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelData.setText("Data");

        textFieldData.setName("Data da Transação"); // NOI18N

        panelVeiculos.setBackground(new java.awt.Color(255, 255, 255));
        panelVeiculos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        buttonAdicionarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/plus.png"))); // NOI18N
        buttonAdicionarVeiculo.setToolTipText("Adicionar Veículo");

        buttonRemoverVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        buttonRemoverVeiculo.setToolTipText("Remover Veículo");

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

        butonRemoverVeiculoATroca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        butonRemoverVeiculoATroca.setToolTipText("Remover veículo à troca");

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

        labelDataNascimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDataNascimento.setText("Data Nascimento");

        textFieldNif.setEditable(false);

        labelNif.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNif.setText("NIF");

        textFieldDataNascimento.setEditable(false);

        textFieldEmail.setEditable(false);

        textFieldNumeroTelefone.setEditable(false);

        labelNumeroTelefone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNumeroTelefone.setText("Número Telefone");

        labelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEmail.setText("E-mail");

        labelMorada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMorada.setText("Morada");

        textFieldNome.setEditable(false);

        buttonProcurarEntidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/magnify.png"))); // NOI18N
        buttonProcurarEntidade.setText("Procurar");
        buttonProcurarEntidade.setToolTipText("Procurar entidade");

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
                .addGroup(panelEntidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(buttonProcurarEntidade))
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

        labelTotal.setText("Total de Transação:");

        javax.swing.GroupLayout panelTotaisLayout = new javax.swing.GroupLayout(panelTotais);
        panelTotais.setLayout(panelTotaisLayout);
        panelTotaisLayout.setHorizontalGroup(
            panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTotaisLayout.setVerticalGroup(
            panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotaisLayout.createSequentialGroup()
                .addGroup(panelTotaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotal)
                    .addComponent(textFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
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

        buttonAdicionarDetalhe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        buttonAdicionarDetalhe.setToolTipText("Remover detalhes da transação");

        buttonRemoverDetalhe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/plus.png"))); // NOI18N
        buttonRemoverDetalhe.setToolTipText("Adicionar detalhes da transação");

        javax.swing.GroupLayout panelDetalhesTransacaoLayout = new javax.swing.GroupLayout(panelDetalhesTransacao);
        panelDetalhesTransacao.setLayout(panelDetalhesTransacaoLayout);
        panelDetalhesTransacaoLayout.setHorizontalGroup(
            panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalhesTransacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetalhesTransacaoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonRemoverDetalhe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdicionarDetalhe))
                    .addComponent(scrollPaneDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDetalhesTransacaoLayout.setVerticalGroup(
            panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalhesTransacaoLayout.createSequentialGroup()
                .addGroup(panelDetalhesTransacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdicionarDetalhe)
                    .addComponent(buttonRemoverDetalhe))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        //acaoGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonRemoverVeiculoATroca;
    private javax.swing.JButton buttonAdicionarDetalhe;
    private javax.swing.JButton buttonAdicionarVeiculo;
    private javax.swing.JButton buttonAdicionarVeiculoATroca;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonProcurarEntidade;
    private javax.swing.JButton buttonRemoverDetalhe;
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
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}