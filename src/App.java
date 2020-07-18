import java.io.File;
import java.io.FileNotFoundException;

public class App {

    private static final String CUSTOMER_FILE_NAME = "customers.json";

    public static void main(String[] args) {
    	
        try {
            Designer planner = new Designer(new File(CUSTOMER_FILE_NAME), 100.0, 53.339428, -6.257664);
            for (Customer customer : planner.getListOfInvitedCustomers()) {
                System.out.println(customer.getCustomerUserId() + "\t" + customer.getCustomerName());
            }
        } catch (InputFormatException e) {
            System.err.println("Error! Cannot read file content:\n\t" + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("A JSON file containing the customers must be provided!");
            System.exit(1);
        }

    }
}
