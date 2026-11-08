package a.a.b.b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.bytedance.b.a.a.c.e;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.bytedance.sdk.b.d.a;
import java.net.HttpURLConnection;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class c {

    /* renamed from: b, reason: collision with root package name */
    public int f334b;

    /* renamed from: c, reason: collision with root package name */
    public int f335c;
    public byte[] d;
    public HandlerThread k;
    public Handler l;

    /* renamed from: a, reason: collision with root package name */
    public String f333a = "";
    public int e = -1;
    public byte[] f = null;
    public int g = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
    public int h = 1;
    public int i = 0;
    public boolean j = false;
    public HttpURLConnection m = null;

    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            c cVar = c.this;
            if (cVar.i >= cVar.h) {
                cVar.j = false;
                c cVar2 = c.this;
                cVar2.i = 0;
                cVar2.k.quit();
                return false;
            }
            if (cVar.a()) {
                c.this.j = false;
                c cVar3 = c.this;
                cVar3.i = 0;
                cVar3.k.quit();
                return true;
            }
            c.this.l.sendEmptyMessageDelayed(1, r6.g * 1);
            c.this.i++;
            return false;
        }
    }

    public c(Context context, String str) {
        if (str != null) {
            str.length();
        }
    }

    public final void a(int i) {
        HttpURLConnection httpURLConnection;
        String str;
        StringBuilder sb;
        String str2 = i != 1 ? i != 2 ? "" : a.f.f5151a : e.f3610c;
        if (str2.length() > 0) {
            this.m.addRequestProperty("Content-Type", str2);
        }
        try {
            String language = Locale.getDefault().getLanguage();
            if (language.equalsIgnoreCase("zh")) {
                httpURLConnection = this.m;
                str = "Accept-Language";
                sb = new StringBuilder();
                sb.append(Locale.getDefault().toString());
                sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                sb.append(language);
                sb.append(";q=0.9");
            } else {
                httpURLConnection = this.m;
                str = "Accept-Language";
                sb = new StringBuilder();
                sb.append(Locale.getDefault().toString());
                sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                sb.append(language);
                sb.append(";q=0.9,en-US;q=0.6,en;q=0.4");
            }
            httpURLConnection.addRequestProperty(str, sb.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void a(int i, int i2, byte[] bArr) {
        if (this.j) {
            return;
        }
        this.h = 2;
        this.f334b = i;
        this.f335c = i2;
        this.d = bArr;
        this.j = true;
        this.i = 0;
        this.k = new HandlerThread(TTLogUtil.TAG_EVENT_REQUEST);
        this.k.start();
        this.l = new Handler(this.k.getLooper(), new a());
        this.l.sendEmptyMessage(1);
    }

    public abstract boolean a(int i, byte[] bArr);

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a() {
        /*
            Method dump skipped, instructions count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.b.b.c.a():boolean");
    }
}
