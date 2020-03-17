package org.srit.rhes.app.aggregater;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.srit.rhes.app.cmd.CreateUserCommand;
import org.srit.rhes.app.events.UserCreatedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Getter  
@Aggregate
public class UserAggregater {
	@AggregateIdentifier
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;

	@CommandHandler
	public UserAggregater(CreateUserCommand cmd) {
		AggregateLifecycle.apply(
				new UserCreatedEvent(
						String.valueOf(cmd.getId()),
						cmd.getFirstName(),
						cmd.getMiddleName(),
						cmd.getLastName(),
						cmd.getAddress()
						)
				);
	}

	@EventSourcingHandler
	public void on(UserCreatedEvent event) {
		this.id=Integer.parseInt(event.getId());
		this.firstName=event.getFirstName();
		this.middleName=event.getMiddleName();
		this.lastName=event.getLastName();
		this.address=event.getAddress();
	}
}
