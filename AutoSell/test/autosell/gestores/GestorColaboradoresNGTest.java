package autosell.gestores;

import autosell.enumeracoes.TipoColaborador;
import autosell.modelos.Colaborador;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class GestorColaboradoresNGTest {

    GestorColaboradores instance;

    public GestorColaboradoresNGTest() {
        instance = GestorColaboradores.getInstance();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        for (Colaborador colaborador : instance.getListagem()) {
            instance.remover(colaborador);
        }
    }

    @Test
    public void testValidarCredenciaisShouldReturnNullWhenInvalidCredenciais() {
        // Arrange
        String email = "emai@email.com";
        String password = "123";
        Colaborador c1 = new Colaborador("A", email, null, password, TipoColaborador.ADMINISTRADOR);
        instance.adicionar(c1);

        // Act
        Colaborador result = instance.validarCredenciais("wrongMail@email.com", "wrongPass");

        // Assert
        assertNull(result);
    }

    @Test
    public void testValidarCredenciaisShouldReturnColaboradorWhenCredenciaisAreValid() {
        // Arrange
        String email = "emai@email.com";
        String password = "123";
        Colaborador c1 = new Colaborador("A", email, null, password, TipoColaborador.ADMINISTRADOR);
        instance.adicionar(c1);

        // Act
        Colaborador result = instance.validarCredenciais(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(c1, result);
    }

    @Test
    public void testIsEmailDuplicatedShoulReturnTrueWhenEmailIsDuplicated() {
        // Arrange
        String email = "email@email.com";
        Colaborador c1 = new Colaborador("A", email, null, "C", TipoColaborador.ADMINISTRADOR);
        instance.adicionar(c1);

        // Act 
        boolean result = instance.isEmailDuplicated(email);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsEmailDuplicatedShoulReturnFalseWhenEmailIsNotDuplicated() {
        // Arrange
        String email = "email@email.com";
        Colaborador c1 = new Colaborador("A", email, null, "C", TipoColaborador.ADMINISTRADOR);
        instance.adicionar(c1);

        // Act 
        boolean result = instance.isEmailDuplicated("AnotherEmail@email.com");

        // Assert
        assertTrue(!result);
    }

}
