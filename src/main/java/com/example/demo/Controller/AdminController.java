package com.example.demo.Controller;

import com.example.demo.Entity.Courses;
import com.example.demo.Entity.EazyClass;

import com.example.demo.Entity.Person;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.EasyClassRepository;
import com.example.demo.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
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
    @Autowired
    private CoursesRepository coursesRepository;
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
    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses (Model model){
        ModelAndView modelAndView = new ModelAndView("courses_secure");
        List<Courses> coursesList = coursesRepository.findAll();
        modelAndView.addObject("courses", coursesList);
        modelAndView.addObject("course" , new Courses());

        return  modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse (Model model ,@ModelAttribute Courses course_ ){
        if(course_ == null){
            throw new NullPointerException();
        }
        coursesRepository.save(course_);
        return new ModelAndView("redirect:/admin/displayCourses");

    }

    @RequestMapping("/viewStudents")
    public ModelAndView viewStudents (Model model , @RequestParam("id") int course_id , HttpSession session,
                                      @RequestParam(value = "error", required = false) String error){
        ModelAndView modelAndView = new ModelAndView("course_students");
        Courses courses = coursesRepository.findById(course_id);
        session.setAttribute("course_id" , course_id);
        if(error != null) {
            error = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", error);
        }
        modelAndView.addObject("courses" ,courses);
        modelAndView.addObject("person" , new Person());
        return modelAndView;

    }

    @PostMapping("/addStudentToCourse")
    public  ModelAndView addStudentToCourse (Model model , @ModelAttribute("person") Person person,  HttpSession session){
        int course_id =(int) session.getAttribute("course_id");
        Courses courses = coursesRepository.findById(course_id);
        Person person1 = personRepository.findPersonByEmail(person.getEmail());

        if(person1==null || !(person1.getPersonId()>0)){
            return new ModelAndView("redirect:/admin/viewStudents?id="+course_id
                    +"&error=true");
        }
        person1.getCourses().add(courses);
        courses.getPersons().add(person1);

        personRepository.save(person1);
        session.setAttribute("courses",courses);
        return new ModelAndView("redirect:/admin/viewStudents?id="+course_id);
    }

    @RequestMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse (Model model , @RequestParam("personId") int personId , HttpSession session ){
        int course_id =(int) session.getAttribute("course_id");
        Courses courses = coursesRepository.findById(course_id);
        Optional<Person> person1 = personRepository.findById(personId);

        courses.getPersons().remove(person1);
        person1.get().getCourses().remove(courses);
        personRepository.save(person1.get());
        return new ModelAndView("redirect:/admin/viewStudents?id="+course_id);

    }

}
