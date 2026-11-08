package org.android.spdy;

import org.android.spdy.ProtectedPointer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class ProtectedPointerTest {

    static class Data {
        private int i = 0;

        Data() {
        }

        public void work() {
            System.out.println("work");
            if (this.i == 1) {
                System.exit(-1);
            }
        }

        public void destroy() {
            System.out.println("destroy");
            this.i = 1;
        }
    }

    public static void test_sequece(ProtectedPointer protectedPointer) {
        protectedPointer.release();
    }

    public static void test_close_with_work(final ProtectedPointer protectedPointer) {
        Thread thread = new Thread(new Runnable() { // from class: org.android.spdy.ProtectedPointerTest.1
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    if (protectedPointer.enter()) {
                        ((Data) protectedPointer.getData()).work();
                        protectedPointer.exit();
                    } else {
                        System.out.println("the data has been destroy");
                    }
                }
            }
        });
        new Thread(new Runnable() { // from class: org.android.spdy.ProtectedPointerTest.2
            @Override // java.lang.Runnable
            public void run() {
                protectedPointer.release();
            }
        }).run();
        thread.run();
    }

    public static void test_close_anywhere1(ProtectedPointer protectedPointer) {
        if (protectedPointer.enter()) {
            Data data = (Data) protectedPointer.getData();
            protectedPointer.release();
            data.work();
            protectedPointer.exit();
        }
    }

    public static void main(String[] strArr) {
        for (int i = 0; i < 1; i++) {
            ProtectedPointer protectedPointer = new ProtectedPointer(new Data());
            protectedPointer.setHow2close(new ProtectedPointer.ProtectedPointerOnClose() { // from class: org.android.spdy.ProtectedPointerTest.3
                @Override // org.android.spdy.ProtectedPointer.ProtectedPointerOnClose
                public void close(Object obj) {
                    ((Data) obj).destroy();
                }
            });
            test_close_with_work(protectedPointer);
        }
    }
}
