package com.kuaishou.tachikoma.api;

import android.webkit.JavascriptInterface;
import com.tachikoma.core.api.IRenderListenerInner;
import com.tachikoma.core.bridge.TKJSContext;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKJSBridge {
    TKJSContext mTKContext;

    public TKJSBridge(TKJSContext tKJSContext) {
        this.mTKContext = tKJSContext;
    }

    public void addJavascriptInterface(Object obj, String str) throws SecurityException {
        Method[] methods = obj.getClass().getMethods();
        if (methods == null) {
            return;
        }
        HashMap map = new HashMap();
        for (Method method : methods) {
            if (method.isAnnotationPresent(JavascriptInterface.class)) {
                map.put(method.getName(), method.getParameterTypes());
            }
        }
        this.mTKContext.registerJavaScriptInterface(obj, str, map);
    }

    public void callJS(String str, String str2) {
        this.mTKContext.context().evaluateScript(str, str2, (IRenderListenerInner) null);
    }
}
