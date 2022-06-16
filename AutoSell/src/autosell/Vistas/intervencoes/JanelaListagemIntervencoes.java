package autosell.vistas.intervencoes;

import autosell.gestores.GestorIntervencoes;
import autosell.modelos.Colaborador;
import autosell.modelos.Intervencao;
import autosell.vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import static autosell.utils.DateUtil.getDateFormated;

public class JanelaListagemIntervencoes extends JanelaListagem<Intervencao> {
    public JanelaListagemIntervencoes(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Intervencões",
                new String[]{
                    "Data",
                    "Matrículo",
                    "Marca",
                    "Modelo",
                    "Estabelecimento",
                    "Descrição da Intervenção",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorIntervencoes.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Intervencao value) {
        return new JanelaEditarIntervencao(value, colaboradorAutenticado);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Intervencao> intervencoes) {
        int i = 0;

        for (Intervencao intervencao : intervencoes) {
            aux[i][0] = getDateFormated(intervencao.getData());
            aux[i][1] = intervencao.getVeiculo().getMatricula();
            aux[i][2] = intervencao.getVeiculo().getMarca();
            aux[i][3] = intervencao.getVeiculo().getModelo();
            aux[i][4] = intervencao.getEstabelecimento().getNome();
            aux[i][5] = intervencao.getDescricao();
            aux[i][6] = intervencao;
            i++;
        }
    }
}
