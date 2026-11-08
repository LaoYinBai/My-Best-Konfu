package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.umeng.message.entity.UMessage;
import com.xiaomi.push.ho;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ao {
    private static Context a;

    /* renamed from: a, reason: collision with other field name */
    private static Object f885a;

    /* renamed from: a, reason: collision with other field name */
    private static WeakHashMap<Integer, ao> f886a = new WeakHashMap<>();

    /* renamed from: a, reason: collision with other field name */
    private static boolean f887a;

    /* renamed from: a, reason: collision with other field name */
    private String f888a;
    private String b;

    private ao(String str) {
        this.f888a = str;
    }

    private static int a(String str) {
        if (Build.VERSION.SDK_INT < 24) {
            return -1;
        }
        try {
            return a.getPackageManager().getPackageUid(str, 0);
        } catch (Exception unused) {
            return -1;
        }
    }

    private static NotificationManager a() {
        return (NotificationManager) a.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
    }

    public static ao a(Context context, String str) {
        a(context);
        int iHashCode = str.hashCode();
        ao aoVar = f886a.get(Integer.valueOf(iHashCode));
        if (aoVar != null) {
            return aoVar;
        }
        ao aoVar2 = new ao(str);
        f886a.put(Integer.valueOf(iHashCode), aoVar2);
        return aoVar2;
    }

    private static <T> T a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return (T) obj.getClass().getMethod("getList", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Object a(List list) {
        return Class.forName("android.content.pm.ParceledListSlice").getConstructor(List.class).newInstance(list);
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strA = a("mipush|%s|%s", str2, "");
        return str.startsWith(strA) ? a("mipush_%s_%s", str2, str.replace(strA, "")) : str;
    }

    private static String a(String str, String str2, String str3) {
        return TextUtils.isEmpty(str) ? "" : String.format(str, str2, str3);
    }

    private static void a(Context context) {
        if (a == null) {
            a = context.getApplicationContext();
            NotificationManager notificationManagerA = a();
            Boolean bool = (Boolean) com.xiaomi.push.bh.a((Object) notificationManagerA, "isSystemConditionProviderEnabled", "xmsf_fake_condition_provider_path");
            m592a("fwk is support.init:" + bool);
            f887a = bool != null ? bool.booleanValue() : false;
            if (f887a) {
                f885a = com.xiaomi.push.bh.a((Object) notificationManagerA, "getService", new Object[0]);
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    static void m592a(String str) {
        com.xiaomi.channel.commonutils.logger.b.m22a("NMHelper:" + str);
    }

    @TargetApi(26)
    /* renamed from: a, reason: collision with other method in class */
    private static boolean m593a() {
        if (com.xiaomi.push.l.m547a() && aq.a(a).a(ho.NotificationBelongToAppSwitch.a(), true)) {
            return f887a;
        }
        return false;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m594a(Context context) {
        a(context);
        return m593a();
    }

    private String b(String str) {
        return a(m593a() ? "mipush|%s|%s" : "mipush_%s_%s", this.f888a, str);
    }

    @TargetApi(26)
    /* renamed from: a, reason: collision with other method in class */
    public NotificationChannel m595a(String str) {
        NotificationChannel notificationChannel = null;
        try {
            if (m593a()) {
                List<NotificationChannel> listM599a = m599a();
                if (listM599a != null) {
                    Iterator<NotificationChannel> it = listM599a.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            NotificationChannel next = it.next();
                            if (str.equals(next.getId())) {
                                notificationChannel = next;
                                break;
                            }
                        }
                    }
                }
            } else {
                notificationChannel = a().getNotificationChannel(str);
            }
            break;
        } catch (Exception e) {
            m592a("getNotificationChannel error" + e);
        }
        return notificationChannel;
    }

    /* renamed from: a, reason: collision with other method in class */
    public Context m596a() {
        return a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m597a() {
        return this.f888a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m598a(String str) {
        return TextUtils.isEmpty(str) ? b() : com.xiaomi.push.l.m547a() ? b(str) : str;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    @TargetApi(26)
    /* renamed from: a, reason: collision with other method in class */
    List<NotificationChannel> m599a() {
        List<NotificationChannel> notificationChannels;
        String str;
        String str2;
        String str3 = this.f888a;
        List<NotificationChannel> list = null;
        try {
            if (m593a()) {
                int iA = a(str3);
                if (iA == -1) {
                    str2 = null;
                    if (!com.xiaomi.push.l.m547a() && list != null) {
                        ArrayList arrayList = new ArrayList();
                        String strA = a(str2, str3, "");
                        for (NotificationChannel notificationChannel : list) {
                            if (notificationChannel.getId().startsWith(strA)) {
                                arrayList.add(notificationChannel);
                            }
                        }
                        return arrayList;
                    }
                }
                notificationChannels = (List) a(com.xiaomi.push.bh.a(f885a, "getNotificationChannelsForPackage", str3, Integer.valueOf(iA), false));
                str = "mipush|%s|%s";
            } else {
                notificationChannels = a().getNotificationChannels();
                str = "mipush_%s_%s";
            }
            List<NotificationChannel> list2 = notificationChannels;
            str2 = str;
            list = list2;
            return !com.xiaomi.push.l.m547a() ? list : list;
        } catch (Exception e) {
            m592a("getNotificationChannels error " + e);
            return list;
        }
    }

    public void a(int i) {
        String str = this.f888a;
        try {
            if (m593a()) {
                com.xiaomi.push.bh.b(f885a, "cancelNotificationWithTag", str, null, Integer.valueOf(i), Integer.valueOf(com.xiaomi.push.i.a()));
                m592a("cancel succ:" + i);
            } else {
                a().cancel(i);
            }
        } catch (Exception e) {
            m592a("cancel error" + e);
        }
    }

    public void a(int i, Notification notification) {
        String str = this.f888a;
        NotificationManager notificationManagerA = a();
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (m593a()) {
                if (i2 >= 19) {
                    notification.extras.putString("xmsf_target_package", str);
                }
                if (i2 >= 29) {
                    notificationManagerA.notifyAsPackage(str, null, i, notification);
                    return;
                }
            }
            notificationManagerA.notify(i, notification);
        } catch (Exception unused) {
        }
    }

    @TargetApi(26)
    void a(NotificationChannel notificationChannel) {
        String str = this.f888a;
        try {
            if (m593a()) {
                int iA = a(str);
                if (iA != -1) {
                    com.xiaomi.push.bh.b(f885a, "createNotificationChannelsForPackage", str, Integer.valueOf(iA), a(Arrays.asList(notificationChannel)));
                }
            } else {
                a().createNotificationChannel(notificationChannel);
            }
        } catch (Exception e) {
            m592a("createNotificationChannel error" + e);
        }
    }

    void a(NotificationChannel notificationChannel, boolean z) {
        String str = this.f888a;
        try {
            if (z) {
                int iA = a(str);
                if (iA != -1) {
                    com.xiaomi.push.bh.b(f885a, "updateNotificationChannelForPackage", str, Integer.valueOf(iA), notificationChannel);
                }
            } else {
                a(notificationChannel);
            }
        } catch (Exception e) {
            m592a("updateNotificationChannel error " + e);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m600a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(b(""));
    }

    String b() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = b("default");
        }
        return this.b;
    }

    String b(String str, String str2) {
        return m593a() ? str : str2;
    }

    @TargetApi(23)
    /* renamed from: b, reason: collision with other method in class */
    public List<StatusBarNotification> m601b() {
        String str = this.f888a;
        NotificationManager notificationManagerA = a();
        List<StatusBarNotification> list = null;
        try {
            if (m593a()) {
                int iA = com.xiaomi.push.i.a();
                if (iA != -1) {
                    list = (List) a(com.xiaomi.push.bh.a(f885a, "getAppActiveNotifications", str, Integer.valueOf(iA)));
                }
            } else {
                StatusBarNotification[] activeNotifications = notificationManagerA.getActiveNotifications();
                boolean zM547a = com.xiaomi.push.l.m547a();
                if (activeNotifications != null && activeNotifications.length > 0) {
                    ArrayList arrayList = new ArrayList();
                    try {
                        for (StatusBarNotification statusBarNotification : activeNotifications) {
                            if (!zM547a || str.equals(ap.a(statusBarNotification.getNotification()))) {
                                arrayList.add(statusBarNotification);
                            }
                        }
                        list = arrayList;
                    } catch (Exception e) {
                        e = e;
                        list = arrayList;
                        m592a("getActiveNotifications error " + e);
                        return list;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        return list;
    }

    public String toString() {
        return "NotificationManagerHelper{" + this.f888a + "}";
    }
}
