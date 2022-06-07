package autosell.Modelos;

import autosell.Enumeracoes.TipoColaborador;
import java.io.Serializable;

public class Colaborador implements Serializable {
    private String nome;
    private String email;
    private Estabelecimento estabelecimento;
    private String password;
    private TipoColaborador tipoColaborador;

    public Colaborador(String nome, String email, Estabelecimento estabelecimento, String password, TipoColaborador tipoColaborador) {
        this.nome = nome;
        this.email = email;
        this.estabelecimento = estabelecimento;
        this.password = password;
        this.tipoColaborador = tipoColaborador;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter Section">
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public String getPassword() {
        return password;
    }
    
    public TipoColaborador getTipoColaborador() {
        return tipoColaborador;
    }
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Setter Section">
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setTipoColaborador(TipoColaborador tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Override Section">
    @Override
    public String toString() {
        return nome;
    } // </editor-fold>
}
