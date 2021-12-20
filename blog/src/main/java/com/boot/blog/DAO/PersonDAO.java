package com.boot.blog.DAO;

import com.boot.blog.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> peopleList(){
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person personShow(int id){
        return jdbcTemplate.query("Select * from person where id=?", new Object[]{id},new PersonMapper())
                .stream().findAny().orElse(null);
    }


    public void create(Person newPerson) {
        jdbcTemplate.update("INSERT INTO person (name,surname,groups) VALUES(?,?,?)",
                newPerson.getName(),newPerson.getSurName(),newPerson.getGroup());
    }


    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE person SET name=?,surname=?,groups=? WHERE id=?",
                person.getName(),person.getSurName(),person.getGroup(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person where id=?", id);
    }
}
