package com.bytedance.a;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.profile.UserProfileCallback;
import com.bytedance.common.utility.NetworkUtils;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ba implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public static final Handler f3391a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    public String f3392b;

    /* renamed from: c, reason: collision with root package name */
    public String f3393c;
    public String d;
    public final UserProfileCallback e;
    public Context f;

    public ba(String str, String str2, String str3, UserProfileCallback userProfileCallback, Context context) {
        this.f3392b = str;
        this.f3393c = str2;
        this.d = str3;
        this.e = userProfileCallback;
        this.f = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!NetworkUtils.isNetworkAvailable(this.f)) {
                f3391a.post(new aq(this, 0));
                return;
            }
            HashMap map = new HashMap();
            map.put("Content-Type", com.baidu.mobads.sdk.internal.ae.d);
            map.put("X-APIKEY", this.f3393c);
            AppLog.getNetClient().post(this.f3392b, this.d.getBytes(), map);
            f3391a.post(new av(this));
        } catch (Throwable th) {
            de.a("", th);
            f3391a.post(new aq(this, 1));
        }
    }
}
