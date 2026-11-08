package com.mbridge.msdk.widget.custom;

/* compiled from: CustomViewRenderListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface a {

    /* compiled from: CustomViewRenderListener.java */
    /* renamed from: com.mbridge.msdk.widget.custom.a$a, reason: collision with other inner class name */
    public enum EnumC0147a {
        CLICK_EVENT_OPEN_PRIVACY,
        CLICK_EVENT_DOWNLOAD,
        CLICK_EVENT_CLOSE
    }

    void a(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap);

    void a(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap, EnumC0147a enumC0147a);

    void a(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap, Exception exc);

    void b(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap);

    void c(DownloadMessageDialog downloadMessageDialog, CustomViewMessageWrap customViewMessageWrap);
}
