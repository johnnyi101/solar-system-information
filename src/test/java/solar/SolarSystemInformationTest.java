package solar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemInformationTest
{
    @Test
    public void user_id_has_to_correct_length_else_not_allowed()
    {
        //arrange
        String input = "XX999";
        String expectedObjectType = "Not Allowed";
        String expectedObjectName = "Not Allowed";
        SolarSystemInformation cut = new SolarSystemInformation(input);
        //act
        String resultOT =  cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }
    @Test
    public void user_id_first_two_letters_must_be_upper_case_alpha_characters()
    {
        //arrange
        String input = "1X999";
        String expectedObjectType = "Not Allowed";
        String expectedObjectName = "Not Allowed";
        SolarSystemInformation cut = new SolarSystemInformation(input);
        //act
        String resultOT =  cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }
    @Test
    public void user_id_final_four_are_digits_if_not_error()
    {
        //arrange
        String input = "1X999";
        String expectedObjectType = "Not Allowed";
        String expectedObjectName = "Not Allowed";
        SolarSystemInformation cut = new SolarSystemInformation(input);
        //act
        String resultOT =  cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);

    }


}
