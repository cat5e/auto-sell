package autosell.modelos;

import autosell.enumeracoes.*;
import java.io.Serializable;

public class Veiculo implements Serializable{

    private float preco;
    private String matricula;
    private int quilometros;
    private int anoRegisto;
    private MesesAno mesRegisto;
    private String marca;
    private String modelo;
    private String cor;
    private TipoCombustivel combustivel;
    private float celindrada;
    private float potencia;
    private int lotacao;
    private ClassesVeiculos classe;
    private int numeorMudancas;
    private int numeroPortas;
    private String caracteristicas;
    private String observacoes;
    private Estabelecimento estabelecimento;
    
    public Veiculo(float preco, String matricula, String marca, Estabelecimento estabelecimento) {
        this.preco = preco;
        this.matricula = matricula;
        this.marca = marca;
        this.estabelecimento = estabelecimento;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters Section">
    public float getPreco() {
        return preco;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getQuilometros() {
        return quilometros;
    }

    public int getAnoRegisto() {
        return anoRegisto;
    }

    public MesesAno getMesRegisto() {
        return mesRegisto;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public TipoCombustivel getCombustivel() {
        return combustivel;
    }

    public float getCelindrada() {
        return celindrada;
    }

    public float getPotencia() {
        return potencia;
    }

    public int getLotacao() {
        return lotacao;
    }

    public ClassesVeiculos getClasse() {
        return classe;
    }

    public int getNumeorMudancas() {
        return numeorMudancas;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Setters Section">
    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setQuilometros(int quilometros) {
        this.quilometros = quilometros;
    }

    public void setAnoRegisto(int anoRegisto) {
        this.anoRegisto = anoRegisto;
    }

    public void setMesRegisto(MesesAno mesRegisto) {
        this.mesRegisto = mesRegisto;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setCombustivel(TipoCombustivel combustivel) {
        this.combustivel = combustivel;
    }

    public void setCelindrada(float celindrada) {
        this.celindrada = celindrada;
    }

    public void setPotencia(float potencia) {
        this.potencia = potencia;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public void setClasse(ClassesVeiculos classe) {
        this.classe = classe;
    }

    public void setNumeorMudancas(int numeorMudancas) {
        this.numeorMudancas = numeorMudancas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
// </editor-fold> 

    @Override
    public String toString() {
        return "(" + preco + ") " + matricula + " | " + marca + " | " + modelo + " | " +  anoRegisto;
    }

    
    
}
