package autosell.Vistas.Eventos;

import autosell.Gestores.GestorEventos;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Evento;
import autosell.Vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import static autosell.Utils.DateUtil.getDateFormated;

public class JanelaListagemEventos extends JanelaListagem<Evento> {
    public JanelaListagemEventos(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Eventos",
                new String[]{
                    "Nome",
                    "Data Início",
                    "Data Fim",
                    "Local",
                    "Número de Veículos",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorEventos.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Evento value) {
        return new JanelaEditarEvento(value);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Evento> eventos) {
        int i = 0;

        for (Evento evento : eventos) {
            aux[i][0] = evento.getNome();
            aux[i][1] = getDateFormated(evento.getDataInicio());
            aux[i][2] = getDateFormated(evento.getDataFim());
            aux[i][3] = evento.getLocal();
            aux[i][4] = evento.getVeiculos().size();
            aux[i][5] = evento;
            i++;
        }
    }
}
