package autosell.Modelos;

import java.util.LinkedList;

public class Intervencao {
    private String data;
    private Veiculo veiculo;
    private String descricao;
    private final LinkedList<Artigo> artigos;
    private Colaborador tecnico;
    private Estabelecimento estabelecimento; 

    public Intervencao(String data, Veiculo veiculo, String descricao, Colaborador tecnico, Estabelecimento estabelecimento) {
        this.data = data;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.tecnico = tecnico;
        this.estabelecimento = estabelecimento;
        artigos = new LinkedList<>();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LinkedList<Artigo> getArtigos() {
        return new LinkedList<>(artigos);
    }

    public Colaborador getTecnico() {
        return tecnico;
    }

    public void setTecnico(Colaborador tecnico) {
        this.tecnico = tecnico;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
    public boolean adidionarArtigo(Artigo artigo){
        if(artigo == null || artigos.contains(artigo)){
            return false;
        }
        
        return artigos.add(artigo);
    }
    
    public boolean removerArtigo(Artigo artigo){
        if(artigo == null || !artigos.contains(artigo)){
            return false;
        }
        
        return artigos.remove(artigo);
    }
    
}
