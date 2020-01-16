package solar;

import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.easymock.EasyMock;

public class SolarSystemInformationTest {
    IAstroService mockAstroService;


    ///////////////////////////////////Set-Up////////////////////////////////////////////////////////////////////////////
    @BeforeEach
    void setUp() {
        mockAstroService = createMock(IAstroService.class);
    }



    ///////////////////////////////////User ID and password validation tests///////////////////////////////////////////////
    @Test
    public void a_valid_user_idlength_is_accepted() {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedObjectType = "Valid";
        String expectedObjectName = "Valid";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
        //act
        String resultOT = cut.getObjectType();
        String resultON = cut.getObjectName();
        //assert
        assertEquals(expectedObjectName, resultON);
        assertEquals(expectedObjectType, resultOT);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void AOC_getter_validation_returns_info_when_code_format_good() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedOutAOC = "SSun27TL";
        String expectedOutInfo = "SSun27TL,Earth,23,23,23,23";
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Earth,23,23,23,23").times(2);
        replay(mockAstroService);
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
        cut.initialiseAOCDetails(inputAOC);
        //act
        String resultAOC = cut.getAstronomicalObjectClassificationCode();
        String resultInfo = mockAstroService.getStatusInfo(inputAOC);
        //assert
        assertEquals(expectedOutAOC, resultAOC);
        assertEquals(expectedOutInfo, resultInfo);


    }
    @Test
    public void AOC_getter_validation_doesnt_return_info_when_code_format_bad() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "Ssun27TL";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
        //act
        //assert
        assertThrows(InvalidFormatException.class, () -> {
            cut.initialiseAOCDetails(inputAOC);});



    }
    @Test
    public void error_thrown_when_info_returned_in_wrong_format() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedOutAOC = "SSun27TL";
        String expectedOutInfo = "SSun27TL,earth,23,23,23,23";
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Earth,23,23,23,23").times(2);
        replay(mockAstroService);
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
        cut.initialiseAOCDetails(inputAOC);
        //act
        String resultAOC = cut.getAstronomicalObjectClassificationCode();
        //assert
        assertEquals(expectedOutAOC, resultAOC);
        assertThrows(InvalidFormatException.class, () -> {
            ;});
    }

    @Test
    public void AOC_getter_validation_bad_format_gets_error() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "s244gfdgf";
        String expectedOut = "Invalid Format";

        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
        //act
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            cut.initialiseAOCDetails(inputAOC);
        });
        String actualOut = exception.getMessage();
        //assert
        assertEquals(expectedOut, actualOut);


    }

    @Test
    public void boolean_true_returned_if_credentials_are_correct() {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        expect(mockAstroService.authenticate(inputID, inputPassword)).andReturn(true);
        replay(mockAstroService);
        //act
        boolean actual = mockAstroService.authenticate(inputID, inputPassword);
        //assert
        assertTrue(actual);
        verify(mockAstroService);

    }

    @Test
    public void boolean_false_returned_if_credentials_are_wrong() {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        expect(mockAstroService.authenticate(inputID, inputPassword)).andReturn(false);
        replay(mockAstroService);
        //act
        boolean actual = mockAstroService.authenticate(inputID, inputPassword);
        //assert
        assertFalse(actual);
        verify(mockAstroService);

    }

    @Test
    public void string_returned_from_WS_when_auth_is_true() {
        //arrange
        String inputAOC = "SSun27TL";
        String expected = "SSun27TL,Earth,23,23,23,23";
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Earth,23,23,23,23");
        replay(mockAstroService);
        //act
        String actual = mockAstroService.getStatusInfo(inputAOC);
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);

    }

    /*@Test
    public void AOC_initialiser_throws_acception_if_not_correct_format() throws InvalidFormatException
    {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun00TL";
        String expectedOut = "No such classification or SMA code";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword,mockAstroService);
        //act
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            cut.initialiseAOCDetails(inputAOC);
        });
        String actualOut = exception.getMessage();
        //assert
        assertEquals(expectedOut, actualOut);

    }




    @Test
    public void string_returned_separated_into_six_parts() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedp1 = "This";
        String expectedp2 = "is";
        String expectedp3 = "a";
        String expectedp4 = "test";
        String expectedp5 = "string";
        String expectedp6 = "thanks";

        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword);
        //act
        String actual1 = cut.initialiseAOCDetails(inputAOC).listOfValues[0];
        String actual2 = cut.initialiseAOCDetails(inputAOC).listOfValues[1];
        String actual3 = cut.initialiseAOCDetails(inputAOC).listOfValues[2];
        String actual4 = cut.initialiseAOCDetails(inputAOC).listOfValues[3];
        String actual5 = cut.initialiseAOCDetails(inputAOC).listOfValues[4];
        String actual6 = cut.initialiseAOCDetails(inputAOC).listOfValues[5];
        //assert
        assertEquals(expectedp1, actual1);
        assertEquals(expectedp2, actual2);
        assertEquals(expectedp3, actual3);
        assertEquals(expectedp4, actual4);
        assertEquals(expectedp5, actual5);
        assertEquals(expectedp6, actual6);
    }*/



}
