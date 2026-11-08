package com.taobao.accs.utl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ChannelService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.orange.OrangeConfig;
import com.taobao.orange.OrangeConfigListenerV1;
import com.umeng.message.common.inter.ITagManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class t {
    public static final String NAMESPACE = "accs";
    public static boolean a;
    private static volatile Long b;
    private static Boolean c;
    private static boolean d;

    static {
        try {
            Class.forName("com.taobao.orange.OrangeConfig");
            a = true;
        } catch (Exception unused) {
            a = false;
        }
        b = null;
        c = null;
    }

    public static void a(String[] strArr, OrangeConfigListenerV1 orangeConfigListenerV1) {
        if (a) {
            OrangeConfig.getInstance().registerListener(strArr, orangeConfigListenerV1);
        } else {
            ALog.w("OrangeAdapter", "no orange sdk", new Object[0]);
        }
    }

    public static String a(String str, String str2, String str3) {
        if (a) {
            return OrangeConfig.getInstance().getConfig(str, str2, str3);
        }
        ALog.w("OrangeAdapter", "no orange sdk", new Object[0]);
        return str3;
    }

    public static boolean a() {
        boolean zBooleanValue;
        try {
            zBooleanValue = Boolean.valueOf(a("accs", "main_function_enable", ITagManager.STATUS_TRUE)).booleanValue();
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isAccsEnabled", th, new Object[0]);
            zBooleanValue = true;
        }
        ALog.i("OrangeAdapter", "isAccsEnabled", "enable", Boolean.valueOf(zBooleanValue));
        return zBooleanValue;
    }

    public static boolean b() {
        boolean zA;
        try {
            zA = a(GlobalClientInfo.getContext(), Constants.SP_KEY_HB_SMART_ENABLE, true);
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isSmartHb", th, new Object[0]);
            zA = true;
        }
        ALog.d("OrangeAdapter", "isSmartHb", "result", Boolean.valueOf(zA));
        return zA;
    }

    public static boolean c() {
        boolean zA;
        try {
            zA = a(GlobalClientInfo.getContext(), Constants.SP_KEY_BIND_SERVICE_ENABLE, true);
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isBindService", th, new Object[0]);
            zA = true;
        }
        ALog.d("OrangeAdapter", "isBindService", "result", Boolean.valueOf(zA));
        return zA;
    }

    public static boolean d() {
        boolean zA;
        try {
            zA = a(GlobalClientInfo.getContext(), Constants.SP_KEY_KEEP_ALIVE_ENABLE, true);
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isKeepAlive", th, new Object[0]);
            zA = true;
        }
        ALog.d("OrangeAdapter", "isKeepAlive", "result", Boolean.valueOf(zA));
        return zA;
    }

    public static boolean e() {
        boolean zA;
        try {
            zA = a(GlobalClientInfo.getContext(), Constants.SP_KEY_PULL_UP_ENABLE, true);
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isPullUp", th, new Object[0]);
            zA = true;
        }
        ALog.d("OrangeAdapter", "isPullUp", "result", Boolean.valueOf(zA));
        return zA;
    }

    static long a(Context context) {
        if (b == null) {
            try {
                b = Long.valueOf(context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getLong(Constants.SP_KEY_LAST_LAUNCH_TIME, 0L));
            } catch (Throwable th) {
                ALog.e("OrangeAdapter", "getLastActiveTime", th, new Object[0]);
            }
            ALog.d("OrangeAdapter", "getLastActiveTime", "result", b);
        }
        return b.longValue();
    }

    static void a(Context context, long j) {
        try {
            b = Long.valueOf(j);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            editorEdit.putLong(Constants.SP_KEY_LAST_LAUNCH_TIME, j);
            editorEdit.apply();
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "saveLastActiveTime fail:", th, "lastLaunchTime", Long.valueOf(j));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0018 A[Catch: Throwable -> 0x003a, TryCatch #0 {Throwable -> 0x003a, blocks: (B:5:0x0006, B:6:0x0010, B:8:0x0018, B:9:0x0023), top: B:19:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023 A[Catch: Throwable -> 0x003a, TRY_LEAVE, TryCatch #0 {Throwable -> 0x003a, blocks: (B:5:0x0006, B:6:0x0010, B:8:0x0018, B:9:0x0023), top: B:19:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(boolean r7) {
        /*
            r0 = 0
            r1 = 1
            java.lang.String r2 = "default"
            if (r7 == 0) goto L10
            java.lang.String r7 = "accs"
            java.lang.String r2 = "tnet_log_off"
            java.lang.String r3 = "default"
            java.lang.String r2 = a(r7, r2, r3)     // Catch: java.lang.Throwable -> L3a
        L10:
            java.lang.String r7 = "default"
            boolean r7 = r2.equals(r7)     // Catch: java.lang.Throwable -> L3a
            if (r7 == 0) goto L23
            android.content.Context r7 = com.taobao.accs.client.GlobalClientInfo.getContext()     // Catch: java.lang.Throwable -> L3a
            java.lang.String r2 = "tnet_log_off"
            boolean r7 = a(r7, r2, r1)     // Catch: java.lang.Throwable -> L3a
            goto L46
        L23:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Throwable -> L3a
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L3a
            android.content.Context r2 = com.taobao.accs.client.GlobalClientInfo.getContext()     // Catch: java.lang.Throwable -> L35
            java.lang.String r3 = "tnet_log_off"
            b(r2, r3, r7)     // Catch: java.lang.Throwable -> L35
            goto L46
        L35:
            r2 = move-exception
            r6 = r2
            r2 = r7
            r7 = r6
            goto L3c
        L3a:
            r7 = move-exception
            r2 = 1
        L3c:
            java.lang.String r3 = "OrangeAdapter"
            java.lang.String r4 = "isTnetLogOff"
            java.lang.Object[] r5 = new java.lang.Object[r0]
            com.taobao.accs.utl.ALog.e(r3, r4, r7, r5)
            r7 = r2
        L46:
            java.lang.String r2 = "OrangeAdapter"
            java.lang.String r3 = "isTnetLogOff"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = "result"
            r4[r0] = r5
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r7)
            r4[r1] = r0
            com.taobao.accs.utl.ALog.i(r2, r3, r4)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.utl.t.a(boolean):boolean");
    }

    public static void f() {
        d = true;
        c = false;
        b(GlobalClientInfo.getContext(), Constants.SP_KEY_CHANNEL_MODE_ENABLE, false);
    }

    public static boolean g() {
        if (c == null) {
            try {
                c = Boolean.valueOf(a(GlobalClientInfo.getContext(), Constants.SP_KEY_CHANNEL_MODE_ENABLE, false));
            } catch (Throwable th) {
                ALog.e("OrangeAdapter", "isChannelModeEnable", th, new Object[0]);
            }
            ALog.d("OrangeAdapter", "isChannelModeEnable", "result", c);
        }
        return c.booleanValue();
    }

    private static boolean a(Context context, String str, boolean z) {
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getBoolean(str, z);
        } catch (Exception e) {
            ALog.e("OrangeAdapter", "getConfigFromSP fail:", e, com.ss.union.game.sdk.redemptionCode.a.p, str);
            return z;
        }
    }

    private static void b(Context context, String str, boolean z) {
        try {
        } catch (Exception e) {
            ALog.e("OrangeAdapter", "saveConfigToSP fail:", e, com.ss.union.game.sdk.redemptionCode.a.p, str, "value", Boolean.valueOf(z));
        }
        if (context == null) {
            ALog.e("OrangeAdapter", "saveTLogOffToSP context null", new Object[0]);
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
        ALog.i("OrangeAdapter", "saveConfigToSP", com.ss.union.game.sdk.redemptionCode.a.p, str, "value", Boolean.valueOf(z));
    }

    public static void a(Context context, String str, int i) {
        try {
        } catch (Exception e) {
            ALog.e("OrangeAdapter", "saveConfigToSP fail:", e, com.ss.union.game.sdk.redemptionCode.a.p, str, "value", Integer.valueOf(i));
        }
        if (context == null) {
            ALog.e("OrangeAdapter", "saveTLogOffToSP context null", new Object[0]);
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
        ALog.i("OrangeAdapter", "saveConfigToSP", com.ss.union.game.sdk.redemptionCode.a.p, str, "value", Integer.valueOf(i));
    }

    private static void a(Context context, Map<String, Boolean> map) {
        if (map != null) {
            try {
            } catch (Exception e) {
                ALog.e("OrangeAdapter", "saveConfigsToSP fail:", e, "configs", map.toString());
            }
            if (map.size() == 0) {
                return;
            }
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                editorEdit.putBoolean(entry.getKey(), entry.getValue().booleanValue());
            }
            editorEdit.apply();
            ALog.i("OrangeAdapter", "saveConfigsToSP", "configs", map.toString());
        }
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            SharedPreferences.Editor editorEdit = GlobalClientInfo.getContext().getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
            editorEdit.putString("pullup", str);
            editorEdit.apply();
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "savePullupInfo fail:", th, "pullup", str);
        }
        ALog.i("OrangeAdapter", "savePullupInfo", "pullup", str);
    }

    public static void h() {
        HashMap map = new HashMap();
        map.put(Constants.SP_KEY_TNET_LOG_OFF, Boolean.valueOf(a("accs", Constants.SP_KEY_TNET_LOG_OFF, "false")));
        map.put(Constants.SP_KEY_ELECTION_ENABLE, Boolean.valueOf(a("accs", Constants.SP_KEY_ELECTION_ENABLE, String.valueOf(GlobalClientInfo.d))));
        map.put(Constants.SP_KEY_HB_SMART_ENABLE, Boolean.valueOf(a("accs", "heartbeat_smart_enable", ITagManager.STATUS_TRUE)));
        map.put(Constants.SP_KEY_BIND_SERVICE_ENABLE, Boolean.valueOf(a("accs", Constants.SP_KEY_BIND_SERVICE_ENABLE, ITagManager.STATUS_TRUE)));
        map.put(Constants.SP_KEY_CHANNEL_MODE_ENABLE, Boolean.valueOf(d ? "false" : a("accs", Constants.SP_KEY_CHANNEL_MODE_ENABLE, "false")));
        map.put(Constants.SP_KEY_KEEP_ALIVE_ENABLE, Boolean.valueOf(a("accs", Constants.SP_KEY_KEEP_ALIVE_ENABLE, ITagManager.STATUS_TRUE)));
        map.put(Constants.SP_KEY_PULL_UP_ENABLE, Boolean.valueOf(a("accs", Constants.SP_KEY_PULL_UP_ENABLE, ITagManager.STATUS_TRUE)));
        a(GlobalClientInfo.getContext(), map);
        a(GlobalClientInfo.getContext(), ChannelService.SUPPORT_FOREGROUND_VERSION_KEY, UtilityImpl.b(a("accs", ChannelService.SUPPORT_FOREGROUND_VERSION_KEY, String.valueOf(21))));
        a(a("accs", "pullup", (String) null));
    }

    public static class a implements OrangeConfigListenerV1 {
        public void onConfigUpdate(String str, boolean z) {
            if (GlobalClientInfo.getContext() == null) {
                ALog.e("OrangeAdapter", "onConfigUpdate context null", new Object[0]);
                return;
            }
            try {
                ALog.i("OrangeAdapter", "onConfigUpdate", "namespace", str);
                if ("accs".equals(str)) {
                    t.i();
                    t.h();
                }
            } catch (Throwable th) {
                ALog.e("OrangeAdapter", "onConfigUpdate", th, new Object[0]);
            }
        }
    }

    public static void i() {
        if (a()) {
            if (UtilityImpl.d(GlobalClientInfo.getContext())) {
                ALog.i("OrangeAdapter", "force enable service", new Object[0]);
                ACCSManager.forceEnableService(GlobalClientInfo.getContext());
                return;
            }
            return;
        }
        ALog.e("OrangeAdapter", "force disable service", new Object[0]);
        ACCSManager.forceDisableService(GlobalClientInfo.getContext());
    }
}
