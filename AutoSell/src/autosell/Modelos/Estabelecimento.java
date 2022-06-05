package autosell.Modelos;

import autosell.Enumeracoes.TipoEstabelecimento;
import java.io.Serializable;

public class Estabelecimento implements Serializable{
    private String nome;
    private TipoEstabelecimento tipo;
    private String morada;
    private int limiteVeiculos;
    private String numeroTelefone;
    private String email;
    
    public Estabelecimento(String nome, String email, String numeroTelefone, String morada, int limiteVeiculos, TipoEstabelecimento tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.morada = morada;
        this.limiteVeiculos = limiteVeiculos;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters Section">
    public String getNome() {
        return nome;
    }

    public TipoEstabelecimento getTipo() {
        return tipo;
    }

    public String getMorada() {
        return morada;
    }

    public int getLimiteVeiculos() {
        return limiteVeiculos;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getEmail() {
        return email;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Setter Section">
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TipoEstabelecimento tipo) {
        this.tipo = tipo;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setLimiteVeiculos(int limiteVeiculos) {
        this.limiteVeiculos = limiteVeiculos;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Override Section">
    @Override
    public String toString() {
        return nome;
    } // </editor-fold>
}
