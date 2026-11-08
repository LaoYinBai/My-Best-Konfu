package com.tachikoma.core.component.text;

import android.content.Context;
import android.graphics.Color;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.utility.TKColorUtil;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKMarqueeText extends TKBase<MarqueeTextView> {
    public String text;

    public TKMarqueeText(Context context, List<Object> list) {
        super(context, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public MarqueeTextView createViewInstance(Context context) {
        return new MarqueeTextView(context);
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        if (getView() != null) {
            getView().stopMarquee();
        }
        super.onDestroy();
    }

    public void setColor(String str) {
        getView().setTextColor(Color.parseColor(TKColorUtil.rgba2argb(str)));
    }

    public void setFontSize(int i) {
        getView().setTextSize(1, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setFontWeight(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -1039745817(0xffffffffc206bce7, float:-33.684475)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L1b
            r1 = 3029637(0x2e3a85, float:4.245426E-39)
            if (r0 == r1) goto L11
            goto L25
        L11:
            java.lang.String r0 = "bold"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L25
            r5 = 0
            goto L26
        L1b:
            java.lang.String r0 = "normal"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L25
            r5 = 1
            goto L26
        L25:
            r5 = -1
        L26:
            switch(r5) {
                case 0: goto L3e;
                case 1: goto L2a;
                default: goto L29;
            }
        L29:
            goto L51
        L2a:
            android.view.View r5 = r4.getView()
            com.tachikoma.core.component.text.MarqueeTextView r5 = (com.tachikoma.core.component.text.MarqueeTextView) r5
            android.view.View r0 = r4.getView()
            com.tachikoma.core.component.text.MarqueeTextView r0 = (com.tachikoma.core.component.text.MarqueeTextView) r0
            android.graphics.Typeface r0 = r0.getTypeface()
            r5.setTypeface(r0, r2)
            goto L51
        L3e:
            android.view.View r5 = r4.getView()
            com.tachikoma.core.component.text.MarqueeTextView r5 = (com.tachikoma.core.component.text.MarqueeTextView) r5
            android.view.View r0 = r4.getView()
            com.tachikoma.core.component.text.MarqueeTextView r0 = (com.tachikoma.core.component.text.MarqueeTextView) r0
            android.graphics.Typeface r0 = r0.getTypeface()
            r5.setTypeface(r0, r3)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.text.TKMarqueeText.setFontWeight(java.lang.String):void");
    }

    public void setText(String str) {
        this.text = str;
        getDomNode().getYogaNode().dirty();
        getView().setText(this.text);
    }
}
