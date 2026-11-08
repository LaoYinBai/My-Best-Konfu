package org.android.agoo.control;

import com.taobao.accs.base.TaoBaseService;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class b implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ String b;
    final /* synthetic */ TaoBaseService.ExtraInfo c;
    final /* synthetic */ AgooFactory d;

    b(AgooFactory agooFactory, byte[] bArr, String str, TaoBaseService.ExtraInfo extraInfo) {
        this.d = agooFactory;
        this.a = bArr;
        this.b = str;
        this.c = extraInfo;
    }

    @Override // java.lang.Runnable
    public void run() throws JSONException, UnsupportedEncodingException {
        this.d.msgReceiverPreHandler(this.a, this.b, this.c, true);
    }
}
