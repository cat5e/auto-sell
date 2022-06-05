package autosell.Vistas;

import autosell.Gestores.GestorColaboradores;
import javax.swing.JOptionPane;

public class JanelaLogin extends javax.swing.JFrame {

    public JanelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCapa = new javax.swing.JLabel();
        buttonEntrar = new javax.swing.JButton();
        buttonFechar = new javax.swing.JButton();
        labelEmail = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        scrollPaneEmail = new javax.swing.JScrollPane();
        textPaneEmail = new javax.swing.JTextPane();
        labelPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AutoSell _Login");
        setResizable(false);

        labelCapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cover_350.jpg"))); // NOI18N
        labelCapa.setText("jLabel1");

        buttonEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/login.png"))); // NOI18N
        buttonEntrar.setText("Entrar");
        buttonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEntrarActionPerformed(evt);
            }
        });

        buttonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/logout.png"))); // NOI18N
        buttonFechar.setText("Fechar");
        buttonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFecharActionPerformed(evt);
            }
        });

        labelEmail.setText("E-mail");

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        textPaneEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        scrollPaneEmail.setViewportView(textPaneEmail);

        labelPassword.setText("Password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPassword)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(buttonEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonFechar))
                        .addComponent(labelEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelCapa)
                .addGap(20, 20, 20)
                .addComponent(labelEmail)
                .addGap(0, 0, 0)
                .addComponent(scrollPaneEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPassword)
                .addGap(0, 0, 0)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEntrar)
                    .addComponent(buttonFechar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEntrarActionPerformed
        var email = textPaneEmail.getText();
        var password = passwordField.getPassword();
        
        var colaborador = GestorColaboradores.INSTANCIA.validarCredenciais(email, String.valueOf(password));
        if(colaborador == null){
            
            JOptionPane.showMessageDialog(this,"Credênciais Invalidas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        var jp = new JanelaPrincipal(colaborador);
        jp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_buttonEntrarActionPerformed

    private void buttonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFecharActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEntrar;
    private javax.swing.JButton buttonFechar;
    private javax.swing.JLabel labelCapa;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JScrollPane scrollPaneEmail;
    private javax.swing.JTextPane textPaneEmail;
    // End of variables declaration//GEN-END:variables
}
