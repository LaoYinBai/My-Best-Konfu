package com.huawei.hms.aaid.constant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface AaidIdConstant {
    public static final String AAID = "aaid";
    public static final String AAID_FILE = "aaid";
    public static final String CREATE_TIME = "creationTime";
    public static final String DEFAULT_SCOPE_TYPE = "HCM";
    public static final String PUSH_VERSION = "com.huawei.hms.client.service.name:push";
    public static final String SIGNATURE_SHA256 = "SHA-256";
    public static final String deleteToken = "push.deletetoken";
    public static final String getToken = "push.gettoken";

    public interface PushClientSelfInfo {
        public static final String FILE_NAME = "push_client_self_info";
        public static final String HAS_REQUEST_AGREEMENT = "hasRequestAgreement";
        public static final String PUSH_KIT_AUTO_INIT_ENABLED = "push_kit_auto_init_enabled";
        public static final String TOKEN_INFO = "token_info_v2";
    }

    public interface PushLocalSecret {
        public static final String FILE_NAME = "zoo";
        public static final String ROOT_KEY_FIRST = "ant";
        public static final String ROOT_KEY_SALT = "dog";
        public static final String ROOT_KEY_SECOND = "bee";
        public static final String ROOT_KEY_THIRD = "cat";
        public static final String WORK_KEY = "fox";
    }
}
