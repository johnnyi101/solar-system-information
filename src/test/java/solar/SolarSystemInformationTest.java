package solar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemInformationTest {
    ///////////////////////////////////User ID and password validation tests///////////////////////////////////////////////
    @Test
    public void a_valid_user_idlength_is_accepted() {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Valid";
        String expectedObjectName = "Valid";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    @Test
    public void id_with_two_capital_letters_at_start_is_accepted() {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Valid";
        String expectedObjectName = "Valid";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    @Test
    public void id_with_four_numbers_at_end_is_accepted() {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Valid";
        String expectedObjectName = "Valid";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    @Test
    public void erroneous_entries_are_not_allowed() {
        //arrange
        String inputID = "1B12/4";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Not Allowed";
        String expectedObjectName = "Not Allowed";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    @Test
    public void user_ID_cannot_end_in_four_zeros() {
        //arrange
        String inputID = "AB0000";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Not Allowed";
        String expectedObjectName = "Not Allowed";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    @Test
    public void password_must_be_at_least_ten_characters_in_length() {
        //arrange
        String inputID = "ABCD1234!";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Not Allowed";
        String expectedObjectName = "Not Allowed";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    @Test
    public void password_must_have_one_upper_lower_special() {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Valid";
        String expectedObjectName = "Valid";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void AOC_getter_validation() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedOut = "SSun27TL";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        cut.setAstronomicalObjectClassificationCode(inputAOC);
        //act
        String result = cut.getAstronomicalObjectClassificationCode();
        //assert
        assertEquals(expectedOut, result);

    }

    @Test
    public void AOC_getter_validation_bad_format_gets_error() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "s244gfdgf";
        String expectedOut = "Invalid Format";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        cut.setAstronomicalObjectClassificationCode(inputAOC);
        //act
        Exception exception = assertThrows(InvalidFormatException.class, ()-> {cut.getAstronomicalObjectClassificationCode();});
        String actualOut = exception.getMessage();
        //assert
        assertEquals(expectedOut, actualOut);


    }
    @Test
    public void objectName_must_be_capitalised_if_not_error_is_thrown(){
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputObjectType = "Star";
        String expectedOut = "Invalid Format";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        cut.setObjectType(inputObjectType);
        //act
        Exception exception = assertThrows(InvalidFormatException.class, ()-> {cut.getAstronomicalObjectClassificationCode();});
        String actualOut = exception.getMessage();
        //assert
        assertEquals(expectedOut, actualOut);
    }
//////////////////////////////////////////////////Mocking Tests/////////////////////////////////////////////////////////
    @Test
    public void test_placeholder()
    {

    }
}
