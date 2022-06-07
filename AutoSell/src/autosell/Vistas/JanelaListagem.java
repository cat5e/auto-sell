package autosell.Vistas;

import autosell.Modelos.Colaborador;
import autosell.Utils.AppLogger;
import autosell.Utils.TableModel;
import autosell.Vistas.Colaboradores.JanelaEditarColaborador;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableRowSorter;

public abstract class JanelaListagem<T> extends javax.swing.JInternalFrame {

    protected JDesktopPane desktopPane;
    protected Colaborador colaboradorAutenticado;
    protected JTable table;
    protected TableRowSorter<TableModel> tableRowSorter;
    protected int[] tableColumnIndex;
    protected TableModel tableModel;
    protected String[] columnNames;
    private int objectColumnIndex;

    public JanelaListagem(String nomeJanela, String[] columnNames, JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        this.desktopPane = desktopPane;
        this.colaboradorAutenticado = colaboradorAutenticado;
        this.columnNames = columnNames;

        initComponents();
        setTitle(nomeJanela);

        initTableComponents(columnNames, getTableData());
    }

    private void initTableComponents(String[] columnNames, Object[][] tableData) {
        try {
            tableModel = new TableModel(columnNames, tableData);
            tableRowSorter = new TableRowSorter<>(tableModel);
            table = new JTable(tableModel);

            if (tableData.length == 0) {
                return;
            }

            table.setRowSorter(tableRowSorter);
            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table.setFillsViewportHeight(true);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getSelectionModel().addListSelectionListener(this::selecaoAlterada);

            panelTable.add(table);

            JScrollPane scrollPane = new JScrollPane(table);
            panelTable.add(scrollPane);

            tableColumnIndex = tableModel.getColumnIndexes();

            objectColumnIndex = columnNames.length - 1;
            table.getColumnModel().getColumn(objectColumnIndex).setMinWidth(0);
            table.getColumnModel().getColumn(objectColumnIndex).setMaxWidth(0);
            table.getColumnModel().getColumn(objectColumnIndex).setWidth(0);
        } catch (Exception e) {
            AppLogger.LOG.warning(getTitle(), e);
        }
    }

    private void selecaoAlterada(ListSelectionEvent e) {
        int viewRow = table.getSelectedRow();

        if (viewRow > 0) {
            table.convertRowIndexToModel(viewRow);
        }
    }

    private void aplicarFiltro(String criterio) {

        RowFilter<TableModel, Object> rowFilter = null;

        if (criterio == null || criterio.length() == 0) {
            tableRowSorter.setRowFilter(rowFilter);
            return;
        }

        String[] textToSearchSplitted = criterio.split(" ");

        List<RowFilter<TableModel, Object>> listOfRowFilters = new ArrayList<>();

        try {
            for (String textToSearchSplitted1 : textToSearchSplitted) {

                String regexToSearch = new StringBuilder("(?i)")
                        .append(textToSearchSplitted1).toString();

                listOfRowFilters.add(RowFilter.regexFilter(regexToSearch, tableColumnIndex));
            }

            rowFilter = RowFilter.andFilter(listOfRowFilters);

        } catch (PatternSyntaxException e) {
            return;
        }

        tableRowSorter.setRowFilter(rowFilter);
    }

    protected void acaoRemover() {
        if (tableModel.getRowCount() < 2) {
            return;
        }

        var resultado = JOptionPane.showConfirmDialog(this,
                "Deseja remover a linha selecionada? Esta opção é irreversível.",
                "Remover?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (resultado != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            var value = (T) table.getValueAt(table.getSelectedRow(), objectColumnIndex);

            acaoRemoverGestor(value);

            tableModel.setData(getTableData());
        } catch (Exception e) {
            AppLogger.LOG.warning(this, e);
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar remover o registo", "Erro", JOptionPane.WARNING_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Registo removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    protected abstract void acaoRemoverGestor(T t);

    private Object[][] getTableData() {
        return getData();
    }

    protected abstract Object[][] getData();

    protected void acaoSelecionar() {
        var value = (T) table.getValueAt(table.getSelectedRow(), objectColumnIndex);

        var janela = getInternalFrame(value);
        desktopPane.add(janela);
        janela.setVisible(true);
    }
    
    protected abstract JInternalFrame getInternalFrame(T value); 

    protected void acaoAtualizar() {
        try {
            tableModel.setData(getTableData());
        } catch (Exception e) {
            AppLogger.LOG.warning(this, e);
        }
    }

    ;

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBarMenu = new javax.swing.JToolBar();
        buttonSelecionar = new javax.swing.JButton();
        separator1 = new javax.swing.JToolBar.Separator();
        buttonPesquisar = new javax.swing.JButton();
        textFieldPesquisar = new javax.swing.JTextField();
        buttonLimpar = new javax.swing.JButton();
        separator2 = new javax.swing.JToolBar.Separator();
        buttonAtualizar = new javax.swing.JButton();
        separator3 = new javax.swing.JToolBar.Separator();
        buttonRemover = new javax.swing.JButton();
        panelTable = new javax.swing.JPanel();

        setClosable(true);
        setMinimumSize(new java.awt.Dimension(700, 517));

        buttonSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/checkbox-marked-outline.png"))); // NOI18N
        buttonSelecionar.setText("Selecionar");
        buttonSelecionar.setFocusable(false);
        buttonSelecionar.setMaximumSize(new java.awt.Dimension(100, 52));
        buttonSelecionar.setMinimumSize(new java.awt.Dimension(100, 52));
        buttonSelecionar.setPreferredSize(new java.awt.Dimension(100, 52));
        buttonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonSelecionar);
        toolBarMenu.add(separator1);

        buttonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/magnify.png"))); // NOI18N
        buttonPesquisar.setFocusable(false);
        buttonPesquisar.setLabel("Pesquisar");
        buttonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisarActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonPesquisar);

        textFieldPesquisar.setMaximumSize(new java.awt.Dimension(150, 32));
        textFieldPesquisar.setMinimumSize(new java.awt.Dimension(150, 32));
        textFieldPesquisar.setName(""); // NOI18N
        textFieldPesquisar.setPreferredSize(new java.awt.Dimension(150, 32));
        toolBarMenu.add(textFieldPesquisar);

        buttonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/backspace.png"))); // NOI18N
        buttonLimpar.setText("Limpar");
        buttonLimpar.setFocusable(false);
        buttonLimpar.setMaximumSize(new java.awt.Dimension(76, 32));
        buttonLimpar.setMinimumSize(new java.awt.Dimension(76, 32));
        buttonLimpar.setPreferredSize(new java.awt.Dimension(76, 32));
        buttonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimparActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonLimpar);
        toolBarMenu.add(separator2);

        buttonAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/autorenew.png"))); // NOI18N
        buttonAtualizar.setText("Atualizar");
        buttonAtualizar.setFocusable(false);
        buttonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtualizarActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonAtualizar);
        toolBarMenu.add(separator3);

        buttonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/trash-can.png"))); // NOI18N
        buttonRemover.setText("Remover");
        buttonRemover.setFocusable(false);
        buttonRemover.setMaximumSize(new java.awt.Dimension(82, 52));
        buttonRemover.setMinimumSize(new java.awt.Dimension(82, 52));
        buttonRemover.setPreferredSize(new java.awt.Dimension(82, 52));
        buttonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverActionPerformed(evt);
            }
        });
        toolBarMenu.add(buttonRemover);

        panelTable.setBackground(new java.awt.Color(255, 255, 255));
        panelTable.setName("panelTable"); // NOI18N
        panelTable.setLayout(new java.awt.GridLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarActionPerformed
        acaoSelecionar();
    }//GEN-LAST:event_buttonSelecionarActionPerformed

    private void buttonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisarActionPerformed
        aplicarFiltro(textFieldPesquisar.getText());
    }//GEN-LAST:event_buttonPesquisarActionPerformed

    private void buttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparActionPerformed
        textFieldPesquisar.setText("");
        aplicarFiltro("");
    }//GEN-LAST:event_buttonLimparActionPerformed

    private void buttonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtualizarActionPerformed
        acaoAtualizar();
    }//GEN-LAST:event_buttonAtualizarActionPerformed

    private void buttonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverActionPerformed
        acaoRemover();
    }//GEN-LAST:event_buttonRemoverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAtualizar;
    private javax.swing.JButton buttonLimpar;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JButton buttonRemover;
    private javax.swing.JButton buttonSelecionar;
    private javax.swing.JPanel panelTable;
    private javax.swing.JToolBar.Separator separator1;
    private javax.swing.JToolBar.Separator separator2;
    private javax.swing.JToolBar.Separator separator3;
    private javax.swing.JTextField textFieldPesquisar;
    private javax.swing.JToolBar toolBarMenu;
    // End of variables declaration//GEN-END:variables
}
