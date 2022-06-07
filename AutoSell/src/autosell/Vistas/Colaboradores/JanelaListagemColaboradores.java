package autosell.Vistas.Colaboradores;

import autosell.Gestores.GestorColaboradores;
import autosell.Modelos.Colaborador;
import autosell.Vistas.JanelaListagem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemColaboradores extends JanelaListagem<Colaborador> {
   
    public JanelaListagemColaboradores(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Colaboradores", 
                new String[]{
                    "Nome", 
                    "E-mail", 
                    "Estabelecimento", 
                    "Veiculos Vendidos", 
                    "Obj"},
                desktopPane, 
                colaboradorAutenticado);
    }

    @Override
    protected void acaoRemoverGestor(Colaborador colaborador){
        GestorColaboradores.INSTANCIA.remover(colaborador);
    }

    @Override
    protected Object[][] getData() {
        var listagem = GestorColaboradores.INSTANCIA.getListagem();

        var aux = new Object[listagem.size()][5];

        int i = 0;

        for (Colaborador colaborador : listagem) {
            aux[i][0] = colaborador.getNome();
            aux[i][1] = colaborador.getEmail();
            aux[i][2] = colaborador.getEstabelecimento();
            aux[i][3] = "Not Implemented.";
            aux[i][4] = colaborador;
            i++;
        }

        return aux;
    }
    
    @Override
    protected JInternalFrame getInternalFrame(Colaborador value){
        return new JanelaEditarColaborador(value, colaboradorAutenticado);
    }
}
