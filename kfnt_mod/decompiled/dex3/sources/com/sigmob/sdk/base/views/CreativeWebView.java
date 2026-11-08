package com.sigmob.sdk.base.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.utils.k;
import com.sigmob.sdk.common.utils.m;
import com.tachikoma.core.utility.UriUtil;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CreativeWebView extends BaseWebView {
    private static float b = 10.0f;
    private final AdLogoView a;
    private a c;

    public interface a {
        void a(MotionEvent motionEvent, MotionEvent motionEvent2);
    }

    class b implements View.OnTouchListener {
        MotionEvent a;
        private boolean c;

        b() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.c = true;
                    this.a = MotionEvent.obtain(motionEvent);
                    return false;
                case 1:
                    if (!this.c) {
                        return false;
                    }
                    this.c = false;
                    if (CreativeWebView.this.c != null) {
                        CreativeWebView.this.c.a(this.a, motionEvent);
                    }
                    return false;
                default:
                    return false;
            }
        }
    }

    private CreativeWebView(Context context, String str, boolean z, boolean z2) {
        super(context);
        a();
        getSettings().setJavaScriptEnabled(true);
        a(true);
        setBackgroundColor(0);
        if (z) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            this.a = new AdLogoView(getContext(), 1);
            this.a.a(str);
            this.a.b(com.sigmob.sdk.base.b.f());
            addView(this.a, layoutParams);
        } else {
            this.a = null;
        }
        setOnTouchListener(new b());
    }

    public static CreativeWebView a(Context context, String str, boolean z, boolean z2) {
        m.a.a(context);
        return new CreativeWebView(context, str, z, z2);
    }

    private void a() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
        setScrollBarStyle(0);
    }

    void a(String str) {
        File fileA = com.sigmob.sdk.common.utils.d.a(str, k.a(str) + ".html");
        if (fileA == null || TextUtils.isEmpty(fileA.getAbsolutePath())) {
            loadDataWithBaseURL(com.sigmob.sdk.common.e.f.h() + "://localhost/", str, "text/html", "utf-8", null);
            return;
        }
        loadUrl(UriUtil.FILE_PREFIX + fileA.getAbsolutePath());
    }

    @Override // com.sigmob.sdk.base.views.BaseWebView, android.webkit.WebView
    public void destroy() {
        SigmobLog.d("CreativeWebView destroy() callSigmobLog.d( \"CreativeWebView destroy() called\");ed");
        super.destroy();
    }

    public void setWebViewClickListener(a aVar) {
        this.c = aVar;
    }
}
