package autosell.Vistas.Entidades;

import autosell.Gestores.GestorEntidades;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Entidade;
import autosell.Vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemEntidades extends JanelaListagem<Entidade> {
    public JanelaListagemEntidades(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Eventos",
                new String[]{
                    "Tipo",
                    "Nome",
                    "Data Nascimento",
                    "NIF",
                    "Nr. Telefone",
                    "E-mail",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorEntidades.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Entidade value) {
        return new JanelaEditarEntidade(value);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Entidade> entidades) {
        int i = 0;

        for (Entidade entidade : entidades) {
            aux[i][0] = entidade.getTipoEntidade();
            aux[i][1] = entidade.getNome();
            aux[i][2] = entidade.getDataNascimento();
            aux[i][3] = entidade.getNif();
            aux[i][4] = entidade.getNumeroTelefone();
            aux[i][5] = entidade.getEmail();
            aux[i][6] = entidade;
            i++;
        }
    }
}
