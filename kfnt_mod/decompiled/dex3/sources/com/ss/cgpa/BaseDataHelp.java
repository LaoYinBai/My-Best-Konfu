package com.ss.cgpa;

import android.util.Log;
import com.ss.union.game.sdk.feedback.picture.select.d;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseDataHelp implements SocketChannelStream {
    private int msgSn = 0;
    private final int maxBuferSizes = 8192;
    private final int maxCacheWriteMsgCount = d.d;
    private Queue<Long> readCgpMsgs = new LinkedList();
    private List<Long> writeMsgList = new ArrayList();
    private ByteBuffer readByteBuffer = ByteBuffer.allocate(8192);
    private ByteBuffer writeByteBuffer = ByteBuffer.allocate(8192);
    private ReentrantLock lock = new ReentrantLock();
    protected NativeDataHelp nativeDataHelp = new NativeDataHelp();
    private String debugTag = getClass().getName();

    @Override // com.ss.cgpa.SocketChannelStream
    public void close() {
        while (this.writeMsgList.size() > 0) {
            long jLongValue = this.writeMsgList.get(0).longValue();
            this.writeMsgList.remove(0);
            this.nativeDataHelp.freeMsgInfo(jLongValue);
        }
    }

    public abstract byte[] decryptMsg(byte[] bArr);

    public abstract byte[] encryptMsg(byte[] bArr);

    public boolean fastWriteMsg(long j) {
        if (this.writeMsgList.size() > 1024) {
            return false;
        }
        this.writeMsgList.add(Long.valueOf(j));
        while (this.writeMsgList.size() > 0) {
            long jLongValue = this.writeMsgList.get(0).longValue();
            byte[] bArrPackCgpMsgInfo = this.nativeDataHelp.packCgpMsgInfo(jLongValue, this.msgSn);
            if (bArrPackCgpMsgInfo.length > this.writeByteBuffer.remaining()) {
                break;
            }
            this.writeByteBuffer.put(bArrPackCgpMsgInfo);
            this.msgSn++;
            this.writeMsgList.remove(0);
            this.nativeDataHelp.freeMsgInfo(jLongValue);
        }
        return true;
    }

    @Override // com.ss.cgpa.SocketChannelStream
    public ByteBuffer getReadByteBuffer() {
        return this.readByteBuffer;
    }

    @Override // com.ss.cgpa.SocketChannelStream
    public ByteBuffer getWriteByteBuffer() {
        return this.writeByteBuffer;
    }

    @Override // com.ss.cgpa.SocketChannelStream
    public boolean procByteBuffer() throws IOException {
        int iAvailable;
        this.readByteBuffer.flip();
        int iRemaining = this.readByteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        this.readByteBuffer.get(bArr);
        this.readByteBuffer.clear();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        while (true) {
            iAvailable = byteArrayInputStream.available();
            if (iAvailable <= 0) {
                break;
            }
            long jUnpackCgpMsgInfo = this.nativeDataHelp.unpackCgpMsgInfo(bArr);
            if (jUnpackCgpMsgInfo == 0) {
                break;
            }
            byteArrayInputStream.skip(this.nativeDataHelp.getCgpMsgInfoSizes(jUnpackCgpMsgInfo));
            this.readCgpMsgs.offer(Long.valueOf(jUnpackCgpMsgInfo));
        }
        if (iAvailable > 0) {
            try {
                this.readByteBuffer.put(bArr, iRemaining - iAvailable, iAvailable);
            } catch (Exception e) {
                Log.e(this.debugTag, e.toString());
                return false;
            }
        }
        byteArrayInputStream.close();
        boolean zProcReadCgpMsg = procReadCgpMsg();
        procWriteCgpMsg();
        return zProcReadCgpMsg;
    }

    public boolean procReadCgpMsg() {
        Long lPoll;
        do {
            lPoll = this.readCgpMsgs.poll();
            if (lPoll == null) {
                return true;
            }
        } while (this.nativeDataHelp.procReadCgpMsg(lPoll.longValue()));
        this.readCgpMsgs.clear();
        return false;
    }

    public int procWriteCgpMsg() {
        this.nativeDataHelp.flushCacheMsg();
        int size = this.writeMsgList.size();
        if (size > 0) {
            this.lock.lock();
            while (this.writeMsgList.size() > 0) {
                long jLongValue = this.writeMsgList.get(0).longValue();
                byte[] bArrPackCgpMsgInfo = this.nativeDataHelp.packCgpMsgInfo(jLongValue, this.msgSn);
                if (bArrPackCgpMsgInfo.length > this.writeByteBuffer.remaining()) {
                    break;
                }
                this.writeByteBuffer.put(bArrPackCgpMsgInfo);
                this.msgSn++;
                this.writeMsgList.remove(0);
                this.nativeDataHelp.freeMsgInfo(jLongValue);
            }
            this.lock.unlock();
        }
        return size;
    }

    protected void setDebugTag(String str) {
        this.debugTag = str;
    }

    public boolean writeMsg(long j) {
        if (this.writeMsgList.size() >= 1024) {
            return false;
        }
        this.lock.lock();
        this.writeMsgList.add(Long.valueOf(j));
        this.lock.unlock();
        return true;
    }
}
