package com.example.demo.service;

import com.example.demo.Entity.Person;
import com.example.demo.Entity.Roles;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    RolesRepository rolesRepository;

    public boolean createNewUser(Person person){
        boolean is_saved = false;
        Roles role = rolesRepository.findByRoleName("STUDENT");
        person.setRoles(role);
        person =  personRepository.save(person);
        if(person != null && person.getPersonId()>0){
            is_saved = true;
        }
        return is_saved;
    }
}
