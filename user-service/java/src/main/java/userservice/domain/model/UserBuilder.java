package userservice.domain.model;

public class UserBuilder {
    private long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String country;
    private boolean enabled;

    public User build() {
        final User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setStreetName(streetName);
        user.setStreetNumber(streetNumber);
        user.setPostalCode(postalCode);
        user.setCity(city);
        user.setCountry(country);
        user.setEnabled(enabled);
        return user;
    }

    public UserBuilder withId(final long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withUsername(final String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(final String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEmail(final String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withPhone(final String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder withEnabled(final boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder withAddress(final String streetName, final String streetNumber, final String postCode, final String city, final String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postCode;
        this.city = city;
        this.country = country;
        return this;
    }
}
