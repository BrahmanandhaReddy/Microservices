package org.srit.rhes.app.query;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.srit.rhes.app.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserQueryService {
	private final QueryGateway queryGateway;
	private final EventStore eventStore;


	public CompletableFuture<UserEntity> findbyId(String id ){ 
		return
			this.queryGateway.query(id, ResponseTypes.instanceOf(UserEntity.class));

	}

	public List<Object> listEventsForAccount(String id) {
		return this.eventStore
				.readEvents(id.toString())
				.asStream()
				.map(Message::getPayload)
				.collect(Collectors.toList());
	}
}
