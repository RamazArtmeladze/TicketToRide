package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserModelDto {
    private Long userId;
    private String name;
    private String email;
}
