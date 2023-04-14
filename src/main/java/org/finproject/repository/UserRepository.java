package org.finproject.repository;

import org.finproject.entity.ProgramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ProgramUser, Integer> {

    boolean existsByUserId(long userId);

    ProgramUser findByUserId(long userId);
}
