package com.tachikoma.core.component.network;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.api.IRequestDelegateInner;
import com.tachikoma.core.base.NativeModule;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.component.network.delegate.RequestConnectionDelegate;
import com.tachikoma.core.component.network.delegate.TKBaseResponseInner;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.utility.V8Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Network implements NativeModule {
    public static String METHOD_GET = "GET";
    public static String METHOD_POST = "POST";
    private static HashMap<JSContext, IRequestDelegateInner> mRequestMap = new HashMap<>();
    public Map<String, String> bodyParameters;
    public Map<String, String> headers;
    public String host;
    private V8Function mGetCallback;
    private final JSContext mJSContext;
    private V8Function mPostCallback;
    private IRequestDelegateInner mRequestDelegate;
    private final TKJSContext mTKJSContext;
    public Map<String, String> parameters;
    public String path;
    public String scheme;
    public int timeoutInterval;
    public Map<String, Object> userInfo;

    public Network(Context context, @Nullable List<Object> list) {
        this.mRequestDelegate = new RequestConnectionDelegate();
        this.mTKJSContext = V8Proxy.getTKContext(list);
        this.mJSContext = this.mTKJSContext.context();
        IRequestDelegateInner iRequestDelegateInner = mRequestMap.get(this.mJSContext);
        if (iRequestDelegateInner != null) {
            this.mRequestDelegate = iRequestDelegateInner;
        }
    }

    public static void removeRequestDelegate(JSContext jSContext) {
        if (jSContext != null) {
            mRequestMap.remove(jSContext);
        }
    }

    public static void setRequestDelegate(TKJSContext tKJSContext, IRequestDelegateInner iRequestDelegateInner) {
        if (tKJSContext == null || iRequestDelegateInner == null) {
            return;
        }
        mRequestMap.put(tKJSContext.context(), iRequestDelegateInner);
    }

    @Override // com.tachikoma.core.base.NativeModule
    public void destroy() {
        if (this.mPostCallback != null) {
            V8Proxy.release(this.mPostCallback);
        }
        if (this.mGetCallback != null) {
            V8Proxy.release(this.mGetCallback);
        }
    }

    public void get(V8Function v8Function) {
        if (this.mGetCallback != null) {
            V8Proxy.release(this.mGetCallback);
        }
        this.mGetCallback = v8Function.twin();
        this.mRequestDelegate.get(new ResponseCallBackInner() { // from class: com.tachikoma.core.component.network.Network.2
            @Override // com.tachikoma.core.component.network.ResponseCallBackInner
            public void onComplete(TKBaseResponseInner tKBaseResponseInner) {
                try {
                    if (tKBaseResponseInner != null) {
                        try {
                            if (!Network.this.mGetCallback.isReleased()) {
                                V8Array v8Array = new V8Array(Network.this.mGetCallback.getRuntime());
                                String jsonString = tKBaseResponseInner.toJsonString();
                                if (TextUtils.isEmpty(jsonString) || tKBaseResponseInner.equals("{}")) {
                                    jsonString = "{}";
                                }
                                v8Array.push(jsonString);
                                Network.this.mGetCallback.call((V8Object) null, v8Array);
                                V8Proxy.release(v8Array);
                            }
                        } catch (Throwable th) {
                            TKExceptionDispatcher.reportException(Network.this.mTKJSContext, th);
                        }
                    }
                } finally {
                    V8Proxy.release(Network.this.mGetCallback);
                }
            }

            @Override // com.tachikoma.core.component.network.ResponseCallBackInner
            public void onError(TKErrorInner tKErrorInner) {
                try {
                    if (tKErrorInner != null) {
                        try {
                            if (!Network.this.mGetCallback.isReleased()) {
                                V8Array v8Array = new V8Array(Network.this.mGetCallback.getRuntime());
                                v8Array.push(tKErrorInner.toString());
                                Network.this.mGetCallback.call((V8Object) null, v8Array);
                                V8Proxy.release(v8Array);
                            }
                        } catch (Throwable th) {
                            TKExceptionDispatcher.reportException(Network.this.mTKJSContext, th);
                        }
                    }
                } finally {
                    V8Proxy.release(Network.this.mGetCallback);
                }
            }
        });
    }

    @Override // com.tachikoma.core.base.NativeModule
    public String getName() {
        return "TKRequest";
    }

    public void post(V8Function v8Function) {
        if (this.mPostCallback != null) {
            V8Proxy.release(this.mPostCallback);
        }
        this.mPostCallback = v8Function.twin();
        this.mRequestDelegate.post(new ResponseCallBackInner() { // from class: com.tachikoma.core.component.network.Network.1
            @Override // com.tachikoma.core.component.network.ResponseCallBackInner
            public void onComplete(TKBaseResponseInner tKBaseResponseInner) {
                if (tKBaseResponseInner != null) {
                    try {
                        try {
                            if (!Network.this.mPostCallback.isReleased()) {
                                V8Array v8Array = new V8Array(Network.this.mPostCallback.getRuntime());
                                String jsonString = tKBaseResponseInner.toJsonString();
                                if (TextUtils.isEmpty(jsonString)) {
                                    jsonString = "{}";
                                }
                                v8Array.push(jsonString);
                                Network.this.mPostCallback.call((V8Object) null, v8Array);
                                V8Proxy.release(v8Array);
                            }
                        } catch (Throwable th) {
                            TKExceptionDispatcher.reportException(Network.this.mTKJSContext, th);
                        }
                    } finally {
                        V8Proxy.release(Network.this.mPostCallback);
                    }
                }
            }

            @Override // com.tachikoma.core.component.network.ResponseCallBackInner
            public void onError(TKErrorInner tKErrorInner) {
                try {
                    if (tKErrorInner != null) {
                        try {
                            if (!Network.this.mPostCallback.isReleased()) {
                                V8Array v8Array = new V8Array(Network.this.mPostCallback.getRuntime());
                                v8Array.push(tKErrorInner.toString());
                                Network.this.mPostCallback.call((V8Object) null, v8Array);
                                V8Proxy.release(v8Array);
                            }
                        } catch (Throwable th) {
                            TKExceptionDispatcher.reportException(Network.this.mTKJSContext, th);
                        }
                    }
                } finally {
                    V8Proxy.release(Network.this.mPostCallback);
                }
            }
        });
    }

    public void setBody(Map map) {
        this.mRequestDelegate.setBody(map);
    }

    public void setHeader(Map map) {
        this.mRequestDelegate.setHeader(map);
    }

    public void setHost(String str) {
        this.mRequestDelegate.setHost(str);
    }

    public void setInterval(int i) {
        this.mRequestDelegate.setInterval(i);
    }

    public void setParams(Map<String, String> map) {
        this.mRequestDelegate.setParams(map);
    }

    public void setPath(String str) {
        this.mRequestDelegate.setPath(str);
    }

    public void setScheme(String str) {
        this.mRequestDelegate.setScheme(str);
    }

    public void setUserInfo(Map<String, Object> map) {
        this.mRequestDelegate.setUserInfo(map);
    }
}
