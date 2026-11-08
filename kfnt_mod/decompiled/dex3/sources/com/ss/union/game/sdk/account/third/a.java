package com.ss.union.game.sdk.account.third;

import android.content.Context;
import com.bytedance.sdk.open.aweme.TikTokOpenApiFactory;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.ss.union.game.sdk.account.third.activity.TikTokLoginEntryActivity;
import com.ss.union.game.sdk.common.util.NetworkUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a a;
    private InterfaceC0052a b;

    /* renamed from: com.ss.union.game.sdk.account.third.a$a, reason: collision with other inner class name */
    public interface InterfaceC0052a {
        void a(int i, String str);

        void a(String str);
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private a() {
    }

    public void a(Context context, InterfaceC0052a interfaceC0052a) {
        this.b = interfaceC0052a;
        if (!NetworkUtils.isNetworkAvailable()) {
            interfaceC0052a.a(10002, "网络异常，请保持网络连接畅通，再重新操作");
            return;
        }
        Authorization.Request request = new Authorization.Request();
        request.scope = "user_info";
        request.state = "ww";
        request.callerLocalEntry = TikTokLoginEntryActivity.class.getName();
        TikTokOpenApiFactory.create(context, 1).authorize(request);
    }

    public InterfaceC0052a b() {
        return this.b;
    }

    public void c() {
        this.b = null;
    }
}
