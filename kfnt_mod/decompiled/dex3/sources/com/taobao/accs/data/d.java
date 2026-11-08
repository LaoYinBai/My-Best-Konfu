package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.antibrush.AntiBrush;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.data.Message;
import com.taobao.accs.flowcontrol.FlowControl;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.ut.monitor.TrafficsMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.p;
import com.taobao.accs.utl.s;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public int b;
    protected TrafficsMonitor c;
    public FlowControl d;
    public AntiBrush e;
    private Context i;
    private com.taobao.accs.ut.a.d j;
    private Message k;
    private com.taobao.accs.net.b l;
    private String m;
    private ConcurrentMap<Message.Id, Message> g = new ConcurrentHashMap();
    public ConcurrentMap<String, ScheduledFuture<?>> a = new ConcurrentHashMap();
    private boolean h = false;
    public String f = "";
    private LinkedHashMap<String, String> n = new LinkedHashMap<String, String>() { // from class: com.taobao.accs.data.MessageHandler$1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
            return size() > 50;
        }
    };
    private Map<String, a> o = new HashMap();
    private Runnable p = new f(this);

    private boolean b(int i) {
        return i == -1 || i == -9 || i == -10 || i == -11;
    }

    public d(Context context, com.taobao.accs.net.b bVar) throws IOException {
        String str;
        this.m = "MsgRecv_";
        this.i = context;
        this.l = bVar;
        this.c = new TrafficsMonitor(this.i);
        this.d = new FlowControl(this.i);
        this.e = new AntiBrush(this.i);
        if (bVar == null) {
            str = this.m;
        } else {
            str = this.m + bVar.m;
        }
        this.m = str;
        i();
        h();
    }

    public void a(byte[] bArr) throws IOException {
        a(bArr, (String) null);
    }

    public void a(byte[] bArr, String str) throws IOException {
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i(this.m, "onMessage", Constants.KEY_HOST, str);
        }
        s sVar = new s(bArr);
        try {
            try {
                int iA = sVar.a();
                int i = (iA & 240) >> 4;
                if (ALog.isPrintLog(ALog.Level.D)) {
                    ALog.d(this.m, "version:" + i, new Object[0]);
                }
                int i2 = iA & 15;
                if (ALog.isPrintLog(ALog.Level.D)) {
                    ALog.d(this.m, "compress:" + i2, new Object[0]);
                }
                sVar.a();
                int iB = sVar.b();
                if (ALog.isPrintLog(ALog.Level.D)) {
                    ALog.d(this.m, "totalLen:" + iB, new Object[0]);
                }
                int length = 0;
                while (length < iB) {
                    int iB2 = sVar.b();
                    int i3 = length + 2;
                    if (iB2 > 0) {
                        byte[] bArr2 = new byte[iB2];
                        sVar.read(bArr2);
                        if (ALog.isPrintLog(ALog.Level.D)) {
                            ALog.d(this.m, "buf len:" + bArr2.length, new Object[0]);
                        }
                        length = i3 + bArr2.length;
                        a(i2, bArr2, str, i);
                    } else {
                        throw new IOException("data format error");
                    }
                }
            } catch (Throwable th) {
                com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", this.b + th.toString());
                ALog.e(this.m, "", th, new Object[0]);
            }
        } finally {
            sVar.close();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 40, insn: 0x0318: MOVE (r6 I:??[OBJECT, ARRAY]) = (r40 I:??[OBJECT, ARRAY]), block:B:109:0x0318 */
    /* JADX WARN: Removed duplicated region for block: B:200:0x052d A[Catch: Exception -> 0x0393, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x0393, blocks: (B:112:0x032f, B:114:0x0333, B:116:0x0343, B:118:0x034b, B:122:0x0359, B:124:0x0379, B:131:0x039c, B:134:0x03a2, B:137:0x03ad, B:141:0x03b9, B:143:0x03c3, B:146:0x03ce, B:148:0x03d6, B:150:0x03de, B:151:0x03f3, B:153:0x03f7, B:154:0x03fc, B:160:0x041e, B:166:0x0431, B:170:0x043b, B:219:0x0657, B:174:0x046d, B:176:0x0473, B:179:0x047c, B:182:0x0486, B:184:0x0497, B:185:0x04b6, B:190:0x04c6, B:200:0x052d, B:204:0x053b, B:208:0x059c, B:195:0x04f2, B:105:0x02f6), top: B:256:0x02f6 }] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x053b A[Catch: Exception -> 0x0393, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x0393, blocks: (B:112:0x032f, B:114:0x0333, B:116:0x0343, B:118:0x034b, B:122:0x0359, B:124:0x0379, B:131:0x039c, B:134:0x03a2, B:137:0x03ad, B:141:0x03b9, B:143:0x03c3, B:146:0x03ce, B:148:0x03d6, B:150:0x03de, B:151:0x03f3, B:153:0x03f7, B:154:0x03fc, B:160:0x041e, B:166:0x0431, B:170:0x043b, B:219:0x0657, B:174:0x046d, B:176:0x0473, B:179:0x047c, B:182:0x0486, B:184:0x0497, B:185:0x04b6, B:190:0x04c6, B:200:0x052d, B:204:0x053b, B:208:0x059c, B:195:0x04f2, B:105:0x02f6), top: B:256:0x02f6 }] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0609  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x060b A[Catch: Exception -> 0x06c4, TryCatch #11 {Exception -> 0x06c4, blocks: (B:156:0x0419, B:163:0x0423, B:217:0x064f, B:222:0x067e, B:221:0x066c, B:188:0x04bb, B:198:0x0517, B:201:0x0535, B:205:0x0542, B:211:0x05a6, B:215:0x060c, B:214:0x060b, B:193:0x04ea), top: B:266:0x0419 }] */
    /* JADX WARN: Type inference failed for: r0v23, types: [android.content.Intent] */
    /* JADX WARN: Type inference failed for: r0v38, types: [com.taobao.accs.net.b] */
    /* JADX WARN: Type inference failed for: r0v52, types: [com.taobao.accs.net.b] */
    /* JADX WARN: Type inference failed for: r0v60, types: [com.taobao.accs.net.b] */
    /* JADX WARN: Type inference failed for: r0v61, types: [com.taobao.accs.net.b] */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r1v31, types: [com.taobao.accs.ut.monitor.NetPerformanceMonitor, java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r2v54, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v94, types: [com.taobao.accs.net.b] */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v84 */
    /* JADX WARN: Type inference failed for: r47v0, types: [com.taobao.accs.data.d] */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.taobao.accs.net.b] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(int r48, byte[] r49, java.lang.String r50, int r51) throws org.json.JSONException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 1963
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.data.d.a(int, byte[], java.lang.String, int):void");
    }

    private byte[] a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = gZIPInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                }
                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                ALog.e(this.m, "uncompress data error " + e.toString(), new Object[0]);
                com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", this.b + " uncompress data error " + e.toString());
                try {
                    gZIPInputStream.close();
                    byteArrayOutputStream.close();
                } catch (Exception unused) {
                }
                return null;
            }
        } finally {
            try {
                gZIPInputStream.close();
                byteArrayOutputStream.close();
            } catch (Exception unused2) {
            }
        }
    }

    private void a(Message message, byte[] bArr, byte[] bArr2, String str) {
        JSONArray jSONArray;
        int i = -8;
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(this.m, "handleControlMessage parse", "json", jSONObject.toString());
            }
            i = message.command.intValue() == 100 ? 200 : jSONObject.getInt("code");
            if (i == 200) {
                int iIntValue = message.command.intValue();
                if (iIntValue != 100) {
                    switch (iIntValue) {
                        case 1:
                            UtilityImpl.c(Constants.SP_FILE_NAME, this.i);
                            try {
                                this.l.j().a(this.i.getPackageName());
                                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                                this.f = p.a(jSONObject2, Constants.KEY_DEVICE_TOKEN, null);
                                if (jSONObject2 != null && (jSONArray = jSONObject2.getJSONArray(Constants.KEY_PACKAGE_NAMES)) != null) {
                                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                        String string = jSONArray.getString(i2);
                                        if (UtilityImpl.a(this.i, string)) {
                                            this.l.j().a(message.m);
                                        } else {
                                            ALog.d(this.m, "unbind app", "pkg", string);
                                            this.l.b(Message.buildUnbindApp(this.l.b((String) null), string), true);
                                        }
                                    }
                                    break;
                                }
                            } catch (Throwable th2) {
                                ALog.i(this.m, "no token/invalid app", th2);
                                break;
                            }
                            break;
                        case 2:
                            this.l.j().b(message.m);
                            break;
                        case 3:
                            this.l.j().a(message.m, message.userinfo);
                            break;
                        case 4:
                            this.l.j().e(message.m);
                            break;
                    }
                } else if ((this.l instanceof com.taobao.accs.net.j) && "4|sal|accs-iot".equals(message.i)) {
                    ((com.taobao.accs.net.j) this.l).a(jSONObject);
                }
            } else if (message.command.intValue() == 3 && i == 300) {
                this.l.j().b(message.m);
            }
        } catch (Throwable th3) {
            th = th3;
            ALog.e(this.m, "handleControlMessage", th, new Object[0]);
            com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "handleControlMessage", "", this.b + th.toString());
            a(message, i, null, bArr, null);
            a(new TrafficsMonitor.a(message.serviceId, GlobalAppRuntimeInfo.isAppBackground(), str, bArr2.length));
        }
        a(message, i, null, bArr, null);
        a(new TrafficsMonitor.a(message.serviceId, GlobalAppRuntimeInfo.isAppBackground(), str, bArr2.length));
    }

    private Map<Integer, String> a(s sVar) {
        HashMap map;
        if (sVar == null) {
            return null;
        }
        try {
            int iB = sVar.b();
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(this.m, "extHeaderLen:" + iB, new Object[0]);
            }
            map = null;
            int i = 0;
            while (i < iB) {
                try {
                    int iB2 = sVar.b();
                    int i2 = (64512 & iB2) >> 10;
                    int i3 = iB2 & 1023;
                    String strA = sVar.a(i3);
                    i = i + 2 + i3;
                    if (map == null) {
                        map = new HashMap();
                    }
                    map.put(Integer.valueOf(i2), strA);
                    if (ALog.isPrintLog(ALog.Level.D)) {
                        ALog.d(this.m, "", "extHeaderType", Integer.valueOf(i2), "value", strA);
                    }
                } catch (Exception e) {
                    e = e;
                    ALog.e(this.m, "parseExtHeader", e, new Object[0]);
                    return map;
                }
            }
        } catch (Exception e2) {
            e = e2;
            map = null;
        }
        return map;
    }

    public void a(Message message, int i) {
        a(message, i, null, null, null);
    }

    public void a(Message message, int i, Map<Integer, String> map) {
        a(message, i, null, null, map);
    }

    public void a(Message message, int i, Message.ReqType reqType, byte[] bArr, Map<Integer, String> map) {
        int i2;
        Message.ReqType reqType2;
        byte[] bArr2;
        if (message.command == null || message.getType() < 0 || message.getType() == 2) {
            ALog.d(this.m, "onError, skip ping/ack", new Object[0]);
            return;
        }
        if (message.cunstomDataId != null) {
            this.a.remove(message.cunstomDataId);
        }
        Map<Integer, String> map2 = map;
        if (this.e.checkAntiBrush(message.host, map2)) {
            i2 = ErrorCode.SERVIER_ANTI_BRUSH;
            map2 = null;
            reqType2 = null;
            bArr2 = null;
        } else {
            i2 = i;
            reqType2 = reqType;
            bArr2 = bArr;
        }
        int iA = this.d.a(map2, message.serviceId);
        if (iA != 0) {
            i2 = iA == 2 ? ErrorCode.SERVIER_HIGH_LIMIT : iA == 3 ? ErrorCode.SERVIER_HIGH_LIMIT_BRUSH : ErrorCode.SERVIER_LOW_LIMIT;
            map2 = null;
            reqType2 = null;
            bArr2 = null;
        }
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d(this.m, "onResult", Constants.KEY_COMMAND, message.command, "erorcode", Integer.valueOf(i2));
        }
        if (message.command.intValue() == 102) {
            return;
        }
        if (!message.isCancel) {
            if (b(i2) && message.command.intValue() != 100 && message.retryTimes <= Message.CONTROL_MAX_RETRY_TIMES) {
                message.startSendTime = System.currentTimeMillis();
                message.retryTimes++;
                ALog.d(this.m, "onResult", "retryTimes", Integer.valueOf(message.retryTimes));
                this.l.b(message, true);
            } else {
                Intent intentC = c(message);
                intentC.putExtra(Constants.KEY_ERROR_CODE, i2);
                Message.ReqType reqTypeValueOf = Message.ReqType.valueOf((message.f >> 13) & 3);
                if (reqType2 == Message.ReqType.RES || reqTypeValueOf == Message.ReqType.REQ) {
                    intentC.putExtra(Constants.KEY_SEND_TYPE, Constants.SEND_TYPE_RES);
                }
                if (i2 == 200) {
                    intentC.putExtra("data", bArr2);
                }
                intentC.putExtra(Constants.KEY_APP_KEY, this.l.b);
                intentC.putExtra(Constants.KEY_CONFIG_TAG, this.l.m);
                a(map2, intentC);
                g.a(this.i, this.l, intentC);
                if (!TextUtils.isEmpty(message.serviceId)) {
                    UTMini.getInstance().commitEvent(66001, "MsgToBuss0", "commandId=" + message.command, "serviceId=" + message.serviceId + " errorCode=" + i2 + " dataId=" + message.dataId, Integer.valueOf(Constants.SDK_VERSION_CODE));
                    StringBuilder sb = new StringBuilder();
                    sb.append("1commandId=");
                    sb.append(message.command);
                    sb.append("serviceId=");
                    sb.append(message.serviceId);
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS, sb.toString(), 0.0d);
                }
            }
        } else {
            ALog.e(this.m, "onResult message is cancel", Constants.KEY_COMMAND, message.command);
        }
        NetPerformanceMonitor netPermanceMonitor = message.getNetPermanceMonitor();
        if (netPermanceMonitor != null) {
            netPermanceMonitor.onToBizDate();
            String string = message.host != null ? message.host.toString() : null;
            if (i2 == 200) {
                netPermanceMonitor.setRet(true);
                if (message.retryTimes > 0) {
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_RESEND, "succ", 0.0d);
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_RESEND, "succ_" + message.retryTimes, 0.0d);
                } else {
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQUEST, string);
                }
            } else {
                if (message.retryTimes > 0) {
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_RESEND, "fail＿" + i2, 0.0d);
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_RESEND, "fail", 0.0d);
                } else if (i2 != -13) {
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQUEST, string, UtilityImpl.a(i2), this.b + message.serviceId + message.timeout);
                }
                netPermanceMonitor.setRet(false);
                netPermanceMonitor.setFailReason(i2);
            }
            AppMonitor.getInstance().commitStat(message.getNetPermanceMonitor());
        }
        b(message, i2);
    }

    public void a() {
        ALog.d(this.m, "onSendPing", new Object[0]);
        synchronized (d.class) {
            this.h = true;
        }
    }

    public void b() {
        ALog.d(this.m, "onRcvPing", new Object[0]);
        synchronized (d.class) {
            this.h = false;
        }
    }

    public boolean c() {
        return this.h;
    }

    public void a(Message message) {
        if (this.k != null && message.cunstomDataId != null && message.serviceId != null && this.k.cunstomDataId.equals(message.cunstomDataId) && this.k.serviceId.equals(message.serviceId)) {
            UTMini.getInstance().commitEvent(66001, "SEND_REPEAT", message.serviceId, message.cunstomDataId, Long.valueOf(Thread.currentThread().getId()));
        }
        if (message.getType() == -1 || message.getType() == 2 || message.isAck) {
            return;
        }
        this.g.put(message.getMsgId(), message);
    }

    public void a(int i) {
        this.h = false;
        Message.Id[] idArr = (Message.Id[]) this.g.keySet().toArray(new Message.Id[0]);
        if (idArr.length > 0) {
            ALog.d(this.m, "onNetworkFail", new Object[0]);
            for (Message.Id id : idArr) {
                Message messageRemove = this.g.remove(id);
                if (messageRemove != null) {
                    a(messageRemove, i);
                }
            }
        }
    }

    public void b(Message message) {
        if (this.g.keySet().size() > 0) {
            Iterator<Message.Id> it = this.g.keySet().iterator();
            while (it.hasNext()) {
                Message message2 = this.g.get(it.next());
                if (message2 != null && message2.command != null && message2.getPackageName().equals(message.getPackageName())) {
                    switch (message.command.intValue()) {
                        case 1:
                        case 2:
                            if (message2.command.intValue() == 1 || message2.command.intValue() == 2) {
                                message2.isCancel = true;
                                break;
                            }
                            break;
                        case 3:
                        case 4:
                            if (message2.command.intValue() == 3 || message2.command.intValue() == 4) {
                                message2.isCancel = true;
                                break;
                            }
                            break;
                        case 5:
                        case 6:
                            if (message2.command.intValue() == 5 || message2.command.intValue() == 6) {
                                message2.isCancel = true;
                                break;
                            }
                            break;
                    }
                }
                if (message2 != null && message2.isCancel) {
                    ALog.e(this.m, "cancelControlMessage", Constants.KEY_COMMAND, message2.command);
                }
            }
        }
    }

    public int d() {
        return this.g.size();
    }

    public Collection<Message> e() {
        return this.g.values();
    }

    public Set<Message.Id> f() {
        return this.g.keySet();
    }

    public Message a(String str) {
        return this.g.get(new Message.Id(0, str));
    }

    public Message b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.g.remove(new Message.Id(0, str));
    }

    private boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.n.containsKey(str);
    }

    private byte[] a(String str, Map<Integer, String> map, byte[] bArr) throws NumberFormatException {
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    int i = Integer.parseInt(map.get(17));
                    int i2 = Integer.parseInt(map.get(16));
                    if (i2 <= 1) {
                        throw new RuntimeException("burstNums <= 1");
                    }
                    if (i < 0 || i >= i2) {
                        throw new RuntimeException(String.format("burstNums:%s burstIndex:%s", Integer.valueOf(i2), Integer.valueOf(i)));
                    }
                    String str2 = map.get(18);
                    long j = 0;
                    try {
                        String str3 = map.get(15);
                        if (!TextUtils.isEmpty(str3)) {
                            j = Long.parseLong(str3);
                        }
                    } catch (Throwable th) {
                        ALog.w(this.m, "putBurstMessage", th, new Object[0]);
                    }
                    a aVar = this.o.get(str);
                    if (aVar == null) {
                        if (ALog.isPrintLog(ALog.Level.I)) {
                            ALog.i(this.m, "putBurstMessage", Constants.KEY_DATA_ID, str, "burstLength", Integer.valueOf(i2));
                        }
                        aVar = new a(str, i2, str2);
                        aVar.a(j);
                        this.o.put(str, aVar);
                    }
                    return aVar.a(i, i2, bArr);
                }
            } catch (Throwable th2) {
                ALog.w(this.m, "putBurstMessage", th2, new Object[0]);
                return null;
            }
        }
        throw new RuntimeException("burstLength == 0");
    }

    private void d(String str) throws IOException {
        if (TextUtils.isEmpty(str) || this.n.containsKey(str)) {
            return;
        }
        this.n.put(str, str);
        j();
    }

    private void i() throws IOException {
        try {
            File file = new File(this.i.getDir("accs", 0), "message" + this.l.i());
            if (!file.exists()) {
                ALog.d(this.m, "message file not exist", new Object[0]);
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    this.n.put(line, line);
                } else {
                    bufferedReader.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void j() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.i.getDir("accs", 0), "message" + this.l.i()));
            fileWriter.write("");
            Iterator<String> it = this.n.keySet().iterator();
            while (it.hasNext()) {
                fileWriter.append((CharSequence) it.next()).append((CharSequence) com.sigmob.sdk.common.Constants.LINE_BREAK);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Intent c(Message message) {
        Intent intent = new Intent(Constants.ACTION_RECEIVE);
        intent.setPackage(message.m);
        intent.putExtra(Constants.KEY_COMMAND, message.command);
        intent.putExtra(Constants.KEY_SERVICE_ID, message.serviceId);
        intent.putExtra(Constants.KEY_USER_ID, message.userinfo);
        if (message.command != null && message.command.intValue() == 100) {
            intent.putExtra(Constants.KEY_DATA_ID, message.cunstomDataId);
        }
        return intent;
    }

    private void a(Map<Integer, String> map, Intent intent) {
        if (map == null || intent == null) {
            return;
        }
        intent.putExtra(TaoBaseService.ExtraInfo.EXT_HEADER, (HashMap) map);
    }

    private void a(Intent intent, String str, String str2, short s) {
        if (intent != null) {
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("source", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                intent.putExtra(Constants.KEY_TARGET, str2);
            }
            intent.putExtra(Constants.KEY_FLAGS, s);
        }
    }

    public com.taobao.accs.ut.a.d g() {
        return this.j;
    }

    private void b(Message message, int i) {
        if (message == null) {
            return;
        }
        String strL = UtilityImpl.l(this.i);
        String str = System.currentTimeMillis() + "";
        boolean z = i == 200;
        int iIntValue = message.command.intValue();
        if (iIntValue == 1) {
            com.taobao.accs.ut.a.a aVar = new com.taobao.accs.ut.a.a();
            aVar.a = strL;
            aVar.b = str;
            aVar.c = z;
            aVar.a(i);
            aVar.a();
            return;
        }
        if (iIntValue != 3) {
            return;
        }
        com.taobao.accs.ut.a.b bVar = new com.taobao.accs.ut.a.b();
        bVar.a = strL;
        bVar.b = str;
        bVar.c = z;
        bVar.e = message.userinfo;
        bVar.a(i);
        bVar.a();
    }

    private void a(String str, String str2) {
        com.taobao.accs.ut.a.e eVar = new com.taobao.accs.ut.a.e();
        eVar.a = UtilityImpl.l(this.i);
        eVar.c = str;
        eVar.d = "" + System.currentTimeMillis();
        eVar.f = "";
        eVar.e = str2;
        eVar.b = "";
        eVar.a();
    }

    public void h() {
        try {
            ThreadPoolExecutorFactory.getScheduledExecutor().execute(this.p);
        } catch (Throwable th) {
            ALog.e(this.m, "restoreTraffics", th, new Object[0]);
        }
    }

    public void a(TrafficsMonitor.a aVar) {
        try {
            ThreadPoolExecutorFactory.getScheduledExecutor().execute(new e(this, aVar));
        } catch (Throwable th) {
            ALog.e(this.m, "addTrafficsInfo", th, new Object[0]);
        }
    }
}
