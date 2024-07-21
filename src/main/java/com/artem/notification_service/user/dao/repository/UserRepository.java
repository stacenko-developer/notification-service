package com.artem.notification_service.user.dao.repository;

import com.artem.notification_service.common.dao.AbstractRepository;
import com.artem.notification_service.user.dao.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, AbstractRepository<User> {
}
