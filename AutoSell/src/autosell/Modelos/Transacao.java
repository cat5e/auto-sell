package autosell.Modelos;

import autosell.Enumeracoes.*;
import java.util.LinkedList;

public class Transacao {
    private TipoTransacao tipo;
    private String data;
    private LinkedList<Veiculo> veiculosAssociados;
    private LinkedList<Veiculo> veiculosTroca;
    private Entidade entidade;
    private LinkedList<DetalhesTransacao> detalhesTransacao;
    private Estabelecimento estabelecimento;
    private Colaborador colaborador;
    private EstadosTransacao estado;
}
