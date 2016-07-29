package cz.gopay.api.v3.model.payment.support;


/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class PayerContact {

    private String first_name;

    private String last_name;

    private String email;

    private String phone_number;

    private String city;

    private String street;

    private String postal_code;

    private String country_code;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(String postalCode) {
        this.postal_code = postalCode;
    }

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String countryCode) {
        this.country_code = countryCode;
    }

    @Override
    public String toString() {
        return String.format(
                "PayerContact [firstName=%s, lastName=%s, email=%s, phone_number=%s, city=%s, street=%s, postalCode=%s, countryCode=%s]",
                first_name, last_name, email, phone_number, city, street, postal_code, country_code);
    }

    public static PayerContact build(String firstName, String lastName) {
        PayerContact result = new PayerContact();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        return result;
    }

    public PayerContact withContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phone_number = phoneNumber;
        return this;
    }

    public PayerContact onAddress(String city, String street, String postalCode, String countryCode) {
        this.city = city;
        this.street = street;
        this.postal_code = postalCode;
        this.country_code = countryCode;
        return this;
    }
}
