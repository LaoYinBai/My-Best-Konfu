package com.ss.union.game.sdk.core.base.event;

import com.ss.union.game.sdk.core.base.constant.LGUris;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface INetUploadStrategy {
    public static final String APP_EVENT_LOG_URL = LGUris.path("/game_sdk/log/");
    public static final String KEY_EVENT = "event";
    public static final String KEY_HEADER = "header";
    public static final String KEY_MAGIC = "magic_tag";
    public static final int MAX_CONCURRENT_REQUEST_SIZE = 5;
    public static final int MAX_SINGLE_UPLOAD_EVENT_SIZE = 200;
    public static final int MAX_UPLOAD_RETRY_TIMES = 1;
    public static final int RESULT_HEADER_NOT_READY = -199;
    public static final int RESULT_NO_EVENT = -201;
    public static final int RESULT_NO_NETWORK = -200;
    public static final int RESULT_SEND_OK = 0;

    int handle();
}
