package com.srit.app.resource;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srit.app.modal.UserDetailsEntity;
import com.srit.app.service.OrderItemsService;

@RestController
@RequestMapping("/orderitem")
/*@RequestMapping("/orderitem")
 * All URLs exposed in this application will be prefaced with /orderitem prefix.
 */
public class OrderItemsResource {

	@Autowired
	OrderItemsService service;
	UserDetailsEntity user;
	Logger logger = Logger.getLogger(OrderItemsResource.class.getName());

	@GetMapping(value = "/getuserdetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetailsEntity> getUserdetails(@RequestParam("id") int id) {
		CompletableFuture<UserDetailsEntity> responce = service.getUserDetailEntity(id);
		logger.log(Level.INFO, responce.toString());
		return new ResponseEntity<UserDetailsEntity>(HttpStatus.OK);
	}

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Async("taskExecutor")
	public ResponseEntity<UserDetailsEntity> saveOrderDt(@RequestBody UserDetailsEntity userdetaislEntity) {
		CompletableFuture<UserDetailsEntity> userdts=service.saveOrder(userdetaislEntity);
		return new ResponseEntity<UserDetailsEntity>(HttpStatus.CREATED);

	}

}
