package com.example.demo.api;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jrojas
 */
@RestController
@RequestMapping("/v1/api")
public class StudentController {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private StudentRepository studentRepository;

    @Value("${key}")
    String key;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudents() {
        LOGGER.info("Invocando al servicio REST para obtener el listado de estudiantes con KEY: {}", key);
        List<Student> studentList = studentRepository.findAll();
        LOGGER.info("Invocacion exitosa para obtener el listado de estudiantes {}", studentList);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/students/v2", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudentsv2() {
        LOGGER.info("Invocando al servicio REST para obtener el listado de estudiantes con KEY: {}", key);
        List<Student> studentList = studentRepository.getStudents();
        LOGGER.info("Invocacion exitosa para obtener el listado de estudiantes {}", studentList);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/students/v3", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudentsv3() {
        LOGGER.info("Invocando al servicio REST para obtener el listado de estudiantes con KEY: {}", key);
        List<Student> studentList = studentRepository.getStudentsJPA();
        LOGGER.info("Invocacion exitosa para obtener el listado de estudiantes {}", studentList);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentsv3(@PathVariable Long id) {
        LOGGER.info("Invocando al servicio REST para obtener el estudiante con id: {}", id);
        Student student = studentRepository.getStudentById(id);
        LOGGER.info("Invocacion exitosa para obtener el estudiantes {}", student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @RequestMapping(value= "/students", method = RequestMethod.POST)
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        LOGGER.info("Invocando al servicio REST para registrar un estudiante con la siguiente información: {}", student);
        Student result = studentRepository.save(student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
