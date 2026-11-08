package com.ss.android.socialbase.downloader.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadStenographer {
    private static final int MAX_NODE_COUNT = 20;
    private static final int SECONDS_TO_MILLS = 1000;
    private int count;
    private Node head;
    private int maxCount;
    private Node tail;

    public DownloadStenographer() {
        this.maxCount = 10;
    }

    public DownloadStenographer(int i) {
        this.maxCount = i > 20 ? 20 : i;
    }

    public boolean markProgress(long j, long j2) {
        synchronized (this) {
            Node node = this.head;
            if (node != null) {
                if (j >= node.curBytes && j2 >= node.when) {
                    Node node2 = node.next;
                    if (node2 != null && j2 - node2.when < 1000) {
                        node.curBytes = j;
                        node.when = j2;
                        return true;
                    }
                }
                return false;
            }
            Node nodeObtainNode = obtainNode();
            nodeObtainNode.curBytes = j;
            nodeObtainNode.when = j2;
            if (node != null) {
                nodeObtainNode.next = node;
                node.prev = nodeObtainNode;
            }
            this.head = nodeObtainNode;
            return true;
        }
    }

    public long getRecentDownloadSpeed(long j, long j2) {
        synchronized (this) {
            Node node = this.head;
            if (node == null) {
                return -1L;
            }
            Node nodeFindFirstNodeNearWhen = findFirstNodeNearWhen(j);
            if (nodeFindFirstNodeNearWhen == null) {
                return -1L;
            }
            long j3 = node.curBytes - nodeFindFirstNodeNearWhen.curBytes;
            long j4 = j2 - nodeFindFirstNodeNearWhen.when;
            if (j3 < 0 || j4 <= 0) {
                return -1L;
            }
            return j3 / j4;
        }
    }

    private Node obtainNode() {
        Node node;
        if (this.count >= this.maxCount && (node = this.tail) != null) {
            Node node2 = node.prev;
            node.prev = null;
            this.tail = node2;
            if (node2 != null) {
                node2.next = null;
            }
            return node;
        }
        this.count++;
        return new Node();
    }

    private Node findFirstNodeNearWhen(long j) {
        Node node;
        Node node2 = this.head;
        Node node3 = null;
        while (true) {
            node = node3;
            node3 = node2;
            if (node3 == null || node3.when <= j) {
                break;
            }
            node2 = node3.next;
        }
        return (node3 == null || node == null || node3 == node || j - node3.when >= node.when - j) ? node : node3;
    }

    private static class Node {
        long curBytes;
        Node next;
        Node prev;
        long when;

        private Node() {
        }
    }
}
