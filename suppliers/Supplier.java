package suppliers;

public class Supplier {
    private String supplierId;
    private String name;
    private String contact;
    private String address;

    public Supplier(String supplierId, String name, String contact, String address) {
        this.supplierId = supplierId;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }
    // getters and setters

    public String getName() {
        return name;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public String getContact() {
        return contact;
    }
    public String getAddress() {
        return address;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
