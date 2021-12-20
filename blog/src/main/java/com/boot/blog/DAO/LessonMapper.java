package com.boot.blog.DAO;

import com.boot.blog.models.Lessons;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonMapper implements RowMapper<Lessons> {
    @Override
    public Lessons mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lessons lesson = new Lessons();
        lesson.setNum(rs.getInt("num"));
        lesson.setName(rs.getString("name"));
        lesson.setGroups(rs.getInt("groups"));
        lesson.setDayOfWeek(rs.getString("day"));
        return lesson;
    }
}
