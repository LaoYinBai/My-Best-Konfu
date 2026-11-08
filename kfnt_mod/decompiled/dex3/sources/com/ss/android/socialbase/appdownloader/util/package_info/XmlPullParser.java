package com.ss.android.socialbase.appdownloader.util.package_info;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface XmlPullParser {
    public static final int END_DOCUMENT = 1;
    public static final int START_TAG = 2;
    public static final String[] TYPES = {"START_DOCUMENT", "END_DOCUMENT", "START_TAG", "END_TAG", "TEXT", "CDSECT", "ENTITY_REF", "IGNORABLE_WHITESPACE", "PROCESSING_INSTRUCTION", "COMMENT", "DOCDECL"};

    int getAttributeCount();

    String getAttributeName(int i);

    String getAttributeValue(int i);

    int getColumnNumber();

    int getEventType() throws XmlPullParserException;

    int getLineNumber();

    String getName();

    String getNamespace();

    String getPositionDescription();

    String getText();

    boolean isWhitespace() throws XmlPullParserException;

    int next() throws IOException, XmlPullParserException;
}
