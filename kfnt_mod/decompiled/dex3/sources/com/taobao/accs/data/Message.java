package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.o;
import com.taobao.accs.utl.p;
import com.taobao.accs.utl.r;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Message implements Serializable {
    public static int CONTROL_MAX_RETRY_TIMES = 5;
    public static final int EXT_HEADER_VALUE_MAX_LEN = 1023;
    public static final int FLAG_ACK_TYPE = 32;
    public static final int FLAG_BIZ_RET = 64;
    public static final int FLAG_DATA_TYPE = 32768;
    public static final int FLAG_ERR = 4096;
    public static final int FLAG_REQ_BIT1 = 16384;
    public static final int FLAG_REQ_BIT2 = 8192;
    public static final int FLAG_RET = 2048;
    public static final String KEY_BINDAPP = "ctrl_bindapp";
    public static final String KEY_BINDSERVICE = "ctrl_bindservice";
    public static final String KEY_BINDUSER = "ctrl_binduser";
    public static final String KEY_UNBINDAPP = "ctrl_unbindapp";
    public static final String KEY_UNBINDSERVICE = "ctrl_unbindservice";
    public static final String KEY_UNBINDUSER = "ctrl_unbinduser";
    public static final int MAX_RETRY_TIMES = 3;
    static long a = 1;
    byte[] D;
    int E;
    long F;
    transient NetPerformanceMonitor G;
    Id I;
    public String cunstomDataId;
    short d;
    public String dataId;
    short e;
    short f;
    byte g;
    byte h;
    public URL host;
    String i;
    String j;
    Map<Integer, String> l;
    public long startSendTime;
    public boolean isAck = false;
    public boolean force = false;
    public boolean isCancel = false;
    byte b = 0;
    byte c = 0;
    int k = -1;
    String m = null;
    public Integer command = null;
    Integer n = 0;
    String o = null;
    public String appSign = null;
    Integer p = null;
    String q = null;
    String r = null;
    String s = null;
    String t = null;
    String u = null;
    Integer v = null;
    String w = null;
    String x = null;
    public String userinfo = null;
    public String serviceId = null;
    String y = null;
    String z = null;
    String A = null;
    String B = null;
    String C = null;
    public long delyTime = 0;
    public int retryTimes = 0;
    public int timeout = com.taobao.accs.net.b.ACCS_RECEIVE_TIMEOUT;
    public String bizId = null;
    String H = null;

    public static class MsgResType implements Serializable {
        public static final int INVALID = -1;
        public static final int NEED_ACK = 1;
        public static final int NO_ACK = 0;

        public static String name(int i) {
            switch (i) {
                case 0:
                    return "NO_ACK";
                case 1:
                    return "NEED_ACK";
                default:
                    return "INVALID";
            }
        }

        public static int valueOf(int i) {
            switch (i) {
            }
            return 1;
        }
    }

    public static class MsgType implements Serializable {
        public static final int CONTROL = 0;
        public static final int DATA = 1;
        public static final int HANDSHAKE = 3;
        public static final int INVALID = -1;
        public static final int PING = 2;

        public static String name(int i) {
            switch (i) {
                case 0:
                    return "CONTROL";
                case 1:
                    return "DATA";
                case 2:
                    return "PING";
                case 3:
                    return "HANDSHAKE";
                default:
                    return "INVALID";
            }
        }

        public static int valueOf(int i) {
            switch (i) {
            }
            return 0;
        }
    }

    public enum ReqType {
        DATA,
        ACK,
        REQ,
        RES;

        public static ReqType valueOf(int i) {
            switch (i) {
                case 0:
                    return DATA;
                case 1:
                    return ACK;
                case 2:
                    return REQ;
                case 3:
                    return RES;
                default:
                    return DATA;
            }
        }
    }

    public static class Id implements Serializable {
        private int a;
        private String b;

        public Id(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public int getId() {
            return this.a;
        }

        public String getDataId() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Id id = (Id) obj;
            return this.a == id.getId() || this.b.equals(id.getDataId());
        }

        public int hashCode() {
            return this.b.hashCode();
        }
    }

    private Message() {
        synchronized (Message.class) {
            this.startSendTime = System.currentTimeMillis();
            this.dataId = String.valueOf(this.startSendTime) + "." + String.valueOf(a);
            long j = a;
            a = 1 + j;
            this.I = new Id((int) j, this.dataId);
        }
    }

    public int getNode() {
        return this.E;
    }

    public int getType() {
        return this.k;
    }

    public String getDataId() {
        return this.dataId;
    }

    public boolean isControlFrame() {
        return Constants.TARGET_CONTROL.equals(this.i);
    }

    public Id getMsgId() {
        return this.I;
    }

    public void setSendTime(long j) {
        this.F = j;
    }

    public NetPerformanceMonitor getNetPermanceMonitor() {
        return this.G;
    }

    public long getDelyTime() {
        return this.delyTime;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    private String c() {
        return "Msg_" + this.H;
    }

    public String getPackageName() {
        return this.m == null ? "" : this.m;
    }

    public boolean isTimeOut() {
        boolean z = (System.currentTimeMillis() - this.startSendTime) + this.delyTime >= ((long) this.timeout);
        if (z) {
            ALog.e(c(), "delay time:" + this.delyTime + " beforeSendTime:" + (System.currentTimeMillis() - this.startSendTime) + " timeout" + this.timeout, new Object[0]);
        }
        return z;
    }

    public byte[] build(Context context, int i) throws Throwable {
        byte[] bytes;
        try {
            b();
        } catch (UnsupportedEncodingException e) {
            ALog.e(c(), "build2", e, new Object[0]);
        } catch (JSONException e2) {
            ALog.e(c(), "build1", e2, new Object[0]);
        }
        String str = this.D != null ? new String(this.D) : "";
        a();
        if (!this.isAck) {
            StringBuilder sb = new StringBuilder();
            sb.append(UtilityImpl.l(context));
            sb.append("|");
            sb.append(this.m);
            sb.append("|");
            sb.append(this.serviceId == null ? "" : this.serviceId);
            sb.append("|");
            sb.append(this.userinfo == null ? "" : this.userinfo);
            this.j = sb.toString();
        }
        try {
            bytes = (this.dataId + "").getBytes("utf-8");
            this.h = (byte) this.j.getBytes("utf-8").length;
            this.g = (byte) this.i.getBytes("utf-8").length;
        } catch (Exception e3) {
            e3.printStackTrace();
            ALog.e(c(), "build3", e3, new Object[0]);
            bytes = (this.dataId + "").getBytes();
            this.h = (byte) this.j.getBytes().length;
            this.g = (byte) this.i.getBytes().length;
        }
        short sA = a(this.l);
        this.e = (short) (this.g + 3 + 1 + this.h + 1 + bytes.length + (this.D == null ? 0 : this.D.length) + sA + 2);
        this.d = (short) (this.e + 2);
        r rVar = new r(this.d + 2 + 4);
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d(c(), "Build Message", Constants.KEY_DATA_ID, new String(bytes));
        }
        try {
            rVar.a((byte) (this.b | 32));
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\tversion:2 compress:" + ((int) this.b), new Object[0]);
            }
            if (i == 0) {
                rVar.a((byte) -128);
                if (ALog.isPrintLog(ALog.Level.D)) {
                    ALog.d(c(), "\tflag: 0x80", new Object[0]);
                }
            } else {
                rVar.a((byte) 64);
                if (ALog.isPrintLog(ALog.Level.D)) {
                    ALog.d(c(), "\tflag: 0x40", new Object[0]);
                }
            }
            rVar.a(this.d);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\ttotalLength:" + ((int) this.d), new Object[0]);
            }
            rVar.a(this.e);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\tdataLength:" + ((int) this.e), new Object[0]);
            }
            rVar.a(this.f);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\tflags:" + Integer.toHexString(this.f), new Object[0]);
            }
            rVar.a(this.g);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\ttargetLength:" + ((int) this.g), new Object[0]);
            }
            rVar.write(this.i.getBytes("utf-8"));
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\ttarget:" + this.i, new Object[0]);
            }
            rVar.a(this.h);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\tsourceLength:" + ((int) this.h), new Object[0]);
            }
            rVar.write(this.j.getBytes("utf-8"));
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\tsource:" + this.j, new Object[0]);
            }
            rVar.a((byte) bytes.length);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\tdataIdLength:" + bytes.length, new Object[0]);
            }
            rVar.write(bytes);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\tdataId:" + new String(bytes), new Object[0]);
            }
            rVar.a(sA);
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\textHeader len:" + ((int) sA), new Object[0]);
            }
            if (this.l != null) {
                Iterator<Integer> it = this.l.keySet().iterator();
                while (it.hasNext()) {
                    int iIntValue = it.next().intValue();
                    String str2 = this.l.get(Integer.valueOf(iIntValue));
                    if (!TextUtils.isEmpty(str2)) {
                        rVar.a((short) ((((short) iIntValue) << 10) | ((short) (str2.getBytes("utf-8").length & 1023))));
                        rVar.write(str2.getBytes("utf-8"));
                        if (ALog.isPrintLog(ALog.Level.D)) {
                            ALog.d(c(), "\textHeader key:" + iIntValue + " value:" + str2, new Object[0]);
                        }
                    }
                }
            }
            if (this.D != null) {
                rVar.write(this.D);
            }
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(c(), "\toriData:" + str, new Object[0]);
            }
            rVar.flush();
        } catch (IOException e4) {
            ALog.e(c(), "build4", e4, new Object[0]);
        }
        byte[] byteArray = rVar.toByteArray();
        try {
            rVar.close();
        } catch (IOException e5) {
            ALog.e(c(), "build5", e5, new Object[0]);
        }
        return byteArray;
    }

    short a(Map<Integer, String> map) {
        short length = 0;
        if (map != null) {
            try {
                Iterator<Integer> it = map.keySet().iterator();
                while (it.hasNext()) {
                    String str = map.get(Integer.valueOf(it.next().intValue()));
                    if (!TextUtils.isEmpty(str)) {
                        length = (short) (length + ((short) (str.getBytes("utf-8").length & 1023)) + 2);
                    }
                }
            } catch (Exception e) {
                e.toString();
            }
        }
        return length;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0064 A[Catch: Exception -> 0x0067, TRY_LEAVE, TryCatch #4 {Exception -> 0x0067, blocks: (B:34:0x005f, B:36:0x0064), top: B:44:0x005f }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a() throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            byte[] r1 = r6.D     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L41
            if (r1 != 0) goto L6
            return
        L6:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L41
            r1.<init>()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L41
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L37
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L37
            byte[] r0 = r6.D     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            r2.write(r0)     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            r2.finish()     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            byte[] r0 = r1.toByteArray()     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            if (r0 == 0) goto L29
            int r3 = r0.length     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            byte[] r4 = r6.D     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            int r4 = r4.length     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            if (r3 >= r4) goto L29
            r6.D = r0     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
            r0 = 1
            r6.b = r0     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L5c
        L29:
            r2.close()     // Catch: java.lang.Exception -> L5b
        L2c:
            r1.close()     // Catch: java.lang.Exception -> L5b
            goto L5b
        L30:
            r0 = move-exception
            goto L45
        L32:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L5d
        L37:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L45
        L3c:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L5d
        L41:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L45:
            java.lang.String r3 = r6.c()     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> L5c
            android.util.Log.e(r3, r4)     // Catch: java.lang.Throwable -> L5c
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L5c
            if (r2 == 0) goto L58
            r2.close()     // Catch: java.lang.Exception -> L5b
        L58:
            if (r1 == 0) goto L5b
            goto L2c
        L5b:
            return
        L5c:
            r0 = move-exception
        L5d:
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.lang.Exception -> L67
        L62:
            if (r1 == 0) goto L67
            r1.close()     // Catch: java.lang.Exception -> L67
        L67:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.data.Message.a():void");
    }

    void b() throws JSONException, UnsupportedEncodingException {
        if (this.command == null || this.command.intValue() == 100 || this.command.intValue() == 102) {
            return;
        }
        this.D = new p.a().a(Constants.KEY_COMMAND, this.command.intValue() == 100 ? null : this.command).a(Constants.KEY_APP_KEY, this.o).a(Constants.KEY_OS_TYPE, this.p).a("sign", this.appSign).a(Constants.KEY_SDK_VERSION, this.v).a(Constants.KEY_APP_VERSION, this.u).a(Constants.KEY_TTID, this.w).a(Constants.KEY_MODEL, this.y).a("brand", this.z).a(Constants.KEY_IMEI, this.A).a(Constants.KEY_IMSI, this.B).a("os", this.q).a(Constants.KEY_EXTS, this.t).a().toString().getBytes("utf-8");
    }

    public static Message BuildPing(boolean z, int i) {
        Message message = new Message();
        message.k = 2;
        message.command = 201;
        message.force = z;
        message.delyTime = i;
        return message;
    }

    public static Message buildForeground(String str) {
        Message message = new Message();
        message.a(1, ReqType.DATA, 0);
        message.command = 100;
        message.i = Constants.TARGET_FORE;
        a(str, message);
        return message;
    }

    public static Message buildBackground(String str) {
        Message message = new Message();
        message.a(1, ReqType.DATA, 0);
        message.command = 100;
        message.i = Constants.TARGET_BACK;
        a(str, message);
        return message;
    }

    public static Message buildHandshake(String str) {
        Message message = new Message();
        message.a(3, ReqType.DATA, 1);
        message.m = str;
        message.i = Constants.TARGET_CONTROL;
        message.command = 200;
        return message;
    }

    @Deprecated
    public static Message buildBindApp(com.taobao.accs.net.b bVar, Context context, Intent intent) {
        return buildBindApp(bVar.b((String) null), bVar.m, context, intent);
    }

    public static Message buildBindApp(String str, String str2, Context context, Intent intent) {
        Message messageBuildBindApp;
        try {
            messageBuildBindApp = buildBindApp(context, str2, intent.getStringExtra(Constants.KEY_APP_KEY), intent.getStringExtra("app_sercet"), intent.getStringExtra("packageName"), intent.getStringExtra(Constants.KEY_TTID), intent.getStringExtra(Constants.KEY_APP_VERSION));
        } catch (Exception e) {
            e = e;
            messageBuildBindApp = null;
        }
        try {
            a(str, messageBuildBindApp);
        } catch (Exception e2) {
            e = e2;
            ALog.e("Msg", "buildBindApp", e.getMessage());
            return messageBuildBindApp;
        }
        return messageBuildBindApp;
    }

    @Deprecated
    public static Message buildBindApp(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        return buildBindApp(context, str, str2, str3, str4, str5, str6);
    }

    public static Message buildBindApp(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str4)) {
            return null;
        }
        Message message = new Message();
        message.E = 1;
        message.a(1, ReqType.DATA, 1);
        message.p = 1;
        message.q = Build.VERSION.SDK_INT + "";
        message.m = str4;
        message.i = Constants.TARGET_CONTROL;
        message.command = 1;
        message.o = str2;
        message.appSign = UtilityImpl.a(context, str2, str3, UtilityImpl.l(context), str);
        message.v = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.u = str6;
        message.m = str4;
        message.w = str5;
        message.y = Build.MODEL;
        message.z = Build.BRAND;
        message.cunstomDataId = KEY_BINDAPP;
        message.H = str;
        message.t = new p.a().a("notifyEnable", UtilityImpl.r(context)).a("romInfo", new o().a()).a().toString();
        UtilityImpl.a(context, Constants.SP_FILE_NAME, UtilityImpl.r(context));
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            message.A = telephonyManager != null ? telephonyManager.getDeviceId() : null;
            message.B = telephonyManager != null ? telephonyManager.getSubscriberId() : null;
        } catch (Throwable th) {
            ALog.w("Msg", "buildBindApp imei", th.getMessage());
        }
        return message;
    }

    @Deprecated
    public static Message buildUnbindApp(com.taobao.accs.net.b bVar, Context context, Intent intent) {
        return buildUnbindApp(bVar.b((String) null), intent);
    }

    public static Message buildUnbindApp(String str, Intent intent) {
        Message messageBuildUnbindApp;
        ALog.e("Msg", "buildUnbindApp1" + UtilityImpl.a(new Exception()), new Object[0]);
        try {
            messageBuildUnbindApp = buildUnbindApp(str, intent.getStringExtra("packageName"));
        } catch (Exception e) {
            e = e;
            messageBuildUnbindApp = null;
        }
        try {
            a(str, messageBuildUnbindApp);
        } catch (Exception e2) {
            e = e2;
            ALog.e("Msg", "buildUnbindApp1", e.getMessage());
            return messageBuildUnbindApp;
        }
        return messageBuildUnbindApp;
    }

    @Deprecated
    public static Message buildUnbindApp(com.taobao.accs.net.b bVar, Context context, String str, String str2, String str3, String str4) {
        return buildUnbindApp(bVar.b((String) null), str);
    }

    public static Message buildUnbindApp(String str, String str2) {
        Message message;
        try {
            ALog.d("Msg", "buildUnbindApp", new Object[0]);
        } catch (Exception e) {
            e = e;
            message = null;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        message = new Message();
        try {
            message.E = 1;
            message.a(1, ReqType.DATA, 1);
            message.m = str2;
            message.i = Constants.TARGET_CONTROL;
            message.command = 2;
            message.m = str2;
            message.v = Integer.valueOf(Constants.SDK_VERSION_CODE);
            message.cunstomDataId = KEY_UNBINDAPP;
            a(str, message);
        } catch (Exception e2) {
            e = e2;
            ALog.e("Msg", "buildUnbindApp", e.getMessage());
            return message;
        }
        return message;
    }

    @Deprecated
    public static Message buildBindService(com.taobao.accs.net.b bVar, Context context, Intent intent) {
        return buildBindService(bVar.b((String) null), bVar.m, intent);
    }

    public static Message buildBindService(String str, String str2, Intent intent) {
        Message messageBuildBindService;
        try {
            messageBuildBindService = buildBindService(intent.getStringExtra("packageName"), intent.getStringExtra(Constants.KEY_SERVICE_ID));
        } catch (Exception e) {
            e = e;
            messageBuildBindService = null;
        }
        try {
            messageBuildBindService.H = str2;
            a(str, messageBuildBindService);
        } catch (Exception e2) {
            e = e2;
            ALog.e("Msg", "buildBindService", e, new Object[0]);
            e.printStackTrace();
            return messageBuildBindService;
        }
        return messageBuildBindService;
    }

    @Deprecated
    public static Message buildBindService(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        return buildBindService(str, str3);
    }

    public static Message buildBindService(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Message message = new Message();
        message.E = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.serviceId = str2;
        message.i = Constants.TARGET_CONTROL;
        message.command = 5;
        message.m = str;
        message.serviceId = str2;
        message.v = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_BINDSERVICE;
        return message;
    }

    @Deprecated
    public static Message buildUnbindService(com.taobao.accs.net.b bVar, Context context, Intent intent) {
        return buildUnbindService(bVar.b((String) null), bVar.m, intent);
    }

    public static Message buildUnbindService(String str, String str2, Intent intent) {
        Message messageBuildUnbindService;
        try {
            messageBuildUnbindService = buildUnbindService(intent.getStringExtra("packageName"), intent.getStringExtra(Constants.KEY_SERVICE_ID));
        } catch (Exception e) {
            e = e;
            messageBuildUnbindService = null;
        }
        try {
            messageBuildUnbindService.H = str2;
            a(str, messageBuildUnbindService);
        } catch (Exception e2) {
            e = e2;
            ALog.e("Msg", "buildUnbindService", e, new Object[0]);
            e.printStackTrace();
            return messageBuildUnbindService;
        }
        return messageBuildUnbindService;
    }

    @Deprecated
    public static Message buildUnbindService(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        return buildUnbindService(str, str3);
    }

    public static Message buildUnbindService(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Message message = new Message();
        message.E = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.serviceId = str2;
        message.i = Constants.TARGET_CONTROL;
        message.command = 6;
        message.m = str;
        message.serviceId = str2;
        message.v = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_UNBINDSERVICE;
        return message;
    }

    @Deprecated
    public static Message buildBindUser(com.taobao.accs.net.b bVar, Context context, Intent intent) {
        return buildBindUser(bVar.b((String) null), bVar.m, intent);
    }

    public static Message buildBindUser(String str, String str2, Intent intent) {
        Message messageBuildBindUser;
        try {
            messageBuildBindUser = buildBindUser(intent.getStringExtra("packageName"), intent.getStringExtra(Constants.KEY_USER_ID));
            if (messageBuildBindUser != null) {
                try {
                    messageBuildBindUser.H = str2;
                    a(str, messageBuildBindUser);
                } catch (Exception e) {
                    e = e;
                    ALog.e("Msg", "buildBindUser", e, new Object[0]);
                    e.printStackTrace();
                    return messageBuildBindUser;
                }
            }
        } catch (Exception e2) {
            e = e2;
            messageBuildBindUser = null;
        }
        return messageBuildBindUser;
    }

    @Deprecated
    public static Message buildBindUser(Context context, String str, String str2, String str3, String str4, String str5) {
        return buildBindUser(str, str4);
    }

    public static Message buildBindUser(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Message message = new Message();
        message.E = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.userinfo = str2;
        message.i = Constants.TARGET_CONTROL;
        message.command = 3;
        message.m = str;
        message.userinfo = str2;
        message.v = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_BINDUSER;
        return message;
    }

    public static Message buildErrorReportMessage(String str, String str2, String str3, int i) {
        Message message = new Message();
        try {
            message.host = new URL(str3);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        message.i = Constants.TARGET_SERVICE_ST;
        message.a(1, ReqType.DATA, 0);
        message.command = 100;
        message.D = ("0|" + i + "|" + str + "|" + com.taobao.accs.utl.j.b(GlobalClientInfo.getContext()) + "|" + str2).getBytes();
        return message;
    }

    @Deprecated
    public static Message buildUnbindUser(com.taobao.accs.net.b bVar, Context context, Intent intent) {
        return buildUnbindUser(bVar.b((String) null), bVar.m, intent);
    }

    public static Message buildUnbindUser(String str, String str2, Intent intent) {
        Message messageBuildUnbindUser;
        try {
            messageBuildUnbindUser = buildUnbindUser(intent.getStringExtra("packageName"));
        } catch (Exception e) {
            e = e;
            messageBuildUnbindUser = null;
        }
        try {
            messageBuildUnbindUser.H = str2;
            a(str, messageBuildUnbindUser);
        } catch (Exception e2) {
            e = e2;
            ALog.e("Msg", "buildUnbindUser", e, new Object[0]);
            e.printStackTrace();
            return messageBuildUnbindUser;
        }
        return messageBuildUnbindUser;
    }

    @Deprecated
    public static Message buildUnbindUser(Context context, String str, String str2, String str3, String str4, String str5) {
        return buildUnbindUser(str);
    }

    public static Message buildUnbindUser(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Message message = new Message();
        message.E = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.i = Constants.TARGET_CONTROL;
        message.command = 4;
        message.v = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_UNBINDUSER;
        return message;
    }

    @Deprecated
    public static Message buildSendData(com.taobao.accs.net.b bVar, Context context, String str, String str2, ACCSManager.AccsRequest accsRequest) {
        return buildSendData(bVar.b((String) null), bVar.m, bVar.i.getStoreId(), context, str, accsRequest, true);
    }

    @Deprecated
    public static Message buildSendData(com.taobao.accs.net.b bVar, Context context, String str, String str2, ACCSManager.AccsRequest accsRequest, boolean z) {
        return buildSendData(bVar.b((String) null), bVar.m, bVar.i.getStoreId(), context, str, accsRequest, z);
    }

    public static Message buildSendData(String str, String str2, String str3, Context context, String str4, ACCSManager.AccsRequest accsRequest) {
        return buildSendData(str, str2, str3, context, str4, accsRequest, true);
    }

    public static Message buildSendData(String str, String str2, String str3, Context context, String str4, ACCSManager.AccsRequest accsRequest, boolean z) {
        if (TextUtils.isEmpty(str4)) {
            return null;
        }
        Message message = new Message();
        message.E = 1;
        message.a(1, ReqType.DATA, 1);
        message.command = 100;
        message.m = str4;
        message.serviceId = accsRequest.serviceId;
        message.userinfo = accsRequest.userId;
        message.D = accsRequest.data;
        String str5 = TextUtils.isEmpty(accsRequest.targetServiceName) ? accsRequest.serviceId : accsRequest.targetServiceName;
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.TARGET_SERVICE_PRE);
        sb.append(str5);
        sb.append("|");
        sb.append(accsRequest.target == null ? "" : accsRequest.target);
        message.i = sb.toString();
        message.cunstomDataId = accsRequest.dataId;
        message.bizId = accsRequest.businessId;
        if (accsRequest.timeout > 0) {
            message.timeout = accsRequest.timeout;
        }
        if (z) {
            a(str, message, accsRequest);
        } else {
            message.host = accsRequest.host;
        }
        a(message, GlobalClientInfo.getInstance(context).getSid(str2), GlobalClientInfo.getInstance(context).getUserId(str2), str3, GlobalClientInfo.c, accsRequest.businessId, accsRequest.tag);
        message.G = new NetPerformanceMonitor();
        message.G.setMsgType(0);
        message.G.setDataId(accsRequest.dataId);
        message.G.setServiceId(accsRequest.serviceId);
        message.G.setHost(message.host.toString());
        message.H = str2;
        return message;
    }

    @Deprecated
    public static Message buildRequest(com.taobao.accs.net.b bVar, Context context, String str, String str2, String str3, ACCSManager.AccsRequest accsRequest, boolean z) {
        return buildRequest(context, bVar.b((String) null), bVar.m, bVar.i.getStoreId(), str, str2, accsRequest, z);
    }

    public static Message buildRequest(Context context, String str, String str2, String str3, String str4, String str5, ACCSManager.AccsRequest accsRequest, boolean z) {
        if (TextUtils.isEmpty(str4)) {
            return null;
        }
        Message message = new Message();
        message.E = 1;
        message.a(1, ReqType.REQ, 1);
        message.command = 100;
        message.m = str4;
        message.serviceId = accsRequest.serviceId;
        message.userinfo = accsRequest.userId;
        message.D = accsRequest.data;
        String str6 = TextUtils.isEmpty(accsRequest.targetServiceName) ? accsRequest.serviceId : accsRequest.targetServiceName;
        StringBuilder sb = new StringBuilder();
        sb.append(str5);
        sb.append(str6);
        sb.append("|");
        sb.append(accsRequest.target == null ? "" : accsRequest.target);
        message.i = sb.toString();
        message.cunstomDataId = accsRequest.dataId;
        message.bizId = accsRequest.businessId;
        message.H = str2;
        if (accsRequest.timeout > 0) {
            message.timeout = accsRequest.timeout;
        }
        if (z) {
            a(str, message, accsRequest);
        } else {
            message.host = accsRequest.host;
        }
        a(message, GlobalClientInfo.getInstance(context).getSid(str2), GlobalClientInfo.getInstance(context).getUserId(str2), str3, GlobalClientInfo.c, accsRequest.businessId, accsRequest.tag);
        message.G = new NetPerformanceMonitor();
        message.G.setDataId(accsRequest.dataId);
        message.G.setServiceId(accsRequest.serviceId);
        message.G.setHost(message.host.toString());
        message.H = str2;
        return message;
    }

    private static void a(String str, Message message, ACCSManager.AccsRequest accsRequest) {
        if (accsRequest.host == null) {
            try {
                message.host = new URL(str);
                return;
            } catch (MalformedURLException e) {
                ALog.e("Msg", "setUnit", e, new Object[0]);
                e.printStackTrace();
                return;
            }
        }
        message.host = accsRequest.host;
    }

    private static void a(String str, Message message) {
        try {
            message.host = new URL(str);
        } catch (Exception e) {
            ALog.e("Msg", "setControlHost", e, new Object[0]);
        }
    }

    @Deprecated
    public static Message buildPushAck(com.taobao.accs.net.b bVar, String str, String str2, String str3, boolean z, short s, String str4, Map<Integer, String> map) {
        return buildPushAck(bVar.b((String) null), bVar.m, str, str2, str3, z, s, str4, map);
    }

    public static Message buildPushAck(String str, String str2, String str3, String str4, String str5, boolean z, short s, String str6, Map<Integer, String> map) {
        Message message = new Message();
        message.E = 1;
        message.a(s, z);
        message.j = str3;
        message.i = str4;
        message.dataId = str5;
        message.isAck = true;
        message.l = map;
        try {
            try {
                try {
                    if (TextUtils.isEmpty(str6)) {
                        message.host = new URL(str);
                    } else {
                        message.host = new URL(str6);
                    }
                    message.H = str2;
                } catch (Throwable th) {
                    ALog.e("Msg", "buildPushAck", th, new Object[0]);
                    if (message.host == null) {
                        message.host = new URL(str);
                    }
                }
                if (message.host == null) {
                    message.host = new URL(str);
                }
            } catch (Throwable th2) {
                if (message.host == null) {
                    try {
                        message.host = new URL(str);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                throw th2;
            }
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
        return message;
    }

    public static Message buildParameterError(String str, int i) {
        Message message = new Message();
        message.a(1, ReqType.ACK, 0);
        message.command = Integer.valueOf(i);
        message.m = str;
        return message;
    }

    private static void a(Message message, String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str5) && TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str6) && str4 == null) {
            return;
        }
        message.l = new HashMap();
        if (str5 != null && UtilityImpl.a(str5) <= 1023) {
            message.l.put(Integer.valueOf(TaoBaseService.ExtHeaderType.TYPE_BUSINESS.ordinal()), str5);
        }
        if (str != null && UtilityImpl.a(str) <= 1023) {
            message.l.put(Integer.valueOf(TaoBaseService.ExtHeaderType.TYPE_SID.ordinal()), str);
        }
        if (str2 != null && UtilityImpl.a(str2) <= 1023) {
            message.l.put(Integer.valueOf(TaoBaseService.ExtHeaderType.TYPE_USERID.ordinal()), str2);
        }
        if (str6 != null && UtilityImpl.a(str6) <= 1023) {
            message.l.put(Integer.valueOf(TaoBaseService.ExtHeaderType.TYPE_TAG.ordinal()), str6);
        }
        if (str4 != null && UtilityImpl.a(str4) <= 1023) {
            message.l.put(Integer.valueOf(TaoBaseService.ExtHeaderType.TYPE_COOKIE.ordinal()), str4);
        }
        if (str3 == null || UtilityImpl.a(str3) > 1023) {
            return;
        }
        message.l.put(19, str3);
    }

    private void a(int i, ReqType reqType, int i2) {
        this.k = i;
        if (i != 2) {
            this.f = (short) (((((i & 1) << 4) | (reqType.ordinal() << 2)) | i2) << 11);
        }
    }

    private void a(short s, boolean z) {
        this.k = 1;
        this.f = s;
        this.f = (short) (this.f & (-16385));
        this.f = (short) (this.f | 8192);
        this.f = (short) (this.f & (-2049));
        this.f = (short) (this.f & (-65));
        if (z) {
            this.f = (short) (this.f | 32);
        }
    }
}
