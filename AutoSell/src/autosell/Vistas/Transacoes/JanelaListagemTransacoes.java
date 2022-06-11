package autosell.Vistas.Transacoes;

import autosell.Vistas.Intervencoes.*;
import autosell.Gestores.GestorIntervencoes;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Intervencao;
import autosell.Vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemTransacoes extends JanelaListagem<Intervencao> {
    public JanelaListagemTransacoes(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
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
            aux[i][0] = intervencao.getData();
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
