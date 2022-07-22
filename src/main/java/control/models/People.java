package control.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class People {
    private int id;

    @NotEmpty(message="Name should empty")
    @Size(min=2, max=30, message="Name should between 2 an 30")
    private String name;

    @Min(value=0, message = "Age should be greater 0")
    private int age;

    @NotEmpty(message="Email should empty")
    @Email(message = "Email should be valid")
    private String email;

    public People() {
    }



    public People(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
