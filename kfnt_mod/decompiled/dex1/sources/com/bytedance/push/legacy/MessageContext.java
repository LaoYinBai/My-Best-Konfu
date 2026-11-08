package com.bytedance.push.legacy;

import android.content.Context;
import com.bytedance.push.d;
import com.ss.android.pushmanager.IMessageContext;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MessageContext implements IMessageContext {
    private final d mConfiguration;

    public MessageContext(d dVar) {
        this.mConfiguration = dVar;
    }

    public Context getContext() {
        return this.mConfiguration.f4770a;
    }

    public String getAppName() {
        return this.mConfiguration.i;
    }

    public int getAid() {
        return this.mConfiguration.f4771b;
    }

    public String getTweakedChannel() {
        return this.mConfiguration.g;
    }

    public String getVersion() {
        return this.mConfiguration.e;
    }

    public int getVersionCode() {
        return this.mConfiguration.f4772c;
    }

    public int getUpdateVersionCode() {
        return this.mConfiguration.d;
    }
}
