package c.a.a.a.a;

import com.bytedance.JProtect;
import java.text.SimpleDateFormat;
import java.util.Date;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ax {
    public static String a() {
        try {
            return String.valueOf(Long.valueOf(new Date().getTime()));
        } catch (Throwable unused) {
            return "exception";
        }
    }

    public static String a(String str) {
        try {
            return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime());
        } catch (Throwable unused) {
            return a();
        }
    }
}
