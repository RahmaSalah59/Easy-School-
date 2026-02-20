package com.example.demo.Controller;

import com.example.demo.Entity.EazyClass;

import com.example.demo.Entity.Person;
import com.example.demo.repository.EasyClassRepository;
import com.example.demo.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private  EasyClassRepository easyClassRepository;
    @Autowired
    private  PersonRepository personRepository;
    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses (Model model){
        List<EazyClass>  classes = easyClassRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("classes",classes);
        modelAndView.addObject("eazyClass", new EazyClass());
        return  modelAndView;
    }

    @RequestMapping(value = "/addNewClass",method = RequestMethod.POST)
    public ModelAndView addNewClass(Model model , @ModelAttribute("eazyClass") EazyClass eazyClass){
        easyClassRepository.save(eazyClass);
        return new ModelAndView("redirect:/admin/displayClasses");

    }
    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model , @RequestParam("ID") int ID){
        EazyClass eazyClass = easyClassRepository.findById(ID);
        easyClassRepository.delete(eazyClass);
        return new ModelAndView("redirect:/admin/displayClasses");
    }
    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents (Model model , @RequestParam("ID") int ID, HttpSession session,
                                         @RequestParam(value = "error", required = false) String error)
    {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        EazyClass eazyClass = easyClassRepository.findById(ID);
        session.setAttribute("eazyClass",eazyClass);
        modelAndView.addObject("person", new Person());
        List<Person> people = eazyClass.getPerson_list();
        modelAndView.addObject("eazyClass",eazyClass);
        modelAndView.addObject("persons",people);
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ModelAndView addStudent (Model model, @ModelAttribute("person") @NotNull Person person , @NotNull HttpSession session){
        Person person1 = personRepository.findPersonByEmail(person.getEmail());
        EazyClass eazyClass =(EazyClass) session.getAttribute("eazyClass");

        if(person1==null || !(person1.getPersonId()>0)){
            return new ModelAndView("redirect:/admin/displayStudents?ID="+eazyClass.getID()
                    +"&error=true");
        }
        person1.setEazyClass(eazyClass);
        personRepository.save(person1);

        return new ModelAndView("redirect:/admin/displayStudents?ID="+eazyClass.getID());
    }





    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setEazyClass(null);
        eazyClass.getPerson_list().remove(person.get());
        EazyClass eazyClassSaved = easyClassRepository.save(eazyClass);
        session.setAttribute("eazyClass",eazyClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?ID="+eazyClass.getID());
        return modelAndView;
    }

}
