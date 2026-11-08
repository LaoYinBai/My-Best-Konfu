package com.bytedance.gameprotect;

import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class n {
    static Object a(int i, long j, Object... objArr) {
        switch (i) {
            case 0:
                return "Hello";
            case 1:
                return d(objArr);
            case 2:
                return b(-1L, objArr);
            case 3:
                return c(-1L, objArr);
            case 4:
                k.d();
                break;
            case 5:
                return e(objArr);
            case 6:
                return b(objArr);
            case 7:
                return b(j, objArr);
            case 8:
                return c(j, objArr);
            case 9:
                i(objArr);
                break;
            case 10:
                return c(objArr);
            case 11:
                return a(objArr);
            case 12:
                q.a();
                break;
            case 13:
                return k(objArr);
            case 14:
                return j(objArr);
            case 15:
                return a(j, objArr);
            case 16:
                return f(objArr);
            case 17:
                return d(j, objArr);
            case 18:
                g(objArr);
                break;
            case 19:
                h(objArr);
                break;
            case 20:
                a();
                break;
            default:
                return "Unknown action";
        }
        return 0;
    }

    private static Object a(long j, Object... objArr) {
        if (objArr.length != 1) {
            return "Invalid argument";
        }
        v.a().a(j, (byte[]) objArr[0]);
        return "ok";
    }

    private static String a(Object... objArr) {
        return objArr.length == 0 ? "Invalid argument" : !s.a((String) objArr[0]) ? "false" : "true";
    }

    private static void a() {
        t.a();
    }

    private static Object b(long j, Object... objArr) {
        if (objArr.length < 1 || objArr.length > 3) {
            return "Invalid argument";
        }
        String str = (String) objArr[0];
        Map map = objArr.length > 1 ? (Map) objArr[1] : null;
        Map map2 = objArr.length > 2 ? (Map) objArr[2] : null;
        return j == -1 ? l.a(str, map, map2) : l.a(j, str, (Map<String, Object>) map, (Map<String, Object>) map2);
    }

    private static Object b(Object... objArr) {
        return objArr.length != 3 ? "Invalid argument" : c.a((String) objArr[0], (String) objArr[1], (String) objArr[2]);
    }

    private static Object c(long j, Object... objArr) {
        if (objArr.length < 2 || objArr.length > 4) {
            return "Invalid argument";
        }
        String str = (String) objArr[0];
        byte[] bArr = (byte[]) objArr[1];
        Map map = objArr.length > 2 ? (Map) objArr[2] : null;
        Map map2 = objArr.length > 3 ? (Map) objArr[2] : null;
        return j == -1 ? l.a(str, bArr, (Map<String, Object>) map, (Map<String, Object>) map2) : l.a(j, str, bArr, map, map2);
    }

    private static Object c(Object... objArr) {
        return objArr.length == 0 ? "Invalid argument" : a.a((String) objArr[0]);
    }

    private static Object d(long j, Object... objArr) {
        return j == 1 ? u.b().c() : j == 2 ? u.b().d() : u.b().a();
    }

    private static String d(Object... objArr) {
        return objArr.length != 1 ? "Invalid argument" : objArr[0].getClass().getName();
    }

    private static Object e(Object... objArr) {
        return objArr.length == 0 ? "Invalid argument" : i.a(Boolean.valueOf((String) objArr[0]).booleanValue());
    }

    private static Object f(Object... objArr) {
        if (objArr.length != 8) {
            return "Invalid argument";
        }
        return u.b().a((String) objArr[0], (String) objArr[1], (String) objArr[2], (String) objArr[3], Integer.valueOf((String) objArr[4]).intValue(), Integer.valueOf((String) objArr[5]).intValue(), Integer.valueOf((String) objArr[6]).intValue(), Boolean.valueOf((String) objArr[7]).booleanValue()) ? "ok" : AccountMonitorConstants.CommonParameter.RESULT_FAIL;
    }

    private static void g(Object... objArr) {
        t.a((String) objArr[0], (Map) objArr[1], (Map) objArr[2]);
    }

    private static void h(Object... objArr) {
        t.a((String) objArr[0], (Map<String, String>) objArr[1]);
    }

    private static void i(Object... objArr) {
        if (objArr.length != 3) {
            return;
        }
        h.a(Integer.valueOf((String) objArr[0]).intValue(), Integer.valueOf((String) objArr[1]).intValue(), (String) objArr[2]);
    }

    private static Object j(Object... objArr) {
        v.a().a((String) objArr[0], (Integer) objArr[1], (String) objArr[2], (Map) objArr[3]);
        return "ok";
    }

    private static Object k(Object... objArr) {
        return objArr.length != 1 ? "Invalid argument" : f.b((String) objArr[0]);
    }
}
