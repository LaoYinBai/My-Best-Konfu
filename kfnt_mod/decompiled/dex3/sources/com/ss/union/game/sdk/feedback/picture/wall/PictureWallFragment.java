package com.ss.union.game.sdk.feedback.picture.wall;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.engine.GlideException;
import com.ss.union.game.sdk.core.glide.request.RequestListener;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PictureWallFragment extends BaseFragment {
    public static final String a = "status_color";
    public static final String b = "picture_paths";
    public static final String c = "init_position";
    public static final String d = "show_delete";
    private static final int e = ResourceUtils.getIdByName("lg_feedback_picture_wall_picture_id");
    private ViewPager f;
    private View g;
    private View h;
    private View i;
    private TextView j;
    private Object q;
    private a s;
    private boolean k = false;
    private List<String> l = new ArrayList();
    private List<String> m = new ArrayList();
    private Integer n = null;
    private int o = 0;
    private Runnable p = null;
    private int r = -1;
    private List<com.ss.union.game.sdk.feedback.picture.wall.a> t = new ArrayList();

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_feedback_fragment_picture_wall";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isShowStatusBar() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isStatusBarDarkMode() {
        return false;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String statusBarColor() {
        return "#B2000000";
    }

    public static void a(boolean z, int i, ArrayList<String> arrayList, com.ss.union.game.sdk.feedback.picture.wall.a aVar) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(d, z);
        bundle.putInt(c, i);
        bundle.putStringArrayList(b, arrayList);
        PictureWallFragment pictureWallFragmentA = a(bundle);
        pictureWallFragmentA.a(aVar);
        new OperationBuilder(pictureWallFragmentA).animation(OperationBuilder.Animation.RIGHT).transparentBackground().cancelable(true).show();
    }

    public static PictureWallFragment a(Bundle bundle) {
        PictureWallFragment pictureWallFragment = new PictureWallFragment();
        pictureWallFragment.setArguments(bundle);
        return pictureWallFragment;
    }

    public void a(com.ss.union.game.sdk.feedback.picture.wall.a aVar) {
        this.t.add(aVar);
    }

    public void a() {
        try {
            Iterator<com.ss.union.game.sdk.feedback.picture.wall.a> it = this.t.iterator();
            while (it.hasNext()) {
                it.next().onResult(this.l, this.m);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            back();
            return;
        }
        this.l = arguments.getStringArrayList(b);
        if (this.l == null || this.l.isEmpty()) {
            back();
            return;
        }
        if (arguments.containsKey(a)) {
            this.n = Integer.valueOf(arguments.getInt(a, Color.parseColor("#ffffff")));
        }
        this.o = arguments.getInt(c, 0);
        this.k = arguments.getBoolean(d, false);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.f = findViewById(ResourceUtils.getIdByName("lg_picture_wall_viewpager"));
        this.g = findViewById(ResourceUtils.getIdByName("lg_picture_wall_indicator_bar"));
        this.h = findViewById(ResourceUtils.getIdByName("lg_feedback_wall_back"));
        this.i = findViewById(ResourceUtils.getIdByName("lg_feedback_wall_delete"));
        this.j = (TextView) findViewById(ResourceUtils.getIdByName("lg_feedback_wall_tv_indicator"));
        this.s = new a(this.l);
        this.f.setAdapter(this.s);
        this.f.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment.1
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                PictureWallFragment.this.a(i);
            }
        });
        if (this.g.getParent() instanceof ViewGroup) {
            fitStatusBar((ViewGroup) this.g.getParent());
        }
        a(this.o);
        this.f.setCurrentItem(this.o);
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PictureWallFragment.this.c();
            }
        });
        this.i.setVisibility(this.k ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.q instanceof View) {
            try {
                b bVar = new b((View) this.q);
                if (bVar.a != null) {
                    String str = (String) bVar.a.getTag(e);
                    this.l.remove(str);
                    this.m.add(str);
                    if (this.l != null && !this.l.isEmpty()) {
                        if (this.r != -1) {
                            a(this.r);
                        }
                        this.s.a(this.l);
                        return;
                    }
                    back();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"SetTextI18n"})
    public void a(int i) {
        this.j.setText((i + 1) + "/" + this.l.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final View view) {
        if (view == null) {
            return;
        }
        if (this.p != null) {
            view.removeCallbacks(this.p);
        }
        this.p = new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    PictureWallFragment.this.p = null;
                    if (new b(view).a() + UIUtils.dip2Px(100.0f) < UIUtils.getScreenHeight()) {
                        PictureWallFragment.this.d();
                    } else {
                        PictureWallFragment.this.e();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
        view.postDelayed(this.p, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.g.setBackgroundColor(Color.parseColor("#000000"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.g.setBackgroundColor(Color.parseColor("#B2000000"));
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PictureWallFragment.this.back();
            }
        });
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a();
    }

    private static class b {
        private ImageView a;
        private RelativeLayout b;

        b(View view) {
            this.a = (ImageView) view.findViewById(ResourceUtils.getIdByName("lg_feedback_wall_iv_detail"));
            this.b = (RelativeLayout) view.findViewById(ResourceUtils.getIdByName("lg_container_reload"));
        }

        public int a() {
            return this.a.getHeight();
        }
    }

    private class a extends PagerAdapter {
        private List<String> b;

        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public a(List<String> list) {
            this.b = list;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_feedback_picture_wall_item"), viewGroup, false);
            viewGroup.addView(viewInflate);
            a(viewInflate, this.b.get(i));
            return viewInflate;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(final b bVar, final String str) {
            Glide.with(bVar.a).load(str).dontAnimate().listener(new RequestListener<Drawable>() { // from class: com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment.a.1
                @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    bVar.b.setVisibility(0);
                    bVar.b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment.a.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            bVar.b.setVisibility(8);
                            a.this.a(bVar, str);
                        }
                    });
                    return false;
                }

                @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    bVar.b.setVisibility(8);
                    return false;
                }
            }).into(bVar.a);
        }

        private void a(View view, String str) {
            b bVar = new b(view);
            bVar.a.setTag(PictureWallFragment.e, str);
            a(bVar, str);
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
            super.setPrimaryItem(viewGroup, i, obj);
            PictureWallFragment.this.q = obj;
            PictureWallFragment.this.r = i;
            if (obj instanceof View) {
                PictureWallFragment.this.a((View) obj);
            }
        }

        public int getCount() {
            return this.b.size();
        }

        public void a(List<String> list) {
            this.b = list;
            notifyDataSetChanged();
        }
    }
}
