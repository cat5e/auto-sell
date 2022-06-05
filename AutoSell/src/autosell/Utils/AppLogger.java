package autosell.Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum AppLogger {
    LOG;

    private final Logger logger;
     private final String ficheiro;
     
    private AppLogger(){
        
        logger = Logger.getLogger("AutoSell");
        ficheiro = System.getProperty("user.home") + File.separator + "AutoSell" 
                    + File.separator + "AutoSell.log";
        
        try{

            new AppFileHandler(ficheiro).create();
 
            var fileHandler  = new FileHandler(ficheiro);
           
            fileHandler.setLevel(Level.ALL);
            
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
     
        } catch (IOException e){
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public void severe(Object obj, Throwable e){
       logger.log(Level.SEVERE, obj.getClass().getName() + " - " + e.getMessage(), e);
    }
    
     public void severe(String obj, Throwable e){
       logger.log(Level.SEVERE, obj + " - " + e.getMessage(), e);
    }
    
    public void info(Object obj, String message){
       logger.log(Level.INFO, "{0} - {1}", new Object[]{obj.getClass().getName(), message});
    }
    
    public void warning(Object obj, Throwable e){
       logger.log(Level.WARNING, obj.getClass().getName() + " - " + e.getMessage(), e);
    }
    
    public void warning(String obj, Throwable e){
       logger.log(Level.WARNING, obj + " - " + e.getMessage(), e);
    }
    
    
}
