package autosell.Gestores;

import autosell.Modelos.Estabelecimento;

public class GestorEstabelecimentos extends Gestor<Estabelecimento> {
   
    private GestorEstabelecimentos() {}
    
     private static class Singleton {
        private static final GestorEstabelecimentos GESTOR_ESTABELECIMENTOS = new GestorEstabelecimentos();
    }

    public static GestorEstabelecimentos getInstance(){
        return Singleton.GESTOR_ESTABELECIMENTOS;
    }
}
