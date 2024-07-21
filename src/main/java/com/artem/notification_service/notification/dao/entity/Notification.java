package com.artem.notification_service.notification.dao.entity;

import com.artem.notification_service.common.dao.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Comment("Уведомление")
@Table(schema = "notification", name = "tr_notification")
public class Notification extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -1498293622384066469L;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String sender;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationView> notificationViews = new ArrayList<>();
}
