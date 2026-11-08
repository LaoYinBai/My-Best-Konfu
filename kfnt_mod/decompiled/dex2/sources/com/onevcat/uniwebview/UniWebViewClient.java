package com.onevcat.uniwebview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.text.method.PasswordTransformationMethod;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class UniWebViewClient extends WebViewClient {
    private UniWebViewDialog dialog;
    private int httpStatusCode = 200;
    private boolean loadingSuccess;
    private boolean sslErrored;
    private boolean userStopped;

    UniWebViewClient(UniWebViewDialog uniWebViewDialog) {
        this.dialog = uniWebViewDialog;
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        Logger.getInstance().info("WebClient onPageStarted: " + str);
        this.dialog.setLoading(true);
        if (this.dialog.isShowSpinnerWhileLoading() && this.dialog.isShowing()) {
            this.dialog.showSpinner();
        }
        this.dialog.hideSystemUI();
        this.dialog.listener.onPageStarted(this.dialog, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        Logger.getInstance().info("WebClient onPageFinished: " + str);
        this.dialog.setLoading(false);
        this.dialog.hideSpinner();
        this.dialog.hideSystemUI();
        if (this.loadingSuccess) {
            if (this.sslErrored) {
                Logger.getInstance().critical("WebClient onReceivedError for url: " + str + " Error Code: -1202 Error: ssl error");
                this.dialog.listener.onReceivedError(this.dialog, -1202, "ssl error", str);
                return;
            }
            if (this.userStopped) {
                Logger.getInstance().critical("WebClient onReceivedError for url: " + str + " Error Code: 999 Error: Operation cancelled.");
                this.dialog.listener.onReceivedError(this.dialog, -999, "Operation cancelled.", str);
                return;
            }
            Logger.getInstance().info("WebClient onPageFinished: " + str + ". Status Code:" + this.httpStatusCode + " Loading success: " + this.loadingSuccess);
            this.dialog.listener.onPageFinished(this.dialog, this.httpStatusCode, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        Logger.getInstance().info("WebClient onReceivedHttpError. Code: " + webResourceResponse.getStatusCode());
        if (webResourceRequest.isForMainFrame()) {
            this.httpStatusCode = webResourceResponse.getStatusCode();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        Logger.getInstance().critical("WebClient onReceivedError for url: " + str2 + " Error Code: " + i + " Error: " + str);
        this.loadingSuccess = false;
        this.dialog.hideSpinner();
        this.dialog.hideSystemUI();
        this.dialog.listener.onReceivedError(this.dialog, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Logger.getInstance().info("WebClient shouldOverrideUrlLoading: " + str);
        if (this.dialog.listener.shouldOverrideUrlLoading(this.dialog, str)) {
            return true;
        }
        if (str.startsWith("file://")) {
            Logger.getInstance().debug("Loading a local file. The local file loading will never be overridden.");
            return false;
        }
        HashMap<String, String> headers = this.dialog.getHeaders();
        if (headers.isEmpty()) {
            return false;
        }
        Logger.getInstance().debug("Adding customized header to request. " + headers.toString());
        webView.loadUrl(str, headers);
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Logger.getInstance().critical("WebClient onReceivedSslError. Error url: " + sslError.getUrl() + " Error type: " + sslError.getPrimaryError());
        try {
            Logger.getInstance().verbose("Trying to process SSL error...");
            String host = new URL(sslError.getUrl()).getHost();
            SslCertificate certificate = sslError.getCertificate();
            if (certificate.getIssuedBy() != null && certificate.getIssuedTo() != null) {
                if (this.dialog.getSslExceptionDomains().contains(host)) {
                    Logger.getInstance().verbose("Found domain '" + host + "' in sslExceptionDomains, proceeding url...");
                    sslErrorHandler.proceed();
                    return;
                }
                Logger.getInstance().verbose("Domain '" + host + "' is not in exception. Refuse proceeding url.");
                this.sslErrored = true;
                sslErrorHandler.cancel();
                return;
            }
            Logger.getInstance().verbose("Cannot get correct certificate issuer. SSL challenge failed.");
            sslErrorHandler.cancel();
        } catch (MalformedURLException e) {
            Logger.getInstance().verbose("Url '" + sslError.getUrl() + "' is malformed. Refuse proceeding url. Exception: " + e);
            this.sslErrored = true;
            sslErrorHandler.cancel();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        String str3;
        String[] httpAuthUsernamePassword;
        String str4 = null;
        if (!httpAuthHandler.useHttpAuthUsernamePassword() || webView == null || (httpAuthUsernamePassword = webView.getHttpAuthUsernamePassword(str, str2)) == null || httpAuthUsernamePassword.length != 2) {
            str3 = null;
        } else {
            str4 = httpAuthUsernamePassword[0];
            str3 = httpAuthUsernamePassword[1];
        }
        if (str4 != null && str3 != null) {
            httpAuthHandler.proceed(str4, str3);
        } else if (this.dialog.getShowHTTPAuthPopUpWindow()) {
            showHttpAuthDialog(webView, httpAuthHandler, str, str2);
        } else {
            httpAuthHandler.cancel();
        }
    }

    private void showHttpAuthDialog(final WebView webView, final HttpAuthHandler httpAuthHandler, final String str, final String str2) {
        Context context = this.dialog.getContext();
        LinearLayout linearLayout = new LinearLayout(context);
        final EditText editText = new EditText(context);
        editText.setHint(context.getResources().getString(R.string.USERNAME));
        final EditText editText2 = new EditText(context);
        editText2.setHint(context.getResources().getString(R.string.PASSWORD));
        editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        linearLayout.setOrientation(1);
        linearLayout.addView(editText);
        linearLayout.addView(editText2);
        new AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.AUTH_REQUIRE_TITLE)).setMessage(str).setView(linearLayout).setCancelable(false).setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewClient.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String string = editText.getText().toString();
                String string2 = editText2.getText().toString();
                webView.setHttpAuthUsernamePassword(str, str2, string, string2);
                httpAuthHandler.proceed(string, string2);
            }
        }).setNegativeButton(context.getString(android.R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewClient.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                httpAuthHandler.cancel();
            }
        }).create().show();
    }

    public void setUserStopped(boolean z) {
        this.userStopped = z;
    }

    public void prepareLoadingStates() {
        this.httpStatusCode = 200;
        this.loadingSuccess = true;
        this.userStopped = false;
        this.sslErrored = false;
    }
}
