package com.bytedance.a;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ar {

    /* renamed from: a, reason: collision with root package name */
    public static int f3368a;

    /* renamed from: b, reason: collision with root package name */
    public static String[] f3369b = {"goldfish"};

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f3370c = {"000000000000000", "012345678912345"};

    /* JADX WARN: Removed duplicated region for block: B:119:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x030d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0212 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0404 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:297:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r19, org.json.JSONObject r20, com.bytedance.a.be r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1106
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ar.a(android.content.Context, org.json.JSONObject, com.bytedance.a.be):void");
    }

    public static boolean a() {
        boolean zIsUserAMonkey = ActivityManager.isUserAMonkey();
        return Build.VERSION.SDK_INT >= 11 ? zIsUserAMonkey || ActivityManager.isRunningInTestHarness() : zIsUserAMonkey;
    }

    public static boolean a(Context context) {
        Iterator<Sensor> it = ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).iterator();
        while (it.hasNext()) {
            if (it.next().getType() == 9) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        try {
            try {
                new FileInputStream(file).close();
            } catch (IOException e) {
                de.a("", e);
            }
            return true;
        } catch (FileNotFoundException e2) {
            return !e2.getMessage().contains("No such file or directory");
        }
    }
}
