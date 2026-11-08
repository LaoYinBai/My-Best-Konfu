package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.cq;
import com.xiaomi.push.ct;
import com.xiaomi.push.cu;
import com.xiaomi.push.ea;
import com.xiaomi.push.eb;
import com.xiaomi.push.fc;
import com.xiaomi.push.fp;
import com.xiaomi.push.gr;
import com.xiaomi.push.hb;
import com.xiaomi.push.hd;
import com.xiaomi.push.service.bk;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ba extends bk.a implements cu.a {
    private long a;

    /* renamed from: a, reason: collision with other field name */
    private XMPushService f914a;

    static class a implements cu.b {
        a() {
        }

        @Override // com.xiaomi.push.cu.b
        public String a(String str) throws IOException {
            Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
            builderBuildUpon.appendQueryParameter("sdkver", String.valueOf(43));
            builderBuildUpon.appendQueryParameter("osver", String.valueOf(Build.VERSION.SDK_INT));
            builderBuildUpon.appendQueryParameter("os", gr.a(Build.MODEL + Constants.COLON_SEPARATOR + Build.VERSION.INCREMENTAL));
            builderBuildUpon.appendQueryParameter("mi", String.valueOf(com.xiaomi.push.t.a()));
            String string = builderBuildUpon.toString();
            com.xiaomi.channel.commonutils.logger.b.c("fetch bucket from : " + string);
            URL url = new URL(string);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                String strA = com.xiaomi.push.bg.a(com.xiaomi.push.t.m643a(), url);
                hd.a(url.getHost() + Constants.COLON_SEPARATOR + port, (int) (System.currentTimeMillis() - jCurrentTimeMillis), null);
                return strA;
            } catch (IOException e) {
                hd.a(url.getHost() + Constants.COLON_SEPARATOR + port, -1, e);
                throw e;
            }
        }
    }

    static class b extends cu {
        protected b(Context context, ct ctVar, cu.b bVar, String str) {
            super(context, ctVar, bVar, str);
        }

        @Override // com.xiaomi.push.cu
        protected String a(ArrayList<String> arrayList, String str, String str2, boolean z) throws IOException {
            try {
                if (hb.m374a().m379a()) {
                    str2 = bk.m619a();
                }
                return super.a(arrayList, str, str2, z);
            } catch (IOException e) {
                hd.a(0, fc.GSLB_ERR.a(), 1, null, com.xiaomi.push.bg.c(a) ? 1 : 0);
                throw e;
            }
        }
    }

    ba(XMPushService xMPushService) {
        this.f914a = xMPushService;
    }

    public static void a(XMPushService xMPushService) {
        ba baVar = new ba(xMPushService);
        bk.a().a(baVar);
        synchronized (cu.class) {
            cu.a(baVar);
            cu.a(xMPushService, null, new a(), com.sigmob.sdk.common.Constants.FAIL, "push", "2.2");
        }
    }

    @Override // com.xiaomi.push.cu.a
    public cu a(Context context, ct ctVar, cu.b bVar, String str) {
        return new b(context, ctVar, bVar, str);
    }

    @Override // com.xiaomi.push.service.bk.a
    public void a(ea.a aVar) {
    }

    @Override // com.xiaomi.push.service.bk.a
    public void a(eb.b bVar) throws JSONException {
        cq cqVarB;
        if (bVar.m232b() && bVar.m231a() && System.currentTimeMillis() - this.a > 3600000) {
            com.xiaomi.channel.commonutils.logger.b.m22a("fetch bucket :" + bVar.m231a());
            this.a = System.currentTimeMillis();
            cu cuVarA = cu.a();
            cuVarA.m185a();
            cuVarA.m188b();
            fp fpVarM567a = this.f914a.m567a();
            if (fpVarM567a == null || (cqVarB = cuVarA.b(fpVarM567a.m330a().c())) == null) {
                return;
            }
            ArrayList<String> arrayListM173a = cqVarB.m173a();
            boolean z = true;
            Iterator<String> it = arrayListM173a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().equals(fpVarM567a.mo331a())) {
                    z = false;
                    break;
                }
            }
            if (!z || arrayListM173a.isEmpty()) {
                return;
            }
            com.xiaomi.channel.commonutils.logger.b.m22a("bucket changed, force reconnect");
            this.f914a.a(0, (Exception) null);
            this.f914a.a(false);
        }
    }
}
