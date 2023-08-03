package com.geekbrains.userservice.entities;

import com.geekbrains.userservice.models.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "about")
    private String about;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Gender sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;

    public UserDetails.Builder builder() {
        return this.new Builder();
    }

    public class Builder {

        private Builder() {}

        public UserDetails.Builder setUserId(Long userId) {
            UserDetails.this.userId = userId;
            return this;
        }

        public UserDetails.Builder setAbout(String about) {
            UserDetails.this.about = about;
            return this;
        }

        public UserDetails.Builder setAddress(String address) {
            UserDetails.this.address = address;
            return this;
        }

        public UserDetails.Builder setCity(String city) {
            UserDetails.this.city = city;
            return this;
        }

        public UserDetails.Builder setSex(Gender sex) {
            UserDetails.this.sex = sex;
            return this;
        }

        public UserDetails.Builder setBirthDate(LocalDate birthDate) {
            UserDetails.this.birthDate = birthDate;
            return this;
        }

        public UserDetails.Builder setEmail(String email) {
            UserDetails.this.email = email;
            return this;
        }

        public UserDetails build() {
            return UserDetails.this;
        }

    }

}
