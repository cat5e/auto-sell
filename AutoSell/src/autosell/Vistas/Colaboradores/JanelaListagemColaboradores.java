package autosell.Vistas.Colaboradores;

import autosell.Gestores.GestorColaboradores;
import autosell.Modelos.Colaborador;
import autosell.Utils.AppLogger;
import autosell.Vistas.JanelaListagem;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class JanelaListagemColaboradores extends JanelaListagem {
    private JDesktopPane desktopPane;
    private Colaborador colaboradorAutenticado;
    
    public JanelaListagemColaboradores(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Colaboradores");
        
        this.colaboradorAutenticado = colaboradorAutenticado;
        this.desktopPane = desktopPane;
        
        var columnNames = new String[]{"Nome", "E-mail", "Estabelecimento", "Veiculos Vendidos", "Obj"};

        try {
            initTableComponents(columnNames, getTableData());
            
            table.getColumnModel().getColumn(4).setMinWidth(0);
            table.getColumnModel().getColumn(4).setMaxWidth(0);
            table.getColumnModel().getColumn(4).setWidth(0);
            
        } catch (Exception e) {
            AppLogger.LOG.warning(JanelaListagemColaboradores.class.getName(), e);
        }
    }

    @Override
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
            var colaborador = (Colaborador) table.getValueAt(table.getSelectedRow(), 4);

            GestorColaboradores.INSTANCIA.remover(colaborador);

            tableModel.setData(getTableData());
        } 
        catch (Exception e) {
            AppLogger.LOG.warning(this, e);
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar remover o colaborador", "Erro", JOptionPane.WARNING_MESSAGE);
        }
        
        JOptionPane.showMessageDialog(this, "Colaborador removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void acaoSelecionar() {
        var colaborador = (Colaborador) table.getValueAt(table.getSelectedRow(), 4);
        
        var janelaEditarColaborador = new JanelaEditarColaborador(colaborador, colaboradorAutenticado);
        desktopPane.add(janelaEditarColaborador);
        janelaEditarColaborador.setVisible(true);
    }

    @Override
    protected void acaoAtualizar() {
        try {
            tableModel.setData(getTableData());
        } 
        catch (Exception e) {
            AppLogger.LOG.warning(this, e);
        } 
    }

    private Object[][] getTableData() {
        var colaboradores = GestorColaboradores.INSTANCIA.getListagem();

        var aux = new Object[colaboradores.size()][5];

        int i = 0;

        for (Colaborador colaborador : colaboradores) {
            aux[i][0] = colaborador.getNome();
            aux[i][1] = colaborador.getEmail();
            aux[i][2] = colaborador.getEstabelecimento();
            aux[i][3] = "Not Implemented.";
            aux[i][4] = colaborador;
            i++;
        }

        return aux;
    }
}
