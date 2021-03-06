package autosell.gestores;

import autosell.modelos.Transacao;

public class GestorTransacoes  extends Gestor<Transacao>{
    private GestorTransacoes(){}
    
    private static class Singleton {
        private static final GestorTransacoes GESTOR_TRANSACOES = new GestorTransacoes();
    }

    public static GestorTransacoes getInstance(){
        return Singleton.GESTOR_TRANSACOES;
    }
}
