package com.example.demo.service;

import com.example.demo.Entity.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Contact result = contactRepository.save(contact);
        if (result != null) {
            is_saved = true;
        }
        return is_saved;
    }

}
