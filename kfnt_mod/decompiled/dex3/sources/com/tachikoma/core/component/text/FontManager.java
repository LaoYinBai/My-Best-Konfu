package com.tachikoma.core.component.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.SparseArray;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static FontManager sFontManagerInstance;
    private Map<String, FontFamily> mFontCache = new HashMap();

    private static class FontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        private FontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }

        public Typeface getTypeface(int i) {
            return this.mTypefaceSparseArray.get(i);
        }

        public void setTypeface(int i, Typeface typeface) {
            this.mTypefaceSparseArray.put(i, typeface);
        }
    }

    private FontManager() {
    }

    private static Typeface createTypeface(String str, int i, AssetManager assetManager, String str2) {
        Typeface typefaceCreateFromFile;
        try {
            return Typeface.createFromAsset(assetManager, FONTS_ASSET_PATH + str);
        } catch (RuntimeException unused) {
            return (TextUtils.isEmpty(str2) || !new File(str2).exists() || (typefaceCreateFromFile = Typeface.createFromFile(str2)) == null) ? Typeface.create(str, i) : typefaceCreateFromFile;
        }
    }

    public static FontManager getInstance() {
        if (sFontManagerInstance == null) {
            sFontManagerInstance = new FontManager();
        }
        return sFontManagerInstance;
    }

    public Typeface getTypeface(String str, int i, AssetManager assetManager, String str2) {
        FontFamily fontFamily = this.mFontCache.get(str);
        if (fontFamily == null) {
            fontFamily = new FontFamily();
            this.mFontCache.put(str, fontFamily);
        }
        Typeface typeface = fontFamily.getTypeface(i);
        if (typeface == null && (typeface = createTypeface(str, i, assetManager, str2)) != null) {
            fontFamily.setTypeface(i, typeface);
        }
        return typeface;
    }

    public void setTypeface(String str, int i, Typeface typeface) {
        if (typeface != null) {
            FontFamily fontFamily = this.mFontCache.get(str);
            if (fontFamily == null) {
                fontFamily = new FontFamily();
                this.mFontCache.put(str, fontFamily);
            }
            fontFamily.setTypeface(i, typeface);
        }
    }
}
