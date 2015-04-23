package be.ordina.threeSixty.common.model;

/**
 * Created by stevedezitter on 14/04/15.
 */
public class Address {

    private String street;
    private int streetNumber;
    private String bus;
    private String zipCode;
    private String city;
    private String province;
    private String country;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getBus() {
        return bus;
    }
    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (streetNumber != address.streetNumber) return false;
        if (!street.equals(address.street)) return false;
        if (!bus.equals(address.bus)) return false;
        if (!zipCode.equals(address.zipCode)) return false;
        if (!city.equals(address.city)) return false;
        if (!province.equals(address.province)) return false;
        return country.equals(address.country);

    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + streetNumber;
        result = 31 * result + bus.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", bus='" + bus + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
