package org.sid.repository;

import org.sid.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo  extends JpaRepository<AppUser, Long>{
	public AppUser findByUsername(String username);

}
