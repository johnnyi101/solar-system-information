package solar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        String expectedOutInfo = "SSun27TL,Planet,Earth,23,23,23,23";
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,23,23,23").times(2);
        replay(mockAstroService);
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        //act
        //assert
        assertThrows(InvalidFormatException.class, () -> {
            cut.initialiseAOCDetails(inputAOC);
        });
    }
    @Test
    public void error_thrown_when_info_returned_in_wrong_format() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "SSun27TL";
        String expectedOutAOC = "SSun27TL";
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,23,23,23").times(2);
        replay(mockAstroService);
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        cut.initialiseAOCDetails(inputAOC);
        //act
        String resultAOC = cut.getAstronomicalObjectClassificationCode();
        //assert
        assertEquals(expectedOutAOC, resultAOC);
        assertThrows(InvalidFormatException.class, () -> {
            cut.initialiseAOCDetails(mockAstroService.getStatusInfo(inputAOC));
        });
    }
    @Test
    public void AOC_getter_validation_bad_format_gets_error() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String inputAOC = "s244gfdgf";
        String expectedOut = "Invalid Format";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
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
        String expected = "SSun27TL,Planet,Earth,23,23,23,23";
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,23,23,23");
        replay(mockAstroService);
        //act
        String actual = mockAstroService.getStatusInfo(inputAOC);
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void does_the_returned_string_info_split_properly_and_variables_are_set() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expectP1 = "SSun27TL";
        String expectP2 = "Earth";
        String expectP3 = "Planet";
        String expectP4 = "23";
        String expectP5 = "23";
        String expectP6 = "23";
        String expectP7 = "23";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Earth,Planet,23,23,23,23");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String returnedp1 = cut.getAOC();
        String returnedp2 = cut.getType();
        String returnedp3 = cut.getName();
        String returnedp4 = cut.getOrbital();
        String returnedp5 = cut.getRad();
        String returnedp6 = cut.getSma();
        String returnedp7 = cut.getMassS();
        //assert
        assertEquals(expectP1, returnedp1);
        assertEquals(expectP2, returnedp2);
        assertEquals(expectP3, returnedp3);
        assertEquals(expectP4, returnedp4);
        assertEquals(expectP5, returnedp5);
        assertEquals(expectP6, returnedp6);
        assertEquals(expectP7, returnedp7);
        verify(mockAstroService);
    }
    @Test
    public void object_type_set_correct() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,23,23,23");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.getObjectType();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void object_name_set_correct() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Earth";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,23,23,23");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.getObjectName();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void object_orbital_period_set_correct() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        int expected = 23;
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,25,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        int actual = cut.getOrbitalPeriod();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void object_radius_set_correct() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        BigDecimal expected = new BigDecimal(24.0);
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,25,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        BigDecimal actual = new BigDecimal(String.valueOf(cut.getRadius()));
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void object_sma_set_correct() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        BigDecimal expected = new BigDecimal("25.0");
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,25,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        BigDecimal actual = new BigDecimal(String.valueOf(cut.getSemiMajorAxis()));
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void object_mass_set_correct() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        BigDecimal expected = new BigDecimal("26.0");
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,25,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        BigDecimal actual = new BigDecimal(String.valueOf(cut.getMass()));
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void if_returned_info_does_not_through_errors_then_exists_is_true() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,25,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        boolean actual = cut.getExists();
        //assert
        assertTrue(actual);
        verify(mockAstroService);
    }
    @Test
    public void all_fields_set_correctly_and_exists_field_set_to_true() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String code = "SSun27TL";
        String type = "Planet";
        String name = "Earth";
        int op = 23;
        BigDecimal expectedRadius = new BigDecimal(24);
        BigDecimal expectedsma = new BigDecimal("25.0");
        BigDecimal expectedMass = new BigDecimal("26.0");
        boolean expectedExists = true;
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,25,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actualCode = cut.getAstronomicalObjectClassificationCode();
        String actualName = cut.getObjectName();
        String actualType = cut.getObjectType();
        int actualOp = cut.getOrbitalPeriod();
        BigDecimal actualRadius = new BigDecimal(String.valueOf(cut.getRadius()));
        BigDecimal actualSma = new BigDecimal(String.valueOf(cut.getSemiMajorAxis()));
        BigDecimal actualMass = new BigDecimal(String.valueOf(cut.getMass()));
        boolean actualExists = cut.getExists();
        //assert
        assertEquals(code, actualCode);
        assertEquals(type, actualType);
        assertEquals(name, actualName);
        assertEquals(op, actualOp);
        assertEquals(expectedRadius, actualRadius);
        assertEquals(expectedsma, actualSma);
        assertEquals(expectedMass, actualMass);
        assertEquals(expectedExists, actualExists);
        verify(mockAstroService);

        verify(mockAstroService);
    }
    @Test
    public void SMA_returned_with_correct_formatting() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        BigDecimal expected = new BigDecimal("6.12E+010");
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,61230000000,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        BigDecimal actual = new BigDecimal(String.valueOf(cut.getSemiMajorAxis()));
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void mass_returned_with_correct_formatting() throws InvalidFormatException {
        //arrange
        String inputAOC = "SSun27TL";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        BigDecimal expected = new BigDecimal("3.45e12");
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("SSun27TL,Planet,Earth,23,24,3450000000000,26");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        BigDecimal actual = new BigDecimal(String.valueOf(cut.getSemiMajorAxis()));
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void to_string_returned_in_correct_format() throws InvalidFormatException {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void all_fields_set_to_zero_and_exists_field_set_to_false_if_username_wrong() throws InvalidFormatException {
        //arrange
        String inputID = "AB123";
        String inputPassword = "abcD1234!@";
        String code = null;
        String type = "Not Allowed";
        String name = "Not Allowed";
        int op = 0;
        BigDecimal expectedRadius = new BigDecimal(0);
        BigDecimal expectedsma = new BigDecimal("0.00");
        BigDecimal expectedMass = new BigDecimal("0.00");
        boolean expectedExists = false;
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        //act
        String actualCode = cut.getAstronomicalObjectClassificationCode();
        String actualName = cut.getObjectName();
        String actualType = cut.getObjectType();
        int actualOp = cut.getOrbitalPeriod();
        BigDecimal actualRadius = new BigDecimal(String.valueOf(cut.getRadius()));
        BigDecimal actualSma = new BigDecimal(String.valueOf(cut.getSemiMajorAxis()));
        BigDecimal actualMass = new BigDecimal(String.valueOf(cut.getMass()));
        boolean actualExists = cut.getExists();
        //assert
        assertEquals(code, actualCode);
        assertEquals(type, actualType);
        assertEquals(name, actualName);
        assertEquals(op, actualOp);
        assertEquals(expectedRadius, actualRadius);
        assertEquals(expectedsma, actualSma);
        assertEquals(expectedMass, actualMass);
        assertEquals(expectedExists, actualExists);
    }
    @Test
    public void all_fields_set_to_zero_and_exists_field_set_to_false_if_password_wrong() throws InvalidFormatException {
        //arrange
        String inputID = "AB1234";
        String inputPassword = "23232332";
        String code = null;
        String type = "Not Allowed";
        String name = "Not Allowed";
        int op = 0;
        BigDecimal expectedRadius = new BigDecimal(0);
        BigDecimal expectedsma = new BigDecimal("0.00");
        BigDecimal expectedMass = new BigDecimal("0.00");
        boolean expectedExists = false;
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        //act
        String actualCode = cut.getAstronomicalObjectClassificationCode();
        String actualName = cut.getObjectName();
        String actualType = cut.getObjectType();
        int actualOp = cut.getOrbitalPeriod();
        BigDecimal actualRadius = new BigDecimal(String.valueOf(cut.getRadius()));
        BigDecimal actualSma = new BigDecimal(String.valueOf(cut.getSemiMajorAxis()));
        BigDecimal actualMass = new BigDecimal(String.valueOf(cut.getMass()));
        boolean actualExists = cut.getExists();
        //assert
        assertEquals(code, actualCode);
        assertEquals(type, actualType);
        assertEquals(name, actualName);
        assertEquals(op, actualOp);
        assertEquals(expectedRadius, actualRadius);
        assertEquals(expectedsma, actualSma);
        assertEquals(expectedMass, actualMass);
        assertEquals(expectedExists, actualExists);
    }
    @Test
    public void if_code_doesnt_exist_then_returned_non_existance_string_is_assigned_to_code_variable() throws InvalidFormatException {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "No such astronomical object classification code";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("No such astronomical object classification code");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.getAstronomicalObjectClassificationCode();
        //assert
        assertEquals(expected, actual );
        verify(mockAstroService);
    }
    @Test
    public void if_code_doesnt_exist_then_returned_non_existance_string_is_assigned_to_code_variable_and_exist_is_set_to_false() throws InvalidFormatException {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expectedCode = "No such astronomical object classification code";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("No such astronomical object classification code");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actualCode = cut.getAstronomicalObjectClassificationCode();
        boolean actualExists = cut.getExists();
        //assert
        assertEquals(expectedCode, actualCode);
        assertFalse(actualExists);
        verify(mockAstroService);
    }
    @Test
    public void if_code_doesnt_exist_then_returned_non_existance_string_is_assigned_to_code_variable_and_exist_is_set_to_false_and_all_else_is_0_or_null() throws InvalidFormatException {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expectedCode = "No such astronomical object classification code";
        String type = null;
        String name = null;
        int op = 0;
        BigDecimal expectedRadius = new BigDecimal(0);
        BigDecimal expectedsma = new BigDecimal("0.00");
        BigDecimal expectedMass = new BigDecimal("0.00");
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("No such astronomical object classification code");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actualCode = cut.getAstronomicalObjectClassificationCode();
        boolean actualExists = cut.getExists();
        String actualName = cut.getObjectName();
        String actualType = cut.getObjectType();
        int actualOp = cut.getOrbitalPeriod();
        BigDecimal actualRadius = new BigDecimal(String.valueOf(cut.getRadius()));
        BigDecimal actualSma = new BigDecimal(String.valueOf(cut.getSemiMajorAxis()));
        BigDecimal actualMass = new BigDecimal(String.valueOf(cut.getMass()));
        //assert
        assertEquals(expectedCode, actualCode);
        assertEquals(type, actualType);
        assertEquals(name, actualName);
        assertEquals(op, actualOp);
        assertEquals(expectedRadius, actualRadius);
        assertEquals(expectedsma, actualSma);
        assertEquals(expectedMass, actualMass);
        assertFalse(actualExists);
        verify(mockAstroService);
    }
    ///////////////////////////////////////////////////////////////////////////Different Outcome Tests///////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void test_diffferent_valued_entries_1() throws InvalidFormatException {
        //arrange
        String inputAOC = "MMoo384T";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Moon, Moon [MMoo384T] 3.84E+5 km, 2.34E+14 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("MMoo384T,Moon,Moon,237677767,2467676776,384000,234350000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    /*@Test
    public void test_diffferent_valued_entries_2()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void test_diffferent_valued_entries_3()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void test_diffferent_valued_entries_4()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void test_diffferent_valued_entries_5()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void test_diffferent_valued_entries_6()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void test_diffferent_valued_entries_7()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void test_diffferent_valued_entries_8()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }
    @Test
    public void test_diffferent_valued_entries_9()
    {
        //arrange
        String inputAOC = "PEar150M";
        String inputID = "AB1234";
        String inputPassword = "abcD1234!@";
        String expected = "Planet, Earth [PEar150M] 1.50E+5 km, 5.97E+24 kg";
        SolarSystemInformation cut = new SolarSystemInformation(inputID, inputPassword, mockAstroService);
        expect(mockAstroService.getStatusInfo(inputAOC)).andReturn("PEar150M,Planet,Earth,23,24,150000,5970000000000000000000000");
        replay(mockAstroService);
        //act
        cut.initialiseAOCDetails(inputAOC);
        String actual = cut.toString();
        //assert
        assertEquals(expected, actual);
        verify(mockAstroService);
    }*/
}
