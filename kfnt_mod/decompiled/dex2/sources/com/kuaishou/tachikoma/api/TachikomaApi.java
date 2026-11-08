package com.kuaishou.tachikoma.api;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.kuaishou.tachikoma.api.app.IHostService;
import com.kuaishou.tachikoma.api.app.ITKLog;
import com.kuaishou.tachikoma.api.app.TkBundleInfo;
import com.kuaishou.tachikoma.api.exception.ITKExceptionHandler;
import com.kuaishou.tachikoma.api.exception.TKExceptionHandlerInnerImpl;
import com.kuaishou.tachikoma.api.model.TKCDNUrl;
import com.kwad.v8.V8;
import com.tachikoma.core.TKJSContextInitCallback;
import com.tachikoma.core.Tachikoma;
import com.tachikoma.core.api.IAnimatedImageInner;
import com.tachikoma.core.api.IHostEnvInner;
import com.tachikoma.core.api.ILogInner;
import com.tachikoma.core.api.IModuleInner;
import com.tachikoma.core.api.IWebImageHandlerInner;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.component.imageview.TKCDNUrlInner;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.manager.TKModuleManager;
import com.tachikoma.core.manager.TKProviderCollection;
import com.tachikoma.core.module.TKBuilder;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TachikomaApi {
    private static final TachikomaApi instance = new TachikomaApi();
    private Application mApplication;

    public static class TKInitParams {
        private final Map<String, Object> config;

        public static class Builder {
            private Map<String, Object> config;

            public TKInitParams build() {
                return new TKInitParams(this.config);
            }

            public Builder setConfig(Map<String, Object> map) {
                this.config = map;
                return this;
            }
        }

        private TKInitParams(Map<String, Object> map) {
            this.config = map;
        }
    }

    private TachikomaApi() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        makeNativeLibraryLoadedToTrue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<TKCDNUrl> convertToTKCDNUrl(List<TKCDNUrlInner> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (TKCDNUrlInner tKCDNUrlInner : list) {
                arrayList.add(new TKCDNUrl(tKCDNUrlInner.mCdn, tKCDNUrlInner.mUrl, tKCDNUrlInner.getIp(), tKCDNUrlInner.getUrlPattern(), tKCDNUrlInner.mIsFreeTrafficCdn, tKCDNUrlInner.mFeature, tKCDNUrlInner.mUrlType, tKCDNUrlInner.mPushCdn, tKCDNUrlInner.mHeaders));
            }
        }
        return arrayList;
    }

    public static TachikomaApi getInstance() {
        return instance;
    }

    private void makeNativeLibraryLoadedToTrue() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Field declaredField = V8.class.getDeclaredField("nativeLibraryLoaded");
            declaredField.setAccessible(true);
            declaredField.setBoolean(null, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public static void registerExtraLib(String... strArr) {
        TKProviderCollection.registerExtraLib(strArr);
    }

    public static void unregisterExtraLib(String... strArr) {
        TKProviderCollection.unregisterExtraLib(strArr);
    }

    public void asyncNewJSContext(final TKContextInitCallback tKContextInitCallback) {
        if (isInitSuccess()) {
            Tachikoma.getInstance().asyncNewTKJSContext(new TKJSContextInitCallback() { // from class: com.kuaishou.tachikoma.api.TachikomaApi.2
                public void onError(String str) {
                    tKContextInitCallback.onError(str);
                }

                public void onSuccess(TKJSContext tKJSContext) {
                    tKContextInitCallback.onSuccess(new TKContext(tKJSContext));
                }
            });
        } else {
            tKContextInitCallback.onError("tk has been not inited");
        }
    }

    public void clearBundleCache() {
    }

    public void clearHostService() {
        TKModuleManager.getInstance().clear();
    }

    public void destroy() {
        Tachikoma.getInstance().destroy();
    }

    @Deprecated
    public TkBundleInfo getTkBundleById(Context context, String str) {
        return null;
    }

    @Deprecated
    public void init(Application application) {
        init(application, (Map<String, Object>) null);
    }

    public void init(Application application, TKInitParams tKInitParams) {
        this.mApplication = application;
        Tachikoma.getInstance().init(application);
        if (tKInitParams.config != null) {
            Tachikoma.getInstance().setConfig(tKInitParams.config);
        }
    }

    @Deprecated
    public void init(Application application, Map<String, Object> map) {
        init(application, new TKInitParams.Builder().setConfig(map).build());
    }

    public boolean isInitSuccess() {
        return Tachikoma.getInstance().isInitSuccess();
    }

    @Nullable
    public TKContext newTKJSContext() {
        if (isInitSuccess()) {
            return new TKContext(Tachikoma.getInstance().newTKJSContext());
        }
        return null;
    }

    @Nullable
    public TKContext newTKJSContext(ViewGroup viewGroup) {
        if (isInitSuccess()) {
            return new TKContext(Tachikoma.getInstance().newTKJSContext(viewGroup));
        }
        return null;
    }

    public void setExceptionHandler(ITKExceptionHandler iTKExceptionHandler) {
        TKExceptionDispatcher.setExceptionHandler(new TKExceptionHandlerInnerImpl(iTKExceptionHandler));
    }

    public void setHostService(final IHostService iHostService) {
        TKModuleManager.getInstance().register(new IModuleInner() { // from class: com.kuaishou.tachikoma.api.TachikomaApi.3
            public void applyOptions(TKBuilder tKBuilder) {
                tKBuilder.setWebImageHandler(new IWebImageHandlerInner() { // from class: com.kuaishou.tachikoma.api.TachikomaApi.3.1
                    public void load(ImageView imageView, String str, Drawable drawable, int i) {
                        iHostService.obtainWebImageService().load(imageView, str, drawable, i);
                    }

                    public void load(ImageView imageView, List<TKCDNUrlInner> list, int i, int i2, int i3) {
                        iHostService.obtainWebImageService().load(imageView, TachikomaApi.this.convertToTKCDNUrl(list), i, i2, i3);
                    }

                    public void load(ImageView imageView, List<TKCDNUrlInner> list, int i, int i2, Drawable drawable, int i3) {
                        iHostService.obtainWebImageService().load(imageView, TachikomaApi.this.convertToTKCDNUrl(list), i, i2, drawable, i3);
                    }

                    public void load(String str, ImageView imageView, int i) {
                        iHostService.obtainWebImageService().load(str, imageView, i);
                    }
                });
                tKBuilder.setEnv(new IHostEnvInner() { // from class: com.kuaishou.tachikoma.api.TachikomaApi.3.2
                    public boolean isDark() {
                        return iHostService.obtainHostEnv().isDark();
                    }

                    public boolean isDebug() {
                        return iHostService.obtainHostEnv().isDebug();
                    }

                    public boolean isShowLog() {
                        return iHostService.obtainHostEnv().isShowLog();
                    }
                });
                tKBuilder.setAnimatedImageInner(new IAnimatedImageInner() { // from class: com.kuaishou.tachikoma.api.TachikomaApi.3.3
                    public ImageView getImageViewHost(Context context) {
                        return iHostService.obtainAnimatedImage().getImageViewHost(context);
                    }

                    public boolean getPlayNow(ImageView imageView) {
                        return iHostService.obtainAnimatedImage().getPlayNow(imageView);
                    }

                    public void setBorderColor(ImageView imageView, String str) {
                        iHostService.obtainAnimatedImage().setBorderColor(imageView, str);
                    }

                    public void setBorderRadius(ImageView imageView, int i) {
                        iHostService.obtainAnimatedImage().setBorderRadius(imageView, i);
                    }

                    public void setBorderWidth(ImageView imageView, double d) {
                        iHostService.obtainAnimatedImage().setBorderWidth(imageView, d);
                    }

                    public void setCDNUrls(ImageView imageView, List<TKCDNUrlInner> list, int i, int i2, String str) {
                        iHostService.obtainAnimatedImage().setCDNUrls(imageView, TachikomaApi.this.convertToTKCDNUrl(list), i, i2, str);
                    }

                    public void setSrc(ImageView imageView, String str, String str2) {
                        iHostService.obtainAnimatedImage().setSrc(imageView, str, str2);
                    }

                    public void setUri(ImageView imageView, String str, String str2, int i, int i2) {
                        iHostService.obtainAnimatedImage().setUri(imageView, str, str2, i, i2);
                    }

                    public void setUrlAndPlaceHolder(ImageView imageView, String str, String str2, String str3, int i, int i2) {
                        iHostService.obtainAnimatedImage().setUrlAndPlaceHolder(imageView, str, str2, str3, i, i2);
                    }

                    public void setUrls(ImageView imageView, List<TKCDNUrlInner> list, int i, int i2) {
                        iHostService.obtainAnimatedImage().setUrls(imageView, TachikomaApi.this.convertToTKCDNUrl(list), i, i2);
                    }

                    public void startGifAnimation(ImageView imageView) {
                        iHostService.obtainAnimatedImage().startGifAnimation(imageView);
                    }

                    public void stopGifAnimation(ImageView imageView) {
                        iHostService.obtainAnimatedImage().stopGifAnimation(imageView);
                    }
                });
            }
        });
    }

    public void setInitResult(boolean z) {
        Tachikoma.getInstance().setInitResult(z);
    }

    public void setLoggerDelegate(final ITKLog iTKLog) {
        Tachikoma.getInstance().setLoggerDelegate(new ILogInner() { // from class: com.kuaishou.tachikoma.api.TachikomaApi.1
            public void d(String str, String str2) {
                if (iTKLog != null) {
                    iTKLog.d(str, str2);
                }
            }

            public void d(String str, String str2, Throwable th) {
                if (iTKLog != null) {
                    iTKLog.d(str, str2, th);
                }
            }

            public void e(String str, String str2) {
                if (iTKLog != null) {
                    iTKLog.e(str, str2);
                }
            }

            public void e(String str, String str2, Throwable th) {
                if (iTKLog != null) {
                    iTKLog.e(str, str2, th);
                }
            }

            public String getStackTraceString(Throwable th) {
                return iTKLog != null ? iTKLog.getStackTraceString(th) : "";
            }

            public void i(String str, String str2) {
                if (iTKLog != null) {
                    iTKLog.i(str, str2);
                }
            }

            public void i(String str, String str2, Throwable th) {
                if (iTKLog != null) {
                    iTKLog.i(str, str2, th);
                }
            }

            public void v(String str, String str2) {
                if (iTKLog != null) {
                    iTKLog.v(str, str2);
                }
            }

            public void v(String str, String str2, Throwable th) {
                if (iTKLog != null) {
                    iTKLog.v(str, str2, th);
                }
            }

            public void w(String str, String str2) {
                if (iTKLog != null) {
                    iTKLog.w(str, str2);
                }
            }

            public void w(String str, String str2, Throwable th) {
                if (iTKLog != null) {
                    iTKLog.w(str, str2, th);
                }
            }

            public void w(String str, Throwable th) {
                if (iTKLog != null) {
                    iTKLog.w(str, th);
                }
            }
        });
    }
}
