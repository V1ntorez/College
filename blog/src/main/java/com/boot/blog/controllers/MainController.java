package com.boot.blog.controllers;

import com.boot.blog.DAO.LessonDAO;
import com.boot.blog.DAO.PersonDAO;
import com.boot.blog.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Controller
@RequestMapping("/college")
public class MainController {

    private PersonDAO personDAO;
    private final LessonDAO lessonDAO;


    @Autowired
    public MainController(PersonDAO personDAO, LessonDAO lessonDAO) {
        this.personDAO = personDAO;
        this.lessonDAO = lessonDAO;
    }

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("people", personDAO.peopleList());
        return "college/home";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model modelPerson, Model modelLesson, Model modelCurrentDay) {
        Person person = personDAO.personShow(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);

        modelCurrentDay.addAttribute("currentDay", dateFormat.format(new Date()));
        modelPerson.addAttribute("person", person);
        modelLesson.addAttribute("lessons", lessonDAO.lessonsList(person.getGroup()));

        return "college/person_office";
    }

    @GetMapping("new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "college/new";
    }

    @PostMapping("")
    public String createPerson(@ModelAttribute("person") Person newPerson) {
        personDAO.create(newPerson);
        return "redirect:/college";
    }

    @GetMapping("{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.personShow(id));
        return "college/edit";
    }


    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.update(person, id);
        return "redirect:/college";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/college";
    }


}
