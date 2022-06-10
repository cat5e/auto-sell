package autosell.Vistas.Intervencoes;

import autosell.CustomExceptions.CustomExeption;
import autosell.Gestores.GestorArmazenamentoDados;
import autosell.Gestores.GestorArtigos;
import autosell.Gestores.GestorIntervencoes;
import autosell.Gestores.GestorVeiculos;
import autosell.Modelos.Artigo;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Consumivel;
import autosell.Modelos.Estabelecimento;
import autosell.Modelos.Intervencao;
import autosell.Modelos.Veiculo;
import autosell.Utils.AppLogger;
import autosell.Utils.TableModel;
import static autosell.Utils.ValidacoesUtils.validacaoComponente;
import autosell.Vistas.JanelaSelecao;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class JanelaEditarIntervencao extends javax.swing.JInternalFrame {

    private Intervencao intervencao;
    private final TableModel tableModel;
    private Veiculo veiculo;
    private Estabelecimento estabelecimento;
    private LinkedList<Consumivel> consumiveis;
    private final String[] columns;
    private Colaborador tecnico;

    public JanelaEditarIntervencao(Intervencao intervencao, Colaborador tecnico) {
        this.intervencao = intervencao;
        this.tecnico = tecnico;
        consumiveis = new LinkedList<>();
        initComponents();

        if (intervencao != null) {
            popularDados();
            bloquearCampos();
        }

        columns = new String[]{
            "Referência",
            "Nome",
            "Quantidade",
            "Unidade",
            "Obj"};
        tableModel = new TableModel(columns, getTableData());
        tableArtigos.setModel(tableModel);
        tableArtigos.getColumnModel().getColumn(columns.length - 1).setMinWidth(0);
        tableArtigos.getColumnModel().getColumn(columns.length - 1).setMaxWidth(0);
        tableArtigos.getColumnModel().getColumn(columns.length - 1).setWidth(0);
    }

    private void popularDados() {
        textFieldDataIntervencao.setText(intervencao.getData());
        textAreaDescricao.setText(intervencao.getDescricao());
        veiculo = intervencao.getVeiculo();
        popularDadosVeiculo();
        estabelecimento = intervencao.getEstabelecimento();
        textFieldLocalIntervencao.setText(estabelecimento.getNome());
        consumiveis = intervencao.getConsumiveis();
    }
    
    private void popularDadosVeiculo() {
        textFieldMatricula.setText(veiculo != null ? veiculo.getMatricula() : "");
        textFieldMarca.setText(veiculo != null ? veiculo.getMarca() : "");
        textFieldModelo.setText(veiculo != null ? veiculo.getModelo() : "");
        textFieldEstabelecimentoVeiculo.setText(veiculo != null ? veiculo.getEstabelecimento().getNome() : "");
    }

    private void bloquearCampos() {
        textFieldDataIntervencao.setEditable(false);
        textAreaDescricao.setEditable(false);
        buttonAdicionarArtigo.setEnabled(false);
        buttonRemoverArtigo.setEnabled(false);
        buttonAdicionarVeiculo.setEnabled(false);
        buttonRemoverVeiculo.setEnabled(false);
        buttonGuardar.setEnabled(false);
    }

    private void acaoGuardar() {
        try {
            if (!isDadosValidos()) {
                return;
            }

            if (intervencao == null) {
                intervencao = new Intervencao(
                        textFieldDataIntervencao.getText(),
                        veiculo,
                        textAreaDescricao.getText(),
                        tecnico,
                        tecnico.getEstabelecimento());

                for (Consumivel consumivel : consumiveis) {
                    var artigo = consumivel.getArtigoReferencia();
                    artigo.setQuantidadeAtual(artigo.getQuantidadeAtual() - consumivel.getQuantidade());
                    intervencao.adidionarConsumivel(consumivel);
                }

                if (!GestorIntervencoes.getInstance().adicionar(intervencao)) {
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
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
        JComponent componentesAValidar[] = {textFieldDataIntervencao, textAreaDescricao};

        for (JComponent jComponent : componentesAValidar) {
            if (!validacaoComponente(this, jComponent)) {
                return false;
            }
        }

        if (veiculo == null) {
            JOptionPane.showMessageDialog(this, "Para criar uma intervenção tem de estar associado um veículo.",
                    "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        for (Consumivel consumivel : consumiveis) {
            if (!validarQuantidades(consumivel.getArtigoReferencia(), consumivel.getQuantidade())) {
                return false;
            }
        }

        return true;
    }

    private Object[][] getTableData() {
        int i = 0;
        var aux = new Object[consumiveis.size()][columns.length];
        for (Consumivel consumivel : consumiveis) {
            aux[i][0] = consumivel.getReferencia();
            aux[i][1] = consumivel.getNome();
            aux[i][2] = consumivel.getQuantidade();
            aux[i][3] = consumivel.getUnidade();
            aux[i][5] = consumivel;
            i++;
        }
        return aux;
    }

    private boolean validarQuantidades(Artigo artigo, float quantidadeSelecionada) {
        float quantidadeFinal = artigo.getQuantidadeAtual() - quantidadeSelecionada;
        if (quantidadeFinal <= 0) {
            JOptionPane.showMessageDialog(this, "Não existe quantidades disponíveis sufecientes.",
                    "Quantidade inválida", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (quantidadeFinal <= artigo.getQuantidadeMinima()) {
            int opcaoSelecionda = JOptionPane.showConfirmDialog(this, "A quantidade final do artigo fica abaixo da quantidade mínima. Deseja prosseguir?", "Quantidade mínima",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcaoSelecionda != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBarMenu = new javax.swing.JToolBar();
        buttonGuardar = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        textFieldLocalIntervencao = new javax.swing.JTextField();
        labelDataIntervencao = new javax.swing.JLabel();
        labelDataFim = new javax.swing.JLabel();
        scrollPanelVeiculos = new javax.swing.JScrollPane();
        tableArtigos = new javax.swing.JTable();
        buttonAdicionarVeiculo = new javax.swing.JButton();
        buttonRemoverVeiculo = new javax.swing.JButton();
        panelVeiculo = new javax.swing.JPanel();
        textFieldModelo = new javax.swing.JTextField();
        labelMatricula = new javax.swing.JLabel();
        textFieldMatricula = new javax.swing.JTextField();
        labelMarca = new javax.swing.JLabel();
        textFieldMarca = new javax.swing.JTextField();
        textFieldEstabelecimentoVeiculo = new javax.swing.JTextField();
        labelModelo = new javax.swing.JLabel();
        labelEstabelecimento = new javax.swing.JLabel();
        buttonRemoverArtigo = new javax.swing.JButton();
        buttonAdicionarArtigo = new javax.swing.JButton();
        labelArtigo = new javax.swing.JLabel();
        scrollPaneDescricao = new javax.swing.JScrollPane();
        textAreaDescricao = new javax.swing.JTextArea();
        textFieldDataIntervencao = new javax.swing.JTextField();
        labelLocalIntervencao = new javax.swing.JLabel();

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

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        textFieldLocalIntervencao.setEditable(false);
        textFieldLocalIntervencao.setName("Data Inicio"); // NOI18N

        labelDataIntervencao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDataIntervencao.setText("Data da intervenção");

        labelDataFim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDataFim.setText("Descricação da intervenção");

        tableArtigos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPanelVeiculos.setViewportView(tableArtigos);

        buttonAdicionarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/plus.png"))); // NOI18N
        buttonAdicionarVeiculo.setToolTipText("Adicionar Veículo");
        buttonAdicionarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarVeiculoActionPerformed(evt);
            }
        });

        buttonRemoverVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        buttonRemoverVeiculo.setToolTipText("Remover Veículos");
        buttonRemoverVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverVeiculoActionPerformed(evt);
            }
        });

        panelVeiculo.setBackground(new java.awt.Color(255, 255, 255));
        panelVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        textFieldModelo.setEditable(false);
        textFieldModelo.setName("Local"); // NOI18N

        labelMatricula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMatricula.setText("Matrícula");

        textFieldMatricula.setEditable(false);
        textFieldMatricula.setName("Local"); // NOI18N

        labelMarca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMarca.setText("Marca");

        textFieldMarca.setEditable(false);
        textFieldMarca.setName("Local"); // NOI18N

        textFieldEstabelecimentoVeiculo.setEditable(false);
        textFieldEstabelecimentoVeiculo.setName("Data Fim"); // NOI18N

        labelModelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelModelo.setText("Modelo");

        labelEstabelecimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstabelecimento.setText("Estabelecimento");

        javax.swing.GroupLayout panelVeiculoLayout = new javax.swing.GroupLayout(panelVeiculo);
        panelVeiculo.setLayout(panelVeiculoLayout);
        panelVeiculoLayout.setHorizontalGroup(
            panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(labelMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelVeiculoLayout.createSequentialGroup()
                        .addComponent(textFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVeiculoLayout.createSequentialGroup()
                        .addComponent(labelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldEstabelecimentoVeiculo)
                    .addComponent(labelEstabelecimento, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVeiculoLayout.setVerticalGroup(
            panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVeiculoLayout.createSequentialGroup()
                .addGroup(panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVeiculoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelEstabelecimento)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVeiculoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMatricula)
                            .addComponent(labelMarca)
                            .addComponent(labelModelo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldEstabelecimentoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonRemoverArtigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/minus.png"))); // NOI18N
        buttonRemoverArtigo.setToolTipText("Remover Artigo");
        buttonRemoverArtigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverArtigoActionPerformed(evt);
            }
        });

        buttonAdicionarArtigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/plus.png"))); // NOI18N
        buttonAdicionarArtigo.setToolTipText("Adicionar Artigo");
        buttonAdicionarArtigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarArtigoActionPerformed(evt);
            }
        });

        labelArtigo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelArtigo.setText("Artigos");

        textAreaDescricao.setColumns(20);
        textAreaDescricao.setRows(5);
        textAreaDescricao.setName("Descrição da intervenção"); // NOI18N
        scrollPaneDescricao.setViewportView(textAreaDescricao);

        textFieldDataIntervencao.setName("Data da Intervenção"); // NOI18N

        labelLocalIntervencao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLocalIntervencao.setText("Local da intervenção");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanelVeiculos)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(labelArtigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdicionarArtigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoverArtigo))
                    .addComponent(labelDataFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneDescricao)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(panelVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPrincipalLayout.createSequentialGroup()
                                    .addComponent(textFieldDataIntervencao, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textFieldLocalIntervencao)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buttonAdicionarVeiculo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buttonRemoverVeiculo)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                            .addComponent(labelDataIntervencao, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelLocalIntervencao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataIntervencao)
                    .addComponent(labelLocalIntervencao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonAdicionarVeiculo)
                    .addComponent(buttonRemoverVeiculo)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldLocalIntervencao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFieldDataIntervencao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(panelVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDataFim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelArtigo)
                    .addComponent(buttonAdicionarArtigo)
                    .addComponent(buttonRemoverArtigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        acaoGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonAdicionarArtigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarArtigoActionPerformed
        var artigoSelecionado = new JanelaSelecao<>("Selecionar artigo...",
                GestorArtigos.getInstance(),
                (artigo) -> artigo.getEstabelecimento().equals(tecnico.getEstabelecimento()),
                false).showDialog().getFirst();

        if (artigoSelecionado == null) {
            return;
        }

        float quantidadeSelecionada;

        try {
            var input = JOptionPane.showInputDialog(this, "Quantidade do artigo selecionado", "Selecionar quantidade");

            quantidadeSelecionada = Float.parseFloat(input);
            if (quantidadeSelecionada < 0) {
                JOptionPane.showMessageDialog(this, "A quantidade selecionada não é valida.",
                        "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "A quantidade selecionada não é valida.",
                    "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarQuantidades(artigoSelecionado, quantidadeSelecionada)) {
            return;
        }

        consumiveis.add(new Consumivel(
                artigoSelecionado.getReferencia(),
                artigoSelecionado.getNome(),
                quantidadeSelecionada,
                artigoSelecionado.getUnidade(),
                artigoSelecionado));

        tableModel.setData(getTableData());
    }//GEN-LAST:event_buttonAdicionarArtigoActionPerformed

    private void buttonRemoverArtigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverArtigoActionPerformed
        var consumiveSelecionado = (Consumivel) tableArtigos.getValueAt(tableArtigos.getSelectedRow(), columns.length - 1);

        int opcaoSelecionda = JOptionPane.showConfirmDialog(this, "Deseja remover o artigo selecionado?", "Remover artigo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcaoSelecionda != 0) {
            return;
        }

        consumiveis.remove(consumiveSelecionado);
        tableModel.setData(getTableData());

    }//GEN-LAST:event_buttonRemoverArtigoActionPerformed

    private void buttonRemoverVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverVeiculoActionPerformed
        int opcaoSelecionda = JOptionPane.showConfirmDialog(this, "Deseja remover o veículo selecionado?", "Remover veículo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcaoSelecionda != 0) {
            return;
        }
        
        veiculo = null;
        
        popularDadosVeiculo();
    }//GEN-LAST:event_buttonRemoverVeiculoActionPerformed

    private void buttonAdicionarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarVeiculoActionPerformed
        var veiculoSelecionado = new JanelaSelecao<>("Selecionar veículo...",
                GestorVeiculos.getInstance(),
                (v) -> v.getEstabelecimento().equals(tecnico.getEstabelecimento()),
                false).showDialog().getFirst();

        if (veiculoSelecionado == null) {
            return;
        }

        veiculo = veiculoSelecionado;

        popularDadosVeiculo();

    }//GEN-LAST:event_buttonAdicionarVeiculoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionarArtigo;
    private javax.swing.JButton buttonAdicionarVeiculo;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonRemoverArtigo;
    private javax.swing.JButton buttonRemoverVeiculo;
    private javax.swing.JLabel labelArtigo;
    private javax.swing.JLabel labelDataFim;
    private javax.swing.JLabel labelDataIntervencao;
    private javax.swing.JLabel labelEstabelecimento;
    private javax.swing.JLabel labelLocalIntervencao;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelModelo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelVeiculo;
    private javax.swing.JScrollPane scrollPaneDescricao;
    private javax.swing.JScrollPane scrollPanelVeiculos;
    private javax.swing.JTable tableArtigos;
    private javax.swing.JTextArea textAreaDescricao;
    private javax.swing.JTextField textFieldDataIntervencao;
    private javax.swing.JTextField textFieldEstabelecimentoVeiculo;
    private javax.swing.JTextField textFieldLocalIntervencao;
    private javax.swing.JTextField textFieldMarca;
    private javax.swing.JTextField textFieldMatricula;
    private javax.swing.JTextField textFieldModelo;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
