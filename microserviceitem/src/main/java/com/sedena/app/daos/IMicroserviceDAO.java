package com.sedena.app.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sedena.app.entities.item;

public interface IMicroserviceDAO extends CrudRepository<item, Long> {
    @Query("SELECT u FROM item u WHERE u.name=:name")
    Optional<item> searchByName(@Param("name") String name);

}