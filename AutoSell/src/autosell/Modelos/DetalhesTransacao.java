package autosell.Modelos;

public class DetalhesTransacao {
    private String descricao;
    private String detalhes;
    private float valor;

    public DetalhesTransacao(String descricao, String detalhes, float valor) {
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.valor = valor;
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