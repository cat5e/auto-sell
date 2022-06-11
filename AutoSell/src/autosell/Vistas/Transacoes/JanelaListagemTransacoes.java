package autosell.Vistas.Transacoes;

import autosell.Vistas.Intervencoes.*;
import autosell.Gestores.GestorIntervencoes;
import autosell.Gestores.GestorTransacoes;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Intervencao;
import autosell.Modelos.Transacao;
import autosell.Vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemTransacoes extends JanelaListagem<Transacao> {
    public JanelaListagemTransacoes(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Transações",
                new String[]{
                    "Tipo",
                    "Nº. Veículos",
                    "Nome entidade",
                    "NIF entidade",
                    "Data",
                    "Preço Final",
                    "Estado",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorTransacoes.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Transacao value) {
        return new JanelaEditarTransacao(value, colaboradorAutenticado);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Transacao> transacaoes) {
        int i = 0;

        for (Transacao transacao : transacaoes) {
            aux[i][0] = transacao.getTipo();
            aux[i][1] = transacao.getVeiculosAssociados().size();
            aux[i][2] = transacao.getEntidade().getNome();
            aux[i][3] = transacao.getEntidade().getNif();
            aux[i][4] = transacao.getData();
            aux[i][5] = transacao.getPrecoFinal();
            aux[i][6] = transacao.getEstado();
            aux[i][7] = transacao;
            i++;
        }
    }
}
