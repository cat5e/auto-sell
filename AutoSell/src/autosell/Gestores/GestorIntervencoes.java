package autosell.Gestores;

import autosell.Modelos.Intervencao;

public class GestorIntervencoes extends Gestor<Intervencao> {

    private GestorIntervencoes() {

    }

    private static class Singleton {

        private static final GestorIntervencoes GESTOR_INTERVENCOES = new GestorIntervencoes();
    }

    public static GestorIntervencoes getInstance() {
        return Singleton.GESTOR_INTERVENCOES;
    }
}
