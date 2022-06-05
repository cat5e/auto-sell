package autosell.Gestores;

import autosell.CustomExceptions.CustomExeption;
import autosell.Utils.AppFileHandler;
import autosell.Utils.AppLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public enum GestorArmazenamentoDados {
    INSTANCIA;

    private final String ficheiro;

    private GestorArmazenamentoDados() {
        ficheiro = System.getProperty("user.home") + File.separator + "AutoSell" + File.separator + "BaseDados.autosell";
    }

    public void lerDados() throws IOException, ClassNotFoundException, CustomExeption {

        var file = new AppFileHandler(ficheiro).seek();
        var fileStream = new FileInputStream(file);
        var objectInputStream = new ObjectInputStream(fileStream);

        // TODO: implementar a criação das listas de todos os gestores
        //var firstread = objectInputStream.readObject();
        objectInputStream.close();
    }

    public void escreverDados() throws IOException { 
        var file = new AppFileHandler(ficheiro).create();
        
        var objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

        // TODO: Implementar a obtenção das listagens aqui. 
        // objectOutputStream.writeObject(colaboradores1);
        objectOutputStream.close();
    }
    
    public void popularDadosIniciais() throws IOException {
        
        escreverDados();
        // TODO: Implementar este método após criar os modelos
        //AppLogger.LOG.info(this, "Os dados iniciais foram populados com sucesso");
    }
}
