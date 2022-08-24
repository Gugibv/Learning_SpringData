package com.tuling.repositories;

import com.tuling.pojo.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CustomerSpecificationsRepository
        extends PagingAndSortingRepository<Customer,Long>,
        JpaSpecificationExecutor<Customer> {

}
