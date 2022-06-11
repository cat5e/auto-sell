package autosell.Modelos;

import autosell.Enumeracoes.*;
import java.util.LinkedList;

public class Transacao {
    private TipoTransacao tipo;
    private String data;
    private LinkedList<Veiculo> veiculosAssociados;
    private LinkedList<Veiculo> veiculosTroca;
    private Entidade entidade;
    private LinkedList<DetalhesTransacao> detalhesTransacao;
    private Estabelecimento estabelecimento;
    private Colaborador colaborador;
    private EstadosTransacao estado;
    private float precoFinal;

    public Transacao(TipoTransacao tipo, String data, Estabelecimento estabelecimento, Colaborador colaborador, EstadosTransacao estado) {
        this.tipo = tipo;
        this.data = data;
        this.estabelecimento = estabelecimento;
        this.colaborador = colaborador;
        this.estado = estado;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LinkedList<Veiculo> getVeiculosAssociados() {
        return veiculosAssociados;
    }

    public void setVeiculosAssociados(LinkedList<Veiculo> veiculosAssociados) {
        this.veiculosAssociados = veiculosAssociados;
    }

    public LinkedList<Veiculo> getVeiculosTroca() {
        return veiculosTroca;
    }

    public void setVeiculosTroca(LinkedList<Veiculo> veiculosTroca) {
        this.veiculosTroca = veiculosTroca;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public LinkedList<DetalhesTransacao> getDetalhesTransacao() {
        return detalhesTransacao;
    }

    public void setDetalhesTransacao(LinkedList<DetalhesTransacao> detalhesTransacao) {
        this.detalhesTransacao = detalhesTransacao;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public EstadosTransacao getEstado() {
        return estado;
    }

    public void setEstado(EstadosTransacao estado) {
        this.estado = estado;
    }

    public float getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(float precoFinal) {
        this.precoFinal = precoFinal;
    }
    
    
    
    
}
