package autosell.vistas.eventos;

import autosell.exceptions.CustomExeption;
import autosell.gestores.GestorArmazenamentoDados;
import autosell.gestores.GestorEventos;
import autosell.gestores.GestorVeiculos;
import autosell.modelos.Evento;
import autosell.modelos.Veiculo;
import autosell.utils.AppLogger;
import autosell.utils.TableModel;
import autosell.vistas.JanelaSelecao;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static autosell.utils.ValidacoesUtils.isComponenteVazio;
import static autosell.utils.ValidacoesUtils.isDateCorrect;
import static autosell.utils.DateUtil.getDateFormated;
import static autosell.utils.DateUtil.parseLocalDate;
import javax.swing.JTextField;

public class JanelaEditarEvento extends javax.swing.JInternalFrame {

    private Evento evento;
    private final TableModel tableModel;
    private LinkedList<Veiculo> veiculos;
    private final String[] columns;

    public JanelaEditarEvento(Evento evento) {
        this.evento = evento;
        veiculos = new LinkedList<>();
        initComponents();
        toolBarMenu.setFloatable(false);

        if (evento != null) {
            popularDados();
        }

        columns = new String[]{
            "Matrícula",
            "Marca",
            "Modelo",
            "Estabelecimento",
            "Obj"};
        tableModel = new TableModel(columns, getTableData());
        tableVeiculos.setModel(tableModel);
        tableVeiculos.getColumnModel().getColumn(columns.length - 1).setMinWidth(0);
        tableVeiculos.getColumnModel().getColumn(columns.length - 1).setMaxWidth(0);
        tableVeiculos.getColumnModel().getColumn(columns.length - 1).setWidth(0);

    }

    private void popularDados() {
        textFieldNome.setText(evento.getNome());
        textFieldDataInicio.setText(getDateFormated(evento.getDataInicio()));
        textFieldDataFim.setText(getDateFormated(evento.getDataFim()));
        textFieldLocal.setText(evento.getLocal());
        veiculos = evento.getVeiculos();
    }

    private void acaoGuardar() {
        try {
            if (!isDadosValidos()) {
                return;
            }

            if (evento == null) {
                evento = new Evento(
                        textFieldNome.getText(),
                        parseLocalDate(textFieldDataInicio.getText()),
                        parseLocalDate(textFieldDataFim.getText()),
                        textFieldLocal.getText());
                
                for (Veiculo veiculo : veiculos) {
                    evento.addVeiculo(veiculo);
                }

                if (!GestorEventos.getInstance().adicionar(evento)) {
                    throw new CustomExeption("Não foi possível guardar o registo.");
                }
            } else {
                evento.setNome(textFieldNome.getText());
                evento.setDataInicio(parseLocalDate(textFieldDataInicio.getText()));
                evento.setDataFim(parseLocalDate(textFieldDataFim.getText()));
                evento.setLocal(textFieldLocal.getText());
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
        JComponent componentesAValidar[] = {textFieldNome, textFieldDataInicio,
            textFieldDataFim, textFieldLocal};

        for (JComponent jComponent : componentesAValidar) {
            if (!isComponenteVazio(this, jComponent)) {
                return false;
            }
        }
        
        JTextField datasAValidar[] = {textFieldDataInicio, textFieldDataFim};
        for (JTextField jTextField : datasAValidar) {
            if (! isDateCorrect(this, jTextField)) {
                return false;
            }
        }

        return true;
    }

    private Object[][] getTableData() {
        int i = 0;
        var aux = new Object[veiculos.size()][columns.length];
        for (Veiculo veiculo : veiculos) {
            aux[i][0] = veiculo.getMatricula();
            aux[i][1] = veiculo.getMarca();
            aux[i][2] = veiculo.getModelo();
            aux[i][3] = veiculo.getEstabelecimento();
            aux[i][4] = veiculo;
            i++;
        }
        return aux;
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
        textFieldNome = new javax.swing.JTextField();
        textFieldDataInicio = new javax.swing.JTextField();
        textFieldDataFim = new javax.swing.JTextField();
        labelDataInicio = new javax.swing.JLabel();
        labelDataFim = new javax.swing.JLabel();
        labelLocal = new javax.swing.JLabel();
        textFieldLocal = new javax.swing.JTextField();
        labelVeiculos = new javax.swing.JLabel();
        scrollPanelVeiculos = new javax.swing.JScrollPane();
        tableVeiculos = new javax.swing.JTable();
        buttonAdicionarVeiculo = new javax.swing.JButton();
        buttonRemoverVeiculo = new javax.swing.JButton();

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

        textFieldNome.setName("Nome"); // NOI18N

        textFieldDataInicio.setName("Data Inicio"); // NOI18N

        textFieldDataFim.setName("Data Fim"); // NOI18N

        labelDataInicio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDataInicio.setText("Data Inicio");

        labelDataFim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDataFim.setText("Data Fim");

        labelLocal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLocal.setText("Local");

        textFieldLocal.setName("Local"); // NOI18N

        labelVeiculos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelVeiculos.setText("Veículos");

        tableVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPanelVeiculos.setViewportView(tableVeiculos);

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

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addComponent(labelVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(buttonAdicionarVeiculo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemoverVeiculo))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(labelDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addComponent(textFieldDataFim)))
                    .addComponent(labelLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldLocal)
                    .addComponent(scrollPanelVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(labelDataInicio)
                    .addComponent(labelDataFim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAdicionarVeiculo)
                    .addComponent(buttonRemoverVeiculo)
                    .addComponent(labelVeiculos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
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

    private void buttonAdicionarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarVeiculoActionPerformed
        var listagem = new JanelaSelecao<>("Selecionar veículos...",
                GestorVeiculos.getInstance(), true).showDialog();

        for (Veiculo veiculo : listagem) {
            if (veiculo == null || veiculos.contains(veiculo)) {
                continue;
            }
            if (evento != null) {
                evento.addVeiculo(veiculo);
            }
            veiculos.add(veiculo);
        }

        tableModel.setData(getTableData());
    }//GEN-LAST:event_buttonAdicionarVeiculoActionPerformed

    private void buttonRemoverVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverVeiculoActionPerformed
        var valorSelecionado = (Veiculo) tableVeiculos.getValueAt(tableVeiculos.getSelectedRow(), columns.length - 1);

        if (evento != null) {
            evento.removeVeiculo(valorSelecionado);
        }

        veiculos.remove(valorSelecionado);

        tableModel.setData(getTableData());
    }//GEN-LAST:event_buttonRemoverVeiculoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionarVeiculo;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonRemoverVeiculo;
    private javax.swing.JLabel labelDataFim;
    private javax.swing.JLabel labelDataInicio;
    private javax.swing.JLabel labelLocal;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelVeiculos;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane scrollPanelVeiculos;
    private javax.swing.JTable tableVeiculos;
    private javax.swing.JTextField textFieldDataFim;
    private javax.swing.JTextField textFieldDataInicio;
    private javax.swing.JTextField textFieldLocal;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
