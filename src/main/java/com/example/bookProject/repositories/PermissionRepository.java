package com.example.bookProject.repositories;

import com.example.bookProject.domain.entities.PermissionEntity;
import com.example.bookProject.domain.type.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    PermissionEntity findByPermission(Permission permission);
}
