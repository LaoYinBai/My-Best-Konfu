package com.ss.union.game.sdk.common.ui.floatview;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.util.UIUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NoScrollFrameLayout extends FrameLayout {
    private int a;
    private int b;
    private MotionEvent c;
    private boolean d;
    private TouchProxy e;
    private List<IConfigurationChangedListener> f;

    public NoScrollFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public NoScrollFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoScrollFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
        this.f = new ArrayList();
    }

    public void registerUIChangedListener(IConfigurationChangedListener iConfigurationChangedListener) {
        this.f.add(iConfigurationChangedListener);
    }

    public void unRegisterUIChangedListener(IConfigurationChangedListener iConfigurationChangedListener) {
        this.f.remove(iConfigurationChangedListener);
    }

    public void setSlideEnabled(boolean z) {
        this.d = z;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.d) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            this.a = (int) motionEvent.getRawX();
            this.b = (int) motionEvent.getRawY();
            this.c = MotionEvent.obtain(motionEvent);
        } else if (motionEvent.getAction() == 2) {
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            int iDip2Px = UIUtils.dip2Px(8.0f);
            if (Math.abs(rawX - this.a) > iDip2Px || Math.abs(rawY - this.b) > iDip2Px) {
                return true;
            }
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.c = null;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.d) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.c != null) {
            this.e.onTouchEvent(this, this.c);
            this.c = null;
        }
        this.e.onTouchEvent(this, motionEvent);
        return true;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator<IConfigurationChangedListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onConfigurationChanged(configuration);
        }
    }

    public void setTouchProxy(TouchProxy touchProxy) {
        this.e = touchProxy;
    }
}
