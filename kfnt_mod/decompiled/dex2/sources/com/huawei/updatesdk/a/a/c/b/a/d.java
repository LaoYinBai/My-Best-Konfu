package com.huawei.updatesdk.a.a.c.b.a;

import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d implements a {
    private static final Object a = new Object();
    private static d b;

    private d() {
    }

    public static d b() {
        d dVar;
        synchronized (a) {
            if (b == null) {
                b = new d();
            }
            dVar = b;
        }
        return dVar;
    }

    private static Object c() throws ClassNotFoundException {
        String str;
        StringBuilder sb;
        String string;
        try {
            Class<?> cls = Class.forName("com.mediatek.telephony.TelephonyManagerEx");
            return cls.getDeclaredMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
        } catch (ClassNotFoundException e) {
            str = "mutiCardMTKImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultTelephonyManagerEx wrong ");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (IllegalAccessException e2) {
            str = "mutiCardMTKImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultTelephonyManagerEx wrong ");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (IllegalArgumentException e3) {
            str = "mutiCardMTKImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultTelephonyManagerEx wrong ");
            string = e3.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (NoSuchMethodException e4) {
            str = "mutiCardMTKImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultTelephonyManagerEx wrong ");
            string = e4.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (SecurityException e5) {
            str = "mutiCardMTKImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultTelephonyManagerEx wrong ");
            string = e5.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (InvocationTargetException e6) {
            str = "mutiCardMTKImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultTelephonyManagerEx wrong ");
            string = e6.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            return null;
        } catch (Exception e7) {
            str = "mutiCardMTKImpl";
            sb = new StringBuilder();
            sb.append(" getDefaultTelephonyManagerEx wrong ");
            string = e7.toString();
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
        Object objC = c();
        if (objC != null) {
            try {
                Object objInvoke = objC.getClass().getMethod("getPreferredDataSubscription", new Class[0]).invoke(objC, new Object[0]);
                if (objInvoke instanceof Integer) {
                    return ((Integer) objInvoke).intValue();
                }
            } catch (IllegalAccessException e) {
                str = "mutiCardMTKImpl";
                sb = new StringBuilder();
                sb.append(" getPreferredDataSubscription wrong ");
                string = e.toString();
                sb.append(string);
                com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
                return -1;
            } catch (NoSuchMethodException e2) {
                str = "mutiCardMTKImpl";
                sb = new StringBuilder();
                sb.append(" getPreferredDataSubscription wrong ");
                string = e2.toString();
                sb.append(string);
                com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
                return -1;
            } catch (InvocationTargetException e3) {
                str = "mutiCardMTKImpl";
                sb = new StringBuilder();
                sb.append(" getPreferredDataSubscription wrong ");
                string = e3.toString();
                sb.append(string);
                com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
                return -1;
            }
        }
        return 0;
    }
}
