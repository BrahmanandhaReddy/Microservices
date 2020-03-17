package org.rhis.app.repo;

import org.rhis.app.modal.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserDetail, Integer>{

}
