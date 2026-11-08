package com.kwai.sodler.kwai;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.kwai.sodler.lib.ext.PluginError;
import com.kwai.sodler.lib.ext.c;
import com.kwai.sodler.lib.h;
import com.kwai.sodler.lib.j;
import com.kwai.sodler.lib.kwai.f;
import com.kwai.sodler.lib.kwai.g;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static boolean a = false;

    public static synchronized void a(Context context) {
        if (a) {
            return;
        }
        j.a().a(context, new c.a().a("sodler").a(3).a(false).a());
        j.a().d().a(new g.a() { // from class: com.kwai.sodler.kwai.b.1
            @Override // com.kwai.sodler.lib.kwai.g.a
            public void a(@NonNull f fVar, File file) throws IOException {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Log.d("Sodler.helper", "==============start download:" + fVar);
                g.a aVarI = j.a().i();
                if (aVarI == null) {
                    return;
                }
                aVarI.a(fVar, file);
                try {
                    try {
                        if (!TextUtils.isEmpty(fVar.p()) && !TextUtils.equals(com.kwai.sodler.lib.b.b.f(file), fVar.p())) {
                            throw new PluginError.UpdateError("file md5 not equal", -4);
                        }
                        a.a((InputStream) null);
                        a.a((OutputStream) null);
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                        j.a aVarH = j.a().h();
                        if (aVarH != null) {
                            aVarH.a("sodler_download", String.valueOf(jCurrentTimeMillis2));
                        }
                    } catch (Throwable th) {
                        throw new PluginError.UpdateError(th.getMessage(), -4);
                    }
                } catch (Throwable th2) {
                    a.a((InputStream) null);
                    a.a((OutputStream) null);
                    throw th2;
                }
            }
        });
        a = true;
    }

    public static void a(g.a aVar) {
        j.a().a(aVar);
    }

    public static void a(String str, com.kwai.sodler.lib.a.b bVar, com.kwai.sodler.lib.ext.b bVar2) {
        h hVar = new h(bVar, str);
        hVar.a(bVar2);
        j.a().a(hVar, 16);
    }
}
