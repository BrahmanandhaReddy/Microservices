package org.rhis.app.aggregater;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.rhis.app.commands.CreateUserCommand;
import org.rhis.app.event.UserCreatedEvent;

import lombok.Data;
@Data
@Aggregate
public class UserAggregater {
	@AggregateIdentifier
	private int usrId;
	private String firestName;
	private String middleName;
	private String lastName;
	private String address;

	@CommandHandler
	public UserAggregater(CreateUserCommand cmd) {
		this.usrId=cmd.getUsrId();
		//it will puts the event into the event bus
		AggregateLifecycle.apply(
				new UserCreatedEvent(
						String.valueOf(cmd.getUsrId()),
						cmd.getFirestName(),
						cmd.getMiddleName(),
						cmd.getLastName(),
						cmd.getAddress()
						)
				);

	}
	public void on(UserCreatedEvent event) {
		this.usrId=Integer.parseInt(event.getUsrId());
		this.firestName=event.getFirestName();
		this.middleName=event.getMiddleName();
		this.lastName=event.getLastName();
		this.address=event.getAddress();
	}
}
