package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.xiaomi.push.Cif;
import com.xiaomi.push.ai;
import com.xiaomi.push.et;
import com.xiaomi.push.eu;
import com.xiaomi.push.ev;
import com.xiaomi.push.hj;
import com.xiaomi.push.hw;
import com.xiaomi.push.service.an;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ac {
    public static long a;

    /* renamed from: a, reason: collision with other field name */
    private static volatile aj f864a;

    /* renamed from: a, reason: collision with other field name */
    private static final LinkedList<Pair<Integer, Cif>> f865a = new LinkedList<>();

    /* renamed from: a, reason: collision with other field name */
    private static ExecutorService f866a = Executors.newCachedThreadPool();

    private static class a implements Callable<Bitmap> {
        private Context a;

        /* renamed from: a, reason: collision with other field name */
        private String f867a;

        /* renamed from: a, reason: collision with other field name */
        private boolean f868a;

        public a(String str, Context context, boolean z) {
            this.a = context;
            this.f867a = str;
            this.f868a = z;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap call() throws Throwable {
            String str;
            Bitmap bitmapA = null;
            if (TextUtils.isEmpty(this.f867a)) {
                str = "Failed get online picture/icon resource cause picUrl is empty";
            } else {
                if (this.f867a.startsWith(Constants.HTTP)) {
                    an.b bVarA = an.a(this.a, this.f867a, this.f868a);
                    if (bVarA != null) {
                        return bVarA.f884a;
                    }
                } else {
                    bitmapA = an.a(this.a, this.f867a);
                    if (bitmapA != null) {
                        return bitmapA;
                    }
                }
                str = "Failed get online picture/icon resource";
            }
            com.xiaomi.channel.commonutils.logger.b.m22a(str);
            return bitmapA;
        }
    }

    public static class b {
        long a = 0;

        /* renamed from: a, reason: collision with other field name */
        Notification f869a;
    }

    public static class c {
        public long a = 0;

        /* renamed from: a, reason: collision with other field name */
        public String f870a;
    }

    static int a(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, Integer.MAX_VALUE);
    }

    private static int a(Context context, String str, String str2) {
        if (str.equals(context.getPackageName())) {
            return context.getResources().getIdentifier(str2, "drawable", str);
        }
        return 0;
    }

    private static int a(Context context, String str, Map<String, String> map) throws URISyntaxException {
        ComponentName componentNameA;
        Intent intentA = a(context, str, map, "notify_effect", "intent_uri", "class_name", "web_uri");
        if (intentA == null || (componentNameA = g.a(context, intentA)) == null) {
            return 0;
        }
        return componentNameA.hashCode();
    }

    private static int a(Map<String, String> map) {
        String str = map == null ? null : map.get(PointCategory.TIMEOUT);
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    private static Notification a(Notification notification) {
        Object objA = com.xiaomi.push.bh.a(notification, "extraNotification");
        if (objA != null) {
            com.xiaomi.push.bh.a(objA, "setCustomizedIcon", true);
        }
        return notification;
    }

    private static Notification a(Notification notification, String str) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Field declaredField = Notification.class.getDeclaredField("extraNotification");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(notification);
            Method declaredMethod = obj.getClass().getDeclaredMethod("setTargetPkg", CharSequence.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, str);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
        return notification;
    }

    private static PendingIntent a(Context context, Cif cif, String str, byte[] bArr, int i) {
        return a(context, cif, str, bArr, i, 0, a(context, cif, str));
    }

    private static PendingIntent a(Context context, Cif cif, String str, byte[] bArr, int i, int i2, boolean z) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intent intent;
        int i3 = c(cif) ? 1000 : m581a(cif) ? 3000 : -1;
        hw hwVarM461a = cif.m461a();
        String strM418a = hwVarM461a != null ? hwVarM461a.m418a() : "";
        boolean zM581a = m581a(cif);
        if (hwVarM461a != null && !TextUtils.isEmpty(hwVarM461a.f553e)) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse(hwVarM461a.f553e));
            intent2.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent2.putExtra("messageId", strM418a);
            intent2.putExtra("eventMessageType", i3);
            return PendingIntent.getActivity(context, 0, intent2, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        }
        if (zM581a) {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(i));
            intent.addCategory(String.valueOf(strM418a));
        } else {
            intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.setComponent(new ComponentName(str, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(i));
            intent.addCategory(String.valueOf(strM418a));
        }
        intent.putExtra("notification_click_button", i2);
        intent.putExtra("messageId", strM418a);
        intent.putExtra("eventMessageType", i3);
        if (zM581a || !z) {
            a(context, intent, cif, hwVarM461a);
            return PendingIntent.getService(context, 0, intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        }
        Intent intent3 = new Intent();
        intent3.setComponent(a(str));
        intent3.addFlags(276824064);
        intent3.putExtra("mipush_serviceIntent", intent);
        intent3.addCategory(String.valueOf(i));
        intent3.addCategory(String.valueOf(strM418a));
        intent3.addCategory(String.valueOf(i2));
        a(context, intent3, cif, hwVarM461a);
        a(intent3);
        return PendingIntent.getActivity(context, 0, intent3, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
    }

    private static PendingIntent a(Context context, String str, Cif cif, byte[] bArr, int i, int i2) {
        Map<String, String> mapM419a = cif.m461a().m419a();
        if (mapM419a == null) {
            return null;
        }
        boolean zA = a(context, cif, str);
        if (zA) {
            return a(context, cif, str, bArr, i, i2, zA);
        }
        Intent intentA = a(context, str, mapM419a, i2);
        if (intentA != null) {
            return PendingIntent.getActivity(context, 0, intentA, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        }
        return null;
    }

    public static ComponentName a(String str) {
        return new ComponentName(str, "com.xiaomi.mipush.sdk.NotificationClickedActivity");
    }

    public static Intent a(Context context, String str, Map<String, String> map, int i) {
        String str2;
        String str3;
        String str4;
        String str5;
        switch (i) {
            case 1:
                str2 = "notification_style_button_left_notify_effect";
                str3 = "notification_style_button_left_intent_uri";
                str4 = "notification_style_button_left_intent_class";
                str5 = "notification_style_button_left_web_uri";
                break;
            case 2:
                str2 = "notification_style_button_mid_notify_effect";
                str3 = "notification_style_button_mid_intent_uri";
                str4 = "notification_style_button_mid_intent_class";
                str5 = "notification_style_button_mid_web_uri";
                break;
            case 3:
                str2 = "notification_style_button_right_notify_effect";
                str3 = "notification_style_button_right_intent_uri";
                str4 = "notification_style_button_right_intent_class";
                str5 = "notification_style_button_right_web_uri";
                break;
            case 4:
                str2 = "notification_colorful_button_notify_effect";
                str3 = "notification_colorful_button_intent_uri";
                str4 = "notification_colorful_button_intent_class";
                str5 = "notification_colorful_button_web_uri";
                break;
            default:
                return null;
        }
        return a(context, str, map, str2, str3, str4, str5);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.Intent a(android.content.Context r2, java.lang.String r3, java.util.Map<java.lang.String, java.lang.String> r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.net.URISyntaxException {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ac.a(android.content.Context, java.lang.String, java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String):android.content.Intent");
    }

    private static Bitmap a(Context context, int i) {
        return a(context.getResources().getDrawable(i));
    }

    private static Bitmap a(Context context, String str, boolean z) {
        Bitmap bitmap;
        Future futureSubmit = f866a.submit(new a(str, context, z));
        try {
            try {
                bitmap = (Bitmap) futureSubmit.get(180L, TimeUnit.SECONDS);
                if (bitmap == null) {
                }
            } finally {
                futureSubmit.cancel(true);
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            futureSubmit.cancel(true);
            bitmap = null;
        }
        return bitmap;
    }

    public static Bitmap a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight > 0 ? intrinsicHeight : 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private static RemoteViews a(Context context, Cif cif, byte[] bArr) throws JSONException, PackageManager.NameNotFoundException {
        hw hwVarM461a = cif.m461a();
        String strA = a(cif);
        if (hwVarM461a != null && hwVarM461a.m419a() != null) {
            Map<String, String> mapM419a = hwVarM461a.m419a();
            String str = mapM419a.get("layout_name");
            String str2 = mapM419a.get("layout_value");
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                try {
                    Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(strA);
                    int identifier = resourcesForApplication.getIdentifier(str, "layout", strA);
                    if (identifier == 0) {
                        return null;
                    }
                    RemoteViews remoteViews = new RemoteViews(strA, identifier);
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.has("text")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("text");
                            Iterator<String> itKeys = jSONObject2.keys();
                            while (itKeys.hasNext()) {
                                String next = itKeys.next();
                                String string = jSONObject2.getString(next);
                                int identifier2 = resourcesForApplication.getIdentifier(next, AdDownloadModel.JsonKey.ID, strA);
                                if (identifier2 > 0) {
                                    remoteViews.setTextViewText(identifier2, string);
                                }
                            }
                        }
                        if (jSONObject.has("image")) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("image");
                            Iterator<String> itKeys2 = jSONObject3.keys();
                            while (itKeys2.hasNext()) {
                                String next2 = itKeys2.next();
                                String string2 = jSONObject3.getString(next2);
                                int identifier3 = resourcesForApplication.getIdentifier(next2, AdDownloadModel.JsonKey.ID, strA);
                                int identifier4 = resourcesForApplication.getIdentifier(string2, "drawable", strA);
                                if (identifier3 > 0) {
                                    remoteViews.setImageViewResource(identifier3, identifier4);
                                }
                            }
                        }
                        if (jSONObject.has(com.umeng.message.proguard.l.D)) {
                            JSONObject jSONObject4 = jSONObject.getJSONObject(com.umeng.message.proguard.l.D);
                            Iterator<String> itKeys3 = jSONObject4.keys();
                            while (itKeys3.hasNext()) {
                                String next3 = itKeys3.next();
                                String string3 = jSONObject4.getString(next3);
                                if (string3.length() == 0) {
                                    string3 = "yy-MM-dd hh:mm";
                                }
                                int identifier5 = resourcesForApplication.getIdentifier(next3, AdDownloadModel.JsonKey.ID, strA);
                                if (identifier5 > 0) {
                                    remoteViews.setTextViewText(identifier5, new SimpleDateFormat(string3).format(new Date(System.currentTimeMillis())));
                                }
                            }
                        }
                        return remoteViews;
                    } catch (JSONException e) {
                        com.xiaomi.channel.commonutils.logger.b.a(e);
                        return null;
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    com.xiaomi.channel.commonutils.logger.b.a(e2);
                }
            }
        }
        return null;
    }

    private static ai.a a(Context context, String str, int i, String str2, Notification notification) {
        return new ae(i, str2, context, str, notification);
    }

    @TargetApi(16)
    private static eu a(Context context, Cif cif, byte[] bArr, String str, int i) {
        PendingIntent pendingIntentA;
        String strA = a(cif);
        Map<String, String> mapM419a = cif.m461a().m419a();
        String str2 = mapM419a.get("notification_style_type");
        if (Build.VERSION.SDK_INT >= 16 && "2".equals(str2)) {
            eu euVar = new eu(context);
            Bitmap bitmapA = TextUtils.isEmpty(mapM419a.get("notification_bigPic_uri")) ? null : a(context, mapM419a.get("notification_bigPic_uri"), false);
            if (bitmapA == null) {
                com.xiaomi.channel.commonutils.logger.b.m22a("can not get big picture.");
                return euVar;
            }
            Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(euVar);
            bigPictureStyle.bigPicture(bitmapA);
            bigPictureStyle.setSummaryText(str);
            bigPictureStyle.bigLargeIcon((Bitmap) null);
            euVar.setStyle(bigPictureStyle);
            return euVar;
        }
        if (Build.VERSION.SDK_INT >= 16 && "1".equals(str2)) {
            eu euVar2 = new eu(context);
            euVar2.setStyle(new Notification.BigTextStyle().bigText(str));
            return euVar2;
        }
        if (DownloadConstants.DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR.equals(str2) && com.xiaomi.push.l.m548a(context)) {
            et etVar = new et(context, strA);
            if (!TextUtils.isEmpty(mapM419a.get("notification_banner_image_uri"))) {
                etVar.setLargeIcon(a(context, mapM419a.get("notification_banner_image_uri"), false));
            }
            if (!TextUtils.isEmpty(mapM419a.get("notification_banner_icon_uri"))) {
                etVar.b(a(context, mapM419a.get("notification_banner_icon_uri"), false));
            }
            etVar.a(mapM419a);
            return etVar;
        }
        if (!"3".equals(str2) || !com.xiaomi.push.l.m548a(context)) {
            return new eu(context);
        }
        ev evVar = new ev(context, i, strA);
        if (!TextUtils.isEmpty(mapM419a.get("notification_colorful_button_text")) && (pendingIntentA = a(context, strA, cif, bArr, i, 4)) != null) {
            evVar.a(mapM419a.get("notification_colorful_button_text"), pendingIntentA).a(mapM419a.get("notification_colorful_button_bg_color"));
        }
        if (!TextUtils.isEmpty(mapM419a.get("notification_colorful_bg_color"))) {
            evVar.b(mapM419a.get("notification_colorful_bg_color"));
        } else if (!TextUtils.isEmpty(mapM419a.get("notification_colorful_bg_image_uri"))) {
            evVar.setLargeIcon(a(context, mapM419a.get("notification_colorful_bg_image_uri"), false));
        }
        evVar.a(mapM419a);
        return evVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x013c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x027f  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.xiaomi.push.service.ac.b a(android.content.Context r31, com.xiaomi.push.Cif r32, byte[] r33, android.widget.RemoteViews r34, android.app.PendingIntent r35, int r36) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1175
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ac.a(android.content.Context, com.xiaomi.push.if, byte[], android.widget.RemoteViews, android.app.PendingIntent, int):com.xiaomi.push.service.ac$b");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0401 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02d9 A[ADDED_TO_REGION] */
    /* renamed from: a, reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.xiaomi.push.service.ac.c m576a(android.content.Context r20, com.xiaomi.push.Cif r21, byte[] r22) {
        /*
            Method dump skipped, instructions count: 1050
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ac.m576a(android.content.Context, com.xiaomi.push.if, byte[]):com.xiaomi.push.service.ac$c");
    }

    /* renamed from: a, reason: collision with other method in class */
    private static String m577a(Context context, String str, Map<String, String> map) {
        return (map == null || TextUtils.isEmpty(map.get("channel_name"))) ? com.xiaomi.push.g.m348b(context, str) : map.get("channel_name");
    }

    static String a(Cif cif) {
        hw hwVarM461a;
        if ("com.xiaomi.xmsf".equals(cif.f638b) && (hwVarM461a = cif.m461a()) != null && hwVarM461a.m419a() != null) {
            String str = hwVarM461a.m419a().get("miui_package_name");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return cif.f638b;
    }

    private static String a(Map<String, String> map, String str) {
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    private static void a(Notification notification, int i) {
        Object objA = com.xiaomi.push.bh.a(notification, "extraNotification");
        if (objA != null) {
            com.xiaomi.push.bh.a(objA, "setMessageCount", Integer.valueOf(i));
        }
    }

    private static void a(Context context, Intent intent, Cif cif, hw hwVar) {
        if (cif == null || hwVar == null) {
            return;
        }
        String strA = a(hwVar.m419a(), "notify_effect");
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        if (az.a.equals(strA) || az.b.equals(strA) || az.c.equals(strA)) {
            intent.putExtra("local_paid", cif.f634a);
            if (!TextUtils.isEmpty(cif.f638b)) {
                intent.putExtra("target_package", cif.f638b);
            }
            intent.putExtra("job_key", a(hwVar.m419a(), "jobkey"));
            intent.putExtra("target_component", a(context, cif.f638b, hwVar.m419a()));
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m578a(Context context, String str) {
        a(context, str, -1);
    }

    public static void a(Context context, String str, int i) {
        ao aoVarA = ao.a(context, str);
        int iHashCode = ((str.hashCode() / 10) * 10) + i;
        LinkedList linkedList = new LinkedList();
        if (i >= 0) {
            aoVarA.a(iHashCode);
        }
        synchronized (f865a) {
            Iterator<Pair<Integer, Cif>> it = f865a.iterator();
            while (it.hasNext()) {
                Pair<Integer, Cif> next = it.next();
                Cif cif = (Cif) next.second;
                if (cif != null) {
                    String strA = a(cif);
                    if (i >= 0) {
                        if (iHashCode == ((Integer) next.first).intValue() && TextUtils.equals(strA, str)) {
                            linkedList.add(next);
                        }
                    } else if (i == -1 && TextUtils.equals(strA, str)) {
                        aoVarA.a(((Integer) next.first).intValue());
                        linkedList.add(next);
                    }
                }
            }
            if (f865a != null) {
                f865a.removeAll(linkedList);
                a(context, (LinkedList<? extends Object>) linkedList);
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        synchronized (f865a) {
            Iterator<Pair<Integer, Cif>> it = f865a.iterator();
            while (it.hasNext()) {
                Pair<Integer, Cif> next = it.next();
                Cif cif = (Cif) next.second;
                if (cif != null) {
                    String strA = a(cif);
                    hw hwVarM461a = cif.m461a();
                    if (hwVarM461a != null && TextUtils.equals(strA, str)) {
                        String strM426c = hwVarM461a.m426c();
                        String strD = hwVarM461a.d();
                        if (!TextUtils.isEmpty(strM426c) && !TextUtils.isEmpty(strD) && a(str2, strM426c) && a(str3, strD)) {
                            ao.a(context, str).a(((Integer) next.first).intValue());
                            linkedList.add(next);
                        }
                    }
                }
            }
            if (f865a != null) {
                f865a.removeAll(linkedList);
                a(context, (LinkedList<? extends Object>) linkedList);
            }
        }
    }

    public static void a(Context context, LinkedList<? extends Object> linkedList) {
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        bo.a(context, "category_clear_notification", "clear_notification", linkedList.size(), "");
    }

    private static void a(Intent intent) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Method declaredMethod = intent.getClass().getDeclaredMethod("addMiuiFlags", Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(intent, 2);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.b("insert flags error " + e);
        }
    }

    @TargetApi(16)
    private static void a(eu euVar, Context context, String str, Cif cif, byte[] bArr, int i) {
        PendingIntent pendingIntentA;
        PendingIntent pendingIntentA2;
        PendingIntent pendingIntentA3;
        Map<String, String> mapM419a = cif.m461a().m419a();
        if (TextUtils.equals("3", mapM419a.get("notification_style_type")) || TextUtils.equals(DownloadConstants.DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR, mapM419a.get("notification_style_type"))) {
            return;
        }
        if (!TextUtils.isEmpty(mapM419a.get("notification_style_button_left_name")) && (pendingIntentA3 = a(context, str, cif, bArr, i, 1)) != null) {
            euVar.addAction(0, mapM419a.get("notification_style_button_left_name"), pendingIntentA3);
        }
        if (!TextUtils.isEmpty(mapM419a.get("notification_style_button_mid_name")) && (pendingIntentA2 = a(context, str, cif, bArr, i, 2)) != null) {
            euVar.addAction(0, mapM419a.get("notification_style_button_mid_name"), pendingIntentA2);
        }
        if (TextUtils.isEmpty(mapM419a.get("notification_style_button_right_name")) || (pendingIntentA = a(context, str, cif, bArr, i, 3)) == null) {
            return;
        }
        euVar.addAction(0, mapM419a.get("notification_style_button_right_name"), pendingIntentA);
    }

    private static boolean a(Context context, Cif cif, String str) {
        if (cif != null && cif.m461a() != null && cif.m461a().m419a() != null && !TextUtils.isEmpty(str)) {
            return Boolean.parseBoolean(cif.m461a().m419a().get("use_clicked_activity")) && g.a(context, a(str));
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("should clicked activity params are null.");
        return false;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m580a(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(hw hwVar) {
        if (hwVar == null) {
            return false;
        }
        String strM418a = hwVar.m418a();
        return !TextUtils.isEmpty(strM418a) && strM418a.length() == 22 && "satuigmo".indexOf(strM418a.charAt(0)) >= 0;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m581a(Cif cif) {
        hw hwVarM461a = cif.m461a();
        return a(hwVarM461a) && hwVarM461a.l();
    }

    private static boolean a(String str, String str2) {
        return TextUtils.isEmpty(str) || str2.contains(str);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m582a(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return "1".equals(map.get("notify_foreground"));
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0072 A[PHI: r0 r3
  0x0072: PHI (r0v4 java.lang.String) = (r0v2 java.lang.String), (r0v5 java.lang.String) binds: [B:18:0x0070, B:10:0x004e] A[DONT_GENERATE, DONT_INLINE]
  0x0072: PHI (r3v15 java.lang.String) = (r3v14 java.lang.String), (r3v21 java.lang.String) binds: [B:18:0x0070, B:10:0x004e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String[] a(android.content.Context r3, com.xiaomi.push.hw r4) {
        /*
            java.lang.String r0 = r4.m426c()
            java.lang.String r1 = r4.d()
            java.util.Map r4 = r4.m419a()
            if (r4 == 0) goto L73
            android.content.res.Resources r2 = r3.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            float r3 = r3.density
            float r2 = (float) r2
            float r2 = r2 / r3
            r3 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r3
            java.lang.Float r3 = java.lang.Float.valueOf(r2)
            int r3 = r3.intValue()
            r2 = 320(0x140, float:4.48E-43)
            if (r3 > r2) goto L51
            java.lang.String r3 = "title_short"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L42
            r0 = r3
        L42:
            java.lang.String r3 = "description_short"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L73
            goto L72
        L51:
            r2 = 360(0x168, float:5.04E-43)
            if (r3 <= r2) goto L73
            java.lang.String r3 = "title_long"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L64
            r0 = r3
        L64:
            java.lang.String r3 = "description_long"
            java.lang.Object r3 = r4.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L73
        L72:
            r1 = r3
        L73:
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]
            r4 = 0
            r3[r4] = r0
            r4 = 1
            r3[r4] = r1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ac.a(android.content.Context, com.xiaomi.push.hw):java.lang.String[]");
    }

    private static int b(Context context, String str) {
        int iA = a(context, str, "mipush_notification");
        int iA2 = a(context, str, "mipush_small_notification");
        if (iA <= 0) {
            iA = iA2 > 0 ? iA2 : context.getApplicationInfo().icon;
        }
        return (iA != 0 || Build.VERSION.SDK_INT < 9) ? iA : context.getApplicationInfo().logo;
    }

    private static int b(Map<String, String> map) {
        if (map == null) {
            return 3;
        }
        String str = map.get("channel_importance");
        if (TextUtils.isEmpty(str)) {
            return 3;
        }
        try {
            com.xiaomi.channel.commonutils.logger.b.c("importance=" + str);
            return Integer.parseInt(str);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d("parsing channel importance error: " + e);
            return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(int i, String str) {
        return "n_top_update_" + i + "_" + str;
    }

    public static String b(Cif cif) {
        return m581a(cif) ? "E100002" : c(cif) ? "E100000" : m585b(cif) ? "E100001" : d(cif) ? "E100003" : "";
    }

    /* renamed from: b, reason: collision with other method in class */
    static void m583b(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    static void b(Context context, String str, int i) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(19)
    public static void b(Context context, String str, int i, String str2, Notification notification) {
        boolean z;
        Notification notification2 = notification;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || ao.a(context, str) == null || Build.VERSION.SDK_INT < 26) {
            return;
        }
        ao aoVarA = ao.a(context, str);
        if (notification2 == null) {
            List<StatusBarNotification> listM601b = aoVarA.m601b();
            if (listM601b == null) {
                return;
            }
            Iterator<StatusBarNotification> it = listM601b.iterator();
            while (true) {
                if (!it.hasNext()) {
                    notification2 = null;
                    break;
                }
                StatusBarNotification next = it.next();
                Notification notification3 = next.getNotification();
                String string = notification3.extras.getString("message_id");
                if (i == next.getId() && str2.equals(string)) {
                    notification2 = notification3;
                    break;
                }
            }
            z = false;
        } else if (!str2.equals(notification2.extras.getString("message_id"))) {
            return;
        } else {
            z = true;
        }
        if (notification2 != null) {
            if (notification2.getGroupAlertBehavior() != 1) {
                com.xiaomi.push.bh.a((Object) notification2, "mGroupAlertBehavior", (Object) 1);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = notification2.extras.getLong("mipush_org_when", 0L);
            int i2 = notification2.extras.getInt("mipush_n_top_fre", 0);
            int i3 = notification2.extras.getInt("mipush_n_top_prd", 0);
            if (i3 <= 0 || i3 < i2) {
                return;
            }
            long j2 = (i3 * 1000) + j;
            if (j >= jCurrentTimeMillis || jCurrentTimeMillis >= j2) {
                i3 = 0;
            } else if (i2 > 0) {
                int iMin = (int) Math.min((j2 - jCurrentTimeMillis) / 1000, i2);
                if (iMin > 0 && !z) {
                    notification2.when = jCurrentTimeMillis;
                    com.xiaomi.channel.commonutils.logger.b.m22a("update top notification: " + str2);
                    aoVarA.a(i, notification2);
                }
                i3 = iMin;
            }
            if (i3 > 0) {
                com.xiaomi.channel.commonutils.logger.b.m22a("schedule top notification next update delay: " + i3);
                com.xiaomi.push.ai.a(context).m94a(b(i, str2));
                com.xiaomi.push.ai.a(context).b(a(context, str, i, str2, (Notification) null), i3);
                return;
            }
            String strA = ak.a(aoVarA);
            Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(context, notification2);
            builderRecoverBuilder.setChannelId(strA);
            builderRecoverBuilder.setPriority(0);
            com.xiaomi.channel.commonutils.logger.b.m22a("update top notification to common: " + str2);
            aoVarA.a(i, builderRecoverBuilder.build());
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    static boolean m584b(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    /* renamed from: b, reason: collision with other method in class */
    public static boolean m585b(Cif cif) {
        hw hwVarM461a = cif.m461a();
        return a(hwVarM461a) && hwVarM461a.f546b == 1 && !m581a(cif);
    }

    /* renamed from: b, reason: collision with other method in class */
    private static boolean m586b(Map<String, String> map) {
        if (map != null) {
            String str = map.get("notification_top_repeat");
            if (!TextUtils.isEmpty(str)) {
                boolean z = Boolean.parseBoolean(str);
                com.xiaomi.channel.commonutils.logger.b.c("top notification' repeat is " + z);
                return z;
            }
        }
        return true;
    }

    private static int c(Map<String, String> map) {
        if (map == null) {
            return 0;
        }
        String str = map.get("notification_priority");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            com.xiaomi.channel.commonutils.logger.b.c("priority=" + str);
            return Integer.parseInt(str);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d("parsing notification priority error: " + e);
            return 0;
        }
    }

    public static boolean c(Cif cif) {
        hw hwVarM461a = cif.m461a();
        return a(hwVarM461a) && hwVarM461a.f546b == 0 && !m581a(cif);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int d(java.util.Map<java.lang.String, java.lang.String> r3) throws java.lang.NumberFormatException {
        /*
            r0 = 0
            if (r3 == 0) goto L3f
            java.lang.String r1 = "notification_top_frequency"
            java.lang.Object r3 = r3.get(r1)
            java.lang.String r3 = (java.lang.String) r3
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 != 0) goto L3f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L2a
            r1.<init>()     // Catch: java.lang.Exception -> L2a
            java.lang.String r2 = "fre of top notification is "
            r1.append(r2)     // Catch: java.lang.Exception -> L2a
            r1.append(r3)     // Catch: java.lang.Exception -> L2a
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L2a
            com.xiaomi.channel.commonutils.logger.b.c(r1)     // Catch: java.lang.Exception -> L2a
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Exception -> L2a
            goto L40
        L2a:
            r3 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "parsing top notification frequency error: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r3)
        L3f:
            r3 = 0
        L40:
            int r3 = java.lang.Math.max(r0, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ac.d(java.util.Map):int");
    }

    public static boolean d(Cif cif) {
        return cif.a() == hj.Registration;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int e(java.util.Map<java.lang.String, java.lang.String> r3) throws java.lang.NumberFormatException {
        /*
            r0 = 0
            if (r3 == 0) goto L3f
            java.lang.String r1 = "notification_top_period"
            java.lang.Object r3 = r3.get(r1)
            java.lang.String r3 = (java.lang.String) r3
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 != 0) goto L3f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L2a
            r1.<init>()     // Catch: java.lang.Exception -> L2a
            java.lang.String r2 = "prd of top notification is "
            r1.append(r2)     // Catch: java.lang.Exception -> L2a
            r1.append(r3)     // Catch: java.lang.Exception -> L2a
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L2a
            com.xiaomi.channel.commonutils.logger.b.c(r1)     // Catch: java.lang.Exception -> L2a
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Exception -> L2a
            goto L40
        L2a:
            r3 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "parsing top notification period error: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r3)
        L3f:
            r3 = 0
        L40:
            int r3 = java.lang.Math.max(r0, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ac.e(java.util.Map):int");
    }

    public static boolean e(Cif cif) {
        return m581a(cif) || c(cif) || m585b(cif);
    }
}
