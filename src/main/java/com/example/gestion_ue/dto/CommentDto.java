package com.example.gestion_ue.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class CommentDto {
    @Setter
    @Getter
    private Long id;

    private Long courseId;

    private String commentText;

    private Long userId;

    private UserDto user;

    @Setter
    @Getter
    private Timestamp createdAt;


}

