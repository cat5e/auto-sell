package autosell.modelos;

import java.io.Serializable;

public class Consumivel implements Serializable{
    private String referencia;
    private String nome;
    private float quantidade;
    private String unidade;
    private Artigo artigoReferencia;

    public Consumivel(String referencia, String nome, float quantidade, String unidade, Artigo artigoReferencia) {
        this.referencia = referencia;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.artigoReferencia = artigoReferencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Artigo getArtigoReferencia() {
        return artigoReferencia;
    }

    public void setArtigoReferencia(Artigo artigoReferencia) {
        this.artigoReferencia = artigoReferencia;
    }
    
    

}
