package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM show_notifications(:user_id)")
    List<Notification> showNotificationsByUserId(@Param("user_id") long userId);

}
