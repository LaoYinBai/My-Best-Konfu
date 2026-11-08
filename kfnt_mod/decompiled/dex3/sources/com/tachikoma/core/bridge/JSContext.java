package com.tachikoma.core.bridge;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import com.kwad.v8.V8;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.tachikoma.core.api.IRenderListenerInner;
import com.tachikoma.core.component.network.Network;
import com.tachikoma.core.debug.TKDebuggerUtils;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.utility.TKEnv;
import com.tachikoma.core.utility.V8Proxy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class JSContext {
    private Map<Object, V8AssociateReference> associateRefMap = new HashMap();
    private String mDebuggerContext;
    private int mTKJsContextHashcode;
    private MemoryManager memoryManager;
    private final V8 runPtr;
    private Field v8DataField;

    public static class V8AssociateReference {

        @Nullable
        public final Object nativeObject;
        private AtomicLong referenceCount;
        public final V8Value v8Value;

        private V8AssociateReference(V8Value v8Value, @Nullable Object obj) {
            this.referenceCount = new AtomicLong(0L);
            this.v8Value = v8Value.twin();
            this.nativeObject = obj;
        }

        public static void closeAllReference(Iterator<V8AssociateReference> it) {
            while (it.hasNext()) {
                it.next().close();
            }
        }

        @Nullable
        public static V8AssociateReference closeByNative(Object obj, Iterator<V8AssociateReference> it) {
            while (it.hasNext()) {
                V8AssociateReference next = it.next();
                if (next.nativeObject == obj) {
                    it.remove();
                    next.close();
                    return next;
                }
            }
            return null;
        }

        public void close() {
            if (this.referenceCount.decrementAndGet() == 0) {
                if (this.v8Value.isReleased() && TKEnv.isDebug()) {
                    throw new RuntimeException("V8Value is already released.");
                }
                V8Proxy.release(this.v8Value);
            }
        }
    }

    private JSContext(V8 v8) {
        this.runPtr = v8;
        this.memoryManager = new MemoryManager(v8, false);
        onCreateV8Debugger(this);
    }

    public static JSContext create() {
        return new JSContext(TKV8.createV8Runtime());
    }

    private Object innerEvaluateScript(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = TKDebuggerUtils.genScriptName(str2);
            }
            onExecuteScript(str, str2);
            return this.runPtr.executeScript(str, str2, 0);
        } catch (Exception e) {
            TKExceptionDispatcher.dispatchJSException(e, this.mTKJsContextHashcode);
            return null;
        }
    }

    private void onCreateV8Debugger(JSContext jSContext) {
        if (TextUtils.isEmpty(this.mDebuggerContext)) {
            this.mDebuggerContext = TKDebuggerUtils.genContextName();
        }
    }

    private void onDestroyV8Debugger() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(this.mDebuggerContext)) {
            return;
        }
        TKDebuggerUtils.disableDebug(this.mDebuggerContext);
    }

    private void onExecuteScript(String str, String str2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(this.mDebuggerContext)) {
            return;
        }
        TKDebuggerUtils.onExecuteScript(this.mDebuggerContext, str, str2, null, 0);
    }

    public void attachView(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(this.mDebuggerContext) || this.runPtr == null) {
            return;
        }
        TKDebuggerUtils.enableDebug(this.runPtr, this.mDebuggerContext, view);
    }

    public void destroy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.memoryManager.release();
        onDestroyV8Debugger();
        this.runPtr.close();
        Network.removeRequestDelegate(this);
    }

    public Object evaluateScript(String str, String str2) {
        return innerEvaluateScript(str, str2);
    }

    public void evaluateScript(String str, String str2, IRenderListenerInner iRenderListenerInner) {
        if (TextUtils.isEmpty(str)) {
            if (iRenderListenerInner != null) {
                iRenderListenerInner.failed(new Exception("empty bundle script"));
                return;
            }
            return;
        }
        try {
            innerEvaluateScript(str, str2);
            if (iRenderListenerInner != null) {
                iRenderListenerInner.success();
            }
        } catch (Throwable th) {
            TKExceptionDispatcher.dispatchJSException(th, this.mTKJsContextHashcode);
            if (iRenderListenerInner != null) {
                iRenderListenerInner.failed(th);
            }
        }
    }

    public Object evaluateScriptById(String str) {
        try {
            return innerEvaluateScript(str, null);
        } catch (Exception e) {
            TKExceptionDispatcher.dispatchJSException(e, this.mTKJsContextHashcode);
            return null;
        }
    }

    public void evaluateScriptById(Context context, String str, boolean z, IRenderListenerInner iRenderListenerInner) {
        evaluateScript(str, null, iRenderListenerInner);
    }

    public V8AssociateReference getAssociateReference(V8Object v8Object) {
        Object linkedNativeObject = this.memoryManager.getLinkedNativeObject(v8Object);
        V8AssociateReference v8AssociateReference = this.associateRefMap.get(v8Object);
        if (v8AssociateReference == null) {
            v8AssociateReference = new V8AssociateReference(v8Object, linkedNativeObject);
        }
        v8AssociateReference.referenceCount.incrementAndGet();
        return v8AssociateReference;
    }

    public V8 getContextRef() {
        return this.runPtr;
    }

    public int getTKJsContextHashcode() {
        return this.mTKJsContextHashcode;
    }

    public V8Object makeObject() {
        return new V8Object(this.runPtr);
    }

    public V8Array makeV8Array() {
        return new V8Array(this.runPtr);
    }

    public void registerAssociateObject(V8Object v8Object, Object obj) {
        this.memoryManager.linkNativeObject(v8Object, obj);
    }

    public boolean removeAssociateObject(Object obj) {
        Iterator<Map.Entry<Object, V8AssociateReference>> it = this.associateRefMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Object, V8AssociateReference> next = it.next();
            if (next.getValue() != null && next.getValue().nativeObject == obj) {
                this.associateRefMap.remove(next.getKey());
                break;
            }
        }
        return this.memoryManager.removeNativeObject(obj);
    }

    public void setTKJsContextHashcode(int i) {
        this.mTKJsContextHashcode = i;
    }
}
