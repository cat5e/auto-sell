package autosell.Gestores;

import autosell.Modelos.Estabelecimento;
import java.util.LinkedList;

public enum GestorEstabelecimentos {
    INSTANCIA;
    
    private LinkedList<Estabelecimento> estabelecimentos;
    
    private GestorEstabelecimentos() {
        estabelecimentos = new LinkedList<>();
    }
    
    public void adicionar(Estabelecimento estabelecimento){
        if(estabelecimento == null || estabelecimentos.contains(estabelecimento)) {
            return;
        }
        
        estabelecimentos.add(estabelecimento);
    }
    
    public LinkedList<Estabelecimento> getListagem(){
        return new LinkedList<>(estabelecimentos);
    }
    
    public void setListagem(LinkedList<Estabelecimento> estabelecimentos){
        this.estabelecimentos = estabelecimentos;
    }
}
