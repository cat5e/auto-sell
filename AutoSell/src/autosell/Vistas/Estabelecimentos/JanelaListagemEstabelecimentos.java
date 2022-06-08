package autosell.Vistas.Estabelecimentos;

import autosell.Gestores.GestorEstabelecimentos;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Estabelecimento;
import autosell.Vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemEstabelecimentos extends JanelaListagem<Estabelecimento> {

    public JanelaListagemEstabelecimentos(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Estabelecimentos",
                new String[]{
                    "Nome",
                    "Tipo",
                    "Nr. Máx. Veículos",
                    "Morada",
                    "Telefone",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorEstabelecimentos.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Estabelecimento value) {
        return new JanelaEditarEstabelecimento(value);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Estabelecimento> estabelecimentos) {
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
    }
}
