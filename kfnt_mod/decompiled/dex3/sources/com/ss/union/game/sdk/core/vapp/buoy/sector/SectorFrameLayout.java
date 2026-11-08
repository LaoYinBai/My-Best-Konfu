package com.ss.union.game.sdk.core.vapp.buoy.sector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.util.UIUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SectorFrameLayout extends RelativeLayout {
    private static final int a = 1;
    private static final int b = 80;
    private static final int c = 181;
    private static final int d = 104;
    private boolean e;
    private View f;
    private a g;
    private boolean h;

    public interface a {
        void a();

        void b();
    }

    public SectorFrameLayout(@NonNull Context context) {
        super(context);
        this.e = false;
        this.h = true;
    }

    public SectorFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = false;
        this.h = true;
    }

    public SectorFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = false;
        this.h = true;
    }

    private List<View> getCollapseViews() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) != this.f) {
                arrayList.add(getChildAt(i));
            }
        }
        return arrayList;
    }

    public void setMainView(View view) {
        this.f = view;
    }

    public void a() {
        if (this.e) {
            b(50);
        } else {
            a(50);
        }
    }

    public void b() {
        this.e = false;
        a(getCollapseViews(), 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<View> list, int i) {
        Iterator<View> it = list.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(i);
        }
    }

    public void setDirectionRight(boolean z) {
        this.h = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r23) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.vapp.buoy.sector.SectorFrameLayout.a(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(int r20) {
        /*
            r19 = this;
            r0 = r19
            java.util.List r1 = r19.getCollapseViews()
            r2 = 0
            r3 = 0
        L8:
            int r4 = r1.size()
            if (r3 >= r4) goto Le6
            android.animation.AnimatorSet r4 = new android.animation.AnimatorSet
            r4.<init>()
            r5 = 4599959831253042311(0x3fd657184ae74487, double:0.3490658503988659)
            int r7 = r3 * 2
            r8 = 1
            int r7 = r7 + r8
            double r9 = (double) r7
            double r9 = r9 * r5
            double r5 = java.lang.Math.cos(r9)
            double r5 = -r5
            double r9 = java.lang.Math.sin(r9)
            double r9 = -r9
            boolean r7 = r0.h
            if (r7 == 0) goto L51
            if (r3 != 0) goto L3f
            r5 = 4580687790476533049(0x3f91df46a2529d39, double:0.017453292519943295)
            double r9 = java.lang.Math.cos(r5)
            double r9 = -r9
            double r5 = java.lang.Math.sin(r5)
            double r5 = -r5
            goto L7a
        L3f:
            if (r3 != r8) goto L75
            r5 = 4608967030507783303(0x3ff657184ae74487, double:1.3962634015954636)
            double r9 = java.lang.Math.cos(r5)
            double r9 = -r9
            double r5 = java.lang.Math.sin(r5)
            double r5 = -r5
            goto L7a
        L51:
            if (r3 != 0) goto L63
            r5 = 4614295957872890450(0x400945b9e188d252, double:3.159045946109736)
            double r9 = java.lang.Math.cos(r5)
            double r9 = -r9
            double r5 = java.lang.Math.sin(r5)
            double r5 = -r5
            goto L7a
        L63:
            if (r3 != r8) goto L75
            r5 = 4610853493908324221(0x3ffd0ad2c7c63f7d, double:1.8151424220741028)
            double r9 = java.lang.Math.cos(r5)
            double r9 = -r9
            double r5 = java.lang.Math.sin(r5)
            double r5 = -r5
            goto L7a
        L75:
            r17 = r5
            r5 = r9
            r9 = r17
        L7a:
            int r7 = r19.c(r20)
            double r11 = (double) r7
            double r9 = r9 * r11
            int r7 = r19.c(r20)
            double r11 = (double) r7
            double r5 = r5 * r11
            r7 = 3
            android.animation.Animator[] r7 = new android.animation.Animator[r7]
            java.lang.Object r11 = r1.get(r3)
            java.lang.String r12 = "translationX"
            r13 = 2
            float[] r14 = new float[r13]
            float r15 = (float) r9
            r14[r2] = r15
            r15 = 4598175219545276416(0x3fd0000000000000, double:0.25)
            double r9 = r9 * r15
            float r9 = (float) r9
            r14[r8] = r9
            android.animation.ObjectAnimator r9 = android.animation.ObjectAnimator.ofFloat(r11, r12, r14)
            r7[r2] = r9
            java.lang.Object r9 = r1.get(r3)
            java.lang.String r10 = "translationY"
            float[] r11 = new float[r13]
            float r12 = (float) r5
            r11[r2] = r12
            double r5 = r5 * r15
            float r5 = (float) r5
            r11[r8] = r5
            android.animation.ObjectAnimator r5 = android.animation.ObjectAnimator.ofFloat(r9, r10, r11)
            r7[r8] = r5
            java.lang.Object r5 = r1.get(r3)
            java.lang.String r6 = "alpha"
            float[] r8 = new float[r13]
            r8 = {x00e8: FILL_ARRAY_DATA , data: [1065353216, 0} // fill-array
            android.animation.ObjectAnimator r5 = android.animation.ObjectAnimator.ofFloat(r5, r6, r8)
            r8 = 200(0xc8, double:9.9E-322)
            android.animation.ObjectAnimator r5 = r5.setDuration(r8)
            r7[r13] = r5
            r4.playTogether(r7)
            r4.setDuration(r8)
            r4.start()
            com.ss.union.game.sdk.core.vapp.buoy.sector.SectorFrameLayout$2 r5 = new com.ss.union.game.sdk.core.vapp.buoy.sector.SectorFrameLayout$2
            r5.<init>()
            r4.addListener(r5)
            int r3 = r3 + 1
            goto L8
        Le6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.vapp.buoy.sector.SectorFrameLayout.b(int):void");
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    private int c(int i) {
        return UIUtils.dip2Px(i);
    }
}
