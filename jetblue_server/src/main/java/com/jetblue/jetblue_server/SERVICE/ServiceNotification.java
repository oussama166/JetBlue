package com.jetblue.jetblue_server.SERVICE;

import com.jetblue.jetblue_server.DOA.Modules.Notification;
import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryNotification;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryUser;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerNotification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceNotification implements ManagerNotification {
    // instance of Notification Repository
    @Autowired
    private RepositoryNotification repositoryNotification;
    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    // Set Notification
    @Transactional
    public Optional<Notification> setNotification(String message, User user) {
        if (user == null || user.getUserId() < 0) {
            throw new IllegalArgumentException("Invalid user information");
        }

        try {
            Optional<User> userOptional = repositoryUser.findById((long) user.getUserId() + 1);
            if (!userOptional.isPresent()) {
                throw new IllegalStateException("User not found to set notification");
            }

            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setUserId(userOptional.get());
            repositoryNotification.save(notification);
            return Optional.of(notification);
        } catch (Exception e) {
            System.out.println("Error setting notification: " + e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    // Get Notification
    public Optional<List<Notification>> getAllNotifications(long user_id) {
        if (user_id < 0) {
            throw new IllegalArgumentException("This user must be have all fileds !!");
        }
        try {
            Optional<List<Notification>> notifications = repositoryNotification.findByUserId(user_id);
            if (notifications.isEmpty()) {
                throw new IllegalArgumentException("user with id : " + user_id + "don't have any notifications");
            }
            return notifications;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    // Get Notification by id of notification
    public Notification getNotificationById(long Id) {
        try {
            Optional<Notification> notificationById = repositoryNotification.findById(Id);
            if (notificationById.isPresent()) {
                return notificationById.get();
            } else {
                throw new Exception("User notified not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    // Get Notification by messages
    public List<Notification> getNotificationByMessage(String Message, long id_user) {
        try {
            Optional<User> user = repositoryUser.findById(id_user);
            if (user.isPresent()) {
                Optional<List<Notification>> notifications = repositoryNotification.findByMessageAndUserId(Message, id_user);
                if (notifications.isPresent()) {
                    return notifications.get();
                }else {
                    throw new Exception("Message not found!!");
                }
            }
            throw new Exception("User never revise like this message : " + Message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    // Delete Notification By ID
    public Notification deleteNotifiactionById(long Id) {
        try {
            Optional<Notification> notification = repositoryNotification.findById(Id);
            if (notification.isPresent()) {
                repositoryNotification.delete(notification.get());
                return notification.get();
            } else {
                throw new Exception("Notification not found by id : " + Id + " !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    // Delete Notification By Message
    public List<Notification> deleteNotifiactionByMessage(String Message) {
        try {
            Optional<List<Notification>> notifications = repositoryNotification.findByMessage(Message);
            if (notifications.isPresent()) {
                repositoryNotification.deleteAll(notifications.get());
                return notifications.get();
            } else {
                throw new Exception("Notification not found message : " + Message + " !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
