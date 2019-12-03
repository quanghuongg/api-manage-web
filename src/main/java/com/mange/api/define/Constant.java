package com.mange.api.define;

public class Constant {
    public static final String SDF_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final int FAILED_CODE = 1;
    public static final String LOGIN_FAIL = "Login fail";
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESSFUL_MESSAGE = "Success";
    public static final String OBJECT_EMPTY_FIELD = "Object empty";
    public static final String USER_CREATE_EXISTING = "User name created";
    public static final class SecurityConstant {
        public static final String JWT_SECRET = "nZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRf";
        public static final String TOKEN_HEADER = "Authorization";
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 864000000;
        public static final String AUTHORITIES_KEY = "auth";
    }

    public static final class UserInfo {
        public static final class JsonField {

            public static final String USER_ID = "user_id";
            public static final String USERNAME = "username";
            public static final String FACEBOOK_UID = "facebookUid";
            public static final String GOOGLE_UID = "googleUid";
            public static final String PASSWORD = "password";
            public static final String EMAIL = "email";
            public static final String CREATED_AT = "created_at";
            public static final String UPDATED_AT = "updated_at";
            public static final String FULL_NAME = "full_name";
            public static final String AVATAR = "avatar";
            public static final String ROLE_ID = "role_id";
            public static final String ID_TOKEN = "id_token";
        }
    }
}
