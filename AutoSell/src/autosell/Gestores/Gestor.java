package autosell.Gestores;

import java.util.LinkedList;

public class Gestor<T> {
    protected LinkedList<T> ts;

    public Gestor() {
        this.ts = new LinkedList<>();
    }
    
    public boolean adicionar(T t){
        if(t == null || ts.contains(t)) {
            return false;
        }
        
        return ts.add(t);
    }
    
    public LinkedList<T> getListagem(){
        return new LinkedList<>(ts);
    }
    
    public void setListagem(LinkedList<T> ts){
        this.ts = ts;
    }
    
    public boolean remover(T t){
        if(t == null || !ts.contains(t)){
            return false;
        }
        
        return ts.remove(t);
    }
    
}
