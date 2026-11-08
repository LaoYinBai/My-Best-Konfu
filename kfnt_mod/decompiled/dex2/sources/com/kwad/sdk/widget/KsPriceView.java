package com.kwad.sdk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.kwad.sdk.R;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsPriceView extends TextView implements b {
    private static String b = "¥%s 到手约 ¥%s";
    private static String c = "¥%s";
    private a a;
    private String d;
    private String e;

    private static class a {
        private int a;
        private int b;
        private int c;
        private int d;

        private a() {
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.c;
        }

        public int c() {
            return this.b;
        }

        public int d() {
            return this.d;
        }
    }

    public KsPriceView(Context context) {
        super(context);
        this.a = new a();
        a(context, (AttributeSet) null, 0);
    }

    public KsPriceView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new a();
        a(context, attributeSet, 0);
    }

    public KsPriceView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new a();
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public KsPriceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new a();
        a(context, attributeSet, i);
    }

    private static float a(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    @Nullable
    private static SpannableString a(String str, @Nullable String str2, a aVar) {
        String str3;
        Object[] objArr;
        if (str2 == null) {
            str3 = c;
            objArr = new Object[]{str};
        } else {
            str3 = b;
            objArr = new Object[]{str, str2};
        }
        String str4 = String.format(str3, objArr);
        SpannableString spannableString = new SpannableString(str4);
        int iIndexOf = str4.indexOf(str) - 1;
        if (iIndexOf < 0) {
            return null;
        }
        int length = str.length() + 1 + iIndexOf;
        spannableString.setSpan(new ForegroundColorSpan(aVar.a()), iIndexOf, length, 18);
        spannableString.setSpan(new AbsoluteSizeSpan(aVar.c()), iIndexOf, length, 18);
        if (str2 != null) {
            int iLastIndexOf = str4.lastIndexOf(str2) - 1;
            int length2 = str2.length() + 1 + iLastIndexOf;
            spannableString.setSpan(new ForegroundColorSpan(aVar.b()), iLastIndexOf, length2, 18);
            spannableString.setSpan(new AbsoluteSizeSpan(aVar.d()), iLastIndexOf, length2, 18);
            spannableString.setSpan(new StrikethroughSpan(), iLastIndexOf, length2, 18);
        }
        return spannableString;
    }

    private void a(Context context, @Nullable AttributeSet attributeSet, int i) {
        setMaxLines(1);
        this.a.a = context.getResources().getColor(R.color.ksad_reward_main_color);
        this.a.b = context.getResources().getDimensionPixelSize(R.dimen.ksad_reward_order_price_size);
        this.a.c = context.getResources().getColor(R.color.ksad_reward_original_price);
        this.a.d = context.getResources().getDimensionPixelSize(R.dimen.ksad_reward_order_original_price_size);
    }

    @Override // com.kwad.sdk.widget.b
    public void a(c cVar) {
        this.a.a = cVar.a();
        a(this.d, this.e);
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        this.d = str;
        this.e = str2;
        if (a(str2) <= 0.0f) {
            this.e = null;
        }
        setTextColor(this.a.a());
        SpannableString spannableStringA = a(str, this.e, this.a);
        if (spannableStringA != null) {
            setText(spannableStringA);
        }
    }
}
