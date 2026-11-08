package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(29)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected Typeface createFromInputStream(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001b A[Catch: IOException -> 0x0069, PHI: r4
  0x001b: PHI (r4v5 android.graphics.fonts.FontFamily$Builder) = (r4v3 android.graphics.fonts.FontFamily$Builder), (r4v1 android.graphics.fonts.FontFamily$Builder) binds: [B:14:0x004c, B:7:0x0019] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #0 {IOException -> 0x0069, blocks: (B:5:0x000d, B:8:0x001b, B:24:0x005c, B:28:0x0065, B:27:0x0061, B:29:0x0068), top: B:43:0x000d, inners: #2 }] */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r11, @androidx.annotation.Nullable android.os.CancellationSignal r12, @androidx.annotation.NonNull androidx.core.provider.FontsContractCompat.FontInfo[] r13, int r14) throws java.lang.Throwable {
        /*
            r10 = this;
            android.content.ContentResolver r11 = r11.getContentResolver()
            int r0 = r13.length
            r1 = 0
            r2 = 0
            r4 = r2
            r3 = 0
        L9:
            if (r3 >= r0) goto L6c
            r5 = r13[r3]
            android.net.Uri r6 = r5.getUri()     // Catch: java.io.IOException -> L69
            java.lang.String r7 = "r"
            android.os.ParcelFileDescriptor r6 = r11.openFileDescriptor(r6, r7, r12)     // Catch: java.io.IOException -> L69
            if (r6 != 0) goto L1f
            if (r6 == 0) goto L69
        L1b:
            r6.close()     // Catch: java.io.IOException -> L69
            goto L69
        L1f:
            android.graphics.fonts.Font$Builder r7 = new android.graphics.fonts.Font$Builder     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            int r8 = r5.getWeight()     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            android.graphics.fonts.Font$Builder r7 = r7.setWeight(r8)     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            boolean r8 = r5.isItalic()     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            android.graphics.fonts.Font$Builder r7 = r7.setSlant(r8)     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            int r5 = r5.getTtcIndex()     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            android.graphics.fonts.Font$Builder r5 = r7.setTtcIndex(r5)     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            android.graphics.fonts.Font r5 = r5.build()     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            if (r4 != 0) goto L49
            android.graphics.fonts.FontFamily$Builder r7 = new android.graphics.fonts.FontFamily$Builder     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            r7.<init>(r5)     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
            r4 = r7
            goto L4c
        L49:
            r4.addFont(r5)     // Catch: java.lang.Throwable -> L4f java.lang.Throwable -> L52
        L4c:
            if (r6 == 0) goto L69
            goto L1b
        L4f:
            r5 = move-exception
            r7 = r2
            goto L58
        L52:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L54
        L54:
            r7 = move-exception
            r9 = r7
            r7 = r5
            r5 = r9
        L58:
            if (r6 == 0) goto L68
            if (r7 == 0) goto L65
            r6.close()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L69
            goto L68
        L60:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch: java.io.IOException -> L69
            goto L68
        L65:
            r6.close()     // Catch: java.io.IOException -> L69
        L68:
            throw r5     // Catch: java.io.IOException -> L69
        L69:
            int r3 = r3 + 1
            goto L9
        L6c:
            if (r4 != 0) goto L6f
            return r2
        L6f:
            android.graphics.fonts.FontStyle r11 = new android.graphics.fonts.FontStyle
            r12 = r14 & 1
            if (r12 == 0) goto L78
            r12 = 700(0x2bc, float:9.81E-43)
            goto L7a
        L78:
            r12 = 400(0x190, float:5.6E-43)
        L7a:
            r13 = r14 & 2
            if (r13 == 0) goto L7f
            r1 = 1
        L7f:
            r11.<init>(r12, r1)
            android.graphics.Typeface$CustomFallbackBuilder r12 = new android.graphics.Typeface$CustomFallbackBuilder
            android.graphics.fonts.FontFamily r13 = r4.build()
            r12.<init>(r13)
            android.graphics.Typeface$CustomFallbackBuilder r11 = r12.setStyle(r11)
            android.graphics.Typeface r11 = r11.build()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi29Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) throws IOException {
        FontFamily.Builder builder = null;
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.getEntries()) {
            try {
                Font fontBuild = new Font.Builder(resources, fontFileResourceEntry.getResourceId()).setWeight(fontFileResourceEntry.getWeight()).setSlant(fontFileResourceEntry.isItalic() ? 1 : 0).setTtcIndex(fontFileResourceEntry.getTtcIndex()).setFontVariationSettings(fontFileResourceEntry.getVariationSettings()).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(fontBuild);
                } else {
                    builder.addFont(fontBuild);
                }
            } catch (IOException unused) {
            }
        }
        if (builder == null) {
            return null;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? 700 : 400, (i & 2) != 0 ? 1 : 0)).build();
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        try {
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(new Font.Builder(resources, i).build()).build()).setStyle(new FontStyle((i2 & 1) != 0 ? 700 : 400, (i2 & 2) != 0 ? 1 : 0)).build();
        } catch (IOException unused) {
            return null;
        }
    }
}
