package com.kwad.sdk.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.core.kwai.a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Presenter {
    private View b;
    private Object c;
    private final List<Presenter> a = new ArrayList();
    private PresenterState d = PresenterState.INIT;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'INIT' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    private static abstract class PresenterState {
        private static final /* synthetic */ PresenterState[] $VALUES;
        public static final PresenterState BIND;
        public static final PresenterState CREATE;
        public static final PresenterState DESTROY;
        public static final PresenterState INIT;
        public static final PresenterState UNBIND;
        private int mIndex;

        static {
            int i = 0;
            INIT = new PresenterState("INIT", i, i) { // from class: com.kwad.sdk.mvp.Presenter.PresenterState.1
                @Override // com.kwad.sdk.mvp.Presenter.PresenterState
                public void performCallState(Presenter presenter) {
                }
            };
            int i2 = 1;
            CREATE = new PresenterState("CREATE", i2, i2) { // from class: com.kwad.sdk.mvp.Presenter.PresenterState.2
                @Override // com.kwad.sdk.mvp.Presenter.PresenterState
                public void performCallState(Presenter presenter) {
                    Iterator it = presenter.a.iterator();
                    while (it.hasNext()) {
                        try {
                            ((Presenter) it.next()).c(presenter.b);
                        } catch (Exception e) {
                            a.a(e);
                            com.kwad.sdk.core.d.a.a(e);
                        }
                    }
                }
            };
            int i3 = 2;
            BIND = new PresenterState("BIND", i3, i3) { // from class: com.kwad.sdk.mvp.Presenter.PresenterState.3
                @Override // com.kwad.sdk.mvp.Presenter.PresenterState
                void performCallState(Presenter presenter) {
                    Iterator it = presenter.a.iterator();
                    while (it.hasNext()) {
                        try {
                            ((Presenter) it.next()).a(presenter.c);
                        } catch (Exception e) {
                            a.a(e);
                            com.kwad.sdk.core.d.a.a(e);
                        }
                    }
                }
            };
            int i4 = 3;
            UNBIND = new PresenterState("UNBIND", i4, i4) { // from class: com.kwad.sdk.mvp.Presenter.PresenterState.4
                @Override // com.kwad.sdk.mvp.Presenter.PresenterState
                void performCallState(Presenter presenter) {
                    Iterator it = presenter.a.iterator();
                    while (it.hasNext()) {
                        try {
                            ((Presenter) it.next()).j();
                        } catch (Exception e) {
                            a.a(e);
                            com.kwad.sdk.core.d.a.a(e);
                        }
                    }
                }
            };
            int i5 = 4;
            DESTROY = new PresenterState("DESTROY", i5, i5) { // from class: com.kwad.sdk.mvp.Presenter.PresenterState.5
                @Override // com.kwad.sdk.mvp.Presenter.PresenterState
                void performCallState(Presenter presenter) {
                    Iterator it = presenter.a.iterator();
                    while (it.hasNext()) {
                        try {
                            ((Presenter) it.next()).k();
                        } catch (Exception e) {
                            a.a(e);
                            com.kwad.sdk.core.d.a.a(e);
                        }
                    }
                }
            };
            $VALUES = new PresenterState[]{INIT, CREATE, BIND, UNBIND, DESTROY};
        }

        private PresenterState(String str, int i, int i2) {
            this.mIndex = i2;
        }

        public static PresenterState valueOf(String str) {
            return (PresenterState) Enum.valueOf(PresenterState.class, str);
        }

        public static PresenterState[] values() {
            return (PresenterState[]) $VALUES.clone();
        }

        public int index() {
            return this.mIndex;
        }

        abstract void performCallState(Presenter presenter);
    }

    protected final <T extends View> T a(int i) {
        return (T) this.b.findViewById(i);
    }

    protected void a() {
    }

    public final void a(Presenter presenter) {
        this.a.add(presenter);
        if (!l() || presenter.l()) {
            return;
        }
        c(this.b);
    }

    @UiThread
    public final void a(@NonNull Object obj) {
        if (this.d != PresenterState.INIT) {
            PresenterState presenterState = this.d;
            PresenterState presenterState2 = PresenterState.DESTROY;
        }
        if (this.d == PresenterState.BIND) {
            j();
        }
        this.d = PresenterState.BIND;
        this.c = obj;
        a();
        this.d.performCallState(this);
    }

    protected void c() {
    }

    @UiThread
    public final void c(View view) {
        this.d = PresenterState.CREATE;
        this.b = view;
        c_();
        this.d.performCallState(this);
    }

    protected void c_() {
    }

    protected void d_() {
    }

    @UiThread
    public final void j() {
        this.d = PresenterState.UNBIND;
        c();
        this.d.performCallState(this);
    }

    @UiThread
    public final void k() {
        if (this.d == PresenterState.BIND) {
            j();
        }
        this.d = PresenterState.DESTROY;
        d_();
        this.d.performCallState(this);
    }

    public final boolean l() {
        return this.d.index() >= PresenterState.CREATE.index();
    }

    public View m() {
        return this.b;
    }

    public List<Presenter> n() {
        return this.a;
    }

    @Nullable
    @UiThread
    public Activity o() {
        Context contextQ = q();
        HashSet hashSet = new HashSet();
        do {
            hashSet.add(contextQ);
            if (!(contextQ instanceof ContextWrapper)) {
                return null;
            }
            if (contextQ instanceof Activity) {
                return (Activity) contextQ;
            }
            if (contextQ instanceof ResContext) {
                Context delegatedContext = ((ResContext) contextQ).getDelegatedContext();
                if (delegatedContext instanceof Activity) {
                    return (Activity) delegatedContext;
                }
            }
            contextQ = ((ContextWrapper) contextQ).getBaseContext();
        } while (!hashSet.contains(contextQ));
        return null;
    }

    public Object p() {
        return this.c;
    }

    protected final Context q() {
        if (this.b == null) {
            return null;
        }
        return this.b.getContext();
    }
}
