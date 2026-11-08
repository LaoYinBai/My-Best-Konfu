package c.a.a.a.a;

import android.os.Build;
import androidx.annotation.RequiresApi;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class aq {
    public static String a() {
        return Build.VERSION.SDK_INT >= 21 ? c() : b();
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        String str = Build.CPU_ABI;
        if (str != null && !str.isEmpty()) {
            sb.append(Build.CPU_ABI);
        }
        String str2 = Build.CPU_ABI2;
        if (str2 != null && !str2.isEmpty()) {
            sb.append(',');
            sb.append(Build.CPU_ABI2);
        }
        return sb.toString();
    }

    @RequiresApi(21)
    private static String c() {
        String[] strArr = Build.SUPPORTED_ABIS;
        if (strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(',');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
