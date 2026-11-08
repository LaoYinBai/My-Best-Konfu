package com.sigmob.sdk.base.views;

import com.sigmob.sdk.common.utils.m;
import com.tachikoma.core.utility.UriUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements Serializable {
    private static final List<String> a = Arrays.asList("image/jpeg", "image/png", "image/bmp", "image/gif");
    private static final List<String> b = Arrays.asList("application/x-javascript");
    private static final long serialVersionUID = 0;
    private final String c;
    private final EnumC0014b d;
    private final a e;
    private final int f;
    private final int g;

    public enum a {
        NONE,
        IMAGE,
        JAVASCRIPT
    }

    /* renamed from: com.sigmob.sdk.base.views.b$b, reason: collision with other inner class name */
    public enum EnumC0014b {
        STATIC_RESOURCE,
        HTML_RESOURCE,
        IFRAME_RESOURCE,
        NATIVE_RESOURCE,
        URL_RESOURCE
    }

    public b(String str, EnumC0014b enumC0014b, a aVar, int i, int i2) {
        m.a.a(str);
        m.a.a(enumC0014b);
        m.a.a(aVar);
        this.c = str;
        this.d = enumC0014b;
        this.e = aVar;
        this.f = i;
        this.g = i2;
    }

    public void a(CreativeWebView creativeWebView) {
        StringBuilder sb;
        String str;
        String string;
        String str2;
        m.a.a(creativeWebView);
        switch (this.d) {
            case IFRAME_RESOURCE:
                sb = new StringBuilder();
                sb.append("<iframe frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" style=\"border: 0px; margin: 0px;\" width=\"");
                sb.append(this.f);
                sb.append("\" height=\"");
                sb.append(this.g);
                sb.append("\" src=\"");
                sb.append(this.c);
                str = "\"></iframe>";
                sb.append(str);
                string = sb.toString();
                creativeWebView.a(string);
                break;
            case HTML_RESOURCE:
                string = this.c;
                creativeWebView.a(string);
                break;
            case STATIC_RESOURCE:
                if (this.e == a.IMAGE) {
                    sb = new StringBuilder();
                    sb.append("<html><head></head><body style=\"margin:0;padding:0\"><img src=\"");
                    sb.append(this.c);
                    str = "\" width=\"100%\" style=\"max-width:100%;max-height:100%;\" /></body></html>";
                } else if (this.e == a.JAVASCRIPT) {
                    sb = new StringBuilder();
                    sb.append("<script src=\"");
                    sb.append(this.c);
                    str = "\"></script>";
                }
                sb.append(str);
                string = sb.toString();
                creativeWebView.a(string);
                break;
            case NATIVE_RESOURCE:
                if (!this.c.toLowerCase().startsWith(UriUtil.FILE_PREFIX)) {
                    str2 = UriUtil.FILE_PREFIX + this.c;
                }
                creativeWebView.loadUrl(str2);
                break;
            case URL_RESOURCE:
                str2 = this.c;
                creativeWebView.loadUrl(str2);
                break;
        }
    }
}
