package com.tachikoma.core.component.view;

import android.content.Context;
import android.view.MotionEvent;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.api.IHostEnvInner;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.TKYogaLayout;
import com.tachikoma.core.event.TKEventProcess;
import com.tachikoma.core.event.base.GestureUtils;
import com.tachikoma.core.event.base.IBaseEvent;
import com.tachikoma.core.event.base.TKBaseEvent;
import com.tachikoma.core.event.guesture.TKDispatchEvent;
import com.tachikoma.core.manager.TKModuleManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKView extends TKBase<TKYogaLayout> {
    private List<JSContext.V8AssociateReference> associateChildRefList;
    private Map<String, TKBase> children;
    public boolean clipChildren;
    private V8Function disPathEventListener;

    public TKView(Context context, @Nullable List<Object> list) {
        super(context, list);
        this.children = new HashMap();
        this.clipChildren = true;
        this.associateChildRefList = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dispatchTouchEvent(String str, TKEventProcess.EventInterface eventInterface) {
        Object objProcessEventCallBack;
        V8Function v8Function = this.eventListener.get(str);
        if (v8Function == null || v8Function.isReleased() || (objProcessEventCallBack = TKEventProcess.processEventCallBack(v8Function, str, this.mTKJSContext.context(), getContext(), eventInterface)) == null || !(objProcessEventCallBack instanceof Boolean)) {
            return true;
        }
        return ((Boolean) objProcessEventCallBack).booleanValue();
    }

    private void throwIfDebug(Throwable th) {
        IHostEnvInner iHostEnvInner = (IHostEnvInner) TKModuleManager.getInstance().getHandler(this.mTKJSContext, IHostEnvInner.class);
        if (iHostEnvInner != null && iHostEnvInner.isDebug()) {
            throw new RuntimeException(th);
        }
    }

    public void add(V8Object v8Object) {
        JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
        TKBase tKBase = (TKBase) associateReference.nativeObject;
        tKBase.onAttach();
        getDomNode().addSubview(tKBase);
        this.children.put(tKBase.getViewID(), tKBase);
        this.associateChildRefList.add(associateReference);
        getDomNode().layoutSubviews();
    }

    @Override // com.tachikoma.core.component.TKBase
    @Keep
    public void addEventListener(String str, V8Function v8Function) {
        if (TKBaseEvent.TK_DISPATCH_EVENT_NAME.equals(str)) {
            getView().registerDispatchListener(new TKYogaLayout.DisPatchEventListener() { // from class: com.tachikoma.core.component.view.TKView.1
                @Override // com.tachikoma.core.component.TKYogaLayout.DisPatchEventListener
                public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
                    return TKView.this.dispatchTouchEvent(TKBaseEvent.TK_DISPATCH_EVENT_NAME, new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.view.TKView.1.1
                        @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                        public void callBackEvent(IBaseEvent iBaseEvent) {
                            if (iBaseEvent instanceof TKDispatchEvent) {
                                TKDispatchEvent tKDispatchEvent = (TKDispatchEvent) iBaseEvent;
                                tKDispatchEvent.setType(TKBaseEvent.TK_DISPATCH_EVENT_NAME);
                                tKDispatchEvent.setPosition(new HashMap<String, Float>() { // from class: com.tachikoma.core.component.view.TKView.1.1.1
                                    {
                                        put("x", Float.valueOf(motionEvent.getX()));
                                        put("y", Float.valueOf(motionEvent.getY()));
                                    }
                                });
                                tKDispatchEvent.setState(GestureUtils.findStateInMotionEvent(motionEvent));
                            }
                        }
                    });
                }
            });
        }
        super.addEventListener(str, v8Function);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public TKYogaLayout createViewInstance(Context context) {
        return new TKYogaLayout(context);
    }

    public Map<String, TKBase> getChildren() {
        return this.children;
    }

    public TKBase getSubview(String str) {
        return this.children.get(str);
    }

    public void insertBefore(V8Object v8Object, V8Object v8Object2) {
        try {
            JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
            JSContext.V8AssociateReference associateReference2 = this.mTKJSContext.context().getAssociateReference(v8Object2);
            TKBase tKBase = (TKBase) associateReference.nativeObject;
            getDomNode().insertBefore(tKBase, (TKBase) associateReference2.nativeObject);
            this.children.put(tKBase.getViewID(), tKBase);
            this.associateChildRefList.add(associateReference);
            getDomNode().layoutSubviews();
            associateReference.close();
            associateReference2.close();
        } catch (Exception e) {
            e.printStackTrace();
            throwIfDebug(e);
        }
    }

    public void layout() {
        getDomNode().layoutSubviews();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        TKYogaLayout view = getView();
        if (view != null) {
            view.removeAllViews();
            view.unRegisterListener();
        }
        JSContext.V8AssociateReference.closeAllReference(this.associateChildRefList.iterator());
        this.associateChildRefList.clear();
    }

    public void onDisPath() {
    }

    public void remove(V8Object v8Object) {
        try {
            JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
            TKBase tKBase = (TKBase) associateReference.nativeObject;
            getDomNode().removeSubview(tKBase);
            tKBase.getDomNode().setYogaNode(null);
            this.children.remove(tKBase.getViewID());
            getDomNode().layoutSubviews();
            associateReference.close();
            JSContext.V8AssociateReference.closeByNative(tKBase, this.associateChildRefList.iterator());
        } catch (Exception e) {
            e.printStackTrace();
            throwIfDebug(e);
        }
    }

    public void removeAll() {
        try {
            getDomNode().removeAllSubviews();
            for (TKBase tKBase : this.children.values()) {
                tKBase.getDomNode().setYogaNode(null);
                tKBase.onDestroy();
            }
            this.children.clear();
            Iterator<JSContext.V8AssociateReference> it = this.associateChildRefList.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.associateChildRefList.clear();
        } catch (Exception e) {
            e.printStackTrace();
            throwIfDebug(e);
        }
    }

    public void replace(V8Object v8Object, V8Object v8Object2) {
        try {
            JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
            JSContext.V8AssociateReference associateReference2 = this.mTKJSContext.context().getAssociateReference(v8Object2);
            TKBase tKBase = (TKBase) associateReference.nativeObject;
            TKBase tKBase2 = (TKBase) associateReference2.nativeObject;
            getDomNode().replaceSubview(tKBase, tKBase2);
            this.children.remove(tKBase2.getViewID());
            this.children.put(tKBase.getViewID(), tKBase2);
            associateReference2.close();
            JSContext.V8AssociateReference.closeByNative(associateReference2, this.associateChildRefList.iterator());
            this.associateChildRefList.add(associateReference);
            getDomNode().layoutSubviews();
        } catch (Exception e) {
            e.printStackTrace();
            throwIfDebug(e);
        }
    }

    public void setClipChildren(boolean z) {
        this.clipChildren = z;
        getView().setClipChildren(z);
    }
}
