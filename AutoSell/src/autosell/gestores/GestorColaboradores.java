package autosell.gestores;

import autosell.modelos.Colaborador;
import static autosell.utils.ValidacoesUtils.isNullOrEmpty;

public class GestorColaboradores extends Gestor<Colaborador> {
    public final int LIMITE_MIN_CHAR_PASSWORD = 4;
    
    private GestorColaboradores()
    {
        
    }
    
    private static class Singleton {
        private static final GestorColaboradores GESTOR_COLABORADORES = new GestorColaboradores();
    }

    public static GestorColaboradores getInstance(){
        return Singleton.GESTOR_COLABORADORES;
    }
    
    public Colaborador validarCredenciais(String email, String password){
        if(isNullOrEmpty(email) || isNullOrEmpty(password)){
            return null;
        }
        
        var colaborador = getColaboradorbyEmail(email);
        
        if(colaborador != null && colaborador.getPassword().equals(password)){
            return colaborador;
        }
        
        return null;
    }
    
    private Colaborador getColaboradorbyEmail(String email) {
        return ts.stream()
            .filter((colaborador) -> colaborador.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }
    
    public boolean isEmailDuplicated(String email){
        return getColaboradorbyEmail(email) != null;
    }
}
