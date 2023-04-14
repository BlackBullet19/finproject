package org.finproject.service;

import org.finproject.entity.ProgramUser;

import java.util.List;

public interface UserService {

    List<ProgramUser> list();

    ProgramUser createUser(ProgramUser user);

    ProgramUser getUser(long userId);

    ProgramUser updateUser(long userId, ProgramUser user);

    void deleteUser(long userId);

    boolean existsByUserId(long userId);
}
