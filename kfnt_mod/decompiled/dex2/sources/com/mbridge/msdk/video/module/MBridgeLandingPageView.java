package com.mbridge.msdk.video.module;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.URLUtil;
import android.webkit.WebView;
import com.mbridge.msdk.click.c;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.video.js.factory.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeLandingPageView extends MBridgeH5EndCardView {
    public MBridgeLandingPageView(Context context) {
        super(context);
    }

    public MBridgeLandingPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView, com.mbridge.msdk.video.module.MBridgeBaseView
    public void init(Context context) {
        super.init(context);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    protected final String a() {
        if (this.b != null) {
            return c.a(this.b.getClickURL(), "-999", "-999");
        }
        return null;
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    public void preLoadData(b bVar) {
        if (this.f) {
            this.l.setFilter(new a());
        }
        super.preLoadData(bVar);
        setVisibility(0);
        setCloseVisible(0);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeH5EndCardView
    public void webviewshow() {
        try {
            s.a(MBridgeBaseView.TAG, "webviewshow");
            h.a().a((WebView) this.l, "webviewshow", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final class a implements com.mbridge.msdk.mbjscommon.base.a {
        private a() {
        }

        @Override // com.mbridge.msdk.mbjscommon.base.a
        public final boolean a(String str) {
            if (TextUtils.isEmpty(str) || URLUtil.isNetworkUrl(str)) {
                return false;
            }
            v.b(com.mbridge.msdk.foundation.controller.a.f().j(), str, null);
            return true;
        }
    }
}
