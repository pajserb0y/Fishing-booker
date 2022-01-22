package com.springboot.app.repository;

import com.springboot.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByHashCode(String hashCode);
    Customer findByUsername(String username);

    @Query("SELECT c.username FROM Customer c")
    Collection<String> findAllUsernames();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Customer p where p.username = :username")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Customer findOneByUsername(@Param("username") String username);
}
