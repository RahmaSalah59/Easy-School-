package com.example.demo.service;

import com.example.demo.Controller.ContactController;
import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@Service
public class ContactService {
//    private static Logger log = LoggerFactory.getLogger(ContactController.class);
     @Autowired
     private ContactRepository contactRepository ;
    public boolean contact_save_msg(Contact contact){
     boolean is_saved = false;
//     log.info(contact.toString());
        contact.setStatus("OPEN");
        contact.setCreatedBy("Rahoma");
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            is_saved = true;
        }
        return is_saved;
    }

}
