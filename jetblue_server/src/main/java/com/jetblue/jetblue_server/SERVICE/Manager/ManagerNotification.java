package com.jetblue.jetblue_server.SERVICE.Manager;


import com.jetblue.jetblue_server.DOA.Modules.Notification;
import com.jetblue.jetblue_server.DOA.Modules.User;

import java.util.List;
import java.util.Optional;

public interface ManagerNotification {
    // Set Notification
     Optional<Notification> setNotification(String message, User user);


    // Get Notification
    Optional<List<Notification>> getAllNotifications(long user_id);

    // Get Notification by id of notification
    Notification getNotificationById(long Id);

    // Get Notification by message
    List<Notification> getNotificationByMessage(String Message, long id_user);

    // Delete Notification By ID
    Notification deleteNotifiactionById(long Id);

    // Delete Notification By Message
    List<Notification> deleteNotifiactionByMessage(String Message);

}
