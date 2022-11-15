package com.example.demo.student;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

  // Select* from student where email=whatever we pass
 // @Query("SELECT s from STUDENT s where s.email=?1")
  Optional<Student> findStudentByEmail(String email);
}
