package autosell.Gestores;

import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    // TODO: Precisa de ser testado
    public LinkedList<T> getListagem(Predicate<T> predicate){
         return ts.stream()
            .filter(predicate)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
