package com.bytedance.pangle.e.a;

import android.content.res.XmlResourceParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a implements XmlResourceParser {

    /* renamed from: a, reason: collision with root package name */
    c f4586a;

    /* renamed from: c, reason: collision with root package name */
    private f f4588c;
    private int[] d;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int[] k;
    private int l;
    private int m;
    private int n;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4587b = false;
    private C0140a e = new C0140a();

    @Override // android.util.AttributeSet
    public final int getAttributeListValue(int i, String[] strArr, int i2) {
        return 0;
    }

    @Override // android.util.AttributeSet
    public final int getAttributeListValue(String str, String str2, String[] strArr, int i) {
        return 0;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getAttributeType(int i) {
        return "CDATA";
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int getColumnNumber() {
        return -1;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final boolean getFeature(String str) {
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getInputEncoding() {
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final Object getProperty(String str) {
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final boolean isAttributeDefault(int i) {
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final boolean isEmptyElementTag() {
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final boolean isWhitespace() {
        return false;
    }

    public a() {
        a();
    }

    @Override // android.content.res.XmlResourceParser, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f4587b) {
            this.f4587b = false;
            c cVar = this.f4586a;
            if (cVar.f4592a != null) {
                try {
                    cVar.f4592a.close();
                } catch (IOException unused) {
                }
                cVar.a((InputStream) null);
            }
            this.f4586a = null;
            this.f4588c = null;
            this.d = null;
            C0140a c0140a = this.e;
            c0140a.f4590b = 0;
            c0140a.f4591c = 0;
            a();
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int next() throws XmlPullParserException, IOException {
        if (this.f4586a == null) {
            throw new XmlPullParserException("Parser is not opened.", this, null);
        }
        try {
            b();
            return this.g;
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int nextToken() {
        return next();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int nextTag() throws XmlPullParserException, IOException {
        int next = next();
        if (next == 4 && isWhitespace()) {
            next = next();
        }
        if (next == 2 || next == 3) {
            return next;
        }
        throw new XmlPullParserException("Expected start or end tag.", this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String nextText() throws XmlPullParserException, IOException {
        if (getEventType() != 2) {
            throw new XmlPullParserException("Parser must be on START_TAG to read next text.", this, null);
        }
        int next = next();
        if (next != 4) {
            if (next == 3) {
                return "";
            }
            throw new XmlPullParserException("Parser must be on START_TAG or TEXT to read text.", this, null);
        }
        String text = getText();
        if (next() == 3) {
            return text;
        }
        throw new XmlPullParserException("Event TEXT must be immediately followed by END_TAG.", this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final void require(int i, String str, String str2) throws XmlPullParserException {
        if (i == getEventType() && ((str == null || str.equals(getNamespace())) && (str2 == null || str2.equals(getName())))) {
            return;
        }
        throw new XmlPullParserException(TYPES[i] + " is expected.", this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int getDepth() {
        return this.e.f4591c - 1;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int getEventType() {
        return this.g;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int getLineNumber() {
        return this.h;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getName() {
        if (this.i == -1) {
            return null;
        }
        if (this.g == 2 || this.g == 3) {
            return this.f4588c.a(this.i);
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getText() {
        if (this.i == -1 || this.g != 4) {
            return null;
        }
        return this.f4588c.a(this.i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final char[] getTextCharacters(int[] iArr) {
        String text = getText();
        if (text == null) {
            return null;
        }
        iArr[0] = 0;
        iArr[1] = text.length();
        char[] cArr = new char[text.length()];
        text.getChars(0, text.length(), cArr, 0);
        return cArr;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getNamespace() {
        return this.f4588c.a(this.j);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getPrefix() {
        return this.f4588c.a(this.e.a(this.j));
    }

    @Override // org.xmlpull.v1.XmlPullParser, android.util.AttributeSet
    public final String getPositionDescription() {
        return "XML line #" + getLineNumber();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final int getNamespaceCount(int i) {
        C0140a c0140a = this.e;
        int i2 = 0;
        if (c0140a.f4590b == 0 || i < 0) {
            return 0;
        }
        if (i > c0140a.f4591c) {
            i = c0140a.f4591c;
        }
        int i3 = 0;
        while (i != 0) {
            int i4 = c0140a.f4589a[i2];
            i3 += i4;
            i2 += (i4 * 2) + 2;
            i--;
        }
        return i3;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getNamespacePrefix(int i) {
        return this.f4588c.a(this.e.a(i, true));
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getNamespaceUri(int i) {
        return this.f4588c.a(this.e.a(i, false));
    }

    @Override // android.util.AttributeSet
    public final String getClassAttribute() {
        if (this.m == -1) {
            return null;
        }
        return this.f4588c.a(this.k[c(this.m) + 2]);
    }

    @Override // android.util.AttributeSet
    public final String getIdAttribute() {
        if (this.l == -1) {
            return null;
        }
        return this.f4588c.a(this.k[c(this.l) + 2]);
    }

    @Override // android.util.AttributeSet
    public final int getIdAttributeResourceValue(int i) {
        if (this.l == -1) {
            return i;
        }
        int iC = c(this.l);
        return this.k[iC + 3] != 1 ? i : this.k[iC + 4];
    }

    @Override // android.util.AttributeSet
    public final int getStyleAttribute() {
        if (this.n == -1) {
            return 0;
        }
        return this.k[c(this.n) + 4];
    }

    @Override // org.xmlpull.v1.XmlPullParser, android.util.AttributeSet
    public final int getAttributeCount() {
        if (this.g != 2) {
            return -1;
        }
        return this.k.length / 5;
    }

    @Override // android.content.res.XmlResourceParser, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet
    public final String getAttributeNamespace(int i) {
        int i2 = this.k[c(i) + 0];
        return i2 == -1 ? "" : this.f4588c.a(i2);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getAttributePrefix(int i) {
        int iA = this.e.a(this.k[c(i) + 0]);
        return iA == -1 ? "" : this.f4588c.a(iA);
    }

    @Override // org.xmlpull.v1.XmlPullParser, android.util.AttributeSet
    public final String getAttributeName(int i) {
        int i2 = this.k[c(i) + 1];
        return i2 == -1 ? "" : this.f4588c.a(i2);
    }

    @Override // android.util.AttributeSet
    public final int getAttributeNameResource(int i) {
        int i2 = this.k[c(i) + 1];
        if (this.d == null || i2 < 0 || i2 >= this.d.length) {
            return 0;
        }
        return this.d[i2];
    }

    public final int a(int i) {
        return this.k[c(i) + 3];
    }

    public final int b(int i) {
        return this.k[c(i) + 4];
    }

    @Override // org.xmlpull.v1.XmlPullParser, android.util.AttributeSet
    public final String getAttributeValue(int i) {
        int iC = c(i);
        if (this.k[iC + 3] != 3) {
            return "";
        }
        return this.f4588c.a(this.k[iC + 2]);
    }

    @Override // android.util.AttributeSet
    public final boolean getAttributeBooleanValue(int i, boolean z) {
        return getAttributeIntValue(i, z ? 1 : 0) != 0;
    }

    @Override // android.util.AttributeSet
    public final float getAttributeFloatValue(int i, float f) {
        int iC = c(i);
        return this.k[iC + 3] == 4 ? Float.intBitsToFloat(this.k[iC + 4]) : f;
    }

    @Override // android.util.AttributeSet
    public final int getAttributeIntValue(int i, int i2) {
        int iC = c(i);
        int i3 = this.k[iC + 3];
        return (i3 < 16 || i3 > 31) ? i2 : this.k[iC + 4];
    }

    @Override // android.util.AttributeSet
    public final int getAttributeUnsignedIntValue(int i, int i2) {
        return getAttributeIntValue(i, i2);
    }

    @Override // android.util.AttributeSet
    public final int getAttributeResourceValue(int i, int i2) {
        int iC = c(i);
        return this.k[iC + 3] == 1 ? this.k[iC + 4] : i2;
    }

    @Override // org.xmlpull.v1.XmlPullParser, android.util.AttributeSet
    public final String getAttributeValue(String str, String str2) {
        int iA = a(str, str2);
        if (iA == -1) {
            return null;
        }
        return getAttributeValue(iA);
    }

    @Override // android.util.AttributeSet
    public final boolean getAttributeBooleanValue(String str, String str2, boolean z) {
        int iA = a(str, str2);
        return iA == -1 ? z : getAttributeBooleanValue(iA, z);
    }

    @Override // android.util.AttributeSet
    public final float getAttributeFloatValue(String str, String str2, float f) {
        int iA = a(str, str2);
        return iA == -1 ? f : getAttributeFloatValue(iA, f);
    }

    @Override // android.util.AttributeSet
    public final int getAttributeIntValue(String str, String str2, int i) {
        int iA = a(str, str2);
        return iA == -1 ? i : getAttributeIntValue(iA, i);
    }

    @Override // android.util.AttributeSet
    public final int getAttributeUnsignedIntValue(String str, String str2, int i) {
        int iA = a(str, str2);
        return iA == -1 ? i : getAttributeUnsignedIntValue(iA, i);
    }

    @Override // android.util.AttributeSet
    public final int getAttributeResourceValue(String str, String str2, int i) {
        int iA = a(str, str2);
        return iA == -1 ? i : getAttributeResourceValue(iA, i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final void setInput(Reader reader) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final String getNamespace(String str) {
        throw new RuntimeException("Method is not supported.");
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final void setProperty(String str, Object obj) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public final void setFeature(String str, boolean z) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    private final int c(int i) {
        if (this.g != 2) {
            throw new IndexOutOfBoundsException("Current event is not START_TAG.");
        }
        int i2 = i * 5;
        if (i2 < this.k.length) {
            return i2;
        }
        throw new IndexOutOfBoundsException("Invalid attribute index (" + i + ").");
    }

    private final int a(String str, String str2) {
        int iA;
        if (this.f4588c == null || str2 == null || (iA = this.f4588c.a(str2)) == -1) {
            return -1;
        }
        int iA2 = str != null ? this.f4588c.a(str) : -1;
        int i = 0;
        while (i != this.k.length) {
            int i2 = i + 1;
            if (iA == this.k[i2] && (iA2 == -1 || iA2 == this.k[i + 0])) {
                return i / 5;
            }
            i = i2;
        }
        return -1;
    }

    private final void a() {
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = null;
        this.l = -1;
        this.m = -1;
        this.n = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x012e, code lost:
    
        throw new java.io.IOException("Invalid resource ids size (" + r3 + ").");
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0273, code lost:
    
        throw new java.io.IOException("Invalid chunk type (" + r7 + ").");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void b() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 629
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.e.a.a.b():void");
    }

    /* renamed from: com.bytedance.pangle.e.a.a$a, reason: collision with other inner class name */
    static final class C0140a {

        /* renamed from: a, reason: collision with root package name */
        int[] f4589a = new int[32];

        /* renamed from: b, reason: collision with root package name */
        int f4590b;

        /* renamed from: c, reason: collision with root package name */
        int f4591c;

        public final void a() {
            b();
            int i = this.f4590b;
            this.f4589a[i] = 0;
            this.f4589a[i + 1] = 0;
            this.f4590b += 2;
            this.f4591c++;
        }

        final void b() {
            int length = this.f4589a.length - this.f4590b;
            if (length <= 2) {
                int[] iArr = new int[(this.f4589a.length + length) * 2];
                System.arraycopy(this.f4589a, 0, iArr, 0, this.f4590b);
                this.f4589a = iArr;
            }
        }

        final int a(int i) {
            if (this.f4590b == 0) {
                return -1;
            }
            int i2 = this.f4590b - 1;
            for (int i3 = this.f4591c; i3 != 0; i3--) {
                i2 -= 2;
                for (int i4 = this.f4589a[i2]; i4 != 0; i4--) {
                    if (this.f4589a[i2 + 1] == i) {
                        return this.f4589a[i2];
                    }
                    i2 -= 2;
                }
            }
            return -1;
        }

        final int a(int i, boolean z) {
            if (this.f4590b == 0 || i < 0) {
                return -1;
            }
            int i2 = 0;
            for (int i3 = this.f4591c; i3 != 0; i3--) {
                int i4 = this.f4589a[i2];
                if (i < i4) {
                    int i5 = i2 + (i * 2) + 1;
                    if (!z) {
                        i5++;
                    }
                    return this.f4589a[i5];
                }
                i -= i4;
                i2 += (i4 * 2) + 2;
            }
            return -1;
        }
    }
}
