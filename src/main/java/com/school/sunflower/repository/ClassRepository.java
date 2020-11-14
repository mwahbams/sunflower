package com.school.sunflower.repository;


import com.school.sunflower.model.entity.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends BaseRepository<Class>, JpaSpecificationExecutor<Class> {

    Page<Class> findAllByIsOpenTrue(Pageable pageable);
}
