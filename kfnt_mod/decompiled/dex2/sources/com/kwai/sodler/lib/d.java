package com.kwai.sodler.lib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.core.content.ContextCompat;
import com.kwai.sodler.lib.ext.PluginError;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class d implements com.kwai.sodler.lib.kwai.d {
    private final Context a;
    private final ConcurrentHashMap<String, com.kwai.sodler.lib.kwai.a> b = new ConcurrentHashMap<>();

    d(Context context) {
        this.a = context.getApplicationContext();
    }

    private com.kwai.sodler.lib.kwai.a a(com.kwai.sodler.lib.kwai.f fVar, com.kwai.sodler.lib.kwai.a aVar) throws PluginError.LoadError {
        String strE = aVar.e();
        File file = new File(strE);
        a.b("Sodler.loader", "Loading plugin, path = " + strE);
        com.kwai.sodler.lib.kwai.e eVarB = fVar.b();
        if (!file.exists()) {
            throw new PluginError.LoadError("Apk file not exist.", PluginError.ERROR_INS_NOT_FOUND);
        }
        String strI = fVar.i();
        String strA = fVar.a();
        aVar.b(strI);
        aVar.a(strA);
        if (eVarB.e().c(strI, strA)) {
            String strB = eVarB.e().b(strI, strA);
            if (com.kwai.sodler.lib.b.b.b(strB)) {
                a.b("Sodler.loader", "The current version has been installed before.");
                aVar.c(strB);
                com.kwai.sodler.lib.kwai.a aVarA = a(strI);
                if (aVarA == null) {
                    a.a("Sodler.loader", "Load plugin from installed path.");
                    aVar.a(this.a, strB);
                    a(strI, aVar);
                    return aVar;
                }
                a.b("Sodler.loader", "The current plugin has been loaded, id = " + strA);
                return aVarA;
            }
        }
        com.kwai.sodler.lib.kwai.a aVarA2 = a(strI);
        if (aVarA2 != null) {
            return aVarA2;
        }
        a.b("Sodler.loader", "Load plugin from dest path.");
        String strB2 = eVarB.e().b(aVar);
        aVar.c(strB2);
        a.b("Sodler.loader", "installed ." + strB2);
        aVar.a(this.a, strB2);
        a(strI, aVar);
        if (strE.endsWith(eVarB.b().e())) {
            com.kwai.sodler.lib.b.b.a(strE);
        }
        return aVar;
    }

    private void a(com.kwai.sodler.lib.kwai.f fVar, PluginError pluginError) {
        a.c("Sodler.loader", "onError state = " + fVar.c());
        fVar.a(-5);
        fVar.a(pluginError);
        d(fVar);
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            if ((ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0) && (connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                return 1 == activeNetworkInfo.getType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void b(com.kwai.sodler.lib.kwai.f fVar) {
        a.c("Sodler.loader", "onPreLoad state = " + fVar.c());
        fVar.b().g().e(fVar);
    }

    private void c(com.kwai.sodler.lib.kwai.f fVar) {
        a.c("Sodler.loader", "onCanceled state = " + fVar.c());
        fVar.a(-7);
        fVar.b().g().b(fVar);
    }

    private void d(com.kwai.sodler.lib.kwai.f fVar) {
        PluginError.LoadError loadError;
        a.c("Sodler.loader", "onPostLoad state = " + fVar.c());
        if (fVar.c() == 0) {
            com.kwai.sodler.lib.kwai.a aVarL = fVar.l();
            if (aVarL != null) {
                fVar.b().g().a(fVar, aVarL);
                return;
            }
            fVar.a(-1);
        }
        if (fVar.g() != null) {
            loadError = new PluginError.LoadError(fVar.g(), PluginError.ERROR_LOA_CREATE_PLUGIN);
        } else {
            loadError = new PluginError.LoadError("Can not get plugin instance " + fVar.c(), PluginError.ERROR_LOA_CREATE_PLUGIN);
        }
        fVar.b().g().a(fVar, loadError);
    }

    public synchronized com.kwai.sodler.lib.kwai.a a(String str) {
        com.kwai.sodler.lib.kwai.a aVar = this.b.get(str);
        if (aVar != null) {
            if (!aVar.b()) {
                return null;
            }
        }
        return aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01fe  */
    @Override // com.kwai.sodler.lib.kwai.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.kwai.sodler.lib.kwai.f a(@androidx.annotation.NonNull com.kwai.sodler.lib.kwai.f r11) {
        /*
            Method dump skipped, instructions count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.sodler.lib.d.a(com.kwai.sodler.lib.kwai.f):com.kwai.sodler.lib.kwai.f");
    }

    public synchronized void a(String str, com.kwai.sodler.lib.kwai.a aVar) {
        if (aVar != null) {
            if (aVar.b()) {
                this.b.put(str, aVar);
            }
        }
    }
}
