package com.artem.notification_service.notification.dao.entity;

import com.artem.notification_service.common.dao.BaseEntity;
import com.artem.notification_service.user.dao.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import java.io.Serial;

@Getter
@Setter
@Entity
@Comment("Информация о прочтении уведомлений")
@Table(schema = "notification", name = "tr_notification_view")
public class NotificationView extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 3144661797828122415L;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;
}
