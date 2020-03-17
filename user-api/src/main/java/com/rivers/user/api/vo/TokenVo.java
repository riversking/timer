package com.rivers.user.api.vo;

import lombok.Data;

@Data
public class TokenVo {

    private String accessToken;

    private String tokenType;

    private String refreshToken;

    private String expiresIn;

    private String scope;

    private String username;

    private Integer uid;

    private String avatar;

    private String nickname;
}
