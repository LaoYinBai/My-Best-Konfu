package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.xiaomi.push.ho;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TargetApi(24)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class al {
    private static al a = new al();

    private class a {

        /* renamed from: a, reason: collision with other field name */
        List<b> f880a;
        List<b> b;

        private a() {
            this.f880a = new ArrayList();
            this.b = new ArrayList();
        }
    }

    private class b {
        int a;

        /* renamed from: a, reason: collision with other field name */
        Notification f881a;

        public b(int i, Notification notification) {
            this.a = i;
            this.f881a = notification;
        }

        public String toString() {
            return "id:" + this.a;
        }
    }

    private al() {
    }

    private int a(String str, String str2) {
        return ("GroupSummary" + str + str2).hashCode();
    }

    public static al a() {
        return a;
    }

    private String a(Notification notification) {
        if (notification == null || notification.extras == null) {
            return null;
        }
        return notification.extras.getString("push_src_group_name");
    }

    private List<StatusBarNotification> a(ao aoVar) {
        List<StatusBarNotification> listM601b = aoVar != null ? aoVar.m601b() : null;
        if (listM601b == null || listM601b.size() == 0) {
            return null;
        }
        return listM601b;
    }

    private void a(Context context, int i, Notification notification, boolean z) {
        String str;
        String strA = ap.a(notification);
        if (TextUtils.isEmpty(strA)) {
            str = "group auto not extract pkg from notification:" + i;
        } else {
            List<StatusBarNotification> listA = a(ao.a(context, strA));
            if (listA != null) {
                String strB = b(notification);
                HashMap map = new HashMap();
                for (StatusBarNotification statusBarNotification : listA) {
                    if (statusBarNotification.getNotification() != null && statusBarNotification.getId() != i) {
                        a(map, statusBarNotification);
                    }
                }
                for (Map.Entry<String, a> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (!TextUtils.isEmpty(key)) {
                        a value = entry.getValue();
                        if (z && key.equals(strB) && !m591b(notification)) {
                            (m590a(notification) ? value.b : value.f880a).add(new b(i, notification));
                        }
                        int size = value.f880a.size();
                        if (value.b.size() <= 0) {
                            if (z && size >= 2) {
                                a(context, strA, key, value.f880a.get(0).f881a);
                            }
                        } else if (size <= 0) {
                            a(context, strA, key);
                        }
                    }
                }
                return;
            }
            str = "group auto not get notifications";
        }
        com.xiaomi.channel.commonutils.logger.b.m22a(str);
    }

    private void a(Context context, String str, String str2) {
        com.xiaomi.channel.commonutils.logger.b.b("group cancel summary:" + str2);
        ao.a(context, str).a(a(str, str2));
    }

    private void a(Context context, String str, String str2, Notification notification) {
        Notification.Builder defaults;
        try {
            if (TextUtils.isEmpty(str2)) {
                com.xiaomi.channel.commonutils.logger.b.m22a("group show summary group is null");
                return;
            }
            int iA = ap.a(context, str);
            if (iA == 0) {
                com.xiaomi.channel.commonutils.logger.b.m22a("group show summary not get icon from " + str);
                return;
            }
            ao aoVarA = ao.a(context, str);
            if (Build.VERSION.SDK_INT >= 26) {
                String strB = aoVarA.b(notification.getChannelId(), "groupSummary");
                NotificationChannel notificationChannelM595a = aoVarA.m595a(strB);
                if ("groupSummary".equals(strB) && notificationChannelM595a == null) {
                    aoVarA.a(new NotificationChannel(strB, "group_summary", 3));
                }
                defaults = new Notification.Builder(context, strB);
            } else {
                defaults = new Notification.Builder(context).setPriority(0).setDefaults(-1);
            }
            ap.a(defaults, true);
            Notification notificationBuild = defaults.setContentTitle("GroupSummary").setContentText("GroupSummary").setSmallIcon(Icon.createWithResource(str, iA)).setAutoCancel(true).setGroup(str2).setGroupSummary(true).build();
            if (!com.xiaomi.push.l.c() && "com.xiaomi.xmsf".equals(context.getPackageName())) {
                ap.a(notificationBuild, str);
            }
            int iA2 = a(str, str2);
            aoVarA.a(iA2, notificationBuild);
            com.xiaomi.channel.commonutils.logger.b.b("group show summary notify:" + iA2);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("group show summary error " + e);
        }
    }

    private void a(Map<String, a> map, StatusBarNotification statusBarNotification) {
        String strB = b(statusBarNotification.getNotification());
        a aVar = map.get(strB);
        if (aVar == null) {
            aVar = new a();
            map.put(strB, aVar);
        }
        (m590a(statusBarNotification.getNotification()) ? aVar.b : aVar.f880a).add(new b(statusBarNotification.getId(), statusBarNotification.getNotification()));
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m589a() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m590a(Notification notification) {
        if (notification == null) {
            return false;
        }
        Object objA = com.xiaomi.push.bh.a((Object) notification, "isGroupSummary", (Object[]) null);
        if (objA instanceof Boolean) {
            return ((Boolean) objA).booleanValue();
        }
        return false;
    }

    private boolean a(Context context) {
        if (b(context) && ao.m594a(context)) {
            return aq.a(context).a(ho.LatestNotificationNotIntoGroupSwitch.a(), false);
        }
        return false;
    }

    private String b(Notification notification) {
        if (notification == null) {
            return null;
        }
        return m591b(notification) ? a(notification) : notification.getGroup();
    }

    private void b(Context context, int i, Notification notification) {
        String str;
        String strA = ap.a(notification);
        if (TextUtils.isEmpty(strA)) {
            str = "group restore not extract pkg from notification:" + i;
        } else {
            ao aoVarA = ao.a(context, strA);
            List<StatusBarNotification> listA = a(aoVarA);
            if (listA != null) {
                for (StatusBarNotification statusBarNotification : listA) {
                    Notification notification2 = statusBarNotification.getNotification();
                    if (notification2 != null && m591b(notification2) && statusBarNotification.getId() != i) {
                        Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(context, statusBarNotification.getNotification());
                        builderRecoverBuilder.setGroup(a(notification2));
                        ap.a(builderRecoverBuilder, m590a(notification2));
                        aoVarA.a(statusBarNotification.getId(), builderRecoverBuilder.build());
                        com.xiaomi.channel.commonutils.logger.b.b("group restore notification:" + statusBarNotification.getId());
                    }
                }
                return;
            }
            str = "group restore not get notifications";
        }
        com.xiaomi.channel.commonutils.logger.b.m22a(str);
    }

    /* renamed from: b, reason: collision with other method in class */
    private boolean m591b(Notification notification) {
        if (notification == null || notification.getGroup() == null || notification.extras == null) {
            return false;
        }
        long j = notification.extras.getLong("push_src_group_time");
        return notification.getGroup().equals(String.format("pushmask_%s_%s", Long.valueOf(j), a(notification)));
    }

    private boolean b(Context context) {
        return aq.a(context).a(ho.NotificationAutoGroupSwitch.a(), true);
    }

    public String a(Context context, Notification.Builder builder, String str) {
        if (!m589a() || !a(context)) {
            return str;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Bundle extras = builder.getExtras();
        extras.putString("push_src_group_name", str);
        extras.putLong("push_src_group_time", jCurrentTimeMillis);
        return String.format("pushmask_%s_%s", Long.valueOf(jCurrentTimeMillis), str);
    }

    public void a(Context context, int i, Notification notification) {
        if (m589a()) {
            if (a(context)) {
                try {
                    b(context, i, notification);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.m22a("group notify handle restore error " + e);
                }
            }
            if (b(context)) {
                try {
                    a(context, i, notification, true);
                } catch (Exception e2) {
                    com.xiaomi.channel.commonutils.logger.b.m22a("group notify handle auto error " + e2);
                }
            }
        }
    }
}
