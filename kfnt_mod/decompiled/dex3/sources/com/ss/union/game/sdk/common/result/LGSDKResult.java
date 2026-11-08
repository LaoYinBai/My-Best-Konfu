package com.ss.union.game.sdk.common.result;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGSDKResult {
    public static final String ERRMSG_PARAMS_ERROR = "参数错误";
    public static final String ERRMSG_SDK_HAS_NOT_INITIALIZED = "SDK还未初始化完成";
    public static final String ERRMSG_SUCCESS = "成功";
    public static final String ERRMSG_UNKNOWN = "系统错误，请稍后再试";
    public static final String ERRMSG_USER_CANCEL = "您好，您已取消操作";
    public static final int ERRNO_PARAMS_ERROR = -203;
    public static final int ERRNO_SDK_HAS_NOT_INITIALIZED = -204;
    public static final int ERRNO_SDK_NO_NETWORK = -205;
    public static final int ERRNO_SUCCESS = 0;
    public static final int ERRNO_UNKNOWN = -202;
    public static final int ERRNO_USER_CANCEL = -1004;
    public static final String ERROR_MESSAGE_SDK_NO_NETWORK = "请检查网络连接";
    public static final int ERROR_NO_LOGIN = -206;
    public static final String ERROR_NO_LOGIN_MSG = "当前为未登录状态";
    public static final int ERROR_NO_NETWORK_V2 = 100;
    public static final String ERROR_NO_NETWORK_V2_MESSAGE = "网络异常，请保持网络连接畅通，再重新操作";
    private final int a;
    private final String b;

    public LGSDKResult(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public int getCode() {
        return this.a;
    }

    public String getMsg() {
        return this.b;
    }
}
