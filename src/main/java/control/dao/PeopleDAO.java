package control.dao;

import control.models.People;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int COUNT_PEOPLE;
    private List<People> people;



    {
        people = new ArrayList<>();
        people.add(new People(++COUNT_PEOPLE, "Mike"));
        people.add(new People(++COUNT_PEOPLE, "Alex"));
        people.add(new People(++COUNT_PEOPLE, "Sara"));
        people.add(new People(++COUNT_PEOPLE, "Jasmine"));
    }

    public List<People> index(){
        return people;
    }

    public People show(int id){
        return people.stream().filter(people -> people.getId()==id).findAny().orElse(null);
    }

    public void save(People newPeople){
        newPeople.setId(++COUNT_PEOPLE);
        people.add(newPeople);
    }

    public void saveEdit(People editPeople, int id){
        people.set(id-1, editPeople);
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}