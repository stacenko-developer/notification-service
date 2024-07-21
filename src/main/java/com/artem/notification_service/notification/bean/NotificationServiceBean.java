package com.artem.notification_service.notification.bean;

import com.artem.notification_service.notification.dao.entity.Notification;
import com.artem.notification_service.notification.dao.entity.NotificationConverter;
import com.artem.notification_service.notification.dao.service.NotificationServiceDao;
import com.artem.notification_service.notification.dto.NotificationDto;
import com.artem.notification_service.notification.dto.SearchNotificationsDto;
import com.artem.notification_service.notification.dto.UpdateNotificationDto;
import com.artem.notification_service.notification.exception.IncorrectNotificationException;
import com.artem.notification_service.user.dao.service.UserServiceDao;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NotificationServiceBean {

    private final NotificationServiceDao notificationServiceDao;
    private final NotificationConverter notificationConverter;
    private final UserServiceDao userServiceDao;

    private void validateNotification(String sender, String text) {
        if (StringUtils.isBlank(sender) || StringUtils.isBlank(text)) {
            throw new IncorrectNotificationException();
        }
    }

    @Transactional
    public Page<NotificationDto> getAllNotifications(SearchNotificationsDto searchNotificationsDto) {
        Page<NotificationDto> result;
        userServiceDao.findById(searchNotificationsDto.getUserId());

        if (searchNotificationsDto.getIsRead() == null) {
            result = notificationServiceDao.getAllNotifications(searchNotificationsDto);
        } else if (searchNotificationsDto.getIsRead()) {
            result = notificationServiceDao.getAllReadNotifications(searchNotificationsDto);
        } else {
            result = notificationServiceDao.getAllUnReadNotifications(searchNotificationsDto);
        }

        return result;
    }

    @Transactional
    public NotificationDto createNotification(NotificationDto notification) {
        validateNotification(notification.getSender(), notification.getText());

        NotificationDto result = notificationConverter
                .convertToDto(notificationServiceDao
                        .save(notificationConverter.convertToModel(notification)));

        return result;
    }

    @Transactional
    public NotificationDto updateNotification(UpdateNotificationDto updateNotificationDto) {
        UUID notificationId = null;
        NotificationDto notificationDto = updateNotificationDto.getNotificationDto();

        if (notificationDto != null) {
            notificationId = notificationDto.getId();
        }

        Notification entity = notificationServiceDao.findById(notificationId);
        UUID id = entity.getId();
        validateNotification(notificationDto.getSender(), notificationDto.getText());
        notificationConverter.map(updateNotificationDto.getNotificationDto(), entity);
        entity.setId(id);

        NotificationDto result = notificationConverter.convertToDto(notificationServiceDao
                .save(entity));

        result.setRead(notificationDto.isRead());

        if (notificationDto.isRead()) {
            notificationServiceDao.doRead(notificationId, updateNotificationDto.getUserId());
        }

        if (!notificationDto.isRead()) {
            notificationServiceDao.doUnRead(notificationId, updateNotificationDto.getUserId());
        }

        return result;
    }

    @Transactional
    public void deleteNotification(UUID id) {
        notificationServiceDao.deleteById(id);
    }
}
