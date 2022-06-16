package autosell.vistas.colaboradores;

import autosell.exceptions.CustomExeption;
import autosell.enumeracoes.TipoColaborador;
import autosell.gestores.GestorArmazenamentoDados;
import autosell.gestores.GestorColaboradores;
import autosell.gestores.GestorEstabelecimentos;
import autosell.modelos.Colaborador;
import autosell.modelos.Estabelecimento;
import autosell.utils.AppLogger;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static autosell.utils.ValidacoesUtils.isNullOrEmpty;
import static autosell.utils.ValidacoesUtils.isComponenteVazio;



public class JanelaEditarColaborador extends javax.swing.JInternalFrame {
    private Colaborador colaborador;
    private final boolean isColaboradorAutenticadoColaborador;
    private final boolean isColaboradorAutenticadoAdmin;
    private final GestorColaboradores gestor;
    // TOOD: Implementar vendas do colaborador
    /*private JTable table;
    private TableRowSorter<TableModel> tableRowSorter;
    private int[] tableColumnIndex;
    private TableModel tableModel;*/
    
    public JanelaEditarColaborador(Colaborador colaborador, Colaborador colaboradorAutenticado) {
        gestor = GestorColaboradores.getInstance();
        
        this.colaborador = colaborador;
        
        isColaboradorAutenticadoColaborador = this.colaborador != null && 
                this.colaborador.equals(colaboradorAutenticado);
        
        isColaboradorAutenticadoAdmin = colaboradorAutenticado != null && 
                colaboradorAutenticado.getTipoColaborador().equals(TipoColaborador.ADMINISTRADOR);
        initComponents();
        toolBarMenu.setFloatable(false);
        
        // TODO: Verificar o tipo de colaborador
        loadTipoColaboradores();
        loadEstabelecimentos();
        if(this.colaborador != null){
            popularDados();
        }
        
        // TODO: Carregar a listagem de vendas do colaborador
    }
    
    private void loadTipoColaboradores(){
        comboBoxTipoColaborador.setModel(new DefaultComboBoxModel(TipoColaborador.values()));
    }
    
    private void loadEstabelecimentos(){
        var estabelecimentos = GestorEstabelecimentos.getInstance().getListagem();
       
        for (Estabelecimento estabelecimento : estabelecimentos) {
            comboBoxEstabelecimento.addItem(estabelecimento);
        }
    }
    
    private void popularDados(){
        textFieldNome.setText(colaborador.getNome());
        textFieldEmail.setText(colaborador.getEmail());
        comboBoxEstabelecimento.setSelectedItem(colaborador.getEstabelecimento());
        comboBoxTipoColaborador.setSelectedItem(colaborador.getTipoColaborador());
        passwordFieldConfirmNovaPassword.setText("");
        passwordFieldNovaPassword.setText("");
        passwordFieldPasswordAntiga.setText("");
    }
    
    private void acaoGuardar(){
        try {
             if(!isDadosValidos()){
                return;
            }

            if(colaborador == null){
                colaborador = new Colaborador(
                        textFieldNome.getText(), 
                        textFieldEmail.getText(), 
                        (Estabelecimento) comboBoxEstabelecimento.getSelectedItem(), 
                        String.valueOf(passwordFieldNovaPassword.getPassword()), 
                        (TipoColaborador) comboBoxTipoColaborador.getSelectedItem());

                if(!gestor.adicionar(colaborador)){
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
            }
            else {
                colaborador.setNome(textFieldNome.getText());
                colaborador.setEmail(textFieldEmail.getText());
                colaborador.setEstabelecimento((Estabelecimento) comboBoxEstabelecimento.getSelectedItem());
                if(passwordFieldNovaPassword.getPassword().length > 0){
                    colaborador.setPassword(String.valueOf(passwordFieldNovaPassword.getPassword()));
                }
                colaborador.setTipoColaborador((TipoColaborador) comboBoxTipoColaborador.getSelectedItem());
            }
            
            GestorArmazenamentoDados.INSTANCIA.escreverDados();
        } 
        catch(CustomExeption e) {
            AppLogger.LOG.warning(this, e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        catch (IOException e) {
            AppLogger.LOG.severe(this, e);
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao guardar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        JOptionPane.showMessageDialog(this, "Os dados foram gravados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean isDadosValidos(){
        JComponent componentesAValidar[] = {textFieldNome, textFieldEmail, 
            comboBoxEstabelecimento, comboBoxTipoColaborador};
   
        for (JComponent jComponent : componentesAValidar) {
            if(!isComponenteVazio(this, jComponent)){
                return false;
            }
        }
        
        
        String emailColaborador = colaborador == null ? "" : colaborador.getEmail();
        if(!textFieldEmail.getText().equals(emailColaborador) &&
              gestor.isEmailDuplicated(textFieldEmail.getText())){
            JOptionPane.showMessageDialog(this,String.format("O email '%s', já existe no sistema.", textFieldEmail.getText()),
                            "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                    return false;
        }
        
        return isPasswordValid();
    }
    
    private boolean isPasswordValid(){
        
        var passwordAtual = String.valueOf(passwordFieldPasswordAntiga.getPassword());
        var novaPassword = String.valueOf(passwordFieldNovaPassword.getPassword());
        var confirmacaoNovaPassword = String.valueOf(passwordFieldConfirmNovaPassword.getPassword());
        
        boolean isValido = true;
        
        if(colaborador == null){
            isValido = isComponenteVazio(this,passwordFieldNovaPassword);
        }
        
        if(isValido && !isNullOrEmpty(novaPassword)){
            if(isColaboradorAutenticadoColaborador) {
               isValido = isComponenteVazio(this,passwordFieldPasswordAntiga);
            }
            
            if(isValido){
                isValido = isComponenteVazio(this,passwordFieldConfirmNovaPassword);
            }
            
            if(isValido && novaPassword.length() < gestor.LIMITE_MIN_CHAR_PASSWORD){
                JOptionPane.showMessageDialog(this, String.format("A password tem de ter no minimo %d caracteres.",
                    gestor.LIMITE_MIN_CHAR_PASSWORD),
                            "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                isValido = false;
            }
            
            if(isValido && !novaPassword.equals(confirmacaoNovaPassword)){
                JOptionPane.showMessageDialog(this,"A nova password e a confirmação não são iguais.",
                            "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                isValido = false;
            }
            
            if(isValido && isColaboradorAutenticadoColaborador && !passwordAtual.equals(colaborador.getPassword())){
                JOptionPane.showMessageDialog(this,"Password antiga inválida.",
                            "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                isValido = false;
            }
        }
        return isValido;
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
        labelNome = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        textFieldEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelEstabelecimento = new javax.swing.JLabel();
        comboBoxEstabelecimento = new javax.swing.JComboBox<>();
        comboBoxEstabelecimento.setEnabled(isColaboradorAutenticadoAdmin);
        labelPasswordAntiga = new javax.swing.JLabel();
        passwordFieldPasswordAntiga = new javax.swing.JPasswordField();
        passwordFieldPasswordAntiga.setEnabled(isColaboradorAutenticadoColaborador);
        passwordFieldNovaPassword = new javax.swing.JPasswordField();
        labelVendas = new javax.swing.JLabel();
        passwordFieldConfirmNovaPassword = new javax.swing.JPasswordField();
        labelConfirmNovaPassword = new javax.swing.JLabel();
        labelNovaPassword = new javax.swing.JLabel();
        panelVendas = new javax.swing.JPanel();
        labelToBeImplemented = new javax.swing.JLabel();
        labelTipoColaborador = new javax.swing.JLabel();
        comboBoxTipoColaborador = new javax.swing.JComboBox<>();
        comboBoxTipoColaborador.setEnabled(isColaboradorAutenticadoAdmin);

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setTitle("Editar Colaborador");
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(678, 230));
        setMinimumSize(new java.awt.Dimension(678, 230));

        buttonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/content-save.png"))); // NOI18N
        buttonGuardar.setText("Guardar");
        buttonGuardar.setFocusable(false);
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

        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNome.setText("Nome");

        textFieldNome.setName("Nome"); // NOI18N

        textFieldEmail.setName("E-mail"); // NOI18N

        labelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEmail.setText("E-mail");

        labelEstabelecimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstabelecimento.setText("Estabelecimento");

        comboBoxEstabelecimento.setName("Estabelecimento"); // NOI18N

        labelPasswordAntiga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPasswordAntiga.setText("Password Antiga");

        passwordFieldPasswordAntiga.setName("Password Antiga"); // NOI18N

        passwordFieldNovaPassword.setName("Nova Password"); // NOI18N

        labelVendas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelVendas.setText("Vendas");

        passwordFieldConfirmNovaPassword.setName("Confirmação Nova Password"); // NOI18N

        labelConfirmNovaPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelConfirmNovaPassword.setText("Confirmação Nova Password");
        labelConfirmNovaPassword.setName("Confirmação Nova Password"); // NOI18N

        labelNovaPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNovaPassword.setText("Nova Password");

        panelVendas.setBackground(new java.awt.Color(255, 255, 255));
        panelVendas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelVendas.setLayout(new java.awt.GridBagLayout());

        labelToBeImplemented.setText("To be Implemented...");
        panelVendas.add(labelToBeImplemented, new java.awt.GridBagConstraints());

        labelTipoColaborador.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTipoColaborador.setText("Tipo Colaborador");

        comboBoxTipoColaborador.setName("Tipo Colaborador"); // NOI18N

        javax.swing.GroupLayout panelEdicaoLayout = new javax.swing.GroupLayout(panelEdicao);
        panelEdicao.setLayout(panelEdicaoLayout);
        panelEdicaoLayout.setHorizontalGroup(
            panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboBoxTipoColaborador, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(passwordFieldPasswordAntiga, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(textFieldNome)
                                    .addComponent(labelNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(labelPasswordAntiga)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(passwordFieldNovaPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                .addComponent(labelEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(labelNovaPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxEstabelecimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordFieldConfirmNovaPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(labelEstabelecimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelConfirmNovaPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTipoColaborador)
                            .addComponent(labelVendas))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEdicaoLayout.setVerticalGroup(
            panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelEdicaoLayout.createSequentialGroup()
                            .addComponent(labelEmail)
                            .addGap(0, 0, 0)
                            .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBoxEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelEdicaoLayout.createSequentialGroup()
                            .addComponent(labelNome)
                            .addGap(0, 0, 0)
                            .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelEstabelecimento))
                .addGap(18, 18, 18)
                .addGroup(panelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addComponent(labelPasswordAntiga)
                        .addGap(0, 0, 0)
                        .addComponent(passwordFieldPasswordAntiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addComponent(labelNovaPassword)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(passwordFieldNovaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEdicaoLayout.createSequentialGroup()
                        .addComponent(labelConfirmNovaPassword)
                        .addGap(0, 0, 0)
                        .addComponent(passwordFieldConfirmNovaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelTipoColaborador)
                .addGap(0, 0, 0)
                .addComponent(comboBoxTipoColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelVendas)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
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
    private javax.swing.JComboBox<Estabelecimento> comboBoxEstabelecimento;
    private javax.swing.JComboBox<TipoColaborador> comboBoxTipoColaborador;
    private javax.swing.JLabel labelConfirmNovaPassword;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEstabelecimento;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNovaPassword;
    private javax.swing.JLabel labelPasswordAntiga;
    private javax.swing.JLabel labelTipoColaborador;
    private javax.swing.JLabel labelToBeImplemented;
    private javax.swing.JLabel labelVendas;
    private javax.swing.JPanel panelEdicao;
    private javax.swing.JPanel panelVendas;
    private javax.swing.JPasswordField passwordFieldConfirmNovaPassword;
    private javax.swing.JPasswordField passwordFieldNovaPassword;
    private javax.swing.JPasswordField passwordFieldPasswordAntiga;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
