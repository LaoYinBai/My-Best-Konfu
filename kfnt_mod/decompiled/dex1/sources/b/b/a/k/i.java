package b.b.a.k;

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
public class i {

    /* renamed from: a, reason: collision with root package name */
    public static int f1133a;

    /* renamed from: b, reason: collision with root package name */
    public static String[] f1134b = {"goldfish"};

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f1135c = {"000000000000000", "012345678912345"};

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
                e.printStackTrace();
            }
            return true;
        } catch (FileNotFoundException e2) {
            return !e2.getMessage().contains("No such file or directory");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0314 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0217 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x040b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:298:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b4  */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v57 */
    /* JADX WARN: Type inference failed for: r9v58, types: [int] */
    /* JADX WARN: Type inference failed for: r9v6, types: [int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v70 */
    /* JADX WARN: Type inference failed for: r9v71 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r20, org.json.JSONObject r21, b.b.a.f.h r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1113
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.k.i.a(android.content.Context, org.json.JSONObject, b.b.a.f.h):void");
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
}
