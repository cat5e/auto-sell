package autosell.Utils;

import java.awt.Component;
import java.util.function.ObjDoubleConsumer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ValidacoesUtils {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isComponenteVazio(Component componente, Object object) {
        String mensagemValidacao = "O campo '%s' é de preenchimento obrigatório!";

        if (object instanceof JTextField jTextField) {
            if (isNullOrEmpty(jTextField.getText())) {
                JOptionPane.showMessageDialog(componente, String.format(mensagemValidacao, jTextField.getName()),
                        "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        if (object instanceof JComboBox jComboBox) {
            if (jComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(componente, String.format(mensagemValidacao, jComboBox.getName()),
                        "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (object instanceof JPasswordField jpasswordField) {
            if (isNullOrEmpty(String.copyValueOf(jpasswordField.getPassword()))) {
                JOptionPane.showMessageDialog(componente, String.format(mensagemValidacao, jpasswordField.getName()),
                        "Dados inválidos", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        return true;
    }

    public static boolean isNumericValue(Component componente, JTextField jTextField) {
        String mensagemValidacao = "O valor do campo '%s' tem de ser do tipo numérico!";

        try {
            Double.parseDouble(jTextField.getText());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(componente, String.format(mensagemValidacao, jTextField.getName()),
                    "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public static boolean isNumberGreaterOrEqualThanZero(Component componente, JTextField jTextField) {
        String mensagemValidacao = "O valor do campo '%s' não pode ser negativo!";

        if (Double.parseDouble(jTextField.getText()) < 0) {
            JOptionPane.showMessageDialog(componente, String.format(mensagemValidacao, jTextField.getName()),
                    "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
