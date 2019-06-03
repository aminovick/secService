package org.sid.web;

import org.sid.entity.AppUser;
import org.sid.service.AccontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@Controller
@RestController

public class ControllerSecurity {
	@Autowired
	private AccontService accontService;
	@PostMapping("/register")
	
public AppUser saveUser(@RequestBody FormUser formUser) {
	return accontService.saveUser(formUser.getUsername(),formUser.getPassword(), formUser.getConfirmation());
}

}
@Data
class FormUser{
	private String username;
	private String password;
	private String confirmation;
}
