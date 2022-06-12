package autosell.Vistas.Veiculos;

import autosell.CustomExceptions.CustomExeption;
import autosell.Enumeracoes.ClassesVeiculos;
import autosell.Enumeracoes.EstadoVeiculo;
import autosell.Enumeracoes.MesesAno;
import autosell.Enumeracoes.TipoCombustivel;
import autosell.Gestores.GestorArmazenamentoDados;
import autosell.Gestores.GestorEstabelecimentos;
import autosell.Gestores.GestorVeiculos;
import autosell.Modelos.Estabelecimento;
import autosell.Modelos.Veiculo;
import autosell.Utils.AppLogger;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static autosell.Utils.ValidacoesUtils.isComponenteVazio;
import static autosell.Utils.ValidacoesUtils.isNullOrEmpty;
import static autosell.Utils.ValidacoesUtils.isNumberGreaterOrEqualThanZero;
import static autosell.Utils.ValidacoesUtils.isNumericValue;
import javax.swing.JTextField;

// TODO: Implementar adicionar/remover foto do veículo
// TODO: Carregar todas as listagens
// TODO: Implementar e apresentar validação se o veículo está num evento
// TODO: Verificar se é necessário implementar o estado do veículo.

public class JanelaEditarVeiculo extends javax.swing.JInternalFrame {

    private Veiculo veiculo;

    public JanelaEditarVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        initComponents();
        toolBarMenu.setFloatable(false);

        loadMeses();
        loadMarcas();
        loadCombustivel();
        loadClasses();
        loadEstadoVeiculo();
        loadEstabelecimentos();

        if (veiculo != null) {
            popularDados();
        }
    }

    private void loadMeses() {
        comboBoxMes.setModel(new DefaultComboBoxModel(MesesAno.values()));
    }

    private void loadMarcas() {
        comboBoxMarca.setModel(new DefaultComboBoxModel(GestorVeiculos.getInstance().getMarcasRegistadas()));
    }

    private void loadCombustivel() {
        comboBoxCombustivel.setModel(new DefaultComboBoxModel(TipoCombustivel.values()));
    }

    private void loadClasses() {
        comboBoxClasse.setModel(new DefaultComboBoxModel(ClassesVeiculos.values()));
    }

    private void loadEstadoVeiculo() {
        comboBoxEstadoVeiculo.setModel(new DefaultComboBoxModel(EstadoVeiculo.values()));
    }

    private void loadEstabelecimentos() {
        var estabelecimentos = GestorEstabelecimentos.getInstance().getListagem();

        for (Estabelecimento estabelecimento : estabelecimentos) {
            comboBoxEstabelecimento.addItem(estabelecimento);
        }
    }

    private void popularDados() {
        textFieldPreco.setText(String.valueOf(veiculo.getPreco()));
        textFieldMatricula.setText(veiculo.getMatricula());
        textFieldQuilometros.setText(String.valueOf(veiculo.getQuilometros()));
        textFieldAno.setText(String.valueOf(veiculo.getAnoRegisto()));
        comboBoxMes.setSelectedItem(veiculo.getMesRegisto());
        comboBoxMarca.setSelectedItem(veiculo.getMarca());
        textFieldModelo.setText(veiculo.getModelo());
        textFieldCor.setText(veiculo.getCor());
        comboBoxCombustivel.setSelectedItem(veiculo.getCombustivel());
        textFieldCilindrada.setText(String.valueOf(veiculo.getCelindrada()));
        textFieldPotencia.setText(String.valueOf(veiculo.getPotencia()));
        textFieldLotacao.setText(String.valueOf(veiculo.getLotacao()));
        comboBoxClasse.setSelectedItem(veiculo.getClasse());
        textFieldMundacas.setText(String.valueOf(veiculo.getNumeorMudancas()));
        textFieldNumPortas.setText(String.valueOf(veiculo.getNumeroPortas()));
        comboBoxEstadoVeiculo.setSelectedItem(veiculo.getEstadoVeiculo());
        comboBoxEstabelecimento.setSelectedItem(veiculo.getEstabelecimento());
        // TODO: Verificar por eventos
        //textFieldEvento.setText(veiculo.getEv);
        textAreaCaracteristicas.setText(veiculo.getCaracteristicas());
        textAreaObservacoes.setText(veiculo.getObservacoes());
    }

    private void acaoGuardar() {
        try {
            if (!isDadosValidos()) {
                return;
            }

            if (veiculo == null) {
                veiculo = new Veiculo(
                        Float.parseFloat(textFieldPreco.getText()),
                        textFieldMatricula.getText(),
                        comboBoxMarca.getSelectedItem().toString(),
                        (Estabelecimento) comboBoxEstabelecimento.getSelectedItem());

                veiculo.setQuilometros(Integer.parseInt(textFieldQuilometros.getText()));
                veiculo.setAnoRegisto(Integer.parseInt(textFieldAno.getText()));
                veiculo.setMesRegisto((MesesAno) comboBoxMes.getSelectedItem());
                veiculo.setModelo(textFieldModelo.getText());
                veiculo.setCor(textFieldCor.getText());
                veiculo.setCombustivel((TipoCombustivel) comboBoxCombustivel.getSelectedItem());
                veiculo.setCelindrada(Float.parseFloat(textFieldCilindrada.getText()));
                veiculo.setPotencia(Float.parseFloat(textFieldPotencia.getText()));
                veiculo.setLotacao(Integer.parseInt(textFieldLotacao.getText()));
                veiculo.setClasse((ClassesVeiculos) comboBoxClasse.getSelectedItem());
                veiculo.setNumeorMudancas(Integer.parseInt(textFieldMundacas.getText()));
                veiculo.setNumeroPortas(Integer.parseInt(textFieldNumPortas.getText()));
                veiculo.setEstadoVeiculo((EstadoVeiculo) comboBoxEstadoVeiculo.getSelectedItem());
                veiculo.setCaracteristicas(textAreaCaracteristicas.getText());
                veiculo.setObservacoes(textAreaObservacoes.getText());

                if (!GestorVeiculos.getInstance().adicionar(veiculo)) {
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
            } else {
                veiculo.setPreco(Float.parseFloat(textFieldPreco.getText()));
                veiculo.setMatricula(textFieldMatricula.getText());
                veiculo.setEstabelecimento((Estabelecimento) comboBoxEstabelecimento.getSelectedItem());
                veiculo.setMarca(comboBoxMarca.getSelectedItem().toString());
                veiculo.setQuilometros(Integer.parseInt(textFieldQuilometros.getText()));
                veiculo.setAnoRegisto(Integer.parseInt(textFieldAno.getText()));
                veiculo.setMesRegisto((MesesAno) comboBoxMes.getSelectedItem());
                veiculo.setModelo(textFieldModelo.getText());
                veiculo.setCor(textFieldCor.getText());
                veiculo.setCombustivel((TipoCombustivel) comboBoxCombustivel.getSelectedItem());
                veiculo.setCelindrada(Float.parseFloat(textFieldCilindrada.getText()));
                veiculo.setPotencia(Float.parseFloat(textFieldPotencia.getText()));
                veiculo.setLotacao(Integer.parseInt(textFieldLotacao.getText()));
                veiculo.setClasse((ClassesVeiculos) comboBoxClasse.getSelectedItem());
                veiculo.setNumeorMudancas(Integer.parseInt(textFieldMundacas.getText()));
                veiculo.setNumeroPortas(Integer.parseInt(textFieldNumPortas.getText()));
                veiculo.setEstadoVeiculo((EstadoVeiculo) comboBoxEstadoVeiculo.getSelectedItem());
                veiculo.setCaracteristicas(textAreaCaracteristicas.getText());
                veiculo.setObservacoes(textAreaObservacoes.getText());
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
        JComponent componentesAValidar[] = {
            textFieldPreco,
            textFieldMatricula,
            comboBoxMarca,
            comboBoxEstabelecimento};

        for (JComponent jComponent : componentesAValidar) {
            if (!isComponenteVazio(this, jComponent)) {
                return false;
            }
        }

        JTextField componentesNumericosAValidar[] = {
            textFieldAno,
            textFieldCilindrada,
            textFieldLotacao,
            textFieldMundacas,
            textFieldNumPortas,
            textFieldPotencia,
            textFieldPreco,
            textFieldQuilometros};

        for (JTextField textField : componentesNumericosAValidar) {
            if (isNullOrEmpty(textField.getText())) {
                continue;
            }

            if (isNumericValue(this, textField) && isNumberGreaterOrEqualThanZero(this, textField)) {
                return false;
            }
        }

        if (GestorVeiculos.getInstance().isMatriculaDuplicada(textFieldMatricula.getText())) {
            JOptionPane.showMessageDialog(this, "Já existe um veículo com uma matrícula igual.",
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
        jLabel1 = new javax.swing.JLabel();
        panelFoto = new javax.swing.JPanel();
        labelFoto = new javax.swing.JLabel();
        labelPreco = new javax.swing.JLabel();
        textFieldPreco = new javax.swing.JTextField();
        textFieldMatricula = new javax.swing.JTextField();
        textFieldQuilometros = new javax.swing.JTextField();
        textFieldAno = new javax.swing.JTextField();
        labelMatricula = new javax.swing.JLabel();
        labelQuilometros = new javax.swing.JLabel();
        labelAno = new javax.swing.JLabel();
        labelMes = new javax.swing.JLabel();
        comboBoxMes = new javax.swing.JComboBox<>();
        comboBoxMarca = new javax.swing.JComboBox<>();
        labelMarca = new javax.swing.JLabel();
        textFieldModelo = new javax.swing.JTextField();
        labelModelo = new javax.swing.JLabel();
        textFieldCor = new javax.swing.JTextField();
        labelCor = new javax.swing.JLabel();
        comboBoxCombustivel = new javax.swing.JComboBox<>();
        labelCombustivel = new javax.swing.JLabel();
        labelCilindrada = new javax.swing.JLabel();
        textFieldCilindrada = new javax.swing.JTextField();
        labelPotencia = new javax.swing.JLabel();
        textFieldPotencia = new javax.swing.JTextField();
        labelLotacao = new javax.swing.JLabel();
        textFieldLotacao = new javax.swing.JTextField();
        comboBoxClasse = new javax.swing.JComboBox<>();
        labelClasse = new javax.swing.JLabel();
        textFieldMundacas = new javax.swing.JTextField();
        labelMudancas = new javax.swing.JLabel();
        textFieldNumPortas = new javax.swing.JTextField();
        labelNumPortas = new javax.swing.JLabel();
        comboBoxEstadoVeiculo = new javax.swing.JComboBox<>();
        labelEstadoVeiculo = new javax.swing.JLabel();
        comboBoxEstabelecimento = new javax.swing.JComboBox<>();
        labelEstabelecimento = new javax.swing.JLabel();
        textFieldEvento = new javax.swing.JTextField();
        labelEvento = new javax.swing.JLabel();
        tabbedPaneEdicao = new javax.swing.JTabbedPane();
        panelCaracteristicas = new javax.swing.JPanel();
        scrollPaneCaracteristicas = new javax.swing.JScrollPane();
        textAreaCaracteristicas = new javax.swing.JTextArea();
        panelObservacoes = new javax.swing.JPanel();
        scrollPaneObservacoes = new javax.swing.JScrollPane();
        textAreaObservacoes = new javax.swing.JTextArea();
        panelReparacoes = new javax.swing.JPanel();
        scrollPaneReparacoes = new javax.swing.JScrollPane();
        tableReparacoes = new javax.swing.JTable();
        panelEventos = new javax.swing.JPanel();
        scrollPaneEventos = new javax.swing.JScrollPane();
        tableEventos = new javax.swing.JTable();
        panelTransacoes = new javax.swing.JPanel();
        scrollPaneTransacoes = new javax.swing.JScrollPane();
        tableTransacoes = new javax.swing.JTable();

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Foto");

        panelFoto.setLayout(new java.awt.GridBagLayout());

        labelFoto.setText("labelFoto");
        panelFoto.add(labelFoto, new java.awt.GridBagConstraints());

        labelPreco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPreco.setText("Preço");

        labelMatricula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMatricula.setText("Matrícula");

        labelQuilometros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelQuilometros.setText("Quilómetros");

        labelAno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelAno.setText("Ano");

        labelMes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMes.setText("Mês do Registo");

        comboBoxMarca.setEditable(true);

        labelMarca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMarca.setText("Marca");

        labelModelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelModelo.setText("Modelo");

        labelCor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCor.setText("Cor");

        labelCombustivel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCombustivel.setText("Combustível");

        labelCilindrada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCilindrada.setText("Cilindrada");

        labelPotencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPotencia.setText("Potência");

        labelLotacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLotacao.setText("Lotação");

        labelClasse.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelClasse.setText("Classe");

        labelMudancas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMudancas.setText("Número de Mudanças");

        labelNumPortas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNumPortas.setText("Nº. de Portas");

        labelEstadoVeiculo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstadoVeiculo.setText("Estado do Veículo");

        labelEstabelecimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstabelecimento.setText("Estabelecimento");

        labelEvento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEvento.setText("Evento");

        tabbedPaneEdicao.setToolTipText("");
        tabbedPaneEdicao.setName("123"); // NOI18N

        textAreaCaracteristicas.setColumns(20);
        textAreaCaracteristicas.setRows(5);
        scrollPaneCaracteristicas.setViewportView(textAreaCaracteristicas);

        javax.swing.GroupLayout panelCaracteristicasLayout = new javax.swing.GroupLayout(panelCaracteristicas);
        panelCaracteristicas.setLayout(panelCaracteristicasLayout);
        panelCaracteristicasLayout.setHorizontalGroup(
            panelCaracteristicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneCaracteristicas, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        panelCaracteristicasLayout.setVerticalGroup(
            panelCaracteristicasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneCaracteristicas, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        tabbedPaneEdicao.addTab("Características", panelCaracteristicas);

        textAreaObservacoes.setColumns(20);
        textAreaObservacoes.setRows(5);
        scrollPaneObservacoes.setViewportView(textAreaObservacoes);

        javax.swing.GroupLayout panelObservacoesLayout = new javax.swing.GroupLayout(panelObservacoes);
        panelObservacoes.setLayout(panelObservacoesLayout);
        panelObservacoesLayout.setHorizontalGroup(
            panelObservacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        panelObservacoesLayout.setVerticalGroup(
            panelObservacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        tabbedPaneEdicao.addTab("Observações", panelObservacoes);

        tableReparacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Estabelecimento", "Descrição da Reparação", "Data da Intervenção"
            }
        ));
        scrollPaneReparacoes.setViewportView(tableReparacoes);

        javax.swing.GroupLayout panelReparacoesLayout = new javax.swing.GroupLayout(panelReparacoes);
        panelReparacoes.setLayout(panelReparacoesLayout);
        panelReparacoesLayout.setHorizontalGroup(
            panelReparacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneReparacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        panelReparacoesLayout.setVerticalGroup(
            panelReparacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneReparacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        tabbedPaneEdicao.addTab("Reparações", panelReparacoes);

        tableEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Data Início", "Data Fim", "Local", "Número de Veículos"
            }
        ));
        scrollPaneEventos.setViewportView(tableEventos);

        javax.swing.GroupLayout panelEventosLayout = new javax.swing.GroupLayout(panelEventos);
        panelEventos.setLayout(panelEventosLayout);
        panelEventosLayout.setHorizontalGroup(
            panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        panelEventosLayout.setVerticalGroup(
            panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        tabbedPaneEdicao.addTab("Eventos", panelEventos);

        tableTransacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Nº. Veiculos", "Nome da entidade", "NIF", "Data", "Title 6", "Estado"
            }
        ));
        scrollPaneTransacoes.setViewportView(tableTransacoes);

        javax.swing.GroupLayout panelTransacoesLayout = new javax.swing.GroupLayout(panelTransacoes);
        panelTransacoes.setLayout(panelTransacoesLayout);
        panelTransacoesLayout.setHorizontalGroup(
            panelTransacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneTransacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        panelTransacoesLayout.setVerticalGroup(
            panelTransacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneTransacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        tabbedPaneEdicao.addTab("Transações", panelTransacoes);

        javax.swing.GroupLayout panelEdicaoLayout = new javax.swing.GroupLayout(panelEdicao);
        panelEdicao.setLayout(panelEdicaoLayout);
        panelEdicaoLayout.setHorizontalGroup(
            panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneEdicao)
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPreco))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMatricula)
                                    .addComponent(textFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldQuilometros, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelQuilometros))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelAno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textFieldAno))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboBoxMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textFieldModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                    .addComponent(labelModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldCor)
                                    .addComponent(labelCor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCilindrada)
                                    .addComponent(textFieldCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldPotencia)
                                    .addComponent(labelPotencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textFieldLotacao, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(labelLotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMudancas)
                                    .addComponent(textFieldMundacas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNumPortas)
                                    .addComponent(textFieldNumPortas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEstadoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxEstadoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxEstabelecimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelEstabelecimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelEvento)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textFieldEvento))))
                .addContainerGap())
        );
        panelEdicaoLayout.setVerticalGroup(
            panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelPreco)
                    .addComponent(labelMatricula)
                    .addComponent(labelQuilometros)
                    .addComponent(labelAno)
                    .addComponent(labelMes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldQuilometros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMarca)
                            .addComponent(labelModelo)
                            .addComponent(labelCor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelCombustivel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelCilindrada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelClasse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelLotacao)
                                    .addComponent(labelPotencia))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldLotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textFieldPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelMudancas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldMundacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelNumPortas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldNumPortas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelEstadoVeiculo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxEstadoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEdicaoLayout.createSequentialGroup()
                                .addComponent(labelEstabelecimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelEvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPaneEdicao)
                .addContainerGap())
        );

        tabbedPaneEdicao.getAccessibleContext().setAccessibleName("asdasd");

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
                .addGap(0, 0, 0)
                .addComponent(panelEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        acaoGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JComboBox<ClassesVeiculos> comboBoxClasse;
    private javax.swing.JComboBox<TipoCombustivel> comboBoxCombustivel;
    private javax.swing.JComboBox<Estabelecimento> comboBoxEstabelecimento;
    private javax.swing.JComboBox<EstadoVeiculo> comboBoxEstadoVeiculo;
    private javax.swing.JComboBox<String> comboBoxMarca;
    private javax.swing.JComboBox<MesesAno> comboBoxMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelAno;
    private javax.swing.JLabel labelCilindrada;
    private javax.swing.JLabel labelClasse;
    private javax.swing.JLabel labelCombustivel;
    private javax.swing.JLabel labelCor;
    private javax.swing.JLabel labelEstabelecimento;
    private javax.swing.JLabel labelEstadoVeiculo;
    private javax.swing.JLabel labelEvento;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelLotacao;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelMes;
    private javax.swing.JLabel labelModelo;
    private javax.swing.JLabel labelMudancas;
    private javax.swing.JLabel labelNumPortas;
    private javax.swing.JLabel labelPotencia;
    private javax.swing.JLabel labelPreco;
    private javax.swing.JLabel labelQuilometros;
    private javax.swing.JPanel panelCaracteristicas;
    private javax.swing.JPanel panelEdicao;
    private javax.swing.JPanel panelEventos;
    private javax.swing.JPanel panelFoto;
    private javax.swing.JPanel panelObservacoes;
    private javax.swing.JPanel panelReparacoes;
    private javax.swing.JPanel panelTransacoes;
    private javax.swing.JScrollPane scrollPaneCaracteristicas;
    private javax.swing.JScrollPane scrollPaneEventos;
    private javax.swing.JScrollPane scrollPaneObservacoes;
    private javax.swing.JScrollPane scrollPaneReparacoes;
    private javax.swing.JScrollPane scrollPaneTransacoes;
    private javax.swing.JTabbedPane tabbedPaneEdicao;
    private javax.swing.JTable tableEventos;
    private javax.swing.JTable tableReparacoes;
    private javax.swing.JTable tableTransacoes;
    private javax.swing.JTextArea textAreaCaracteristicas;
    private javax.swing.JTextArea textAreaObservacoes;
    private javax.swing.JTextField textFieldAno;
    private javax.swing.JTextField textFieldCilindrada;
    private javax.swing.JTextField textFieldCor;
    private javax.swing.JTextField textFieldEvento;
    private javax.swing.JTextField textFieldLotacao;
    private javax.swing.JTextField textFieldMatricula;
    private javax.swing.JTextField textFieldModelo;
    private javax.swing.JTextField textFieldMundacas;
    private javax.swing.JTextField textFieldNumPortas;
    private javax.swing.JTextField textFieldPotencia;
    private javax.swing.JTextField textFieldPreco;
    private javax.swing.JTextField textFieldQuilometros;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
