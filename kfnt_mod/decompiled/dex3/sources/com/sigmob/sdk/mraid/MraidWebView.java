package com.sigmob.sdk.mraid;

import android.content.Context;
import android.os.Build;
import android.view.View;
import com.sigmob.sdk.base.views.BaseWebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MraidWebView extends BaseWebView {
    private a a;
    private boolean b;

    public interface a {
        void a(boolean z);
    }

    public MraidWebView(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT <= 22) {
            this.b = getVisibility() == 0;
        }
    }

    private void setMraidViewable(boolean z) {
        if (this.b == z) {
            return;
        }
        this.b = z;
        if (this.a != null) {
            this.a.a(z);
        }
    }

    public boolean a() {
        return this.b;
    }

    @Override // com.sigmob.sdk.base.views.BaseWebView, android.webkit.WebView
    public void destroy() {
        super.destroy();
        this.a = null;
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            return;
        }
        setMraidViewable(false);
    }

    void setVisibilityChangedListener(a aVar) {
        this.a = aVar;
    }
}
