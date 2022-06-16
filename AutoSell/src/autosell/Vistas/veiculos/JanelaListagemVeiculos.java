package autosell.vistas.veiculos;

import autosell.gestores.GestorVeiculos;
import autosell.modelos.Colaborador;
import autosell.modelos.Veiculo;
import autosell.vistas.JanelaListagem;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class JanelaListagemVeiculos extends JanelaListagem<Veiculo> {

    public JanelaListagemVeiculos(JDesktopPane desktopPane, Colaborador colaboradorAutenticado) {
        super("Listagem de Veículos",
                new String[]{
                    "Preço",
                    "Matrícula",
                    "Marca",
                    "Modelo",
                    "Ano",
                    "Combustível",
                    "Estabelecimento",
                    "Estado",
                    "Obj"},
                desktopPane,
                colaboradorAutenticado,
                GestorVeiculos.getInstance());
    }

    @Override
    protected JInternalFrame getInternalFrame(Veiculo value) {
        return new JanelaEditarVeiculo(value);
    }

    @Override
    protected void getData(Object[][] aux, LinkedList<Veiculo> veiculos) {
        int i = 0;
        for (Veiculo veiculo : veiculos) {
            aux[i][0] = veiculo.getPreco();
            aux[i][1] = veiculo.getMatricula();
            aux[i][2] = veiculo.getMarca();
            aux[i][3] = veiculo.getModelo();
            aux[i][4] = veiculo.getAnoRegisto();
            aux[i][5] = veiculo.getCombustivel();
            aux[i][6] = veiculo.getEstabelecimento();
            aux[i][7] = veiculo.getEstadoVeiculo();
            aux[i][8] = veiculo;
            i++;
        }
    }
}
