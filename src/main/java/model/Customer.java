package model;

public class Customer {
	private int id;
    private String name;
    private String companyName;
    private String contactName;
    private String contactPhone;
    private String customerLevel;

    // Constructors, getters, setters, and toString() methods
    public Customer(int id,String name, String companyName, String contactName, String contactPhone, String customerLevel) {
    	this.id = id;
    	this.name = name;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.customerLevel = customerLevel;
    }

    public Customer() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }
    public void setId(int id) {
    	this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", customerLevel='" + customerLevel + '\'' +
                '}';
    }
}