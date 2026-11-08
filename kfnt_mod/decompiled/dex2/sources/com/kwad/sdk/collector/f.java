package com.kwad.sdk.collector;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.utils.ah;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    @Nullable
    public static JSONArray a(Context context) {
        List<g> listC = c(context);
        listC.add(b(context));
        return g.a(listC);
    }

    private static g b(Context context) {
        boolean zA = ah.a(context);
        com.kwad.sdk.core.d.a.a("InfoCollector", "queryAccessibilityServicePermission result: " + zA);
        return new g("android.permission.BIND_ACCESSIBILITY_SERVICE", zA ? g.b : g.c);
    }

    @NonNull
    private static List<g> c(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context == null) {
            return arrayList;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (String str : strArr) {
                    int iA = ah.a(context, str);
                    arrayList.add(new g(str, iA == 0 ? g.b : iA == -1 ? g.c : g.a));
                }
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            com.kwad.sdk.core.d.a.a(e);
            return arrayList;
        }
    }
}
