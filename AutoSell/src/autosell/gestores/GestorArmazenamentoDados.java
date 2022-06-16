package autosell.gestores;

import autosell.exceptions.CustomExeption;
import autosell.enumeracoes.TipoColaborador;
import autosell.enumeracoes.TipoEstabelecimento;
import autosell.modelos.Artigo;
import autosell.modelos.Colaborador;
import autosell.modelos.Entidade;
import autosell.modelos.Estabelecimento;
import autosell.modelos.Evento;
import autosell.modelos.Intervencao;
import autosell.modelos.Transacao;
import autosell.modelos.Veiculo;
import autosell.utils.AppFileHandler;
import autosell.utils.AppLogger;
import autosell.utils.data.seed.Seeder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public enum GestorArmazenamentoDados {
    INSTANCIA;

    private final String ficheiro;

    private GestorArmazenamentoDados() {
        ficheiro = System.getProperty("user.home") + File.separator + "AutoSell" + File.separator + "BaseDados.autosell";
    }

    public void lerDados() throws IOException, ClassNotFoundException, CustomExeption {
        var file = new AppFileHandler(ficheiro).seek();
        var fileStream = new FileInputStream(file);
        
        try (var objectInputStream = new ObjectInputStream(fileStream)) {
            GestorEstabelecimentos.getInstance().setListagem((LinkedList<Estabelecimento>) objectInputStream.readObject());
            GestorColaboradores.getInstance().setListagem((LinkedList<Colaborador>) objectInputStream.readObject());
            GestorVeiculos.getInstance().setListagem((LinkedList<Veiculo>) objectInputStream.readObject());
            GestorEntidades.getInstance().setListagem((LinkedList<Entidade>) objectInputStream.readObject());
            GestorEventos.getInstance().setListagem((LinkedList<Evento>) objectInputStream.readObject());
            GestorArtigos.getInstance().setListagem((LinkedList<Artigo>) objectInputStream.readObject());
            GestorIntervencoes.getInstance().setListagem((LinkedList<Intervencao>) objectInputStream.readObject());
            GestorTransacoes.getInstance().setListagem((LinkedList<Transacao>) objectInputStream.readObject());
        }
    }

    public boolean escreverDados() throws IOException { 
        var file = new AppFileHandler(ficheiro).create();
        
        try (var objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(GestorEstabelecimentos.getInstance().getListagem());
            objectOutputStream.writeObject(GestorColaboradores.getInstance().getListagem());
            objectOutputStream.writeObject(GestorVeiculos.getInstance().getListagem());
            objectOutputStream.writeObject(GestorEntidades.getInstance().getListagem());
            objectOutputStream.writeObject(GestorEventos.getInstance().getListagem());
            objectOutputStream.writeObject(GestorArtigos.getInstance().getListagem());            
            objectOutputStream.writeObject(GestorIntervencoes.getInstance().getListagem());     
            objectOutputStream.writeObject(GestorTransacoes.getInstance().getListagem());     
        }
        
        return true;
    }
    
    public void popularDadosIniciais() throws IOException {

        new Seeder();
        
        if(escreverDados()) {
            AppLogger.LOG.info(this, "Os dados iniciais foram populados com sucesso");
        }
    }
}
