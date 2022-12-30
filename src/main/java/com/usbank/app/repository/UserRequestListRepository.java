package com.usbank.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.usbank.app.dbmodel.DBMeta;

public interface UserRequestListRepository extends CrudRepository<DBMeta, String>{
	
    @Override
    List<DBMeta> findAll();

}
