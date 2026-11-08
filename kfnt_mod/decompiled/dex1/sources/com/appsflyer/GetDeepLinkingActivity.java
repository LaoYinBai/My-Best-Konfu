package com.appsflyer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetDeepLinkingActivity extends Activity {
    private static String TAG = "AppsFlyerDeepLinkActivity";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        startActivity(new Intent(this, getMainActivityClass()));
        AppsFlyerLib.getInstance().setDeepLinkData(getIntent());
        finish();
    }

    private Class<?> getMainActivityClass() {
        try {
            return Class.forName(getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent().getClassName());
        } catch (Exception unused) {
            Log.e(TAG, "Unable to find Main Activity Class");
            return null;
        }
    }
}
