package autosell.Modelos;

import java.io.Serializable;
import java.util.LinkedList;

public class Evento implements Serializable {
      private String nome;
      private String dataInicio;
      private String dataFim;
      private String local;
      private final LinkedList<Veiculo> veiculos;

    public Evento(String nome, String dataInicio, String dataFim, String local) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        veiculos = new LinkedList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LinkedList<Veiculo> getVeiculos() {
        return new LinkedList<>(veiculos);
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if(veiculo == null || veiculos.contains(veiculo)){
            return false;
        }
        return veiculos.add(veiculo);
    }
      
    public boolean removeVeiculo(Veiculo veiculo) {
        if(veiculo == null || !veiculos.contains(veiculo)){
            return false;
        }
        return veiculos.remove(veiculo);
    }
      
      
}
