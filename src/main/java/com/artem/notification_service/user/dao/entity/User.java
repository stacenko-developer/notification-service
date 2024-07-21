package com.artem.notification_service.user.dao.entity;

import com.artem.notification_service.common.dao.BaseEntity;
import com.artem.notification_service.notification.dao.entity.NotificationView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Comment("Пользователь")
@Table(schema = "notification", name = "tr_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -6969042351025013037L;

    @Column(nullable = false)
    private String login;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationView> notificationViews = new ArrayList<>();
}
