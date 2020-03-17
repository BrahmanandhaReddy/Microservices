package org.srit.rhes.app.cmd;


import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data               
@NoArgsConstructor 
@AllArgsConstructor
public class CreateUserCommand {
	@TargetAggregateIdentifier
	private UUID id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	
}
