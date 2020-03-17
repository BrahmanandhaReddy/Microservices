package org.srit.rhes.app.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.srit.rhes.app.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
