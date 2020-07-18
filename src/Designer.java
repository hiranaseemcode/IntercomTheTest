import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

public class Designer {
	
    private List<Customer> allCustomersList;

    private final double minDistanceVal;
    private final double officeLatitudeVal;
    private final double officeLongitudeVal;


    private static final double earthDegrees = 110.57;

    /**
     * Constructor of the class which gets in input the filename containing the
     * customers list in a JSON format.
     */
    public Designer(File filename, double minDistanceVal, double officeLatitudeVal, double officeLongitudeVal)
            throws FileNotFoundException, InputFormatException {
    	
        Scanner scanner = null;
        allCustomersList = new ArrayList<Customer>();
        this.minDistanceVal = minDistanceVal;
        this.officeLatitudeVal = officeLatitudeVal;
        this.officeLongitudeVal = officeLongitudeVal;

        try {
            scanner = new Scanner(filename);
            while (scanner.hasNextLine()) {
            	
                String value = scanner.nextLine();
                JSONObject object = new JSONObject(value);

                double tempLatitude = Double.parseDouble(object.getString("latitude"));
                double tempLongitude = Double.parseDouble(object.getString("longitude"));

                String tempUserName = object.getString("name");
                int tempUserId = object.getInt("user_id");

                allCustomersList.add(new Customer(tempUserId, tempUserName, tempLatitude, tempLongitude));
            }
        } catch (JSONException e) {
            throw new InputFormatException(e.getMessage());
        } finally {
            if (scanner != null)
            	scanner.close();
        }
    }

    /**
     * Method for calculating the great circle distance, given the
     * coordinates of the two locations. The coordinates must be in degrees
     */

    public static double getGreaterDist(double a1, double b1, double a2, double b2) {
        double dist = 0.0;
        double lat_x1Rad = Math.toRadians(a1);
        double long_x1Rad = Math.toRadians(b1);
        double lat_x2Rad = Math.toRadians(a2);
        double long_x2Rad = Math.toRadians(b2);

        double angle = Math.acos(Math.sin(lat_x1Rad) * Math.sin(lat_x2Rad)
                + Math.cos(lat_x1Rad) * Math.cos(lat_x2Rad) * Math.cos(long_x1Rad - long_x2Rad));

        // convert back to degrees
        angle = Math.toDegrees(angle);
        dist = (earthDegrees * angle);
        return dist;
    }

    /**
     * Method which checks for every customer in list, the ones within n
     * kilometers of distance from the office and returns them in a list of
     * customers.
     */

    public List<Customer> getListOfInvitedCustomers() {
       return allCustomersList.stream().sorted().filter( customer -> getGreaterDist(customer.getLatitudeVal(), customer.getLongitudeVal(), officeLatitudeVal,
        		officeLongitudeVal) <= minDistanceVal).collect(Collectors.toList());

    }
}