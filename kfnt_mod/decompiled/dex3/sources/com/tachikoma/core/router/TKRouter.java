package com.tachikoma.core.router;

import android.content.Context;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.base.NativeModule;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.manager.RouterManager;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRouter implements NativeModule {
    private JSContext mJSContext;

    public TKRouter(Context context, List<Object> list) {
        this.mJSContext = V8Proxy.getTKContext(list).context();
    }

    @Override // com.tachikoma.core.base.NativeModule
    public void destroy() {
    }

    @Override // com.tachikoma.core.base.NativeModule
    public String getName() {
        return "Router";
    }

    public void navigateTo(String str) {
        RouterManager.getInstance().navigateTo(this.mJSContext, str);
    }
}
