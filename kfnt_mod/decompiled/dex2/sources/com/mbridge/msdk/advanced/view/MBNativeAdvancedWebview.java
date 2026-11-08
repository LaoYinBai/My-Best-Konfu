package com.mbridge.msdk.advanced.view;

import android.content.Context;
import android.content.IntentFilter;
import com.mbridge.msdk.advanced.common.NetWorkStateReceiver;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBNativeAdvancedWebview extends WindVaneWebView {
    private static final String e = "MBNativeAdvancedWebview";
    private NetWorkStateReceiver f;

    public MBNativeAdvancedWebview(Context context) {
        super(context);
        setBackgroundColor(0);
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerNetWorkReceiver();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterNetWorkReceiver();
    }

    public void registerNetWorkReceiver() {
        try {
            if (this.f == null) {
                this.f = new NetWorkStateReceiver(this);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            getContext().registerReceiver(this.f, intentFilter);
        } catch (Throwable th) {
            s.a(e, th.getMessage());
        }
    }

    public void unregisterNetWorkReceiver() {
        try {
            if (this.f != null) {
                this.f.a();
                getContext().unregisterReceiver(this.f);
            }
        } catch (Throwable th) {
            s.a(e, th.getMessage());
        }
    }
}
