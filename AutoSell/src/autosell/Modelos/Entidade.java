package autosell.Modelos;

import autosell.Enumeracoes.TipoEntidade;
import java.io.Serializable;

public class Entidade implements Serializable{
    private String nome;
    private String dataNascimento;
    private String nif;
    private String numeroTelefone;
    private String email;
    private String morada;
    private TipoEntidade tipoEntidade;

    public Entidade(String nome, String nif, String numeroTelefone, TipoEntidade tipoEntidade) {
        this.nome = nome;
        this.nif = nif;
        this.numeroTelefone = numeroTelefone;
        this.tipoEntidade = tipoEntidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public TipoEntidade getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(TipoEntidade tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    } 
}
