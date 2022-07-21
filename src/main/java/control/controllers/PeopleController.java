package control.controllers;



import control.dao.PeopleDAO;
import control.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PeopleDAO peopleDAO;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping() //Получим всех людей из списка
    public String index(Model model){
        model.addAttribute("peoples", peopleDAO.index());
        return "people/all_people";
    }

    @GetMapping("/{id}") //Получим конкретного из списка
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("people", peopleDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new") //создаем навого человека
    public String newPerson(@ModelAttribute("people") People people){
//        model.addAttribute("people", new People());
        return "people/new";
    }

    @PostMapping() //отправка формы и редирект на список всех
    public String create(@ModelAttribute("people") People people){
        peopleDAO.save(people);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")//страница изменения человека
    public String edit(Model model, @PathVariable("id") int id){ //@ModelAttribute("people") People people,
        model.addAttribute("people", peopleDAO.show(id));
        return "people/edit";
    }


    @PatchMapping("/{id}") // полуает измененного человека и сохраняет его
    public String saveEdit(@PathVariable("id") int id, @ModelAttribute("people") People people){
        peopleDAO.saveEdit(people, id);
        return "redirect:/people";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleDAO.delete(id);
        return "redirect:/people";
    }



}
