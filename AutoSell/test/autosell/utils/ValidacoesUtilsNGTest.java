package autosell.utils;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class ValidacoesUtilsNGTest {

    @Test
    public void testIsNullOrEmptyShouldReturnTrueWhenValueIsNull() {
        // Arrange
        String s = null;

        // Act
        boolean result = ValidacoesUtils.isNullOrEmpty(s);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsNullOrEmptyShouldReturnTrueWhenValueIsEmpty() {
        // Arrange
        String s = "";

        // Act
        boolean result = ValidacoesUtils.isNullOrEmpty(s);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsNullOrEmptyShouldReturnTrueWhenValueValid() {
        // Arrange
        String s = "Valid";

        // Act
        boolean result = ValidacoesUtils.isNullOrEmpty(s);

        // Assert
        assertTrue(!result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnFalseWhenJTextFieldIsEmpty() {
        // Arrange
        JTextField jt = new JTextField();

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, jt);

        // Arrange
        assertTrue(!result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnFalseWhenJComboBoxHasSelectedNullItem() {
        // Arrange
        JComboBox jc = new JComboBox();

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, jc);

        // Arrange
        assertTrue(!result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnFalseWhenJTextAreadIsEmpty() {
        // Arrange
        JTextArea ja = new JTextArea();

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, ja);

        // Arrange
        assertTrue(!result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnFalseWhenJPasswordFielddIsEmpty() {
        // Arrange
        JPasswordField jf = new JPasswordField();

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, jf);

        // Arrange
        assertTrue(!result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnTrueWhenJTextFieldIsNotEmpty() {
        // Arrange
        JTextField jt = new JTextField();
        jt.setText("Test");

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, jt);

        // Arrange
        assertTrue(result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnTrueWhenJComboBoxHasSelectedItem() {
        // Arrange
        JComboBox<String> jc = new JComboBox();
        jc.addItem("A");
        jc.addItem("B");
        jc.setSelectedIndex(0);

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, jc);

        // Arrange
        assertTrue(result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnTrueWhenJTextAreadIsNotEmpty() {
        // Arrange
        JTextArea ja = new JTextArea();
        ja.setText("A");

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, ja);

        // Arrange
        assertTrue(result);
    }

    @Test
    public void testIsComponenteVazioShouldReturnTrueWhenJPasswordFielddIsNotEmpty() {
        // Arrange
        JPasswordField jf = new JPasswordField();
        jf.setText("Test");

        // Act
        boolean result = ValidacoesUtils.isComponenteVazio(null, jf);

        // Arrange
        assertTrue(result);
    }

    @Test
    public void testIsNumericValueShouldReturnTrueWhenIsNumericValue() {
        // Arrange
        JTextField jf = new JTextField();
        jf.setText("123.4");

        // Act
        boolean result = ValidacoesUtils.isNumericValue(null, jf);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsNumericValueShouldReturnFalseWhenIsNotNumericValue() {
        // Arrange
        JTextField jf = new JTextField();
        jf.setText("ABC");

        // Act
        boolean result = ValidacoesUtils.isNumericValue(null, jf);

        // Assert
        assertTrue(!result);
    }

    @Test
    public void testIsNumberGreaterOrEqualThanZeroShouldReturnTrueWhenIsGreaterThanZero() {
        // Arrange
        JTextField jf = new JTextField();
        jf.setText("0.1");
        
        // Act
        boolean result = ValidacoesUtils.isNumberGreaterOrEqualThanZero(null, jf);
        
        // Assert
        assertTrue(result);
        
    }

    @Test
    public void testIsNumberGreaterOrEqualThanZeroShouldReturnTrueWhenIsEqualThanZero() {
        // Arrange
        JTextField jf = new JTextField();
        jf.setText("0");
        
        // Act
        boolean result = ValidacoesUtils.isNumberGreaterOrEqualThanZero(null, jf);
        
        // Assert
        assertTrue(result); 
    }
    
    @Test
    public void testIsNumberGreaterOrEqualThanZeroShouldReturnFalseWhenIsValueIsNotGreatedOrEqualThanZero() {
        // Arrange
        JTextField jf = new JTextField();
        jf.setText("-0.01");
        
        // Act
        boolean result = ValidacoesUtils.isNumberGreaterOrEqualThanZero(null, jf);
        
        // Assert
        assertTrue(!result); 
    }
    
   
    @Test
    public void testIsDateCorrectShouldReturnTrueWhenDateIsCorrect() {
        // Arrange
        JTextField jf = new JTextField();
        jf.setText("12/12/2012");
        
        // Act
        boolean result = ValidacoesUtils.isDateCorrect(null, jf);
        
        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsDateCorrectShouldReturnFalseWhenDateIsNotCorrect() {
        // Arrange
        JTextField jf = new JTextField();
        jf.setText("12/AA/2012");
        
        // Act
        boolean result = ValidacoesUtils.isDateCorrect(null, jf);
        
        // Assert
        assertTrue(!result);
    }
}
