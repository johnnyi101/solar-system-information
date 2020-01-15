package TDDUtilitiesTests;
import TDDUtilities.IFileReader;
import TDDUtilities.OrderReader;
import static org.junit.Assert.assertTrue;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.Before;


public class OrderReaderTests {
    //MOCKS VS STUBS

    //Stubs and mocks may seem the same but the flow of information from each is very different.

    //Stubs provide input for the application under test so that the test can be performed on something else.
    //Mocks provide input to the test to decide on pass\fail. the opposite direction.
    //See graphic in Images Folder

    IFileReader strictMockFileReader;
    IFileReader mockFileReader; 
    IFileReader niceMockFileReader;
    OrderReader strictOrderReader;
    OrderReader orderReader;
    OrderReader niceOrderReader;

    
    @Before
    public void setUp() {
        //Simple (standard) Mock
        //With a simple mock, any methods/properties which are called by your tests for which you 
        //have not provided an implementation will return the default value for the data type of 
        //the return value.  In other words, you’ll get back a 0 for number types, false for Booleans 
        //and a null for any object types.
        mockFileReader = createMock(IFileReader.class);
        orderReader = new OrderReader(mockFileReader);

        //Strict Mock
        //A strict mock requires you to provide alternate implementations for each method/property 
        //that is used on the mock. If any methods/properties are used which you have not provided 
        //implementations for, an exception will be thrown.
        strictMockFileReader = EasyMock.createStrictMock(IFileReader.class);
        strictOrderReader = new OrderReader(strictMockFileReader);

        //Nice Mock
        //A nice mock expects recorded calls in any order and returns default empty values 
        //(0, null, false) for unexpected calls.        
        niceMockFileReader = EasyMock.createNiceMock(IFileReader.class);
        niceOrderReader = new OrderReader(niceMockFileReader);
    }
    
 
	@Test
	public void testUsingStandardMock() {
		//Arrange		
		mockFileReader.setFileName("record.csv");
		//EasyMock.expectLastCall().once();
		expect(mockFileReader.readLine()).andReturn("27412,ABC,876543.21").times(2);
		mockFileReader.close();
		//EasyMock.expectLastCall().once();
		replay(mockFileReader);

		//Act
        String s = orderReader.readFirstOrder();

        //Assert
        assertTrue("27412 ABC £876543.21".equals(s));
        verify(mockFileReader);
	}
	
	 
	@Test
	public void testUsingStrictMock() {
		//Arrange
		
		strictMockFileReader.setFileName("record.csv");
		EasyMock.expectLastCall().once();
		expect(strictMockFileReader.readLine()).andReturn("27412,ABC,876543.21").once();
		strictMockFileReader.close();
		EasyMock.expectLastCall().once();

		replay(strictMockFileReader);

		//Act
        String s = strictOrderReader.readFirstOrder();

        //Assert
        assertTrue("27412 ABC £876543.21".equals(s));
	}
	
	 
	@Test
	public void testUsingNiceMock() {
		//Arrange

		//expect(niceMockFileReader.readLine()).andReturn("27412,ABC,876543.21");
		expect(niceMockFileReader.readLine()).andReturn("27412,ABC,876543.21").once();
		niceMockFileReader.close();
		EasyMock.expectLastCall().once();
		niceMockFileReader.setFileName("record.csv");
		EasyMock.expectLastCall().once();
		replay(niceMockFileReader);

		//Act
       String s = niceOrderReader.readFirstOrder();

       //Assert
       assertTrue("27412 ABC £876543.21".equals(s));
       //verify(niceMockFileReader);
	}

}
