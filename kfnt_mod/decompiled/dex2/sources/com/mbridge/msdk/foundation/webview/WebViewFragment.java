package com.mbridge.msdk.foundation.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.foundation.fragment.BaseFragment;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.foundation.webview.BrowserView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class WebViewFragment extends BaseFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        BrowserView browserView = new BrowserView(getActivity());
        browserView.loadUrl(getActivity().getIntent().getExtras().getString(RemoteMessageConst.MessageBody.MSG));
        browserView.setListener(new BrowserView.a() { // from class: com.mbridge.msdk.foundation.webview.WebViewFragment.1
            @Override // com.mbridge.msdk.foundation.webview.BrowserView.a
            public final boolean a(WebView webView, String str) {
                return false;
            }

            @Override // com.mbridge.msdk.foundation.webview.BrowserView.a
            public final void a() {
                WebViewFragment.this.getActivity().finish();
            }

            @Override // com.mbridge.msdk.foundation.webview.BrowserView.a
            public final void a(WebView webView, String str, Bitmap bitmap) {
                if (v.a.a(str) && v.a.a(WebViewFragment.this.getActivity().getApplicationContext(), str, null)) {
                    WebViewFragment.this.getActivity().finish();
                }
            }
        });
        return browserView;
    }
}
