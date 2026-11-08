package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.content.Context;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.error.AdBreakError;
import com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class d extends TTAdHeaderBidingRequestCore {

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f1783a;

        a(AdError adError) {
            this.f1783a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.a(this.f1783a);
        }
    }

    protected d(Context context, String str) {
        super(context, str);
    }

    private AdError K() {
        AdSlot adSlot;
        if (!this.G || (adSlot = this.h) == null) {
            return null;
        }
        AdSlot shallowCopy = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
        shallowCopy.setLinkedId(com.bytedance.msdk.core.corelogic.f.c());
        AdBreakError adBreakError = new AdBreakError(AdError.ERROR_CODE_LOADED, AdError.getMessage(AdError.ERROR_CODE_LOADED));
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(shallowCopy, cVar != null ? cVar.v() : null, true, h(), AdError.ERROR_CODE_LOADED);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar2 = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(shallowCopy, adBreakError, cVar2 != null ? cVar2.v() : null, h());
        return adBreakError;
    }

    private AdError L() {
        AdSlot adSlot;
        if (!this.H || (adSlot = this.h) == null) {
            return null;
        }
        AdSlot shallowCopy = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
        shallowCopy.setLinkedId(com.bytedance.msdk.core.corelogic.f.c());
        AdBreakError adBreakError = new AdBreakError(AdError.ERROR_CODE_SHOWED, AdError.getMessage(AdError.ERROR_CODE_SHOWED));
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(shallowCopy, cVar != null ? cVar.v() : null, true, h(), AdError.ERROR_CODE_SHOWED);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar2 = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(shallowCopy, adBreakError, cVar2 != null ? cVar2.v() : null, h());
        return adBreakError;
    }

    protected boolean J() {
        if (this.h == null) {
            return true;
        }
        AdError adErrorL = L();
        if (adErrorL == null && (adErrorL = K()) == null) {
            return true;
        }
        ThreadHelper.runOnUiThread(new a(adErrorL));
        return false;
    }
}
