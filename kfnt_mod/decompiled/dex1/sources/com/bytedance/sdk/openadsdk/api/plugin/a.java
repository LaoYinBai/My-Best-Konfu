package com.bytedance.sdk.openadsdk.api.plugin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.common.CommonListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a implements TTAdManager {

    /* renamed from: a, reason: collision with root package name */
    static final a f5192a = new a();

    /* renamed from: b, reason: collision with root package name */
    private volatile TTAdManager f5193b;

    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.a$a, reason: collision with other inner class name */
    private interface InterfaceC0158a<T> {
        void a(T t);
    }

    private interface b<T> {
        void a(InterfaceC0158a<T> interfaceC0158a);
    }

    private interface c<T> extends InterfaceC0158a<T> {
        void a();
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getSDKVersion() {
        return "4.0.2.1";
    }

    a() {
    }

    public void a(TTAdManager tTAdManager) {
        this.f5193b = tTAdManager;
    }

    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.a$1, reason: invalid class name */
    class AnonymousClass1 implements b<TTAdNative> {

        /* renamed from: a, reason: collision with root package name */
        TTAdNative f5194a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f5195b;

        AnonymousClass1(Context context) {
            this.f5195b = context;
        }

        @Override // com.bytedance.sdk.openadsdk.api.plugin.a.b
        public void a(final InterfaceC0158a<TTAdNative> interfaceC0158a) {
            if (this.f5194a == null) {
                a.this.call(new InterfaceC0158a<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.1.1
                    @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                    public void a(TTAdManager tTAdManager) {
                        AnonymousClass1.this.f5194a = tTAdManager.createAdNative(AnonymousClass1.this.f5195b);
                        interfaceC0158a.a(AnonymousClass1.this.f5194a);
                    }
                });
            } else {
                interfaceC0158a.a(this.f5194a);
            }
        }
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public TTAdNative createAdNative(Context context) {
        return new d(new AnonymousClass1(context));
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void register(final Object obj) {
        final Object objA;
        if (obj instanceof TTPluginListener) {
            TTPluginListener tTPluginListener = (TTPluginListener) obj;
            objA = f.a(TTAppContextHolder.getContext()).a(tTPluginListener.packageName(), tTPluginListener.config());
        } else {
            objA = obj;
        }
        call(new InterfaceC0158a<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.2
            @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
            public void a(TTAdManager tTAdManager) {
                if (obj instanceof TTPluginListener) {
                    f.a(TTAppContextHolder.getContext()).a((TTPluginListener) obj);
                }
                tTAdManager.register(objA);
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void unregister(final Object obj) {
        call(new InterfaceC0158a<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.3
            @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
            public void a(TTAdManager tTAdManager) {
                tTAdManager.unregister(obj);
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public <T> T getExtra(final Class<T> cls, final Bundle bundle) {
        if (this.f5193b != null) {
            return (T) this.f5193b.getExtra(cls, bundle);
        }
        if (cls == Bundle.class && bundle != null && bundle.getInt("action", 0) == 1) {
            call(new c<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.4
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.c
                public void a() {
                    e.a(bundle);
                }

                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdManager tTAdManager) {
                    tTAdManager.getExtra(cls, bundle);
                }
            });
            return null;
        }
        call(new InterfaceC0158a<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.5
            @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
            public void a(TTAdManager tTAdManager) {
                tTAdManager.getExtra(cls, bundle);
            }
        });
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void requestPermissionIfNecessary(final Context context) {
        call(new InterfaceC0158a<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.6
            @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
            public void a(TTAdManager tTAdManager) {
                tTAdManager.requestPermissionIfNecessary(context);
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public boolean tryShowInstallDialogWhenExit(Activity activity, ExitInstallListener exitInstallListener) {
        return this.f5193b != null && this.f5193b.tryShowInstallDialogWhenExit(activity, exitInstallListener);
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getPluginVersion() {
        return this.f5193b != null ? this.f5193b.getPluginVersion() : "";
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getBiddingToken(AdSlot adSlot) {
        if (this.f5193b != null) {
            return this.f5193b.getBiddingToken(adSlot);
        }
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public String getBiddingToken(AdSlot adSlot, boolean z, int i) {
        if (this.f5193b != null) {
            return this.f5193b.getBiddingToken(adSlot, z, i);
        }
        return null;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public boolean onlyVerityPlayable(String str, int i, String str2, String str3, String str4) {
        if (this.f5193b != null) {
            return this.f5193b.onlyVerityPlayable(str, i, str2, str3, str4);
        }
        return false;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public void setThemeStatus(final int i) {
        call(new InterfaceC0158a<TTAdManager>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.7
            @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
            public void a(TTAdManager tTAdManager) {
                a.this.f5193b.setThemeStatus(i);
            }
        });
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdManager
    public int getThemeStatus() {
        if (this.f5193b != null) {
            return this.f5193b.getThemeStatus();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void call(final InterfaceC0158a<TTAdManager> interfaceC0158a) {
        if (this.f5193b != null) {
            try {
                interfaceC0158a.a(this.f5193b);
                return;
            } catch (Throwable th) {
                com.bytedance.sdk.openadsdk.api.b.d.c("PluginDefaultAdManager", "Unexpected manager call error: " + th.getMessage());
                return;
            }
        }
        if (g.f5280a != null) {
            g.f5280a.submit(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (a.this.f5193b != null) {
                            interfaceC0158a.a(a.this.f5193b);
                            return;
                        }
                        if (interfaceC0158a instanceof c) {
                            ((c) interfaceC0158a).a();
                        }
                        com.bytedance.sdk.openadsdk.api.b.d.c("PluginDefaultAdManager", "Not ready, no manager");
                    } catch (Throwable th2) {
                        com.bytedance.sdk.openadsdk.api.b.d.c("PluginDefaultAdManager", "Unexpected manager call error: " + th2.getMessage());
                    }
                }
            });
        } else {
            com.bytedance.sdk.openadsdk.api.b.d.c("PluginDefaultAdManager", "Not ready, no executor");
        }
    }

    private static final class d implements TTAdNative {

        /* renamed from: a, reason: collision with root package name */
        private b<TTAdNative> f5216a;

        public d(b<TTAdNative> bVar) {
            this.f5216a = bVar;
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadFeedAd(final AdSlot adSlot, final TTAdNative.FeedAdListener feedAdListener) {
            a(feedAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.1
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadFeedAd(adSlot, feedAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadStream(final AdSlot adSlot, final TTAdNative.FeedAdListener feedAdListener) {
            a(feedAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.7
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadStream(adSlot, feedAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadDrawFeedAd(final AdSlot adSlot, final TTAdNative.DrawFeedAdListener drawFeedAdListener) {
            a(drawFeedAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.8
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadDrawFeedAd(adSlot, drawFeedAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadNativeAd(final AdSlot adSlot, final TTAdNative.NativeAdListener nativeAdListener) {
            a(nativeAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.9
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadNativeAd(adSlot, nativeAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadBannerAd(final AdSlot adSlot, final TTAdNative.BannerAdListener bannerAdListener) {
            a(bannerAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.10
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadBannerAd(adSlot, bannerAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadInteractionAd(final AdSlot adSlot, final TTAdNative.InteractionAdListener interactionAdListener) {
            a(interactionAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.11
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadInteractionAd(adSlot, interactionAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadSplashAd(final AdSlot adSlot, final TTAdNative.SplashAdListener splashAdListener, final int i) {
            a(splashAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.12
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadSplashAd(adSlot, splashAdListener, i);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadSplashAd(final AdSlot adSlot, final TTAdNative.SplashAdListener splashAdListener) {
            a(splashAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.13
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadSplashAd(adSlot, splashAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadRewardVideoAd(final AdSlot adSlot, final TTAdNative.RewardVideoAdListener rewardVideoAdListener) {
            a(rewardVideoAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.14
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadRewardVideoAd(adSlot, rewardVideoAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadFullScreenVideoAd(final AdSlot adSlot, final TTAdNative.FullScreenVideoAdListener fullScreenVideoAdListener) {
            a(fullScreenVideoAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.2
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadFullScreenVideoAd(adSlot, fullScreenVideoAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadNativeExpressAd(final AdSlot adSlot, final TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            a(nativeExpressAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.3
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadNativeExpressAd(adSlot, nativeExpressAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadExpressDrawFeedAd(final AdSlot adSlot, final TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            a(nativeExpressAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.4
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadExpressDrawFeedAd(adSlot, nativeExpressAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadBannerExpressAd(final AdSlot adSlot, final TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            a(nativeExpressAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.5
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadBannerExpressAd(adSlot, nativeExpressAdListener);
                }
            });
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdNative
        public void loadInteractionExpressAd(final AdSlot adSlot, final TTAdNative.NativeExpressAdListener nativeExpressAdListener) {
            a(nativeExpressAdListener, new InterfaceC0158a<TTAdNative>() { // from class: com.bytedance.sdk.openadsdk.api.plugin.a.d.6
                @Override // com.bytedance.sdk.openadsdk.api.plugin.a.InterfaceC0158a
                public void a(TTAdNative tTAdNative) {
                    tTAdNative.loadInteractionExpressAd(adSlot, nativeExpressAdListener);
                }
            });
        }

        private final void a(CommonListener commonListener, InterfaceC0158a<TTAdNative> interfaceC0158a) {
            try {
                this.f5216a.a(interfaceC0158a);
            } catch (Throwable th) {
                if (commonListener != null) {
                    commonListener.onError(4202, "Load ad failed: " + th.getMessage());
                }
            }
        }
    }
}
