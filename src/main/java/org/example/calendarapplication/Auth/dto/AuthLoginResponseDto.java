package org.example.calendarapplication.Auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponseDto {
    private final Long id;

    public AuthLoginResponseDto(Long id) {
        this.id = id;
    }
}
