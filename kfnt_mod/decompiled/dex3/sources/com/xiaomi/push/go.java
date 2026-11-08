package com.xiaomi.push;

import android.text.TextUtils;
import com.qq.e.comm.pi.ACTD;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.umeng.message.common.inter.ITagManager;
import com.xiaomi.push.ge;
import com.xiaomi.push.gi;
import com.xiaomi.push.gk;
import com.xiaomi.push.service.av;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class go {
    private static XmlPullParser a;

    public static gd a(String str, String str2, XmlPullParser xmlPullParser) {
        Object objM361a = gn.a().m361a(PushMultiProcessSharedProvider.ALL_TYPE, "xm:chat");
        if (objM361a == null || !(objM361a instanceof com.xiaomi.push.service.f)) {
            return null;
        }
        return ((com.xiaomi.push.service.f) objM361a).b(xmlPullParser);
    }

    public static ge a(XmlPullParser xmlPullParser, fp fpVar) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", AdDownloadModel.JsonKey.ID);
        String attributeValue2 = xmlPullParser.getAttributeValue("", "to");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
        String attributeValue4 = xmlPullParser.getAttributeValue("", "chid");
        ge.a aVarA = ge.a.a(xmlPullParser.getAttributeValue("", "type"));
        HashMap map = new HashMap();
        boolean z = false;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            map.put(attributeName, xmlPullParser.getAttributeValue("", attributeName));
        }
        ge gqVar = null;
        gk gkVarM365a = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("error")) {
                    gkVarM365a = m365a(xmlPullParser);
                } else {
                    gqVar = new ge();
                    gqVar.a(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z = true;
            }
        }
        if (gqVar == null) {
            if (ge.a.a == aVarA || ge.a.b == aVarA) {
                gp gpVar = new gp();
                gpVar.k(attributeValue);
                gpVar.m(attributeValue3);
                gpVar.n(attributeValue2);
                gpVar.a(ge.a.d);
                gpVar.l(attributeValue4);
                gpVar.a(new gk(gk.a.e));
                fpVar.a(gpVar);
                com.xiaomi.channel.commonutils.logger.b.d("iq usage error. send packet in packet parser.");
                return null;
            }
            gqVar = new gq();
        }
        gqVar.k(attributeValue);
        gqVar.m(attributeValue2);
        gqVar.l(attributeValue4);
        gqVar.n(attributeValue3);
        gqVar.a(aVarA);
        gqVar.a(gkVarM365a);
        gqVar.a(map);
        return gqVar;
    }

    public static gg a(XmlPullParser xmlPullParser) throws XmlPullParserException, ga, IOException {
        String attributeValue;
        boolean z = false;
        String strNextText = null;
        if ("1".equals(xmlPullParser.getAttributeValue("", com.umeng.commonsdk.proguard.o.at))) {
            String attributeValue2 = xmlPullParser.getAttributeValue("", "chid");
            String attributeValue3 = xmlPullParser.getAttributeValue("", AdDownloadModel.JsonKey.ID);
            String attributeValue4 = xmlPullParser.getAttributeValue("", "from");
            String attributeValue5 = xmlPullParser.getAttributeValue("", "to");
            String attributeValue6 = xmlPullParser.getAttributeValue("", "type");
            av.b bVarA = com.xiaomi.push.service.av.a().a(attributeValue2, attributeValue5);
            if (bVarA == null) {
                bVarA = com.xiaomi.push.service.av.a().a(attributeValue2, attributeValue4);
            }
            if (bVarA == null) {
                throw new ga("the channel id is wrong while receiving a encrypted message");
            }
            gg ggVarA = null;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (!com.umeng.commonsdk.proguard.o.at.equals(xmlPullParser.getName())) {
                        throw new ga("error while receiving a encrypted message with wrong format");
                    }
                    if (xmlPullParser.next() != 4) {
                        throw new ga("error while receiving a encrypted message with wrong format");
                    }
                    String text = xmlPullParser.getText();
                    if ("5".equals(attributeValue2) || DownloadConstants.DOWNLOAD_FINISH_REASON_BEFORE_DOWNLOAD_SUCCESS.equals(attributeValue2)) {
                        gf gfVar = new gf();
                        gfVar.l(attributeValue2);
                        gfVar.b(true);
                        gfVar.n(attributeValue4);
                        gfVar.m(attributeValue5);
                        gfVar.k(attributeValue3);
                        gfVar.f(attributeValue6);
                        String[] strArr = (String[]) null;
                        gd gdVar = new gd(com.umeng.commonsdk.proguard.o.at, null, strArr, strArr);
                        gdVar.m352a(text);
                        gfVar.a(gdVar);
                        return gfVar;
                    }
                    a(com.xiaomi.push.service.be.a(com.xiaomi.push.service.be.a(bVarA.h, attributeValue3), text));
                    a.next();
                    ggVarA = a(a);
                } else if (next == 3 && xmlPullParser.getName().equals("message")) {
                    z = true;
                }
            }
            if (ggVarA != null) {
                return ggVarA;
            }
            throw new ga("error while receiving a encrypted message with wrong format");
        }
        gf gfVar2 = new gf();
        String attributeValue7 = xmlPullParser.getAttributeValue("", AdDownloadModel.JsonKey.ID);
        if (attributeValue7 == null) {
            attributeValue7 = "ID_NOT_AVAILABLE";
        }
        gfVar2.k(attributeValue7);
        gfVar2.m(xmlPullParser.getAttributeValue("", "to"));
        gfVar2.n(xmlPullParser.getAttributeValue("", "from"));
        gfVar2.l(xmlPullParser.getAttributeValue("", "chid"));
        gfVar2.a(xmlPullParser.getAttributeValue("", ACTD.APPID_KEY));
        try {
            attributeValue = xmlPullParser.getAttributeValue("", "transient");
        } catch (Exception unused) {
            attributeValue = null;
        }
        try {
            String attributeValue8 = xmlPullParser.getAttributeValue("", "seq");
            if (!TextUtils.isEmpty(attributeValue8)) {
                gfVar2.b(attributeValue8);
            }
        } catch (Exception unused2) {
        }
        try {
            String attributeValue9 = xmlPullParser.getAttributeValue("", "mseq");
            if (!TextUtils.isEmpty(attributeValue9)) {
                gfVar2.c(attributeValue9);
            }
        } catch (Exception unused3) {
        }
        try {
            String attributeValue10 = xmlPullParser.getAttributeValue("", "fseq");
            if (!TextUtils.isEmpty(attributeValue10)) {
                gfVar2.d(attributeValue10);
            }
        } catch (Exception unused4) {
        }
        try {
            String attributeValue11 = xmlPullParser.getAttributeValue("", "status");
            if (!TextUtils.isEmpty(attributeValue11)) {
                gfVar2.e(attributeValue11);
            }
        } catch (Exception unused5) {
        }
        gfVar2.a(!TextUtils.isEmpty(attributeValue) && attributeValue.equalsIgnoreCase(ITagManager.STATUS_TRUE));
        gfVar2.f(xmlPullParser.getAttributeValue("", "type"));
        String strB = b(xmlPullParser);
        if (strB == null || "".equals(strB.trim())) {
            gg.q();
        } else {
            gfVar2.j(strB);
        }
        while (!z) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(namespace)) {
                    namespace = "xm";
                }
                if (name.equals("subject")) {
                    b(xmlPullParser);
                    gfVar2.g(m366a(xmlPullParser));
                } else if (name.equals("body")) {
                    String attributeValue12 = xmlPullParser.getAttributeValue("", "encode");
                    String strM366a = m366a(xmlPullParser);
                    if (TextUtils.isEmpty(attributeValue12)) {
                        gfVar2.h(strM366a);
                    } else {
                        gfVar2.a(strM366a, attributeValue12);
                    }
                } else if (name.equals("thread")) {
                    if (strNextText == null) {
                        strNextText = xmlPullParser.nextText();
                    }
                } else if (name.equals("error")) {
                    gfVar2.a(m365a(xmlPullParser));
                } else {
                    gfVar2.a(a(name, namespace, xmlPullParser));
                }
            } else if (next2 == 3 && xmlPullParser.getName().equals("message")) {
                z = true;
            }
        }
        gfVar2.i(strNextText);
        return gfVar2;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static gi m363a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        gi.b bVarValueOf = gi.b.available;
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (attributeValue != null && !attributeValue.equals("")) {
            try {
                bVarValueOf = gi.b.valueOf(attributeValue);
            } catch (IllegalArgumentException unused) {
                System.err.println("Found invalid presence type " + attributeValue);
            }
        }
        gi giVar = new gi(bVarValueOf);
        giVar.m(xmlPullParser.getAttributeValue("", "to"));
        giVar.n(xmlPullParser.getAttributeValue("", "from"));
        giVar.l(xmlPullParser.getAttributeValue("", "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", AdDownloadModel.JsonKey.ID);
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        giVar.k(attributeValue2);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    giVar.a(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        giVar.a(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException unused2) {
                    } catch (IllegalArgumentException unused3) {
                        giVar.a(0);
                    }
                } else if (name.equals(PointCategory.SHOW)) {
                    String strNextText = xmlPullParser.nextText();
                    try {
                        giVar.a(gi.a.valueOf(strNextText));
                    } catch (IllegalArgumentException unused4) {
                        System.err.println("Found invalid presence mode " + strNextText);
                    }
                } else if (name.equals("error")) {
                    giVar.a(m365a(xmlPullParser));
                } else {
                    giVar.a(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z = true;
            }
        }
        return giVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static gj m364a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        gj gjVar = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                gjVar = new gj(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        return gjVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static gk m365a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        String attributeValue = "-1";
        String attributeValue2 = null;
        String attributeValue3 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals("code")) {
                attributeValue = xmlPullParser.getAttributeValue("", "code");
            }
            if (xmlPullParser.getAttributeName(i).equals("type")) {
                attributeValue2 = xmlPullParser.getAttributeValue("", "type");
            }
            if (xmlPullParser.getAttributeName(i).equals("reason")) {
                attributeValue3 = xmlPullParser.getAttributeValue("", "reason");
            }
        }
        String str = null;
        String strNextText = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    strNextText = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str = name;
                    } else {
                        arrayList.add(a(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("error")) {
                    z = true;
                }
            } else if (next == 4) {
                strNextText = xmlPullParser.getText();
            }
        }
        return new gk(Integer.parseInt(attributeValue), attributeValue2 == null ? "cancel" : attributeValue2, attributeValue3, str, strNextText, arrayList);
    }

    /* renamed from: a, reason: collision with other method in class */
    private static String m366a(XmlPullParser xmlPullParser) {
        String str = "";
        int depth = xmlPullParser.getDepth();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    private static void a(byte[] bArr) throws XmlPullParserException {
        if (a == null) {
            try {
                a = XmlPullParserFactory.newInstance().newPullParser();
                a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    private static String b(XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }
}
