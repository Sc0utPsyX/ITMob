package com.geekbrains.userservice.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@Entity
@Table(name = "privacy_settings")
public class PrivacySetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "show_age", nullable = false)
    Boolean showAge = true;

    @Column(name = "open_profile", nullable = false)
    Boolean openProfile = true;

    /**
     * Получать сообщения от подписчиков
     */
    @Column(name = "invitation_subscribers", nullable = false)
    Boolean getInvitationFromSubscribers = true;

    /**
     * Получать сообщения от подписок
     */
    @Column(name = "invitation_subscriptions", nullable = false)
    Boolean getInvitationFromSubscriptions = true;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "privacySetting")
    User user;

}
