package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PurchaseActivity extends Activity {
    protected static final String TAG = "UnityIAP";
    private boolean receivedResult;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "Creating purchase activity");
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("vr", false)) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT >= 11) {
                decorView.setSystemUiVisibility(6);
            }
        }
        String string = getIntent().getExtras().getString("productId");
        String string2 = getIntent().getExtras().getString("developerPayload");
        String string3 = getIntent().getExtras().getString("type");
        String string4 = getIntent().getExtras().getString("oldSkuMetadata");
        String string5 = getIntent().getExtras().getString("newSku");
        if (string3 != null && string3.equals("subscription_update")) {
            if (GooglePlayPurchasing.ContinueSubscriptionUpdate(this, string4, string5)) {
                return;
            }
            setResult(0);
            finish();
            return;
        }
        if (GooglePlayPurchasing.ContinuePurchase(this, string, string2)) {
            return;
        }
        setResult(0);
        finish();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        processActivityResult(i, i2, intent);
        this.receivedResult = true;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.receivedResult) {
            return;
        }
        processActivityResult(999, 0, null);
    }

    public void processActivityResult(int i, int i2, Intent intent) {
        GooglePlayPurchasing.ProcessActivityResult(i, i2, intent);
        finish();
    }
}
