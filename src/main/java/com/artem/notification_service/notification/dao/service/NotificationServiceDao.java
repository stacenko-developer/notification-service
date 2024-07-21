package com.artem.notification_service.notification.dao.service;

import com.artem.notification_service.common.dao.AbstractServiceDao;
import com.artem.notification_service.notification.dao.entity.Notification;
import com.artem.notification_service.notification.dao.entity.NotificationConverter;
import com.artem.notification_service.notification.dao.entity.NotificationView;
import com.artem.notification_service.notification.dao.repository.NotificationRepository;
import com.artem.notification_service.notification.dao.repository.NotificationViewRepository;
import com.artem.notification_service.notification.dto.NotificationDto;
import com.artem.notification_service.notification.dto.SearchNotificationsDto;
import com.artem.notification_service.user.dao.service.UserServiceDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class NotificationServiceDao extends AbstractServiceDao<Notification, NotificationRepository> {

    private final NotificationViewRepository notificationViewRepository;
    private final NotificationConverter notificationConverter;
    private final UserServiceDao userServiceDao;

    protected NotificationServiceDao(NotificationRepository repository,
                                     NotificationViewRepository notificationViewRepository,
                                     NotificationConverter notificationConverter, UserServiceDao userServiceDao) {
        super(repository);
        this.notificationViewRepository = notificationViewRepository;
        this.notificationConverter = notificationConverter;
        this.userServiceDao = userServiceDao;
    }

    @Transactional
    public Page<NotificationDto> getAllNotifications(SearchNotificationsDto searchNotificationsDto) {
        Page<NotificationDto> result = repository
                .findAll(searchNotificationsDto.getPageRequest())
                .map(notification -> notificationConverter.convertToDto(notification,
                        searchNotificationsDto.getUserId()));

        return result;
    }

    @Transactional
    public Page<NotificationDto> getAllReadNotifications(SearchNotificationsDto searchNotificationsDto) {
        Page<NotificationDto> result = new PageImpl<>(repository
                .findNotificationsByNotificationViews_UserId(searchNotificationsDto.getUserId(),
                        searchNotificationsDto.getPageRequest()))
                .map(notification
                        -> notificationConverter.convertToDto(notification, searchNotificationsDto.getUserId()));

        return result;
    }

    @Transactional
    public Page<NotificationDto> getAllUnReadNotifications(SearchNotificationsDto searchNotificationsDto) {
        Page<NotificationDto> result = new PageImpl<>(repository
                .findAllUnReadNotifications(searchNotificationsDto.getUserId(),
                        searchNotificationsDto.getPageRequest()))
                .map(notification
                        -> notificationConverter.convertToDto(notification, searchNotificationsDto.getUserId()));

        return result;
    }

    @Transactional
    public void doRead(UUID notificationId, UUID userId) {
        NotificationView notificationView = notificationViewRepository
                .findByUserIdAndNotificationId(userId, notificationId).orElse(null);

        if (notificationView == null) {
            NotificationView view = new NotificationView();
            view.setNotification(findById(notificationId));
            view.setUser(userServiceDao.findById(userId));

            notificationViewRepository.save(view);
        }
    }

    @Transactional
    public void doUnRead(UUID notificationId, UUID userId) {
        NotificationView notificationView = notificationViewRepository
                .findByUserIdAndNotificationId(userId, notificationId).orElse(null);

        if (notificationView != null) {
            notificationView.getUser().getNotificationViews().removeIf(note -> note.getId() == notificationView.getId());
            notificationView.getNotification().getNotificationViews().removeIf(note -> note.getId() == notificationView.getId());
        }
    }
}