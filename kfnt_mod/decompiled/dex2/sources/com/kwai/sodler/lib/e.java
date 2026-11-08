package com.kwai.sodler.lib;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwai.sodler.lib.ext.PluginError;
import com.kwai.sodler.lib.kwai.g;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class e implements com.kwai.sodler.lib.kwai.g {
    private final Context a;
    private g.a b;

    e(Context context) {
        this.a = context.getApplicationContext();
    }

    @TargetApi(11)
    private void a(int i, @NonNull com.kwai.sodler.lib.kwai.f fVar) {
        if (i != 0) {
            if (i == -1) {
                a.a("Sodler.update", "Request remote plugin info fail, illegal online plugin.");
                fVar.a(-3);
                fVar.a((PluginError.UpdateError) null);
                return;
            }
            return;
        }
        a.a("Sodler.update", "Using online plugin.");
        com.kwai.sodler.lib.a.b bVarA = f.a(this.a, fVar);
        if (bVarA == null) {
            a.a("Sodler.update", "No available plugin, abort.");
            fVar.a(-3);
            return;
        }
        a.a("Sodler.update", "Download new plugin, version = " + bVarA.b + ", url = " + bVarA.c);
        fVar.a(3);
        fVar.g(bVarA.c);
        fVar.a(bVarA.d);
        fVar.f(bVarA.f);
        fVar.b(bVarA.b);
    }

    private void a(com.kwai.sodler.lib.kwai.f fVar, PluginError.UpdateError updateError) {
        a.c("Sodler.update", "onError state = " + fVar.c());
        fVar.a(-4);
        fVar.a((Throwable) updateError);
        fVar.b(updateError);
        e(fVar);
    }

    private void a(com.kwai.sodler.lib.kwai.f fVar, File file) {
        if (this.b == null) {
            throw new RuntimeException("not yet supported!");
        }
        this.b.a(fVar, file);
    }

    private void c(com.kwai.sodler.lib.kwai.f fVar) {
        a.c("Sodler.update", "onPreUpdate state = " + fVar.c());
        fVar.b().g().c(fVar);
    }

    private void d(com.kwai.sodler.lib.kwai.f fVar) {
        a.c("Sodler.update", "onCanceled state = " + fVar.c());
        fVar.a(-7);
        fVar.b().g().b(fVar);
    }

    private void e(com.kwai.sodler.lib.kwai.f fVar) {
        a.c("Sodler.update", "onPostUpdate state = " + fVar.c());
        fVar.b().g().d(fVar);
    }

    @Override // com.kwai.sodler.lib.kwai.g
    public com.kwai.sodler.lib.kwai.f a(@NonNull com.kwai.sodler.lib.kwai.f fVar) {
        a.c("Sodler.update", "Start update, id = " + fVar.i());
        fVar.c("Update");
        c(fVar);
        b(fVar);
        if (fVar.f()) {
            d(fVar);
            return fVar;
        }
        if (fVar.c() != 2) {
            if (fVar.c() != 3) {
                e(fVar);
                return fVar;
            }
            try {
                fVar.b().e().a();
                try {
                    File fileD = fVar.b().e().d(fVar.i());
                    try {
                        a(fVar, fileD);
                        a.a("Sodler.update", "Download plugin online success.");
                        fVar.d(fileD.getAbsolutePath());
                        fVar.a(1);
                        e(fVar);
                        return fVar;
                    } catch (PluginError.CancelError unused) {
                        d(fVar);
                        return fVar;
                    } catch (PluginError.UpdateError e) {
                        a.a("Sodler.update", "Download plugin fail, error = " + e.getLocalizedMessage());
                        a.a("Sodler.update", e);
                        fVar.a((Throwable) e);
                        a(fVar, e);
                        return fVar;
                    }
                } catch (Throwable th) {
                    a.a("Sodler.update", "Can not get temp file, error = " + th.getLocalizedMessage());
                    a.a("Sodler.update", th);
                    a(fVar, new PluginError.UpdateError(th, 2003));
                    return fVar;
                }
            } catch (Throwable th2) {
                a.a("Sodler.update", th2);
                a(fVar, new PluginError.UpdateError(th2, 2005));
                return fVar;
            }
        }
        try {
            fVar.b().e().a();
            try {
                File fileD2 = fVar.b().e().d(fVar.i());
                int i = 0;
                fVar.b(fVar.b().b().a());
                while (!fVar.f()) {
                    try {
                        com.kwai.sodler.lib.b.b.a(this.a, fVar.o(), fileD2);
                        a.a("Sodler.update", "Extract plugin from assets success.");
                        fVar.d(fileD2.getAbsolutePath());
                        fVar.a(1);
                        e(fVar);
                        return fVar;
                    } catch (Throwable th3) {
                        a.a("Sodler.update", th3);
                        try {
                            fVar.h();
                            StringBuilder sb = new StringBuilder();
                            sb.append("Extract fail, retry ");
                            int i2 = i + 1;
                            sb.append(i);
                            a.a("Sodler.update", sb.toString());
                            fVar.c("Retry extract " + i2);
                            i = i2;
                        } catch (PluginError.RetryError unused2) {
                            a.a("Sodler.update", "Extract plugin from assets fail, error = " + th3.toString());
                            a(fVar, new PluginError.UpdateError(th3, 2004));
                            return fVar;
                        }
                    }
                }
                d(fVar);
                return fVar;
            } catch (Throwable th4) {
                a.a("Sodler.update", "Can not get temp file, error = " + th4.getLocalizedMessage());
                a.a("Sodler.update", th4);
                a(fVar, new PluginError.UpdateError(th4, 2003));
                return fVar;
            }
        } catch (Throwable th5) {
            a.a("Sodler.update", th5);
            a(fVar, new PluginError.UpdateError(th5, 2005));
            return fVar;
        }
    }

    @Override // com.kwai.sodler.lib.kwai.g
    public com.kwai.sodler.lib.kwai.g a(g.a aVar) {
        this.b = aVar;
        return this;
    }

    com.kwai.sodler.lib.kwai.f b(com.kwai.sodler.lib.kwai.f fVar) {
        a.b("Sodler.update", "Request remote plugin info.");
        if (fVar.j()) {
            fVar.b().e().c(fVar.i());
        }
        try {
            if (!fVar.n() && fVar.s() == null) {
                fVar.b().f().a(fVar);
            }
        } catch (Exception e) {
            a.e("Sodler.update", "Request remote plugin info fail, error = " + e.toString());
            a.a("Sodler.update", e);
            fVar.a(-2);
            PluginError.UpdateError updateError = new PluginError.UpdateError(e, PluginError.ERROR_UPD_REQUEST);
            fVar.a((Throwable) updateError);
            fVar.a(updateError);
        }
        if (TextUtils.isEmpty(fVar.i())) {
            a(-1, fVar);
            return fVar;
        }
        a(0, fVar);
        return fVar;
    }
}
