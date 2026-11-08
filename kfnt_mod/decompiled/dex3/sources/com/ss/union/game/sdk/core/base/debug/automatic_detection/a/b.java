package com.ss.union.game.sdk.core.base.debug.automatic_detection.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends BaseAdapter {
    private List<com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b> a;
    private a b;

    public interface a {
        void a(com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b bVar);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public b(List<com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b> list) {
        this.a = list;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.a != null) {
            return this.a.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        C0070b c0070b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_automatic_detection_parameter_item"), viewGroup, false);
            c0070b = new C0070b(view);
            view.setTag(c0070b);
        } else {
            c0070b = (C0070b) view.getTag();
        }
        final com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b bVar = this.a.get(i);
        c0070b.c.setText(bVar.a);
        c0070b.d.setText(bVar.a());
        if (i == getCount() - 1) {
            c0070b.e.setVisibility(8);
        } else {
            c0070b.e.setVisibility(0);
        }
        c0070b.b.setOnClickListener(new AntiShakeClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.a.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (b.this.b != null) {
                    b.this.b.a(bVar);
                }
            }
        }));
        return view;
    }

    /* renamed from: com.ss.union.game.sdk.core.base.debug.automatic_detection.a.b$b, reason: collision with other inner class name */
    private static class C0070b {
        View a;
        LinearLayout b = (LinearLayout) a("lg_detection_parameter_item_layout");
        TextView c = (TextView) a("lg_detection_parameter_tv_name");
        TextView d = (TextView) a("lg_detection_parameter_tv");
        View e = a("lg_detection_parameter_divider_view");

        C0070b(View view) {
            this.a = view;
        }

        <T extends View> T a(String str) {
            return (T) this.a.findViewById(ResourceUtils.getIdByName(str));
        }
    }
}
