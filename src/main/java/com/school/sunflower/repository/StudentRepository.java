package com.school.sunflower.repository;


import com.school.sunflower.model.entity.Class;
import com.school.sunflower.model.entity.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends BaseRepository<Student>, JpaSpecificationExecutor<Student> {

    Optional<Student> findByEmail(String email);
}
