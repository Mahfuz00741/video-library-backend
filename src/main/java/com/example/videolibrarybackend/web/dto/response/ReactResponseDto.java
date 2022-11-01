package com.example.videolibrarybackend.web.dto.response;

import com.example.videolibrarybackend.auth.model.domain.User;
import com.example.videolibrarybackend.auth.web.dto.response.UserResponseDto;
import com.example.videolibrarybackend.web.dto.BaseEntityDto;
import lombok.Data;

import javax.persistence.Column;

@Data
public class ReactResponseDto extends BaseEntityDto {

    private Boolean isLike;

    private Boolean isDisLike;

    private Long userId;

    private UserResponseDto user;

}
