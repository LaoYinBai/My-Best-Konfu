package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.Context;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f2014a = false;

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                for (File file : externalFilesDir.listFiles()) {
                    if (a(file)) {
                        f2014a = true;
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean a(File file) {
        if (file != null && file.isFile()) {
            return file.getName().equals("gromore.debug");
        }
        return false;
    }
}
