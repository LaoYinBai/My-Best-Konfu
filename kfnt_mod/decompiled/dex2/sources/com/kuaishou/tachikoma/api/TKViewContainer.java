package com.kuaishou.tachikoma.api;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kuaishou.tachikoma.api.page.IFunction;
import com.kwad.v8.JavaCallback;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwad.v8.utils.V8ObjectUtils;
import com.kwad.yoga.YogaAlign;
import com.kwad.yoga.YogaJustify;
import com.kwad.yoga.YogaNode;
import com.tachikoma.core.IV8Function;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.component.TKBase;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKViewContainer extends FrameLayout {
    private final JSContext.V8AssociateReference mAssociateReference;
    private IJS2NativeInvoker mInvoker;
    private TKBase tkView;

    public interface IJS2NativeInvoker {
        Object onJSInvokeNativeWithJSONString(String str, String str2, IFunction iFunction);
    }

    public interface INativeCallback {
        Object invoke(List<Object> list);
    }

    public TKViewContainer(@NonNull JSContext.V8AssociateReference v8AssociateReference) {
        super(((TKBase) v8AssociateReference.nativeObject).getContext());
        this.mAssociateReference = v8AssociateReference;
        bindTKView((TKBase) v8AssociateReference.nativeObject);
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

    private void registerInvoker() {
        if (this.tkView == null) {
            return;
        }
        final IJS2NativeInvoker iJS2NativeInvoker = this.mInvoker;
        if (iJS2NativeInvoker == null) {
            this.tkView.setIJS2NativeInvoker((TKBase.IJS2NativeInvoker) null);
        } else {
            this.tkView.setIJS2NativeInvoker(new TKBase.IJS2NativeInvoker() { // from class: com.kuaishou.tachikoma.api.TKViewContainer.2
                public Object onJSInvokeNativeWithJSONString(String str, String str2, final IV8Function iV8Function) {
                    return iJS2NativeInvoker.onJSInvokeNativeWithJSONString(str, str2, new IFunction() { // from class: com.kuaishou.tachikoma.api.TKViewContainer.2.1
                        @Override // com.kuaishou.tachikoma.api.page.IFunction
                        @Nullable
                        public Object call(@Nullable Object... objArr) {
                            return iV8Function.call(objArr);
                        }
                    });
                }
            });
        }
    }

    void bindTKView(TKBase tKBase) {
        if (this.tkView == tKBase) {
            return;
        }
        if (this.tkView != null) {
            tKBase.setIJS2NativeInvoker((TKBase.IJS2NativeInvoker) null);
            removeView(this.tkView.getView());
            this.tkView.destroy();
        }
        this.tkView = tKBase;
        registerInvoker();
        tKBase.bindV8This((V8Object) this.mAssociateReference.v8Value);
        ViewGroup viewGroup = (ViewGroup) tKBase.getView();
        if (viewGroup != null) {
            YogaNode yogaNode = tKBase.getDomNode().getYogaNode();
            yogaNode.setAlignContent(YogaAlign.CENTER);
            yogaNode.setAlignItems(YogaAlign.CENTER);
            yogaNode.setJustifyContent(YogaJustify.CENTER);
            yogaNode.calculateLayout(0.0f, 0.0f);
            addView(viewGroup);
        }
    }

    public void close() {
        this.mAssociateReference.close();
        dfsDestroyChildren(this.tkView.getView());
    }

    public Object invokeJSFunctionWithJSONString(String str, @Nullable String str2, @Nullable final IFunction iFunction) {
        return this.tkView.invokeJSFunctionWithJSONString(str, str2, iFunction == null ? null : new JavaCallback() { // from class: com.kuaishou.tachikoma.api.TKViewContainer.1
            @Override // com.kwad.v8.JavaCallback
            public Object invoke(V8Object v8Object, V8Array v8Array) {
                return iFunction.call(V8ObjectUtils.toList(v8Array).toArray());
            }
        });
    }

    public void setData(Object... objArr) {
        if (this.tkView == null) {
            throw new IllegalStateException("TKViewContainer 里没有 view，代码不应该执行到这里");
        }
        this.tkView.setData(objArr);
    }

    public void setIJS2NativeInvoker(IJS2NativeInvoker iJS2NativeInvoker) {
        this.mInvoker = iJS2NativeInvoker;
        registerInvoker();
    }
}
