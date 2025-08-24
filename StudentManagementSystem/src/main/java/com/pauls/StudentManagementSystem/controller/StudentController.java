package com.pauls.StudentManagementSystem.controller;

import com.pauls.StudentManagementSystem.entity.Student;
import com.pauls.StudentManagementSystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Handler Method to handle list students and return model and view
    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "students";
    }


    @GetMapping("/students/new")
    public String createStudentForm(Model model){

        // Create Student object to hold data from Student Form
        Student student = new Student();
        model.addAttribute("student",student);
        return "create_student";
    }

    // Handler method to create form request
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) // Binding student data received from the form to student entity
    {
        // Saving the student object to the database
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // Handler Method to handle edit requests
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model)
    {
        // Get student from database using their id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // Save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    // Handler Method to handle delete student request
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
