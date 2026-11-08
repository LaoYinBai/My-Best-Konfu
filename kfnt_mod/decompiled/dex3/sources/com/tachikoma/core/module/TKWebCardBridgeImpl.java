package com.tachikoma.core.module;

import android.content.Context;
import com.kwad.v8.V8Function;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.api.IWebCardBridgeInner;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKWebCardBridgeImpl implements IWebCardBridgeInner {
    private TKJSContext mTKJSContext;

    public TKWebCardBridgeImpl(Context context, List<Object> list) {
        this.mTKJSContext = V8Proxy.getTKContext(list);
    }

    private IWebCardBridgeInner getBridge() {
        return (IWebCardBridgeInner) this.mTKJSContext.getHandler(IWebCardBridgeInner.class);
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void close() {
        getBridge().close();
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void convert() {
        getBridge().convert();
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public String getData() {
        return getBridge().getData();
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void handleAdUrl(String str) {
        getBridge().handleAdUrl(str);
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void hide() {
        getBridge().hide();
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void log(String str) {
        getBridge().log(str);
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void nonActionbarClick(String str) {
        getBridge().nonActionbarClick(str);
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void pageStatus(String str) {
        getBridge().pageStatus(str);
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void registerProgressListener(V8Function v8Function) {
        getBridge().registerProgressListener(v8Function);
    }

    @Override // com.tachikoma.core.api.IWebCardBridgeInner
    public void trace(String str) {
        getBridge().trace(str);
    }
}
