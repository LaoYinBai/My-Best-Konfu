package com.xiaomi.push;

import android.os.Build;
import com.ss.android.download.api.constant.BaseConstants;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.eb;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.Adler32;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fj {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private fn f378a;

    /* renamed from: a, reason: collision with other field name */
    private OutputStream f379a;

    /* renamed from: a, reason: collision with other field name */
    private byte[] f382a;
    private int b;

    /* renamed from: a, reason: collision with other field name */
    ByteBuffer f380a = ByteBuffer.allocate(2048);

    /* renamed from: b, reason: collision with other field name */
    private ByteBuffer f383b = ByteBuffer.allocate(4);

    /* renamed from: a, reason: collision with other field name */
    private Adler32 f381a = new Adler32();

    fj(OutputStream outputStream, fn fnVar) {
        this.f379a = new BufferedOutputStream(outputStream);
        this.f378a = fnVar;
        TimeZone timeZone = TimeZone.getDefault();
        this.a = timeZone.getRawOffset() / BaseConstants.Time.HOUR;
        this.b = timeZone.useDaylightTime() ? 1 : 0;
    }

    public int a(fg fgVar) {
        int iC = fgVar.c();
        if (iC > 32768) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Blob size=" + iC + " should be less than 32768 Drop blob chid=" + fgVar.a() + " id=" + fgVar.e());
            return 0;
        }
        this.f380a.clear();
        int i = iC + 8 + 4;
        if (i > this.f380a.capacity() || this.f380a.capacity() > 4096) {
            this.f380a = ByteBuffer.allocate(i);
        }
        this.f380a.putShort((short) -15618);
        this.f380a.putShort((short) 5);
        this.f380a.putInt(iC);
        int iPosition = this.f380a.position();
        this.f380a = fgVar.mo313a(this.f380a);
        if (!"CONN".equals(fgVar.m312a())) {
            if (this.f382a == null) {
                this.f382a = this.f378a.m327a();
            }
            com.xiaomi.push.service.be.a(this.f382a, this.f380a.array(), true, iPosition, iC);
        }
        this.f381a.reset();
        this.f381a.update(this.f380a.array(), 0, this.f380a.position());
        this.f383b.putInt(0, (int) this.f381a.getValue());
        this.f379a.write(this.f380a.array(), 0, this.f380a.position());
        this.f379a.write(this.f383b.array(), 0, 4);
        this.f379a.flush();
        int iPosition2 = this.f380a.position() + 4;
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] Wrote {cmd=" + fgVar.m312a() + ";chid=" + fgVar.a() + ";len=" + iPosition2 + "}");
        return iPosition2;
    }

    public void a() {
        eb.e eVar = new eb.e();
        eVar.a(106);
        eVar.a(Build.MODEL);
        eVar.b(t.m644a());
        eVar.c(com.xiaomi.push.service.bk.m619a());
        eVar.b(43);
        eVar.d(this.f378a.b());
        eVar.e(this.f378a.mo341a());
        eVar.f(Locale.getDefault().toString());
        eVar.c(Build.VERSION.SDK_INT);
        byte[] bArrMo338a = this.f378a.mo341a().mo338a();
        if (bArrMo338a != null) {
            eVar.a(eb.b.a(bArrMo338a));
        }
        fg fgVar = new fg();
        fgVar.a(0);
        fgVar.a("CONN", (String) null);
        fgVar.a(0L, "xiaomi.com", null);
        fgVar.a(eVar.a(), (String) null);
        a(fgVar);
        com.xiaomi.channel.commonutils.logger.b.m22a("[slim] open conn: andver=" + Build.VERSION.SDK_INT + " sdk=43 hash=" + com.xiaomi.push.service.bk.m619a() + " tz=" + this.a + Constants.COLON_SEPARATOR + this.b + " Model=" + Build.MODEL + " os=" + Build.VERSION.INCREMENTAL);
    }

    public void b() throws IOException {
        fg fgVar = new fg();
        fgVar.a("CLOSE", (String) null);
        a(fgVar);
        this.f379a.close();
    }
}
