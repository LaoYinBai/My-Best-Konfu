package com.bytedance.sdk.account.network.dispatcher;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IRequest {

    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    Priority getPriority();

    int getSequence();
}
