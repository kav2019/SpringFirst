package control.dao;

import control.models.People;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int COUNT_PEOPLE = 0;
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
            String sqlQueryCount ="select count(*) from person"; // запрос на количество записей в таблице
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryCount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                COUNT_PEOPLE = resultSet.getInt(1);
            }
            System.out.println(COUNT_PEOPLE);
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

    public List<People> index(){ //возврщает список всех
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

    public People show(int id){ //возврщает одного
//        return people.stream().filter(people -> people.getId()==id).findAny().orElse(null);
        String sqlQuery = "";
        People people = new People();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from person where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                people.setEmail(resultSet.getString("email"));
                people.setName(resultSet.getString("name"));
                people.setAge(resultSet.getInt("age"));
                people.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public void save(People newPeople){ //сохраняет нового
//        newPeople.setId(++COUNT_PEOPLE); версия со списком
//        people.add(newPeople);

//        try { версия до защиты от sql иньекций
//            Statement statement = connection.createStatement();
//            String SQL = String.format("insert into person values (%s, '%s', %s, '%s')",
//                    ++COUNT_PEOPLE,
//                    newPeople.getName(),
//                    newPeople.getAge(),
//                    newPeople.getEmail());
//            System.out.println(SQL);
//            statement.executeUpdate(SQL);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into person values (?, ?, ?, ?)");
            preparedStatement.setInt(1, ++COUNT_PEOPLE);
            preparedStatement.setString(2, newPeople.getName());
            preparedStatement.setInt(3, newPeople.getAge());
            preparedStatement.setString(4, newPeople.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveEdit(People editPeople, int id){ //сохраяет изменения
//        People peopleToUpdate = show(id); версия со списком
//        peopleToUpdate.setName(editPeople.getName());
//        peopleToUpdate.setAge(editPeople.getAge());
//        peopleToUpdate.setEmail(editPeople.getEmail());
////        people.set(id-1, editPeople);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update person set name=?, age=?, email=? where id=?");
            preparedStatement.setString(1, editPeople.getName());
            preparedStatement.setInt(2, editPeople.getAge());
            preparedStatement.setString(3, editPeople.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){ //удаляет
//        people.removeIf(p -> p.getId() == id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from person where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
