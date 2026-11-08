package com.tachikoma.core.component.text;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.sigmob.sdk.common.Constants;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.utility.TKAsync;
import com.tachikoma.core.utility.TKColorUtil;
import com.tachikoma.core.utility.TKConverter;
import com.tachikoma.core.utility.UIThreadUtil;
import com.tachikoma.core.utility.V8Proxy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKSpan extends TKBase<View> {
    private static final float DP = Resources.getSystem().getDisplayMetrics().density;
    private static final String IMAGE_PLACE_HOLDER = " ";
    private Context mContext;
    private final Map<SpanItem, V8Function> mItemFunctions;
    private final List<JSContext.V8AssociateReference> mSpanAsoRefList;
    private final List<SpanItem> mSpanItems;
    private List<SpanItemData> mSpans;

    private static class SpanItemData {
        int mEnd;
        Object mSpan;
        int mStart;

        SpanItemData(Object obj, int i, int i2) {
            this.mSpan = obj;
            this.mStart = i;
            this.mEnd = i2;
        }
    }

    public TKSpan(Context context, List<Object> list) {
        super(context, list);
        this.mSpanAsoRefList = new ArrayList();
        this.mItemFunctions = new HashMap();
        this.mContext = context;
        this.mSpanItems = new ArrayList();
        this.mSpans = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SpannableString appendImagePlaceHolder(SpannableString spannableString, SpanItem spanItem) {
        StringBuilder sb;
        Object obj;
        int i;
        int i2;
        String string = spannableString.toString();
        if (spanItem.index < 1) {
            sb = new StringBuilder();
            sb.append(IMAGE_PLACE_HOLDER);
        } else if (spanItem.index > string.length()) {
            sb = new StringBuilder();
            sb.append(string);
            string = IMAGE_PLACE_HOLDER;
        } else {
            sb = new StringBuilder();
            sb.append(string.substring(0, spanItem.index));
            sb.append(IMAGE_PLACE_HOLDER);
            string = string.substring(spanItem.index);
        }
        sb.append(string);
        SpannableString spannableString2 = new SpannableString(sb.toString());
        if (this.mSpans.size() > 0) {
            for (SpanItemData spanItemData : this.mSpans) {
                if (spanItem.index <= spanItemData.mStart || spanItem.index >= spanItemData.mEnd) {
                    if (spanItem.index > spanItemData.mEnd) {
                        obj = spanItemData.mSpan;
                        i = spanItemData.mStart;
                        i2 = spanItemData.mEnd;
                        spannableString2.setSpan(obj, i, i2, 17);
                    } else if (spanItem.index < spanItemData.mStart || spanItem.index == spanItemData.mStart) {
                        obj = spanItemData.mSpan;
                        i = spanItemData.mStart + 1;
                        i2 = spanItemData.mEnd + 1;
                        spannableString2.setSpan(obj, i, i2, 17);
                    } else if (spanItem.index == spanItemData.mEnd) {
                    }
                }
                obj = spanItemData.mSpan;
                i = spanItemData.mStart;
                i2 = spanItemData.mEnd + 1;
                spannableString2.setSpan(obj, i, i2, 17);
            }
        }
        return spannableString2;
    }

    private void fetchBitmapAndSetImageSpan(final SpanItem spanItem, final SpannableString spannableString, final TextView textView) {
        TKAsync.execute(new Runnable() { // from class: com.tachikoma.core.component.text.TKSpan.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                InputStream inputStream = null;
                try {
                    try {
                        try {
                            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(spanItem.imageUrl).openConnection();
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.connect();
                            if (httpURLConnection.getResponseCode() == 200) {
                                InputStream inputStream2 = httpURLConnection.getInputStream();
                                try {
                                    final Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream2);
                                    UIThreadUtil.runOnUiThread(new Runnable() { // from class: com.tachikoma.core.component.text.TKSpan.2.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            SpannableString spannableStringAppendImagePlaceHolder = TKSpan.this.appendImagePlaceHolder(spannableString, spanItem);
                                            TKSpan.this.setImageSpan(new BitmapDrawable(TKSpan.this.mContext.getResources(), bitmapDecodeStream), spannableStringAppendImagePlaceHolder, spanItem);
                                            if (textView != null) {
                                                textView.setText(spannableStringAppendImagePlaceHolder);
                                            }
                                        }
                                    });
                                    inputStream = inputStream2;
                                } catch (Exception e) {
                                    e = e;
                                    inputStream = inputStream2;
                                    Logger.logE("fetchSpanBitmap", e);
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    return;
                                } catch (Throwable th) {
                                    th = th;
                                    inputStream = inputStream2;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException e2) {
                                            Logger.logE("fetchSpanBitmap close io", e2);
                                        }
                                    }
                                    throw th;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e4) {
                    Logger.logE("fetchSpanBitmap close io", e4);
                }
            }
        });
    }

    private Drawable getSpanDrawable(String str) {
        BitmapDrawable bitmapDrawable;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String strConcat = getRootDir().concat(str);
            if (!new File(strConcat).exists()) {
                return null;
            }
            bitmapDrawable = new BitmapDrawable(BitmapFactory.decodeFile(strConcat));
            try {
                bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                return bitmapDrawable;
            } catch (Exception e) {
                e = e;
                Logger.logE("getSpanDrawable", e);
                return bitmapDrawable;
            }
        } catch (Exception e2) {
            e = e2;
            bitmapDrawable = null;
        }
    }

    private void setFontFamilySpan(SpannableString spannableString, SpanItem spanItem) {
        if (TextUtils.isEmpty(spanItem.fontFamily)) {
            return;
        }
        Typeface typeface = FontManager.getInstance().getTypeface(spanItem.fontFamily, 0, this.mContext.getAssets(), getRootDir().concat(spanItem.fontFamily));
        if (typeface != null) {
            CustomTypefaceSpan customTypefaceSpan = new CustomTypefaceSpan(null, typeface);
            spannableString.setSpan(customTypefaceSpan, spanItem.start, spanItem.end, 17);
            this.mSpans.add(new SpanItemData(customTypefaceSpan, spanItem.start, spanItem.end));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageSpan(Drawable drawable, SpannableString spannableString, SpanItem spanItem) {
        if (drawable == null) {
            return;
        }
        CenterImageSpan centerImageSpan = new CenterImageSpan(drawable, "");
        centerImageSpan.setImageRect((spanItem.imageWidth <= 0 || spanItem.imageHeight <= 0) ? drawable.getIntrinsicWidth() : (int) TKConverter.dp2px(this.mContext, spanItem.imageWidth), (spanItem.imageWidth <= 0 || spanItem.imageHeight <= 0) ? drawable.getIntrinsicHeight() : (int) TKConverter.dp2px(this.mContext, spanItem.imageHeight)).setMarginLeft(TKConverter.dp2px(this.mContext, spanItem.marginLeft)).setMarginRight(TKConverter.dp2px(this.mContext, spanItem.marginRight)).setOffsetY(TKConverter.dp2px(this.mContext, spanItem.offsetY));
        spannableString.setSpan(centerImageSpan, spanItem.index, spanItem.index + 1, 17);
    }

    private void setSizeSpan(SpannableString spannableString, SpanItem spanItem) {
        if (spanItem.size <= 0) {
            return;
        }
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan((int) (spanItem.size * DP));
        spannableString.setSpan(absoluteSizeSpan, spanItem.start, spanItem.end, 17);
        this.mSpans.add(new SpanItemData(absoluteSizeSpan, spanItem.start, spanItem.end));
    }

    private void setStyleSpan(SpannableString spannableString, SpanItem spanItem) {
        StyleSpan styleSpan = new StyleSpan("bold".equals(spanItem.textStyle) ? 1 : "bold_italic".equals(spanItem.textStyle) ? 3 : "italic".equals(spanItem.textStyle) ? 2 : 0);
        spannableString.setSpan(styleSpan, spanItem.start, spanItem.end, 17);
        this.mSpans.add(new SpanItemData(styleSpan, spanItem.start, spanItem.end));
    }

    public void addSpan(V8Object v8Object) {
        JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
        this.mSpanItems.add((SpanItem) associateReference.nativeObject);
        this.mSpanAsoRefList.add(associateReference);
    }

    @Override // com.tachikoma.core.component.TKBase
    protected View createViewInstance(Context context) {
        return null;
    }

    public CharSequence getSpannableString(String str, TextView textView) {
        List<SpanItemData> list;
        SpanItemData spanItemData;
        SpannableString spannableString = new SpannableString(str);
        for (final SpanItem spanItem : this.mSpanItems) {
            switch (spanItem.spanType) {
                case "url":
                    URLSpan uRLSpan = new URLSpan(spanItem.url);
                    spannableString.setSpan(uRLSpan, spanItem.start, spanItem.end, 17);
                    list = this.mSpans;
                    spanItemData = new SpanItemData(uRLSpan, spanItem.start, spanItem.end);
                    list.add(spanItemData);
                    break;
                case "backgroundColor":
                    if (TextUtils.isEmpty(spanItem.color)) {
                        break;
                    } else {
                        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor(TKColorUtil.rgba2argb(spanItem.color)));
                        spannableString.setSpan(backgroundColorSpan, spanItem.start, spanItem.end, 17);
                        list = this.mSpans;
                        spanItemData = new SpanItemData(backgroundColorSpan, spanItem.start, spanItem.end);
                        list.add(spanItemData);
                        break;
                    }
                case "foregroundColor":
                    if (TextUtils.isEmpty(spanItem.color)) {
                        break;
                    } else {
                        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor(TKColorUtil.rgba2argb(spanItem.color)));
                        spannableString.setSpan(foregroundColorSpan, spanItem.start, spanItem.end, 17);
                        list = this.mSpans;
                        spanItemData = new SpanItemData(foregroundColorSpan, spanItem.start, spanItem.end);
                        list.add(spanItemData);
                        break;
                    }
                case "absoluteSize":
                    setSizeSpan(spannableString, spanItem);
                    break;
                case "textStyle":
                    setStyleSpan(spannableString, spanItem);
                    break;
                case "strikeThrough":
                    StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                    spannableString.setSpan(strikethroughSpan, spanItem.start, spanItem.end, 17);
                    list = this.mSpans;
                    spanItemData = new SpanItemData(strikethroughSpan, spanItem.start, spanItem.end);
                    list.add(spanItemData);
                    break;
                case "underLine":
                    UnderlineSpan underlineSpan = new UnderlineSpan();
                    spannableString.setSpan(underlineSpan, spanItem.start, spanItem.end, 17);
                    list = this.mSpans;
                    spanItemData = new SpanItemData(underlineSpan, spanItem.start, spanItem.end);
                    list.add(spanItemData);
                    break;
                case "image":
                    if (TextUtils.isEmpty(spanItem.imageUrl)) {
                        break;
                    } else if (spanItem.imageUrl.startsWith(Constants.HTTP)) {
                        fetchBitmapAndSetImageSpan(spanItem, spannableString, textView);
                        break;
                    } else {
                        spannableString = appendImagePlaceHolder(spannableString, spanItem);
                        setImageSpan(getSpanDrawable(spanItem.imageUrl), spannableString, spanItem);
                        break;
                    }
                case "fontFamily":
                    setFontFamilySpan(spannableString, spanItem);
                    setSizeSpan(spannableString, spanItem);
                    setStyleSpan(spannableString, spanItem);
                    break;
                case "click":
                    V8Function v8Function = spanItem.getV8Function();
                    if (V8Proxy.isV8Valid(v8Function)) {
                        V8Function v8FunctionTwin = v8Function.twin();
                        V8Function v8Function2 = this.mItemFunctions.get(spanItem);
                        if (v8Function2 != null) {
                            V8Proxy.release(v8Function2);
                        }
                        this.mItemFunctions.put(spanItem, v8FunctionTwin);
                        textView.setMovementMethod(LinkMovementMethod.getInstance());
                        spannableString.setSpan(new ClickableSpan() { // from class: com.tachikoma.core.component.text.TKSpan.1
                            @Override // android.text.style.ClickableSpan
                            public void onClick(@NonNull View view) {
                                V8Function v8Function3 = (V8Function) TKSpan.this.mItemFunctions.get(spanItem);
                                if (V8Proxy.isV8Valid(v8Function3)) {
                                    try {
                                        v8Function3.call((V8Object) null, new V8Array(v8Function3.getRuntime()));
                                    } catch (Throwable th) {
                                        TKExceptionDispatcher.reportException(TKSpan.this.mTKJSContext, th);
                                    }
                                }
                            }

                            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                            public void updateDrawState(@NonNull TextPaint textPaint) {
                                super.updateDrawState(textPaint);
                                if (spanItem.color != null) {
                                    textPaint.setColor(Color.parseColor(spanItem.color));
                                }
                                textPaint.setUnderlineText(false);
                            }
                        }, spanItem.start, spanItem.end, 17);
                        break;
                    } else {
                        break;
                    }
            }
        }
        return spannableString;
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        JSContext.V8AssociateReference.closeAllReference(this.mSpanAsoRefList.iterator());
        this.mSpanAsoRefList.clear();
        Iterator<Map.Entry<SpanItem, V8Function>> it = this.mItemFunctions.entrySet().iterator();
        while (it.hasNext()) {
            V8Proxy.release(it.next().getValue());
        }
        this.mItemFunctions.clear();
    }
}
