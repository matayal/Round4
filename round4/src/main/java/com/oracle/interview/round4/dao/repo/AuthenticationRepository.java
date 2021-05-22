package com.oracle.interview.round4.dao.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oracle.interview.round4.dao.beans.UserAuthentication;

@Repository
@Transactional
public interface AuthenticationRepository extends JpaRepository<UserAuthentication, String> {


	UserAuthentication findByUsernameIgnoreCaseAndPassword(String username, String password);


}
