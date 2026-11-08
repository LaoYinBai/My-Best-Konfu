package com.onevcat.uniwebview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class UniWebViewDialog extends Dialog implements OnKeyboardVisibilityListener {
    static final int ANIMATION_EDGE_BOTTOM = 3;
    static final int ANIMATION_EDGE_LEFT = 2;
    static final int ANIMATION_EDGE_NONE = 0;
    static final int ANIMATION_EDGE_RIGHT = 4;
    static final int ANIMATION_EDGE_TOP = 1;
    private Activity activity;
    private boolean animating;
    private boolean backButtonEnabled;
    private UniWebViewChromeClient chromeClient;
    private HashMap<String, String> headers;
    private int height;
    private boolean immersiveMode;
    private boolean isLoading;
    final DialogListener listener;
    private boolean loadingInterrupted;
    private boolean openLinksInExternalBrowser;
    private HashSet<String> permissionTrustDomains;
    private HashSet<String> schemes;
    private boolean showHTTPAuthPopUpWindow;
    private boolean showSpinnerWhileLoading;
    private ProgressDialog spinner;
    private String spinnerText;
    private HashSet<String> sslExceptionDomains;
    boolean touchFromAnotherDialog;
    private UniWebView uniWebView;
    private String userAgent;
    private boolean userInteractionEnabled;
    FrameLayout videoContainer;
    private float webViewAlpha;
    FrameLayout webViewContainer;
    private String webViewName;
    private boolean webViewShowing;
    private boolean webViewVisible;
    private int width;
    private AndroidBug5497Workaround workaround;
    private int x;
    private int y;
    static HashMap<String, String> presetUserAgent = new HashMap<>();
    static String defaultUserAgent = "";

    interface DialogListener {
        void onAddJavaScriptFinished(UniWebViewDialog uniWebViewDialog, String str);

        void onAnimationFinished(UniWebViewDialog uniWebViewDialog, String str);

        void onDialogClose(UniWebViewDialog uniWebViewDialog);

        void onDialogClosedByBackButton(UniWebViewDialog uniWebViewDialog);

        void onDialogKeyDown(UniWebViewDialog uniWebViewDialog, int i);

        void onHideTransitionFinished(UniWebViewDialog uniWebViewDialog, String str);

        void onJavaScriptFinished(UniWebViewDialog uniWebViewDialog, String str);

        void onPageFinished(UniWebViewDialog uniWebViewDialog, int i, String str);

        void onPageStarted(UniWebViewDialog uniWebViewDialog, String str);

        void onReceivedError(UniWebViewDialog uniWebViewDialog, int i, String str, String str2);

        void onSendMessageReceived(UniWebViewDialog uniWebViewDialog, String str);

        void onShowTransitionFinished(UniWebViewDialog uniWebViewDialog, String str);

        boolean shouldOverrideUrlLoading(UniWebViewDialog uniWebViewDialog, String str);
    }

    public UniWebViewChromeClient getChromeClient() {
        return this.chromeClient;
    }

    public String getWebViewName() {
        return this.webViewName;
    }

    public void setWebViewName(String str) {
        this.webViewName = str;
    }

    static String getUserAgent(UniWebViewDialog uniWebViewDialog, String str) {
        if (uniWebViewDialog != null) {
            return uniWebViewDialog.getUserAgent();
        }
        String str2 = presetUserAgent.get(str);
        return str2 != null ? str2 : defaultUserAgent;
    }

    static void setUserAgent(UniWebViewDialog uniWebViewDialog, String str, String str2) {
        if (uniWebViewDialog != null) {
            uniWebViewDialog.setUserAgent(str2);
            presetUserAgent.remove(str);
        } else {
            presetUserAgent.put(str, str2);
        }
    }

    private String getUserAgent() {
        return this.userAgent;
    }

    private void setUserAgent(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            getWebView().getSettings().setUserAgentString(str);
        }
        this.userAgent = str;
    }

    String getUrl() {
        return getWebView().getUrl();
    }

    boolean isCanGoBack() {
        return getWebView().canGoBack();
    }

    boolean isCanGoForward() {
        return getWebView().canGoForward();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    UniWebViewDialog(Activity activity, DialogListener dialogListener) {
        super(activity, android.R.style.Theme.Holo.NoActionBar);
        this.immersiveMode = true;
        this.showHTTPAuthPopUpWindow = true;
        this.spinnerText = null;
        this.userInteractionEnabled = true;
        this.backButtonEnabled = true;
        this.webViewAlpha = 1.0f;
        this.webViewShowing = false;
        this.webViewVisible = false;
        this.userAgent = defaultUserAgent;
        this.touchFromAnotherDialog = false;
        Logger.getInstance().debug("Creating new UniWebView dialog.");
        this.activity = activity;
        this.listener = dialogListener;
        this.schemes = new HashSet<>();
        this.schemes.add("uniwebview");
        this.permissionTrustDomains = new HashSet<>();
        this.sslExceptionDomains = new HashSet<>();
        this.headers = new HashMap<>();
        prepareWindow();
        hideSystemUI();
        addWebViewContent();
        setBouncesEnabled(false);
        setKeyboardVisibilityListener(this);
        this.workaround = AndroidBug5497Workaround.assistFrameLayout(this.webViewContainer, activity);
        this.uniWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.onevcat.uniwebview.UniWebViewDialog.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return !UniWebViewDialog.this.isUserInteractionEnabled();
            }
        });
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.touchFromAnotherDialog) {
            return (this.webViewContainer.getVisibility() == 0) && isEventInside(motionEvent);
        }
        return UniWebViewManager.getInstance().handleTouchEvent(this, this.activity, motionEvent);
    }

    private boolean isEventInside(MotionEvent motionEvent) {
        return isViewContains(this.uniWebView, (int) motionEvent.getRawX(), (int) motionEvent.getRawY());
    }

    private boolean isViewContains(View view, int i, int i2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        return i >= i3 && i <= i3 + view.getWidth() && i2 >= i4 && i2 <= i4 + view.getHeight();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Logger.getInstance().verbose("onKeyDown, key code: " + i);
        if (i != 4) {
            Logger.getInstance().verbose("Not back key. Delegating to super...");
            return super.onKeyDown(i, keyEvent);
        }
        if (!this.backButtonEnabled) {
            Logger.getInstance().verbose("Back button is not enabled. Ignore.");
            return true;
        }
        if (!goBack()) {
            Logger.getInstance().verbose("No back page for the web view. Trying to close current web view...");
            this.listener.onDialogClosedByBackButton(this);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Logger.getInstance().verbose("dispatchKeyEvent: " + keyEvent);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            Logger.getInstance().verbose("Key down event for: " + keyCode);
            this.listener.onDialogKeyDown(this, keyCode);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        this.listener.onDialogClose(this);
    }

    boolean getAnimating() {
        return this.animating;
    }

    HashSet<String> getSchemes() {
        return this.schemes;
    }

    void setFrame(int i, int i2, int i3, int i4) {
        Logger.getInstance().verbose(String.format(Locale.US, "Setting web dialog frame to {%d, %d, %d, %d}", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        setPosition(i, i2);
        setSize(i3, i4);
    }

    void setPosition(int i, int i2) {
        this.x = i;
        this.y = i2;
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 51;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        this.webViewContainer.setX(i);
        this.webViewContainer.setY(i2);
    }

    void setSize(int i, int i2) {
        this.width = Math.max(0, i);
        this.height = Math.max(0, i2);
        Window window = getWindow();
        Point pointDisplaySize = displaySize();
        window.setLayout(pointDisplaySize.x, pointDisplaySize.y);
        ViewGroup.LayoutParams layoutParams = this.webViewContainer.getLayoutParams();
        layoutParams.width = this.width;
        layoutParams.height = this.height;
        this.webViewContainer.setLayoutParams(layoutParams);
        this.workaround.setTargetHeight(this.height);
    }

    void updateFrame() {
        setPosition(this.x, this.y);
        setSize(this.width, this.height);
    }

    boolean setShow(final boolean z, boolean z2, int i, float f, final String str) {
        if (this.webViewVisible && z) {
            Logger.getInstance().critical("Showing web view is ignored since it is already visible.");
            return false;
        }
        if (!this.webViewVisible && !z) {
            Logger.getInstance().critical("Hiding web view is ignored since it is already invisible.");
            return false;
        }
        if (this.animating) {
            Logger.getInstance().critical("Trying to animate but another transition animation is not finished yet. Ignore this one.");
            return false;
        }
        if (this.webViewShowing) {
            if (z == (this.webViewContainer.getVisibility() == 0)) {
                return false;
            }
        }
        if (z) {
            this.webViewVisible = true;
            showDialog();
            UniWebViewManager.getInstance().addShowingDialog(this);
            if (this.showSpinnerWhileLoading && this.isLoading) {
                showSpinner();
            }
        } else {
            this.webViewVisible = false;
            ((InputMethodManager) this.activity.getSystemService("input_method")).hideSoftInputFromWindow(this.uniWebView.getWindowToken(), 0);
            hideSpinner();
        }
        if (!z2 && i == 0) {
            new Handler().postDelayed(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewDialog.2
                @Override // java.lang.Runnable
                public void run() {
                    UniWebViewDialog.this.finishShowDialog(z, str);
                }
            }, 1L);
        } else {
            animatedShow(z, z2, i, f, str);
        }
        return true;
    }

    private void animatedShow(final boolean z, boolean z2, int i, float f, final String str) {
        this.animating = true;
        AnimationSet animationSet = new AnimationSet(false);
        int i2 = (int) (f * 1000.0f);
        Animation animationFadeAnimation = fadeAnimation(z, z2, i2);
        if (animationFadeAnimation != null) {
            animationSet.addAnimation(animationFadeAnimation);
        }
        Animation animationMoveAnimation = moveAnimation(z, i, i2);
        if (animationMoveAnimation != null) {
            animationSet.addAnimation(animationMoveAnimation);
        }
        this.webViewContainer.startAnimation(animationSet);
        new Handler().postDelayed(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewDialog.3
            @Override // java.lang.Runnable
            public void run() {
                UniWebViewDialog.this.animating = false;
                UniWebViewDialog.this.webViewContainer.clearAnimation();
                UniWebViewDialog.this.finishShowDialog(z, str);
            }
        }, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishShowDialog(boolean z, String str) {
        if (z) {
            this.listener.onShowTransitionFinished(this, str);
        } else {
            this.webViewContainer.setVisibility(8);
            this.listener.onHideTransitionFinished(this, str);
        }
    }

    boolean animateTo(final int i, final int i2, final int i3, final int i4, float f, float f2, final String str) {
        if (this.animating) {
            Logger.getInstance().critical("Trying to animate but another transition animation is not finished yet. Ignore this one.");
            return false;
        }
        this.animating = true;
        int i5 = (int) (f * 1000.0f);
        int i6 = (int) (1000.0f * f2);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(moveToAnimation(i, i2, i5, i6));
        animationSet.addAnimation(sizeToAnimation(i3, i4, i5, i6));
        this.webViewContainer.startAnimation(animationSet);
        new Handler().postDelayed(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewDialog.4
            @Override // java.lang.Runnable
            public void run() {
                UniWebViewDialog.this.animating = false;
                UniWebViewDialog.this.setFrame(i, i2, i3, i4);
                UniWebViewDialog.this.webViewContainer.clearAnimation();
                UniWebViewDialog.this.listener.onAnimationFinished(UniWebViewDialog.this, str);
            }
        }, i5 + i6);
        return true;
    }

    void load(String str) {
        Logger.getInstance().info("UniWebView will load url: " + str + ". With headers: " + this.headers.toString());
        if (shouldOverride(str, false)) {
            return;
        }
        this.uniWebView.loadUrl(str, this.headers);
    }

    void stop() {
        this.uniWebView.stopLoading();
        this.uniWebView.getClient().setUserStopped(true);
    }

    boolean shouldOverride(String str, boolean z) {
        Logger logger = Logger.getInstance();
        logger.info("shouldOverrideUrlLoading for: " + str);
        URLLoadingResponser uRLLoadingResponser = new URLLoadingResponser(this.activity, this, str);
        if (uRLLoadingResponser.canResponseBuiltinScheme()) {
            logger.debug("Url handled internally in UniWebView.");
            return true;
        }
        if (uRLLoadingResponser.handleWithIntent()) {
            logger.debug("Url handled by intent.");
            return true;
        }
        if (uRLLoadingResponser.canResponseDefinedScheme()) {
            logger.debug("Url redirected to Unity: " + str);
            this.listener.onSendMessageReceived(this, str);
            return true;
        }
        if (z && openUrlExternal(str)) {
            logger.debug("Url redirected to Unity: " + str);
            return true;
        }
        if (uRLLoadingResponser.handleWithThirdPartyApp()) {
            logger.debug("Url handled by a third party app: " + str);
            return true;
        }
        logger.debug("Url is opening without overridden: " + str);
        this.uniWebView.getClient().prepareLoadingStates();
        return false;
    }

    void loadHTMLString(String str, String str2) {
        Logger.getInstance().info("UniWebView will load html string with base url: " + str2);
        Logger.getInstance().verbose("Input html content: \n" + str);
        this.uniWebView.getClient().prepareLoadingStates();
        this.uniWebView.loadDataWithBaseURL(str2, str, "text/html", C.UTF8_NAME, null);
    }

    void addJavaScript(String str, final String str2) {
        if (str == null) {
            Logger.getInstance().critical("Trying to add null as js string. Aborting...");
            return;
        }
        Logger.getInstance().debug("Adding javascript string to web view. Requesting string: " + str);
        this.uniWebView.evaluateJavascript(str, new ValueCallback<String>() { // from class: com.onevcat.uniwebview.UniWebViewDialog.5
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String str3) {
                Logger.getInstance().info("Receive a call back of adding js interface: " + str3);
                if (str3.equalsIgnoreCase("null")) {
                    UniWebViewDialog.this.listener.onAddJavaScriptFinished(UniWebViewDialog.this, new UniWebViewResultPayload(str2, PushConstants.PUSH_TYPE_NOTIFY, "").toString());
                } else {
                    UniWebViewDialog.this.listener.onAddJavaScriptFinished(UniWebViewDialog.this, new UniWebViewResultPayload(str2, "-1", str3).toString());
                }
            }
        });
    }

    void evaluateJavaScript(String str, final String str2) {
        if (str == null) {
            Logger.getInstance().critical("Trying to evaluate null as js string. Aborting...");
            return;
        }
        Logger.getInstance().debug("Evaluating javascript string in web view. Requesting string: " + str);
        this.uniWebView.evaluateJavascript(str, new ValueCallback<String>() { // from class: com.onevcat.uniwebview.UniWebViewDialog.6
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String str3) {
                Logger.getInstance().info("Receive a call back of evaluating js interface: " + str3);
                if (str3.equalsIgnoreCase("null")) {
                    UniWebViewDialog.this.listener.onJavaScriptFinished(UniWebViewDialog.this, new UniWebViewResultPayload(str2, PushConstants.PUSH_TYPE_NOTIFY, "").toString());
                    return;
                }
                UniWebViewDialog.this.listener.onJavaScriptFinished(UniWebViewDialog.this, new UniWebViewResultPayload(str2, PushConstants.PUSH_TYPE_NOTIFY, UniWebViewDialog.this.unescapeJavaString(UniWebViewDialog.this.removeUTFCharacters(str3.replaceAll("^\"|\"$", "")).toString())).toString());
            }
        });
    }

    boolean goBack() {
        UniWebView popUpWebView = this.chromeClient.getPopUpWebView();
        if (popUpWebView != null) {
            if (popUpWebView.canGoBack()) {
                popUpWebView.goBack();
            } else {
                popUpWebView.evaluateJavascript("window.close()", new ValueCallback<String>() { // from class: com.onevcat.uniwebview.UniWebViewDialog.7
                    @Override // android.webkit.ValueCallback
                    public void onReceiveValue(String str) {
                    }
                });
            }
            return true;
        }
        if (!this.uniWebView.canGoBack()) {
            return false;
        }
        this.uniWebView.goBack();
        return true;
    }

    boolean goForward() {
        if (!this.uniWebView.canGoForward()) {
            return false;
        }
        this.uniWebView.goForward();
        return true;
    }

    void destroy() {
        this.uniWebView.loadUrl("about:blank");
        UniWebViewManager.getInstance().removeShowingDialog(this);
        CookieManager.getInstance().flush();
        dismiss();
    }

    void goBackground() {
        if (this.isLoading) {
            this.loadingInterrupted = true;
            this.uniWebView.stopLoading();
        }
        if (this.webViewShowing) {
            AlertDialog alertDialog = this.chromeClient.getAlertDialog();
            if (alertDialog != null) {
                alertDialog.hide();
            }
            hide();
            this.uniWebView.onPause();
        }
    }

    void goForeground() {
        if (this.loadingInterrupted) {
            this.uniWebView.reload();
            this.loadingInterrupted = false;
        }
        if (this.webViewShowing) {
            show();
            AlertDialog alertDialog = this.chromeClient.getAlertDialog();
            if (alertDialog != null) {
                alertDialog.show();
            }
            this.uniWebView.onResume();
        }
    }

    void setSpinnerText(String str) {
        if (str != null) {
            this.spinnerText = str;
        } else {
            this.spinnerText = getContext().getResources().getString(R.string.LOADING);
        }
        this.spinner.setMessage(this.spinnerText);
    }

    void showSpinner() {
        Logger.getInstance().verbose("Show spinner for loading.");
        this.spinner.show();
    }

    void hideSpinner() {
        Logger.getInstance().verbose("Hide spinner.");
        this.spinner.hide();
    }

    void setBackgroundColor(float f, float f2, float f3, float f4) {
        int iArgb = Color.argb((int) (f4 * 255.0f), (int) (f * 255.0f), (int) (f2 * 255.0f), (int) (f3 * 255.0f));
        this.webViewContainer.setBackground(new ColorDrawable(iArgb));
        this.videoContainer.setBackground(new ColorDrawable(iArgb));
    }

    void setOpenLinksInExternalBrowser(boolean z) {
        this.openLinksInExternalBrowser = z;
    }

    private void prepareWindow() {
        Logger.getInstance().verbose("Preparing window layout for web view dialog.");
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.addFlags(32);
        window.setSoftInputMode(16);
        window.setFlags(1024, 1024);
        window.setFlags(C.DEFAULT_MUXED_BUFFER_SIZE, C.DEFAULT_MUXED_BUFFER_SIZE);
        if (Build.VERSION.SDK_INT >= 28) {
            int i = this.activity.getWindow().getAttributes().layoutInDisplayCutoutMode;
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = i;
            getWindow().setAttributes(attributes);
        }
    }

    private void addWebViewContent() {
        this.webViewContainer = new FrameLayout(getContext());
        this.webViewContainer.setVisibility(0);
        this.videoContainer = new FrameLayout(getContext());
        this.videoContainer.setVisibility(4);
        this.uniWebView = new UniWebView(this.activity);
        this.uniWebView = new UniWebView(this.activity) { // from class: com.onevcat.uniwebview.UniWebViewDialog.8
            @Override // com.onevcat.uniwebview.UniWebView
            public HashMap<String, String> getCustomizeHeaders() {
                Logger.getInstance().critical("Get header!!!");
                return UniWebViewDialog.this.getHeaders();
            }
        };
        this.uniWebView.setClient(new UniWebViewClient(this));
        this.chromeClient = new UniWebViewChromeClient(this.activity, this.webViewContainer, this.videoContainer, null, this.uniWebView, this);
        this.uniWebView.setWebChromeClient(this.chromeClient);
        this.uniWebView.setDownloadListener(new DownloadListener() { // from class: com.onevcat.uniwebview.UniWebViewDialog.9
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                Logger.getInstance().info("UniWebView onDownloadStart for url: " + str);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                UniWebViewDialog.this.activity.startActivity(intent);
            }
        });
        this.uniWebView.setVisibility(0);
        this.uniWebView.setBackgroundColor(0);
        this.spinner = new ProgressDialog(getContext());
        this.spinner.setCanceledOnTouchOutside(true);
        this.spinner.requestWindowFeature(1);
        setSpinnerText(this.spinnerText);
        addContentView(this.videoContainer, new ViewGroup.LayoutParams(-1, -1));
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(this.width, this.height);
        this.webViewContainer.setX(this.x);
        this.webViewContainer.setY(this.y);
        addContentView(this.webViewContainer, layoutParams);
        this.webViewContainer.addView(this.uniWebView);
        setBackgroundColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    boolean openUrlExternal(String str) {
        if (this.uniWebView == null || this.uniWebView.getHitTestResult() == null) {
            Logger.getInstance().critical("Failed to open url due to dialog or webview being null. Url: " + str);
            return false;
        }
        if (!this.openLinksInExternalBrowser) {
            Logger.getInstance().verbose("UniWebView should open links in current web view.");
            return false;
        }
        if (this.uniWebView.getHitTestResult().getType() == 0) {
            Logger.getInstance().debug("UniWebView getHitTestResult unknown. Do not open url externally.");
            return false;
        }
        Logger.getInstance().verbose("UniWebView is opening links in external browser.");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        getContext().startActivity(intent);
        return true;
    }

    void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(this.immersiveMode ? 5894 : 4);
    }

    void setLoading(boolean z) {
        this.isLoading = z;
    }

    boolean isShowSpinnerWhileLoading() {
        return this.showSpinnerWhileLoading;
    }

    void setShowSpinnerWhileLoading(boolean z) {
        this.showSpinnerWhileLoading = z;
    }

    void print() {
        ((PrintManager) getContext().getSystemService("print")).print("UniWebView Printing", getWebView().createPrintDocumentAdapter(this.uniWebView.getUrl()), new PrintAttributes.Builder().build());
    }

    UniWebView getWebView() {
        return this.uniWebView;
    }

    HashSet<String> getSslExceptionDomains() {
        return this.sslExceptionDomains;
    }

    HashSet<String> getPermissionTrustDomains() {
        return this.permissionTrustDomains;
    }

    boolean getImmersiveMode() {
        return this.immersiveMode;
    }

    private void setKeyboardVisibilityListener(final OnKeyboardVisibilityListener onKeyboardVisibilityListener) {
        final View childAt = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.onevcat.uniwebview.UniWebViewDialog.10
            private final int EstimatedKeyboardDP;
            private boolean alreadyOpen;
            private final int defaultKeyboardHeightDP = 100;
            private final Rect rect;

            {
                this.EstimatedKeyboardDP = (Build.VERSION.SDK_INT >= 21 ? 48 : 0) + 100;
                this.rect = new Rect();
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int iApplyDimension = (int) TypedValue.applyDimension(1, this.EstimatedKeyboardDP, childAt.getResources().getDisplayMetrics());
                childAt.getWindowVisibleDisplayFrame(this.rect);
                boolean z = childAt.getRootView().getHeight() - (this.rect.bottom - this.rect.top) >= iApplyDimension;
                if (z == this.alreadyOpen) {
                    return;
                }
                this.alreadyOpen = z;
                onKeyboardVisibilityListener.onVisibilityChanged(z);
            }
        });
    }

    @Override // com.onevcat.uniwebview.OnKeyboardVisibilityListener
    public void onVisibilityChanged(boolean z) {
        if (z) {
            return;
        }
        new Handler().post(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewDialog.11
            @Override // java.lang.Runnable
            public void run() {
                UniWebViewDialog.this.hideSystemUI();
            }
        });
    }

    void setImmersiveMode(boolean z) {
        this.immersiveMode = z;
    }

    void setBouncesEnabled(boolean z) {
        if (z) {
            this.uniWebView.setOverScrollMode(0);
        } else {
            this.uniWebView.setOverScrollMode(2);
        }
    }

    void setCalloutEnabled(boolean z) {
        this.uniWebView.calloutEnabled = z;
    }

    void setBackButtonEnabled(boolean z) {
        this.backButtonEnabled = z;
    }

    void setZoomEnabled(boolean z) {
        this.uniWebView.getSettings().setBuiltInZoomControls(z);
    }

    float getWebViewAlpha() {
        return this.webViewAlpha;
    }

    void setWebViewAlpha(float f, boolean z) {
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        this.webViewAlpha = f;
        if (z) {
            this.webViewContainer.setAlpha(f);
        }
    }

    void setHeaderField(String str, String str2) {
        if (str == null || str.length() == 0) {
            Logger.getInstance().critical("Trying to set null or empty key for header field. Please check you have set correct key.");
        } else if (str2 == null) {
            this.headers.remove(str);
        } else {
            this.headers.put(str, str2);
        }
    }

    void setSupportMultipleWindows(boolean z) {
        this.uniWebView.getSettings().setSupportMultipleWindows(z);
    }

    void setDefaultFontSize(int i) {
        this.uniWebView.getSettings().setDefaultFontSize(Math.round(i / this.activity.getResources().getConfiguration().fontScale));
    }

    HashMap<String, String> getHeaders() {
        return this.headers;
    }

    void clearHttpAuthUsernamePassword(String str, String str2) {
        getWebView().setHttpAuthUsernamePassword(str, str2, null, null);
    }

    void scrollTo(int i, int i2, boolean z) {
        if (z) {
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this.uniWebView, "scrollX", this.uniWebView.getScrollX(), i);
            ObjectAnimator objectAnimatorOfInt2 = ObjectAnimator.ofInt(this.uniWebView, "scrollY", this.uniWebView.getScrollY(), i2);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorOfInt, objectAnimatorOfInt2);
            animatorSet.setDuration(400L).start();
            return;
        }
        this.uniWebView.scrollTo(i, i2);
    }

    private void showDialog() {
        if (this.webViewShowing) {
            this.webViewContainer.setVisibility(0);
        } else {
            this.webViewShowing = true;
            show();
        }
    }

    private Point displaySize() {
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        if (this.immersiveMode) {
            defaultDisplay.getRealSize(point);
            return point;
        }
        defaultDisplay.getSize(point);
        return point;
    }

    private Animation fadeAnimation(boolean z, boolean z2, int i) {
        if (!z2) {
            return null;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(z ? 0.0f : getWebViewAlpha(), z ? getWebViewAlpha() : 0.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(i);
        return alphaAnimation;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.animation.Animation moveAnimation(boolean r7, int r8, int r9) {
        /*
            r6 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            android.graphics.Point r1 = r6.displaySize()
            r2 = 1
            r3 = 0
            if (r8 != r2) goto L12
            int r8 = r1.y
            int r8 = -r8
        Lf:
            r3 = r8
            r8 = 0
            goto L24
        L12:
            r4 = 2
            if (r8 != r4) goto L19
            int r8 = r1.x
            int r8 = -r8
            goto L24
        L19:
            r4 = 3
            if (r8 != r4) goto L1f
            int r8 = r1.y
            goto Lf
        L1f:
            r4 = 4
            if (r8 != r4) goto L45
            int r8 = r1.x
        L24:
            android.view.animation.TranslateAnimation r0 = new android.view.animation.TranslateAnimation
            r1 = 0
            if (r7 == 0) goto L2b
            float r4 = (float) r8
            goto L2c
        L2b:
            r4 = 0
        L2c:
            if (r7 == 0) goto L30
            r8 = 0
            goto L31
        L30:
            float r8 = (float) r8
        L31:
            if (r7 == 0) goto L35
            float r5 = (float) r3
            goto L36
        L35:
            r5 = 0
        L36:
            if (r7 == 0) goto L39
            goto L3a
        L39:
            float r1 = (float) r3
        L3a:
            r0.<init>(r4, r8, r5, r1)
            r0.setFillAfter(r2)
            long r7 = (long) r9
            r0.setDuration(r7)
            return r0
        L45:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onevcat.uniwebview.UniWebViewDialog.moveAnimation(boolean, int, int):android.view.animation.Animation");
    }

    private Animation moveToAnimation(int i, int i2, int i3, int i4) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, i - this.x, 0.0f, i2 - this.y);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(i3);
        translateAnimation.setStartOffset(i4);
        return translateAnimation;
    }

    private Animation sizeToAnimation(int i, int i2, int i3, int i4) {
        ResizeAnimation resizeAnimation = new ResizeAnimation(this.webViewContainer, this.width, i, this.height, i2);
        resizeAnimation.setFillAfter(true);
        resizeAnimation.setDuration(i3);
        resizeAnimation.setStartOffset(i4);
        return resizeAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StringBuffer removeUTFCharacters(String str) {
        Matcher matcher = Pattern.compile("\\\\u(\\p{XDigit}{4})").matcher(str);
        StringBuffer stringBuffer = new StringBuffer(str.length());
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(String.valueOf((char) Integer.parseInt(matcher.group(1), 16))));
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String unescapeJavaString(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onevcat.uniwebview.UniWebViewDialog.unescapeJavaString(java.lang.String):java.lang.String");
    }

    public boolean getShowHTTPAuthPopUpWindow() {
        return this.showHTTPAuthPopUpWindow;
    }

    public void setShowHTTPAuthPopUpWindow(boolean z) {
        this.showHTTPAuthPopUpWindow = z;
    }

    public boolean isUserInteractionEnabled() {
        return this.userInteractionEnabled;
    }

    public void setUserInteractionEnabled(boolean z) {
        this.userInteractionEnabled = z;
    }
}
