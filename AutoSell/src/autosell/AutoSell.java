package autosell;

import autosell.CustomExceptions.CustomExeption;
import autosell.Gestores.GestorArmazenamentoDados;
import autosell.Utils.AppLogger;
import autosell.Vistas.JanelaLogin;
import java.io.IOException;

public class AutoSell {

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            AppLogger.LOG.severe(AutoSell.class.getClass(), ex);
        }
        //</editor-fold>

        try {
            GestorArmazenamentoDados.INSTANCIA.lerDados();
        }
        catch (CustomExeption ne){
            AppLogger.LOG.warning(GestorArmazenamentoDados.class.getName(), ne);
            
            try {
                GestorArmazenamentoDados.INSTANCIA.popularDadosIniciais();
            } 
            catch (IOException ex) {
                AppLogger.LOG.severe(GestorArmazenamentoDados.class.getName(), ex);
                return;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            AppLogger.LOG.severe(GestorArmazenamentoDados.class.getName(), e);
            return;
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new JanelaLogin().setVisible(true);
        });
    }
}
