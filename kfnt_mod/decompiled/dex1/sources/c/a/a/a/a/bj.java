package c.a.a.a.a;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bj {
    public static String a(Context context) {
        return "";
    }

    public static String b(Context context) {
        return "[]";
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x007b A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007e  */
    @android.annotation.SuppressLint({"MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(android.content.Context r3) {
        /*
            java.lang.String r0 = "location"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch: java.lang.Throwable -> L78
            android.location.LocationManager r3 = (android.location.LocationManager) r3     // Catch: java.lang.Throwable -> L78
            java.lang.String r0 = "gps"
            android.location.Location r0 = r3.getLastKnownLocation(r0)     // Catch: java.lang.Throwable -> L78
            if (r0 == 0) goto L2e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L78
            r3.<init>()     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = ""
            r3.append(r1)     // Catch: java.lang.Throwable -> L78
            double r1 = r0.getLongitude()     // Catch: java.lang.Throwable -> L78
            r3.append(r1)     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = ","
            r3.append(r1)     // Catch: java.lang.Throwable -> L78
        L26:
            double r0 = r0.getLatitude()     // Catch: java.lang.Throwable -> L78
            r3.append(r0)     // Catch: java.lang.Throwable -> L78
            goto L73
        L2e:
            java.lang.String r0 = "network"
            android.location.Location r0 = r3.getLastKnownLocation(r0)     // Catch: java.lang.Throwable -> L78
            if (r0 == 0) goto L4d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L78
            r3.<init>()     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = ""
            r3.append(r1)     // Catch: java.lang.Throwable -> L78
            double r1 = r0.getLongitude()     // Catch: java.lang.Throwable -> L78
            r3.append(r1)     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = ","
            r3.append(r1)     // Catch: java.lang.Throwable -> L78
            goto L26
        L4d:
            java.lang.String r0 = "passive"
            android.location.Location r3 = r3.getLastKnownLocation(r0)     // Catch: java.lang.Throwable -> L78
            if (r3 == 0) goto L78
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L78
            r0.<init>()     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = ""
            r0.append(r1)     // Catch: java.lang.Throwable -> L78
            double r1 = r3.getLongitude()     // Catch: java.lang.Throwable -> L78
            r0.append(r1)     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = ","
            r0.append(r1)     // Catch: java.lang.Throwable -> L78
            double r1 = r3.getLatitude()     // Catch: java.lang.Throwable -> L78
            r0.append(r1)     // Catch: java.lang.Throwable -> L78
            r3 = r0
        L73:
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L78
            goto L79
        L78:
            r3 = 0
        L79:
            if (r3 != 0) goto L7e
            java.lang.String r3 = ""
            goto L82
        L7e:
            java.lang.String r3 = r3.trim()
        L82:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.bj.c(android.content.Context):java.lang.String");
    }
}
