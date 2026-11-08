package com.bytedance.pangle.res;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.util.e;
import com.bytedance.pangle.util.h;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PluginResources extends Resources {
    public PluginResources(Resources resources) {
        super(appendHostRes(resources), resources.getDisplayMetrics(), Zeus.getAppApplication().getResources().getConfiguration());
    }

    public static AssetManager appendHostRes(Resources resources) throws JSONException, NoSuchMethodException, SecurityException {
        File parentFile;
        Application appApplication = Zeus.getAppApplication();
        if (e.f4712a == null && (parentFile = appApplication.getCacheDir().getParentFile()) != null) {
            e.f4712a = parentFile.getAbsolutePath();
        }
        String str = e.f4712a;
        List<String> listB = h.b();
        a aVar = new a();
        AssetManager assets = resources.getAssets();
        AssetManager assets2 = Zeus.getAppApplication().getAssets();
        HashSet hashSet = new HashSet(h.a(assets));
        List<String> listA = h.a(assets2);
        for (String str2 : listB) {
            if (!hashSet.contains(str2)) {
                assets = aVar.a(assets, str2, true);
            }
        }
        for (String str3 : listA) {
            if (TextUtils.isEmpty(str) || !str3.contains(str)) {
                if (!hashSet.contains(str3) && !listB.contains(str3)) {
                    assets = aVar.a(assets, str3, false);
                }
            }
        }
        return assets;
    }
}
