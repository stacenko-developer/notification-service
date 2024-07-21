package com.artem.notification_service.user.dao.service;

import com.artem.notification_service.common.dao.AbstractServiceDao;
import com.artem.notification_service.user.dao.entity.User;
import com.artem.notification_service.user.dao.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDao extends AbstractServiceDao<User, UserRepository> {

    protected UserServiceDao(UserRepository repository) {
        super(repository);
    }
}
