package com.huawei.updatesdk.a.a.c.b.a;

import com.huawei.updatesdk.a.a.c.b.a.a;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static a.EnumC0013a a = a.EnumC0013a.MODE_SUPPORT_UNKNOWN;
    private static a b;

    public static a a() {
        b();
        b = a == a.EnumC0013a.MODE_SUPPORT_MTK_GEMINI ? d.b() : c.b();
        return b;
    }

    public static boolean b() {
        a.EnumC0013a enumC0013a;
        if (a == a.EnumC0013a.MODE_SUPPORT_UNKNOWN) {
            if (d()) {
                enumC0013a = a.EnumC0013a.MODE_SUPPORT_MTK_GEMINI;
            } else if (c()) {
                enumC0013a = a.EnumC0013a.MODE_SUPPORT_HW_GEMINI;
            } else {
                a = a.EnumC0013a.MODE_NOT_SUPPORT_GEMINI;
            }
            a = enumC0013a;
            return true;
        }
        if (a == a.EnumC0013a.MODE_SUPPORT_HW_GEMINI || a == a.EnumC0013a.MODE_SUPPORT_MTK_GEMINI) {
            return true;
        }
        return false;
    }

    public static boolean c() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        String str;
        StringBuilder sb;
        String string;
        boolean zBooleanValue = false;
        try {
            Object objC = c.c();
            if (objC != null) {
                Object objInvoke = objC.getClass().getMethod("isMultiSimEnabled", new Class[0]).invoke(objC, new Object[0]);
                if (objInvoke instanceof Boolean) {
                    zBooleanValue = ((Boolean) objInvoke).booleanValue();
                }
            }
        } catch (IllegalAccessException e) {
            str = "mutiCardFactory";
            sb = new StringBuilder();
            sb.append("MSimTelephonyManager.getDefault().isMultiSimEnabled()");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isHwGeminiSupport1 " + zBooleanValue);
            return zBooleanValue;
        } catch (NoSuchMethodException e2) {
            str = "mutiCardFactory";
            sb = new StringBuilder();
            sb.append("MSimTelephonyManager.getDefault().isMultiSimEnabled()?");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isHwGeminiSupport1 " + zBooleanValue);
            return zBooleanValue;
        } catch (InvocationTargetException e3) {
            str = "mutiCardFactory";
            sb = new StringBuilder();
            sb.append("MSimTelephonyManager.getDefault().isMultiSimEnabled()");
            string = e3.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.d(str, sb.toString());
            com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isHwGeminiSupport1 " + zBooleanValue);
            return zBooleanValue;
        }
        com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isHwGeminiSupport1 " + zBooleanValue);
        return zBooleanValue;
    }

    private static boolean d() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        String str;
        StringBuilder sb;
        String string;
        boolean z;
        try {
            Field declaredField = Class.forName("com.mediatek.common.featureoption.FeatureOption").getDeclaredField("MTK_GEMINI_SUPPORT");
            declaredField.setAccessible(true);
            z = declaredField.getBoolean(null);
        } catch (ClassNotFoundException e) {
            str = "mutiCardFactory";
            sb = new StringBuilder();
            sb.append("FeatureOption.MTK_GEMINI_SUPPORT");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            z = false;
            com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isMtkGeminiSupport " + z);
            return z;
        } catch (IllegalAccessException e2) {
            str = "mutiCardFactory";
            sb = new StringBuilder();
            sb.append("FeatureOption.MTK_GEMINI_SUPPORT");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            z = false;
            com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isMtkGeminiSupport " + z);
            return z;
        } catch (NoSuchFieldException e3) {
            str = "mutiCardFactory";
            sb = new StringBuilder();
            sb.append("FeatureOption.MTK_GEMINI_SUPPORT");
            string = e3.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            z = false;
            com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isMtkGeminiSupport " + z);
            return z;
        } catch (Exception e4) {
            str = "mutiCardFactory";
            sb = new StringBuilder();
            sb.append("FeatureOption.MTK_GEMINI_SUPPORT");
            string = e4.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.b(str, sb.toString());
            z = false;
            com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isMtkGeminiSupport " + z);
            return z;
        }
        com.huawei.updatesdk.a.a.b.a.a.a.a("mutiCardFactory", "isMtkGeminiSupport " + z);
        return z;
    }
}
