package com.mange.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EnityName {

    private Integer id;

    private String username;

    private String display_name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;

    private String address;

    private String phone;

    private Long expired;

    private int status;

    private Long created;

    private Long updated;


}
