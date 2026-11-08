package com.qq.e.ads.nativ.express2;

import android.content.Context;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.NativeAbstractAD;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.pi.NEIADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.GDTLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeExpressAD2 extends NativeAbstractAD<NEIADI> {
    private int g;
    private int h;
    private VideoOption2 i;
    private AdLoadListener j;
    private List<Integer> k = Collections.synchronizedList(new ArrayList());

    public interface AdLoadListener extends NativeAbstractAD.BasicADListener {
        void onLoadSuccess(List<NativeExpressADData2> list);
    }

    private static class AdLoadListenerAdapter implements ADListener {
        AdLoadListener a;

        AdLoadListenerAdapter(AdLoadListener adLoadListener) {
            this.a = adLoadListener;
        }

        @Override // com.qq.e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            if (this.a != null) {
                int type = aDEvent.getType();
                if (type != 1) {
                    if (type != 2) {
                        return;
                    }
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        this.a.onNoAD(AdErrorConvertor.formatErrorCode(((Integer) aDEvent.getParas()[0]).intValue()));
                    } else {
                        AbstractAD.a(NativeExpressAD2.class, aDEvent);
                    }
                }
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                    this.a.onLoadSuccess((List) aDEvent.getParas()[0]);
                } else {
                    AbstractAD.a(NativeExpressAD2.class, aDEvent);
                }
            }
        }
    }

    public NativeExpressAD2(Context context, String str, AdLoadListener adLoadListener) {
        GDTLogger.e("NativeExpressAD2 接口将于2022年1月移除，请使用 NativeExpressAD 接口");
        this.j = adLoadListener;
        a(context, str);
    }

    protected NEIADI a(Context context, POFactory pOFactory, String str, String str2) {
        return pOFactory.getNativeExpressADDelegate2(context, str, str2, new AdLoadListenerAdapter(this.j));
    }

    protected /* bridge */ /* synthetic */ Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return a(context, pOFactory, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(NEIADI neiadi) {
        super.a(neiadi);
        neiadi.setAdSize(this.g, this.h);
        neiadi.setVideoOption(this.i);
        synchronized (this.k) {
            Iterator<Integer> it = this.k.iterator();
            while (it.hasNext()) {
                loadAd(it.next().intValue());
            }
        }
    }

    protected void b(int i) {
        AdLoadListener adLoadListener = this.j;
        if (adLoadListener != null) {
            adLoadListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public void loadAd(int i) {
        if (a()) {
            if (!b()) {
                this.k.add(Integer.valueOf(i));
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((NEIADI) obj).loadAd(i);
            } else {
                a("loadAd");
            }
        }
    }

    public void setAdSize(int i, int i2) {
        this.g = i;
        this.h = i2;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NEIADI) obj).setAdSize(i, i2);
        }
    }

    public void setVideoOption2(VideoOption2 videoOption2) {
        this.i = videoOption2;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NEIADI) obj).setVideoOption(videoOption2);
        }
    }
}
