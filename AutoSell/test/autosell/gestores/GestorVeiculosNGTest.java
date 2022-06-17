package autosell.gestores;

import autosell.modelos.Veiculo;
import java.util.LinkedList;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class GestorVeiculosNGTest {

    GestorVeiculos instance;

    public GestorVeiculosNGTest() {
        instance = GestorVeiculos.getInstance();
    }


    @AfterMethod
    public void tearDownMethod() throws Exception {
        for (Veiculo veiculo : instance.getListagem()) {
            instance.remover(veiculo);
        }
    }

    @Test
    public void testAdicionarShouldReturnFalseWhenVeiculoIsNull() {
        // Arrange
        Veiculo veiculo = null;

        // Act
        boolean result = instance.adicionar(veiculo);

        // Assert
        assertTrue(!result);
    }

    @Test
    public void testAdicionarShouldReturnFalseWhenVeiculoAlreadyExists() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "A", "B", null);
        instance.adicionar(v1);

        // Act
        boolean result = instance.adicionar(v1);

        // Assert
        assertTrue(!result);
    }

    @Test
    public void testAdicionarShouldReturnTrueWhenVeiculoIsNew() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "A", "B", null);
        instance.adicionar(v1);
        Veiculo v2 = new Veiculo(2, "C", "D", null);

        // Act
        boolean result = instance.adicionar(v2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetListagemShouldGetCopyOfList() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "A", "B", null);
        Veiculo v2 = new Veiculo(2, "C", "D", null);
        instance.adicionar(v1);
        instance.adicionar(v2);

        // Act
        LinkedList<Veiculo> copyList = instance.getListagem();
        copyList.remove(v1);
        LinkedList<Veiculo> result = instance.getListagem();

        // Assert
        assertEquals(2, result.size());

    }

    @Test
    public void testSetListagemListShouldBeNew() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "A", "B", null);
        Veiculo v2 = new Veiculo(2, "C", "D", null);
        LinkedList<Veiculo> newList = new LinkedList<>();
        instance.adicionar(v1);
        instance.adicionar(v2);
        newList.add(v1);
        newList.add(v2);

        // Act
        instance.setListagem(newList);
        LinkedList<Veiculo> result = instance.getListagem();

        // Assert
        assertEquals(newList, result);
    }

    @Test
    public void testRemoverShouldReturnFalseWhenVeiculoToRemoveIsNull() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "A", "B", null);
        Veiculo v2 = new Veiculo(2, "C", "D", null);
        Veiculo vn = null;
        instance.adicionar(v1);
        instance.adicionar(v2);

        // Act
        boolean result = instance.remover(vn);

        // Assert
        assertTrue(!result);
    }

    @Test
    public void testRemoverShouldReturnFalseWhenRemovingVeiculoThatNotExists() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "A", "B", null);
        Veiculo v2 = new Veiculo(2, "C", "D", null);
        Veiculo v3 = new Veiculo(3, "Z", "X", null);
        instance.adicionar(v1);
        instance.adicionar(v2);

        // Act
        boolean result = instance.remover(v3);

        // Assert
        assertTrue(!result);
    }

    @Test
    public void testRemoverShouldReturnTrueWhenVeiculoIsRemovedSuccessfully() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "A", "B", null);
        Veiculo v2 = new Veiculo(2, "C", "D", null);
        instance.adicionar(v1);
        instance.adicionar(v2);

        // Act
        boolean result = instance.remover(v2);
        LinkedList<Veiculo> veiculos = instance.getListagem();

        // Assert
        assertTrue(result);
        assertEquals(1, veiculos.size());
    }

    @Test
    public void testGetMarcasRegistadasHasNoDuplicatedMarca() {
        // Arrange
        String marca = "Test";
        Veiculo v1 = new Veiculo(1, "Test-1", marca, null);
        Veiculo v2 = new Veiculo(1, "Test-2", marca, null);
        instance.adicionar(v1);
        instance.adicionar(v2);
        int countEvidences = 0;

        // Act 
        Object[] result = instance.getMarcasRegistadas();
        for (Object obj : result) {
            if (String.valueOf(obj).equals(marca)) {
                countEvidences++;
            }
        }

        // Assert
        assertEquals(1, countEvidences);
    }

    @Test
    public void testIsMatriculaDuplicadaShouldReturnTrue() {
        // Arrange
        String matricula = "Test-1";
        instance.adicionar(new Veiculo(1, matricula, "Brand", null));

        // Act
        boolean result = instance.isMatriculaDuplicada(matricula);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsMatriculaDuplicadaShouldReturnFalse() {
        // Arrange
        String matricula = "Test-1";
        instance.adicionar(new Veiculo(1, "ABC-TEST-123", "Brand", null));

        // Act
        boolean result = instance.isMatriculaDuplicada(matricula);

        // Assert
        assertTrue(!result);
    }

    @Test
    public void testGetListagemShouldReturnResultsWhenValidPredicate() {
        // Arrange
        Veiculo v1 = new Veiculo(1, "ABC-TEST-123", "Brand", null);
        instance.adicionar(v1);

        // Act
        LinkedList<Veiculo> result = instance.getListagem(v -> v.getMarca().equals("Brand"));
        
        // Assert
        assertEquals(v1, result.getFirst());
    }

}
