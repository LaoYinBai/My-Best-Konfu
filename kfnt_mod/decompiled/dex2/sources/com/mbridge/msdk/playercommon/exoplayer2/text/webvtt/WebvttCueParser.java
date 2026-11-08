package com.mbridge.msdk.playercommon.exoplayer2.text.webvtt;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCue;
import com.mbridge.msdk.playercommon.exoplayer2.util.ParsableByteArray;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class WebvttCueParser {
    private static final char CHAR_AMPERSAND = '&';
    private static final char CHAR_GREATER_THAN = '>';
    private static final char CHAR_LESS_THAN = '<';
    private static final char CHAR_SEMI_COLON = ';';
    private static final char CHAR_SLASH = '/';
    private static final char CHAR_SPACE = ' ';
    public static final Pattern CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
    private static final String ENTITY_AMPERSAND = "amp";
    private static final String ENTITY_GREATER_THAN = "gt";
    private static final String ENTITY_LESS_THAN = "lt";
    private static final String ENTITY_NON_BREAK_SPACE = "nbsp";
    private static final int STYLE_BOLD = 1;
    private static final int STYLE_ITALIC = 2;
    private static final String TAG = "WebvttCueParser";
    private static final String TAG_BOLD = "b";
    private static final String TAG_CLASS = "c";
    private static final String TAG_ITALIC = "i";
    private static final String TAG_LANG = "lang";
    private static final String TAG_UNDERLINE = "u";
    private static final String TAG_VOICE = "v";
    private final StringBuilder textBuilder = new StringBuilder();

    public final boolean parseCue(ParsableByteArray parsableByteArray, WebvttCue.Builder builder, List<WebvttCssStyle> list) {
        String line = parsableByteArray.readLine();
        if (line == null) {
            return false;
        }
        Matcher matcher = CUE_HEADER_PATTERN.matcher(line);
        if (matcher.matches()) {
            return parseCue(null, matcher, parsableByteArray, builder, this.textBuilder, list);
        }
        String line2 = parsableByteArray.readLine();
        if (line2 == null) {
            return false;
        }
        Matcher matcher2 = CUE_HEADER_PATTERN.matcher(line2);
        if (matcher2.matches()) {
            return parseCue(line.trim(), matcher2, parsableByteArray, builder, this.textBuilder, list);
        }
        return false;
    }

    static void parseCueSettingsList(String str, WebvttCue.Builder builder) {
        Matcher matcher = CUE_SETTING_PATTERN.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            try {
                if ("line".equals(strGroup)) {
                    parseLineAttribute(strGroup2, builder);
                } else if ("align".equals(strGroup)) {
                    builder.setTextAlignment(parseTextAlignment(strGroup2));
                } else if ("position".equals(strGroup)) {
                    parsePositionAttribute(strGroup2, builder);
                } else if ("size".equals(strGroup)) {
                    builder.setWidth(WebvttParserUtil.parsePercentage(strGroup2));
                } else {
                    Log.w(TAG, "Unknown cue setting " + strGroup + ":" + strGroup2);
                }
            } catch (NumberFormatException unused) {
                Log.w(TAG, "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    static void parseCueText(String str, String str2, WebvttCue.Builder builder, List<WebvttCssStyle> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str2.length()) {
            char cCharAt = str2.charAt(i);
            if (cCharAt == '&') {
                i++;
                int iIndexOf = str2.indexOf(59, i);
                int iIndexOf2 = str2.indexOf(32, i);
                if (iIndexOf == -1) {
                    iIndexOf = iIndexOf2;
                } else if (iIndexOf2 != -1) {
                    iIndexOf = Math.min(iIndexOf, iIndexOf2);
                }
                if (iIndexOf != -1) {
                    applyEntity(str2.substring(i, iIndexOf), spannableStringBuilder);
                    if (iIndexOf == iIndexOf2) {
                        spannableStringBuilder.append(" ");
                    }
                    i = iIndexOf + 1;
                } else {
                    spannableStringBuilder.append(cCharAt);
                }
            } else if (cCharAt == '<') {
                int iFindEndOfTag = i + 1;
                if (iFindEndOfTag < str2.length()) {
                    boolean z = str2.charAt(iFindEndOfTag) == '/';
                    iFindEndOfTag = findEndOfTag(str2, iFindEndOfTag);
                    int i2 = iFindEndOfTag - 2;
                    boolean z2 = str2.charAt(i2) == '/';
                    int i3 = i + (z ? 2 : 1);
                    if (!z2) {
                        i2 = iFindEndOfTag - 1;
                    }
                    String strSubstring = str2.substring(i3, i2);
                    String tagName = getTagName(strSubstring);
                    if (tagName != null && isSupportedTag(tagName)) {
                        if (z) {
                            while (!arrayDeque.isEmpty()) {
                                StartTag startTag = (StartTag) arrayDeque.pop();
                                applySpansForTag(str, startTag, spannableStringBuilder, list, arrayList);
                                if (startTag.name.equals(tagName)) {
                                    break;
                                }
                            }
                        } else if (!z2) {
                            arrayDeque.push(StartTag.buildStartTag(strSubstring, spannableStringBuilder.length()));
                        }
                    }
                }
                i = iFindEndOfTag;
            } else {
                spannableStringBuilder.append(cCharAt);
                i++;
            }
        }
        while (!arrayDeque.isEmpty()) {
            applySpansForTag(str, (StartTag) arrayDeque.pop(), spannableStringBuilder, list, arrayList);
        }
        applySpansForTag(str, StartTag.buildWholeCueVirtualTag(), spannableStringBuilder, list, arrayList);
        builder.setText(spannableStringBuilder);
    }

    private static boolean parseCue(String str, Matcher matcher, ParsableByteArray parsableByteArray, WebvttCue.Builder builder, StringBuilder sb, List<WebvttCssStyle> list) {
        try {
            builder.setStartTime(WebvttParserUtil.parseTimestampUs(matcher.group(1))).setEndTime(WebvttParserUtil.parseTimestampUs(matcher.group(2)));
            parseCueSettingsList(matcher.group(3), builder);
            sb.setLength(0);
            while (true) {
                String line = parsableByteArray.readLine();
                if (!TextUtils.isEmpty(line)) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(line.trim());
                } else {
                    parseCueText(str, sb.toString(), builder, list);
                    return true;
                }
            }
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Skipping cue with bad header: " + matcher.group());
            return false;
        }
    }

    private static void parseLineAttribute(String str, WebvttCue.Builder builder) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setLineAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setLineAnchor(Integer.MIN_VALUE);
        }
        if (str.endsWith("%")) {
            builder.setLine(WebvttParserUtil.parsePercentage(str)).setLineType(0);
            return;
        }
        int i = Integer.parseInt(str);
        if (i < 0) {
            i--;
        }
        builder.setLine(i).setLineType(1);
    }

    private static void parsePositionAttribute(String str, WebvttCue.Builder builder) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setPositionAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setPositionAnchor(Integer.MIN_VALUE);
        }
        builder.setPosition(WebvttParserUtil.parsePercentage(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int parsePositionAnchor(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == r1) goto L3a
            r1 = -1074341483(0xffffffffbff6d995, float:-1.9285151)
            if (r0 == r1) goto L30
            r1 = 100571(0x188db, float:1.4093E-40)
            if (r0 == r1) goto L26
            r1 = 109757538(0x68ac462, float:5.219839E-35)
            if (r0 == r1) goto L1c
            goto L44
        L1c:
            java.lang.String r0 = "start"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L44
            r0 = 0
            goto L45
        L26:
            java.lang.String r0 = "end"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L44
            r0 = 3
            goto L45
        L30:
            java.lang.String r0 = "middle"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L44
            r0 = 2
            goto L45
        L3a:
            java.lang.String r0 = "center"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L44
            r0 = 1
            goto L45
        L44:
            r0 = -1
        L45:
            switch(r0) {
                case 0: goto L63;
                case 1: goto L62;
                case 2: goto L62;
                case 3: goto L61;
                default: goto L48;
            }
        L48:
            java.lang.String r0 = "WebvttCueParser"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid anchor value: "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            android.util.Log.w(r0, r5)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            return r5
        L61:
            return r2
        L62:
            return r3
        L63:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.parsePositionAnchor(java.lang.String):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.text.Layout.Alignment parseTextAlignment(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            switch(r0) {
                case -1364013995: goto L3a;
                case -1074341483: goto L30;
                case 100571: goto L26;
                case 3317767: goto L1c;
                case 108511772: goto L12;
                case 109757538: goto L8;
                default: goto L7;
            }
        L7:
            goto L44
        L8:
            java.lang.String r0 = "start"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L44
            r0 = 0
            goto L45
        L12:
            java.lang.String r0 = "right"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L44
            r0 = 5
            goto L45
        L1c:
            java.lang.String r0 = "left"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L44
            r0 = 1
            goto L45
        L26:
            java.lang.String r0 = "end"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L44
            r0 = 4
            goto L45
        L30:
            java.lang.String r0 = "middle"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L44
            r0 = 3
            goto L45
        L3a:
            java.lang.String r0 = "center"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L44
            r0 = 2
            goto L45
        L44:
            r0 = -1
        L45:
            switch(r0) {
                case 0: goto L66;
                case 1: goto L66;
                case 2: goto L63;
                case 3: goto L63;
                case 4: goto L60;
                case 5: goto L60;
                default: goto L48;
            }
        L48:
            java.lang.String r0 = "WebvttCueParser"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid alignment value: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            android.util.Log.w(r0, r3)
            r3 = 0
            return r3
        L60:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            return r3
        L63:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_CENTER
            return r3
        L66:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.parseTextAlignment(java.lang.String):android.text.Layout$Alignment");
    }

    private static int findEndOfTag(String str, int i) {
        int iIndexOf = str.indexOf(62, i);
        return iIndexOf == -1 ? str.length() : iIndexOf + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void applyEntity(java.lang.String r2, android.text.SpannableStringBuilder r3) {
        /*
            int r0 = r2.hashCode()
            r1 = 3309(0xced, float:4.637E-42)
            if (r0 == r1) goto L35
            r1 = 3464(0xd88, float:4.854E-42)
            if (r0 == r1) goto L2b
            r1 = 96708(0x179c4, float:1.35517E-40)
            if (r0 == r1) goto L21
            r1 = 3374865(0x337f11, float:4.729193E-39)
            if (r0 == r1) goto L17
            goto L3f
        L17:
            java.lang.String r0 = "nbsp"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L3f
            r0 = 2
            goto L40
        L21:
            java.lang.String r0 = "amp"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L3f
            r0 = 3
            goto L40
        L2b:
            java.lang.String r0 = "lt"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L3f
            r0 = 0
            goto L40
        L35:
            java.lang.String r0 = "gt"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L3f
            r0 = 1
            goto L40
        L3f:
            r0 = -1
        L40:
            switch(r0) {
                case 0: goto L71;
                case 1: goto L6b;
                case 2: goto L65;
                case 3: goto L5f;
                default: goto L43;
            }
        L43:
            java.lang.String r3 = "WebvttCueParser"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "ignoring unsupported entity: '&"
            r0.append(r1)
            r0.append(r2)
            java.lang.String r2 = ";'"
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            android.util.Log.w(r3, r2)
            goto L76
        L5f:
            r2 = 38
            r3.append(r2)
            goto L76
        L65:
            r2 = 32
            r3.append(r2)
            goto L76
        L6b:
            r2 = 62
            r3.append(r2)
            goto L76
        L71:
            r2 = 60
            r3.append(r2)
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.applyEntity(java.lang.String, android.text.SpannableStringBuilder):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean isSupportedTag(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case 98: goto L3c;
                case 99: goto L32;
                case 105: goto L28;
                case 117: goto L1e;
                case 118: goto L14;
                case 3314158: goto La;
                default: goto L9;
            }
        L9:
            goto L46
        La:
            java.lang.String r0 = "lang"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L46
            r3 = 3
            goto L47
        L14:
            java.lang.String r0 = "v"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L46
            r3 = 5
            goto L47
        L1e:
            java.lang.String r0 = "u"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L46
            r3 = 4
            goto L47
        L28:
            java.lang.String r0 = "i"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L46
            r3 = 2
            goto L47
        L32:
            java.lang.String r0 = "c"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L46
            r3 = 1
            goto L47
        L3c:
            java.lang.String r0 = "b"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L46
            r3 = 0
            goto L47
        L46:
            r3 = -1
        L47:
            switch(r3) {
                case 0: goto L4b;
                case 1: goto L4b;
                case 2: goto L4b;
                case 3: goto L4b;
                case 4: goto L4b;
                case 5: goto L4b;
                default: goto L4a;
            }
        L4a:
            return r2
        L4b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.isSupportedTag(java.lang.String):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void applySpansForTag(java.lang.String r7, com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.StartTag r8, android.text.SpannableStringBuilder r9, java.util.List<com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCssStyle> r10, java.util.List<com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.StyleMatch> r11) {
        /*
            int r0 = r8.position
            int r1 = r9.length()
            java.lang.String r2 = r8.name
            int r3 = r2.hashCode()
            r4 = 0
            r5 = 2
            r6 = 1
            switch(r3) {
                case 0: goto L4f;
                case 98: goto L45;
                case 99: goto L3b;
                case 105: goto L31;
                case 117: goto L27;
                case 118: goto L1d;
                case 3314158: goto L13;
                default: goto L12;
            }
        L12:
            goto L59
        L13:
            java.lang.String r3 = "lang"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L59
            r2 = 4
            goto L5a
        L1d:
            java.lang.String r3 = "v"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L59
            r2 = 5
            goto L5a
        L27:
            java.lang.String r3 = "u"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L59
            r2 = 2
            goto L5a
        L31:
            java.lang.String r3 = "i"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L59
            r2 = 1
            goto L5a
        L3b:
            java.lang.String r3 = "c"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L59
            r2 = 3
            goto L5a
        L45:
            java.lang.String r3 = "b"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L59
            r2 = 0
            goto L5a
        L4f:
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L59
            r2 = 6
            goto L5a
        L59:
            r2 = -1
        L5a:
            r3 = 33
            switch(r2) {
                case 0: goto L72;
                case 1: goto L69;
                case 2: goto L60;
                case 3: goto L7a;
                case 4: goto L7a;
                case 5: goto L7a;
                case 6: goto L7a;
                default: goto L5f;
            }
        L5f:
            return
        L60:
            android.text.style.UnderlineSpan r2 = new android.text.style.UnderlineSpan
            r2.<init>()
            r9.setSpan(r2, r0, r1, r3)
            goto L7a
        L69:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r5)
            r9.setSpan(r2, r0, r1, r3)
            goto L7a
        L72:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r6)
            r9.setSpan(r2, r0, r1, r3)
        L7a:
            r11.clear()
            getApplicableStyles(r10, r7, r8, r11)
            int r7 = r11.size()
        L84:
            if (r4 >= r7) goto L94
            java.lang.Object r8 = r11.get(r4)
            com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser$StyleMatch r8 = (com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.StyleMatch) r8
            com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCssStyle r8 = r8.style
            applyStyleToText(r9, r8, r0, r1)
            int r4 = r4 + 1
            goto L84
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser.applySpansForTag(java.lang.String, com.mbridge.msdk.playercommon.exoplayer2.text.webvtt.WebvttCueParser$StartTag, android.text.SpannableStringBuilder, java.util.List, java.util.List):void");
    }

    private static void applyStyleToText(SpannableStringBuilder spannableStringBuilder, WebvttCssStyle webvttCssStyle, int i, int i2) {
        if (webvttCssStyle == null) {
        }
        if (webvttCssStyle.getStyle() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(webvttCssStyle.getStyle()), i, i2, 33);
        }
        if (webvttCssStyle.isLinethrough()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (webvttCssStyle.isUnderline()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (webvttCssStyle.hasFontColor()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(webvttCssStyle.getFontColor()), i, i2, 33);
        }
        if (webvttCssStyle.hasBackgroundColor()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(webvttCssStyle.getBackgroundColor()), i, i2, 33);
        }
        if (webvttCssStyle.getFontFamily() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(webvttCssStyle.getFontFamily()), i, i2, 33);
        }
        if (webvttCssStyle.getTextAlign() != null) {
            spannableStringBuilder.setSpan(new AlignmentSpan.Standard(webvttCssStyle.getTextAlign()), i, i2, 33);
        }
        switch (webvttCssStyle.getFontSizeUnit()) {
            case 1:
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) webvttCssStyle.getFontSize(), true), i, i2, 33);
                break;
            case 2:
                spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize()), i, i2, 33);
                break;
            case 3:
                spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize() / 100.0f), i, i2, 33);
                break;
        }
    }

    private static String getTagName(String str) {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return null;
        }
        return Util.splitAtFirst(strTrim, "[ \\.]")[0];
    }

    private static void getApplicableStyles(List<WebvttCssStyle> list, String str, StartTag startTag, List<StyleMatch> list2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WebvttCssStyle webvttCssStyle = list.get(i);
            int specificityScore = webvttCssStyle.getSpecificityScore(str, startTag.name, startTag.classes, startTag.voice);
            if (specificityScore > 0) {
                list2.add(new StyleMatch(specificityScore, webvttCssStyle));
            }
        }
        Collections.sort(list2);
    }

    private static final class StyleMatch implements Comparable<StyleMatch> {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int i, WebvttCssStyle webvttCssStyle) {
            this.score = i;
            this.style = webvttCssStyle;
        }

        @Override // java.lang.Comparable
        public final int compareTo(StyleMatch styleMatch) {
            return this.score - styleMatch.score;
        }
    }

    private static final class StartTag {
        private static final String[] NO_CLASSES = new String[0];
        public final String[] classes;
        public final String name;
        public final int position;
        public final String voice;

        private StartTag(String str, int i, String str2, String[] strArr) {
            this.position = i;
            this.name = str;
            this.voice = str2;
            this.classes = strArr;
        }

        public static StartTag buildStartTag(String str, int i) {
            String str2;
            String[] strArr;
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return null;
            }
            int iIndexOf = strTrim.indexOf(" ");
            if (iIndexOf == -1) {
                str2 = "";
            } else {
                String strTrim2 = strTrim.substring(iIndexOf).trim();
                strTrim = strTrim.substring(0, iIndexOf);
                str2 = strTrim2;
            }
            String[] strArrSplit = Util.split(strTrim, "\\.");
            String str3 = strArrSplit[0];
            if (strArrSplit.length > 1) {
                strArr = (String[]) Arrays.copyOfRange(strArrSplit, 1, strArrSplit.length);
            } else {
                strArr = NO_CLASSES;
            }
            return new StartTag(str3, i, str2, strArr);
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag("", 0, "", new String[0]);
        }
    }
}
