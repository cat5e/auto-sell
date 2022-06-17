package autosell.vistas.veiculos;

import autosell.exceptions.CustomExeption;
import autosell.enumeracoes.*;
import autosell.gestores.*;
import autosell.modelos.Estabelecimento;
import autosell.modelos.Veiculo;
import autosell.utils.AppLogger;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static autosell.utils.ValidacoesUtils.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class JanelaEditarVeiculo extends javax.swing.JInternalFrame {

    private Veiculo veiculo;
    private ImageIcon fotoVeiculo;

    public JanelaEditarVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        initComponents();
        toolBarMenu.setFloatable(false);
      
        loadMeses();
        loadMarcas();
        loadCombustivel();
        loadClasses();
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
        comboBoxEstabelecimento.setSelectedItem(veiculo.getEstabelecimento());
        textAreaCaracteristicas.setText(veiculo.getCaracteristicas());
        textAreaObservacoes.setText(veiculo.getObservacoes());
        
        fotoVeiculo = veiculo.getFoto();
        loadFoto();
    }

    private void loadFoto() {
        
        if(fotoVeiculo == null) {
            labelFoto.setIcon(null);
            return;
        }
        
        try {
            labelFoto.setIcon(fotoVeiculo);
            
            labelFoto.revalidate();
            labelFoto.repaint();

        } catch (Exception e) {
            AppLogger.LOG.warning(this, e);
        }
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

                guardarDadosVeículo(veiculo);

                if (!GestorVeiculos.getInstance().adicionar(veiculo)) {
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
            } else {
                veiculo.setPreco(Float.parseFloat(textFieldPreco.getText()));
                veiculo.setMatricula(textFieldMatricula.getText());
                veiculo.setEstabelecimento((Estabelecimento) comboBoxEstabelecimento.getSelectedItem());
                veiculo.setMarca(comboBoxMarca.getSelectedItem().toString());

                guardarDadosVeículo(veiculo);
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

    private void guardarDadosVeículo(Veiculo veiculo) {
        veiculo.setMesRegisto((MesesAno) comboBoxMes.getSelectedItem());
        veiculo.setModelo(textFieldModelo.getText());
        veiculo.setCor(textFieldCor.getText());
        veiculo.setCombustivel((TipoCombustivel) comboBoxCombustivel.getSelectedItem());
        veiculo.setClasse((ClassesVeiculos) comboBoxClasse.getSelectedItem());
        veiculo.setCaracteristicas(textAreaCaracteristicas.getText());
        veiculo.setObservacoes(textAreaObservacoes.getText());

        if (!isNullOrEmpty(textFieldQuilometros.getText())) {
            veiculo.setQuilometros(Integer.parseInt(textFieldQuilometros.getText()));
        }
        if (!isNullOrEmpty(textFieldAno.getText())) {
            veiculo.setAnoRegisto(Integer.parseInt(textFieldAno.getText()));
        }
        if (!isNullOrEmpty(textFieldCilindrada.getText())) {
            veiculo.setCelindrada(Float.parseFloat(textFieldCilindrada.getText()));
        }
        if (!isNullOrEmpty(textFieldPotencia.getText())) {
            veiculo.setPotencia(Float.parseFloat(textFieldPotencia.getText()));
        }
        if (!isNullOrEmpty(textFieldLotacao.getText())) {
            veiculo.setLotacao(Integer.parseInt(textFieldLotacao.getText()));
        }
        if (!isNullOrEmpty(textFieldMundacas.getText())) {
            veiculo.setNumeorMudancas(Integer.parseInt(textFieldMundacas.getText()));
        }
        if (!isNullOrEmpty(textFieldNumPortas.getText())) {
            veiculo.setNumeroPortas(Integer.parseInt(textFieldNumPortas.getText()));
        }
        
        veiculo.setFoto(fotoVeiculo);
    }

    private boolean isDadosValidos() {
        if (!hasEstabelecimentoCapacidade()) {
            return false;
        }

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

            if (!isNumericValue(this, textField) && isNumberGreaterOrEqualThanZero(this, textField)) {
                return false;
            }
        }

        if (!veiculo.getMatricula().equals(textFieldMatricula.getText()) &&
                GestorVeiculos.getInstance().isMatriculaDuplicada(textFieldMatricula.getText())) {
            JOptionPane.showMessageDialog(this, "Já existe um veículo com uma matrícula igual.",
                    "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean hasEstabelecimentoCapacidade() {
        Estabelecimento e = (Estabelecimento) comboBoxEstabelecimento.getSelectedItem();
        int countVeiculos = GestorVeiculos.getInstance().getListagem((veiculo) -> veiculo.getEstabelecimento().equals(e)).size();
        if (countVeiculos >= e.getLimiteVeiculos()) {
            JOptionPane.showMessageDialog(this, "Foi atingido o limite de veículos do estabelecimento " + e.getNome(),
                    "Limite veículos", JOptionPane.WARNING_MESSAGE);
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
        buttonAdicionarImagem = new javax.swing.JButton();
        buttonRemoverImagem = new javax.swing.JButton();
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
        comboBoxEstabelecimento = new javax.swing.JComboBox<>();
        labelEstabelecimento = new javax.swing.JLabel();
        tabbedPaneEdicao = new javax.swing.JTabbedPane();
        panelCaracteristicas = new javax.swing.JPanel();
        scrollPaneCaracteristicas = new javax.swing.JScrollPane();
        textAreaCaracteristicas = new javax.swing.JTextArea();
        panelObservacoes = new javax.swing.JPanel();
        scrollPaneObservacoes = new javax.swing.JScrollPane();
        textAreaObservacoes = new javax.swing.JTextArea();

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

        buttonAdicionarImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/resources/plus.png"))); // NOI18N
        buttonAdicionarImagem.setText("Adicionar Imagem");
        buttonAdicionarImagem.setFocusable(false);
        buttonAdicionarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarImagemActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonAdicionarImagem);

        buttonRemoverImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/resources/minus.png"))); // NOI18N
        buttonRemoverImagem.setText("Remover Imagem");
        buttonRemoverImagem.setFocusable(false);
        buttonRemoverImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverImagemActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonRemoverImagem);

        panelEdicao.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Foto");

        panelFoto.setMaximumSize(new java.awt.Dimension(49, 16));
        panelFoto.setMinimumSize(new java.awt.Dimension(49, 16));
        panelFoto.setLayout(new java.awt.GridBagLayout());
        panelFoto.add(labelFoto, new java.awt.GridBagConstraints());

        labelPreco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPreco.setText("Preço");

        textFieldPreco.setName("Preço"); // NOI18N

        textFieldMatricula.setName("Matrícula"); // NOI18N

        textFieldQuilometros.setName("Quilómetros"); // NOI18N

        textFieldAno.setName("Ano"); // NOI18N

        labelMatricula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMatricula.setText("Matrícula");

        labelQuilometros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelQuilometros.setText("Quilómetros");

        labelAno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelAno.setText("Ano");

        labelMes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMes.setText("Mês do Registo");

        comboBoxMes.setName("Mês do Registo"); // NOI18N

        comboBoxMarca.setEditable(true);
        comboBoxMarca.setName("Marca"); // NOI18N

        labelMarca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMarca.setText("Marca");

        textFieldModelo.setName("Modelo"); // NOI18N

        labelModelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelModelo.setText("Modelo");

        textFieldCor.setName("Cor"); // NOI18N

        labelCor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCor.setText("Cor");

        comboBoxCombustivel.setName("Combustível"); // NOI18N

        labelCombustivel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCombustivel.setText("Combustível");

        labelCilindrada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCilindrada.setText("Cilindrada");

        textFieldCilindrada.setName("Cilindrada"); // NOI18N

        labelPotencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPotencia.setText("Potência");

        textFieldPotencia.setName("Pontência"); // NOI18N

        labelLotacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLotacao.setText("Lotação");

        textFieldLotacao.setName("Lotação"); // NOI18N

        comboBoxClasse.setName("Classe"); // NOI18N

        labelClasse.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelClasse.setText("Classe");

        textFieldMundacas.setName("Número de Mudanças"); // NOI18N

        labelMudancas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMudancas.setText("Número de Mudanças");

        textFieldNumPortas.setName("Número de Portas"); // NOI18N

        labelNumPortas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNumPortas.setText("Nº. de Portas");

        comboBoxEstabelecimento.setName("Estabelecimento"); // NOI18N
        comboBoxEstabelecimento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxEstabelecimentoItemStateChanged(evt);
            }
        });

        labelEstabelecimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstabelecimento.setText("Estabelecimento");

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
            .addGroup(panelCaracteristicasLayout.createSequentialGroup()
                .addComponent(scrollPaneCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
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
            .addComponent(scrollPaneObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );

        tabbedPaneEdicao.addTab("Observações", panelObservacoes);

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
                            .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                    .addComponent(comboBoxEstabelecimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelEstabelecimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(181, 181, 181)))))
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
                                .addComponent(labelEstabelecimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPaneEdicao))
        );

        tabbedPaneEdicao.getAccessibleContext().setAccessibleName("asdasd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        acaoGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void comboBoxEstabelecimentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxEstabelecimentoItemStateChanged
        hasEstabelecimentoCapacidade();
    }//GEN-LAST:event_comboBoxEstabelecimentoItemStateChanged

    private void buttonAdicionarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarImagemActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);

        try {
            File file = fileChooser.getSelectedFile();

            fotoVeiculo = new ImageIcon(ImageIO.read(file));
            loadFoto();
        } catch (IOException e) {
            AppLogger.LOG.severe(this, e);
        }
    }//GEN-LAST:event_buttonAdicionarImagemActionPerformed

    private void buttonRemoverImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverImagemActionPerformed
        fotoVeiculo = null;
        loadFoto();
    }//GEN-LAST:event_buttonRemoverImagemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionarImagem;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonRemoverImagem;
    private javax.swing.JComboBox<ClassesVeiculos> comboBoxClasse;
    private javax.swing.JComboBox<TipoCombustivel> comboBoxCombustivel;
    private javax.swing.JComboBox<Estabelecimento> comboBoxEstabelecimento;
    private javax.swing.JComboBox<String> comboBoxMarca;
    private javax.swing.JComboBox<MesesAno> comboBoxMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelAno;
    private javax.swing.JLabel labelCilindrada;
    private javax.swing.JLabel labelClasse;
    private javax.swing.JLabel labelCombustivel;
    private javax.swing.JLabel labelCor;
    private javax.swing.JLabel labelEstabelecimento;
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
    private javax.swing.JPanel panelFoto;
    private javax.swing.JPanel panelObservacoes;
    private javax.swing.JScrollPane scrollPaneCaracteristicas;
    private javax.swing.JScrollPane scrollPaneObservacoes;
    private javax.swing.JTabbedPane tabbedPaneEdicao;
    private javax.swing.JTextArea textAreaCaracteristicas;
    private javax.swing.JTextArea textAreaObservacoes;
    private javax.swing.JTextField textFieldAno;
    private javax.swing.JTextField textFieldCilindrada;
    private javax.swing.JTextField textFieldCor;
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
