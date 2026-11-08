package com.umeng.message.entity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UNotificationItem {
    public int id;
    public UMessage message;

    public UNotificationItem(int i, UMessage uMessage) {
        this.id = i;
        this.message = uMessage;
    }

    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass() && this.id == ((UNotificationItem) obj).id;
    }
}
