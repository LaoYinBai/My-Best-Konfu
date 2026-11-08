package com.sigmob.sdk.common.b;

import java.util.concurrent.ThreadFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a implements ThreadFactory {
    a() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable) { // from class: com.sigmob.sdk.common.b.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
            }
        };
    }
}
