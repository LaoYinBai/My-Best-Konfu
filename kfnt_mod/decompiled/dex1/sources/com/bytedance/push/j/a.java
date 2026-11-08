package com.bytedance.push.j;

import com.ss.android.b.a.a.a;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements com.bytedance.push.c.b {
    @Override // com.bytedance.push.c.b
    public String a() {
        com.ss.android.b.a.a.a aVarA = com.ss.android.b.a.b.a(com.ss.android.b.a.a.a.class);
        if (aVarA == null) {
            return null;
        }
        return aVarA.a();
    }

    @Override // com.bytedance.push.c.b
    public List<String> b() throws InterruptedException {
        com.ss.android.b.a.a.a aVarA = com.ss.android.b.a.b.a(com.ss.android.b.a.a.a.class);
        if (aVarA == null) {
            return null;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList arrayList = new ArrayList();
        aVarA.a(new a.a() { // from class: com.bytedance.push.j.a.1
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
