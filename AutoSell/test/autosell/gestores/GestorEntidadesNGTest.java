package autosell.gestores;

import autosell.enumeracoes.TipoEntidade;
import autosell.modelos.Entidade;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class GestorEntidadesNGTest {

    GestorEntidades instance;

    public GestorEntidadesNGTest() {
        instance = GestorEntidades.getInstance();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        for (Entidade entidade : instance.getListagem()) {
            instance.remover(entidade);
        }
    }

    @Test
    public void testIsNifDuplicatedShouldReturnTrueWhenNifIsDuplicated() {
        // Arrange
        String nif = "123456789";
        Entidade e1 = new Entidade("A", nif, "C", TipoEntidade.CLIENTE);
        instance.adicionar(e1);

        // Act
        boolean result = instance.isNifDuplicated(nif);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsNifDuplicatedShouldReturnFalseWhenNifIsNotDuplicated() {
        // Arrange
        String nif = "123456789";
        Entidade e1 = new Entidade("A", nif, "C", TipoEntidade.CLIENTE);
        instance.adicionar(e1);

        // Act
        boolean result = instance.isNifDuplicated("321654987");

        // Assert
        assertTrue(!result);
    }

}
