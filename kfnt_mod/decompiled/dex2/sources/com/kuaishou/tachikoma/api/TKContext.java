package com.kuaishou.tachikoma.api;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import com.kuaishou.tachikoma.api.app.IRenderListener;
import com.kuaishou.tachikoma.api.app.IRequestCallback;
import com.kuaishou.tachikoma.api.app.IRequestDelegate;
import com.kuaishou.tachikoma.api.app.TKBaseResponse;
import com.kuaishou.tachikoma.api.app.TKError;
import com.kuaishou.tachikoma.api.app.TkBundleInfo;
import com.kuaishou.tachikoma.api.exception.BundleContextMap;
import com.kuaishou.tachikoma.api.model.TKBundle;
import com.kuaishou.tachikoma.api.page.IBaseBridge;
import com.kuaishou.tachikoma.api.page.INewBaseBridge;
import com.kuaishou.tachikoma.api.page.IRouter;
import com.kuaishou.tachikoma.api.page.IWebCardBridge;
import com.kwad.v8.V8;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.tachikoma.core.api.IBaseBridgeInner;
import com.tachikoma.core.api.IRenderListenerInner;
import com.tachikoma.core.api.IRequestDelegateInner;
import com.tachikoma.core.api.IRouterInner;
import com.tachikoma.core.api.IWebCardBridgeInner;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.component.network.Network;
import com.tachikoma.core.component.network.ResponseCallBackInner;
import com.tachikoma.core.component.network.TKErrorInner;
import com.tachikoma.core.component.network.delegate.TKBaseResponseInner;
import com.tachikoma.core.manager.RouterManager;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKContext {
    private static final String TAG = "TKContext";
    private final TKJSContext mContext;
    private LruCache<String, String> mScriptCache = null;
    private String mRootDir = "";

    public TKContext(TKJSContext tKJSContext) {
        this.mContext = tKJSContext;
    }

    private String getBundleScript(Context context, String str, boolean z) {
        if (z) {
            maybeCreateCache();
        }
        String str2 = (TextUtils.isEmpty(str) || this.mScriptCache == null) ? null : (String) this.mScriptCache.get(str);
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    private void maybeCreateCache() {
        if (this.mScriptCache == null) {
            synchronized (this) {
                if (this.mScriptCache == null) {
                    this.mScriptCache = new LruCache<>(3);
                }
            }
        }
    }

    public TKJSBridge buildJsBridge() {
        return new TKJSBridge(this.mContext);
    }

    @Nullable
    public TKViewContainer createView(Context context, String str, Object... objArr) {
        JSContext.V8AssociateReference v8AssociateReferenceCreateView = this.mContext.createView(context, str, objArr);
        if (v8AssociateReferenceCreateView != null) {
            return new TKViewContainer(v8AssociateReferenceCreateView);
        }
        Log.e(TAG, "Can not createView by script");
        return null;
    }

    @Deprecated
    public TKViewContainer createView(String str, Object... objArr) {
        return createView(null, str, objArr);
    }

    public Object evaluateScript(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str3)) {
            this.mRootDir = str3;
            this.mContext.setRootDir(str3);
        }
        if (this.mContext.context() == null) {
            return null;
        }
        return this.mContext.context().evaluateScript(str, str2);
    }

    public void evaluateScript(String str, String str2, String str3, final IRenderListener iRenderListener) {
        if (!TextUtils.isEmpty(str3)) {
            this.mRootDir = str3;
            this.mContext.setRootDir(str3);
        }
        if (this.mContext.context() != null) {
            this.mContext.context().evaluateScript(str, str2, new IRenderListenerInner() { // from class: com.kuaishou.tachikoma.api.TKContext.6
                public void failed(Throwable th) {
                    if (iRenderListener != null) {
                        iRenderListener.failed(th);
                    }
                }

                public void success() {
                    if (iRenderListener != null) {
                        iRenderListener.success();
                    }
                }
            });
        } else if (iRenderListener != null) {
            iRenderListener.failed(new Throwable("mContext.context() is null"));
        }
    }

    public Object evaluateScriptById(Context context, String str, boolean z) {
        String bundleScript = getBundleScript(context, str, z);
        if (this.mContext.context() == null) {
            return null;
        }
        return this.mContext.context().evaluateScriptById(bundleScript);
    }

    public void evaluateScriptById(Context context, String str, boolean z, final IRenderListener iRenderListener) {
        if (this.mContext.context() != null) {
            this.mContext.context().evaluateScriptById(context, str, z, new IRenderListenerInner() { // from class: com.kuaishou.tachikoma.api.TKContext.7
                public void failed(Throwable th) {
                    if (iRenderListener != null) {
                        iRenderListener.failed(th);
                    }
                }

                public void success() {
                    if (iRenderListener != null) {
                        iRenderListener.success();
                    }
                }
            });
            return;
        }
        if (iRenderListener != null) {
            iRenderListener.failed(new RuntimeException("mContext.context() is null"));
        }
        Log.e(TAG, "mContext.context() is null and listener is null");
    }

    public TKJSContext getContext() {
        return this.mContext;
    }

    @Nullable
    public V8 getV8Context() {
        if (this.mContext.context() == null) {
            return null;
        }
        return this.mContext.context().getContextRef();
    }

    public String getmRootDir() {
        return this.mRootDir;
    }

    public V8Object makeObject() {
        return this.mContext.context().makeObject();
    }

    public V8Array makeV8Array() {
        return this.mContext.context().makeV8Array();
    }

    public void onCreate() {
        this.mContext.onCreate();
    }

    public void onDestroy() {
        if (this.mScriptCache != null) {
            this.mScriptCache.evictAll();
            this.mScriptCache = null;
        }
        RouterManager.getInstance().unRegisterRouter(this.mContext.context());
        BundleContextMap.remove(this.mContext.hashCode());
        this.mContext.onDestroy();
    }

    public void registerOKClient(final IRequestDelegate iRequestDelegate) {
        if (iRequestDelegate == null) {
            return;
        }
        Network.setRequestDelegate(this.mContext, new IRequestDelegateInner() { // from class: com.kuaishou.tachikoma.api.TKContext.1
            public void get(final ResponseCallBackInner responseCallBackInner) {
                iRequestDelegate.get(new IRequestCallback() { // from class: com.kuaishou.tachikoma.api.TKContext.1.2
                    @Override // com.kuaishou.tachikoma.api.app.IRequestCallback
                    public void onComplete(TKBaseResponse tKBaseResponse) {
                        TKBaseResponseInner tKBaseResponseInner = new TKBaseResponseInner();
                        if (tKBaseResponse != null) {
                            tKBaseResponseInner.allHeaderFields = tKBaseResponse.allHeaderFields;
                            tKBaseResponseInner.body = tKBaseResponse.body;
                            tKBaseResponseInner.statusCode = tKBaseResponse.statusCode;
                        }
                        responseCallBackInner.onComplete(tKBaseResponseInner);
                    }

                    @Override // com.kuaishou.tachikoma.api.app.IRequestCallback
                    public void onError(TKError tKError) {
                        TKErrorInner tKErrorInner = new TKErrorInner();
                        if (tKError != null) {
                            tKErrorInner.code = tKError.code;
                            tKErrorInner.msg = tKError.msg;
                            tKErrorInner.name = tKError.name;
                        }
                        responseCallBackInner.onError(tKErrorInner);
                    }
                });
            }

            public void post(final ResponseCallBackInner responseCallBackInner) {
                iRequestDelegate.post(new IRequestCallback() { // from class: com.kuaishou.tachikoma.api.TKContext.1.1
                    @Override // com.kuaishou.tachikoma.api.app.IRequestCallback
                    public void onComplete(TKBaseResponse tKBaseResponse) {
                        TKBaseResponseInner tKBaseResponseInner = new TKBaseResponseInner();
                        if (tKBaseResponse != null) {
                            tKBaseResponseInner.allHeaderFields = tKBaseResponse.allHeaderFields;
                            tKBaseResponseInner.body = tKBaseResponse.body;
                            tKBaseResponseInner.statusCode = tKBaseResponse.statusCode;
                        }
                        responseCallBackInner.onComplete(tKBaseResponseInner);
                    }

                    @Override // com.kuaishou.tachikoma.api.app.IRequestCallback
                    public void onError(TKError tKError) {
                        TKErrorInner tKErrorInner = new TKErrorInner();
                        if (tKError != null) {
                            tKErrorInner.code = tKError.code;
                            tKErrorInner.msg = tKError.msg;
                            tKErrorInner.name = tKError.name;
                        }
                        responseCallBackInner.onError(tKErrorInner);
                    }
                });
            }

            public void setBody(Map<String, String> map) {
                iRequestDelegate.setBody(map);
            }

            public void setHeader(Map<String, String> map) {
                iRequestDelegate.setHeader(map);
            }

            public void setHost(String str) {
                iRequestDelegate.setHost(str);
            }

            public void setInterval(int i) {
                iRequestDelegate.setInterval(i);
            }

            public void setParams(Map<String, String> map) {
                iRequestDelegate.setParams(map);
            }

            public void setPath(String str) {
                iRequestDelegate.setPath(str);
            }

            public void setScheme(String str) {
                iRequestDelegate.setScheme(str);
            }

            public void setUserInfo(Map<String, Object> map) {
                iRequestDelegate.setUserInfo(map);
            }
        });
    }

    public void registerRouter(final IRouter iRouter) {
        RouterManager.getInstance().registerRouter(this.mContext.context(), new IRouterInner() { // from class: com.kuaishou.tachikoma.api.TKContext.2
            public void navigateTo(Uri uri) {
                iRouter.navigateTo(uri);
            }
        });
    }

    @Deprecated
    public void setBaseBridge(@NonNull final IBaseBridge iBaseBridge) {
        if (iBaseBridge == null) {
            return;
        }
        this.mContext.setBaseBridge(new IBaseBridgeInner() { // from class: com.kuaishou.tachikoma.api.TKContext.4
            public Object invoke(String str, @Nullable String str2, @Nullable V8Function v8Function) {
                return iBaseBridge.invoke(str, str2, v8Function);
            }

            public Object invokeM(String str, String str2, @Nullable String str3, @Nullable V8Function v8Function) {
                return iBaseBridge.invoke(str, str2, str3, v8Function);
            }
        });
    }

    public void setBundleInfo(TkBundleInfo tkBundleInfo, String str) {
        TKBundle tKBundle = new TKBundle(tkBundleInfo.mBundleId, tkBundleInfo.mVersion, String.valueOf(tkBundleInfo.mVersionCode), str, tkBundleInfo.mTaskId);
        if (this.mContext != null) {
            BundleContextMap.setData(this.mContext.hashCode(), tKBundle);
        }
    }

    public void setNewBaseBridge(@NonNull final INewBaseBridge iNewBaseBridge) {
        if (iNewBaseBridge == null) {
            return;
        }
        this.mContext.setBaseBridge(new IBaseBridgeInner() { // from class: com.kuaishou.tachikoma.api.TKContext.5
            public Object invoke(String str, @Nullable String str2, @Nullable V8Function v8Function) {
                return iNewBaseBridge.invoke(str, str2, new FunctionCallImpl(v8Function));
            }

            public Object invokeM(String str, String str2, @Nullable String str3, @Nullable V8Function v8Function) {
                return iNewBaseBridge.invoke(str, str2, str3, new FunctionCallImpl(v8Function));
            }
        });
    }

    public void setWebCardBridge(@NonNull final IWebCardBridge iWebCardBridge) {
        this.mContext.setWebCardBridge(new IWebCardBridgeInner() { // from class: com.kuaishou.tachikoma.api.TKContext.3
            public void close() {
                iWebCardBridge.close();
            }

            public void convert() {
                iWebCardBridge.convert();
            }

            public String getData() {
                return iWebCardBridge.getData();
            }

            public void handleAdUrl(String str) {
                iWebCardBridge.handleAdUrl(str);
            }

            public void hide() {
                iWebCardBridge.hide();
            }

            public void log(String str) {
                iWebCardBridge.log(str);
            }

            public void nonActionbarClick(String str) {
                iWebCardBridge.nonActionbarClick(str);
            }

            public void pageStatus(String str) {
                iWebCardBridge.pageStatus(str);
            }

            public void registerProgressListener(V8Function v8Function) {
                iWebCardBridge.registerProgressListener(v8Function);
            }

            public void trace(String str) {
                iWebCardBridge.trace(str);
            }
        });
    }
}
