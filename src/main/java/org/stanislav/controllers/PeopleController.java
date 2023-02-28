package org.stanislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stanislav.dao.PersonDao;
import org.stanislav.models.Person;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping(value = "/people", produces = "text/plain;charset=UTF-8")
public class PeopleController {
    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("peopleList", personDao.readAllPersons());
        return "people/people";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/newPerson";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") Person person) {
        personDao.create(person);
        return "redirect:/people";
    }
}
