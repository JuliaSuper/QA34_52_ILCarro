package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {
  private static Faker faker = new Faker();

    public static User positiveRegistrazioneUser() {
        User user = User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.internet().emailAddress())
                .password("Walid$Pass!")
                .build();
        return user;
    }

    public static User positiveUser() { //было написанно для ДЗ_4
        return User.builder()
                .username(faker.internet().emailAddress())
                .password("123DFjk$")
                .build();
    }
    public static User userWithInvalidEmail() {
        return User.builder()
                .username(faker.credentials().username() + "gmail.com")
                .password("123DFjk$")
                .build();
    }

    public static User userWithEmptyPassword() {
        return User.builder()
                .username(faker.internet().emailAddress())
                .password("")
                .build();
    }

    public static User userWithInvalidPassword() {
        return User.builder()
                .username(faker.internet().emailAddress())
                .password("123456")
                .build();
    }
}


