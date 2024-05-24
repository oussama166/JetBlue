package com.jetblue.jetblue_server.DOA.Modules;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jetblue.jetblue_server.DOA.Modules.ENUMS.Gender;
import com.jetblue.jetblue_server.DOA.Modules.Security.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;

    private String Name;

    private String LastName;

    @Column(unique = true)
    private String Email;

    private String PhoneNumber;
    private String Password;
    private String Address;
    private String City;
    private String State;
    private String Country;


    @Nullable()
    @Enumerated(EnumType.STRING)
    private com.jetblue.jetblue_server.DOA.Modules.ENUMS.Gender Gender;
    @Nullable()
    private LocalDate BirthDay;
    @Builder.Default
    private Boolean isConnected = false;

    @Builder.Default
    private Date CreatedAt = new Date();
    @Builder.Default
    private Date UpdatedAt = new Date();

    @OneToMany(
            mappedBy = "userId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Review> reviwesList = new ArrayList<>();

    @OneToMany(
            mappedBy = "userId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Notification> userNotifications = new ArrayList<>();

    //    Adding role of user
    @Enumerated(EnumType.STRING)
    private Role role;

    //    This from the user details

    @Override
    public String getPassword() {
        return Password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.grantedAuthorities();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
