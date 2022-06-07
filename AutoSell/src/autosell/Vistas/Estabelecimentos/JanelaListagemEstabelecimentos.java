package autosell.Vistas.Estabelecimentos;

import autosell.Gestores.GestorEstabelecimentos;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Estabelecimento;
import autosell.Utils.AppLogger;
import autosell.Vistas.JanelaListagem;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class JanelaListagemEstabelecimentos extends JanelaListagem {

    private JDesktopPane desktopPane;
    private Colaborador colaboradorAutenticado;

    public JanelaListagemEstabelecimentos(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Estabelecimentos");

        this.colaboradorAutenticado = colaboradorAutenticado;
        this.desktopPane = desktopPane;

        var columnNames = new String[]{"Nome", "Tipo", "Nr. Máx. Veículos",
            "Morada", "Telefone", "Obj"};

        try {
            initTableComponents(columnNames, getTableData());

            table.getColumnModel().getColumn(5).setMinWidth(0);
            table.getColumnModel().getColumn(5).setMaxWidth(0);
            table.getColumnModel().getColumn(5).setWidth(0);

        } catch (Exception e) {
            AppLogger.LOG.warning(JanelaListagemEstabelecimentos.class.getName(), e);
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
            var estabelecimento = (Estabelecimento) table.getValueAt(table.getSelectedRow(), 5);

            GestorEstabelecimentos.INSTANCIA.remover(estabelecimento);

            tableModel.setData(getTableData());
        } catch (Exception e) {
            AppLogger.LOG.warning(this, e);
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar remover o registo", "Erro", JOptionPane.WARNING_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Registo removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void acaoSelecionar() {
        var estabelecimento = (Estabelecimento) table.getValueAt(table.getSelectedRow(), 4);

        /*var janelaEditarEstabelecimento = new JanelaEditarEstabelecimento(estabelecimento, colaboradorAutenticado);
        desktopPane.add(janelaEditarEstabelecimento);
        janelaEditarEstabelecimento.setVisible(true);*/
    }

    @Override
    protected void acaoAtualizar() {
        try {
            tableModel.setData(getTableData());
        } catch (Exception e) {
            AppLogger.LOG.warning(this, e);
        }
    }

    private Object[][] getTableData() {
        var estabelecimentos = GestorEstabelecimentos.INSTANCIA.getListagem();

        var aux = new Object[estabelecimentos.size()][6];

        int i = 0;
            
        for (Estabelecimento estabelecimento : estabelecimentos) {
            aux[i][0] = estabelecimento.getNome();
            aux[i][1] = estabelecimento.getTipo();
            aux[i][2] = estabelecimento.getLimiteVeiculos();
            aux[i][3] = estabelecimento.getMorada();
            aux[i][4] = estabelecimento.getNumeroTelefone();
            aux[i][5] = estabelecimento;
            i++;
        }

        return aux;
    }

}
