import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.List;
import org.junit.Test;

public class DesignerTests {
    private static final String filePath = "UnitTestCases/";
    private static final double minDistance = 100.0;
    private static final double dublinOfficeLat = 53.339428;
    private static final double dubOfficeLong = -6.257664;

    /*
     * If a JSON file with broken content is given, this test case should return an
     * InputFormatException
     */
    @Test(expected = InputFormatException.class)
    public void testFaultyFile() throws Exception {
        new Designer(new File(filePath + "customersFaultyFile.json"), minDistance, dublinOfficeLat, dubOfficeLong);
    }
    
    /*
     * If a file is given a bad name a "FileNotFoundException" should be thrown by the class
     */
    @Test(expected = java.io.FileNotFoundException.class)
    public void testIncorrectFileName() throws Exception {
        new Designer(new File("test.json"), minDistance, dublinOfficeLat, dubOfficeLong);
    }

    /*
     * Inside the file there's one record containing a location within 100km
     * from the dublin office this location will be contained in the invited
     * customers list
     */
    @Test
    public void testRange() throws Exception {
    	Designer designer = new Designer(new File(filePath + "customersRange.json"), minDistance, dublinOfficeLat,
    			dubOfficeLong);
        List<Customer> cust = designer.getListOfInvitedCustomers();

        assertEquals(cust.get(0).getCustomerUserId(), 1);
    }
    
    /*
     * If the file is empty, an exception is thrown
     */
    @Test
    public void testEmptyContentInFile() throws Exception {
    	Designer designer = new Designer(new File(filePath + "customersEmptyContent.json"), minDistance,
    			dublinOfficeLat, dubOfficeLong);
        List<Customer> customers = designer.getListOfInvitedCustomers();

        assertEquals(customers.size(), 0);
    }

    /*
     * The file contains a customer located outside of the 100km range thus the
     * list of customers will be empty
     */
    @Test
    public void testOutOfRange() throws Exception {
    	Designer designer = new Designer(new File(filePath + "customersOutOfRange.json"), minDistance,
    			dublinOfficeLat, dubOfficeLong);
        List<Customer> customers = designer.getListOfInvitedCustomers();

        assertEquals(customers.size(), 0);
    }
    
    /*
     * The customers file contains two records with user id in descending order
     * both within 100km of distance from the office. The Algorithm should
     * return a list with the two customers in ascending order
     */
    @Test
    public void testListInAscendingOrder() throws Exception {
        int[] perUserID = { 2, 34 };
        int indexArray = 0;

        Designer designer = new Designer(new File(filePath + "customersAscOrder.json"), minDistance,
        		dublinOfficeLat, dubOfficeLong);
        List<Customer> listOfCust = designer.getListOfInvitedCustomers();
        for (Customer cust : listOfCust) {
            assertEquals(cust.getCustomerUserId(), perUserID[indexArray++]);
        }
    }

    /*
     * This test case will test a location which has the location 
     * with coordinates equal to the Dublin office location
     */
    @Test
    public void testOfficeCustomers() throws Exception {
        Designer designer = new Designer(new File(filePath + "customersOffice.json"), minDistance,
        		dublinOfficeLat, dubOfficeLong);
        List<Customer> cust = designer.getListOfInvitedCustomers();

        assertEquals(cust.get(0).getCustomerUserId(), 1);
    }

}