package com.school.sunflower.repository;


import com.school.sunflower.model.entity.Course;
import com.school.sunflower.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends BaseRepository<Teacher>, JpaSpecificationExecutor<Teacher> {
    
}
