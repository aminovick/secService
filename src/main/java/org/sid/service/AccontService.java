package org.sid.service;

import org.sid.entity.AppRole;
import org.sid.entity.AppUser;

public interface AccontService {
public AppUser saveUser(String username,String password,String confirmation);
public AppRole saveRole(AppRole appRole);
public AppUser loadUserbyUsername(String username);
public void addRoleToUser(String username,String rolename);
}
