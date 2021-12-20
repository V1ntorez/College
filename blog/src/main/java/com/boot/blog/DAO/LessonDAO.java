package com.boot.blog.DAO;

import com.boot.blog.models.Lessons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonDAO  {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Lessons> lessonsList(int group){
        return jdbcTemplate.query("SELECT * from lessons where groups=?", new Object[]{group} , new LessonMapper());
    }

    public Lessons lessonShow(int group){
        return jdbcTemplate.query("Select * from lessons where groups=?", new Object[]{group}, new LessonMapper())
                .stream().findAny().orElse(null);
    }


}
