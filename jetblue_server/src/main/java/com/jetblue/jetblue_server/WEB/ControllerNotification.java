package com.jetblue.jetblue_server.WEB;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jetblue.jetblue_server.DOA.Modules.Notification;
import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerNotification;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin
public class ControllerNotification {
    private final ManagerNotification managerNotification;

    public ControllerNotification(ManagerNotification managerNotification) {
        this.managerNotification = managerNotification;
    }


    @PostMapping(
            path = "/setNotification",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Notification> setNotification(@RequestParam String message, @RequestBody User user) {
        Optional<Notification> notification = null;
        try {
            notification = managerNotification.setNotification(message, user);
            return ResponseEntity.status(200).body(notification.get());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping(
            path = "/AllNotifications"
    )
    public ResponseEntity<List<Notification>> getAllNotifications(@RequestParam long id_user) {
        Optional<List<Notification>> notifications;
        try {
            notifications = managerNotification.getAllNotifications(id_user);
            if (notifications.isPresent()) {
                return ResponseEntity.status(200).body(notifications.get());
            }
            throw new IllegalArgumentException("User has problem in notification level");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(null);
        }

    }

    @GetMapping(
            path = "/getNotificationID",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Notification> getNotificationById(@RequestBody ObjectNode notification_id) {
        long notification_conv = notification_id.get("notification_id").asLong();
        if (notification_conv < 0) {
            throw new IllegalArgumentException("user can not have negative id");
        }
        try {
            Notification notification = managerNotification.getNotificationById(notification_conv);
            return ResponseEntity.status(200).body(notification);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping(
            path = "/getNotificationByMessage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Notification>> getNotificationsByMessage(@RequestBody ObjectNode Message) {
        try {
            String message = Message.get("message").asText().trim().replaceAll("^\"|\"$", "");
            long user_id = Message.get("user_id").asLong();
            List<Notification> notifications = managerNotification.getNotificationByMessage(message, user_id);
            return ResponseEntity.status(200).body(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping(
            path = "/removeNotification",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Notification> deleteNotificationById(@RequestBody ObjectNode notifiactionNode) {
        long id_notification = notifiactionNode.get("id_Notification").asLong();
        if (id_notification < 0) {
            throw new IllegalReceiveException("Notification id shouldn't be under negative ID");
        }
        try {
            Notification notification = managerNotification.deleteNotifiactionById(id_notification);
            return ResponseEntity.status(HttpStatus.OK).body(notification);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * This controller to Delete all the notifications data contains the message passed by parms
     *
     * @return List
     * @params BodyRequest
     */
    @DeleteMapping(
            path = "/removeNotifications",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Notification>> removeNotifications(
            @RequestBody ObjectNode MessageDelete
    ) {
        String Message = MessageDelete.get("message").asText().trim().replaceAll("^\"|\"$", "");
        if (Message.isBlank() || Message.isEmpty()) {
            return ResponseEntity.status(200).body(null);
        }
        try {
            List<Notification> notifications = managerNotification.deleteNotifiactionByMessage(Message);
            if (notifications == null) {
                return ResponseEntity.status(HttpStatus.IM_USED).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(notifications);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(null);
        }
    }
}
