package com.tachikoma.core.event;

import android.content.Context;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.v8.utils.V8ObjectUtils;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.event.base.IBaseEvent;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.manager.TKExportManager;
import com.tachikoma.core.utility.V8Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKEventProcess {

    public interface EventInterface {
        void callBackEvent(IBaseEvent iBaseEvent);
    }

    public static Object processEventCallBack(V8Function v8Function, String str, JSContext jSContext, Context context, EventInterface eventInterface) {
        Object objCall = null;
        if (v8Function != null) {
            String eventName = TKEventCollection.getInstance().getEventName(str);
            IBaseEvent iBaseEvent = (IBaseEvent) TKExportManager.getInstance().getIFactory(eventName).newInstance(context, null);
            eventInterface.callBackEvent(iBaseEvent);
            V8Object v8Object = V8ObjectUtils.toV8Object(v8Function.getRuntime(), TKExportManager.getInstance().retrieveEvent(eventName, iBaseEvent));
            V8Array v8ArrayPush = jSContext.makeV8Array().push(v8Object);
            try {
                objCall = v8Function.call((V8Object) null, v8ArrayPush);
            } catch (Exception e) {
                TKExceptionDispatcher.dispatchJSException(e, jSContext.getTKJsContextHashcode());
            }
            V8Proxy.release(v8Object);
            V8Proxy.release(v8ArrayPush);
        }
        return objCall;
    }
}
