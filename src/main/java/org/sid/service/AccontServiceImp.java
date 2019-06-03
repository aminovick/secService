package org.sid.service;

import org.sid.entity.AppRole;
import org.sid.entity.AppUser;
import org.sid.repository.AppRoleRepo;
import org.sid.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional

public class AccontServiceImp implements AccontService{
	@Autowired
	private AppRoleRepo appRoleRepo;
	@Autowired
	private AppUserRepo appUserRepo;
	@Autowired
	private BCryptPasswordEncoder bcryptpassword;

	@Override
	public AppUser saveUser(String username, String password, String confirmation) {
AppUser user =appUserRepo.findByUsername(username);
if (user !=null)  throw new RuntimeException("user allready existe!");
if (!password.equals(confirmation))throw new RuntimeException("enter correct password");
AppUser appUser = new AppUser();
appUser.setUsername(username);
appUser.setPassword(bcryptpassword.encode(password));
appUser.setActive(true);
appUserRepo.save(appUser);
addRoleToUser(username, "USER");
		return appUser;
	}

	@Override
	public AppRole saveRole(AppRole appRole) {
		
		return appRoleRepo.save(appRole);
	}

	@Override
	public AppUser loadUserbyUsername(String username) {
		return appUserRepo.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		AppUser user=appUserRepo.findByUsername(username);
		AppRole role= appRoleRepo.findByRoleName(rolename);
	    user.getRoles().add(role);
		
		
	}

}
