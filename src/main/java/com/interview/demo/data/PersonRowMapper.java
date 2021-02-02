package com.interview.demo.data;

import com.interview.demo.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        Person result = new Person();
        result.setFirstName(rs.getString("FIRST_NAME"));
        result.setEmail(rs.getString("EMAIL"));
        result.setLastName(rs.getString("LAST_NAME"));
        result.setJoinedDate(rs.getDate("JOINED_DATE"));

        return result;
    }
}
