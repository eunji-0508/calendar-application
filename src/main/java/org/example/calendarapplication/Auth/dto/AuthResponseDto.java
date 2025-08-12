package org.example.calendarapplication.Auth.dto;

import lombok.Getter;

@Getter
public class AuthResponseDto {
    private final Long id;

    public AuthResponseDto(Long id) {
        this.id = id;
    }
}
