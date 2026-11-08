package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.ba;
import com.baidu.mobads.sdk.internal.r;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PatchAdView extends RelativeLayout {
    private static final String PLAY_END = "playCompletion";
    private static final String PLAY_ERROR = "playFailure";
    private static final String PLAY_START = "onAdShow";
    private static final String TAG = "PacthAdView";
    private View mAdView;
    private IPatchAdListener mListener;
    private ClassLoader mLoader;
    private final String mRemoteClassName;
    private Context mViewContext;

    public PatchAdView(Context context) {
        super(context);
        this.mRemoteClassName = r.i;
        init(context);
        setBackgroundColor(Color.parseColor("#000000"));
    }

    private void init(Context context) {
        this.mViewContext = context;
        Object[] objArr = {this.mViewContext};
        this.mLoader = ba.a(context);
        this.mAdView = (View) aj.a(this.mRemoteClassName, this.mLoader, (Class<?>[]) new Class[]{Context.class}, objArr);
        if (this.mAdView != null) {
            addView(this.mAdView, new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    public void setAdData(XAdVideoResponse xAdVideoResponse) {
        if (xAdVideoResponse == null || this.mAdView == null) {
            return;
        }
        aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setAdData", new Class[]{Object.class}, xAdVideoResponse);
    }

    public void setPatchAdListener(IPatchAdListener iPatchAdListener) {
        this.mListener = iPatchAdListener;
        try {
            Class<?> clsA = aj.a("com.component.patchad.IPatchAdListener", this.mLoader);
            if (clsA != null) {
                Object objNewProxyInstance = Proxy.newProxyInstance(clsA.getClassLoader(), new Class[]{clsA}, new InvocationHandlerImp());
                if (this.mAdView != null) {
                    aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setPatchAdListener", new Class[]{clsA}, objNewProxyInstance);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void setVideoVolume(boolean z) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setVideoVolume", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public long getCurrentPosition() {
        if (this.mAdView != null) {
            return ((Long) aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "getCurrentPosition", new Class[0], new Object[0])).longValue();
        }
        return 0L;
    }

    public long getDuration() {
        if (this.mAdView != null) {
            return ((Long) aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "getDuration", new Class[0], new Object[0])).longValue();
        }
        return 0L;
    }

    class InvocationHandlerImp implements InvocationHandler {
        InvocationHandlerImp() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            if (TextUtils.isEmpty(name)) {
                return null;
            }
            if (name.equals(PatchAdView.PLAY_END)) {
                if (PatchAdView.this.mListener != null) {
                    PatchAdView.this.mListener.playCompletion();
                }
            } else if (name.equals(PatchAdView.PLAY_ERROR)) {
                if (PatchAdView.this.mListener != null) {
                    PatchAdView.this.mListener.playError();
                }
            } else if (name.equals(PatchAdView.PLAY_START) && PatchAdView.this.mListener != null) {
                PatchAdView.this.mListener.onAdShow();
            }
            return null;
        }
    }
}
