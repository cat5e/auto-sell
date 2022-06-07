package autosell.Gestores;

import autosell.CustomExceptions.CustomExeption;
import autosell.Enumeracoes.TipoColaborador;
import autosell.Enumeracoes.TipoEstabelecimento;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Estabelecimento;
import autosell.Utils.AppFileHandler;
import autosell.Utils.AppLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public enum GestorArmazenamentoDados {
    INSTANCIA;

    // TODO: As listagens dos gestores deveriam ser implementadas aqui para proteção de carregamento e disponibilização.
    
    private final String ficheiro;

    private GestorArmazenamentoDados() {
        ficheiro = System.getProperty("user.home") + File.separator + "AutoSell" + File.separator + "BaseDados.autosell";
    }

    public void lerDados() throws IOException, ClassNotFoundException, CustomExeption {
        var file = new AppFileHandler(ficheiro).seek();
        var fileStream = new FileInputStream(file);
        
        try (var objectInputStream = new ObjectInputStream(fileStream)) {
            GestorEstabelecimentos.INSTANCIA.setListagem((LinkedList<Estabelecimento>) objectInputStream.readObject());
            GestorColaboradores.INSTANCIA.setListagem((LinkedList<Colaborador>) objectInputStream.readObject());
        }
    }

    public boolean escreverDados() throws IOException { 
        var file = new AppFileHandler(ficheiro).create();
        
        try (var objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(GestorEstabelecimentos.INSTANCIA.getListagem());
            objectOutputStream.writeObject(GestorColaboradores.INSTANCIA.getListagem());
        }
        
        return true;
    }
    
    public void popularDadosIniciais() throws IOException {
        var estabelecimento = new Estabelecimento("AutoSell - Lisboa", 
                "lisboa@autosell.pt", 
                "+351 211 000 00", 
                "", 
                1000, 
                TipoEstabelecimento.SEDE);
        GestorEstabelecimentos.INSTANCIA.adicionar(estabelecimento);
        
        var estabelecimento1 = new Estabelecimento("Filial AutoSell - Leiria", 
                "leiria@autosell.pt", 
                "+351 248 500 323", 
                "Rua da Gordinhela, N2", 
                100, 
                TipoEstabelecimento.FILIAL);
        GestorEstabelecimentos.INSTANCIA.adicionar(estabelecimento1);
        
        var colaborador = new Colaborador("Administrador", 
                "admin@autosell.pt", 
                estabelecimento, 
                "1234", 
                TipoColaborador.ADMINISTRADOR);
        GestorColaboradores.INSTANCIA.adicionar(colaborador);
        
        
        
        if(escreverDados()) {
            AppLogger.LOG.info(this, "Os dados iniciais foram populados com sucesso");
        }
    }
}
