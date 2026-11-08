package com.huawei.updatesdk.a.a.c.b.a;

import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements a {
    private static final Object a = new Object();
    private static c b;

    public static c b() {
        c cVar;
        synchronized (a) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    public static Object c() throws ClassNotFoundException {
        String str;
        StringBuilder sb;
        String string;
        try {
            Class<?> cls = Class.forName("android.telephony.MSimTelephonyManager");
            return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
        } catch (ClassNotFoundException e) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultMSimTelephonyManager wrong ");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (IllegalAccessException e2) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultMSimTelephonyManager wrong ");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (IllegalArgumentException e3) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultMSimTelephonyManager wrong ");
            string = e3.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (NoSuchMethodException e4) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultMSimTelephonyManager wrong ");
            string = e4.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (InvocationTargetException e5) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultMSimTelephonyManager wrong ");
            string = e5.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (Exception e6) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultMSimTelephonyManager wrong ");
            string = e6.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        }
    }

    @Override // com.huawei.updatesdk.a.a.c.b.a.a
    public int a() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        String str;
        StringBuilder sb;
        String string;
        try {
            Object objC = c();
            if (objC != null) {
                Object objInvoke = objC.getClass().getMethod("getPreferredDataSubscription", new Class[0]).invoke(objC, new Object[0]);
                if (objInvoke instanceof Integer) {
                    return ((Integer) objInvoke).intValue();
                }
            }
            return 0;
        } catch (IllegalAccessException e) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getPreferredDataSubscription wrong ");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return -1;
        } catch (NoSuchMethodException e2) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getPreferredDataSubscription wrong ");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return -1;
        } catch (InvocationTargetException e3) {
            str = "MutiCardHwImpl";
            sb = new StringBuilder();
            sb.append(" getPreferredDataSubscription wrong ");
            string = e3.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return -1;
        }
    }
}
