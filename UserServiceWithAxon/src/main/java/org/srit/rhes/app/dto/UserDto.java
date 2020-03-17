package org.srit.rhes.app.dto;


import java.util.UUID;

import lombok.Value;

@Value
public class UserDto {
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
}
