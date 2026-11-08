package com.bytedance.push.h;

import android.util.Pair;
import com.bytedance.common.push.utility.f;
import com.bytedance.push.PushBody;
import com.bytedance.push.i;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final PushBody f4840a;

    /* renamed from: b, reason: collision with root package name */
    private final int f4841b;

    public e(int i, PushBody pushBody) {
        this.f4841b = i;
        this.f4840a = pushBody;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.bytedance.push.utils.e.d("Show", "start to upload filter event");
        com.bytedance.push.c.b bVar = i.j().d().x;
        String str = this.f4840a.targetSecUid;
        String strA = "";
        com.bytedance.push.e.c cVarA = b.a(com.ss.android.message.a.a(), this.f4841b);
        String str2 = cVarA != null ? cVarA.f4794b : "";
        com.bytedance.push.utils.e.d("Show", "token info = " + cVarA);
        if (bVar != null && (strA = bVar.a()) == null) {
            strA = "";
        }
        String strA2 = com.ss.android.pushmanager.a.a("/cloudpush/user_push_replace/");
        com.ss.android.message.a.b.a(strA2, i.j().a());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("ttpush_sec_target_uid", str));
        arrayList.add(new Pair("local_sec_uid", strA));
        arrayList.add(new Pair("send_sdk", String.valueOf(this.f4841b)));
        arrayList.add(new Pair("token", str2));
        arrayList.add(new Pair("rid64", String.valueOf(this.f4840a.rid64)));
        try {
            com.bytedance.push.utils.e.c("Show", "upload filter event. result = " + f.a().a(strA2, arrayList));
        } catch (Throwable th) {
            com.bytedance.push.utils.e.b("Show", "upload filter event. result = " + th);
            th.printStackTrace();
        }
    }
}
