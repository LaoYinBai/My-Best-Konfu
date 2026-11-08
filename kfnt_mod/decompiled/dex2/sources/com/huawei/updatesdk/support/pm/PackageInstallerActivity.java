package com.huawei.updatesdk.support.pm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.updatesdk.a.a.b.a.a.a;
import com.huawei.updatesdk.a.b.c.b;
import com.huawei.updatesdk.fileprovider.UpdateSdkFileProvider;
import com.huawei.updatesdk.service.c.g;
import com.huawei.updatesdk.service.c.h;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PackageInstallerActivity extends Activity {
    private String a = "";
    private String b = "";
    private int c = -1;

    private Intent a(Context context, String str) {
        Uri uriFromFile;
        if (TextUtils.isEmpty(str) || str.contains("../") || str.contains("..") || str.contains("%00") || str.contains(".\\.\\") || str.contains("./")) {
            throw new IllegalArgumentException("getNomalInstallIntent: Not a standard path");
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.INSTALL_PACKAGE");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(1);
            uriFromFile = UpdateSdkFileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + UpdateSdkFileProvider.AUTHORITIES_SUFFIX, new File(str));
        } else {
            uriFromFile = Uri.fromFile(new File(str));
        }
        intent.setData(uriFromFile);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        return intent;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (1000 == i) {
            if (i2 == 0) {
                g.a(7, 0);
            } else {
                int intExtra = intent != null ? intent.getIntExtra("android.intent.extra.INSTALL_RESULT", -10004) : -10004;
                String[] strArrA = h.a();
                if (Build.VERSION.SDK_INT < 24 && -3 == intExtra && this.c < strArrA.length - 1) {
                    g.a.a(this.b, -3, true);
                } else if (intExtra != 0 && intExtra != 1) {
                    g.a(4, intExtra);
                }
            }
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        String str;
        String str2;
        requestWindowFeature(1);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            str = "PackageInstallerActivity";
            str2 = "PackageInstallerActivity error intent";
        } else {
            b bVarA = b.a(intent);
            this.a = bVarA.a("install_path");
            this.b = bVarA.a("install_packagename");
            if (!TextUtils.isEmpty(this.a)) {
                this.c = bVarA.a("install_change_path_times", -1);
                try {
                    Intent intentA = a(this, this.a);
                    intentA.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
                    intentA.putExtra("android.intent.extra.RETURN_RESULT", true);
                    a.c("PackageInstallerActivity", " onCreate filePath:" + this.a + ",packageName:" + this.b + ",taskId:" + getTaskId());
                    startActivityForResult(intentA, 1000);
                    return;
                } catch (Exception unused) {
                    a.d("PackageInstallerActivity", "can not start install action");
                    g.a(4, -2);
                    finish();
                    return;
                }
            }
            g.a(4, -3);
            finish();
            str = "PackageInstallerActivity";
            str2 = "PackageInstallerActivity can not find filePath.";
        }
        a.d(str, str2);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        finishActivity(1000);
        super.onDestroy();
        a.c("PackageInstallerActivity", " onDestroy removeTaskId:" + this.a);
    }
}
