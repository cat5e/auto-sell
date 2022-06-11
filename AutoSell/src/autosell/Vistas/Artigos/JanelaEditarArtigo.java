package autosell.Vistas.Artigos;

import autosell.CustomExceptions.CustomExeption;
import autosell.Enumeracoes.TipoColaborador;
import autosell.Enumeracoes.TipoEstabelecimento;
import autosell.Gestores.GestorArmazenamentoDados;
import autosell.Gestores.GestorArtigos;
import autosell.Gestores.GestorEstabelecimentos;
import autosell.Modelos.Artigo;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Estabelecimento;
import autosell.Utils.AppLogger;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static autosell.Utils.ValidacoesUtils.isComponenteVazio;

public class JanelaEditarArtigo extends javax.swing.JInternalFrame {

    private Artigo artigo;
    private Colaborador colaboradorAutenticado;

    public JanelaEditarArtigo(Artigo artigo, Colaborador colaboradorAutenticado) {
        this.artigo = artigo;
        this.colaboradorAutenticado = colaboradorAutenticado;
        
        initComponents();

        loadEstabelecimentos();
        if (artigo != null) {
            popularDados();
        }
        
        if(!colaboradorAutenticado.getTipoColaborador().equals(TipoColaborador.TECNICO)) {
            comboBoxEstabelecimento.setEditable(true);
            comboBoxEstabelecimento.setSelectedItem(colaboradorAutenticado.getEstabelecimento());
        } 
    }

    private void loadEstabelecimentos(){
        var estabelecimentos = GestorEstabelecimentos.getInstance().getListagem();
       
        for (Estabelecimento estabelecimento : estabelecimentos) {
            comboBoxEstabelecimento.addItem(estabelecimento);
        }
    }

    private void popularDados() {
        textFieldReferencia.setText(artigo.getReferencia());
        textFieldNome.setText(artigo.getNome());
        textFieldQuantidadeMinima.setText(String.valueOf(artigo.getQuantidadeMinima()));
        textFieldQuantidadeAtual.setText(String.valueOf(artigo.getQuantidadeAtual()));
        textFieldUnidade.setText(artigo.getUnidade());
        comboBoxEstabelecimento.setSelectedItem(artigo.getEstabelecimento());
        textAreaDescricao.setText(artigo.getDescricao());
    }

    private void acaoGuardar() {
        try {
            if (!isDadosValidos()) {
                return;
            }

            if (artigo == null) {
                artigo = new Artigo(
                        textFieldReferencia.getText(),
                        textFieldNome.getText(),
                        Float.parseFloat(textFieldQuantidadeMinima.getText()),
                        Float.parseFloat(textFieldQuantidadeAtual.getText()),
                        textFieldUnidade.getText(),
                        (Estabelecimento) comboBoxEstabelecimento.getSelectedItem()
                );

                artigo.setDescricao(textAreaDescricao.getText());

                if (!GestorArtigos.getInstance().adicionar(artigo)) {
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
            } else {
                artigo.setReferencia(textFieldReferencia.getText());
                artigo.setNome(textFieldNome.getText());
                artigo.setQuantidadeMinima(Float.parseFloat(textFieldQuantidadeMinima.getText()));
                artigo.setQuantidadeAtual(Float.parseFloat(textFieldQuantidadeAtual.getText()));
                artigo.setUnidade(textFieldUnidade.getText());
                artigo.setEstabelecimento((Estabelecimento) comboBoxEstabelecimento.getSelectedItem());
                artigo.setDescricao(textAreaDescricao.getText());
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
            textFieldReferencia,
            textFieldNome,
            textFieldQuantidadeMinima,
            textFieldQuantidadeAtual,
            textFieldUnidade,
            comboBoxEstabelecimento
        };
        
        for (JComponent jComponent : componentesAValidar) {
            if (!isComponenteVazio(this, jComponent)) {
                return false;
            }
        }
        
        try {
            float quantidadeAtual = Float.parseFloat(textFieldQuantidadeAtual.getText());
            if(quantidadeAtual < 0){
                JOptionPane.showMessageDialog(this, "A quantidade atual não pode ser negativa.",
                            "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } 
        catch(HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O valor introduzido no campo 'Quantidade atual' não é valido.",
                           "Dados inválidos", JOptionPane.WARNING_MESSAGE);
               return false;
        }
        
        try {
            float quantidadeMinima = Float.parseFloat(textFieldQuantidadeMinima.getText());
            if(quantidadeMinima < 0){
                JOptionPane.showMessageDialog(this, "A quantidade mínima não pode ser negativa.",
                            "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } 
        catch(HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O valor introduzido no campo 'Quantidade miníma' não é valido.",
                           "Dados inválidos", JOptionPane.WARNING_MESSAGE);
               return false;
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
        buttonEnviarArtigo = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        labelReferencia = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        textFieldReferencia = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        textFieldQuantidadeMinima = new javax.swing.JTextField();
        labelQuantidadeMinima = new javax.swing.JLabel();
        textFieldUnidade = new javax.swing.JTextField();
        labelQuantidadeAtual = new javax.swing.JLabel();
        textFieldQuantidadeAtual = new javax.swing.JTextField();
        labelEstabelecimento = new javax.swing.JLabel();
        comboBoxEstabelecimento = new javax.swing.JComboBox<>();
        labelUnidade = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        scrollPaneDescricao = new javax.swing.JScrollPane();
        textAreaDescricao = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
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

        buttonEnviarArtigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/cube-send.png"))); // NOI18N
        buttonEnviarArtigo.setText("Enviar Artigo");
        buttonEnviarArtigo.setFocusable(false);
        buttonEnviarArtigo.setMaximumSize(new java.awt.Dimension(80, 52));
        buttonEnviarArtigo.setMinimumSize(new java.awt.Dimension(80, 52));
        buttonEnviarArtigo.setPreferredSize(new java.awt.Dimension(80, 52));
        buttonEnviarArtigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarArtigoActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonEnviarArtigo);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        labelReferencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelReferencia.setText("Referência");

        textFieldNome.setName("Nome"); // NOI18N

        textFieldReferencia.setName("Referência"); // NOI18N

        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNome.setText("Nome");

        textFieldQuantidadeMinima.setName("Quantidade Mínima"); // NOI18N

        labelQuantidadeMinima.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelQuantidadeMinima.setText("Quantidade Mínima");

        textFieldUnidade.setName("Unidade"); // NOI18N

        labelQuantidadeAtual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelQuantidadeAtual.setText("Quantidade Atual");

        textFieldQuantidadeAtual.setName("Quantidade Atual"); // NOI18N

        labelEstabelecimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstabelecimento.setText("Estabelecimento");

        comboBoxEstabelecimento.setName("Estabelecimento"); // NOI18N

        labelUnidade.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelUnidade.setText("Unidade");

        labelDescricao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDescricao.setText("Descrição");

        textAreaDescricao.setColumns(20);
        textAreaDescricao.setRows(5);
        textAreaDescricao.setName("Descrição"); // NOI18N
        scrollPaneDescricao.setViewportView(textAreaDescricao);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelReferencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textFieldNome)))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelQuantidadeMinima))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldQuantidadeAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelQuantidadeAtual))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUnidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(labelEstabelecimento)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(comboBoxEstabelecimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(labelDescricao)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPaneDescricao))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelReferencia)
                    .addComponent(labelNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidadeMinima)
                    .addComponent(labelQuantidadeAtual)
                    .addComponent(labelEstabelecimento)
                    .addComponent(labelUnidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldQuantidadeAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void buttonEnviarArtigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnviarArtigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEnviarArtigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEnviarArtigo;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JComboBox<Estabelecimento> comboBoxEstabelecimento;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelEstabelecimento;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelQuantidadeAtual;
    private javax.swing.JLabel labelQuantidadeMinima;
    private javax.swing.JLabel labelReferencia;
    private javax.swing.JLabel labelUnidade;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane scrollPaneDescricao;
    private javax.swing.JTextArea textAreaDescricao;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldQuantidadeAtual;
    private javax.swing.JTextField textFieldQuantidadeMinima;
    private javax.swing.JTextField textFieldReferencia;
    private javax.swing.JTextField textFieldUnidade;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
