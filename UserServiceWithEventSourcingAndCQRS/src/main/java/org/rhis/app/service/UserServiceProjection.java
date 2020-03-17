package org.rhis.app.service;

import org.axonframework.eventhandling.EventHandler;
import org.rhis.app.event.UserCreatedEvent;
import org.rhis.app.modal.UserDetail;
import org.rhis.app.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceProjection {
	private final UserRepository repo;

	public UserServiceProjection(UserRepository repo) {
		this.repo = repo;
	}

	@EventHandler
	public void handle(UserCreatedEvent event) {
		repo.save(
				new UserDetail(Integer.parseInt(event.getUsrId()),
						event.getFirestName(),
						event.getMiddleName(),
						event.getLastName(),
						event.getAddress()
						)
				);

	}
}
