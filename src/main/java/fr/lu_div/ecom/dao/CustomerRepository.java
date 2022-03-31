package fr.lu_div.ecom.dao;

import fr.lu_div.ecom.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}