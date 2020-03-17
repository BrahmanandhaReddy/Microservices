package com.srit.user.resource;

import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srit.user.modal.OrderDetailsEntity;
import com.srit.user.modal.UserDetails;
import com.srit.user.service.UserDetailsServic;

@RestController
@RequestMapping("/userservice")
public class UserServiceResource {

	private static final Logger log = LogManager.getLogger(UserServiceResource.class.getName());

	@Autowired
	UserDetailsServic userDetailsServic;
	CompletableFuture<UserDetails> userDetails;
	OrderDetailsEntity orderDetailsEntity;

	@StreamListener(target = Processor.INPUT)
	public void handle(OrderDetailsEntity orderDetailsEntityO) {
		this.orderDetailsEntity=orderDetailsEntityO;
		//System.out.println(orderDetailsEntity);
	}

	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetails> getDetails(@PathVariable Long id) {
		log.info("inside method getdetails with id:" + id);
		UserDetails userdetails = userDetailsServic.findById(id);
		log.info("user details with id:" + userdetails);
		System.out.println(userdetails);
		return new ResponseEntity<UserDetails>(HttpStatus.OK);

	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@Async("taskExecutor")
	public ResponseEntity<UserDetails> creat(@RequestBody UserDetails user) {
		userDetails = userDetailsServic.creatUser(user);
		log.info("inside method creat user with details:" + userDetails); 
		return new ResponseEntity<UserDetails>(HttpStatus.CREATED);

	}

	@DeleteMapping(value = "/delet/{id}")
	public ResponseEntity<UserDetails> deleteUser(@PathVariable("id") long id) {
		log.info("inside method deleteuser with id:" + id);
		userDetailsServic.deleteUserById(id);
		return new ResponseEntity<UserDetails>(HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDetails updateUserDetails(@RequestBody UserDetails usd, @PathVariable("id") long id) {
		return userDetailsServic.upDate(usd, id);
	}


}


