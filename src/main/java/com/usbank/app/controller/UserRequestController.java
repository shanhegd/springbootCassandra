package com.usbank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usbank.app.dbmodel.DBMeta;
import com.usbank.app.service.KafkaConsumerService;
@RestController
public class UserRequestController
{
	
	
	@Autowired
	KafkaConsumerService kafkaConsumerService;
	
	
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"isWorking\" : true }";
	}

//	@GetMapping("/v2/userrequestdata")
//	public void storeMetaData() {
//		kafkaConsumerService.storeMetaData();
//	
//		System.out.println("Saved to cassandra Database");
//		
//	}
//	
//	
	@GetMapping("/v2/viewuserrequest")
	public List<DBMeta> viewuserrequest() {
		List<DBMeta> dbMetaList=kafkaConsumerService.viewuserrequestList();
	
		return dbMetaList;
		
	}
	
}
