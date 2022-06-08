package autosell.Vistas.Colaboradores;

import autosell.Gestores.GestorColaboradores;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Veiculo;
import autosell.Vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemColaboradores extends JanelaListagem<Colaborador> {

    public JanelaListagemColaboradores(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Colaboradores",
                new String[]{
                    "Nome",
                    "E-mail",
                    "Estabelecimento",
                    "Veiculos Vendidos",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorColaboradores.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Colaborador value) {
        return new JanelaEditarColaborador(value, colaboradorAutenticado);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Colaborador> colaboradores) {
        int i = 0;
        for (Colaborador colaborador : colaboradores) {
            aux[i][0] = colaborador.getNome();
            aux[i][1] = colaborador.getEmail();
            aux[i][2] = colaborador.getEstabelecimento();
            aux[i][3] = "Not Implemented.";
            aux[i][4] = colaborador;
            i++;
        }
    }
}
