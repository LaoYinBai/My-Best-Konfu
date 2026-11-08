package com.huawei.hms.update.provider;

import android.content.Context;
import android.net.Uri;
import com.huawei.hms.utils.Checker;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class a {
    private Context a;
    private String b;

    a() {
    }

    private static String a(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException unused) {
            return null;
        }
    }

    private static File b(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalFile();
        } catch (IOException unused) {
            return null;
        }
    }

    public void a(Context context) {
        if (this.a == null) {
            Checker.checkNonNull(context, "context must not be null.");
            this.a = context;
        }
    }

    public File a(String str) {
        String strA = a();
        if (strA == null) {
            return null;
        }
        return b(new File(strA, str));
    }

    private String a() {
        String str;
        Context context = (Context) Checker.assertNonNull(this.a, "mContext is null, call setContext first.");
        synchronized (this) {
            if (this.b == null) {
                if (context.getExternalCacheDir() != null) {
                    this.b = a(context.getExternalCacheDir());
                } else {
                    this.b = a(context.getFilesDir());
                }
            }
            str = this.b;
        }
        return str;
    }

    public Uri a(File file, String str) {
        String strB;
        String strA = a(file);
        if (strA == null || (strB = b(strA)) == null) {
            return null;
        }
        return new Uri.Builder().scheme("content").authority(str).encodedPath(strB).build();
    }

    private String b(String str) {
        int length;
        String strA = a();
        if (strA == null || !str.startsWith(strA)) {
            return null;
        }
        if (strA.endsWith("/")) {
            length = strA.length();
        } else {
            length = strA.length() + 1;
        }
        return Uri.encode("ContentUriHelper") + '/' + str.substring(length);
    }

    File a(Uri uri) {
        String strC;
        String encodedPath = uri.getEncodedPath();
        if (encodedPath == null || (strC = c(encodedPath)) == null) {
            return null;
        }
        return b(new File(strC));
    }

    private String c(String str) {
        int iIndexOf;
        String strA;
        String strA2 = a();
        if (strA2 != null && (iIndexOf = str.indexOf(47, 1)) >= 0 && "ContentUriHelper".equals(Uri.decode(str.substring(1, iIndexOf))) && (strA = a(new File(strA2, Uri.decode(str.substring(iIndexOf + 1))))) != null && strA.startsWith(strA2)) {
            return strA;
        }
        return null;
    }
}
