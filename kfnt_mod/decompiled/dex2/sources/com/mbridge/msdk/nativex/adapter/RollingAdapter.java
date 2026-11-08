package com.mbridge.msdk.nativex.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.mbridge.msdk.nativex.view.MBNativeRollView;
import com.mbridge.msdk.out.Frame;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RollingAdapter extends PagerAdapter {
    private List<Frame> a;
    private List<View> b = new ArrayList();
    private MBNativeRollView.a c;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public final void a(MBNativeRollView.a aVar) {
        this.c = aVar;
    }

    public RollingAdapter(List<Frame> list) {
        this.a = new ArrayList();
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View viewA;
        if (this.c == null || this.a == null || this.a.size() <= 0 || (viewA = this.c.a(this.a.get(i), i)) == null) {
            return null;
        }
        this.b.add(viewA);
        viewGroup.addView(viewA);
        return viewA;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.b.get(i));
    }
}
