package com.vijay.studentmanagementsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.vijay.studentmanagementsp.dao.StudentRepo;
import com.vijay.studentmanagementsp.model.Student;



@Controller
public class StudentController {
	@Autowired
	StudentRepo studentrepo;
	


    // Home page – list all students
    @GetMapping("/")
    public String viewStudents(Model model) {
        model.addAttribute("students", studentrepo.findAll());
        return "student";
    }
    // search student 
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("students", studentrepo.findAll());
        } else {
            model.addAttribute("students", studentrepo.findBySnameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword));
        }
        return "student";
    }



    //  Add new student
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentrepo.save(student);
        return "redirect:/";
    }

    // Load student for editing (when "Update" button is clicked)
//    @GetMapping("/edit/{id}")
//    public String editStudent(@PathVariable int id, Model model) {
//        Student student = studentrepo.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
//        model.addAttribute("student", student);
//        model.addAttribute("students", studentrepo.findAll()); // keep full list visible
//        return "editstudent"; // create this page OR make it inline (see below)
//    }

    // Update existing student
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute Student updatedStudent) {
        Student student = studentrepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));

        student.setSname(updatedStudent.getSname());
        student.setSMarks(updatedStudent.getSMarks());
        student.setEmail(updatedStudent.getEmail());

        studentrepo.save(student);
        return "redirect:/";
    }

    // Delete student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentrepo.deleteById(id);
        return "redirect:/";
    }
    

    
}
