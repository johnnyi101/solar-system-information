package solar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemInformationTest
{

    @Test
    public void a_valid_user_idlength_is_accepted()
    {
        //arrange
        String input = "AB1234";
        String expectedObjectType = "Valid";
        String expectedObjectName = "Valid";
        SolarSystemInformation cut = new SolarSystemInformation(input);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

}
