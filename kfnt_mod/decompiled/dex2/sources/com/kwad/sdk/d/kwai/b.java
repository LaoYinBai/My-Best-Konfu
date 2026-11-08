package com.kwad.sdk.d.kwai;

import com.kwad.sdk.mvp.Presenter;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends Presenter {
    public void g() {
        List<Presenter> listN = n();
        if (listN == null) {
            return;
        }
        for (Presenter presenter : listN) {
            if (presenter instanceof b) {
                ((b) presenter).g();
            }
        }
    }

    public void h() {
        List<Presenter> listN = n();
        if (listN == null) {
            return;
        }
        for (Presenter presenter : listN) {
            if (presenter instanceof b) {
                ((b) presenter).h();
            }
        }
    }
}
