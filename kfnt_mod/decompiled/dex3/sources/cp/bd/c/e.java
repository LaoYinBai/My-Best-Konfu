package cp.bd.c;

import android.content.Context;
import cp.bd.c.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class e {

    /* renamed from: cp.bd.c.e$1, reason: invalid class name */
    public static class AnonymousClass1 extends d.a {
        @Override // cp.bd.c.d.a
        public final Object a(String str, Object obj) {
            try {
                Context context = c.a().a;
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        }
    }

    /* renamed from: cp.bd.c.e$2, reason: invalid class name */
    public static class AnonymousClass2 extends d.a {
        @Override // cp.bd.c.d.a
        public final Object a(String str, Object obj) {
            Context context = c.a().a;
            return context != null ? context.getPackageName() : "";
        }
    }

    /* renamed from: cp.bd.c.e$3, reason: invalid class name */
    public static class AnonymousClass3 extends d.a {
        @Override // cp.bd.c.d.a
        public final Object a(String str, Object obj) {
            File file = new File(c.a().a.getFilesDir(), ".msdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
    }

    /* renamed from: cp.bd.c.e$4, reason: invalid class name */
    public static class AnonymousClass4 extends d.a {
        @Override // cp.bd.c.d.a
        public final Object a(String str, Object obj) {
            boolean zA;
            try {
                zA = e.a(c.a().a, str, obj.toString());
            } catch (Throwable th) {
                th.printStackTrace();
                zA = false;
            }
            return Boolean.valueOf(zA);
        }
    }

    static boolean a(Context context, String str, String str2) throws IOException {
        try {
            String[] list = context.getAssets().list(str);
            if (list.length > 0) {
                new File(str2).mkdirs();
                for (String str3 : list) {
                    a(context, str + "/" + str3, str2 + "/" + str3);
                }
            } else {
                InputStream inputStreamOpen = context.getAssets().open(str);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
                byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
                while (true) {
                    int i = inputStreamOpen.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
                fileOutputStream.flush();
                inputStreamOpen.close();
                fileOutputStream.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
