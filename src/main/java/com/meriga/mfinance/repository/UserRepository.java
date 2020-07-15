package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

//    Optional<User> findOneByActivationKey(String activationKey);
//
//    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);
//
//    Optional<User> findOneByResetKey(String resetKey);
//
//    Optional<User> findOneByEmailIgnoreCase(String email);
//
//    Optional<User> findOneByLogin(String login);
}
