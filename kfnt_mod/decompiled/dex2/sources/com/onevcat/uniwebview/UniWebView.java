package com.onevcat.uniwebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.huawei.hms.framework.common.ContainerUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class UniWebView extends VideoEnabledWebView {
    private static boolean allowAutoPlay = false;
    private static boolean allowJavaScriptOpenWindow = false;
    private static boolean javaScriptEnabled = true;
    private Activity activity;
    boolean calloutEnabled;
    private UniWebViewClient client;

    static void setAllowAutoPlay(boolean z) {
        allowAutoPlay = z;
    }

    static void setAllowJavaScriptOpenWindow(boolean z) {
        allowJavaScriptOpenWindow = z;
    }

    static void setJavaScriptEnabled(boolean z) {
        javaScriptEnabled = z;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    UniWebView(Context context) {
        super(context);
        this.calloutEnabled = true;
        this.activity = (Activity) context;
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setGeolocationEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setMediaPlaybackRequiresUserGesture(!allowAutoPlay);
        settings.setJavaScriptCanOpenWindowsAutomatically(allowJavaScriptOpenWindow);
        settings.setJavaScriptEnabled(javaScriptEnabled);
        settings.setMixedContentMode(2);
        settings.setAppCachePath(context.getCacheDir().getPath());
        settings.setAppCacheEnabled(true);
        setScrollBarStyle(0);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    }

    static void clearCookies() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null);
        cookieManager.flush();
        Logger.getInstance().verbose("Cookie manager flushed.");
    }

    static void setCookie(String str, String str2) {
        Logger logger = Logger.getInstance();
        CookieManager cookieManager = CookieManager.getInstance();
        logger.verbose("Cookie set for url: " + str + ". Content: " + str2);
        cookieManager.setCookie(str, str2);
        cookieManager.flush();
        logger.verbose("Cookie manager flushed.");
    }

    static String getCookie(String str, String str2) {
        Logger logger = Logger.getInstance();
        String str3 = "";
        String cookie = CookieManager.getInstance().getCookie(str);
        if (cookie == null) {
            Logger.getInstance().debug("The content for url is not found in cookie. Url: " + str);
            return "";
        }
        logger.verbose("Cookie string is found: " + cookie + ", for url: " + str);
        StringBuilder sb = new StringBuilder();
        sb.append("Trying to parse cookie to find for key: ");
        sb.append(str2);
        logger.verbose(sb.toString());
        for (String str4 : cookie.split(";")) {
            if (str4.contains(str2)) {
                String[] strArrSplit = str4.split(ContainerUtils.KEY_VALUE_DELIMITER, 2);
                if (strArrSplit.length >= 2) {
                    str3 = strArrSplit[1];
                    logger.verbose("Found cookie value: " + str3 + ", for key: " + str2);
                }
            }
        }
        return str3;
    }

    public UniWebViewClient getClient() {
        return this.client;
    }

    public void setClient(UniWebViewClient uniWebViewClient) {
        this.client = uniWebViewClient;
        setWebViewClient(uniWebViewClient);
    }

    public HashMap<String, String> getCustomizeHeaders() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.view.View
    protected void onCreateContextMenu(ContextMenu contextMenu) {
        if (this.calloutEnabled) {
            super.onCreateContextMenu(contextMenu);
            WebView.HitTestResult hitTestResult = getHitTestResult();
            int type = hitTestResult.getType();
            if (type == 5 || type == 8) {
                final String extra = hitTestResult.getExtra();
                if (extra.toLowerCase(Locale.ROOT).startsWith("http://") || extra.toLowerCase(Locale.ROOT).startsWith("https://")) {
                    contextMenu.setHeaderTitle(extra);
                    final Resources resources = getContext().getResources();
                    contextMenu.add(0, 1, 0, resources.getString(R.string.SAVE_IMAGE)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.onevcat.uniwebview.UniWebView.1
                        @Override // android.view.MenuItem.OnMenuItemClickListener
                        public boolean onMenuItemClick(MenuItem menuItem) throws Resources.NotFoundException {
                            if (Build.VERSION.SDK_INT >= 23 && UniWebView.this.activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                                UniWebView.this.activity.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                                Toast.makeText(UniWebView.this.getContext(), resources.getString(R.string.EXTERNAL_WRITE_PERMISSION_LACK), 1).show();
                                return false;
                            }
                            if (URLUtil.isValidUrl(extra)) {
                                String strGuessFileName = URLUtil.guessFileName(extra, null, MimeTypeMap.getFileExtensionFromUrl(extra));
                                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(extra));
                                request.allowScanningByMediaScanner();
                                request.setNotificationVisibility(1);
                                request.setDescription(strGuessFileName);
                                request.setTitle(strGuessFileName);
                                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, strGuessFileName);
                                for (Map.Entry<String, String> entry : UniWebView.this.getCustomizeHeaders().entrySet()) {
                                    request.addRequestHeader(entry.getKey(), entry.getValue());
                                }
                                ((DownloadManager) UniWebView.this.getContext().getSystemService("download")).enqueue(request);
                                Toast.makeText(UniWebView.this.getContext(), resources.getString(R.string.DOWNLOAD_STARTED), 1).show();
                            } else {
                                Toast.makeText(UniWebView.this.getContext(), resources.getString(R.string.INVALID_URL), 1).show();
                            }
                            return false;
                        }
                    });
                }
            }
        }
    }
}
