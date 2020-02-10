package com.srit.app.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srit.app.modal.OrderDetailsEntity;
import com.srit.app.modal.UserDetailsEntity;
import com.srit.app.repo.USerDetailsRepository;

@Service
public class OrderItemsService {
	@Autowired
	USerDetailsRepository userRepository;
	@Autowired
	@Qualifier("output")
	MessageChannel channl;
	UserDetailsEntity user;

	@StreamListener(target = Processor.INPUT)
	public void handle(UserDetailsEntity user) {
		this.user = user;
		System.out.println(user);
	}
	public UserDetailsEntity getUserDetailEntity(int id) {

		Optional<UserDetailsEntity> userDetailOptional = userRepository.findById(id);
		UserDetailsEntity userDetail = userDetailOptional.get();// Optional to object Conversion
		return userDetail;
	}

	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	public UserDetailsEntity saveOrder(UserDetailsEntity userDetailsEntity) {
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, +2);  
		Date date=calendar.getTime();
		System.out.println(date);
	
		Set<OrderDetailsEntity> olist=userDetailsEntity.getOrderDetailsEntities();
		Iterator<OrderDetailsEntity> ite=olist.iterator();
		while (ite.hasNext()) {
			OrderDetailsEntity obj=ite.next();
			Calendar calendar1=Calendar.getInstance();
			obj.setOrderdate(calendar1.getTime());
			obj.setExpecteddate(date);
			olist.add(obj);
		}
		Optional<UserDetailsEntity> userDetailOptional = userRepository.findById(user.getId());
		UserDetailsEntity entity=userDetailOptional.get();
		olist.addAll(entity.getOrderDetailsEntities());
		
		userDetailsEntity.setOrderDetailsEntities(olist);
		
		userDetailsEntity.setId(user.getId());
		userDetailsEntity.setFirstName(user.getFirstName());
		userDetailsEntity.setMiddleName(user.getMiddleName());
		userDetailsEntity.setAddress(user.getAddress());
		userDetailsEntity.setLastName(user.getLastName());
		UserDetailsEntity user=userRepository.save(userDetailsEntity);
		channl.send(MessageBuilder.withPayload(user).build());
		return user;

	}

}


