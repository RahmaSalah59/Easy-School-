package com.example.demo.repository;

import com.example.demo.Entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository <Contact,Integer> {



}
