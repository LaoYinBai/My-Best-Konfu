package com.ss.union.game.sdk.feedback.a;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.feedback.module.d;
import com.ss.union.game.sdk.feedback.view.FlowPhotoCard;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends BaseAdapter {
    private static final int a = 0;
    private static final int b = 1;
    private d c;

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

    public a(d dVar) {
        this.c = dVar;
    }

    public void a(d dVar) {
        this.c = dVar;
        notifyDataSetChanged();
    }

    public void a() {
        if (this.c.i != null) {
            Iterator<com.ss.union.game.sdk.feedback.module.a> it = this.c.i.iterator();
            while (it.hasNext()) {
                it.next().f = false;
            }
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        if (this.c.i != null) {
            return this.c.i.size() + 1;
        }
        return 1;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) == 0) {
            return a(view, viewGroup);
        }
        return a(i, view, viewGroup);
    }

    private View a(View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = a(viewGroup, "lg_user_feedback_detail_content_item");
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        a(bVar);
        return view;
    }

    private void a(b bVar) {
        bVar.a.setText(this.c.f);
        bVar.c.setText(DateUtils.dateToString(new Date(this.c.a()), DateUtils.FORMAT_ONE));
        a(this.c.h, bVar.b);
    }

    private View a(int i, View view, ViewGroup viewGroup) {
        C0096a c0096a;
        if (view == null) {
            view = a(viewGroup, "lg_user_feedback_detail_message_item");
            c0096a = new C0096a(view);
            view.setTag(c0096a);
        } else {
            c0096a = (C0096a) view.getTag();
        }
        a(c0096a, this.c.i.get(i - 1));
        return view;
    }

    private void a(C0096a c0096a, com.ss.union.game.sdk.feedback.module.a aVar) {
        c0096a.a.setBackgroundColor(Color.parseColor(aVar.f ? "#14FFCA00" : "#FFFFFF"));
        c0096a.b.setImageResource(ResourceUtils.getDrawableIdByName(aVar.a() == 2 ? "lg_feedback_avatar_myself" : "lg_feedback_avatar_customer_service"));
        c0096a.c.setText(aVar.d);
        c0096a.f.setText(DateUtils.dateToString(new Date(aVar.b()), DateUtils.FORMAT_ONE));
        c0096a.d.setText(aVar.e);
        a(aVar.g, c0096a.e);
    }

    private void a(List<com.ss.union.game.sdk.feedback.module.b> list, FlowPhotoCard flowPhotoCard) {
        if (list == null || list.size() == 0) {
            flowPhotoCard.setVisibility(8);
            return;
        }
        flowPhotoCard.setVisibility(0);
        ArrayList arrayList = new ArrayList();
        Iterator<com.ss.union.game.sdk.feedback.module.b> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().e);
        }
        flowPhotoCard.setData(arrayList);
    }

    private View a(ViewGroup viewGroup, String str) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(ResourceUtils.getLayoutIdByName(str), viewGroup, false);
    }

    private static class b {
        TextView a;
        FlowPhotoCard b;
        TextView c;

        b(View view) {
            this.a = (TextView) a(view, "lg_user_feedback_detail_content_item_content");
            this.b = (FlowPhotoCard) a(view, "lg_user_feedback_detail_content_item_image_container");
            this.c = (TextView) a(view, "lg_user_feedback_detail_content_item_time");
        }

        <T extends View> T a(View view, String str) {
            return (T) view.findViewById(ResourceUtils.getIdByName(str));
        }
    }

    /* renamed from: com.ss.union.game.sdk.feedback.a.a$a, reason: collision with other inner class name */
    private static class C0096a {
        View a;
        ImageView b;
        TextView c;
        TextView d;
        FlowPhotoCard e;
        TextView f;

        C0096a(View view) {
            this.a = a(view, "lg_feedback_message_item_root_view");
            this.b = (ImageView) a(view, "lg_feedback_detail_answer_avatar");
            this.c = (TextView) a(view, "lg_feedback_detail_answer_name");
            this.f = (TextView) a(view, "lg_feedback_detail_answer_time");
            this.d = (TextView) a(view, "lg_feedback_detail_answer_content");
            this.e = (FlowPhotoCard) a(view, "lg_feedback_detail_answer_image_container");
        }

        <T extends View> T a(View view, String str) {
            return (T) view.findViewById(ResourceUtils.getIdByName(str));
        }
    }
}
