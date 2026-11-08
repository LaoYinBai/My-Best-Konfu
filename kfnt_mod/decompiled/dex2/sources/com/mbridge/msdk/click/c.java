package com.mbridge.msdk.click;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.b.a;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.aa;
import com.mbridge.msdk.foundation.tools.j;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: CommonClickUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    static Handler a = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.click.c.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            String str;
            String string;
            String str2;
            super.handleMessage(message);
            try {
                if (message.what != 1000) {
                    return;
                }
                int i = message.arg1;
                int i2 = message.arg2;
                Bundle data = message.getData();
                if (data != null) {
                    String string2 = data.getString("rid");
                    String string3 = data.getString("rid_n");
                    string = data.getString(BidResponsedEx.KEY_CID);
                    str = string2;
                    str2 = string3;
                } else {
                    str = "";
                    string = "";
                    str2 = "";
                }
                new com.mbridge.msdk.foundation.same.report.c(com.mbridge.msdk.foundation.controller.a.f().j()).a(i, i2, str, str2, string);
            } catch (Throwable th) {
                if (MBridgeConstans.DEBUG) {
                    th.printStackTrace();
                }
            }
        }
    };
    private static volatile boolean b = false;

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.lastIndexOf("/") == -1) {
            return str.hashCode() + "";
        }
        return (str.hashCode() + str.substring(str.lastIndexOf("/") + 1).hashCode()) + "";
    }

    public static void a(Context context, String str) {
        if (str == null || context == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(268435456);
            ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, C.DEFAULT_BUFFER_SEGMENT_SIZE);
            if (resolveInfoResolveActivity != null) {
                intent.setClassName(resolveInfoResolveActivity.activityInfo.packageName, resolveInfoResolveActivity.activityInfo.name);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void b(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            Class.forName("com.mbridge.msdk.activity.MBCommonActivity");
            Intent intent = new Intent(context, Class.forName("com.mbridge.msdk.activity.MBCommonActivity"));
            intent.putExtra("url", str);
            if (!(context instanceof ContextThemeWrapper)) {
                intent.setFlags(268435456);
            }
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            s.d("CommonClickUtil", e.getMessage());
        } catch (Throwable th) {
            s.d("CommonClickUtil", th.getMessage());
        }
    }

    public static void a(Activity activity, int i, boolean z, Context context) {
        s.a("======", "======onActivityStarted:" + i + "---" + z);
        if (Build.VERSION.SDK_INT < 29 || z || i != 0) {
            return;
        }
        s.a("======", "======onActivityStarted:inner");
        com.mbridge.msdk.foundation.entity.g gVarA = com.mbridge.msdk.foundation.db.e.a(i.a(context)).a();
        if (gVarA != null) {
            s.a("======", "======dlinfo:" + gVarA.toString());
            String strA = gVarA.a();
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            File file = new File(strA);
            if (file.exists()) {
                String strC = gVarA.c();
                a(context, Uri.fromFile(file), gVarA.b(), gVarA.c());
                com.mbridge.msdk.foundation.same.report.d.a(context, strC, gVarA.d(), gVarA.e(), gVarA.f(), gVarA.h(), gVarA.g());
            }
        }
    }

    public static void c(Context context, String str) {
        com.mbridge.msdk.foundation.db.e eVarA = com.mbridge.msdk.foundation.db.e.a(i.a(context));
        int iC = eVarA.c(str);
        if ((Build.VERSION.SDK_INT >= 29 && com.mbridge.msdk.foundation.controller.a.f().b() == 0) && iC == 1) {
            eVarA.a(str);
        }
        if (iC == 10) {
            eVarA.a(str, 11);
        }
    }

    public static boolean a(CampaignEx campaignEx) throws NoSuchAlgorithmException {
        if (campaignEx != null) {
            String akdlui = campaignEx.getAkdlui();
            if (TextUtils.isEmpty(akdlui)) {
                akdlui = SameMD5.getMD5(campaignEx.getClickURL());
            }
            if (!TextUtils.isEmpty(y.b(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui, "").toString())) {
                return !new File(r7).exists();
            }
            int iIntValue = ((Integer) y.b(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui + "process", 0)).intValue();
            int iMyPid = Process.myPid();
            if (iIntValue != 0 && iIntValue == iMyPid) {
                if (((Long) y.b(com.mbridge.msdk.foundation.controller.a.f().j(), akdlui + "isDowning", 0L)).longValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void a(Context context, Uri uri, String str, String str2) {
        File file;
        int iIntValue;
        int iIntValue2;
        String str3;
        String str4;
        String str5;
        if (b) {
            synchronized (c.class) {
                if (b) {
                    return;
                }
            }
        }
        b = true;
        Uri uri2 = null;
        try {
            file = new File(new URI(uri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            file = null;
        }
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        try {
            try {
                if (!aa.b(context)) {
                    a(context, str, str2);
                } else if (aa.d(context)) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        try {
                            uri2 = (Uri) Class.forName("com.mbridge.msdk.foundation.tools.MBFileProvider").getMethod("getUriForFile", Context.class, String.class, File.class).invoke(null, context, context.getApplicationContext().getPackageName() + ".mbFileProvider", file);
                        } catch (ClassNotFoundException e2) {
                            e2.printStackTrace();
                        } catch (IllegalAccessException e3) {
                            e3.printStackTrace();
                        } catch (IllegalArgumentException e4) {
                            e4.printStackTrace();
                        } catch (NoSuchMethodException e5) {
                            e5.printStackTrace();
                        } catch (SecurityException e6) {
                            e6.printStackTrace();
                        } catch (InvocationTargetException e7) {
                            e7.printStackTrace();
                        }
                        if (uri2 != null) {
                            intent.addFlags(1);
                            intent.setDataAndType(uri2, context.getContentResolver().getType(uri2));
                            c(context, str2);
                            context.startActivity(intent);
                        } else {
                            a(context, str, str2);
                        }
                    } else {
                        intent.setDataAndType(Uri.fromFile(file), a(file));
                        c(context, str2);
                        context.startActivity(intent);
                    }
                } else if (aa.e(context)) {
                    a(context, str, str2);
                } else {
                    intent.setDataAndType(Uri.fromFile(file), a(file));
                    c(context, str2);
                    context.startActivity(intent);
                }
                b = false;
                iIntValue = ((Integer) y.b(context, str2 + "downloadType", -1)).intValue();
                iIntValue2 = ((Integer) y.b(context, str2 + "linkType", -1)).intValue();
                str3 = (String) y.b(context, str2 + "rid", "");
                str4 = (String) y.b(context, str2 + "rid_n", "");
                str5 = (String) y.b(context, str2 + BidResponsedEx.KEY_CID, "");
                if (iIntValue == 3) {
                    return;
                }
            } catch (Throwable th) {
                b = false;
                int iIntValue3 = ((Integer) y.b(context, str2 + "downloadType", -1)).intValue();
                int iIntValue4 = ((Integer) y.b(context, str2 + "linkType", -1)).intValue();
                String str6 = (String) y.b(context, str2 + "rid", "");
                String str7 = (String) y.b(context, str2 + "rid_n", "");
                String str8 = (String) y.b(context, str2 + BidResponsedEx.KEY_CID, "");
                if (iIntValue3 != 3) {
                    a(iIntValue3, iIntValue4, str6, str7, str8);
                }
                throw th;
            }
        } catch (Exception e8) {
            e8.printStackTrace();
            a(context, str, str2);
            b = false;
            iIntValue = ((Integer) y.b(context, str2 + "downloadType", -1)).intValue();
            iIntValue2 = ((Integer) y.b(context, str2 + "linkType", -1)).intValue();
            str3 = (String) y.b(context, str2 + "rid", "");
            str4 = (String) y.b(context, str2 + "rid_n", "");
            str5 = (String) y.b(context, str2 + BidResponsedEx.KEY_CID, "");
            if (iIntValue == 3) {
                return;
            }
        } catch (Throwable unused) {
            a(context, str, str2);
            b = false;
            iIntValue = ((Integer) y.b(context, str2 + "downloadType", -1)).intValue();
            iIntValue2 = ((Integer) y.b(context, str2 + "linkType", -1)).intValue();
            str3 = (String) y.b(context, str2 + "rid", "");
            str4 = (String) y.b(context, str2 + "rid_n", "");
            str5 = (String) y.b(context, str2 + BidResponsedEx.KEY_CID, "");
            if (iIntValue == 3) {
                return;
            }
        }
        a(iIntValue, iIntValue2, str3, str4, str5);
    }

    private static void a(int i, int i2, String str, String str2, String str3) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new com.mbridge.msdk.foundation.same.report.c(com.mbridge.msdk.foundation.controller.a.f().j()).a(i, i2, str, str2, str3);
            } else {
                Message messageObtain = Message.obtain();
                messageObtain.what = 1000;
                messageObtain.arg1 = i;
                messageObtain.arg2 = i2;
                Bundle bundle = new Bundle();
                bundle.putString("rid", str);
                bundle.putString("rid_n", str2);
                bundle.putString(BidResponsedEx.KEY_CID, str3);
                messageObtain.setData(bundle);
                a.sendMessage(messageObtain);
            }
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), str2 + "downloadType", 3);
            a(context, str);
            a(((Integer) y.b(context, str2 + "downloadType", -1)).intValue(), ((Integer) y.b(context, str2 + "linkType", -1)).intValue(), (String) y.b(context, str2 + "rid", ""), (String) y.b(context, str2 + "rid_n", ""), (String) y.b(context, str2 + BidResponsedEx.KEY_CID, ""));
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }

    private static String a(File file) {
        String name = file.getName();
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase());
    }

    public static boolean d(Context context, String str) throws PackageManager.NameNotFoundException {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 8192);
            if (packageInfo != null) {
                return str.equals(packageInfo.packageName);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean e(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Uri uri = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.setData(uri);
            if ((uri.getScheme().startsWith(n.b("L7QthdSsLkP8")) || j.a().a(str)) && a(context, intent)) {
                return true;
            }
            intent.setFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            s.b("SDKCLICK", th.getMessage(), th);
            return false;
        }
    }

    private static boolean a(Context context, Intent intent) {
        try {
            if (context instanceof Activity) {
                context.startActivity(intent);
                return true;
            }
            if (com.mbridge.msdk.foundation.controller.a.f().c() == null) {
                return false;
            }
            com.mbridge.msdk.foundation.controller.a.f().c().startActivity(intent);
            return true;
        } catch (Throwable th) {
            s.b("SDKCLICK", th.getMessage(), th);
            return false;
        }
    }

    public static void b(final CampaignEx campaignEx) {
        com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.click.c.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (campaignEx == null || TextUtils.isEmpty(campaignEx.getDeepLinkURL())) {
                        return;
                    }
                    String deepLinkURL = campaignEx.getDeepLinkURL();
                    if (Uri.parse(deepLinkURL).getScheme().startsWith(n.b("L7QthdSsLkP8")) || j.a().a(deepLinkURL)) {
                        com.mbridge.msdk.foundation.db.e.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(campaignEx);
                    }
                } catch (Throwable th) {
                    s.d("SDKCLICK", th.getMessage());
                }
            }
        });
    }

    public static void f(Context context, String str) {
        Intent launchIntentForPackage;
        List<ResolveInfo> listQueryIntentActivities;
        ResolveInfo next;
        try {
            if (TextUtils.isEmpty(str) || !d(context, str) || (launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str)) == null || (listQueryIntentActivities = context.getPackageManager().queryIntentActivities(launchIntentForPackage, 0)) == null || listQueryIntentActivities.size() <= 0 || (next = listQueryIntentActivities.iterator().next()) == null) {
                return;
            }
            ComponentName componentName = new ComponentName(next.activityInfo.packageName, next.activityInfo.name);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "The app connot start up", 0).show();
            e.printStackTrace();
        }
    }

    private static String a(String str, List<String> list, String str2) {
        if (list != null) {
            for (String str3 : list) {
                if (!TextUtils.isEmpty(str3)) {
                    str = str.replaceAll(str3, str2);
                }
            }
        }
        return str;
    }

    public static String a(String str, String str2, String str3) {
        Map<String, a.C0102a> mapAf;
        try {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            String host = Uri.parse(str).getHost();
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null || (mapAf = aVarB.af()) == null || TextUtils.isEmpty(host)) {
                return str;
            }
            Iterator<Map.Entry<String, a.C0102a>> it = mapAf.entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                if (!TextUtils.isEmpty(key) && host.contains(key)) {
                    a.C0102a c0102a = mapAf.get(key);
                    String strA = a(str, c0102a.d(), String.valueOf(o.x(com.mbridge.msdk.foundation.controller.a.f().j())));
                    try {
                        str = a(strA, c0102a.c(), String.valueOf(o.w(com.mbridge.msdk.foundation.controller.a.f().j())));
                        String strA2 = a(str, c0102a.a(), str2);
                        try {
                            return a(strA2, c0102a.b(), str3);
                        } catch (Exception e) {
                            e = e;
                            str = strA2;
                            e.printStackTrace();
                            return str;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        str = strA;
                    }
                }
            }
            return str;
        } catch (Exception e3) {
            e = e3;
        }
    }
}
