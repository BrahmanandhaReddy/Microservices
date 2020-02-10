package com.srit.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srit.app.modal.OrderDetailsEntity;
import com.srit.app.modal.UserDetailsEntity;

@Repository
public interface USerDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {

	UserDetailsEntity save(OrderDetailsEntity userDetailsEntity);
}