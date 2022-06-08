package autosell.Gestores;

import autosell.Modelos.Evento;

public class GestorEventos extends Gestor<Evento> {

    private GestorEventos() {

    }

    private static class Singleton {

        private static final GestorEventos GESTOR_EVENTO = new GestorEventos();
    }

    public static GestorEventos getInstance() {
        return Singleton.GESTOR_EVENTO;
    }
}