package com.tachikoma.core.manager;

import com.kwad.v8.V8;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.tachikoma.core.api.IHostEnvInner;
import com.tachikoma.core.bridge.IComponentRegister;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.component.timer.KTTimer;
import com.tachikoma.core.utility.Console;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class W3CMoudleManager implements IComponentRegister {
    private static W3CMoudleManager moudleManager = new W3CMoudleManager();

    private W3CMoudleManager() {
    }

    public static W3CMoudleManager getInstance() {
        return moudleManager;
    }

    @Override // com.tachikoma.core.bridge.IComponentRegister
    public boolean register(String str) {
        return false;
    }

    public void registerW3CObject(JSContext jSContext, TKJSContext tKJSContext, V8 v8) {
        Console console = new Console();
        IHostEnvInner iHostEnvInner = (IHostEnvInner) TKModuleManager.getInstance().getHandler(tKJSContext, IHostEnvInner.class);
        if (iHostEnvInner != null) {
            console.setShowLog(iHostEnvInner.isShowLog());
        }
        V8Object v8ObjectMakeObject = jSContext.makeObject();
        v8.add("console", v8ObjectMakeObject);
        v8ObjectMakeObject.registerJavaMethod(console, "log", "log", new Class[]{String.class});
        v8ObjectMakeObject.registerJavaMethod(console, "err", "err", new Class[]{String.class});
        v8ObjectMakeObject.close();
        KTTimer kTTimer = new KTTimer();
        v8.registerJavaMethod(kTTimer, "setInterval", "setInterval", new Class[]{V8Function.class, Long.TYPE});
        v8.registerJavaMethod(kTTimer, "clearInterval", "clearInterval", new Class[]{Integer.TYPE});
        v8.registerJavaMethod(kTTimer, "setTimeout", "setTimeout", new Class[]{V8Function.class, Long.TYPE});
        v8.registerJavaMethod(kTTimer, "clearTimeout", "clearTimeout", new Class[]{Integer.TYPE});
    }
}
