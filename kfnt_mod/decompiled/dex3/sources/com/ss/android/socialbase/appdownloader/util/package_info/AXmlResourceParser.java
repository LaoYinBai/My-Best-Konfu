package com.ss.android.socialbase.appdownloader.util.package_info;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class AXmlResourceParser implements XmlResourceParser {
    private int[] m_attributes;
    private int m_classAttribute;
    private boolean m_decreaseDepth;
    private int m_event;
    private int m_idAttribute;
    private int m_lineNumber;
    private int m_name;
    private int m_namespaceUri;
    private IntReader m_reader;
    private int[] m_resourceIDs;
    private StringBlock m_strings;
    private int m_styleAttribute;
    private boolean m_operational = false;
    private NamespaceStack m_namespaces = new NamespaceStack();

    public int getAttributeListValue(int i, String[] strArr, int i2) {
        return 0;
    }

    public int getAttributeListValue(String str, String str2, String[] strArr, int i) {
        return 0;
    }

    public String getAttributeType(int i) {
        return "CDATA";
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public int getColumnNumber() {
        return -1;
    }

    public boolean getFeature(String str) {
        return false;
    }

    public String getInputEncoding() {
        return null;
    }

    public Object getProperty(String str) {
        return null;
    }

    public boolean isAttributeDefault(int i) {
        return false;
    }

    public boolean isEmptyElementTag() throws XmlPullParserException {
        return false;
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public boolean isWhitespace() throws XmlPullParserException {
        return false;
    }

    public AXmlResourceParser() {
        resetEventInfo();
    }

    public void open(InputStream inputStream) throws IOException {
        close();
        if (inputStream != null) {
            this.m_reader = new IntReader(inputStream, false);
        }
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlResourceParser
    public void close() throws IOException {
        if (this.m_operational) {
            this.m_operational = false;
            this.m_reader.close();
            this.m_reader = null;
            this.m_strings = null;
            this.m_resourceIDs = null;
            this.m_namespaces.reset();
            resetEventInfo();
        }
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public int next() throws IOException, XmlPullParserException {
        if (this.m_reader == null) {
            throw new XmlPullParserException("Parser is not opened.", this, (Throwable) null);
        }
        try {
            doNext();
            return this.m_event;
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    public int nextToken() throws IOException, XmlPullParserException {
        return next();
    }

    public int nextTag() throws IOException, XmlPullParserException {
        int next = next();
        if (next == 4 && isWhitespace()) {
            next = next();
        }
        if (next == 2 || next == 3) {
            return next;
        }
        throw new XmlPullParserException("Expected start or end tag.", this, (Throwable) null);
    }

    public String nextText() throws IOException, XmlPullParserException {
        if (getEventType() != 2) {
            throw new XmlPullParserException("Parser must be on START_TAG to read next text.", this, (Throwable) null);
        }
        int next = next();
        if (next != 4) {
            if (next == 3) {
                return "";
            }
            throw new XmlPullParserException("Parser must be on START_TAG or TEXT to read text.", this, (Throwable) null);
        }
        String text = getText();
        if (next() == 3) {
            return text;
        }
        throw new XmlPullParserException("Event TEXT must be immediately followed by END_TAG.", this, (Throwable) null);
    }

    public void require(int i, String str, String str2) throws IOException, XmlPullParserException {
        if (i == getEventType() && ((str == null || str.equals(getNamespace())) && (str2 == null || str2.equals(getName())))) {
            return;
        }
        throw new XmlPullParserException(TYPES[i] + " is expected.", this, (Throwable) null);
    }

    public int getDepth() {
        return this.m_namespaces.getDepth() - 1;
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public int getEventType() throws XmlPullParserException {
        return this.m_event;
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public int getLineNumber() {
        return this.m_lineNumber;
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public String getName() {
        if (this.m_name == -1 || !(this.m_event == 2 || this.m_event == 3)) {
            return null;
        }
        return this.m_strings.getString(this.m_name);
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public String getText() {
        if (this.m_name == -1 || this.m_event != 4) {
            return null;
        }
        return this.m_strings.getString(this.m_name);
    }

    public char[] getTextCharacters(int[] iArr) {
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

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public String getNamespace() {
        return this.m_strings.getString(this.m_namespaceUri);
    }

    public String getPrefix() {
        return this.m_strings.getString(this.m_namespaces.findPrefix(this.m_namespaceUri));
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public String getPositionDescription() {
        return "XML line #" + getLineNumber();
    }

    public int getNamespaceCount(int i) throws XmlPullParserException {
        return this.m_namespaces.getAccumulatedCount(i);
    }

    public String getNamespacePrefix(int i) throws XmlPullParserException {
        return this.m_strings.getString(this.m_namespaces.getPrefix(i));
    }

    public String getNamespaceUri(int i) throws XmlPullParserException {
        return this.m_strings.getString(this.m_namespaces.getUri(i));
    }

    public String getClassAttribute() {
        if (this.m_classAttribute == -1) {
            return null;
        }
        return this.m_strings.getString(this.m_attributes[getAttributeOffset(this.m_classAttribute) + 2]);
    }

    public String getIdAttribute() {
        if (this.m_idAttribute == -1) {
            return null;
        }
        return this.m_strings.getString(this.m_attributes[getAttributeOffset(this.m_idAttribute) + 2]);
    }

    public int getIdAttributeResourceValue(int i) {
        if (this.m_idAttribute == -1) {
            return i;
        }
        int attributeOffset = getAttributeOffset(this.m_idAttribute);
        return this.m_attributes[attributeOffset + 3] != 1 ? i : this.m_attributes[attributeOffset + 4];
    }

    public int getStyleAttribute() {
        if (this.m_styleAttribute == -1) {
            return 0;
        }
        return this.m_attributes[getAttributeOffset(this.m_styleAttribute) + 4];
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet, com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public int getAttributeCount() {
        if (this.m_event != 2) {
            return -1;
        }
        return this.m_attributes.length / 5;
    }

    public String getAttributeNamespace(int i) {
        int i2 = this.m_attributes[getAttributeOffset(i) + 0];
        return i2 == -1 ? "" : this.m_strings.getString(i2);
    }

    public String getAttributePrefix(int i) {
        int iFindPrefix = this.m_namespaces.findPrefix(this.m_attributes[getAttributeOffset(i) + 0]);
        return iFindPrefix == -1 ? "" : this.m_strings.getString(iFindPrefix);
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet, com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public String getAttributeName(int i) {
        int i2 = this.m_attributes[getAttributeOffset(i) + 1];
        return i2 == -1 ? "" : this.m_strings.getString(i2);
    }

    public int getAttributeNameResource(int i) {
        int i2 = this.m_attributes[getAttributeOffset(i) + 1];
        if (this.m_resourceIDs == null || i2 < 0 || i2 >= this.m_resourceIDs.length) {
            return 0;
        }
        return this.m_resourceIDs[i2];
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet
    public int getAttributeValueType(int i) {
        return this.m_attributes[getAttributeOffset(i) + 3];
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet
    public int getAttributeValueData(int i) {
        return this.m_attributes[getAttributeOffset(i) + 4];
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet, com.ss.android.socialbase.appdownloader.util.package_info.XmlPullParser
    public String getAttributeValue(int i) {
        int attributeOffset = getAttributeOffset(i);
        if (this.m_attributes[attributeOffset + 3] == 3) {
            return this.m_strings.getString(this.m_attributes[attributeOffset + 2]);
        }
        int i2 = this.m_attributes[attributeOffset + 4];
        return "";
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet
    public boolean getAttributeBooleanValue(int i, boolean z) {
        return getAttributeIntValue(i, z ? 1 : 0) != 0;
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet
    public float getAttributeFloatValue(int i, float f) {
        int attributeOffset = getAttributeOffset(i);
        return this.m_attributes[attributeOffset + 3] == 4 ? Float.intBitsToFloat(this.m_attributes[attributeOffset + 4]) : f;
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet
    public int getAttributeIntValue(int i, int i2) {
        int attributeOffset = getAttributeOffset(i);
        int i3 = this.m_attributes[attributeOffset + 3];
        return (i3 < 16 || i3 > 31) ? i2 : this.m_attributes[attributeOffset + 4];
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet
    public int getAttributeUnsignedIntValue(int i, int i2) {
        return getAttributeIntValue(i, i2);
    }

    @Override // com.ss.android.socialbase.appdownloader.util.package_info.AttributeSet
    public int getAttributeResourceValue(int i, int i2) {
        int attributeOffset = getAttributeOffset(i);
        return this.m_attributes[attributeOffset + 3] == 1 ? this.m_attributes[attributeOffset + 4] : i2;
    }

    public String getAttributeValue(String str, String str2) {
        int iFindAttribute = findAttribute(str, str2);
        if (iFindAttribute == -1) {
            return null;
        }
        return getAttributeValue(iFindAttribute);
    }

    public boolean getAttributeBooleanValue(String str, String str2, boolean z) {
        int iFindAttribute = findAttribute(str, str2);
        return iFindAttribute == -1 ? z : getAttributeBooleanValue(iFindAttribute, z);
    }

    public float getAttributeFloatValue(String str, String str2, float f) {
        int iFindAttribute = findAttribute(str, str2);
        return iFindAttribute == -1 ? f : getAttributeFloatValue(iFindAttribute, f);
    }

    public int getAttributeIntValue(String str, String str2, int i) {
        int iFindAttribute = findAttribute(str, str2);
        return iFindAttribute == -1 ? i : getAttributeIntValue(iFindAttribute, i);
    }

    public int getAttributeUnsignedIntValue(String str, String str2, int i) {
        int iFindAttribute = findAttribute(str, str2);
        return iFindAttribute == -1 ? i : getAttributeUnsignedIntValue(iFindAttribute, i);
    }

    public int getAttributeResourceValue(String str, String str2, int i) {
        int iFindAttribute = findAttribute(str, str2);
        return iFindAttribute == -1 ? i : getAttributeResourceValue(iFindAttribute, i);
    }

    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    public void setInput(Reader reader) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    public String getNamespace(String str) {
        throw new RuntimeException("Method is not supported.");
    }

    public void setProperty(String str, Object obj) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        throw new XmlPullParserException("Method is not supported.");
    }

    final StringBlock getStrings() {
        return this.m_strings;
    }

    private final int getAttributeOffset(int i) {
        if (this.m_event != 2) {
            throw new IndexOutOfBoundsException("Current event is not START_TAG.");
        }
        int i2 = i * 5;
        if (i2 < this.m_attributes.length) {
            return i2;
        }
        throw new IndexOutOfBoundsException("Invalid attribute index (" + i + ").");
    }

    private final int findAttribute(String str, String str2) {
        int iFind;
        if (this.m_strings == null || str2 == null || (iFind = this.m_strings.find(str2)) == -1) {
            return -1;
        }
        int iFind2 = str != null ? this.m_strings.find(str) : -1;
        int i = 0;
        while (i != this.m_attributes.length) {
            int i2 = i + 1;
            if (iFind == this.m_attributes[i2] && (iFind2 == -1 || iFind2 == this.m_attributes[i + 0])) {
                return i / 5;
            }
            i = i2;
        }
        return -1;
    }

    private final void resetEventInfo() {
        this.m_event = -1;
        this.m_lineNumber = -1;
        this.m_name = -1;
        this.m_namespaceUri = -1;
        this.m_attributes = null;
        this.m_idAttribute = -1;
        this.m_classAttribute = -1;
        this.m_styleAttribute = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0097, code lost:
    
        throw new java.io.IOException("Invalid resource ids size (" + r2 + ").");
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0198, code lost:
    
        throw new java.io.IOException("Invalid chunk type (" + r5 + ").");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void doNext() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.util.package_info.AXmlResourceParser.doNext():void");
    }

    private static final class NamespaceStack {
        private int[] m_data = new int[32];
        private int m_dataLength;
        private int m_depth;

        public final void reset() {
            this.m_dataLength = 0;
            this.m_depth = 0;
        }

        public final int getCurrentCount() {
            if (this.m_dataLength == 0) {
                return 0;
            }
            return this.m_data[this.m_dataLength - 1];
        }

        public final int getAccumulatedCount(int i) {
            int i2 = 0;
            if (this.m_dataLength == 0 || i < 0) {
                return 0;
            }
            if (i > this.m_depth) {
                i = this.m_depth;
            }
            int i3 = 0;
            while (i != 0) {
                int i4 = this.m_data[i2];
                i3 += i4;
                i2 += (i4 * 2) + 2;
                i--;
            }
            return i3;
        }

        public final void push(int i, int i2) {
            if (this.m_depth == 0) {
                increaseDepth();
            }
            ensureDataCapacity(2);
            int i3 = this.m_dataLength - 1;
            int i4 = this.m_data[i3];
            int i5 = (i3 - 1) - (i4 * 2);
            int i6 = i4 + 1;
            this.m_data[i5] = i6;
            this.m_data[i3] = i;
            this.m_data[i3 + 1] = i2;
            this.m_data[i3 + 2] = i6;
            this.m_dataLength += 2;
        }

        public final boolean pop() {
            int i;
            int i2;
            if (this.m_dataLength == 0 || (i2 = this.m_data[(i = this.m_dataLength - 1)]) == 0) {
                return false;
            }
            int i3 = i2 - 1;
            int i4 = i - 2;
            this.m_data[i4] = i3;
            this.m_data[i4 - ((i3 * 2) + 1)] = i3;
            this.m_dataLength -= 2;
            return true;
        }

        public final int getPrefix(int i) {
            return get(i, true);
        }

        public final int getUri(int i) {
            return get(i, false);
        }

        public final int findPrefix(int i) {
            return find(i, false);
        }

        public final int getDepth() {
            return this.m_depth;
        }

        public final void increaseDepth() {
            ensureDataCapacity(2);
            int i = this.m_dataLength;
            this.m_data[i] = 0;
            this.m_data[i + 1] = 0;
            this.m_dataLength += 2;
            this.m_depth++;
        }

        public final void decreaseDepth() {
            if (this.m_dataLength != 0) {
                int i = this.m_dataLength - 1;
                int i2 = this.m_data[i] * 2;
                if ((i - 1) - i2 != 0) {
                    this.m_dataLength -= i2 + 2;
                    this.m_depth--;
                }
            }
        }

        private void ensureDataCapacity(int i) {
            int length = this.m_data.length - this.m_dataLength;
            if (length <= i) {
                int[] iArr = new int[(this.m_data.length + length) * 2];
                System.arraycopy(this.m_data, 0, iArr, 0, this.m_dataLength);
                this.m_data = iArr;
            }
        }

        private final int find(int i, boolean z) {
            if (this.m_dataLength == 0) {
                return -1;
            }
            int i2 = this.m_dataLength - 1;
            for (int i3 = this.m_depth; i3 != 0; i3--) {
                i2 -= 2;
                for (int i4 = this.m_data[i2]; i4 != 0; i4--) {
                    if (z) {
                        if (this.m_data[i2] == i) {
                            return this.m_data[i2 + 1];
                        }
                    } else if (this.m_data[i2 + 1] == i) {
                        return this.m_data[i2];
                    }
                    i2 -= 2;
                }
            }
            return -1;
        }

        private final int get(int i, boolean z) {
            if (this.m_dataLength == 0 || i < 0) {
                return -1;
            }
            int i2 = 0;
            for (int i3 = this.m_depth; i3 != 0; i3--) {
                int i4 = this.m_data[i2];
                if (i < i4) {
                    int i5 = i2 + (i * 2) + 1;
                    if (!z) {
                        i5++;
                    }
                    return this.m_data[i5];
                }
                i -= i4;
                i2 += (i4 * 2) + 2;
            }
            return -1;
        }
    }
}
