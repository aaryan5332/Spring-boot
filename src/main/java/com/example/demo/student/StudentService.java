package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentService {
      private static StudentRepository studentRepository;
@Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }



  public static void addNewStudent(Student student) {
    Optional<Student> studentByEmail = studentRepository.findStudentByEmail((student.getEmail()));
    if(studentByEmail.isPresent()){
      throw new IllegalStateException("email taken");
    }
    studentRepository.save(student);
  }

  public List<Student> getStudents(){
    return studentRepository.findAll();
//        new Student(
//            1L,
//            "Mariam",
//            "mariam.jamal@gmail.com",
//            LocalDate.of(2000, Month.JANUARY,05),
//            21
//        )

  }

  public void deleteStudent(Long StudentId) {
     boolean exists=studentRepository.existsById(StudentId);
     if(!exists){
       throw new IllegalStateException("student with id "+StudentId+"  does not exists");
     }
     studentRepository.deleteById(StudentId);
  }

  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
  Student student=studentRepository.findById(studentId)
      .orElseThrow(()->new IllegalStateException("student with id "+studentId+" does not exist"));

  if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
    student.setName(name);
  }
    if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
      Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
      if(studentOptional.isPresent()){
        throw new IllegalStateException("email taken");
      }
      student.setEmail(email);
    }
  }
}
