package userservice.infrastructure.persistence;

import java.util.List;

import com.google.common.collect.Lists;

import userservice.domain.model.User;
import userservice.domain.model.UserBuilder;

public class UserData {

    public static List<User> getUsers() {
        final UserBuilder builder = new UserBuilder();
        return Lists.newArrayList(
                builder.withId(1)
                        .withUsername("user1")
                        .withPassword("[PROTECTED]")
                        .withEmail("user1@brandwatch.com")
                        .withFirstName("first name 1")
                        .withLastName("last name 1")
                        .withPhone("1234567890")
                        .withEnabled(true)
                        .withAddress("streetName1", "streetNumber1", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(2)
                        .withUsername("user2")
                        .withPassword("[PROTECTED]")
                        .withEmail("user2@brandwatch.com")
                        .withFirstName("first name 2")
                        .withLastName("last name 2")
                        .withPhone("1234567890")
                        .withEnabled(true)
                        .withAddress("streetName2", "streetNumber2", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(3)
                        .withUsername("user3")
                        .withPassword("[PROTECTED]")
                        .withEmail("user3@brandwatch.com")
                        .withFirstName("first name 3")
                        .withLastName("last name 3")
                        .withPhone("1234567890")
                        .withEnabled(false)
                        .withAddress("streetName3", "streetNumber3", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(4)
                        .withUsername("user4")
                        .withPassword("[PROTECTED]")
                        .withEmail("user4@brandwatch.com")
                        .withFirstName("first name 4")
                        .withLastName("last name 4")
                        .withPhone("1234567890")
                        .withEnabled(true)
                        .withAddress("streetName4", "streetNumber4", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(5)
                        .withUsername("user5")
                        .withPassword("[PROTECTED]")
                        .withEmail("user5@brandwatch.com")
                        .withFirstName("first name 5")
                        .withLastName("last name 5")
                        .withPhone("1234567890")
                        .withEnabled(true)
                        .withAddress("streetName5", "streetNumber5", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(6)
                        .withUsername("user6")
                        .withPassword("[PROTECTED]")
                        .withEmail("user6@brandwatch.com")
                        .withFirstName("first name 6")
                        .withLastName("last name 6")
                        .withPhone("1234567890")
                        .withEnabled(true)
                        .withAddress("streetName6", "streetNumber6", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(7)
                        .withUsername("user7")
                        .withPassword("[PROTECTED]")
                        .withEmail("user7@brandwatch.com")
                        .withFirstName("first name 7")
                        .withLastName("last name 7")
                        .withPhone("1234567890")
                        .withEnabled(true)
                        .withAddress("streetName7", "streetNumber7", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(8)
                        .withUsername("user8")
                        .withPassword("[PROTECTED]")
                        .withEmail("user8@brandwatch.com")
                        .withFirstName("first name 8")
                        .withLastName("last name 8")
                        .withPhone("1234567890")
                        .withEnabled(true)
                        .withAddress("streetName8", "streetNumber8", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(9)
                        .withUsername("user9")
                        .withPassword("[PROTECTED]")
                        .withEmail("user9@brandwatch.com")
                        .withFirstName("first name 9")
                        .withLastName("last name 9")
                        .withPhone("1234567890")
                        .withEnabled(false)
                        .withAddress("streetName9", "streetNumber9", "123242", "Vienna", "Austria")
                        .build(),
                builder.withId(10)
                        .withUsername("user10")
                        .withPassword("[PROTECTED]")
                        .withEmail("user10@brandwatch.com")
                        .withFirstName("first name 10")
                        .withLastName("last name 10")
                        .withPhone("1234567890")
                        .withEnabled(false)
                        .withAddress("streetName10", "streetNumber10", "123242", "Vienna", "Austria")
                        .build()
        );
    }
}
