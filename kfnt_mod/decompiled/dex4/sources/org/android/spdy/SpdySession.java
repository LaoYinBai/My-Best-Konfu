package org.android.spdy;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicBoolean;
import org.android.spdy.ProtectedPointer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public final class SpdySession {
    private static volatile int count;
    private SpdyAgent agent;
    private String authority;
    private String domain;
    private Handler handler;
    Intenalcb intenalcb;
    private int mode;
    private int pubkey_seqnum;
    SessionCb sessionCallBack;
    private volatile long sessionNativePtr;
    private NetSparseArray<SpdyStreamContext> spdyStream;
    private HandlerThread thread;
    private Object userData;
    private AtomicBoolean closed = new AtomicBoolean();
    private boolean sessionClearedFromSessionMgr = false;
    private Object lock = new Object();
    private int streamcount = 1;
    volatile int refcount = 1;
    private ProtectedPointer pptr4sessionNativePtr = new ProtectedPointer(this);

    /* JADX INFO: Access modifiers changed from: private */
    public native int NotifyNotInvokeAnyMoreN(long j);

    private native int sendCustomControlFrameN(long j, int i, int i2, int i3, int i4, byte[] bArr);

    private native int sendHeadersN(long j, int i, String[] strArr, boolean z);

    private native int setOptionN(long j, int i, int i2);

    private native int streamCloseN(long j, int i, int i2);

    private native int streamSendDataN(long j, int i, byte[] bArr, int i2, int i3, boolean z);

    private native int submitBioPingN(long j);

    private native int submitPingN(long j);

    private native int submitRequestN(long j, String str, byte b, String[] strArr, byte[] bArr, boolean z, int i, int i2, int i3);

    SpdySession(long j, SpdyAgent spdyAgent, String str, String str2, SessionCb sessionCb, int i, int i2, Object obj) {
        this.spdyStream = null;
        this.sessionCallBack = null;
        this.pubkey_seqnum = 0;
        this.userData = null;
        this.sessionNativePtr = j;
        this.pptr4sessionNativePtr.setHow2close(new ProtectedPointer.ProtectedPointerOnClose() { // from class: org.android.spdy.SpdySession.1
            @Override // org.android.spdy.ProtectedPointer.ProtectedPointerOnClose
            public void close(Object obj2) {
                SpdySession spdySession = (SpdySession) obj2;
                spdySession.NotifyNotInvokeAnyMoreN(spdySession.sessionNativePtr);
                spdySession.setSessionNativePtr(0L);
            }
        });
        this.agent = spdyAgent;
        this.authority = str;
        this.intenalcb = new SpdySessionCallBack();
        this.domain = str2;
        this.spdyStream = new NetSparseArray<>(5);
        this.sessionCallBack = sessionCb;
        this.pubkey_seqnum = i2;
        this.mode = i;
        this.userData = obj;
        this.closed.set(false);
    }

    public int getRefCount() {
        return this.refcount;
    }

    void increRefCount() {
        this.refcount++;
    }

    private String getAuthority() {
        return this.authority;
    }

    public String getDomain() {
        return this.domain;
    }

    public Object getUserData() {
        return this.userData;
    }

    int putSpdyStreamCtx(SpdyStreamContext spdyStreamContext) {
        int i;
        synchronized (this.lock) {
            i = this.streamcount;
            this.streamcount = i + 1;
            this.spdyStream.put(i, spdyStreamContext);
        }
        return i;
    }

    SpdyStreamContext getSpdyStream(int i) {
        SpdyStreamContext spdyStreamContext;
        if (i <= 0) {
            return null;
        }
        synchronized (this.lock) {
            spdyStreamContext = this.spdyStream.get(i);
        }
        return spdyStreamContext;
    }

    void removeSpdyStream(int i) {
        if (i > 0) {
            synchronized (this.lock) {
                this.spdyStream.remove(i);
            }
        }
    }

    public SpdyStreamContext[] getAllStreamCb() {
        SpdyStreamContext[] spdyStreamContextArr;
        synchronized (this.lock) {
            int size = this.spdyStream.size();
            if (size > 0) {
                spdyStreamContextArr = new SpdyStreamContext[size];
                this.spdyStream.toArray(spdyStreamContextArr);
            } else {
                spdyStreamContextArr = null;
            }
        }
        return spdyStreamContextArr;
    }

    public void clearAllStreamCb() {
        spduLog.Logd("tnet-jni", "[SpdySession.clearAllStreamCb] - ");
        synchronized (this.lock) {
            this.spdyStream.clear();
        }
    }

    SpdyAgent getSpdyAgent() {
        return this.agent;
    }

    Handler getMsgHandler() {
        return this.handler;
    }

    long getSessionNativePtr() {
        return this.sessionNativePtr;
    }

    void setSessionNativePtr(long j) {
        this.sessionNativePtr = j;
    }

    public int setOption(int i, int i2) throws SpdyErrorException {
        int optionN;
        sessionIsOpen();
        if (this.pptr4sessionNativePtr.enter()) {
            optionN = setOptionN(this.sessionNativePtr, i, i2);
            this.pptr4sessionNativePtr.exit();
        } else {
            optionN = TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS;
        }
        if (optionN == 0) {
            return optionN;
        }
        throw new SpdyErrorException("setOption error: " + optionN, optionN);
    }

    public int submitPing() throws SpdyErrorException {
        int iSubmitPingN;
        sessionIsOpen();
        if (this.pptr4sessionNativePtr.enter()) {
            iSubmitPingN = submitPingN(this.sessionNativePtr);
            this.pptr4sessionNativePtr.exit();
        } else {
            iSubmitPingN = TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS;
        }
        if (iSubmitPingN == 0) {
            return iSubmitPingN;
        }
        throw new SpdyErrorException("submitPing error: " + iSubmitPingN, iSubmitPingN);
    }

    @Deprecated
    public int submitBioPing() throws SpdyErrorException {
        int iSubmitBioPingN;
        sessionIsOpen();
        if (this.pptr4sessionNativePtr.enter()) {
            iSubmitBioPingN = submitBioPingN(this.sessionNativePtr);
            this.pptr4sessionNativePtr.exit();
        } else {
            iSubmitBioPingN = TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS;
        }
        if (iSubmitBioPingN == 0) {
            return iSubmitBioPingN;
        }
        throw new SpdyErrorException("submitBioPing error: " + iSubmitBioPingN, iSubmitBioPingN);
    }

    public int streamReset(long j, int i) throws SpdyErrorException {
        int iStreamCloseN;
        sessionIsOpen();
        spduLog.Logd("tnet-jni", "[SpdySession.streamReset] - ");
        if (this.pptr4sessionNativePtr.enter()) {
            iStreamCloseN = streamCloseN(this.sessionNativePtr, (int) j, i);
            this.pptr4sessionNativePtr.exit();
        } else {
            iStreamCloseN = TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS;
        }
        if (iStreamCloseN == 0) {
            return iStreamCloseN;
        }
        throw new SpdyErrorException("streamReset error: " + iStreamCloseN, iStreamCloseN);
    }

    public int sendCustomControlFrame(int i, int i2, int i3, int i4, byte[] bArr) throws SpdyErrorException {
        int iSendCustomControlFrameN;
        sessionIsOpen();
        if (bArr != null && bArr.length <= 0) {
            bArr = null;
        }
        byte[] bArr2 = bArr;
        spduLog.Logi("tnet-jni", "[sendCustomControlFrame] - type: " + i2);
        if (this.pptr4sessionNativePtr.enter()) {
            iSendCustomControlFrameN = sendCustomControlFrameN(this.sessionNativePtr, i, i2, i3, i4, bArr2);
            this.pptr4sessionNativePtr.exit();
        } else {
            iSendCustomControlFrameN = TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS;
        }
        if (iSendCustomControlFrameN == 0) {
            return iSendCustomControlFrameN;
        }
        throw new SpdyErrorException("sendCustomControlFrame error: " + iSendCustomControlFrameN, iSendCustomControlFrameN);
    }

    public int submitRequest(SpdyRequest spdyRequest, SpdyDataProvider spdyDataProvider, Object obj, Spdycb spdycb) throws SpdyErrorException {
        int iSubmitRequestN;
        if (spdyRequest == null || obj == null || spdyRequest.getAuthority() == null) {
            throw new SpdyErrorException("submitRequest error: -1102", TnetStatusCode.TNET_JNI_ERR_INVLID_PARAM);
        }
        sessionIsOpen();
        byte[] bArrDataproviderToByteArray = SpdyAgent.dataproviderToByteArray(spdyRequest, spdyDataProvider);
        if (bArrDataproviderToByteArray != null && bArrDataproviderToByteArray.length <= 0) {
            bArrDataproviderToByteArray = null;
        }
        byte[] bArr = bArrDataproviderToByteArray;
        boolean z = spdyDataProvider != null ? spdyDataProvider.finished : true;
        SpdyStreamContext spdyStreamContext = new SpdyStreamContext(obj, spdycb);
        int iPutSpdyStreamCtx = putSpdyStreamCtx(spdyStreamContext);
        String[] strArrMapToByteArray = SpdyAgent.mapToByteArray(spdyRequest.getHeaders());
        spduLog.Logi("tnet-jni", "index=" + iPutSpdyStreamCtx + "  starttime=" + System.currentTimeMillis());
        if (this.pptr4sessionNativePtr.enter()) {
            iSubmitRequestN = submitRequestN(this.sessionNativePtr, spdyRequest.getUrlPath(), (byte) spdyRequest.getPriority(), strArrMapToByteArray, bArr, z, iPutSpdyStreamCtx, spdyRequest.getRequestTimeoutMs(), spdyRequest.getRequestRdTimeoutMs());
            this.pptr4sessionNativePtr.exit();
        } else {
            iSubmitRequestN = TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS;
        }
        spduLog.Logi("tnet-jni", "index=" + iPutSpdyStreamCtx + "   calltime=" + System.currentTimeMillis());
        if (iSubmitRequestN < 0) {
            removeSpdyStream(iPutSpdyStreamCtx);
            throw new SpdyErrorException("submitRequest error: " + iSubmitRequestN, iSubmitRequestN);
        }
        spdyStreamContext.streamId = iSubmitRequestN;
        return iSubmitRequestN;
    }

    void sessionIsOpen() {
        if (this.closed.get()) {
            throw new SpdyErrorException("session is already closed: -1104", TnetStatusCode.TNET_JNI_ERR_ASYNC_CLOSE);
        }
    }

    public int cleanUp() {
        spduLog.Logd("tnet-jni", "[SpdySession.cleanUp] - ");
        if (this.closed.getAndSet(true)) {
            return 0;
        }
        this.agent.removeSession(this);
        return closeprivate();
    }

    int closeInternal() {
        if (this.closed.getAndSet(true)) {
            return 0;
        }
        return closeprivate();
    }

    public int closeSession() {
        int iCloseSession;
        spduLog.Logd("tnet-jni", "[SpdySession.closeSession] - ");
        synchronized (this.lock) {
            iCloseSession = 0;
            if (!this.sessionClearedFromSessionMgr) {
                spduLog.Logd("tnet-jni", "[SpdySession.closeSession] - " + this.authority);
                this.agent.clearSpdySession(this.authority, this.domain, this.mode);
                this.sessionClearedFromSessionMgr = true;
                if (this.pptr4sessionNativePtr.enter()) {
                    try {
                        try {
                            iCloseSession = this.agent.closeSession(this.sessionNativePtr);
                        } catch (UnsatisfiedLinkError e) {
                            e.printStackTrace();
                            this.pptr4sessionNativePtr.exit();
                        }
                    } finally {
                        this.pptr4sessionNativePtr.exit();
                    }
                } else {
                    iCloseSession = TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS;
                }
            }
        }
        return iCloseSession;
    }

    private int closeprivate() {
        synchronized (this.lock) {
            if (!this.sessionClearedFromSessionMgr) {
                this.agent.clearSpdySession(this.authority, this.domain, this.mode);
                this.sessionClearedFromSessionMgr = true;
            }
        }
        synchronized (this.lock) {
            SpdyStreamContext[] allStreamCb = getAllStreamCb();
            if (allStreamCb != null) {
                for (SpdyStreamContext spdyStreamContext : allStreamCb) {
                    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyStreamCloseCallback] unfinished stm=" + spdyStreamContext.streamId);
                    spdyStreamContext.callBack.spdyStreamCloseCallback(this, (long) spdyStreamContext.streamId, TnetStatusCode.EASY_REASON_CONN_NOT_EXISTS, spdyStreamContext.streamContext, null);
                }
            }
            this.spdyStream.clear();
        }
        return 0;
    }

    void releasePptr() {
        this.pptr4sessionNativePtr.release();
    }
}
