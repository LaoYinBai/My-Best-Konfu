package com.huawei.hmf.tasks;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g<TResult> {
    private final com.huawei.hmf.tasks.a.e<TResult> a = new com.huawei.hmf.tasks.a.e<>();

    public g() {
    }

    public g(a aVar) {
        aVar.a(new Runnable() { // from class: com.huawei.hmf.tasks.g.1
            @Override // java.lang.Runnable
            public void run() {
                g.this.a.e();
            }
        });
    }

    public f<TResult> a() {
        return this.a;
    }

    public void a(Exception exc) {
        this.a.a(exc);
    }

    public void a(TResult tresult) {
        this.a.a((com.huawei.hmf.tasks.a.e<TResult>) tresult);
    }
}
