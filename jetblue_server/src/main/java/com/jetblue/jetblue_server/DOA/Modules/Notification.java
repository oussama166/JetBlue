package com.jetblue.jetblue_server.DOA.Modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int NotificationId;
    private String Message;
    @Builder.Default
    private Date messageTimeStamp = new Date();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private User userId;
}
