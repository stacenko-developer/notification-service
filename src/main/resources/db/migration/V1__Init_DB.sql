create table notification.tr_notification (
    id uuid default gen_random_uuid() constraint tr_notification_pk primary key,
    text text not null,
    sender text not null
);

create table notification.tr_notification_view (
    id uuid default gen_random_uuid() constraint tr_notification_view_pk primary key,
    notification_id uuid not null,
    user_id uuid not null
);

create table notification.tr_user (
    id uuid default gen_random_uuid() constraint tr_user_pk primary key,
    login text not null
);

alter table if exists notification.tr_notification_view
    add constraint tr_notification_view_tr_notification_fk
    foreign key (notification_id) references notification.tr_notification;

alter table if exists notification.tr_notification_view
    add constraint tr_notification_view_tr_user_fk
    foreign key (user_id) references notification.tr_user;

comment on table notification.tr_user is 'Пользователи';
comment on column notification.tr_user.id is 'Идентификатор пользователя';
comment on column notification.tr_user.login is 'Логин пользователя';

comment on table notification.tr_notification is 'Уведомления';
comment on column notification.tr_notification.id is 'Идентификатор уведомления';
comment on column notification.tr_notification.sender is 'Отправитель уведомления';
comment on column notification.tr_notification.text is 'Текст уведомления';

comment on table notification.tr_notification_view is 'Информация о прочтении уведомлений';
comment on column notification.tr_notification_view.id is 'Идентификатор записи о прочтении уведомлений';
comment on column notification.tr_notification_view.notification_id is 'Идентификатор уведомления';
comment on column notification.tr_notification_view.user_id is 'Идентификатор пользователя';