package autosell.Modelos;

import autosell.Enumeracoes.TipoDetalheTransacao;
import java.io.Serializable;

public class DetalhesTransacao implements Serializable{
    private String descricao;
    private String detalhes;
    private float valor;
    private TipoDetalheTransacao tipoDetalheTransacao;

    public TipoDetalheTransacao getTipoDetalheTransacao() {
        return tipoDetalheTransacao;
    }

    public void setTipoDetalheTransacao(TipoDetalheTransacao tipoDetalheTransacao) {
        this.tipoDetalheTransacao = tipoDetalheTransacao;
    }

    public DetalhesTransacao(String descricao, String detalhes, float valor, TipoDetalheTransacao tipoDetalheTransacao) {
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.valor = valor;
        this.tipoDetalheTransacao = tipoDetalheTransacao;
    }   
    
    // <editor-fold defaultstate="collapsed" desc="Getters Section">
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters Section">

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    // </editor-fold>
}