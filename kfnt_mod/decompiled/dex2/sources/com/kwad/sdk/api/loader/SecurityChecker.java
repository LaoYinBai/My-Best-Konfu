package com.kwad.sdk.api.loader;

import android.text.TextUtils;
import com.kwad.sdk.api.loader.a;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class SecurityChecker {
    private static volatile State a;

    public enum State {
        INIT,
        DATA_VALID,
        MD5,
        SUCCESS
    }

    public interface a {
        void a(Exception exc);

        void a(boolean z, State state);
    }

    static void a(final File file, final a.C0023a c0023a, final a aVar) {
        i.a(new Runnable() { // from class: com.kwad.sdk.api.loader.SecurityChecker.1
            @Override // java.lang.Runnable
            public void run() {
                State unused = SecurityChecker.a = State.INIT;
                try {
                    boolean z = c0023a != null && SecurityChecker.b(file) && SecurityChecker.b(file, c0023a.c);
                    if (z) {
                        State unused2 = SecurityChecker.a = State.SUCCESS;
                    }
                    if (aVar != null) {
                        aVar.a(z, SecurityChecker.a);
                    }
                } catch (Exception e) {
                    if (aVar != null) {
                        aVar.a(e);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(File file) {
        a = State.DATA_VALID;
        return file != null && file.exists() && file.getName().endsWith(".apk") && file.length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(File file, String str) {
        a = State.MD5;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().equals(p.a(file).toLowerCase());
    }
}
