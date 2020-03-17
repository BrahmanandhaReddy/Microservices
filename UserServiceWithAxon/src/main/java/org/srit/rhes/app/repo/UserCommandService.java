package org.srit.rhes.app.repo;

import java.util.Random;
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
		Random randam=new Random();
		return this.commandGateway.send(
				new CreateUserCommand(
						randam.nextInt(1000),
						creatUserDto.getFirstName(),
						creatUserDto.getMiddleName(),
						creatUserDto.getLastName(),
						creatUserDto.getAddress()
						)
				);

	}
}
