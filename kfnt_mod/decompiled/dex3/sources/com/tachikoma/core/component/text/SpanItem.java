package com.tachikoma.core.component.text;

import android.content.Context;
import android.view.View;
import com.kwad.v8.V8Function;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SpanItem extends TKBase<View> {
    static final String STYLE_BOLD = "bold";
    static final String STYLE_BOLD_ITALIC = "bold_italic";
    static final String STYLE_ITALIC = "italic";

    @Deprecated
    static final String TYPE_ABSOLUTE_SIZE = "absoluteSize";
    static final String TYPE_BACKGROUND_COLOR = "backgroundColor";
    static final String TYPE_CLICK = "click";
    static final String TYPE_FONT_FAMILY = "fontFamily";
    static final String TYPE_FOREGROUND_COLOR = "foregroundColor";
    static final String TYPE_IMAGE = "image";
    static final String TYPE_STRIKE_THROUGH = "strikeThrough";

    @Deprecated
    static final String TYPE_TEXT_STYLE = "textStyle";
    static final String TYPE_UNDERLINE = "underLine";
    static final String TYPE_URL = "url";
    public String color;
    public int end;
    public String fontFamily;
    public int imageHeight;
    public String imageUrl;
    public int imageWidth;
    public int index;
    private V8Function mV8Function;
    public float marginLeft;
    public float marginRight;
    public int offsetX;
    public float offsetY;
    public int size;
    public String spanType;
    public int start;
    public String textStyle;
    public String url;

    public SpanItem(Context context, List<Object> list) {
        super(context, list);
    }

    @Override // com.tachikoma.core.component.TKBase
    protected View createViewInstance(Context context) {
        return null;
    }

    public V8Function getV8Function() {
        return this.mV8Function;
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        V8Proxy.release(this.mV8Function);
        this.mV8Function = null;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public void setSpanOnClickListener(V8Function v8Function) {
        if (V8Proxy.isV8Valid(v8Function)) {
            this.mV8Function = v8Function.twin();
        }
    }
}
