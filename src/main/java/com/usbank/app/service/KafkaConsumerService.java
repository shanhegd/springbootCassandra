/**
 * 
 */
package com.usbank.app.service;



import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usbank.app.dbmodel.DBMeta;
import com.usbank.app.dbmodel.UserRequest;
import com.usbank.app.repository.MetaRepositry;
import com.usbank.app.repository.UserRequestListRepository;

/**
 * @author c084199
 *
 */

@Service
public class KafkaConsumerService {
	
	
	@Autowired
	MetaRepositry metaRepositry;

	@Autowired
	UserRequestListRepository userRequestListRepository;
	
	 @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
	    public void consume(@Payload List<UserRequest> payload ) throws JsonParseException, JsonMappingException, IOException{
		
		 ObjectMapper objectMapper = new ObjectMapper();
		 StringBuilder sb=new StringBuilder();  
	     sb.append(payload);
	     UserRequest[] userRequest = objectMapper.readValue(sb.toString(), UserRequest[].class);	
	    System.out.println("Concumed KAFKA Message "+objectMapper.writeValueAsString(payload.toString()));
	     System.out.println("DataSet "+userRequest[0].getDataset());
		 //System.out.println(" Payload metadata "+payload.value().getMeta());
		 DBMeta bBMeta=new DBMeta();
		 
		 bBMeta.setUsedBy(userRequest[0].getUsedBy());
		 bBMeta.setDataset(userRequest[0].getDataset());
		 bBMeta.setPath(userRequest[0].getPath());
		 bBMeta.setDatasetVersion(userRequest[0].getDatasetVersion());
		 metaRepositry.save(bBMeta);
		 System.out.println("Consumed kafka request Messages and saved it to Database!! ");

	    }
	
	
	
	public List<DBMeta> viewuserrequestList() {
		
		List<DBMeta> dbMetaList=userRequestListRepository.findAll();
		return dbMetaList;
		
	}
}
