package org.android.spdy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class SessionInfo {
    private static int INVALID_PUBLIC_SEQNUM = -1;
    private String domain;
    private String host;
    private int mode;
    private int port;
    private String proxyHost;
    private int proxyPort;
    private SessionCb sessionCb;
    private Object sessionUserData;
    private String certHost = null;
    private int pubkey_seqnum = INVALID_PUBLIC_SEQNUM;
    private int connTimeoutMs = -1;

    public SessionInfo(String str, int i, String str2, String str3, int i2, Object obj, SessionCb sessionCb, int i3) {
        this.host = str;
        this.port = i;
        this.domain = str2;
        this.proxyHost = str3;
        this.proxyPort = i2;
        this.sessionUserData = obj;
        this.sessionCb = sessionCb;
        this.mode = i3;
    }

    String getAuthority() {
        if (this.proxyHost != null && this.proxyPort != 0) {
            return this.host + ":" + this.port + "/" + this.proxyHost + ":" + this.proxyPort;
        }
        return this.host + ":" + this.port;
    }

    String getCertHost() {
        if (this.pubkey_seqnum != INVALID_PUBLIC_SEQNUM) {
            return null;
        }
        return this.certHost;
    }

    public void setCertHost(String str) {
        this.certHost = str;
    }

    Object getSessonUserData() {
        return this.sessionUserData;
    }

    SessionCb getSessionCb() {
        return this.sessionCb;
    }

    int getMode() {
        return this.mode;
    }

    String getDomain() {
        return this.domain;
    }

    public void setConnectionTimeoutMs(int i) {
        this.connTimeoutMs = i;
    }

    int getConnectionTimeoutMs() {
        return this.connTimeoutMs;
    }

    public void setPubKeySeqNum(int i) {
        this.pubkey_seqnum = i;
    }

    int getPubKeySeqNum() {
        return this.pubkey_seqnum;
    }
}
