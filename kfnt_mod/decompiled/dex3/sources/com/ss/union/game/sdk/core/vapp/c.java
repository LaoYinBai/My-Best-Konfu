package com.ss.union.game.sdk.core.vapp;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final String a = "VAppUtils";
    private static final String b = "lg_float_ball_times_sp";
    private static volatile int c;

    public interface a {
        void a(int i, String str);

        void a(String str);
    }

    public static int a() {
        return c;
    }

    public static boolean b() {
        return ConfigManager.DanJuanConfig.isOpenFloatBall();
    }

    public static void a(int i) {
        try {
            String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
            SPUtils.getInstance(b).clear();
            SPUtils.getInstance(b).put(str, i);
        } catch (Exception unused) {
        }
    }

    public static int c() {
        try {
            return SPUtils.getInstance(b).getInt(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())), 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    private static boolean h() {
        return (ConfigManager.LoginConfig.isNoUserLogin() || TextUtils.isEmpty(LGAccountDataUtil.getCurrentUserOpenId())) ? false : true;
    }

    public static void d() {
        if (!h()) {
            e();
        } else {
            f();
        }
    }

    public static void e() {
        if (!b()) {
            VLog.d(a, "没有开启广告卷功能，跳过");
        } else {
            if (h()) {
                return;
            }
            AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.core.vapp.c.1
                @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
                public void onIdChanged(String str, String str2) {
                    c.b(str, str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, String str2) {
        c = 0;
        com.ss.union.game.sdk.core.vapp.b.a.a(str, str2, new com.ss.union.game.sdk.core.vapp.b.a.d() { // from class: com.ss.union.game.sdk.core.vapp.c.2
            @Override // com.ss.union.game.sdk.core.vapp.b.a.a
            public void a(com.ss.union.game.sdk.core.vapp.a.d dVar) {
                for (com.ss.union.game.sdk.core.vapp.a.c cVar : dVar.d) {
                    if (cVar.b == 1) {
                        VLog.d(c.a, "refreshDeviceVUserInfoAsync success，广告卷数量:" + cVar.f);
                        int unused = c.c = cVar.f;
                        return;
                    }
                }
            }

            @Override // com.ss.union.game.sdk.core.vapp.b.a.a
            public void a(int i, String str3, @Nullable JSONObject jSONObject) {
                VLog.d(c.a, "refreshDeviceVUserInfoAsync fail " + str3);
            }
        });
    }

    public static void f() {
        if (!b()) {
            VLog.d(a, "没有开启广告卷功能，跳过");
            return;
        }
        if (!h()) {
            VLog.d(a, "没有账号id，返回!!!");
            return;
        }
        String currentUserOpenId = LGAccountDataUtil.getCurrentUserOpenId();
        String loginToken = LGAccountDataUtil.getLoginToken();
        if (TextUtils.isEmpty(currentUserOpenId) || TextUtils.isEmpty(loginToken)) {
            VLog.d("refreshAccountVUserInfo,用户信息为null： openId:" + currentUserOpenId + ",token:" + loginToken);
            return;
        }
        c = 0;
        com.ss.union.game.sdk.core.vapp.b.a.b(currentUserOpenId, loginToken, new com.ss.union.game.sdk.core.vapp.b.a.d() { // from class: com.ss.union.game.sdk.core.vapp.c.3
            @Override // com.ss.union.game.sdk.core.vapp.b.a.a
            public void a(com.ss.union.game.sdk.core.vapp.a.d dVar) {
                for (com.ss.union.game.sdk.core.vapp.a.c cVar : dVar.d) {
                    if (cVar.b == 1) {
                        int unused = c.c = cVar.f;
                        if (c.c == 0) {
                            VLog.d("切换帐号或者其他，查询到广告卷的数量是0，悬浮球主动退出广告模式");
                            LGFloatBallUtils.exitAdVolumeMode();
                            return;
                        }
                        return;
                    }
                }
            }

            @Override // com.ss.union.game.sdk.core.vapp.b.a.a
            public void a(int i, String str, @Nullable JSONObject jSONObject) {
                VLog.d(c.a, "refreshAccountVUserInfo fail " + str);
                VLog.d("切换帐号或者其他，查询到广告卷的数量是0，悬浮球主动退出广告模式");
                LGFloatBallUtils.exitAdVolumeMode();
            }
        });
    }

    public static void a(a aVar) {
        a(String.valueOf(1), aVar);
    }

    public static void a(final String str, final a aVar) {
        if (!h()) {
            AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.core.vapp.c.4
                @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
                public void onIdChanged(String str2, String str3) {
                    com.ss.union.game.sdk.core.vapp.b.a.c(str2, str3, str, new b(aVar, str));
                }
            });
            return;
        }
        String currentUserOpenId = LGAccountDataUtil.getCurrentUserOpenId();
        String loginToken = LGAccountDataUtil.getLoginToken();
        if (TextUtils.isEmpty(currentUserOpenId) || TextUtils.isEmpty(loginToken)) {
            VLog.d("requestConsumeVTool,用户信息为null： openId:" + currentUserOpenId + ",token:" + loginToken);
            return;
        }
        com.ss.union.game.sdk.core.vapp.b.a.d(currentUserOpenId, loginToken, str, new b(aVar, str));
    }

    private static class b extends com.ss.union.game.sdk.core.vapp.b.a.c {
        private a a;
        private String b;

        b(a aVar, String str) {
            this.a = aVar;
            this.b = str;
        }

        @Override // com.ss.union.game.sdk.core.vapp.b.a.a
        public void a(com.ss.union.game.sdk.core.vapp.a.b bVar) {
            if (bVar.b == 1) {
                int unused = c.c = bVar.e;
                VLog.d("剩余广告卷数量是： " + c.c);
            }
            this.a.a(this.b);
        }

        @Override // com.ss.union.game.sdk.core.vapp.b.a.a
        public void a(int i, String str, @Nullable JSONObject jSONObject) {
            this.a.a(i, str);
        }
    }
}
