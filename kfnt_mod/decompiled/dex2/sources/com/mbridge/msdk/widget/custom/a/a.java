package com.mbridge.msdk.widget.custom.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: MBLayoutInflate.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static final Class[] f = {Context.class, AttributeSet.class};
    private static final HashMap<String, Constructor> g = new HashMap<>();
    protected final Context a;
    private InterfaceC0148a c;
    private b d;
    private HashMap<String, Boolean> h;
    private final boolean b = false;
    private final Object[] e = new Object[2];

    /* compiled from: MBLayoutInflate.java */
    /* renamed from: com.mbridge.msdk.widget.custom.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0148a {
        View a(String str, Context context, AttributeSet attributeSet);
    }

    /* compiled from: MBLayoutInflate.java */
    public interface b {
        boolean a(Class cls);
    }

    public a(Context context) {
        this.a = context;
    }

    public final XmlPullParser a(String str) throws XmlPullParserException {
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        try {
            xmlPullParserNewPullParser.setInput(new FileInputStream(str), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlPullParserNewPullParser;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.view.View, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [android.view.View] */
    public final View a(XmlPullParser xmlPullParser, ViewGroup viewGroup, boolean z) {
        int next;
        synchronized (this.e) {
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
            this.e[0] = this.a;
            do {
                try {
                    next = xmlPullParser.next();
                    if (next == 2) {
                        break;
                    }
                } catch (IOException e) {
                    InflateException inflateException = new InflateException(xmlPullParser.getPositionDescription() + ": " + e.getMessage());
                    inflateException.initCause(e);
                    throw inflateException;
                } catch (XmlPullParserException e2) {
                    InflateException inflateException2 = new InflateException(e2.getMessage());
                    inflateException2.initCause(e2);
                    throw inflateException2;
                }
            } while (next != 1);
            if (next != 2) {
                throw new InflateException(xmlPullParser.getPositionDescription() + ": No start tag found!");
            }
            String name = xmlPullParser.getName();
            if (!"merge".equals(name)) {
                View viewA = a(name, attributeSetAsAttributeSet);
                ViewGroup.LayoutParams layoutParamsGenerateLayoutParams = null;
                if (viewGroup != 0) {
                    layoutParamsGenerateLayoutParams = viewGroup.generateLayoutParams(attributeSetAsAttributeSet);
                    if (!z) {
                        viewA.setLayoutParams(layoutParamsGenerateLayoutParams);
                    }
                }
                a(xmlPullParser, viewA, attributeSetAsAttributeSet);
                if (viewGroup != 0 && z) {
                    viewGroup.addView(viewA, layoutParamsGenerateLayoutParams);
                }
                if (viewGroup == 0 || !z) {
                    viewGroup = viewA;
                }
            } else {
                if (viewGroup == 0 || !z) {
                    throw new InflateException("<merge /> can be used only with a valid ViewGroup root and attachToRoot=true");
                }
                a(xmlPullParser, (View) viewGroup, attributeSetAsAttributeSet);
            }
        }
        return viewGroup;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.View a(java.lang.String r8, java.lang.String r9, android.util.AttributeSet r10) throws android.view.InflateException, java.lang.NoSuchMethodException, java.lang.ClassNotFoundException, java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.widget.custom.a.a.a(java.lang.String, java.lang.String, android.util.AttributeSet):android.view.View");
    }

    private void b(String str, String str2, AttributeSet attributeSet) {
        StringBuilder sb = new StringBuilder();
        sb.append(attributeSet.getPositionDescription());
        sb.append(": Class not allowed to be inflated ");
        if (str2 != null) {
            str = str2 + str;
        }
        sb.append(str);
        throw new InflateException(sb.toString());
    }

    private View a(String str, AttributeSet attributeSet) {
        if (str.equals(MBridgeConstans.DYNAMIC_VIEW_KEY_VIEW)) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            View viewA = this.c == null ? null : this.c.a(str, this.a, attributeSet);
            if (viewA != null) {
                return viewA;
            }
            if (-1 != str.indexOf(46)) {
                return a(str, (String) null, attributeSet);
            }
            if (str.equals("MBStarLevelLayoutView")) {
                return a(str, "", attributeSet);
            }
            return a(str, com.mbridge.msdk.widget.custom.b.d.b, attributeSet);
        } catch (InflateException e) {
            throw e;
        } catch (ClassNotFoundException e2) {
            InflateException inflateException = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
            inflateException.initCause(e2);
            throw inflateException;
        } catch (Exception e3) {
            InflateException inflateException2 = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
            inflateException2.initCause(e3);
            throw inflateException2;
        }
    }

    private void a(XmlPullParser xmlPullParser, View view, AttributeSet attributeSet) throws XmlPullParserException, Resources.NotFoundException, IOException {
        int next;
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next2 = xmlPullParser.next();
            if ((next2 == 3 && xmlPullParser.getDepth() <= depth) || next2 == 1) {
                return;
            }
            if (next2 == 2) {
                String name = xmlPullParser.getName();
                if (!"requestFocus".equals(name)) {
                    if ("include".equals(name)) {
                        if (xmlPullParser.getDepth() == 0) {
                            throw new InflateException("<include /> cannot be the root element");
                        }
                        b(xmlPullParser, view, attributeSet);
                    } else {
                        if ("merge".equals(name)) {
                            throw new InflateException("<merge /> must be the root element");
                        }
                        View viewA = a(name, attributeSet);
                        ViewGroup viewGroup = (ViewGroup) view;
                        ViewGroup.LayoutParams layoutParamsGenerateLayoutParams = viewGroup.generateLayoutParams(attributeSet);
                        a(xmlPullParser, viewA, attributeSet);
                        viewGroup.addView(viewA, layoutParamsGenerateLayoutParams);
                    }
                } else {
                    view.requestFocus();
                    int depth2 = xmlPullParser.getDepth();
                    do {
                        next = xmlPullParser.next();
                        if (next != 3 || xmlPullParser.getDepth() > depth2) {
                        }
                    } while (next != 1);
                }
            }
        }
    }

    private void b(XmlPullParser xmlPullParser, View view, AttributeSet attributeSet) throws XmlPullParserException, Resources.NotFoundException, IOException {
        int next;
        ViewGroup.LayoutParams layoutParamsGenerateLayoutParams;
        int next2;
        if (view instanceof ViewGroup) {
            int attributeResourceValue = attributeSet.getAttributeResourceValue(null, TtmlNode.TAG_LAYOUT, 0);
            if (attributeResourceValue == 0) {
                String attributeValue = attributeSet.getAttributeValue(null, TtmlNode.TAG_LAYOUT);
                if (attributeValue == null) {
                    throw new InflateException("You must specifiy a layout in the include tag: <include layout=\"@layout/layoutID\" />");
                }
                throw new InflateException("You must specifiy a valid layout reference. The layout ID " + attributeValue + " is not valid.");
            }
            XmlResourceParser layout = this.a.getResources().getLayout(attributeResourceValue);
            try {
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(layout);
                do {
                    next = layout.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new InflateException(layout.getPositionDescription() + ": No start tag found!");
                }
                String name = layout.getName();
                if ("merge".equals(name)) {
                    a(layout, view, attributeSetAsAttributeSet);
                } else {
                    View viewA = a(name, attributeSetAsAttributeSet);
                    ViewGroup viewGroup = (ViewGroup) view;
                    try {
                        layoutParamsGenerateLayoutParams = viewGroup.generateLayoutParams(attributeSet);
                    } catch (RuntimeException unused) {
                        layoutParamsGenerateLayoutParams = viewGroup.generateLayoutParams(attributeSetAsAttributeSet);
                        if (layoutParamsGenerateLayoutParams != null) {
                        }
                    }
                    if (layoutParamsGenerateLayoutParams != null) {
                        viewA.setLayoutParams(layoutParamsGenerateLayoutParams);
                    }
                    a(layout, viewA, attributeSetAsAttributeSet);
                    viewGroup.addView(viewA);
                }
                layout.close();
                int depth = xmlPullParser.getDepth();
                do {
                    next2 = xmlPullParser.next();
                    if (next2 == 3 && xmlPullParser.getDepth() <= depth) {
                        return;
                    }
                } while (next2 != 1);
                return;
            } catch (Throwable th) {
                layout.close();
                throw th;
            }
        }
        throw new InflateException("<include /> can only be used inside of a ViewGroup");
    }
}
