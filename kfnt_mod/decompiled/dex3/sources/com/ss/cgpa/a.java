package com.ss.cgpa;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private Selector a = null;
    private boolean b = false;
    private String c = getClass().getName();

    private SocketChannelStream a(SelectionKey selectionKey) {
        Object objAttachment = selectionKey.attachment();
        if (objAttachment == null || !(objAttachment instanceof SocketChannelStream)) {
            return null;
        }
        return (SocketChannelStream) objAttachment;
    }

    protected int a(int i) {
        if (!this.b) {
            try {
                if (this.a != null) {
                    return this.a.select(i);
                }
            } catch (Exception e) {
                Log.e(this.c, e.toString());
            }
        }
        return -1;
    }

    protected void a(String str) {
        this.c = str;
    }

    protected boolean a() throws IOException {
        try {
            if (this.a == null) {
                this.a = Selector.open();
            }
        } catch (Exception e) {
            Log.e(this.c, e.toString());
            b();
        }
        return this.a != null;
    }

    protected void b() throws IOException {
        Selector selector = this.a;
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception unused) {
            }
            this.a = null;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002a -> B:17:0x0033). Please report as a decompilation issue!!! */
    protected boolean b(SelectionKey selectionKey) throws IOException {
        int i;
        SocketChannelStream socketChannelStreamA = a(selectionKey);
        boolean z = false;
        if (socketChannelStreamA == null) {
            return false;
        }
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer readByteBuffer = socketChannelStreamA.getReadByteBuffer();
        try {
            i = socketChannel.read(readByteBuffer);
        } catch (Exception e) {
            Log.e(this.c, e.toString());
        }
        if (i > 0) {
            if (!socketChannelStreamA.procByteBuffer()) {
                z = true;
            }
        } else if (i != 0 || !readByteBuffer.hasRemaining()) {
            z = true;
        }
        if (z) {
            selectionKey.cancel();
            try {
                socketChannel.close();
                socketChannelStreamA.close();
            } catch (Exception e2) {
                Log.e(this.c, e2.toString());
            }
        } else if (socketChannelStreamA.getWriteByteBuffer().position() > 0) {
            selectionKey.interestOps(selectionKey.interestOps() | 4);
        }
        return !z;
    }

    protected Set<SelectionKey> c() {
        return this.a.selectedKeys();
    }

    protected boolean c(SelectionKey selectionKey) throws IOException {
        SocketChannelStream socketChannelStreamA = a(selectionKey);
        boolean z = false;
        if (socketChannelStreamA == null) {
            return false;
        }
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer writeByteBuffer = socketChannelStreamA.getWriteByteBuffer();
        writeByteBuffer.flip();
        boolean z2 = true;
        try {
            socketChannel.write(writeByteBuffer);
            if (writeByteBuffer.hasRemaining()) {
                selectionKey.interestOps(selectionKey.interestOps() | 4);
            } else {
                z = true;
            }
        } catch (Exception e) {
            Log.e(this.c, e.toString());
            z = true;
            z2 = false;
        }
        writeByteBuffer.compact();
        if (z) {
            selectionKey.interestOps(selectionKey.interestOps() & (-5));
        }
        return z2;
    }

    protected Selector d() {
        return this.a;
    }
}
