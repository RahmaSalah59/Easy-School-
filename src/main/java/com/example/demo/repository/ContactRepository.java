package com.example.demo.repository;

import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository (JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact){
        String sql = "INSERT INTO CONTACT_MSG (name,mobile_num,email,subject,message,status," +
                "created_at,created_by) VALUES (?,?,?,?,?,?,?,?)";

       return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),contact.getEmail(),contact.getSubject(),
                contact.getMessage(),contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
    }

}
