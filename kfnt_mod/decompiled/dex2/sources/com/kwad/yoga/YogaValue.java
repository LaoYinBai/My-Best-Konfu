package com.kwad.yoga;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class YogaValue {
    public final YogaUnit unit;
    public final float value;
    static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    static final YogaValue AUTO = new YogaValue(Float.NaN, YogaUnit.AUTO);

    YogaValue(float f, int i) {
        this(f, YogaUnit.fromInt(i));
    }

    public YogaValue(float f, YogaUnit yogaUnit) {
        this.value = f;
        this.unit = yogaUnit;
    }

    public static YogaValue parse(String str) {
        if (str == null) {
            return null;
        }
        return "undefined".equals(str) ? UNDEFINED : "auto".equals(str) ? AUTO : str.endsWith("%") ? new YogaValue(Float.parseFloat(str.substring(0, str.length() - 1)), YogaUnit.PERCENT) : new YogaValue(Float.parseFloat(str), YogaUnit.POINT);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof YogaValue)) {
            return false;
        }
        YogaValue yogaValue = (YogaValue) obj;
        if (this.unit == yogaValue.unit) {
            return this.unit == YogaUnit.UNDEFINED || this.unit == YogaUnit.AUTO || Float.compare(this.value, yogaValue.value) == 0;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }

    public String toString() {
        switch (this.unit) {
            case UNDEFINED:
                return "undefined";
            case POINT:
                return Float.toString(this.value);
            case PERCENT:
                return this.value + "%";
            case AUTO:
                return "auto";
            default:
                throw new IllegalStateException();
        }
    }
}
