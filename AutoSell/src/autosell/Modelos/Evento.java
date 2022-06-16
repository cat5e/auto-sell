package autosell.Modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Evento implements Serializable {
      private String nome;
      private LocalDate dataInicio;
      private LocalDate dataFim;
      private String local;
      private final LinkedList<Veiculo> veiculos;

    public Evento(String nome, LocalDate dataInicio, LocalDate dataFim, String local) {
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
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
