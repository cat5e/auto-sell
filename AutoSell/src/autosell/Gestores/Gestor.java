package autosell.Gestores;

import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Gestor<T> {
    protected LinkedList<T> ts;

    public Gestor() {
        ts = new LinkedList<>();
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
    
    public LinkedList<T> getListagem(Predicate<T> predicate){
         return ts.stream()
            .filter(predicate)
            .collect(Collectors.toCollection(LinkedList::new));
    }
    
    
    /*
        From StackOverFlow : https://stackoverflow.com/questions/23699371/java-8-distinct-by-property?page=1&tab=scoredesc#tab-top
    */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
