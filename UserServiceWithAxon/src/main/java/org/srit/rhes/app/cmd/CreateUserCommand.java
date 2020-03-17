package org.srit.rhes.app.cmd;



import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data               
@NoArgsConstructor 
public class CreateUserCommand {
	@TargetAggregateIdentifier
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	public CreateUserCommand(int id, String firstName, String middleName, String lastName, String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address = address;
	}
	
}
