package com.bytedance.sdk.account.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDAccountEvent {
    public static final int EVENT_ACCOUNT_LOGOUT = 1;
    public static final int EVENT_ACCOUNT_REFRESH = 0;
    public static final int EVENT_ACCOUNT_SESSION_EXIPIRED = 2;
    public int logoutScene;
    public boolean success;
    public final int type;

    public BDAccountEvent(int i) {
        this.type = i;
    }
}
