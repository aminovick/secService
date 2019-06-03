package org.sid.repository;

import org.sid.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole, Long> {
 public AppRole findByRoleName(String roleName);

}
