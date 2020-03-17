package org.srit.rhes.app.repo;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.srit.rhes.app.cmd.CreateUserCommand;
import org.srit.rhes.app.dto.UserDto;
import org.srit.rhes.app.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserCommandService {
	private final CommandGateway commandGateway;

	public CompletableFuture<UserEntity>creatUser(UserDto creatUserDto){
		return this.commandGateway.send(
				new CreateUserCommand(
						UUID.randomUUID(),
						creatUserDto.getFirstName(),
						creatUserDto.getMiddleName(),
						creatUserDto.getLastName(),
						creatUserDto.getAddress()
						)
				);

	}
}
