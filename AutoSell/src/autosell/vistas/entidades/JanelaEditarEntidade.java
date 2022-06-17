package autosell.vistas.entidades;

import autosell.exceptions.CustomExeption;
import autosell.enumeracoes.TipoEntidade;
import autosell.gestores.GestorArmazenamentoDados;
import autosell.gestores.GestorEntidades;
import autosell.modelos.Entidade;
import autosell.utils.AppLogger;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static autosell.utils.ValidacoesUtils.isComponenteVazio;
import static autosell.utils.ValidacoesUtils.isDateCorrect;
import static autosell.utils.DateUtil.getDateFormated;
import static autosell.utils.DateUtil.parseLocalDate;


public class JanelaEditarEntidade extends javax.swing.JInternalFrame {
    private Entidade entidade;
    
    public JanelaEditarEntidade(Entidade entidade) {
        this.entidade = entidade;
        initComponents();
        toolBarMenu.setFloatable(false);
        
        loadTipoEntidade();
        if (entidade != null) {
            popularDados();
        }
    }
    
    private void loadTipoEntidade() {
        comboBoxTipo.setModel(new DefaultComboBoxModel(TipoEntidade.values()));
    }
    
    private void popularDados() {
        comboBoxTipo.setSelectedItem(entidade.getTipoEntidade());
        textFieldDataNascimento.setText(getDateFormated(entidade.getDataNascimento()));
        textFieldEmail.setText(entidade.getEmail());
        textFieldMorada.setText(entidade.getMorada());
        textFieldNIF.setText(entidade.getNif());
        textFieldNome.setText(entidade.getNome());
        textFieldTelefone.setText(entidade.getNumeroTelefone());  
    }
    
    private void acaoGuardar() {
        try {
            if (!isDadosValidos()) {
                return;
            }
            
            if (entidade == null) {
                entidade = new Entidade(
                        textFieldNome.getText(),
                        textFieldNIF.getText(),
                        textFieldTelefone.getText(),
                        (TipoEntidade) comboBoxTipo.getSelectedItem());
                
                entidade.setDataNascimento(parseLocalDate(textFieldDataNascimento.getText()));
                entidade.setMorada(textFieldMorada.getText());

                if (!GestorEntidades.getInstance().adicionar(entidade)) {
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
            } else {
                entidade.setNome(textFieldNome.getText());
                entidade.setNumeroTelefone(textFieldTelefone.getText());
                entidade.setNif(textFieldNIF.getText());
                entidade.setNumeroTelefone(textFieldTelefone.getText());
                entidade.setTipoEntidade((TipoEntidade) comboBoxTipo.getSelectedItem());
                entidade.setDataNascimento(parseLocalDate(textFieldDataNascimento.getText()));
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
        JComponent componentesAValidar[] = {textFieldNome, textFieldNIF,
            textFieldTelefone, comboBoxTipo};

        for (JComponent jComponent : componentesAValidar) {
            if (!isComponenteVazio(this, jComponent)) {
                return false;
            }
        }
        
        String nifEntidade = entidade == null ? "" : entidade.getNif();
        
        if(!textFieldNIF.getText().equals(nifEntidade) && 
                GestorEntidades.getInstance().isNifDuplicated(textFieldNIF.getText())){
             JOptionPane.showMessageDialog(this, "Já existe uma entidade com o mesmo NIF.",
                            "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return isDateCorrect(this,textFieldDataNascimento);
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
        panelPrincipaç = new javax.swing.JPanel();
        labelDataNascimento = new javax.swing.JLabel();
        textFieldDataNascimento = new javax.swing.JTextField();
        comboBoxTipo = new javax.swing.JComboBox<>();
        labelTipo = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        labelNIF = new javax.swing.JLabel();
        textFieldNIF = new javax.swing.JTextField();
        labelTelefone = new javax.swing.JLabel();
        textFieldTelefone = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JTextField();
        labelMorada = new javax.swing.JLabel();
        textFieldMorada = new javax.swing.JTextField();

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

        panelPrincipaç.setBackground(new java.awt.Color(255, 255, 255));

        labelDataNascimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDataNascimento.setText("Data Nascimento");

        textFieldDataNascimento.setName("Data Nascimento"); // NOI18N

        comboBoxTipo.setName("Tipo de Colaborador"); // NOI18N

        labelTipo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTipo.setText("Tipo");

        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNome.setText("Nome");

        textFieldNome.setName("Nome"); // NOI18N

        labelNIF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNIF.setText("NIF");

        textFieldNIF.setName("NIF"); // NOI18N

        labelTelefone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTelefone.setText("Número de Telefone");

        textFieldTelefone.setName("Número de Telefone"); // NOI18N

        labelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEmail.setText("E-mail");

        textFieldEmail.setName("E-mail"); // NOI18N

        labelMorada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMorada.setText("Morada");

        textFieldMorada.setName("Morada"); // NOI18N

        javax.swing.GroupLayout panelPrincipaçLayout = new javax.swing.GroupLayout(panelPrincipaç);
        panelPrincipaç.setLayout(panelPrincipaçLayout);
        panelPrincipaçLayout.setHorizontalGroup(
            panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipaçLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipaçLayout.createSequentialGroup()
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipaçLayout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addGap(412, 412, 412))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipaçLayout.createSequentialGroup()
                                .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxTipo, 0, 187, Short.MAX_VALUE)
                            .addComponent(labelTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelPrincipaçLayout.createSequentialGroup()
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataNascimento)
                            .addComponent(textFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNIF)
                            .addComponent(textFieldNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTelefone)
                            .addComponent(textFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipaçLayout.createSequentialGroup()
                                .addComponent(labelEmail)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textFieldEmail)))
                    .addComponent(textFieldMorada)
                    .addGroup(panelPrincipaçLayout.createSequentialGroup()
                        .addComponent(labelMorada)
                        .addGap(0, 596, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPrincipaçLayout.setVerticalGroup(
            panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipaçLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipaçLayout.createSequentialGroup()
                        .addComponent(labelNIF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipaçLayout.createSequentialGroup()
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTipo)
                            .addComponent(labelNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipaçLayout.createSequentialGroup()
                                .addComponent(labelDataNascimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipaçLayout.createSequentialGroup()
                                .addComponent(labelEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelPrincipaçLayout.createSequentialGroup()
                        .addComponent(labelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMorada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldMorada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addComponent(panelPrincipaç, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipaç, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        acaoGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JComboBox<TipoEntidade> comboBoxTipo;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelMorada;
    private javax.swing.JLabel labelNIF;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JPanel panelPrincipaç;
    private javax.swing.JTextField textFieldDataNascimento;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldMorada;
    private javax.swing.JTextField textFieldNIF;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldTelefone;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
