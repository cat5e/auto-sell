package autosell.modelos;

import autosell.enumeracoes.TipoTransacao;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Transacao implements Serializable {

    private TipoTransacao tipo;
    private LocalDate data;
    private LinkedList<Veiculo> veiculosAssociados;
    private LinkedList<Veiculo> veiculosTroca;
    private Entidade entidade;
    private LinkedList<DetalhesTransacao> detalhesTransacao;
    private Colaborador colaborador;
    private float precoFinal;

    public Transacao(TipoTransacao tipo, LocalDate data, Colaborador colaborador) {
        this.tipo = tipo;
        this.data = data;
        this.colaborador = colaborador;
        veiculosAssociados = new LinkedList<>();
        veiculosTroca = new LinkedList<>();
        detalhesTransacao = new LinkedList<>();
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public LinkedList<Veiculo> getVeiculosTroca() {
        return veiculosTroca;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public float getPrecoFinal() {
        return precoFinal;
    }

    public LinkedList<Veiculo> getVeiculosAssociados() {
        return veiculosAssociados;
    }

    public LinkedList<DetalhesTransacao> getDetalhesTransacao() {
        return detalhesTransacao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setVeiculosAssociados(LinkedList<Veiculo> veiculosAssociados) {
        this.veiculosAssociados = veiculosAssociados;
    }

    public void setVeiculosTroca(LinkedList<Veiculo> veiculosTroca) {
        this.veiculosTroca = veiculosTroca;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public void setDetalhesTransacao(LinkedList<DetalhesTransacao> detalhesTransacao) {
        this.detalhesTransacao = detalhesTransacao;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void setPrecoFinal(float precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void adicionarVeiculoAssociado(Veiculo veiculo) {
        if (veiculo == null || veiculosAssociados.contains(veiculo)) {
            return;
        }

        veiculosAssociados.add(veiculo);
    }

    public void removerVeiculoAssociado(Veiculo veiculo) {
        if (veiculo == null || !veiculosAssociados.contains(veiculo)) {
            return;
        }

        veiculosAssociados.remove(veiculo);
    }

    public void adicionarVeiculoTroca(Veiculo veiculo) {
        if (veiculo == null || veiculosTroca.contains(veiculo)) {
            return;
        }

        veiculosTroca.add(veiculo);
    }

    public void removerVeiculosTroca(Veiculo veiculo) {
        if (veiculo == null || !veiculosTroca.contains(veiculo)) {
            return;
        }

        veiculosTroca.remove(veiculo);
    }

    public void adicionarDetalhesTransacao(DetalhesTransacao detalhe) {
        if (detalhe == null || detalhesTransacao.contains(detalhe)) {
            return;
        }

        detalhesTransacao.add(detalhe);
    }

    public void removerDetalhesTransacao(DetalhesTransacao detalhe) {
        if (detalhe == null || !detalhesTransacao.contains(detalhe)) {
            return;
        }

        detalhesTransacao.remove(detalhe);
    }

    public void limparDetalhesTransacao(){
        detalhesTransacao.clear();
    }
    
    public void limparVeiculosTroca() {
        veiculosTroca.clear();
    }
    
    public void limparVeiculosAssociados() {
        veiculosAssociados.clear();
    }
            
            
}
