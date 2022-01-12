package com.egorbahar.repository;

import com.egorbahar.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
