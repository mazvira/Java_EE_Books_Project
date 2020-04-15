package com.example.bookProject.services;

import com.example.bookProject.domain.entities.PermissionEntity;
import com.example.bookProject.domain.type.Permission;
import com.example.bookProject.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Transactional
    public PermissionEntity saveUserPermission() {
        return permissionRepository.save(PermissionEntity.builder().permission(Permission.USER).build());
    }

    @Transactional
    public PermissionEntity saveAdminPermission() {
        return permissionRepository.save(PermissionEntity.builder().permission(Permission.ADMIN).build());
    }
}
