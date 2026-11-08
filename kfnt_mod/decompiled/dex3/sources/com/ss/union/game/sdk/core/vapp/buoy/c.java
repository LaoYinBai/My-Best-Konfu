package com.ss.union.game.sdk.core.vapp.buoy;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import com.ss.union.game.sdk.common.ui.floatview.FloatIntent;
import com.ss.union.game.sdk.common.ui.floatview.FloatViewManager;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements b<d> {
    private Activity a;
    private boolean b = false;
    private f c;

    @Override // com.ss.union.game.sdk.core.vapp.buoy.b
    public void a(d dVar) {
    }

    public c(Activity activity) {
        this.a = activity;
    }

    f a(Activity activity) {
        return (f) FloatViewManager.getInstance().create(new FloatIntent(activity, f.class));
    }

    @Override // com.ss.union.game.sdk.core.vapp.buoy.b
    public void b(d dVar) {
        if (!this.b) {
            this.c = a(this.a);
            this.b = true;
        }
        if (a(dVar, this.c)) {
            this.c.a();
        } else {
            this.c.b();
        }
    }

    @Override // com.ss.union.game.sdk.core.vapp.buoy.b
    public void c(d dVar) throws JSONException {
        if (this.b) {
            if (a(dVar, this.c)) {
                dVar.onDestroyed();
                com.ss.union.game.sdk.core.vapp.d.a();
            }
            if (this.c != null) {
                this.c.onDestroyed();
            }
            this.b = false;
        }
    }

    private boolean a(d dVar, f fVar) {
        if (dVar.getRootView() == null || fVar.getRootView() == null) {
            return false;
        }
        return a(dVar.getRootView(), fVar.getRootView());
    }

    private boolean a(View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (iArr[0] == 0 || iArr[1] == 0) {
            return false;
        }
        Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + view.getMeasuredWidth(), iArr[1] + view.getMeasuredHeight());
        view2.getLocationOnScreen(iArr);
        if (iArr[0] == 0 || iArr[1] == 0) {
            return false;
        }
        return rect.intersect(new Rect(iArr[0], iArr[1], iArr[0] + view2.getMeasuredWidth(), iArr[1] + view2.getMeasuredHeight()));
    }
}
