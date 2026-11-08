package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.Cif;
import com.xiaomi.push.bm;
import com.xiaomi.push.bw;
import com.xiaomi.push.es;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.hv;
import com.xiaomi.push.hw;
import com.xiaomi.push.hx;
import com.xiaomi.push.hz;
import com.xiaomi.push.ia;
import com.xiaomi.push.ii;
import com.xiaomi.push.im;
import com.xiaomi.push.it;
import com.xiaomi.push.iu;
import com.xiaomi.push.iz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class al {
    private static al a;

    /* renamed from: a, reason: collision with other field name */
    private static Object f63a = new Object();

    /* renamed from: a, reason: collision with other field name */
    private static Queue<String> f64a;

    /* renamed from: a, reason: collision with other field name */
    private Context f65a;

    private al(Context context) {
        this.f65a = context.getApplicationContext();
        if (this.f65a == null) {
            this.f65a = context;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.Intent a(android.content.Context r5, java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7, int r8) throws java.net.URISyntaxException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.al.a(android.content.Context, java.lang.String, java.util.Map, int):android.content.Intent");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0166  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.xiaomi.mipush.sdk.PushMessageHandler.a a(com.xiaomi.push.Cif r17, boolean r18, byte[] r19, java.lang.String r20, int r21, android.content.Intent r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 2754
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.al.a(com.xiaomi.push.if, boolean, byte[], java.lang.String, int, android.content.Intent):com.xiaomi.mipush.sdk.PushMessageHandler$a");
    }

    private PushMessageHandler.a a(Cif cif, byte[] bArr) {
        String str;
        iu iuVarA;
        String str2 = null;
        try {
            iuVarA = ah.a(this.f65a, cif);
        } catch (t e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            str = "message arrived: receive a message but decrypt failed. report when click.";
        } catch (iz e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
            str = "message arrived: receive a message which action string is not valid. is the reg expired?";
        }
        if (iuVarA == null) {
            com.xiaomi.channel.commonutils.logger.b.d("message arrived: receiving an un-recognized message. " + cif.f631a);
            return null;
        }
        hj hjVarA = cif.a();
        com.xiaomi.channel.commonutils.logger.b.m22a("message arrived: processing an arrived message, action=" + hjVarA);
        if (am.a[hjVarA.ordinal()] != 1) {
            return null;
        }
        if (cif.m469b()) {
            im imVar = (im) iuVarA;
            hv hvVarA = imVar.a();
            if (hvVarA != null) {
                if (cif.f632a != null && cif.f632a.m419a() != null) {
                    str2 = cif.f632a.f544a.get("jobkey");
                }
                MiPushMessage miPushMessageGenerateMessage = PushMessageHelper.generateMessage(imVar, cif.m461a(), false);
                miPushMessageGenerateMessage.setArrivedMessage(true);
                com.xiaomi.channel.commonutils.logger.b.m22a("message arrived: receive a message, msgid=" + hvVarA.m410a() + ", jobkey=" + str2);
                return miPushMessageGenerateMessage;
            }
            str = "message arrived: receive an empty message without push content, drop it";
        } else {
            str = "message arrived: receiving an un-encrypt message(SendMessage).";
        }
        com.xiaomi.channel.commonutils.logger.b.d(str);
        return null;
    }

    public static al a(Context context) {
        if (a == null) {
            a = new al(context);
        }
        return a;
    }

    private void a() {
        SharedPreferences sharedPreferences = this.f65a.getSharedPreferences("mipush_extra", 0);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - sharedPreferences.getLong(Constants.SP_KEY_LAST_REINITIALIZE, 0L)) > 1800000) {
            MiPushClient.reInitialize(this.f65a, hx.PackageUnregistered);
            sharedPreferences.edit().putLong(Constants.SP_KEY_LAST_REINITIALIZE, jCurrentTimeMillis).commit();
        }
    }

    public static void a(Context context, String str) {
        synchronized (f63a) {
            f64a.remove(str);
            b.m63a(context);
            SharedPreferences sharedPreferencesA = b.a(context);
            String strA = bm.a(f64a, Constants.ACCEPT_TIME_SEPARATOR_SP);
            SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
            editorEdit.putString("pref_msg_ids", strA);
            com.xiaomi.push.r.a(editorEdit);
        }
    }

    private void a(ia iaVar) {
        String strA = iaVar.a();
        com.xiaomi.channel.commonutils.logger.b.b("receive ack " + strA);
        Map<String, String> mapM442a = iaVar.m442a();
        if (mapM442a != null) {
            String str = mapM442a.get("real_source");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            com.xiaomi.channel.commonutils.logger.b.b("receive ack : messageId = " + strA + "  realSource = " + str);
            bw.a(this.f65a).a(strA, str, Boolean.valueOf(iaVar.f589a == 0));
        }
    }

    private void a(Cif cif) {
        com.xiaomi.channel.commonutils.logger.b.m22a("receive a message but decrypt failed. report now.");
        ii iiVar = new ii(cif.m461a().f542a, false);
        iiVar.c(ht.DecryptMessageFail.f509a);
        iiVar.b(cif.m462a());
        iiVar.d(cif.f638b);
        iiVar.f650a = new HashMap();
        iiVar.f650a.put("regid", MiPushClient.getRegId(this.f65a));
        an.a(this.f65a).a((an) iiVar, hj.Notification, false, (hw) null);
    }

    private void a(ii iiVar) {
        ia iaVar = new ia();
        iaVar.c(ht.CancelPushMessageACK.f509a);
        iaVar.a(iiVar.m476a());
        iaVar.a(iiVar.a());
        iaVar.b(iiVar.b());
        iaVar.e(iiVar.c());
        iaVar.a(0L);
        iaVar.d("success clear push message.");
        an.a(this.f65a).a(iaVar, hj.Notification, false, true, null, false, this.f65a.getPackageName(), b.m63a(this.f65a).m64a(), false);
    }

    private void a(im imVar, Cif cif) {
        hw hwVarM461a = cif.m461a();
        hz hzVar = new hz();
        hzVar.b(imVar.b());
        hzVar.a(imVar.m497a());
        hzVar.a(imVar.a().a());
        if (!TextUtils.isEmpty(imVar.c())) {
            hzVar.c(imVar.c());
        }
        if (!TextUtils.isEmpty(imVar.d())) {
            hzVar.d(imVar.d());
        }
        hzVar.a(it.a(this.f65a, cif));
        an.a(this.f65a).a((an) hzVar, hj.AckMessage, hwVarM461a);
    }

    private void a(String str, long j, e eVar) {
        at atVarA = l.a(eVar);
        if (atVarA == null) {
            return;
        }
        if (j == 0) {
            synchronized (ae.class) {
                if (ae.a(this.f65a).m43a(str)) {
                    ae.a(this.f65a).c(str);
                    if ("syncing".equals(ae.a(this.f65a).a(atVarA))) {
                        ae.a(this.f65a).a(atVarA, "synced");
                    }
                }
            }
            return;
        }
        if (!"syncing".equals(ae.a(this.f65a).a(atVarA))) {
            ae.a(this.f65a).c(str);
            return;
        }
        synchronized (ae.class) {
            if (ae.a(this.f65a).m43a(str)) {
                if (ae.a(this.f65a).a(str) < 10) {
                    ae.a(this.f65a).b(str);
                    an.a(this.f65a).a(str, atVarA, eVar);
                } else {
                    ae.a(this.f65a).c(str);
                }
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private static boolean m44a(Context context, String str) {
        synchronized (f63a) {
            b.m63a(context);
            SharedPreferences sharedPreferencesA = b.a(context);
            if (f64a == null) {
                String[] strArrSplit = sharedPreferencesA.getString("pref_msg_ids", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                f64a = new LinkedList();
                for (String str2 : strArrSplit) {
                    f64a.add(str2);
                }
            }
            if (f64a.contains(str)) {
                return true;
            }
            f64a.add(str);
            if (f64a.size() > 25) {
                f64a.poll();
            }
            String strA = bm.a(f64a, Constants.ACCEPT_TIME_SEPARATOR_SP);
            SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
            editorEdit.putString("pref_msg_ids", strA);
            com.xiaomi.push.r.a(editorEdit);
            return false;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m45a(Cif cif) {
        Map<String, String> mapM419a = cif.m461a() == null ? null : cif.m461a().m419a();
        if (mapM419a == null) {
            return false;
        }
        String str = mapM419a.get(Constants.EXTRA_KEY_PUSH_SERVER_ACTION);
        return TextUtils.equals(str, Constants.EXTRA_VALUE_HYBRID_MESSAGE) || TextUtils.equals(str, Constants.EXTRA_VALUE_PLATFORM_MESSAGE);
    }

    private void b(ia iaVar) {
        long j;
        e eVar;
        com.xiaomi.channel.commonutils.logger.b.c("ASSEMBLE_PUSH : " + iaVar.toString());
        String strA = iaVar.a();
        Map<String, String> mapM442a = iaVar.m442a();
        if (mapM442a != null) {
            String str = mapM442a.get(Constants.ASSEMBLE_PUSH_REG_INFO);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.contains("brand:" + af.FCM.name())) {
                com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : receive fcm token sync ack");
                i.b(this.f65a, e.ASSEMBLE_PUSH_FCM, str);
                j = iaVar.f589a;
                eVar = e.ASSEMBLE_PUSH_FCM;
            } else {
                if (str.contains("brand:" + af.HUAWEI.name())) {
                    com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : receive hw token sync ack");
                    i.b(this.f65a, e.ASSEMBLE_PUSH_HUAWEI, str);
                    j = iaVar.f589a;
                    eVar = e.ASSEMBLE_PUSH_HUAWEI;
                } else {
                    if (str.contains("brand:" + af.OPPO.name())) {
                        com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : receive COS token sync ack");
                        i.b(this.f65a, e.ASSEMBLE_PUSH_COS, str);
                        j = iaVar.f589a;
                        eVar = e.ASSEMBLE_PUSH_COS;
                    } else {
                        if (!str.contains("brand:" + af.VIVO.name())) {
                            return;
                        }
                        com.xiaomi.channel.commonutils.logger.b.m22a("ASSEMBLE_PUSH : receive FTOS token sync ack");
                        i.b(this.f65a, e.ASSEMBLE_PUSH_FTOS, str);
                        j = iaVar.f589a;
                        eVar = e.ASSEMBLE_PUSH_FTOS;
                    }
                }
            }
            a(strA, j, eVar);
        }
    }

    private void b(Cif cif) {
        hw hwVarM461a = cif.m461a();
        hz hzVar = new hz();
        hzVar.b(cif.m462a());
        hzVar.a(hwVarM461a.m418a());
        hzVar.a(hwVarM461a.m416a());
        if (!TextUtils.isEmpty(hwVarM461a.m423b())) {
            hzVar.c(hwVarM461a.m423b());
        }
        hzVar.a(it.a(this.f65a, cif));
        an.a(this.f65a).a((an) hzVar, hj.AckMessage, false, cif.m461a());
    }

    public PushMessageHandler.a a(Intent intent) {
        String str;
        es esVarA;
        String packageName;
        String str2;
        es esVarA2;
        String packageName2;
        String str3;
        String action = intent.getAction();
        com.xiaomi.channel.commonutils.logger.b.m22a("receive an intent from server, action=" + action);
        String stringExtra = intent.getStringExtra("mrt");
        if (stringExtra == null) {
            stringExtra = Long.toString(System.currentTimeMillis());
        }
        String stringExtra2 = intent.getStringExtra("messageId");
        int intExtra = intent.getIntExtra("eventMessageType", -1);
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                com.xiaomi.channel.commonutils.logger.b.d("receiving an empty message, drop");
                es.a(this.f65a).a(this.f65a.getPackageName(), intent, "12");
                return null;
            }
            Cif cif = new Cif();
            try {
                it.a(cif, byteArrayExtra);
                b bVarM63a = b.m63a(this.f65a);
                hw hwVarM461a = cif.m461a();
                if (cif.a() == hj.SendMessage && hwVarM461a != null && !bVarM63a.m73d() && !booleanExtra) {
                    hwVarM461a.a("mrt", stringExtra);
                    hwVarM461a.a("mat", Long.toString(System.currentTimeMillis()));
                    if (m45a(cif)) {
                        com.xiaomi.channel.commonutils.logger.b.b("this is a mina's message, ack later");
                        hwVarM461a.a(Constants.EXTRA_KEY_HYBRID_MESSAGE_TS, String.valueOf(hwVarM461a.m416a()));
                        hwVarM461a.a(Constants.EXTRA_KEY_HYBRID_DEVICE_STATUS, String.valueOf((int) it.a(this.f65a, cif)));
                    } else {
                        b(cif);
                    }
                }
                if (cif.a() == hj.SendMessage && !cif.m469b()) {
                    if (com.xiaomi.push.service.ac.m581a(cif)) {
                        Object[] objArr = new Object[2];
                        objArr[0] = cif.b();
                        objArr[1] = hwVarM461a != null ? hwVarM461a.m418a() : "";
                        com.xiaomi.channel.commonutils.logger.b.m22a(String.format("drop an un-encrypted wake-up messages. %1$s, %2$s", objArr));
                        esVarA2 = es.a(this.f65a);
                        packageName2 = this.f65a.getPackageName();
                        str3 = String.format("13: %1$s", cif.b());
                    } else {
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = cif.b();
                        objArr2[1] = hwVarM461a != null ? hwVarM461a.m418a() : "";
                        com.xiaomi.channel.commonutils.logger.b.m22a(String.format("drop an un-encrypted messages. %1$s, %2$s", objArr2));
                        esVarA2 = es.a(this.f65a);
                        packageName2 = this.f65a.getPackageName();
                        str3 = String.format("14: %1$s", cif.b());
                    }
                    esVarA2.a(packageName2, intent, str3);
                    return null;
                }
                if (cif.a() == hj.SendMessage && cif.m469b() && com.xiaomi.push.service.ac.m581a(cif) && (!booleanExtra || hwVarM461a == null || hwVarM461a.m419a() == null || !hwVarM461a.m419a().containsKey("notify_effect"))) {
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = cif.b();
                    objArr3[1] = hwVarM461a != null ? hwVarM461a.m418a() : "";
                    com.xiaomi.channel.commonutils.logger.b.m22a(String.format("drop a wake-up messages which not has 'notify_effect' attr. %1$s, %2$s", objArr3));
                    es.a(this.f65a).a(this.f65a.getPackageName(), intent, String.format("25: %1$s", cif.b()));
                    return null;
                }
                if (bVarM63a.m72c() || cif.f631a == hj.Registration) {
                    if (!bVarM63a.m72c() || !bVarM63a.m74e()) {
                        return a(cif, booleanExtra, byteArrayExtra, stringExtra2, intExtra, intent);
                    }
                    if (cif.f631a == hj.UnRegistration) {
                        bVarM63a.m65a();
                        MiPushClient.clearExtras(this.f65a);
                        PushMessageHandler.a();
                    } else {
                        MiPushClient.unregisterPush(this.f65a);
                    }
                } else {
                    if (com.xiaomi.push.service.ac.m581a(cif)) {
                        return a(cif, booleanExtra, byteArrayExtra, stringExtra2, intExtra, intent);
                    }
                    com.xiaomi.channel.commonutils.logger.b.d("receive message without registration. need re-register!");
                    es.a(this.f65a).a(this.f65a.getPackageName(), intent, "15");
                    a();
                }
            } catch (iz e) {
                e = e;
                esVarA = es.a(this.f65a);
                packageName = this.f65a.getPackageName();
                str2 = "16";
                esVarA.a(packageName, intent, str2);
                com.xiaomi.channel.commonutils.logger.b.a(e);
                return null;
            } catch (Exception e2) {
                e = e2;
                esVarA = es.a(this.f65a);
                packageName = this.f65a.getPackageName();
                str2 = "17";
                esVarA.a(packageName, intent, str2);
                com.xiaomi.channel.commonutils.logger.b.a(e);
                return null;
            }
        } else {
            if ("com.xiaomi.mipush.ERROR".equals(action)) {
                MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
                Cif cif2 = new Cif();
                try {
                    byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                    if (byteArrayExtra2 != null) {
                        it.a(cif2, byteArrayExtra2);
                    }
                } catch (iz unused) {
                }
                miPushCommandMessage.setCommand(String.valueOf(cif2.a()));
                miPushCommandMessage.setResultCode(intent.getIntExtra("mipush_error_code", 0));
                miPushCommandMessage.setReason(intent.getStringExtra("mipush_error_msg"));
                com.xiaomi.channel.commonutils.logger.b.d("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
                return miPushCommandMessage;
            }
            if ("com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
                byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra3 == null) {
                    com.xiaomi.channel.commonutils.logger.b.d("message arrived: receiving an empty message, drop");
                    return null;
                }
                Cif cif3 = new Cif();
                try {
                    it.a(cif3, byteArrayExtra3);
                    b bVarM63a2 = b.m63a(this.f65a);
                    if (com.xiaomi.push.service.ac.m581a(cif3)) {
                        str = "message arrived: receive ignore reg message, ignore!";
                    } else if (!bVarM63a2.m72c()) {
                        str = "message arrived: receive message without registration. need unregister or re-register!";
                    } else {
                        if (!bVarM63a2.m72c() || !bVarM63a2.m74e()) {
                            return a(cif3, byteArrayExtra3);
                        }
                        str = "message arrived: app info is invalidated";
                    }
                    com.xiaomi.channel.commonutils.logger.b.d(str);
                } catch (Exception e3) {
                    com.xiaomi.channel.commonutils.logger.b.d("fail to deal with arrived message. " + e3);
                }
            }
        }
        return null;
    }

    public List<String> a(TimeZone timeZone, TimeZone timeZone2, List<String> list) throws NumberFormatException {
        if (timeZone.equals(timeZone2)) {
            return list;
        }
        long rawOffset = ((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60;
        long j = ((((Long.parseLong(list.get(0).split(Constants.COLON_SEPARATOR)[0]) * 60) + Long.parseLong(list.get(0).split(Constants.COLON_SEPARATOR)[1])) - rawOffset) + 1440) % 1440;
        long j2 = ((((Long.parseLong(list.get(1).split(Constants.COLON_SEPARATOR)[0]) * 60) + Long.parseLong(list.get(1).split(Constants.COLON_SEPARATOR)[1])) - rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j / 60), Long.valueOf(j % 60)));
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j2 / 60), Long.valueOf(j2 % 60)));
        return arrayList;
    }
}
