package anet.channel.monitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e {

    /* renamed from: b, reason: collision with root package name */
    private double f470b;

    /* renamed from: c, reason: collision with root package name */
    private double f471c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;

    /* renamed from: a, reason: collision with root package name */
    private long f469a = 0;
    private double i = 0.0d;
    private double j = 0.0d;
    private double k = 0.0d;

    e() {
    }

    public double a(double d, double d2) {
        double d3 = d / d2;
        if (d3 < 8.0d) {
            if (this.f469a != 0) {
                return this.k;
            }
            this.k = d3;
            return this.k;
        }
        if (this.f469a == 0) {
            this.i = d3;
            this.h = this.i;
            this.d = this.h * 0.1d;
            this.f471c = this.h * 0.02d;
            this.e = this.h * 0.1d * this.h;
        } else if (this.f469a == 1) {
            this.j = d3;
            this.h = this.j;
        } else {
            double d4 = d3 - this.j;
            this.i = this.j;
            this.j = d3;
            this.f470b = d3 / 0.95d;
            this.g = this.f470b - (this.h * 0.95d);
            char c2 = 0;
            double dSqrt = Math.sqrt(this.d);
            if (this.g >= 4.0d * dSqrt) {
                this.g = (this.g * 0.75d) + (dSqrt * 2.0d);
                c2 = 1;
            } else if (this.g <= (-4.0d) * dSqrt) {
                this.g = (dSqrt * (-1.0d)) + (this.g * 0.75d);
                c2 = 2;
            }
            this.d = Math.min(Math.max(Math.abs((this.d * 1.05d) - ((this.g * 0.0025d) * this.g)), this.d * 0.8d), this.d * 1.25d);
            this.f = this.e / ((0.9025d * this.e) + this.d);
            this.h = this.h + (1.0526315789473684d * d4) + (this.f * this.g);
            if (c2 == 1) {
                this.h = Math.min(this.h, this.f470b);
            } else if (c2 == 2) {
                this.h = Math.max(this.h, this.f470b);
            }
            this.e = (1.0d - (0.95d * this.f)) * (this.e + this.f471c);
        }
        if (this.h < 0.0d) {
            this.k = this.j * 0.7d;
            this.h = this.k;
        } else {
            this.k = this.h;
        }
        return this.k;
    }

    public void a() {
        this.f469a = 0L;
        this.k = 0.0d;
    }
}
