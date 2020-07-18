public class Customer implements Comparable<Customer> {
	
	//initialize common variables
    private int customerUserId;
    private String customerName;
    private double latitudeVal;
    private double longitudeVal;

    //constructor
    public Customer(int customerUserId, String customerName, double latitudeVal, double longitudeVal) {
        this.customerUserId = customerUserId;
        this.customerName = customerName;
        this.latitudeVal = latitudeVal;
        this.longitudeVal = longitudeVal;
    }
    
    //getters and setters
    public int getCustomerUserId() {
		return customerUserId;
	}


	public void setCustomerUserId(int customerUserId) {
		this.customerUserId = customerUserId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public double getLatitudeVal() {
		return latitudeVal;
	}


	public void setLatitudeVal(double latitudeVal) {
		this.latitudeVal = latitudeVal;
	}


	public double getLongitudeVal() {
		return longitudeVal;
	}


	public void setLongitudeVal(double longitudeVal) {
		this.longitudeVal = longitudeVal;
	}


	public int compareTo(Customer c) {
        if (this.getCustomerUserId() > c.getCustomerUserId()) {
            return 1;
        } else if (this.getCustomerUserId() < c.getLatitudeVal()) {
            return -1;
        } else {
            return 0;
        }
    }

}