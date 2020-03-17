package com.srit.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srit.user.modal.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long>{

	public UserDetails findById(long id);
}
