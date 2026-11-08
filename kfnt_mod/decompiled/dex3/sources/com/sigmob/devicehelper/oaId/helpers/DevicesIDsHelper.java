package com.sigmob.devicehelper.oaId.helpers;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.taobao.accs.utl.UtilityImpl;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DevicesIDsHelper {
    private static AppIdsUpdater a = null;
    private static int c = -1;
    private static int d;
    private AppIdsUpdater b;

    public interface AppIdsUpdater {
        void OnIdsAvalid(String str);
    }

    static class IdentifyListenerHandler implements InvocationHandler {
        IdentifyListenerHandler() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws ClassNotFoundException {
            Class<?> cls;
            try {
                if (!"OnSupport".equals(method.getName()) || !((Boolean) objArr[0]).booleanValue()) {
                    return null;
                }
                try {
                    try {
                        cls = Class.forName("com.bun.miitmdid.supplier.IdSupplier");
                    } catch (ClassNotFoundException unused) {
                        cls = Class.forName("com.bun.miitmdid.interfaces.IdSupplier");
                    }
                } catch (ClassNotFoundException unused2) {
                    cls = Class.forName("com.bun.supplier.IdSupplier");
                }
                String str = (String) cls.getDeclaredMethod("getOAID", new Class[0]).invoke(objArr[1], new Object[0]);
                if (DevicesIDsHelper.a != null && !TextUtils.isEmpty(str)) {
                    DevicesIDsHelper.a.OnIdsAvalid(str);
                }
                SigmobLog.e("oaid:" + str);
                return null;
            } catch (Exception unused3) {
                return null;
            }
        }
    }

    private static String a(String str) throws ClassNotFoundException {
        if (str == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls, str, UtilityImpl.NET_TYPE_UNKNOWN);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void a(Context context) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls;
        StringBuilder sb;
        String str;
        try {
            Class<?> cls2 = Class.forName("com.bun.miitmdid.core.JLibrary");
            Field field = cls2.getField("classLoader");
            if (field.get(cls2) == null) {
                cls2.getMethod("InitEntry", Context.class).invoke(cls2, context);
                SigmobLog.e("field:" + field);
            }
        } catch (Exception unused) {
        }
        try {
            try {
                cls = Class.forName("com.bun.miitmdid.core.IIdentifierListener");
            } catch (ClassNotFoundException unused2) {
                cls = Class.forName("com.bun.miitmdid.interfaces.IIdentifierListener");
            }
        } catch (ClassNotFoundException unused3) {
            cls = Class.forName("com.bun.supplier.IIdentifierListener");
        }
        c = ((Integer) Class.forName("com.bun.miitmdid.core.MdidSdkHelper").getDeclaredMethod("InitSdk", Context.class, Boolean.TYPE, cls).invoke(null, context, true, Proxy.newProxyInstance(context.getClassLoader(), new Class[]{cls}, new IdentifyListenerHandler()))).intValue();
        if (c != 0) {
            String strValueOf = String.valueOf(c);
            if (strValueOf.equals("1008614")) {
                sb = new StringBuilder();
                str = "msa sdk error: ";
            } else {
                sb = new StringBuilder();
                str = "无法获取OAID,请确保应用正确集成OAID: ";
            }
            sb.append(str);
            sb.append(strValueOf);
            SigmobLog.e(sb.toString());
        }
    }

    private void a(final Context context, final String str) {
        new Thread(new Runnable() { // from class: com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if ("ASUS".equals(str)) {
                    new a(context).a(DevicesIDsHelper.this.b);
                    return;
                }
                if ("HUAWEI".equals(str)) {
                    new b(context).a(DevicesIDsHelper.this.b);
                    return;
                }
                if ("OPPO".equals(str)) {
                    new g(context).a(DevicesIDsHelper.this.b);
                    return;
                }
                if (RomUtils.ROM_LENOVO.equals(str) || "MOTOLORA".equals(str)) {
                    c cVar = new c(context);
                    cVar.a(DevicesIDsHelper.this.b);
                    return;
                }
                if ("MEIZU".equals(str)) {
                    new d(context).a(DevicesIDsHelper.this.b);
                    return;
                }
                if (RomUtils.ROM_SAMSUNG.equals(str)) {
                    new h(context).a(DevicesIDsHelper.this.b);
                } else if (RomUtils.ROM_ONEPLUS.equals(str)) {
                    new f(context).a(DevicesIDsHelper.this.b);
                } else {
                    k kVar = (RomUtils.ROM_ZTE.equals(str) || "FERRMEOS".equals(str) || DevicesIDsHelper.a() || "SSUI".equals(str) || !DevicesIDsHelper.b()) ? new k(context) : new k(context);
                    kVar.a(DevicesIDsHelper.this.b);
                }
            }
        }).start();
    }

    public static boolean a() throws ClassNotFoundException {
        String strA = a("ro.build.freeme.label");
        return !TextUtils.isEmpty(strA) && strA.equalsIgnoreCase("FREEMEOS");
    }

    public static void b(Context context, AppIdsUpdater appIdsUpdater) {
        try {
            a = appIdsUpdater;
            try {
                a(context);
            } catch (Throwable th) {
                SigmobLog.e("getOAID error " + th.getMessage());
            }
        } catch (Exception e) {
            SigmobLog.e(e.getMessage());
        }
    }

    public static boolean b() throws ClassNotFoundException {
        String strA = a("ro.ssui.product");
        return (TextUtils.isEmpty(strA) || strA.equalsIgnoreCase(UtilityImpl.NET_TYPE_UNKNOWN)) ? false : true;
    }

    private static String d() {
        return Build.MANUFACTURER.toUpperCase();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.content.Context r3, com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.AppIdsUpdater r4) throws android.os.RemoteException, java.lang.ClassNotFoundException {
        /*
            r2 = this;
            int r0 = com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.d
            r1 = 10
            if (r0 <= r1) goto L7
            return
        L7:
            int r0 = com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.d
            int r0 = r0 + 1
            com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.d = r0
            r2.b = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "getManufacturer ===> "
            r4.append(r0)
            java.lang.String r0 = d()
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.sigmob.logger.SigmobLog.d(r4)
            java.lang.String r4 = d()
            java.lang.String r4 = r4.toUpperCase()
            java.lang.String r0 = "ASUS"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L3c
        L37:
            r2.a(r3, r4)
            goto Le9
        L3c:
            java.lang.String r0 = "HUAWEI"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L45
            goto L37
        L45:
            java.lang.String r0 = "LENOVO"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L4e
            goto L37
        L4e:
            java.lang.String r0 = "MOTOLORA"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L57
            goto L37
        L57:
            java.lang.String r0 = "MEIZU"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L60
            goto L37
        L60:
            java.lang.String r0 = "NUBIA"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L73
            com.sigmob.devicehelper.oaId.helpers.e r4 = new com.sigmob.devicehelper.oaId.helpers.e
            r4.<init>(r3)
            java.lang.String r3 = r4.a()
            goto Lea
        L73:
            java.lang.String r0 = "OPPO"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L7c
            goto L37
        L7c:
            java.lang.String r0 = "SAMSUNG"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L85
            goto L37
        L85:
            java.lang.String r0 = "VIVO"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L97
            com.sigmob.devicehelper.oaId.helpers.i r4 = new com.sigmob.devicehelper.oaId.helpers.i
            r4.<init>(r3)
            java.lang.String r3 = r4.a()
            goto Lea
        L97:
            java.lang.String r0 = "XIAOMI"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto La9
            com.sigmob.devicehelper.oaId.helpers.j r4 = new com.sigmob.devicehelper.oaId.helpers.j
            r4.<init>(r3)
        La4:
            java.lang.String r3 = r4.a()
            goto Lea
        La9:
            java.lang.String r0 = "BLACKSHARK"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto Lb7
            com.sigmob.devicehelper.oaId.helpers.j r4 = new com.sigmob.devicehelper.oaId.helpers.j
            r4.<init>(r3)
            goto La4
        Lb7:
            java.lang.String r0 = "ONEPLUS"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto Lc1
            goto L37
        Lc1:
            java.lang.String r0 = "ZTE"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto Lcb
            goto L37
        Lcb:
            java.lang.String r0 = "FERRMEOS"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L37
            boolean r0 = a()
            if (r0 == 0) goto Ldb
            goto L37
        Ldb:
            java.lang.String r0 = "SSUI"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L37
            boolean r0 = b()
            goto L37
        Le9:
            r3 = 0
        Lea:
            com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper$AppIdsUpdater r4 = r2.b
            if (r4 == 0) goto Lf3
            com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper$AppIdsUpdater r4 = r2.b
            r4.OnIdsAvalid(r3)
        Lf3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.a(android.content.Context, com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper$AppIdsUpdater):void");
    }
}
