package org.srit.rhes.app.events;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserCreatedEvent {
	
	private UUID id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
}
