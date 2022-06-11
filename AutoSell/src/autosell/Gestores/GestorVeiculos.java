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
    
    public Object[] getMarcasRegistadas(){
        return ts.stream()
            .filter(distinctByKey(Veiculo::getMarca))
            .map(Veiculo::getMarca)
                .toArray(Object[]::new);
    }
    
    public boolean isMatriculaDuplicada(String matricula){
         return ts.stream()
            .filter((v) -> v.getMatricula().equals(matricula))
            .findFirst()
            .orElse(null) != null;
    }
}
