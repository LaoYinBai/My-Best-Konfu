package anetwork.channel.entity;

import java.util.concurrent.ThreadFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements ThreadFactory {
    c() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, String.format("RepeaterThread:%d", Integer.valueOf(b.f686b.getAndIncrement())));
    }
}
