package com.onevcat.uniwebview;

import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.onevcat.uniwebview.UniWebViewDialog;
import com.unity3d.player.UnityPlayer;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UniWebViewInterface {
    public static void setLogLevel(int i) {
        Logger.getInstance().setLevel(i);
    }

    public static void prepare() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Activity activity_getUnityActivity = _getUnityActivity();
        activity_getUnityActivity.runOnUiThread(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewInterface.1
            @Override // java.lang.Runnable
            public void run() {
                UniWebViewDialog.defaultUserAgent = WebSettings.getDefaultUserAgent(activity_getUnityActivity);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void init(final String str, final int i, final int i2, final int i3, final int i4) {
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.2
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable, java.lang.Runnable
            public void run() {
                UniWebViewDialog.DialogListener dialogListener = new UniWebViewDialog.DialogListener() { // from class: com.onevcat.uniwebview.UniWebViewInterface.2.1
                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onPageFinished(UniWebViewDialog uniWebViewDialog, int i5, String str2) {
                        Logger.getInstance().info("onPageFinished: " + str2);
                        UniWebViewInterface._sendMessage(str, "PageFinished", new UniWebViewResultPayload("", Integer.toString(i5), str2).toString());
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onPageStarted(UniWebViewDialog uniWebViewDialog, String str2) {
                        Logger.getInstance().info("onPageStarted: " + str2);
                        UniWebViewInterface._sendMessage(str, "PageStarted", str2);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onReceivedError(UniWebViewDialog uniWebViewDialog, int i5, String str2, String str3) {
                        Logger.getInstance().critical("onReceivedError: " + str3 + " Error Code: " + i5 + "Error Description: " + str2);
                        UniWebViewInterface._sendMessage(str, "PageErrorReceived", new UniWebViewResultPayload("", Integer.toString(i5), str2).toString());
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public boolean shouldOverrideUrlLoading(UniWebViewDialog uniWebViewDialog, String str2) {
                        return uniWebViewDialog.shouldOverride(str2, true);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onSendMessageReceived(UniWebViewDialog uniWebViewDialog, String str2) {
                        UniWebViewInterface._sendMessage(str, "MessageReceived", str2);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onDialogClosedByBackButton(UniWebViewDialog uniWebViewDialog) {
                        Logger.getInstance().info("onDialogClosedByBackButton");
                        UniWebViewInterface._sendMessage(str, "WebViewDone", "");
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onDialogKeyDown(UniWebViewDialog uniWebViewDialog, int i5) {
                        Logger.getInstance().info("onDialogKeyDown, key: " + i5);
                        UniWebViewInterface._sendMessage(str, "WebViewKeyDown", Integer.toString(i5));
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onDialogClose(UniWebViewDialog uniWebViewDialog) {
                        Logger.getInstance().info("onDialogClose");
                        UniWebViewManager.getInstance().removeUniWebView(str);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onAddJavaScriptFinished(UniWebViewDialog uniWebViewDialog, String str2) {
                        Logger.getInstance().info("onAddJavaScriptFinished");
                        Logger.getInstance().debug("js result: " + str2);
                        UniWebViewInterface._sendMessage(str, "AddJavaScriptFinished", str2);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onJavaScriptFinished(UniWebViewDialog uniWebViewDialog, String str2) {
                        Logger.getInstance().info("onEvalJavaScriptFinished");
                        Logger.getInstance().debug("js result: " + str2);
                        UniWebViewInterface._sendMessage(str, "EvalJavaScriptFinished", str2);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onAnimationFinished(UniWebViewDialog uniWebViewDialog, String str2) {
                        Logger.getInstance().info("onAnimationFinished, animation id: " + str2);
                        UniWebViewInterface._sendMessage(str, "AnimateToFinished", str2);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onShowTransitionFinished(UniWebViewDialog uniWebViewDialog, String str2) {
                        Logger.getInstance().info("onShowTransitionFinished");
                        UniWebViewInterface._sendMessage(str, "ShowTransitionFinished", str2);
                    }

                    @Override // com.onevcat.uniwebview.UniWebViewDialog.DialogListener
                    public void onHideTransitionFinished(UniWebViewDialog uniWebViewDialog, String str2) {
                        Logger.getInstance().info("onHideTransitionFinished");
                        UniWebViewInterface._sendMessage(str, "HideTransitionFinished", str2);
                    }
                };
                Logger.getInstance().info("Interface init: " + str);
                UniWebViewDialog uniWebViewDialog = new UniWebViewDialog(UniWebViewInterface._getUnityActivity(), dialogListener);
                uniWebViewDialog.setFrame(i, i2, i3, i4);
                UniWebViewManager.getInstance().setUniWebView(str, uniWebViewDialog);
            }
        });
    }

    public static void destroy(String str) {
        Logger.getInstance().info("Interface destroy web view" + str);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.3
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.destroy();
            }
        });
    }

    public static void load(String str, final String str2) {
        Logger.getInstance().info("Interface load: " + str2);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.4
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            public void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.load(str2);
            }
        });
    }

    public static void loadHTMLString(String str, final String str2, final String str3) {
        Logger.getInstance().info("Interface loadHTMLString");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.5
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            public void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.loadHTMLString(str2, str3);
            }
        });
    }

    public static void reload(String str) {
        Logger.getInstance().info("Interface reload");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.6
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            public void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getWebView().reload();
            }
        });
    }

    public static void stop(final String str) {
        Logger.getInstance().info("Interface stop");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.7
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable, java.lang.Runnable
            public void run() {
                UniWebViewDialog uniWebViewDialog = UniWebViewManager.getInstance().getUniWebViewDialog(str);
                if (uniWebViewDialog != null) {
                    uniWebViewDialog.stop();
                }
            }
        });
    }

    public static String getUrl(String str) throws InterruptedException {
        Logger.getInstance().info("Interface getUrl");
        UniWebViewManager.getInstance().getUniWebViewDialog(str);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String[] strArr = {""};
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.8
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                strArr[0] = uniWebViewDialog.getUrl();
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return strArr[0];
    }

    public static void setFrame(String str, final int i, final int i2, final int i3, final int i4) {
        Logger.getInstance().info(String.format(Locale.US, "Interface setFrame: {%d, %d, %d, %d}", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.9
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            public void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setFrame(i, i2, i3, i4);
            }
        });
    }

    public static void setPosition(String str, final int i, final int i2) {
        Logger.getInstance().info(String.format(Locale.US, "Interface setPosition: {%d, %d}", Integer.valueOf(i), Integer.valueOf(i2)));
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.10
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setPosition(i, i2);
            }
        });
    }

    public static void setSize(String str, final int i, final int i2) {
        Logger.getInstance().info(String.format(Locale.US, "Interface setSize: {%d, %d}", Integer.valueOf(i), Integer.valueOf(i2)));
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.11
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setSize(i, i2);
            }
        });
    }

    public static boolean show(String str, final boolean z, final int i, final float f, final String str2) throws InterruptedException {
        Logger.getInstance().info("Interface show");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.12
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            public void runWith(UniWebViewDialog uniWebViewDialog) {
                zArr[0] = uniWebViewDialog.setShow(true, z, i, f, str2);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zArr[0];
    }

    public static boolean hide(String str, final boolean z, final int i, final float f, final String str2) throws InterruptedException {
        Logger.getInstance().info("Interface hide");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.13
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                zArr[0] = uniWebViewDialog.setShow(false, z, i, f, str2);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zArr[0];
    }

    public static boolean animateTo(String str, final int i, final int i2, final int i3, final int i4, final float f, final float f2, final String str2) throws InterruptedException {
        Logger.getInstance().info(String.format(Locale.US, "Interface animateTo: {%d, %d, %d, %d}", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.14
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                zArr[0] = uniWebViewDialog.animateTo(i, i2, i3, i4, f, f2, str2);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zArr[0];
    }

    public static void addJavaScript(String str, final String str2, final String str3) {
        Logger.getInstance().info("Interface addJavaScript");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.15
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.addJavaScript(str2, str3);
            }
        });
    }

    public static void evaluateJavaScript(String str, final String str2, final String str3) {
        Logger.getInstance().info("Interface evaluateJavaScript");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.16
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.evaluateJavaScript(str2, str3);
            }
        });
    }

    public static void addUrlScheme(String str, final String str2) {
        Logger.getInstance().info("Interface addUrlScheme");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.17
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                Logger.getInstance().verbose("Adding url scheme to web view: " + str2);
                uniWebViewDialog.getSchemes().add(str2);
            }
        });
    }

    public static void removeUrlScheme(String str, final String str2) {
        Logger.getInstance().info("Interface addUrlScheme");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.18
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                Logger.getInstance().verbose("Removing url scheme to web view: " + str2);
                uniWebViewDialog.getSchemes().remove(str2);
            }
        });
    }

    public static void setHeaderField(String str, final String str2, final String str3) {
        Logger.getInstance().info("Interface setHeaderField for key: " + str2 + ", value: " + str3);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.19
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setHeaderField(str2, str3);
            }
        });
    }

    public static void setUserAgent(final String str, final String str2) {
        final Logger logger = Logger.getInstance();
        logger.info("Interface setUserAgent");
        UniWebViewDialog.setUserAgent(UniWebViewManager.getInstance().getUniWebViewDialog(str), str, str2);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.20
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                logger.verbose("Setting user agent string of web view to:" + str2);
                UniWebViewDialog.setUserAgent(uniWebViewDialog, str, str2);
            }
        });
    }

    public static String getUserAgent(String str) {
        Logger.getInstance().info("Interface getUserAgent");
        return UniWebViewDialog.getUserAgent(UniWebViewManager.getInstance().getUniWebViewDialog(str), str);
    }

    public static void setAllowAutoPlay(boolean z) {
        Logger.getInstance().info("Interface setAllowAutoPlay: " + z);
        UniWebView.setAllowAutoPlay(z);
    }

    public static void setAllowJavaScriptOpenWindow(boolean z) {
        Logger.getInstance().info("Interface setAllowJavaScriptOpenWindow: " + z);
        UniWebView.setAllowJavaScriptOpenWindow(z);
    }

    public static void setJavaScriptEnabled(boolean z) {
        Logger.getInstance().info("Interface setJavaScriptEnabled: " + z);
        UniWebView.setJavaScriptEnabled(z);
    }

    public static void cleanCache(String str) {
        Logger.getInstance().info("Interface cleanCache");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.21
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getWebView().clearCache(true);
            }
        });
    }

    public static void clearCookies() {
        Logger.getInstance().info("Interface clearCookies");
        UniWebView.clearCookies();
    }

    public static void setCookie(String str, String str2) {
        Logger.getInstance().info("Interface setCookie");
        UniWebView.setCookie(str, str2);
    }

    public static String getCookie(String str, String str2) {
        Logger.getInstance().info("Interface getCookie");
        return UniWebView.getCookie(str, str2);
    }

    public static void clearHttpAuthUsernamePassword(final String str, final String str2) {
        Logger.getInstance().info("Interface clearHttpAuthUsernamePassword host: " + str + ", realm:" + str2);
        final Activity activity_getUnityActivity = _getUnityActivity();
        activity_getUnityActivity.runOnUiThread(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewInterface.22
            @Override // java.lang.Runnable
            public void run() {
                new UniWebViewDialog(activity_getUnityActivity, null).clearHttpAuthUsernamePassword(str, str2);
            }
        });
    }

    public static void setBackgroundColor(String str, final float f, final float f2, final float f3, final float f4) {
        Logger.getInstance().info(String.format(Locale.US, "Interface setBackgroundColor: {%f, %f, %f, %f}", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)));
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.23
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setBackgroundColor(f, f2, f3, f4);
            }
        });
    }

    public static void setWebViewAlpha(String str, final float f) {
        Logger.getInstance().info("Interface setWebViewAlpha: " + f);
        UniWebViewDialog uniWebViewDialog = UniWebViewManager.getInstance().getUniWebViewDialog(str);
        if (uniWebViewDialog != null) {
            uniWebViewDialog.setWebViewAlpha(f, false);
        }
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.24
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog2) {
                uniWebViewDialog2.setWebViewAlpha(f, true);
            }
        });
    }

    public static float getWebViewAlpha(String str) {
        Logger.getInstance().info("Interface getWebViewAlpha");
        UniWebViewDialog uniWebViewDialog = UniWebViewManager.getInstance().getUniWebViewDialog(str);
        if (uniWebViewDialog != null) {
            return uniWebViewDialog.getWebViewAlpha();
        }
        return 1.0f;
    }

    public static void setShowSpinnerWhileLoading(String str, final boolean z) {
        Logger.getInstance().info("Interface setShowSpinnerWhenLoading: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.25
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setShowSpinnerWhileLoading(z);
            }
        });
    }

    public static void setSpinnerText(String str, final String str2) {
        Logger.getInstance().info("Interface setSpinnerText: " + str2);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.26
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setSpinnerText(str2);
            }
        });
    }

    public static boolean canGoBack(String str) throws InterruptedException {
        Logger.getInstance().info("Interface canGoBack");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.27
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                zArr[0] = uniWebViewDialog.isCanGoBack();
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zArr[0];
    }

    public static boolean canGoForward(String str) throws InterruptedException {
        Logger.getInstance().info("Interface canGoForward");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.28
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                zArr[0] = uniWebViewDialog.isCanGoForward();
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zArr[0];
    }

    public static void goBack(String str) {
        Logger.getInstance().info("Interface goBack");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.29
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.goBack();
            }
        });
    }

    public static void goForward(String str) {
        Logger.getInstance().info("Interface goForward");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.30
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.goForward();
            }
        });
    }

    public static void setOpenLinksInExternalBrowser(String str, final boolean z) {
        Logger.getInstance().info("Interface setOpenLinksInExternalBrowser: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.31
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setOpenLinksInExternalBrowser(z);
            }
        });
    }

    public static void setHorizontalScrollBarEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setHorizontalScrollBarEnabled: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.32
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getWebView().setHorizontalScrollBarEnabled(z);
            }
        });
    }

    public static void setVerticalScrollBarEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setVerticalScrollBarEnabled: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.33
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getWebView().setVerticalScrollBarEnabled(z);
            }
        });
    }

    public static void setBouncesEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setBouncesEnabled");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.34
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setBouncesEnabled(z);
            }
        });
    }

    public static void setZoomEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setZoomEnabled");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.35
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setZoomEnabled(z);
            }
        });
    }

    public static void addPermissionTrustDomain(String str, final String str2) {
        Logger.getInstance().info("Interface addPermissionTrustDomain: " + str2);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.36
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getPermissionTrustDomains().add(str2);
            }
        });
    }

    public static void removePermissionTrustDomain(String str, final String str2) {
        Logger.getInstance().info("Interface removePermissionTrustDomain: " + str2);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.37
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getPermissionTrustDomains().remove(str2);
            }
        });
    }

    public static void addSslExceptionDomain(String str, final String str2) {
        Logger.getInstance().info("Interface addSslExceptionDomain: " + str2);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.38
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getSslExceptionDomains().add(str2);
            }
        });
    }

    public static void removeSslExceptionDomain(String str, final String str2) {
        Logger.getInstance().info("Interface addSslExceptionDomain: " + str2);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.39
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getSslExceptionDomains().remove(str2);
            }
        });
    }

    public static void setBackButtonEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setBackButtonEnabled");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.40
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setBackButtonEnabled(z);
            }
        });
    }

    public static void setUseWideViewPort(String str, final boolean z) {
        Logger.getInstance().info("Interface setUseWideViewPort");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.41
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getWebView().getSettings().setUseWideViewPort(z);
            }
        });
    }

    public static void setLoadWithOverviewMode(String str, final boolean z) {
        Logger.getInstance().info("Interface setLoadWithOverviewMode");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.42
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.getWebView().getSettings().setLoadWithOverviewMode(z);
            }
        });
    }

    public static void setImmersiveModeEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setImmersiveModeEnabled");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.43
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setImmersiveMode(z);
            }
        });
    }

    public static void setUserInteractionEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setUserInteractionEnabled");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.44
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setUserInteractionEnabled(z);
            }
        });
    }

    public static void setWebContentsDebuggingEnabled(final boolean z) {
        Logger.getInstance().info("Interface setWebContentsDebuggingEnabled: " + z);
        runSafelyOnUiThread(new DialogRunnable(null) { // from class: com.onevcat.uniwebview.UniWebViewInterface.45
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable, java.lang.Runnable
            public void run() {
                WebView.setWebContentsDebuggingEnabled(z);
            }
        });
    }

    public static void setAllowHTTPAuthPopUpWindow(String str, final boolean z) {
        Logger.getInstance().info("Interface setAllowHTTPAuthPopUpWindow: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.46
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setShowHTTPAuthPopUpWindow(z);
            }
        });
    }

    public static void setCalloutEnabled(String str, final boolean z) {
        Logger.getInstance().info("Interface setCalloutEnabled: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.47
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setCalloutEnabled(z);
            }
        });
    }

    public static void setSupportMultipleWindows(String str, final boolean z) {
        Logger.getInstance().info("Interface setSupportMultipleWindows: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.48
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setSupportMultipleWindows(z);
            }
        });
    }

    public static void setDefaultFontSize(String str, final int i) {
        Logger.getInstance().info("setDefaultFontSize: " + i);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.49
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.setDefaultFontSize(i);
            }
        });
    }

    public static void print(String str) {
        Logger.getInstance().info("Interface print");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.50
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.print();
            }
        });
    }

    public static void showWebViewDialog(String str, final boolean z) {
        Logger.getInstance().info("Interface showWebViewDialog");
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.51
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                if (z) {
                    uniWebViewDialog.goForeground();
                    uniWebViewDialog.hideSystemUI();
                } else {
                    uniWebViewDialog.goBackground();
                }
            }
        });
    }

    public static void scrollTo(String str, final int i, final int i2, final boolean z) {
        Logger.getInstance().info("Interface scrollTo (" + i + ", " + i2 + "), animated: " + z);
        runSafelyOnUiThread(new DialogRunnable(str) { // from class: com.onevcat.uniwebview.UniWebViewInterface.52
            @Override // com.onevcat.uniwebview.UniWebViewInterface.DialogRunnable
            void runWith(UniWebViewDialog uniWebViewDialog) {
                uniWebViewDialog.scrollTo(i, i2, z);
            }
        });
    }

    public static float screenWidth() {
        return _getUnityActivity().findViewById(android.R.id.content).getWidth();
    }

    public static float screenHeight() {
        return _getUnityActivity().findViewById(android.R.id.content).getHeight();
    }

    private static void runSafelyOnUiThread(final DialogRunnable dialogRunnable) {
        _getUnityActivity().runOnUiThread(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewInterface.53
            @Override // java.lang.Runnable
            public void run() {
                try {
                    dialogRunnable.run();
                } catch (Exception e) {
                    Logger.getInstance().critical("UniWebView should run on UI thread: " + e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Activity _getUnityActivity() {
        return UnityPlayer.currentActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void _sendMessage(String str, String str2, String str3) {
        UnityPlayer.UnitySendMessage("UniWebViewAndroidStaticListener", "OnJavaMessage", String.format("%s@%s@%s", str, str2, str3));
    }

    private static class DialogRunnable implements Runnable {
        private String name;

        DialogRunnable(String str) {
            this.name = str;
        }

        void runWith(UniWebViewDialog uniWebViewDialog) {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Runnable
        public void run() {
            UniWebViewDialog uniWebViewDialog = UniWebViewManager.getInstance().getUniWebViewDialog(this.name);
            if (uniWebViewDialog != null) {
                runWith(uniWebViewDialog);
                return;
            }
            Logger.getInstance().critical("Did not find the correct web view dialog for name: " + this.name);
        }
    }
}
