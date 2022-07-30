package control.dao;

import control.models.People;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int COUNT_PEOPLE = 4;
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db?currentSchema=public"; //?currentSchema=public
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




//    private List<People> people;
//    {
//        people = new ArrayList<>();
//        people.add(new People(++COUNT_PEOPLE, "Mike", 30, "a@mail.ru"));
//        people.add(new People(++COUNT_PEOPLE, "Alex", 2, "b@mail.ru"));
//        people.add(new People(++COUNT_PEOPLE, "Sara", 10, "c@mail.ru"));
//        people.add(new People(++COUNT_PEOPLE, "Jasmine", 34, "d@mail.ru"));
//    }

    public List<People> index(){
//        return people;
        List<People> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                People person = new People();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public People show(int id){
//        return people.stream().filter(people -> people.getId()==id).findAny().orElse(null);
        return null;
    }

    public void save(People newPeople){
//        newPeople.setId(++COUNT_PEOPLE);
//        people.add(newPeople);
        try {
            Statement statement = connection.createStatement();
            String SQL = String.format("insert into person values (%s, '%s', %s, '%s')",
                    ++COUNT_PEOPLE,
                    newPeople.getName(),
                    newPeople.getAge(),
                    newPeople.getEmail());
            System.out.println(SQL);
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveEdit(People editPeople, int id){
        People peopleToUpdate = show(id);
        peopleToUpdate.setName(editPeople.getName());
        peopleToUpdate.setAge(editPeople.getAge());
        peopleToUpdate.setEmail(editPeople.getEmail());
//        people.set(id-1, editPeople);
    }

    public void delete(int id){
//        people.removeIf(p -> p.getId() == id);
    }
}
