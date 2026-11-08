package com.mbridge.msdk.advanced.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.advanced.js.b;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.h;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBNativeAdvancedView extends RelativeLayout {
    private static String a = "MBAdvancedNativeView";
    private MBNativeAdvancedWebview b;
    private View c;
    private boolean d;
    private boolean e;
    private boolean f;
    private com.mbridge.msdk.advanced.js.a g;
    private Context h;

    public void setH5Ready(boolean z) {
        this.d = z;
    }

    public void setVideoReady(boolean z) {
        this.e = z;
    }

    public boolean isEndCardReady() {
        return this.f;
    }

    public void setEndCardReady(boolean z) {
        this.f = z;
    }

    public boolean isH5Ready() {
        return this.d;
    }

    public boolean isVideoReady() {
        return this.e;
    }

    public void resetLoadState() {
        this.f = false;
        this.e = false;
        this.d = false;
    }

    public com.mbridge.msdk.advanced.js.a getAdvancedNativeJSBridgeImpl() {
        return this.g;
    }

    public void setAdvancedNativeJSBridgeImpl(com.mbridge.msdk.advanced.js.a aVar) {
        this.g = aVar;
        if (this.b != null) {
            this.b.setObject(this.g);
        }
    }

    public MBNativeAdvancedView(Context context) {
        this(context, null);
    }

    public MBNativeAdvancedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MBNativeAdvancedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = context;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public MBNativeAdvancedWebview getAdvancedNativeWebview() {
        return this.b;
    }

    public void setAdvancedNativeWebview(MBNativeAdvancedWebview mBNativeAdvancedWebview) {
        this.b = mBNativeAdvancedWebview;
        if (this.g != null) {
            mBNativeAdvancedWebview.setObject(this.g);
        }
    }

    public void changeCloseBtnState(int i) {
        if (this.c != null) {
            if (i == 2) {
                this.c.setVisibility(8);
            } else {
                this.c.setVisibility(0);
            }
        }
    }

    public View getCloseView() {
        return this.c;
    }

    public void setCloseView(View view) {
        this.c = view;
        if (view != null) {
            view.setContentDescription("closeButton");
        }
    }

    public void clearResState() {
        this.f = false;
        this.e = false;
        this.d = false;
    }

    public void clearResStateAndRemoveClose() {
        clearResState();
        if (this.c == null || this.c.getParent() == null) {
            return;
        }
        removeView(this.c);
    }

    public static void transInfoForMraid(WebView webView, int i, int i2, int i3, int i4) {
        s.d(a, "transInfoForMraid");
        try {
            int i5 = com.mbridge.msdk.foundation.controller.a.f().j().getResources().getConfiguration().orientation;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("orientation", i5 == 2 ? "landscape" : i5 == 1 ? "portrait" : "undefined");
            jSONObject.put("locked", "true");
            float fW = o.w(com.mbridge.msdk.foundation.controller.a.f().j());
            float fX = o.x(com.mbridge.msdk.foundation.controller.a.f().j());
            HashMap mapY = o.y(com.mbridge.msdk.foundation.controller.a.f().j());
            int iIntValue = ((Integer) mapY.get("width")).intValue();
            int iIntValue2 = ((Integer) mapY.get("height")).intValue();
            HashMap map = new HashMap();
            map.put("placementType", "inline");
            map.put("state", "default");
            map.put("viewable", "true");
            map.put("currentAppOrientation", jSONObject);
            float f = i;
            float f2 = i2;
            float f3 = i3;
            float f4 = i4;
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(webView, f, f2, f3, f4);
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(webView, f, f2, f3, f4);
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(webView, fW, fX);
            com.mbridge.msdk.mbjscommon.mraid.a.a().c(webView, iIntValue, iIntValue2);
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(webView, map);
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(webView);
        } catch (Throwable th) {
            s.b(a, "transInfoForMraid", th);
        }
    }

    public void destroy() {
        removeAllViews();
        if (this.b != null && !this.b.isDestoryed()) {
            this.b.release();
            b.a(this.b, "onSystemDestory", "");
        }
        if (this.h != null) {
            this.h = null;
        }
    }

    public void show() {
        if (this.b != null && this.b.getParent() == null) {
            addView(this.b, new ViewGroup.LayoutParams(-1, -1));
        }
        int[] iArr = new int[2];
        this.b.getLocationInWindow(iArr);
        transInfoForMraid(this.b, iArr[0], iArr[1], this.b.getWidth(), this.b.getHeight());
        if (this.b != null) {
            this.b.setObject(this.g);
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.advanced.view.MBNativeAdvancedView.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        s.a(MBNativeAdvancedView.a, "webviewshow");
                        String string = "";
                        try {
                            int[] iArr2 = new int[2];
                            MBNativeAdvancedView.this.b.getLocationOnScreen(iArr2);
                            s.d(MBNativeAdvancedView.a, "coordinate:" + iArr2[0] + "--" + iArr2[1]);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("startX", w.a(com.mbridge.msdk.foundation.controller.a.f().j(), (float) iArr2[0]));
                            jSONObject.put("startY", w.a(com.mbridge.msdk.foundation.controller.a.f().j(), (float) iArr2[1]));
                            string = jSONObject.toString();
                        } catch (Throwable th) {
                            s.b(MBNativeAdvancedView.a, th.getMessage(), th);
                        }
                        h.a().a((WebView) MBNativeAdvancedView.this.b, "webviewshow", Base64.encodeToString(string.toString().getBytes(), 2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if (this.c != null) {
            if (this.c.getParent() == null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 28.0f), w.b(getContext(), 16.0f));
                layoutParams.addRule(10);
                layoutParams.addRule(11);
                layoutParams.rightMargin = w.b(getContext(), 2.0f);
                layoutParams.topMargin = w.b(getContext(), 2.0f);
                addView(this.c, layoutParams);
            } else {
                bringChildToFront(this.c);
            }
        }
        clearResState();
    }
}
