package com.mbridge.msdk.video.module.a.a;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DefaultImageLoaderListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements com.mbridge.msdk.foundation.same.c.c {
    private CampaignEx a;
    protected ImageView b;
    private String c;

    public d(ImageView imageView) {
        this.b = imageView;
    }

    public d(ImageView imageView, CampaignEx campaignEx, String str) {
        this.b = imageView;
        this.a = campaignEx;
        this.c = str;
    }

    @Override // com.mbridge.msdk.foundation.same.c.c
    public void onSuccessLoad(Bitmap bitmap, String str) {
        try {
            if (bitmap == null) {
                s.d("ImageLoaderListener", "bitmap=null");
            } else {
                if (this.b == null || bitmap.isRecycled()) {
                    return;
                }
                this.b.setImageBitmap(bitmap);
                this.b.setVisibility(0);
            }
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.mbridge.msdk.foundation.same.c.c
    public void onFailedLoad(String str, String str2) {
        u uVarA;
        try {
            uVarA = u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            s.a("ImageLoaderListener", "campaign is null");
            return;
        }
        q qVar = new q();
        qVar.o("2000044");
        qVar.b(o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
        qVar.n(this.a.getId());
        qVar.d(this.a.getImageUrl());
        qVar.k(this.a.getRequestId());
        qVar.l(this.a.getRequestIdNotice());
        qVar.m(this.c);
        qVar.p(str);
        uVarA.a(qVar);
        s.d("ImageLoaderListener", "desc:" + str);
    }
}
