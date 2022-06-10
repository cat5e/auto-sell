package autosell.Gestores;

import autosell.Modelos.Artigo;

public class GestorArtigos extends Gestor<Artigo> {

    private GestorArtigos() {

    }

    private static class Singleton {

        private static final GestorArtigos GESTOR_ARTIGOS = new GestorArtigos();
    }

    public static GestorArtigos getInstance() {
        return Singleton.GESTOR_ARTIGOS;
    }
}
