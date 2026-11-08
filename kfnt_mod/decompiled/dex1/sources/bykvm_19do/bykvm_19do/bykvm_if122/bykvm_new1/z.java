package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class z implements Thread.UncaughtExceptionHandler {

    /* renamed from: a, reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f2036a = Thread.getDefaultUncaughtExceptionHandler();

    /* renamed from: b, reason: collision with root package name */
    private String f2037b;

    private z() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x011d A[Catch: all -> 0x0136, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x0136, blocks: (B:68:0x0133, B:58:0x011d), top: B:92:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0133 A[Catch: all -> 0x0136, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x0136, blocks: (B:68:0x0133, B:58:0x011d), top: B:92:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0118 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.lang.Thread r10, java.lang.Throwable r11) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.z.a(java.lang.Thread, java.lang.Throwable):void");
    }

    private void b() {
        File externalFilesDir;
        Context contextD = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
        if (contextD == null) {
            return;
        }
        try {
            if ("mounted".equals(Environment.getExternalStorageState()) && (externalFilesDir = contextD.getExternalFilesDir("TTCache")) != null) {
                externalFilesDir.mkdirs();
                this.f2037b = externalFilesDir.getPath();
            }
            if (TextUtils.isEmpty(this.f2037b)) {
                File file = new File(contextD.getFilesDir(), "TTCache");
                file.mkdirs();
                this.f2037b = file.getPath();
            }
        } catch (Throwable unused) {
        }
    }

    public static z c() {
        return new z();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String string;
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            string = stringWriter.toString();
        } catch (Throwable unused) {
        }
        if (string != null ? string.contains("com.bytedance.msdk") : false) {
            a(thread, th);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f2036a;
        if (uncaughtExceptionHandler == null || uncaughtExceptionHandler == this) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }

    private void a() {
        try {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().b();
        } catch (Throwable unused) {
        }
    }
}
