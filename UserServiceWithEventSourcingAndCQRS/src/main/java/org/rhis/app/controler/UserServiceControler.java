package org.rhis.app.controler;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.rhis.app.commands.CreateUserCommand;
import org.rhis.app.modal.UserDetail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserServiceControler {
	
	private final CommandGateway commandGateway;

	public UserServiceControler(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}
	@PostMapping
	public CompletableFuture<Object> creatUser(@RequestBody UserDetail detail){
		return commandGateway.send(
				new CreateUserCommand(
						detail.getUsrId(),
						detail.getFirestName(),
						detail.getMiddleName(),
						detail.getLastName(),
						detail.getAddress()));
	}

}
