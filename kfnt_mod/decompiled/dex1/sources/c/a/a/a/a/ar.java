package c.a.a.a.a;

import com.bytedance.JProtect;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ar {
    public static String a() {
        String className;
        String str = "";
        String str2 = "";
        String str3 = "";
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int i = 5;
            int lineNumber = 0;
            while (i < stackTrace.length) {
                try {
                    className = stackTrace[i].getClassName();
                    try {
                        String methodName = stackTrace[i].getMethodName();
                        try {
                            lineNumber = stackTrace[i].getLineNumber();
                        } catch (Throwable unused) {
                        }
                        str3 = methodName;
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    className = str2;
                }
                str2 = className;
                i++;
                str = str + str2 + "." + str3 + ":" + lineNumber + "\n";
            }
        } catch (Throwable unused4) {
        }
        return str;
    }
}
