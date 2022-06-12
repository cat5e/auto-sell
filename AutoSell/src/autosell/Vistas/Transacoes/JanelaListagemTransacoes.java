package autosell.Vistas.Transacoes;

import autosell.Gestores.GestorTransacoes;
import autosell.Modelos.Colaborador;
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
                    "Colaborador",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorTransacoes.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Transacao value) {
        return new JanelaEditarTransacao(value, colaboradorAutenticado, value.getTipo());
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
            aux[i][6] = transacao.getColaborador();
            aux[i][7] = transacao;
            i++;
        }
    }
}
