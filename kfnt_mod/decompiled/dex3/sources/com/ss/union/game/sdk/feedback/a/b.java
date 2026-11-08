package com.ss.union.game.sdk.feedback.a;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends BaseAdapter {
    private int a = -1;
    private List<String> b;
    private Context c;
    private LayoutInflater d;
    private AdapterView.OnItemClickListener e;
    private Typeface f;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public b(Context context) {
        this.c = context;
        this.d = LayoutInflater.from(context);
        try {
            this.f = ResourcesCompat.getFont(context, ResourceUtils.getResIdByType("font", "pingfang_medium"));
        } catch (Throwable unused) {
            this.f = Typeface.DEFAULT;
        }
    }

    public void a(List<String> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        if (this.a < -1 || this.a >= getCount()) {
            return null;
        }
        return (String) getItem(this.a);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.b.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.d.inflate(ResourceUtils.getLayoutIdByName("lg_item_user_feedback_question"), viewGroup, false);
            aVar = new a();
            aVar.a = view.findViewById(ResourceUtils.getIdByName("id_item_user_feedback_question_container"));
            aVar.b = (TextView) view.findViewById(ResourceUtils.getIdByName("id_item_user_feedback_question_content"));
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        String str = this.b.get(i);
        if (str != null) {
            aVar.b.setText(str);
        }
        if (i == this.a) {
            aVar.b.setTypeface(this.f);
            aVar.b.setTextColor(Color.parseColor("#FF9900"));
            aVar.a.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_bg_user_feedback_question_type_select"));
        } else {
            aVar.b.setTypeface(Typeface.DEFAULT);
            aVar.b.setTextColor(Color.parseColor("#333333"));
            aVar.a.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_bg_user_feedback_question_type_normal"));
        }
        aVar.a.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.a.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (b.this.a != i) {
                    b.this.a = i;
                    b.this.notifyDataSetChanged();
                    if (b.this.e != null) {
                        b.this.e.onItemClick(null, view2, i, i);
                    }
                }
            }
        });
        ViewGroup.LayoutParams layoutParams = aVar.a.getLayoutParams();
        layoutParams.height = this.c.getResources().getDimensionPixelSize(ResourceUtils.getDimenIdByName("lg_user_feedback_question_height"));
        aVar.a.setLayoutParams(layoutParams);
        return view;
    }

    public static class a {
        private View a;
        private TextView b;
    }
}
