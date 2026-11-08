package com.tachikoma.core.bridge;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import com.kwad.v8.JavaCallback;
import com.kwad.v8.JavaVoidCallback;
import com.kwad.v8.V8;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.kwad.v8.utils.V8ObjectUtils;
import com.kwad.yoga.YogaAlign;
import com.kwad.yoga.YogaJustify;
import com.kwad.yoga.YogaNode;
import com.tachikoma.core.IV8Function;
import com.tachikoma.core.V8FunctionImpl;
import com.tachikoma.core.api.IBaseBridgeInner;
import com.tachikoma.core.api.IExceptionHandlerInner;
import com.tachikoma.core.api.IHostEnvInner;
import com.tachikoma.core.api.IWebCardBridgeInner;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.common.ILifeCycle;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.manager.TKExportManager;
import com.tachikoma.core.manager.TKModuleManager;
import com.tachikoma.core.manager.W3CMoudleManager;
import com.tachikoma.core.module.handler.IBusinessSchoolBridge;
import com.tachikoma.core.module.handler.TKLifeCycle;
import com.tachikoma.core.utility.TKConverter;
import com.tachikoma.core.utility.TKEnv;
import com.tachikoma.core.utility.V8Proxy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKJSContext implements ILifeCycle {
    private static Integer fileIndex = 0;
    private Map<String, V8ClassRegisterSlot> classMethodRegisterMap;
    private float density;
    private List<IComponentRegister> mComponentRegisters;
    Context mContext;
    private Map<String, V8Function> mCreateViewFunctionMap;
    private IExceptionHandlerInner mExceptionHandler;
    private TKExportManager mExportManager;
    private final HashMap<Class, Object> mHandlerMap;
    private final LruCache<String, Method> mInvokeMethodCache;
    JSContext mJsContext;
    private ILifeCycle mLifeCycleCallback;
    private V8Function mLinkClass;
    private List<V8Value> mPendingReleaseV8List;
    private String mRootDir;

    @Nullable
    private ViewGroup mRootView;

    @Nullable
    private JSContext.V8AssociateReference mRootViewReference;
    Context mViewCreateContext;

    private static class V8ClassRegisterSlot {
        final V8Object classObject;
        final V8Object handleObject;

        private V8ClassRegisterSlot(JSContext jSContext) {
            this.classObject = jSContext.makeObject();
            this.handleObject = jSContext.makeObject();
        }
    }

    public TKJSContext(@NonNull Context context, @Nullable ViewGroup viewGroup, @NonNull TKExportManager tKExportManager) {
        this.mHandlerMap = new HashMap<>();
        this.mCreateViewFunctionMap = new HashMap();
        this.mRootDir = "";
        this.density = -1.0f;
        this.mInvokeMethodCache = new LruCache<>(100);
        this.classMethodRegisterMap = new HashMap();
        this.mContext = context;
        this.mRootView = viewGroup;
        this.mExportManager = tKExportManager;
    }

    public TKJSContext(Context context, @NonNull TKExportManager tKExportManager) {
        this(context, null, tKExportManager);
    }

    private String createFileName() {
        Integer num = fileIndex;
        fileIndex = Integer.valueOf(fileIndex.intValue() + 1);
        return "NativeJS" + fileIndex.toString();
    }

    private void dfsDestroyChildren(@Nullable View view) {
        if (view == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            int i = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i >= viewGroup.getChildCount()) {
                    break;
                }
                dfsDestroyChildren(viewGroup.getChildAt(i));
                i++;
            }
        }
        TKBase tKBaseFromView = TKBase.getTKBaseFromView(view);
        if (tKBaseFromView != null) {
            tKBaseFromView.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureLinkClassFunction() {
        if (this.mLinkClass == null) {
            this.mLinkClass = this.mJsContext.getContextRef().executeObjectScript("(function(a,b){let sourceProp=Object.getPrototypeOf(this);Object.setPrototypeOf(this,Object.assign(sourceProp,a));return new Proxy(this,b)})");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public Context getViewCreateContext() {
        return this.mViewCreateContext != null ? this.mViewCreateContext : this.mRootView == null ? this.mContext : this.mRootView.getContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) throws NoSuchMethodException, SecurityException {
        try {
            String str2 = obj.getClass() + "_" + str;
            Method method = (Method) this.mInvokeMethodCache.get(str2);
            if (method == null) {
                method = obj.getClass().getMethod(str, clsArr);
                this.mInvokeMethodCache.put(str2, method);
            }
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            TKExceptionDispatcher.reportException(this, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logError(String str, boolean z) {
        if (z && TKEnv.isDebug(this)) {
            TKExceptionDispatcher.reportException(this, new IllegalStateException(str));
        }
        Logger.logE(str, null);
    }

    private void maybeInitComponentRegister() {
        if (this.mComponentRegisters == null) {
            this.mComponentRegisters = new ArrayList();
            this.mComponentRegisters.add(W3CMoudleManager.getInstance());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeInitDensity() {
        try {
            if (this.density == -1.0f) {
                this.density = this.mContext.getResources().getDisplayMetrics().density;
            }
        } catch (Exception e) {
            TKExceptionDispatcher.reportException(this, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V8ClassRegisterSlot registerClassObject(JSContext jSContext, String str) {
        V8ClassRegisterSlot v8ClassRegisterSlot = this.classMethodRegisterMap.get(str);
        if (v8ClassRegisterSlot != null) {
            return v8ClassRegisterSlot;
        }
        V8ClassRegisterSlot v8ClassRegisterSlot2 = new V8ClassRegisterSlot(jSContext);
        this.classMethodRegisterMap.put(str, v8ClassRegisterSlot2);
        String strReplaceAll = str.replaceAll("\\.", "_");
        HashMap<String, Class[]> classMethods = this.mExportManager.getClassMethods(strReplaceAll);
        if (classMethods != null) {
            for (final Map.Entry<String, Class[]> entry : classMethods.entrySet()) {
                final String key = entry.getKey();
                v8ClassRegisterSlot2.classObject.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.2
                    public Object invoke(V8Object v8Object, V8Array v8Array) {
                        JSContext.V8AssociateReference associateReference = TKJSContext.this.mJsContext.getAssociateReference(v8Object);
                        Object obj = associateReference.nativeObject;
                        associateReference.close();
                        Object[] objArrTransferV8Function = TKJSContext.this.transferV8Function((Class[]) entry.getValue(), v8Array);
                        int i = 0;
                        try {
                            return TKJSContext.this.invokeMethod(obj, key, (Class[]) entry.getValue(), objArrTransferV8Function);
                        } finally {
                            int length = objArrTransferV8Function.length;
                            while (i < length) {
                                Object obj2 = objArrTransferV8Function[i];
                                if (obj2 instanceof V8Value) {
                                    V8Proxy.release((V8Value) obj2);
                                }
                                i++;
                            }
                        }
                    }
                }, this.mExportManager.getJsFunctionName(key));
            }
        }
        final ArrayList<String> properties = this.mExportManager.getProperties(strReplaceAll);
        v8ClassRegisterSlot2.handleObject.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.3
            public Object invoke(V8Object v8Object, V8Array v8Array) {
                ArrayList arrayList = new ArrayList();
                try {
                    try {
                        V8Object v8Object2 = (V8Object) v8Array.get(0);
                        arrayList.add(v8Object2);
                        String string = v8Array.getString(1);
                        JSContext.V8AssociateReference associateReference = TKJSContext.this.mJsContext.getAssociateReference(v8Object2);
                        Object obj = associateReference.nativeObject;
                        arrayList.add(associateReference);
                        Object obj2 = v8Array.get(2);
                        arrayList.add(obj2);
                        if (properties != null && properties.contains(string) && obj != null) {
                            HashMap<String, Object> map = new HashMap<>();
                            map.put(string, V8ObjectUtils.getValue(obj2));
                            TKJSContext.this.mExportManager.applyProperties(obj.getClass().getName(), obj, map);
                        } else if (obj2 instanceof V8Value) {
                            v8Object2.add(string, (V8Value) obj2);
                        } else if (obj2 instanceof Integer) {
                            v8Object2.add(string, ((Integer) obj2).intValue());
                        } else if (obj2 instanceof Boolean) {
                            v8Object2.add(string, ((Boolean) obj2).booleanValue());
                        } else if (obj2 instanceof String) {
                            v8Object2.add(string, (String) obj2);
                        } else if (obj2 instanceof Number) {
                            v8Object2.add(string, ((Number) obj2).doubleValue());
                        } else {
                            if (obj2 != null) {
                                throw new RuntimeException("Can not add prop: " + string + " for value: " + obj2);
                            }
                            v8Object2.addNull(string);
                        }
                        for (Object obj3 : arrayList) {
                            if (obj3 instanceof V8Value) {
                                ((V8Value) obj3).close();
                            } else if (obj3 instanceof JSContext.V8AssociateReference) {
                                ((JSContext.V8AssociateReference) obj3).close();
                            }
                        }
                    } catch (Throwable th) {
                        TKExceptionDispatcher.reportException(TKJSContext.this, th);
                        for (Object obj4 : arrayList) {
                            if (obj4 instanceof V8Value) {
                                ((V8Value) obj4).close();
                            } else if (obj4 instanceof JSContext.V8AssociateReference) {
                                ((JSContext.V8AssociateReference) obj4).close();
                            }
                        }
                    }
                    return true;
                } catch (Throwable th2) {
                    for (Object obj5 : arrayList) {
                        if (obj5 instanceof V8Value) {
                            ((V8Value) obj5).close();
                        } else if (obj5 instanceof JSContext.V8AssociateReference) {
                            ((JSContext.V8AssociateReference) obj5).close();
                        }
                    }
                    throw th2;
                }
            }
        }, "set");
        return v8ClassRegisterSlot2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerGlobalComponent(String str) {
        maybeInitComponentRegister();
        Iterator<IComponentRegister> it = this.mComponentRegisters.iterator();
        while (it.hasNext() && !it.next().register(str)) {
        }
    }

    private void registerSingleJsClass(final V8 v8, String str, final String str2) {
        v8.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.1
            public Object invoke(V8Object v8Object, V8Array v8Array) {
                V8Object v8Object2;
                V8ClassRegisterSlot v8ClassRegisterSlotRegisterClassObject = TKJSContext.this.registerClassObject(TKJSContext.this.mJsContext, str2);
                List<Object> arrayList = v8Array.length() == 0 ? new ArrayList<>() : V8ObjectUtils.toList(v8Array);
                arrayList.add(TKJSContext.this);
                arrayList.add(v8Object);
                Object objNewInstance = TKJSContext.this.mExportManager.getIFactory(str2).newInstance(TKJSContext.this.getViewCreateContext(), arrayList);
                if (objNewInstance instanceof ILifeCycle) {
                    ((ILifeCycle) objNewInstance).onCreate();
                }
                TKJSContext.this.mJsContext.registerAssociateObject(v8Object, objNewInstance);
                V8Array v8Array2 = new V8Array(v8);
                v8Array2.push(v8ClassRegisterSlotRegisterClassObject.classObject);
                v8Array2.push(v8ClassRegisterSlotRegisterClassObject.handleObject);
                TKJSContext.this.ensureLinkClassFunction();
                try {
                    v8Object2 = (V8Object) TKJSContext.this.mLinkClass.call(v8Object, v8Array2);
                } catch (Throwable th) {
                    TKExceptionDispatcher.reportException(TKJSContext.this, th);
                    v8Object2 = null;
                }
                v8Array2.close();
                return v8Object2;
            }
        }, str);
    }

    private void registerUtilMethod(V8Object v8Object) {
        v8Object.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.4
            public Object invoke(V8Object v8Object2, V8Array v8Array) {
                V8Object v8Object3 = (V8Object) v8Array.get(0);
                TKJSContext.this.onRendered(v8Object3);
                V8Proxy.release(v8Object3);
                return null;
            }
        }, "render");
        v8Object.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.5
            public Object invoke(V8Object v8Object2, V8Array v8Array) {
                if (v8Array.length() != 2) {
                    TKJSContext.this.logError("JS 调用 Tachikoma.registerView 参数错误，参数个数应该为 2，实际为 " + v8Array.length(), true);
                    return null;
                }
                Object obj = v8Array.get(0);
                Object obj2 = v8Array.get(1);
                if ((obj2 instanceof V8Function) && (obj instanceof String)) {
                    TKJSContext.this.mCreateViewFunctionMap.put((String) obj, (V8Function) obj2);
                } else {
                    TKJSContext.this.logError("JS 调用 Tachikoma.registerView 参数类型不对，应该为(String, Function) 实际为: " + obj.getClass().getSimpleName() + ", " + obj2.getClass().getSimpleName(), true);
                }
                return null;
            }
        }, "registerView");
        v8Object.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.6
            public Object invoke(V8Object v8Object2, V8Array v8Array) {
                TKJSContext.this.maybeInitDensity();
                return Double.valueOf(((Number) v8Array.get(0)).doubleValue() / TKJSContext.this.density);
            }
        }, "px2Dp");
        v8Object.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.7
            public Object invoke(V8Object v8Object2, V8Array v8Array) {
                TKJSContext.this.maybeInitDensity();
                return Integer.valueOf((int) ((TKJSContext.this.density * ((Number) v8Array.get(0)).doubleValue()) + 0.5d));
            }
        }, "dp2Px");
        v8Object.registerJavaMethod(new JavaVoidCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.8
            public void invoke(V8Object v8Object2, V8Array v8Array) {
                TKJSContext.this.maybeInitDensity();
                TKJSContext.this.registerGlobalComponent((String) v8Array.get(0));
            }
        }, "requireGlobalComponent");
        v8Object.registerJavaMethod(new JavaCallback() { // from class: com.tachikoma.core.bridge.TKJSContext.9
            public Object invoke(V8Object v8Object2, V8Array v8Array) {
                IHostEnvInner iHostEnvInner = (IHostEnvInner) TKModuleManager.getInstance().getHandler(TKJSContext.this, IHostEnvInner.class);
                return Boolean.valueOf(iHostEnvInner != null && iHostEnvInner.isDark());
            }
        }, "isDark");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object[] transferV8Function(Class<?>[] clsArr, V8Array v8Array) {
        Object[] objArr = new Object[v8Array.length()];
        for (int i = 0; i < v8Array.length(); i++) {
            if (clsArr[i].isAssignableFrom(IV8Function.class) && (v8Array.get(i) instanceof V8Function)) {
                objArr[i] = new V8FunctionImpl((V8Function) v8Array.get(i), this);
            } else {
                objArr[i] = v8Array.get(i);
            }
        }
        return objArr;
    }

    private V8Object updateEnv(HashMap<String, Object> map) {
        String key;
        double dFloatValue;
        V8Object v8ObjectMakeObject = this.mJsContext.makeObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            Class<?> cls = value.getClass();
            if (cls.equals(String.class)) {
                v8ObjectMakeObject.add(entry.getKey(), (String) value);
            } else if (cls.equals(Integer.class)) {
                v8ObjectMakeObject.add(entry.getKey(), ((Integer) value).intValue());
            } else {
                if (cls.equals(Float.class)) {
                    key = entry.getKey();
                    dFloatValue = ((Float) value).floatValue();
                } else if (cls.equals(Double.class)) {
                    key = entry.getKey();
                    dFloatValue = ((Double) value).doubleValue();
                } else if (cls.equals(Boolean.class)) {
                    v8ObjectMakeObject.add(entry.getKey(), ((Boolean) value).booleanValue());
                }
                v8ObjectMakeObject.add(key, dFloatValue);
            }
        }
        return v8ObjectMakeObject;
    }

    public JSContext context() {
        return this.mJsContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public JSContext.V8AssociateReference createView(@Nullable Context context, String str, Object... objArr) throws Throwable {
        V8Object v8Object;
        V8Function v8Function = this.mCreateViewFunctionMap.get(str);
        V8Value v8Value = 0;
        v8Value = 0;
        if (v8Function == null) {
            logError("找不到对应 key: " + str + " 的 view", false);
            return null;
        }
        Context context2 = this.mViewCreateContext;
        try {
            try {
                this.mViewCreateContext = context;
                context = this.mJsContext.makeV8Array();
                for (Object obj : objArr) {
                    try {
                        context.push(TKConverter.toV8Value(obj, this.mJsContext));
                    } catch (Throwable th) {
                        th = th;
                        v8Object = null;
                    }
                }
                Object objCall = v8Function.call((V8Object) null, context);
                if (objCall == null) {
                    logError("对应 key: " + str + " 的构建返回了 null", false);
                    V8Proxy.release(context);
                    V8Proxy.release(null);
                    this.mViewCreateContext = context2;
                    return null;
                }
                if (!(objCall instanceof V8Object)) {
                    throw new IllegalStateException("对应 key: " + str + " 的构建返回了非 view 类型");
                }
                v8Object = (V8Object) objCall;
                try {
                    JSContext.V8AssociateReference associateReference = this.mJsContext.getAssociateReference(v8Object);
                    if (associateReference.nativeObject instanceof TKBase) {
                        V8Proxy.release(context);
                        V8Proxy.release(v8Object);
                        this.mViewCreateContext = context2;
                        return associateReference;
                    }
                    String str2 = "对应 key: " + str + " 的构建返回了非 view 类型";
                    associateReference.close();
                    throw new IllegalStateException(str2);
                } catch (Throwable th2) {
                    th = th2;
                    TKExceptionDispatcher.reportException(this, th);
                    V8Proxy.release(context);
                    V8Proxy.release(v8Object);
                    this.mViewCreateContext = context2;
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                v8Value = objArr;
            }
        } catch (Throwable th4) {
            th = th4;
            context = null;
        }
    }

    @Nullable
    public IExceptionHandlerInner getExceptionHandler() {
        return this.mExceptionHandler;
    }

    public <T> T getHandler(Class cls) {
        T t;
        if (cls == null || (t = (T) this.mHandlerMap.get(cls)) == null) {
            return null;
        }
        return t;
    }

    public String getRootDir() {
        if (!TextUtils.isEmpty(this.mRootDir) && !this.mRootDir.endsWith("/")) {
            this.mRootDir = this.mRootDir.concat("/");
        }
        return this.mRootDir;
    }

    @Override // com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        this.mJsContext = JSContext.create();
        this.mJsContext.setTKJsContextHashcode(hashCode());
        this.mJsContext.attachView(this.mRootView);
        if (this.mExportManager == null) {
            this.mExportManager = TKExportManager.getInstance();
        }
        if (!this.mExportManager.isCreate()) {
            this.mExportManager.onCreate();
        }
        this.mPendingReleaseV8List = new ArrayList();
        registerJSClasses(this.mContext, this.mRootView);
        if (this.mLifeCycleCallback != null) {
            this.mLifeCycleCallback.onCreate();
        }
    }

    @Override // com.tachikoma.core.common.ILifeCycle
    public void onDestroy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.mLifeCycleCallback != null) {
            this.mLifeCycleCallback.onDestroy();
        }
        TKLifeCycle.ActivityLifecycleCallbacksProxy.unRegisterLifecycleCallbacks(this);
        if (this.mRootView != null) {
            dfsDestroyChildren(this.mRootView);
            this.mRootView = null;
        }
        if (this.mRootViewReference != null) {
            this.mRootViewReference.close();
        }
        Env.mEnvListener = null;
        Iterator<V8Function> it = this.mCreateViewFunctionMap.values().iterator();
        while (it.hasNext()) {
            V8Proxy.release(it.next());
        }
        this.mCreateViewFunctionMap.clear();
        if (this.mLinkClass != null) {
            V8Proxy.release(this.mLinkClass);
            this.mLinkClass = null;
        }
        for (Map.Entry<String, V8ClassRegisterSlot> entry : this.classMethodRegisterMap.entrySet()) {
            if (entry.getValue() != null) {
                V8Proxy.release(entry.getValue().classObject);
                V8Proxy.release(entry.getValue().handleObject);
            }
        }
        this.classMethodRegisterMap.clear();
        if (this.mPendingReleaseV8List != null) {
            for (V8Value v8Value : this.mPendingReleaseV8List) {
                if (!v8Value.isReleased()) {
                    v8Value.close();
                }
            }
            this.mPendingReleaseV8List.clear();
            this.mPendingReleaseV8List = null;
        }
        if (this.mJsContext != null) {
            this.mJsContext.destroy();
            this.mJsContext = null;
        }
        this.mHandlerMap.clear();
        this.mInvokeMethodCache.evictAll();
    }

    public void onRendered(V8Object v8Object) {
        JSContext.V8AssociateReference associateReference = this.mJsContext.getAssociateReference(v8Object);
        TKBase tKBase = (TKBase) associateReference.nativeObject;
        ViewGroup viewGroup = (ViewGroup) tKBase.getView();
        if (viewGroup != null) {
            YogaNode yogaNode = tKBase.getDomNode().getYogaNode();
            yogaNode.setAlignContent(YogaAlign.CENTER);
            yogaNode.setAlignItems(YogaAlign.CENTER);
            yogaNode.setJustifyContent(YogaJustify.CENTER);
            yogaNode.calculateLayout(0.0f, 0.0f);
        }
        if (this.mRootViewReference != null) {
            this.mRootViewReference.close();
        }
        this.mRootViewReference = associateReference;
        if (this.mRootView == null || viewGroup == null) {
            return;
        }
        this.mRootView.removeAllViews();
        this.mRootView.addView(viewGroup);
    }

    public void registerJSClasses(Context context, ViewGroup viewGroup) {
        V8 contextRef = this.mJsContext.getContextRef();
        V8Object v8ObjectMakeObject = this.mJsContext.makeObject();
        registerUtilMethod(v8ObjectMakeObject);
        V8Object v8ObjectUpdateEnv = updateEnv(Env.sContextInfo);
        v8ObjectMakeObject.add("env", v8ObjectUpdateEnv);
        contextRef.add("Tachikoma", v8ObjectMakeObject);
        v8ObjectUpdateEnv.close();
        W3CMoudleManager.getInstance().registerW3CObject(this.mJsContext, this, contextRef);
        v8ObjectMakeObject.close();
        for (Map.Entry<String, String> entry : this.mExportManager.allExportClasses().entrySet()) {
            registerSingleJsClass(contextRef, entry.getKey(), entry.getValue());
        }
    }

    public void registerJavaScriptInterface(Object obj, String str, HashMap<String, Class[]> map) {
        V8 contextRef = this.mJsContext.getContextRef();
        V8Object v8ObjectMakeObject = this.mJsContext.makeObject();
        contextRef.add(str, v8ObjectMakeObject);
        for (Map.Entry<String, Class[]> entry : map.entrySet()) {
            v8ObjectMakeObject.registerJavaMethod(obj, entry.getKey(), entry.getKey(), entry.getValue());
        }
        v8ObjectMakeObject.close();
    }

    public View rootView() {
        return this.mRootView;
    }

    public void setBaseBridge(@NonNull IBaseBridgeInner iBaseBridgeInner) {
        this.mHandlerMap.put(IBaseBridgeInner.class, iBaseBridgeInner);
    }

    public void setBusinessSchoolBridge(@NonNull IBusinessSchoolBridge iBusinessSchoolBridge) {
        this.mHandlerMap.put(IBusinessSchoolBridge.class, iBusinessSchoolBridge);
    }

    public void setRootDir(String str) {
        this.mRootDir = str;
    }

    public void setWebCardBridge(@NonNull IWebCardBridgeInner iWebCardBridgeInner) {
        this.mHandlerMap.put(IWebCardBridgeInner.class, iWebCardBridgeInner);
    }
}
