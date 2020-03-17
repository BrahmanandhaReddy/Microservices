package org.srit.rhes.app.controler;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.srit.rhes.app.query.UserQueryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserServiceQueryControler {

	private final UserQueryService userQueryService;
	@GetMapping("/{id}")
    public List<Object> findById(@PathVariable("id") String id) {
        return this.userQueryService.listEventsForAccount(id);
    }
}
