package autosell.modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Intervencao implements Serializable {
    private LocalDate data;
    private Veiculo veiculo;
    private String descricao;
    private final LinkedList<Consumivel> consumiveis;
    private Colaborador tecnico;
    private Estabelecimento estabelecimento; 

    public Intervencao(LocalDate data, Veiculo veiculo, String descricao, Colaborador tecnico, Estabelecimento estabelecimento) {
        this.data = data;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.tecnico = tecnico;
        this.estabelecimento = estabelecimento;
        consumiveis = new LinkedList<>();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LinkedList<Consumivel> getConsumiveis() {
        return new LinkedList<>(consumiveis);
    }

    public Colaborador getTecnico() {
        return tecnico;
    }

    public void setTecnico(Colaborador tecnico) {
        this.tecnico = tecnico;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
    public boolean adidionarConsumivel(Consumivel consumivel){
        if(consumivel == null || consumiveis.contains(consumivel)){
            return false;
        }
        
        return consumiveis.add(consumivel);
    }
    
    public boolean removerConsumivel(Consumivel consumivel){
        if(consumivel == null || !consumiveis.contains(consumivel)){
            return false;
        }
        
        return consumiveis.remove(consumivel);
    }
    
}
