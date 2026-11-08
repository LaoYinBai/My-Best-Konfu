package com.ss.android.pushmanager;

import java.util.HashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MessageConstants {
    public static final String BUNDLE_FROM_NOTIFICATION = "from_notification";
    public static final String BUNDLE_MESSAGE_BODY = "push_body";
    public static final String BUNDLE_OPEN_URL = "open_url";
    public static final String KEY_MESSAGE_EXTRA = "message_extra";
    public static final String KEY_MESSAGE_FROM = "message_from";
    public static final String KEY_PUSH_TOKEN_SENT = "push_token_sent";
    public static final String LOG_TYPE = "ss_push";
    public static final String MESSAGE_ACTION = "com.ss.android.message";
    public static final String MESSAGE_KEY_DATA = "message_data";
    public static final String MSG_FROM = "msg_from";
    public static final int MSG_FROM_MESSAGE = 1;
    public static final String MSG_ID = "msg_id";
    public static final String NOTIFY_ACTION = "com.ss.android.message.action.PUSH_SERVICE";
    private static final String SEND_NOTIFY_ENABLE_URL_PATH = "/bytepush/app_notice_status/";
    private static final String SEND_PUSH_TOKEN_URL_PATH = "/bytepush/update_token/";
    public static final String TAG = "MessageAppManager";
    public static final int TYPE_MSG_FROM_NOTIFY = 1;
    public static final int TYPE_MSG_FROM_WINDOW = 2;
    public static final int TYPE_REGISTER_FROM_HEART_BEAT = 4;
    public static final int TYPE_REGISTER_FROM_INIT = 2;
    public static final int TYPE_REGISTER_FROM_RECEIVER = 1;
    public static final int TYPE_REGISTER_FROM_SCHEDULED = 3;
    public static final Set<Integer> TYPE_REGISTER_SET = new HashSet();
    public static final String UNINSTALL_QUESTION_URL_MD5_SALT = "device_info_url_salt_650fa7";
    private static final String UPDATE_SENDER_URL_PATH = "/bytepush/update_sender/";

    static {
        TYPE_REGISTER_SET.add(1);
        TYPE_REGISTER_SET.add(2);
        TYPE_REGISTER_SET.add(3);
        TYPE_REGISTER_SET.add(4);
    }

    public static String getSendPushTokenUrl() {
        return a.a(SEND_PUSH_TOKEN_URL_PATH);
    }

    public static String getSendNotifyEnableUrl() {
        return a.a(SEND_NOTIFY_ENABLE_URL_PATH);
    }

    public static String getUpdateSenderUrl() {
        return a.a(UPDATE_SENDER_URL_PATH);
    }
}
