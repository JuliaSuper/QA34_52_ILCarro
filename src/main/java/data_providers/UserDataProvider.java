package data_providers;

import dto.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {
    @DataProvider
    public Iterator<User> dataProvider() {
        List<User> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources" +
                        "/Wrong_registration_password.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] sprintLine = line.split(",");
                list.add(User.builder()
                        .username(sprintLine[0])
                        .password(sprintLine[1])
                        .firstName(sprintLine[2])
                        .lastName(sprintLine[3])
                        .build());
                line = bufferedReader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.iterator();
    }
}
