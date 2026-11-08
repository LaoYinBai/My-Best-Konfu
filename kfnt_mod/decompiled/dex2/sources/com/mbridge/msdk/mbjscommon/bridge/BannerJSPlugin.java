package com.mbridge.msdk.mbjscommon.bridge;

import android.content.Context;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BannerJSPlugin extends AbsMbridgeDownload {
    private final String a = "BannerJSBridge";
    private c b;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.mbridge.msdk.mbjscommon.windvane.j
    public void initialize(Context context, WindVaneWebView windVaneWebView) {
        super.initialize(context, windVaneWebView);
        try {
            if (context instanceof c) {
                this.b = (c) context;
            } else if (windVaneWebView.getObject() != null && (windVaneWebView.getObject() instanceof c)) {
                this.b = (c) windVaneWebView.getObject();
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "initialize", th);
        }
    }

    public void onJSBridgeConnect(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "onJSBridgeConnect");
            if (this.b != null) {
                this.b.f(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "onJSBridgeConnect", th);
        }
    }

    public void readyStatus(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "readyStatus");
            if (this.b != null) {
                this.b.c(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "readyStatus", th);
        }
    }

    public void init(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "init");
            if (this.b != null) {
                this.b.a(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "init", th);
        }
    }

    public void click(Object obj, String str) {
        try {
            String strAddDownloaderListener = addDownloaderListener(obj, str);
            s.d("BannerJSBridge", CampaignEx.JSON_NATIVE_VIDEO_CLICK);
            if (this.b != null) {
                this.b.b(obj, strAddDownloaderListener);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", CampaignEx.JSON_NATIVE_VIDEO_CLICK, th);
        }
    }

    public void toggleCloseBtn(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "toggleCloseBtn");
            if (this.b != null) {
                this.b.d(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "toggleCloseBtn", th);
        }
    }

    public void triggerCloseBtn(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "triggerCloseBtn");
            if (this.b != null) {
                this.b.e(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "triggerCloseBtn", th);
        }
    }

    public void sendImpressions(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "sendImpressions");
            if (this.b != null) {
                this.b.i(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "sendImpressions", th);
        }
    }

    public void reportUrls(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "reportUrls");
            if (this.b != null) {
                this.b.k(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "reportUrls", th);
        }
    }

    public void increaseOfferFrequence(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "increaseOfferFrequence");
            if (this.b != null) {
                this.b.l(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "increaseOfferFrequence", th);
        }
    }

    public void resetCountdown(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "resetCountdown");
            if (this.b != null) {
                this.b.h(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "resetCountdown", th);
        }
    }

    public void handlerH5Exception(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "handlerH5Exception");
            if (this.b != null) {
                this.b.m(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "handlerH5Exception", th);
        }
    }

    public void install(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "install");
            if (this.b != null) {
                this.b.g(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "install", th);
        }
    }

    public void getNetstat(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "getNetstat");
            if (this.b != null) {
                this.b.o(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "getNetstat", th);
        }
    }

    public void openURL(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "openURL");
            if (this.b != null) {
                this.b.n(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "openURL", th);
        }
    }

    public void cai(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "cai");
            if (this.b != null) {
                this.b.p(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "cai", th);
        }
    }

    public void gial(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "gial");
            if (this.b != null) {
                this.b.q(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "gial", th);
        }
    }

    public void getFileInfo(Object obj, String str) {
        try {
            s.d("BannerJSBridge", "getFileInfo");
            if (this.b != null) {
                this.b.j(obj, str);
            }
        } catch (Throwable th) {
            s.b("BannerJSBridge", "getFileInfo", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.AbsMbridgeDownload
    public void sendNoticeAndCallBackClick(Object obj, String str) {
        install(obj, str);
    }
}
