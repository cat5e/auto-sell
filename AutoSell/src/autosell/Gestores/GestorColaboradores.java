package autosell.Gestores;

import autosell.Modelos.Colaborador;
import static autosell.Utils.ValidacoesUtils.isNullOrEmpty;
import java.util.LinkedList;

public enum GestorColaboradores {
    INSTANCIA;
    public final int LIMITE_MIN_CHAR_PASSWORD = 4;
    
    private LinkedList<Colaborador> colaboradores;
    
    private GestorColaboradores(){
        colaboradores = new LinkedList<>();
    }
    
    public boolean adicionar(Colaborador colaborador){
        if(colaborador == null || colaboradores.contains(colaborador)) {
            return false;
        }
        
        return colaboradores.add(colaborador);
    }
    
    public LinkedList<Colaborador> getListagem(){
        return new LinkedList<>(colaboradores);
    }
    
    public void setListagem(LinkedList<Colaborador> colaboradores){
        this.colaboradores = colaboradores;
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
        return colaboradores.stream()
            .filter((colaborador) -> colaborador.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }
    
    public boolean remover(Colaborador colaborador){
        if(colaborador == null || !colaboradores.contains(colaborador)){
            return false;
        }
        
        return colaboradores.remove(colaborador);
    }
    
    public boolean isEmailDuplicated(String email){
        return getColaboradorbyEmail(email) != null;
    }
}
