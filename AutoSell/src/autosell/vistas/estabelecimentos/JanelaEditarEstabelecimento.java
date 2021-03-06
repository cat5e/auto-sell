package autosell.vistas.estabelecimentos;

import autosell.exceptions.CustomExeption;
import autosell.enumeracoes.TipoEstabelecimento;
import autosell.gestores.GestorArmazenamentoDados;
import autosell.gestores.GestorEstabelecimentos;
import autosell.modelos.Estabelecimento;
import autosell.utils.AppLogger;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static autosell.utils.ValidacoesUtils.isComponenteVazio;

public class JanelaEditarEstabelecimento extends javax.swing.JInternalFrame {

    private Estabelecimento estabelecimento;

    public JanelaEditarEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
        initComponents();
        toolBarMenu.setFloatable(false);

        loadTipoEstabelecimentos();
        if (estabelecimento != null) {
            popularDados();
        }
    }

    private void loadTipoEstabelecimentos() {
        comboboxTipo.setModel(new DefaultComboBoxModel(TipoEstabelecimento.values()));
    }

    private void popularDados() {
        textFieldNome.setText(estabelecimento.getNome());
        comboboxTipo.setSelectedItem(estabelecimento.getTipo());
        textAreaMorada.setText(estabelecimento.getMorada());
        textFieldLimiteVeiculos.setText(String.valueOf(estabelecimento.getLimiteVeiculos()));
        textFieldTelefone.setText(estabelecimento.getNumeroTelefone());
        textFieldEmail.setText(estabelecimento.getEmail());
    }

    private void acaoGuardar() {
        try {
            if (!isDadosValidos()) {
                return;
            }

            if (estabelecimento == null) {
                estabelecimento = new Estabelecimento(
                        textFieldNome.getText(),
                        textFieldEmail.getText(),
                        textFieldTelefone.getText(),
                        textAreaMorada.getText(),
                        Integer.parseInt(textFieldLimiteVeiculos.getText()),
                        (TipoEstabelecimento) comboboxTipo.getSelectedItem());

                if (!GestorEstabelecimentos.getInstance().adicionar(estabelecimento)) {
                    throw new CustomExeption("N??o foi poss??vel guardar o registo.");
                }
            } else {
                estabelecimento.setNome(textFieldNome.getText());
                estabelecimento.setEmail(textFieldEmail.getText());
                estabelecimento.setNumeroTelefone(textFieldTelefone.getText());
                estabelecimento.setMorada(textAreaMorada.getText());
                estabelecimento.setLimiteVeiculos(Integer.parseInt(textFieldLimiteVeiculos.getText()));
                estabelecimento.setTipo((TipoEstabelecimento) comboboxTipo.getSelectedItem());
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
        JComponent componentesAValidar[] = {textFieldNome, textFieldEmail,
            textFieldTelefone, textAreaMorada, textFieldLimiteVeiculos,
            comboboxTipo};

        for (JComponent jComponent : componentesAValidar) {
            if (!isComponenteVazio(this, jComponent)) {
                return false;
            }
        }
        
        int limiteVeiculosValidacao;
        try
        {
            limiteVeiculosValidacao = Integer.parseInt(textFieldLimiteVeiculos.getText());
        }
        catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Limite de ve??culos inv??lido.",
                            "Dados inv??lidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(limiteVeiculosValidacao < 0){
            JOptionPane.showMessageDialog(this, "Limite de ve??culos tem de ser positivo",
                            "Dados inv??lidos", JOptionPane.WARNING_MESSAGE);
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
        panelPrincipal = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        labelMorada = new javax.swing.JLabel();
        scrollPaneMorada = new javax.swing.JScrollPane();
        textAreaMorada = new javax.swing.JTextArea();
        labelLimiteVeiculos = new javax.swing.JLabel();
        textFieldLimiteVeiculos = new javax.swing.JTextField();
        textFieldTelefone = new javax.swing.JTextField();
        textFieldEmail = new javax.swing.JTextField();
        labelTelefone = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        comboboxTipo = new javax.swing.JComboBox<>();

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

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNome.setText("Nome");

        labelTipo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTipo.setText("Tipo");

        textFieldNome.setName("Nome"); // NOI18N

        labelMorada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMorada.setText("Morada");

        textAreaMorada.setColumns(20);
        textAreaMorada.setRows(5);
        textAreaMorada.setName("Morada"); // NOI18N
        scrollPaneMorada.setViewportView(textAreaMorada);

        labelLimiteVeiculos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLimiteVeiculos.setText("Limite de Ve??culos");

        textFieldLimiteVeiculos.setName("LimiteVeiculos"); // NOI18N

        textFieldTelefone.setName("Telefone"); // NOI18N

        textFieldEmail.setName("E-mail"); // NOI18N

        labelTelefone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTelefone.setText("Telefone");

        labelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEmail.setText("E-mail");

        comboboxTipo.setName("Tipo"); // NOI18N

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldNome)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(labelTipo)
                                .addGap(165, 165, 165))
                            .addComponent(comboboxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(scrollPaneMorada)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldLimiteVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMorada)
                            .addComponent(labelLimiteVeiculos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTelefone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(labelEmail)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(labelTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMorada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMorada, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLimiteVeiculos)
                    .addComponent(labelTelefone)
                    .addComponent(labelEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldLimiteVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JComboBox<TipoEstabelecimento> comboboxTipo;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelLimiteVeiculos;
    private javax.swing.JLabel labelMorada;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane scrollPaneMorada;
    private javax.swing.JTextArea textAreaMorada;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldLimiteVeiculos;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldTelefone;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
