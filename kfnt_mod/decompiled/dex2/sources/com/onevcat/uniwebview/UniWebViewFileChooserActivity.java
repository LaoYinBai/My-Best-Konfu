package com.onevcat.uniwebview;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import java.io.FileNotFoundException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UniWebViewFileChooserActivity extends Activity {
    private static final int FILECHOOSER_RESULTCODE = 19238467;
    public static final String INTENT_CHROME_CLIENT_NAME = "chrome_client";
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 19238468;

    private UniWebViewChromeClient getChromeClient() {
        String stringExtra = getIntent().getStringExtra(INTENT_CHROME_CLIENT_NAME);
        if (stringExtra == null) {
            Logger.getInstance().critical("The intent does not contain a extra name for chrome client. It should not happen.");
            return null;
        }
        Logger.getInstance().verbose("Getting chrome client with web view name: " + stringExtra);
        UniWebViewDialog uniWebViewDialog = UniWebViewManager.getInstance().getUniWebViewDialog(stringExtra);
        if (uniWebViewDialog == null) {
            Logger.getInstance().critical("Cannot get a correct chrome client. Error.");
            return null;
        }
        return uniWebViewDialog.getChromeClient();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.getInstance().verbose("UniWebViewFileChooserActivity onCreate. Bound client: " + getChromeClient());
        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            startFileChooserActivity();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) throws FileNotFoundException {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (iArr[0] == 0) {
                startFileChooserActivity();
                return;
            }
            UniWebViewChromeClient chromeClient = getChromeClient();
            if (chromeClient == null) {
                Logger.getInstance().critical("Unexpected onRequestPermissionsResult since the chrome client has been already reset to null.");
            } else {
                chromeClient.receivedFileValue(null, false);
                finish();
            }
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) throws FileNotFoundException {
        Logger.getInstance().verbose("File Chooser activity result: " + i2);
        if (i == FILECHOOSER_RESULTCODE) {
            UniWebViewChromeClient chromeClient = getChromeClient();
            if (chromeClient == null) {
                Logger.getInstance().critical("Unexpected onActivityResult since the chrome client has been already reset to null.");
            } else if (i2 == -1) {
                Logger.getInstance().info("File chooser got a file. : " + intent);
                chromeClient.receivedFileValue(intent, true);
            } else {
                Logger.getInstance().critical("File chooser failed to get a file. Result code: " + i2);
                chromeClient.receivedFileValue(null, false);
            }
        }
        super.onActivityResult(i, i2, intent);
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void startFileChooserActivity() {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onevcat.uniwebview.UniWebViewFileChooserActivity.startFileChooserActivity():void");
    }
}
