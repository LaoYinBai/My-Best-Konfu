package com.bytedance.push.settings.storage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.push.settings.SettingsManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a implements e {
    @Override // com.bytedance.push.settings.storage.e
    public String a() {
        return "_contains";
    }

    a() {
    }

    static Bundle a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("_storage_key", str);
        bundle.putString("_key", str2);
        return bundle;
    }

    static boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean("_result");
    }

    @Override // com.bytedance.push.settings.storage.e
    public Bundle a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("_storage_key");
        String string2 = bundle.getString("_key");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("_result", SettingsManager.getStorageFactory().a(context, true, string).f(string2));
        return bundle2;
    }
}
