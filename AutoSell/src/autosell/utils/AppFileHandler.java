package autosell.utils;

import autosell.exceptions.CustomExeption;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOException;


public class AppFileHandler {

    private final String filePath;
    
    public AppFileHandler(String filePath) {
        this.filePath = filePath;
    }
    
    public File create() throws IOException {
        var file = new File(filePath);
        
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
        
        return file;
    }
    
    public File seek() throws CustomExeption, IIOException{
        var file = new File(filePath);

        if (!file.exists()) {
            throw new CustomExeption(
                    new StringBuilder("O ficheiro '")
                            .append(filePath)
                            .append("', não existe.")
                            .toString());
        }

        if (!file.canRead()) {
            throw new IIOException(
                    new StringBuilder("Não foi possível ler o ficheiro. ")
                            .append("O ficheiro '")
                            .append(filePath)
                            .append("' pode não existir corrumpido.")
                            .toString());
        }

        if (file.length() < 1) {
            throw new CustomExeption(
                    new StringBuilder("O ficheiro '")
                            .append(filePath)
                            .append("' está vazio.")
                            .toString());
        }
        
        return file;
    }
}
