package org.rhis.app.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreatedEvent {
	@TargetAggregateIdentifier
	private String usrId;
	private String firestName;
	private String middleName;
	private String lastName;
	private String address;
}
