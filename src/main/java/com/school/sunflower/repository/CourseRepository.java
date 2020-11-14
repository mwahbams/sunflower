package com.school.sunflower.repository;


import com.school.sunflower.model.entity.Class;
import com.school.sunflower.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course>, JpaSpecificationExecutor<Course> {

    @Query("select course from Course course inner join course.classes")
    Page<Course> findAllCoursesMappedClasses(PageRequest pageRequest);
}
