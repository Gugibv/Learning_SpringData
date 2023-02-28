package com.grey.repository;

import com.grey.entity.converter.MoneyBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyBeanRepository extends JpaRepository<MoneyBean,String> {
}
