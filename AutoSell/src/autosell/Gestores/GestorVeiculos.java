package autosell.Gestores;

import autosell.Modelos.Veiculo;


public class GestorVeiculos extends Gestor<Veiculo>{
    
    private GestorVeiculos(){}
    
    private static class Singleton {
        private static final GestorVeiculos GESTOR_VEICULOS = new GestorVeiculos();
    }

    public static GestorVeiculos getInstance(){
        return Singleton.GESTOR_VEICULOS;
    }
}
