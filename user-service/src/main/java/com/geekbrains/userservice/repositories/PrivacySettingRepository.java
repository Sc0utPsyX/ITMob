package com.geekbrains.userservice.repositories;
import com.geekbrains.userservice.entities.PrivacySetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacySettingRepository extends JpaRepository<PrivacySetting, Long> {
}
