package com.qq.e.ads.nativ;

import android.content.Context;
import android.text.TextUtils;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.NativeAbstractAD;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.constants.LoadAdParams;
import com.qq.e.comm.pi.NUADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.GDTLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeUnifiedAD extends NativeAbstractAD<NUADI> {
    private AdListenerAdapter g;
    private NativeADUnifiedListener h;
    private List<Integer> i;
    private List<String> j;
    private volatile int k;
    private volatile int l;
    private String m;
    private LoadAdParams n;

    private static class AdListenerAdapter implements ADListener {
        private NativeADUnifiedListener a;

        public AdListenerAdapter(NativeADUnifiedListener nativeADUnifiedListener) {
            this.a = nativeADUnifiedListener;
        }

        @Override // com.qq.e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            if (this.a != null) {
                Object[] paras = aDEvent.getParas();
                int type = aDEvent.getType();
                if (type != 1) {
                    if (type != 2) {
                        return;
                    }
                    if (paras.length < 1 || !(paras[0] instanceof Integer)) {
                        AbstractAD.a(NativeUnifiedAD.class, aDEvent);
                        return;
                    } else {
                        this.a.onNoAD(AdErrorConvertor.formatErrorCode(((Integer) aDEvent.getParas()[0]).intValue()));
                        return;
                    }
                }
                if (paras.length != 1 || !(paras[0] instanceof List)) {
                    AbstractAD.a(NativeUnifiedAD.class, aDEvent);
                    return;
                }
                List list = (List) paras[0];
                ArrayList arrayList = new ArrayList();
                if (list != null && list.size() > 0) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new NativeUnifiedADDataAdapter((NativeUnifiedADData) it.next()));
                    }
                }
                this.a.onADLoaded(arrayList);
            }
        }
    }

    public NativeUnifiedAD(Context context, String str, NativeADUnifiedListener nativeADUnifiedListener) {
        this.i = new ArrayList();
        this.h = nativeADUnifiedListener;
        this.g = new AdListenerAdapter(nativeADUnifiedListener);
        a(context, str);
    }

    public NativeUnifiedAD(Context context, String str, NativeADUnifiedListener nativeADUnifiedListener, String str2) {
        this.i = new ArrayList();
        this.h = nativeADUnifiedListener;
        this.g = new AdListenerAdapter(nativeADUnifiedListener);
        a(context, str, str2);
    }

    @Deprecated
    public NativeUnifiedAD(Context context, String str, String str2, NativeADUnifiedListener nativeADUnifiedListener) {
        this(context, str2, nativeADUnifiedListener);
        c();
    }

    private void a(int i, boolean z) {
        if (a()) {
            if (!b()) {
                if (z) {
                    this.i.add(Integer.valueOf(i));
                    return;
                }
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                LoadAdParams loadAdParams = this.n;
                if (loadAdParams != null) {
                    ((NUADI) obj).loadData(i, loadAdParams);
                } else {
                    ((NUADI) obj).loadData(i);
                }
            }
        }
    }

    protected Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getNativeAdManagerDelegate(context, str, str2, str3, this.g);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(NUADI nuadi) {
        super.a(nuadi);
        nuadi.setMinVideoDuration(this.k);
        nuadi.setMaxVideoDuration(this.l);
        nuadi.setVastClassName(this.m);
        List<String> list = this.j;
        if (list != null) {
            setCategories(list);
        }
        Iterator<Integer> it = this.i.iterator();
        while (it.hasNext()) {
            a(it.next().intValue(), false);
        }
    }

    protected void b(int i) {
        NativeADUnifiedListener nativeADUnifiedListener = this.h;
        if (nativeADUnifiedListener != null) {
            nativeADUnifiedListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public String getAdNetWorkName() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((NUADI) obj).getAdNetWorkName();
        }
        a("getAdNetWorkName");
        return null;
    }

    public void loadData(int i) {
        a(i, true);
    }

    public void loadData(int i, LoadAdParams loadAdParams) {
        this.n = loadAdParams;
        loadData(i);
    }

    public void setCategories(List<String> list) {
        this.j = list;
        Object obj = ((AbstractAD) this).a;
        if (obj == null || list == null) {
            return;
        }
        ((NUADI) obj).setCategories(list);
    }

    public void setMaxVideoDuration(int i) {
        this.l = i;
        if (this.l > 0 && this.k > this.l) {
            GDTLogger.e("maxVideoDuration 设置值非法，不得小于minVideoDuration");
        }
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NUADI) obj).setMaxVideoDuration(this.l);
        }
    }

    public void setMinVideoDuration(int i) {
        this.k = i;
        if (this.l > 0 && this.k > this.l) {
            GDTLogger.e("minVideoDuration 设置值非法，不得大于maxVideoDuration");
        }
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NUADI) obj).setMinVideoDuration(this.k);
        }
    }

    public void setVastClassName(String str) {
        if (TextUtils.isEmpty(str)) {
            GDTLogger.e("Vast class name 不能为空");
            return;
        }
        this.m = str;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NUADI) obj).setVastClassName(str);
        }
    }

    @Deprecated
    public void setVideoADContainerRender(int i) {
    }

    @Deprecated
    public void setVideoPlayPolicy(int i) {
    }
}
