package org.srit.rhes.app.events;



import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserCreatedEvent {
	
	private String  id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
}
