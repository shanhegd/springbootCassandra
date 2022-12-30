/**
 * 
 */
package com.usbank.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.usbank.app.dbmodel.DBMeta;

/**
 * @author Shankar Hegde
 *
 */
public interface MetaRepositry extends CrudRepository<DBMeta, String>{

}
