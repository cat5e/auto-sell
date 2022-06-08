package autosell.Gestores;

import autosell.CustomExceptions.CustomExeption;
import autosell.Enumeracoes.TipoColaborador;
import autosell.Enumeracoes.TipoEstabelecimento;
import autosell.Modelos.Colaborador;
import autosell.Modelos.Estabelecimento;
import autosell.Modelos.Veiculo;
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
            GestorEstabelecimentos.getInstance().setListagem((LinkedList<Estabelecimento>) objectInputStream.readObject());
            GestorColaboradores.getInstance().setListagem((LinkedList<Colaborador>) objectInputStream.readObject());
            GestorVeiculos.getInstance().setListagem((LinkedList<Veiculo>) objectInputStream.readObject());
        }
    }

    public boolean escreverDados() throws IOException { 
        var file = new AppFileHandler(ficheiro).create();
        
        try (var objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(GestorEstabelecimentos.getInstance().getListagem());
            objectOutputStream.writeObject(GestorColaboradores.getInstance().getListagem());
            objectOutputStream.writeObject(GestorVeiculos.getInstance().getListagem());
        }
        
        return true;
    }
    
    public void popularDadosIniciais() throws IOException {
        var gestorEstabelecimentos = GestorEstabelecimentos.getInstance();
        var estabelecimento = new Estabelecimento("AutoSell - Lisboa", 
                "lisboa@autosell.pt", 
                "+351 211 000 00", 
                "", 
                1000, 
                TipoEstabelecimento.SEDE);
        gestorEstabelecimentos.adicionar(estabelecimento);
        
        var estabelecimento1 = new Estabelecimento("Filial AutoSell - Leiria", 
                "leiria@autosell.pt", 
                "+351 248 500 323", 
                "Rua da Gordinhela, N2", 
                100, 
                TipoEstabelecimento.FILIAL);
        gestorEstabelecimentos.adicionar(estabelecimento1);
        
        var colaborador = new Colaborador("Administrador", 
                "admin@autosell.pt", 
                estabelecimento, 
                "1234", 
                TipoColaborador.ADMINISTRADOR);
        GestorColaboradores.getInstance().adicionar(colaborador);
        
        var gestorVeiculos = GestorVeiculos.getInstance();
        gestorVeiculos.adicionar(new Veiculo(1234.02F, "AA-11-AA", "Mercedes", estabelecimento));
        gestorVeiculos.adicionar(new Veiculo(35435.52F, "BB-11-CC", "BMW", estabelecimento));
        gestorVeiculos.adicionar(new Veiculo(23644F, "AB-11-AA", "Tesla", estabelecimento));
        gestorVeiculos.adicionar(new Veiculo(18902F, "AC-11-AA", "VW", estabelecimento));
        gestorVeiculos.adicionar(new Veiculo(12454F, "AD-11-AA", "Toyota", estabelecimento));
        gestorVeiculos.adicionar(new Veiculo(12240.12F, "AE-11-AA", "Porshe", estabelecimento));
        gestorVeiculos.adicionar(new Veiculo(2334.00F, "AF-11-AA", "Opel", estabelecimento));
        gestorVeiculos.adicionar(new Veiculo(45644.11F, "AG-11-AA", "Ford", estabelecimento));
        
        if(escreverDados()) {
            AppLogger.LOG.info(this, "Os dados iniciais foram populados com sucesso");
        }
    }
}
