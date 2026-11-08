package com.onevcat.uniwebview;

import android.app.Activity;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class UniWebViewManager {
    private static UniWebViewManager instance;
    private HashMap<String, UniWebViewDialog> webViewDialog = new HashMap<>();
    private ArrayList<UniWebViewDialog> showingDialogs = new ArrayList<>();

    private UniWebViewManager() {
    }

    static UniWebViewManager getInstance() {
        if (instance == null) {
            instance = new UniWebViewManager();
        }
        return instance;
    }

    UniWebViewDialog getUniWebViewDialog(String str) {
        if (str == null || str.length() == 0 || !this.webViewDialog.containsKey(str)) {
            return null;
        }
        return this.webViewDialog.get(str);
    }

    void removeUniWebView(String str) {
        if (this.webViewDialog.containsKey(str)) {
            Logger.getInstance().debug("Removing web view dialog from manager: " + str);
            this.webViewDialog.remove(str);
        }
    }

    void setUniWebView(String str, UniWebViewDialog uniWebViewDialog) {
        Logger.getInstance().debug("Adding web view dialog to manager: " + str);
        uniWebViewDialog.setWebViewName(str);
        this.webViewDialog.put(str, uniWebViewDialog);
    }

    Collection<UniWebViewDialog> allDialogs() {
        return this.webViewDialog.values();
    }

    void addShowingDialog(UniWebViewDialog uniWebViewDialog) {
        if (this.showingDialogs.contains(uniWebViewDialog)) {
            return;
        }
        this.showingDialogs.add(uniWebViewDialog);
    }

    void removeShowingDialog(UniWebViewDialog uniWebViewDialog) {
        this.showingDialogs.remove(uniWebViewDialog);
    }

    boolean handleTouchEvent(UniWebViewDialog uniWebViewDialog, Activity activity, MotionEvent motionEvent) {
        boolean z = false;
        for (UniWebViewDialog uniWebViewDialog2 : allDialogs()) {
            if (uniWebViewDialog2 != uniWebViewDialog) {
                uniWebViewDialog2.getWebView().requestFocus();
                boolean z2 = true;
                uniWebViewDialog2.touchFromAnotherDialog = true;
                if (!uniWebViewDialog2.dispatchTouchEvent(motionEvent) && !z) {
                    z2 = false;
                }
                uniWebViewDialog2.touchFromAnotherDialog = false;
                z = z2;
            }
        }
        if (!z) {
            activity.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    ArrayList<UniWebViewDialog> getShowingDialogs() {
        return this.showingDialogs;
    }
}
