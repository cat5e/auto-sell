package autosell.Modelos;

public class Artigo {
    private String referencia;
    private String nome;
    private float quantidadeMinima;
    private float quantidadeAtual;
    private String unidade;
    private Estabelecimento estabelecimento;
    private String descricao;

    public Artigo(String referencia, String nome, float quantidadeMinima, float quantidadeAtual, String unidade, Estabelecimento estabelecimento) {
        this.referencia = referencia;
        this.nome = nome;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeAtual = quantidadeAtual;
        this.unidade = unidade;
        this.estabelecimento = estabelecimento;
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

    public float getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(float quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public float getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(float quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

    
}
