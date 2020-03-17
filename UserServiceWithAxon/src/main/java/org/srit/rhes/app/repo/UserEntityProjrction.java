package org.srit.rhes.app.repo;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;
import org.srit.rhes.app.entity.UserEntity;
import org.srit.rhes.app.events.UserCreatedEvent;
import org.srit.rhes.app.query.FindUserQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserEntityProjrction {
	private final UserRepository userRepository;
	private final QueryUpdateEmitter updateEmitter;
	
	@EventHandler
	public void on(UserCreatedEvent event) {
		 log.debug("Handling a User creation command {}", event.getId());
		 UserEntity entity=new UserEntity(
				 Integer.parseInt(event.getId()),
				 event.getFirstName(),
				 event.getMiddleName(),
				 event.getLastName(),
				 event.getAddress()
				 );
		 this.userRepository.save(entity);
	}
	@QueryHandler
	public UserEntity handle(FindUserQuery query) {
		log.debug("Handling FindBankAccountQuery query: {}", query);
		return this.userRepository.findById(query.getId()).orElse(null);
		
	}
}
