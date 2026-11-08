package com.ss.union.game.sdk.core.base.callback;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GameSDKResult {
    public static final String ERRMSG_PARAMS_ERROR = "参数错误";
    public static final String ERRMSG_SDK_HAS_NOT_INITIALIZED = "SDK还未初始化完成";
    public static final String ERRMSG_SUCCESS = "成功";
    public static final String ERRMSG_UNKNOWN = "系统错误，请稍后再试";
    public static final String ERRMSG_USER_CANCEL = "您好，您已取消操作";
    public static final int ERRNO_PARAMS_ERROR = -203;
    public static final int ERRNO_SDK_HAS_NOT_INITIALIZED = -204;
    public static final int ERRNO_SUCCESS = 0;
    public static final int ERRNO_UNKNOWN = -202;
    public static final int ERRNO_USER_CANCEL = -1004;
    private String errMsg;
    private int errno;
    protected Map<Integer, String> map = new HashMap();
    private String sucMsg;

    public void setSucMsg(String str) {
        this.sucMsg = str;
    }

    public String getSucMsg() {
        return this.sucMsg;
    }

    public void setErrno(int i) {
        this.errno = i;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public int getErrNo() {
        return this.errno;
    }

    public String getErrMsg() {
        if (TextUtils.isEmpty(this.errMsg)) {
            return this.map.get(Integer.valueOf(this.errno)) == null ? "系统错误，请稍后再试" : this.map.get(Integer.valueOf(this.errno));
        }
        return this.errMsg;
    }

    public GameSDKResult() {
        this.map.put(0, "成功");
        this.map.put(-1004, "您好，您已取消操作");
        this.map.put(-202, "系统错误，请稍后再试");
        this.map.put(-203, "参数错误");
        this.map.put(-204, "SDK还未初始化完成");
    }
}
