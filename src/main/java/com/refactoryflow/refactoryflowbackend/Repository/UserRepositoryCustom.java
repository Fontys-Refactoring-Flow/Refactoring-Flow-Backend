package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.User;

public interface UserRepositoryCustom {

    User findUserByEmailAndPassword(String email, String password);

    User findUserByUsername(String email);
}
