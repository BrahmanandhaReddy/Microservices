package org.rhis.app.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserCommand {
	@TargetAggregateIdentifier
	private int usrId;
	private String firestName;
	private String middleName;
	private String lastName;
	private String address;
}
