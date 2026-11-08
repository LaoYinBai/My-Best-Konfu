package com.xiaomi.push;

import com.xiaomi.mipush.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bu implements gb {
    public static boolean a;

    /* renamed from: a, reason: collision with other field name */
    private fp f161a;

    /* renamed from: a, reason: collision with other field name */
    private SimpleDateFormat f164a = new SimpleDateFormat("hh:mm:ss aaa");

    /* renamed from: a, reason: collision with other field name */
    private a f160a = null;
    private a b = null;

    /* renamed from: a, reason: collision with other field name */
    private fs f162a = null;

    /* renamed from: a, reason: collision with other field name */
    private final String f163a = "[Slim] ";

    class a implements fu, gc {

        /* renamed from: a, reason: collision with other field name */
        String f165a;

        a(boolean z) {
            this.f165a = z ? " RCV " : " Sent ";
        }

        @Override // com.xiaomi.push.fu
        public void a(fg fgVar) {
            StringBuilder sb;
            String string;
            if (bu.a) {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bu.this.f164a.format(new Date()));
                sb.append(this.f165a);
                string = fgVar.toString();
            } else {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bu.this.f164a.format(new Date()));
                sb.append(this.f165a);
                sb.append(" Blob [");
                sb.append(fgVar.m312a());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(fgVar.a());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(fgVar.e());
                string = "]";
            }
            sb.append(string);
            com.xiaomi.channel.commonutils.logger.b.c(sb.toString());
        }

        @Override // com.xiaomi.push.fu
        public void a(gg ggVar) {
            StringBuilder sb;
            String strMo354a;
            if (bu.a) {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bu.this.f164a.format(new Date()));
                sb.append(this.f165a);
                sb.append(" PKT ");
                strMo354a = ggVar.mo354a();
            } else {
                sb = new StringBuilder();
                sb.append("[Slim] ");
                sb.append(bu.this.f164a.format(new Date()));
                sb.append(this.f165a);
                sb.append(" PKT [");
                sb.append(ggVar.k());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(ggVar.j());
                strMo354a = "]";
            }
            sb.append(strMo354a);
            com.xiaomi.channel.commonutils.logger.b.c(sb.toString());
        }

        @Override // com.xiaomi.push.gc
        /* renamed from: a, reason: collision with other method in class */
        public boolean mo143a(gg ggVar) {
            return true;
        }
    }

    public bu(fp fpVar) {
        this.f161a = null;
        this.f161a = fpVar;
        a();
    }

    private void a() {
        this.f160a = new a(true);
        this.b = new a(false);
        this.f161a.a(this.f160a, this.f160a);
        this.f161a.b(this.b, this.b);
        this.f162a = new bv(this);
    }
}
