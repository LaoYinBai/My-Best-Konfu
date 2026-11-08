package com.bytedance.push.notification;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.push.settings.LocalFrequencySettings;
import com.bytedance.push.settings.SettingsManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e {

    /* renamed from: a, reason: collision with root package name */
    private static b f4889a;

    interface b {
        JSONArray a(Context context);

        void a(Context context, com.bytedance.push.e.a aVar);

        boolean a(Context context, int i);

        void b(Context context, com.bytedance.push.e.a aVar);
    }

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            f4889a = new c();
        } else {
            f4889a = new a();
        }
    }

    public static b a() {
        return f4889a;
    }

    private static class a implements b {

        /* renamed from: a, reason: collision with root package name */
        private NotificationManager f4890a;

        @Override // com.bytedance.push.notification.e.b
        public void a(Context context, com.bytedance.push.e.a aVar) {
        }

        @Override // com.bytedance.push.notification.e.b
        public void b(Context context, com.bytedance.push.e.a aVar) {
        }

        private a() {
            this.f4890a = null;
        }

        @Override // com.bytedance.push.notification.e.b
        public boolean a(Context context, int i) {
            return com.ss.android.message.a.b.d(context) != i;
        }

        @Override // com.bytedance.push.notification.e.b
        public JSONArray a(Context context) {
            return new JSONArray();
        }

        NotificationManager b(Context context) {
            if (this.f4890a == null) {
                this.f4890a = (NotificationManager) context.getSystemService("notification");
            }
            return this.f4890a;
        }
    }

    @TargetApi(26)
    private static class c extends a {

        /* renamed from: a, reason: collision with root package name */
        private List<NotificationChannel> f4891a;

        private c() {
            super();
        }

        @Override // com.bytedance.push.notification.e.a, com.bytedance.push.notification.e.b
        public void b(Context context, com.bytedance.push.e.a aVar) {
            NotificationManager notificationManagerB;
            if (aVar == null || (notificationManagerB = b(context)) == null || TextUtils.isEmpty(aVar.b()) || notificationManagerB.getNotificationChannel(aVar.b()) == null) {
                return;
            }
            notificationManagerB.deleteNotificationChannel(aVar.b());
        }

        @Override // com.bytedance.push.notification.e.a, com.bytedance.push.notification.e.b
        public void a(Context context, com.bytedance.push.e.a aVar) {
            NotificationManager notificationManagerB;
            if (aVar == null || (notificationManagerB = b(context)) == null || TextUtils.isEmpty(aVar.b()) || TextUtils.isEmpty(aVar.c()) || notificationManagerB.getNotificationChannel(aVar.b()) != null) {
                return;
            }
            int iD = aVar.d();
            if (iD < 0 || iD > 5) {
                iD = 3;
            }
            NotificationChannel notificationChannel = new NotificationChannel(aVar.b(), aVar.c(), iD);
            notificationChannel.setShowBadge(aVar.i());
            notificationChannel.setDescription(aVar.k());
            notificationChannel.enableVibration(aVar.h());
            notificationChannel.setBypassDnd(aVar.f());
            notificationChannel.enableLights(aVar.g());
            notificationChannel.setLockscreenVisibility(aVar.e());
            notificationManagerB.createNotificationChannel(notificationChannel);
        }

        @Override // com.bytedance.push.notification.e.a, com.bytedance.push.notification.e.b
        public JSONArray a(Context context) {
            return a(c(context));
        }

        @Override // com.bytedance.push.notification.e.a, com.bytedance.push.notification.e.b
        public boolean a(Context context, int i) {
            if (super.a(context, i)) {
                return true;
            }
            return d(context);
        }

        private List<NotificationChannel> c(Context context) {
            if (this.f4891a == null || this.f4891a.isEmpty()) {
                try {
                    this.f4891a = ((NotificationManager) context.getSystemService(NotificationManager.class)).getNotificationChannels();
                } catch (Throwable th) {
                    th.printStackTrace();
                    this.f4891a = Collections.emptyList();
                }
            }
            return this.f4891a;
        }

        private static JSONArray a(List<NotificationChannel> list) {
            JSONArray jSONArray = new JSONArray();
            if (list == null) {
                return jSONArray;
            }
            Iterator<NotificationChannel> it = list.iterator();
            while (it.hasNext()) {
                try {
                    jSONArray.put(new com.bytedance.push.e.a(it.next()).a());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return jSONArray;
        }

        private boolean d(Context context) {
            try {
                List<NotificationChannel> listC = c(context);
                if (TextUtils.isEmpty(((LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class)).i())) {
                    return (listC == null || listC.isEmpty()) ? false : true;
                }
                return !a(listC, a(new JSONArray(r5)));
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        private static Map<String, com.bytedance.push.e.a> a(JSONArray jSONArray) {
            if (jSONArray == null || jSONArray.length() == 0) {
                return Collections.emptyMap();
            }
            HashMap map = new HashMap(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                map.put(jSONObjectOptJSONObject.optString("id"), new com.bytedance.push.e.a(jSONObjectOptJSONObject));
            }
            return map;
        }

        private static boolean a(List<NotificationChannel> list, Map<String, com.bytedance.push.e.a> map) {
            if (list.size() != map.size()) {
                return false;
            }
            for (NotificationChannel notificationChannel : list) {
                com.bytedance.push.e.a aVar = map.get(notificationChannel.getId());
                if (aVar == null || aVar.d() != notificationChannel.getImportance() || aVar.e() != notificationChannel.getLockscreenVisibility() || aVar.f() != notificationChannel.canBypassDnd() || aVar.g() != notificationChannel.shouldShowLights() || aVar.h() != notificationChannel.shouldVibrate()) {
                    return false;
                }
            }
            return true;
        }
    }
}
