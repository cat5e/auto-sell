package autosell.Gestores;

import autosell.Modelos.Veiculo;
import java.util.LinkedList;

public enum GestorVeiculos {
    INSTANCIA;
    
    private LinkedList<Veiculo> veiculos;
    
    private GestorVeiculos() {
        veiculos = new LinkedList<>();
    }
    
    public void adicionar(Veiculo veiculo){
        if(veiculo == null || veiculos.contains(veiculo)) {
            return;
        }
        
        veiculos.add(veiculo);
    }
    
    public LinkedList<Veiculo> getListagem(){
        return new LinkedList<>(veiculos);
    }
    
    public void setListagem(LinkedList<Veiculo> veiculos){
        this.veiculos = veiculos;
    }
}
