package com.geekbrains.userservice.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import lombok.*;
import org.springframework.stereotype.Service;

public enum Gender {
    F,
    M
}
