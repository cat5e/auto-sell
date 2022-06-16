package autosell.vistas.artigos;

import autosell.gestores.GestorArtigos;
import autosell.modelos.Artigo;
import autosell.modelos.Colaborador;
import autosell.vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemArtigos extends JanelaListagem<Artigo> {

    public JanelaListagemArtigos(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Estabelecimentos",
                new String[]{
                    "Referência",
                    "Nome",
                    "Qtd. Disponível",
                    "Unidade",
                    "Estabelecimento",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorArtigos.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Artigo value) {
        return new JanelaEditarArtigo(value, colaboradorAutenticado);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Artigo> artigos) {
        int i = 0;
        for (Artigo artigo : artigos) {
            aux[i][0] = artigo.getReferencia();
            aux[i][1] = artigo.getNome();
            aux[i][2] = artigo.getQuantidadeAtual();
            aux[i][3] = artigo.getUnidade();
            aux[i][4] = artigo.getEstabelecimento().getNome();
            aux[i][5] = artigo;
            i++;
        }
    }
}
