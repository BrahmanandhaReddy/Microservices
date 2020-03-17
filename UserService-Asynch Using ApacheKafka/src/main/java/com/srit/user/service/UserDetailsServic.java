package com.srit.user.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srit.user.exception.EntityEmptyException;
import com.srit.user.exception.RecordNotFoundException;
import com.srit.user.modal.UserDetails;
import com.srit.user.repo.UserRepository;

@Service
public class UserDetailsServic {

	@Autowired
	UserRepository userRepository;
	@Autowired
	@Qualifier("output")
	MessageChannel channal;
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	@Async("taskExecutor")
	public CompletableFuture<UserDetails> creatUser(UserDetails user) {
		if(user==null&&user.getFirstName().isEmpty()) {
			throw new EntityEmptyException("entity empty");
		}
		UserDetails userDetails=userRepository.save(user);
		channal.send(MessageBuilder.withPayload(userDetails).build());
		return CompletableFuture.completedFuture(userDetails);

	}
	public void deleteUserById(long id) { 
		UserDetails userdetails = userRepository.findById(id);
		if (userdetails == null) {
			throw new RecordNotFoundException("Invalid id:"+id);
		}
		userRepository.deleteById(id);
	}
	public UserDetails findById(long id){
		UserDetails userdetails = userRepository.findById(id);
		if (userdetails == null) {
			throw new RecordNotFoundException("Invalid id:"+id);
		}
		return userdetails;
	}
	public UserDetails upDate(UserDetails user,long id) {
		UserDetails userdetails = userRepository.findById(id);
		if (userdetails == null) {
			throw new RecordNotFoundException("Invalid id:"+id);
		}
		userdetails.setFirstName(user.getFirstName());
		userdetails.setMiddleName(user.getMiddleName());
		userdetails.setLastName(user.getLastName());
		userdetails.setAddress(user.getAddress());
		UserDetails userdetail= userRepository.saveAndFlush(userdetails);
		channal.send(MessageBuilder.withPayload(userdetail).build());
		return userdetail;
	}

}
