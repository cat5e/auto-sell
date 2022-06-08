package autosell.Gestores;

import autosell.Modelos.Entidade;

public class GestorEntidades extends Gestor<Entidade> {

    private GestorEntidades() {

    }

    private static class Singleton {

        private static final GestorEntidades GESTOR_ENTIDADES = new GestorEntidades();
    }

    public static GestorEntidades getInstance() {
        return Singleton.GESTOR_ENTIDADES;
    }
}
