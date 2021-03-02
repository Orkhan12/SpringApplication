package org.iskandarov.repositories;

import org.iskandarov.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    @Override
    <S extends User> S saveAndFlush(S s);

    @Override
    User getOne(Integer integer);

    @Override
    <S extends User> List<S> findAll(Example<S> example);


}
