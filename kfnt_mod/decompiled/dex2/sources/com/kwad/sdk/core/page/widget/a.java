package com.kwad.sdk.core.page.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.bc;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"ViewConstructor"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends View implements bc.a {
    private InterfaceC0044a a;
    private View b;
    private boolean c;
    private boolean d;
    private boolean e;
    private final bc f;
    private final AtomicBoolean g;

    /* renamed from: com.kwad.sdk.core.page.widget.a$a, reason: collision with other inner class name */
    public interface InterfaceC0044a {
        void a();

        void a(View view);

        void a(boolean z);

        void b();
    }

    public a(Context context, View view) {
        super(context);
        this.f = new bc(this);
        this.g = new AtomicBoolean(true);
        this.b = view;
        setLayoutParams(new ViewGroup.LayoutParams(0, 0));
    }

    private void a() {
        if (!this.g.getAndSet(false) || this.a == null) {
            return;
        }
        this.a.a();
    }

    private void b() {
        if (this.g.getAndSet(true) || this.a == null) {
            return;
        }
        this.a.b();
    }

    private void c() {
        if (this.d) {
            this.f.removeCallbacksAndMessages(null);
            this.d = false;
        }
    }

    private void d() {
        if (!this.e || this.d) {
            return;
        }
        this.d = true;
        this.f.sendEmptyMessage(1);
    }

    @Override // com.kwad.sdk.utils.bc.a
    public void a(Message message) {
        switch (message.what) {
            case 1:
                com.kwad.sdk.core.d.a.a("EmptyView", "handleMsg MSG_CHECKING");
                if (this.d) {
                    if (!bb.a(this.b, 30, false)) {
                        this.f.sendEmptyMessageDelayed(1, 500L);
                        break;
                    } else {
                        c();
                        Message messageObtainMessage = this.f.obtainMessage();
                        messageObtainMessage.what = 2;
                        messageObtainMessage.arg1 = 1000;
                        this.f.sendMessageDelayed(messageObtainMessage, 1000L);
                        break;
                    }
                }
                break;
            case 2:
                if (!bb.a(this.b, 30, false)) {
                    if (!this.c) {
                        setNeedCheckingShow(true);
                        break;
                    }
                } else {
                    if (message.arg1 == 1000 && this.a != null) {
                        this.a.a(this.b);
                    }
                    this.f.sendEmptyMessageDelayed(2, 500L);
                    break;
                }
                break;
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.kwad.sdk.core.d.a.a("EmptyView", "onAttachedToWindow:" + this);
        d();
        this.c = false;
        a();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.kwad.sdk.core.d.a.a("EmptyView", "onDetachedFromWindow" + this);
        c();
        this.c = true;
        b();
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        com.kwad.sdk.core.d.a.a("EmptyView", "onFinishTemporaryDetach:" + this.b.getParent());
        a();
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        com.kwad.sdk.core.d.a.a("EmptyView", "onStartTemporaryDetach:" + this.b.getParent());
        b();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        com.kwad.sdk.core.d.a.a("EmptyView", "onWindowFocusChanged hasWindowFocus:" + z);
        if (this.a != null) {
            this.a.a(z);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        com.kwad.sdk.core.d.a.a("EmptyView", "onWindowVisibilityChanged visibility:" + i);
    }

    public void setNeedCheckingShow(boolean z) {
        this.e = z;
        if (!z && this.d) {
            c();
        } else {
            if (!z || this.d) {
                return;
            }
            d();
        }
    }

    public void setViewCallback(InterfaceC0044a interfaceC0044a) {
        this.a = interfaceC0044a;
    }
}
