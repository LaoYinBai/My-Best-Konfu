package com.onevcat.uniwebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UniWebViewChromeClient extends VideoEnabledWebChromeClient {
    private Activity activity;
    private AlertDialog alertDialog;
    private ValueCallback<Uri[]> callback;
    private String cameraPhotoPath;
    private UniWebViewDialog dialog;
    private WebChromeClient.FileChooserParams params;
    private UniWebView popUpWebView;

    AlertDialog getAlertDialog() {
        return this.alertDialog;
    }

    UniWebViewChromeClient(Activity activity, View view, ViewGroup viewGroup, View view2, UniWebView uniWebView, UniWebViewDialog uniWebViewDialog) {
        super(view, viewGroup, view2, uniWebView);
        this.activity = activity;
        this.dialog = uniWebViewDialog;
    }

    private UniWebViewChromeClient(Activity activity, UniWebViewDialog uniWebViewDialog) {
        this.activity = activity;
        this.dialog = uniWebViewDialog;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Logger.getInstance().info("UniWebViewChromeClient onShowFileChooser.");
        if (this.callback != null) {
            Logger.getInstance().critical("Trying to show another file chooser before previous one finished. Discard previous upload!");
            this.callback.onReceiveValue(null);
        }
        this.callback = valueCallback;
        this.params = fileChooserParams;
        Logger.getInstance().verbose("Start file chooser activity.");
        Intent intent = new Intent(this.activity, (Class<?>) UniWebViewFileChooserActivity.class);
        intent.putExtra(UniWebViewFileChooserActivity.INTENT_CHROME_CLIENT_NAME, this.dialog.getWebViewName());
        this.activity.startActivity(intent);
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(final PermissionRequest permissionRequest) {
        Logger.getInstance().info("UniWebViewChromeClient onPermissionRequest, url: " + permissionRequest.getOrigin().toString());
        this.activity.runOnUiThread(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    URL url = new URL(permissionRequest.getOrigin().toString());
                    if (UniWebViewChromeClient.this.dialog.getPermissionTrustDomains().contains(url.getHost())) {
                        Logger.getInstance().info("Permission domain '" + url.getHost() + "' contains in permission trusted domains. Granting...");
                        permissionRequest.grant(permissionRequest.getResources());
                    } else {
                        Logger.getInstance().critical("Permission domain '" + url.getHost() + "' is not allowed. Deny request.");
                        Logger.getInstance().critical("If you want to allow permission access from this domain, add it through `UniWebView.AddPermissionTrustDomain` first");
                        permissionRequest.deny();
                    }
                } catch (MalformedURLException e) {
                    Logger.getInstance().critical("onPermissionRequest failed due to malformed url exception. " + e.getMessage());
                    permissionRequest.deny();
                }
            }
        });
    }

    WebChromeClient.FileChooserParams getFileChooserParams() {
        return this.params;
    }

    private void showAlert() {
        if (this.dialog.getImmersiveMode()) {
            this.alertDialog.getWindow().setFlags(8, 8);
            this.alertDialog.show();
            this.alertDialog.getWindow().getDecorView().setSystemUiVisibility(this.dialog.getWindow().getDecorView().getSystemUiVisibility());
            this.alertDialog.getWindow().clearFlags(8);
            return;
        }
        this.alertDialog.show();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
        this.alertDialog = new AlertDialog.Builder(this.dialog.getContext()).setTitle(str).setMessage(str2).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.confirm();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        }).create();
        showAlert();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
        this.alertDialog = new AlertDialog.Builder(this.dialog.getContext()).setTitle(str).setMessage(str2).setIcon(android.R.drawable.ic_dialog_info).setCancelable(false).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.confirm();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.cancel();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        }).create();
        showAlert();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.dialog.getContext());
        builder.setTitle(str).setMessage(str2).setIcon(android.R.drawable.ic_dialog_info).setCancelable(false);
        final EditText editText = new EditText(this.dialog.getContext());
        editText.setSingleLine();
        builder.setView(editText);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Editable text = editText.getText();
                String string = text != null ? text.toString() : "";
                dialogInterface.dismiss();
                jsPromptResult.confirm(string);
                UniWebViewChromeClient.this.alertDialog = null;
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsPromptResult.cancel();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        });
        this.alertDialog = builder.create();
        showAlert();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        callback.invoke(str, true, false);
    }

    @Override // android.webkit.WebChromeClient
    @SuppressLint({"SetJavaScriptEnabled"})
    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        Logger.getInstance().info("onCreateWindow: " + webView + ", isUserGesture: " + z2);
        if (this.dialog.getWebView().getSettings().supportMultipleWindows() && z2) {
            UniWebView uniWebView = new UniWebView(this.activity);
            uniWebView.getSettings().setJavaScriptEnabled(true);
            uniWebView.setWebChromeClient(new UniWebViewChromeClient(this.activity, this.dialog));
            uniWebView.setWebViewClient(new WebViewClient());
            uniWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            uniWebView.getSettings().setUserAgentString(this.dialog.getWebView().getSettings().getUserAgentString());
            webView.addView(uniWebView);
            ((WebView.WebViewTransport) message.obj).setWebView(uniWebView);
            message.sendToTarget();
            setPopUpWebView(uniWebView);
            return true;
        }
        return super.onCreateWindow(webView, z, z2, message);
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(WebView webView) {
        super.onCloseWindow(webView);
        this.dialog.getWebView().removeView(webView);
        setPopUpWebView(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void receivedFileValue(android.content.Intent r7, boolean r8) throws java.io.FileNotFoundException {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            r2 = 0
            if (r7 == 0) goto Lb0
            java.lang.String r3 = r7.getDataString()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r3 == 0) goto L18
            android.net.Uri r7 = android.net.Uri.parse(r3)
            r4.add(r7)
            goto L33
        L18:
            android.content.ClipData r7 = r7.getClipData()
            if (r7 == 0) goto L33
            r3 = 0
        L1f:
            int r5 = r7.getItemCount()
            if (r3 >= r5) goto L33
            android.content.ClipData$Item r5 = r7.getItemAt(r3)
            android.net.Uri r5 = r5.getUri()
            r4.add(r5)
            int r3 = r3 + 1
            goto L1f
        L33:
            boolean r7 = r4.isEmpty()
            if (r7 == 0) goto L4b
            if (r8 == 0) goto Lc1
            java.lang.String r7 = r6.cameraPhotoPath
            if (r7 == 0) goto Lc1
            android.net.Uri[] r7 = new android.net.Uri[r1]
            java.lang.String r8 = r6.cameraPhotoPath
            android.net.Uri r8 = android.net.Uri.parse(r8)
            r7[r2] = r8
            goto Lc2
        L4b:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r8 = 0
        L51:
            int r1 = r4.size()
            if (r8 >= r1) goto La7
            java.lang.Object r1 = r4.get(r8)
            android.net.Uri r1 = (android.net.Uri) r1
            java.lang.String r3 = r6.uriToFilename(r1)
            if (r3 == 0) goto L70
            java.io.File r1 = new java.io.File
            r1.<init>(r3)
            android.net.Uri r1 = android.net.Uri.fromFile(r1)
            r7.add(r1)
            goto La4
        L70:
            android.app.Activity r3 = r6.activity     // Catch: java.io.IOException -> L89 java.io.FileNotFoundException -> L97
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.io.IOException -> L89 java.io.FileNotFoundException -> L97
            java.io.InputStream r3 = r3.openInputStream(r1)     // Catch: java.io.IOException -> L89 java.io.FileNotFoundException -> L97
            java.io.File r1 = r6.createTempFile(r1)     // Catch: java.io.IOException -> L89 java.io.FileNotFoundException -> L97
            r6.copyInputStreamToFile(r3, r1)     // Catch: java.io.IOException -> L89 java.io.FileNotFoundException -> L97
            android.net.Uri r1 = android.net.Uri.fromFile(r1)     // Catch: java.io.IOException -> L89 java.io.FileNotFoundException -> L97
            r7.add(r1)     // Catch: java.io.IOException -> L89 java.io.FileNotFoundException -> L97
            goto La4
        L89:
            r1 = move-exception
            r1.printStackTrace()
            com.onevcat.uniwebview.Logger r1 = com.onevcat.uniwebview.Logger.getInstance()
            java.lang.String r3 = "Can not get correct path on disk storage."
            r1.critical(r3)
            goto La4
        L97:
            r1 = move-exception
            r1.printStackTrace()
            com.onevcat.uniwebview.Logger r1 = com.onevcat.uniwebview.Logger.getInstance()
            java.lang.String r3 = "Can not get correct path on disk storage."
            r1.critical(r3)
        La4:
            int r8 = r8 + 1
            goto L51
        La7:
            android.net.Uri[] r8 = new android.net.Uri[r2]
            java.lang.Object[] r7 = r7.toArray(r8)
            android.net.Uri[] r7 = (android.net.Uri[]) r7
            goto Lc2
        Lb0:
            if (r8 == 0) goto Lc1
            java.lang.String r7 = r6.cameraPhotoPath
            if (r7 == 0) goto Lc1
            android.net.Uri[] r7 = new android.net.Uri[r1]
            java.lang.String r8 = r6.cameraPhotoPath
            android.net.Uri r8 = android.net.Uri.parse(r8)
            r7[r2] = r8
            goto Lc2
        Lc1:
            r7 = r0
        Lc2:
            android.webkit.ValueCallback<android.net.Uri[]> r8 = r6.callback
            if (r8 == 0) goto Lcb
            android.webkit.ValueCallback<android.net.Uri[]> r8 = r6.callback
            r8.onReceiveValue(r7)
        Lcb:
            r6.callback = r0
            r6.params = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onevcat.uniwebview.UniWebViewChromeClient.receivedFileValue(android.content.Intent, boolean):void");
    }

    private void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i > 0) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String uriToFilename(Uri uri) {
        return ProviderPathConverter.getPath(this.activity, uri);
    }

    File createImageFile() throws IOException {
        String str = "IMAGE_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + "_";
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdir();
        }
        return File.createTempFile(str, ".png", externalStoragePublicDirectory);
    }

    File createTempFile(Uri uri) throws IOException {
        Cursor cursorQuery = this.activity.getContentResolver().query(uri, null, null, null, null, null);
        String string = "";
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            string = cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"));
        }
        cursorQuery.close();
        return File.createTempFile(string, null, null);
    }

    void setCameraPhotoPath(String str) {
        this.cameraPhotoPath = str;
    }

    String getCameraPhotoPath() {
        return this.cameraPhotoPath;
    }

    public UniWebView getPopUpWebView() {
        return this.popUpWebView;
    }

    public void setPopUpWebView(UniWebView uniWebView) {
        this.popUpWebView = uniWebView;
    }
}
