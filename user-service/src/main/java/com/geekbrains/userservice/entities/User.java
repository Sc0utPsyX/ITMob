package com.geekbrains.userservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(schema = "itmob", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @OneToOne
    @JoinColumn(name = "id")
    private UserDetails userDetails;

    @OneToOne
    @JoinColumn(name = "privacy_setting_id")
    private PrivacySetting privacySetting;

    @ManyToMany
    @JoinTable(
            schema = "itmob",
            name = "user_rights",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "right_id")
    )
    private List<Right> rights;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    // TODO: 15.07.2023 listen to userDetails changes too
    @Column(name = "modify_date")
    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @Column(name = "reg_confirmed")
    private Boolean regConfirmed;

    @Column(name = "active")
    private Boolean active;

    public User.Builder builder() {
        return this.new Builder();
    }

    public class Builder {

        private Builder() {}

        public User.Builder setId(Long id) {
            User.this.id = id;
            return this;
        }

        public User.Builder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public User.Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public User.Builder setUsername(String username) {
            User.this.username = username;
            return this;
        }

        public User.Builder setUserDetails(UserDetails userDetails) {
            User.this.userDetails = userDetails;
            return this;
        }

        public User.Builder setRights(List<Right> rights) {
            User.this.rights = rights;
            return this;
        }

        public User.Builder setCreateDate(LocalDateTime createDate) {
            User.this.createDate = createDate;
            return this;
        }

        public User.Builder setModifyDate(LocalDateTime modifyDate) {
            User.this.modifyDate = modifyDate;
            return this;
        }

        public User.Builder setRegConfirmed(boolean regConfirmed) {
            User.this.regConfirmed = regConfirmed;
            return this;
        }

        public User.Builder setActive(boolean active) {
            User.this.active = active;
            return this;
        }

        public User.Builder setPrivacySetting(PrivacySetting privacySetting) {
            User.this.privacySetting = privacySetting;
            return this;
        }

        public User build() {
            return User.this;
        }

    }

}
