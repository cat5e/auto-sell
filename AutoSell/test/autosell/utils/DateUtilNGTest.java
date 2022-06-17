package autosell.utils;

import java.time.LocalDate;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class DateUtilNGTest {

    @Test
    public void testGetDateFormatedShouldReturnCorrectFormat() {
        // Arrange
        String dateText = "12/12/2012";
        LocalDate date = DateUtil.parseLocalDate(dateText);

        // Act 
        String result = DateUtil.getDateFormated(date);

        // Assert
        assertEquals(dateText, result);
    }

    @Test
    public void testParseLocalDateShouldReturnCorrectDateWhenDateIsValid() {
        // Arrange
        String dateText = "12/12/2012";

        // Act
        LocalDate result = DateUtil.parseLocalDate(dateText);

        // Assert
        assertNotEquals(LocalDate.MIN, result);
    }

    @Test
    public void testParseLocalDateShouldReturnMinPossibleLocaleDateWhenDateIsInvalid() {
        // Arrange
        String dateText = "12/A/2012";

        // Act
        LocalDate result = DateUtil.parseLocalDate(dateText);

        // Assert
        assertEquals(LocalDate.MIN, result);
    }

}
