package org.srit.rhes.app.controler;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.srit.rhes.app.dto.UserDto;
import org.srit.rhes.app.entity.UserEntity;
import org.srit.rhes.app.repo.UserCommandService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserServiceCommandControler {

	public final UserCommandService userCommandService;

    @PostMapping(value = "/create")
    public CompletableFuture<UserEntity> createAccount(@RequestBody UserDto creationDTO) {
        return this.userCommandService.creatUser(creationDTO);
    }

}
