package com.artem.notification_service.user.bean;

import com.artem.notification_service.common.PagingDto;
import com.artem.notification_service.user.dao.entity.User;
import com.artem.notification_service.user.dao.service.UserServiceDao;
import com.artem.notification_service.user.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceBean {

    private final UserServiceDao userServiceDao;
    private final ModelMapper mapper;

    @Transactional
    public Page<UserDto> getAllUsers(PagingDto pagingDto) {
        Page<UserDto> result = userServiceDao.findAll(pagingDto.getPageRequest())
                .map(user -> mapper.map(user, UserDto.class));

        return result;
    }

    @Transactional
    public UserDto getUserById(UUID id) {
        User user = userServiceDao.findById(id);
        UserDto result = mapper.map(user, UserDto.class);
        return result;
    }

    @Transactional
    public UserDto createUser(UserDto user) {
        UserDto result = mapper
                .map(userServiceDao.save(mapper.map(user, User.class)),
                        UserDto.class);

        return result;
    }

    @Transactional
    public UserDto updateUser(UserDto user) {
        User entity = userServiceDao.findById(user.getId());
        UUID id = entity.getId();
        mapper.map(user, entity);
        entity.setId(id);
        UserDto result = mapper
                .map(userServiceDao.save(entity), UserDto.class);

        return result;
    }

    @Transactional
    public void deleteUser(UUID id) {
        userServiceDao.deleteById(id);
    }
}
