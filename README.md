# Notification service
### Сервис уведомлений

## Настройка БД
1. **Создать БД**
```
create database notification;
```

2. **Создать схемы в БД notification**
```
create schema notification;
```
3.  **Запустить сервис**

Версия java

```
% java -version                                                                                          
openjdk version "20.0.1" 2023-04-18
OpenJDK Runtime Environment (build 20.0.1+10)
OpenJDK 64-Bit Server VM (build 20.0.1+10, mixed mode, sharing)
```

При запуске нужно указать расположение **application.yml**.

Пример запуска:
```
java -jar notification-service.jar --spring.config.location=/directory/application.yml 
```

4.  **Swagger**

```
http://localhost:8079/notification-service/swagger-ui/index.html#
```

