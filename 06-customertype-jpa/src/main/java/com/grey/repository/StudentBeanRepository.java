package com.grey.repository;

import com.grey.entity.type.StudentBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentBeanRepository extends JpaRepository<StudentBean,String> {
}
