package org.example.dao;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static final String URL = "jdbc:mysql://localhost:3306/giraffe";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kbk?21:jdbc.nedrag";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM mvc_person";
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                Person person = new Person();
                person.setId(set.getInt("id"));
                person.setName(set.getString("first_name"));
                person.setAge(set.getInt("age"));
                person.setEmail(set.getString("email"));

                people.add(person);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }
    public Person show(int id) {
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mvc_person WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            person = new Person();
            person.setId(set.getInt("id"));
            person.setName(set.getString("first_name"));
            person.setAge(set.getInt("age"));
            person.setEmail(set.getString("email"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    public void save(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mvc_person (first_name,age,email) VALUES(?,?,?)");
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE mvc_person SET first_name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1,updatedPerson.getName());
            preparedStatement.setInt(2,updatedPerson.getAge());
            preparedStatement.setString(3,updatedPerson.getEmail());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM mvc_person WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
