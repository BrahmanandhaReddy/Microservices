package org.rhis.app.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
	@Id
	private int usrId;
	private String firestName;
	private String middleName;
	private String lastName;
	private String address;
	
	
}
