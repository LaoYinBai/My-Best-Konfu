package com.ss.union.game.sdk.core.base.debug.automatic_detection.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends BaseAdapter {
    private static final int a = 0;
    private static final int b = 1;
    private com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a c;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    public a(com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a aVar) {
        this.c = aVar;
    }

    public void a(com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a aVar) {
        this.c = aVar;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        if (this.c.b == null) {
            return 1;
        }
        return this.c.b.size() + 1;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == 0) {
            return a(i, view, viewGroup);
        }
        return b(i, view, viewGroup);
    }

    private View a(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_automatic_detection_basic_info_item"), viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        a(bVar);
        return view;
    }

    private void a(b bVar) {
        bVar.a.setText(String.format(ResourceUtils.getString("lg_automatic_detection_id_format"), Long.valueOf(this.c.a)));
        if (this.c.b != null) {
            bVar.b.setText(String.format(ResourceUtils.getString("lg_automatic_detection_number_format"), Integer.valueOf(this.c.b.size())));
        }
        bVar.c.setText(String.format(ResourceUtils.getString("lg_automatic_detection_created_time_format"), this.c.a()));
        bVar.d.setText(String.format(ResourceUtils.getString("lg_automatic_detection_finished_time_format"), this.c.b()));
    }

    private View b(int i, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_automatic_detection_detection_item"), viewGroup, false);
            cVar = new c(view);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        a(cVar, i, this.c.b.get(i - 1));
        return view;
    }

    private void a(final c cVar, int i, final com.ss.union.game.sdk.core.base.debug.automatic_detection.c.c cVar2) {
        cVar.a.setText(cVar2.b);
        switch (cVar2.a()) {
            case PASS:
                cVar.b.setImageResource(ResourceUtils.getDrawableIdByName("lg_automatic_detection_pass"));
                cVar.c.setText(ResourceUtils.getString("lg_automatic_detection_state_pass"));
                break;
            case FAIL:
                cVar.b.setImageResource(ResourceUtils.getDrawableIdByName("lg_automatic_detection_fail"));
                cVar.c.setText(ResourceUtils.getString("lg_automatic_detection_state_fail"));
                break;
            default:
                cVar.b.setImageResource(ResourceUtils.getDrawableIdByName("lg_automatic_detection_un_handle"));
                cVar.c.setText(ResourceUtils.getString("lg_automatic_detection_state_un_handle"));
                break;
        }
        if (cVar2.c) {
            cVar.d.setRotation(180.0f);
            cVar.e.setVisibility(0);
        } else {
            cVar.d.setRotation(0.0f);
            cVar.e.setVisibility(8);
        }
        cVar.e.setText(cVar2.b());
        boolean z = cVar2.a() == LGDetectionConstant.DetectionState.FAIL;
        cVar.d.setVisibility(z ? 0 : 8);
        cVar.g.setVisibility(i != getCount() - 1 ? 0 : 8);
        if (z) {
            cVar.f.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.a.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (cVar2.c) {
                        cVar2.c = false;
                        cVar.e.setVisibility(8);
                        cVar.d.setRotation(0.0f);
                    } else {
                        cVar2.c = true;
                        cVar.e.setVisibility(0);
                        cVar.d.setRotation(180.0f);
                    }
                }
            });
        }
    }

    private static class b extends C0069a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;

        b(View view) {
            super(view);
            this.a = (TextView) a("lg_automatic_detection_basic_info_id");
            this.b = (TextView) a("lg_automatic_detection_basic_info_number");
            this.c = (TextView) a("lg_automatic_detection_basic_info_create_time");
            this.d = (TextView) a("lg_automatic_detection_basic_info_finish_time");
        }
    }

    private static class c extends C0069a {
        TextView a;
        ImageView b;
        TextView c;
        ImageView d;
        TextView e;
        LinearLayout f;
        View g;

        c(View view) {
            super(view);
            this.a = (TextView) a("lg_automatic_detection_item_name");
            this.b = (ImageView) a("lg_automatic_detection_item_iv_state");
            this.c = (TextView) a("lg_automatic_detection_item_tv_state");
            this.d = (ImageView) a("lg_automatic_detection_item_iv_expand");
            this.e = (TextView) a("lg_automatic_detection_item_tv_error_reason");
            this.f = (LinearLayout) a("lg_automatic_detection_item_title_layout");
            this.g = a("lg_automatic_detection_item_divider_view");
        }
    }

    /* renamed from: com.ss.union.game.sdk.core.base.debug.automatic_detection.a.a$a, reason: collision with other inner class name */
    private static class C0069a {
        private View a;

        C0069a(View view) {
            this.a = view;
        }

        <T extends View> T a(String str) {
            return (T) this.a.findViewById(ResourceUtils.getIdByName(str));
        }
    }
}
