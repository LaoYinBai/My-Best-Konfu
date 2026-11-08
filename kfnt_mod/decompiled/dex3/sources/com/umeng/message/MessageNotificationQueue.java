package com.umeng.message;

import android.annotation.TargetApi;
import com.umeng.message.entity.UNotificationItem;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MessageNotificationQueue {
    private static MessageNotificationQueue b;
    private LinkedList<UNotificationItem> a = new LinkedList<>();

    private MessageNotificationQueue() {
    }

    public static synchronized MessageNotificationQueue getInstance() {
        if (b == null) {
            b = new MessageNotificationQueue();
        }
        return b;
    }

    public void addLast(UNotificationItem uNotificationItem) {
        this.a.addLast(uNotificationItem);
    }

    @TargetApi(9)
    public UNotificationItem pollFirst() {
        return this.a.pollFirst();
    }

    public void remove(UNotificationItem uNotificationItem) {
        this.a.remove(uNotificationItem);
    }

    public int size() {
        return this.a.size();
    }

    public LinkedList<UNotificationItem> getQueue() {
        return this.a;
    }
}
