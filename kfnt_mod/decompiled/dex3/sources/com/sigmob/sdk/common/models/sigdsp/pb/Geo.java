package com.sigmob.sdk.common.models.sigdsp.pb;

import android.os.Parcelable;
import com.sigmob.wire.AndroidMessage;
import com.sigmob.wire.FieldEncoding;
import com.sigmob.wire.Message;
import com.sigmob.wire.ProtoAdapter;
import com.sigmob.wire.ProtoReader;
import com.sigmob.wire.ProtoWriter;
import com.sigmob.wire.WireField;
import com.sigmob.wire.internal.Internal;
import com.sigmob.wire.okio.ByteString;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Geo extends AndroidMessage<Geo, Builder> {
    public static final String DEFAULT_COUNTRY = "";
    public static final String DEFAULT_LANGUAGE = "";
    public static final String DEFAULT_REGION_CODE = "";
    public static final String DEFAULT_SECONDSFROMGMT = "";
    public static final String DEFAULT_TIMEZONE = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#DOUBLE", tag = 9)
    public final Double accuracy;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 5)
    public final Long city_code;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String country;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String language;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float lat;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
    public final Float lon;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String region_code;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String secondsFromGMT;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String timeZone;
    public static final ProtoAdapter<Geo> ADAPTER = new ProtoAdapter_Geo();
    public static final Parcelable.Creator<Geo> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Float DEFAULT_LAT = Float.valueOf(0.0f);
    public static final Float DEFAULT_LON = Float.valueOf(0.0f);
    public static final Long DEFAULT_CITY_CODE = 0L;
    public static final Double DEFAULT_ACCURACY = Double.valueOf(0.0d);

    public static final class Builder extends Message.Builder<Geo, Builder> {
        public Float lat = Geo.DEFAULT_LAT;
        public Float lon = Geo.DEFAULT_LON;
        public String language = "";
        public String timeZone = "";
        public Long city_code = Geo.DEFAULT_CITY_CODE;
        public String country = "";
        public String region_code = "";
        public String secondsFromGMT = "";
        public Double accuracy = Geo.DEFAULT_ACCURACY;

        public Builder accuracy(Double d) {
            this.accuracy = d;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public Geo build() {
            return new Geo(this.lat, this.lon, this.language, this.timeZone, this.city_code, this.country, this.region_code, this.secondsFromGMT, this.accuracy, super.buildUnknownFields());
        }

        public Builder city_code(Long l) {
            this.city_code = l;
            return this;
        }

        public Builder country(String str) {
            this.country = str;
            return this;
        }

        public Builder language(String str) {
            this.language = str;
            return this;
        }

        public Builder lat(Float f) {
            this.lat = f;
            return this;
        }

        public Builder lon(Float f) {
            this.lon = f;
            return this;
        }

        public Builder region_code(String str) {
            this.region_code = str;
            return this;
        }

        public Builder secondsFromGMT(String str) {
            this.secondsFromGMT = str;
            return this;
        }

        public Builder timeZone(String str) {
            this.timeZone = str;
            return this;
        }
    }

    private static final class ProtoAdapter_Geo extends ProtoAdapter<Geo> {
        public ProtoAdapter_Geo() {
            super(FieldEncoding.LENGTH_DELIMITED, Geo.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Geo decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                switch (iNextTag) {
                    case 1:
                        builder.lat(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        builder.lon(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        builder.language(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.timeZone(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.city_code(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 6:
                        builder.country(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 7:
                        builder.region_code(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 8:
                        builder.secondsFromGMT(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 9:
                        builder.accuracy(ProtoAdapter.DOUBLE.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Geo geo) {
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, geo.lat);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, geo.lon);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, geo.language);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, geo.timeZone);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 5, geo.city_code);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, geo.country);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, geo.region_code);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, geo.secondsFromGMT);
            ProtoAdapter.DOUBLE.encodeWithTag(protoWriter, 9, geo.accuracy);
            protoWriter.writeBytes(geo.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Geo geo) {
            return ProtoAdapter.FLOAT.encodedSizeWithTag(1, geo.lat) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, geo.lon) + ProtoAdapter.STRING.encodedSizeWithTag(3, geo.language) + ProtoAdapter.STRING.encodedSizeWithTag(4, geo.timeZone) + ProtoAdapter.UINT64.encodedSizeWithTag(5, geo.city_code) + ProtoAdapter.STRING.encodedSizeWithTag(6, geo.country) + ProtoAdapter.STRING.encodedSizeWithTag(7, geo.region_code) + ProtoAdapter.STRING.encodedSizeWithTag(8, geo.secondsFromGMT) + ProtoAdapter.DOUBLE.encodedSizeWithTag(9, geo.accuracy) + geo.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Geo redact(Geo geo) {
            Builder builderNewBuilder = geo.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Geo(Float f, Float f2, String str, String str2, Long l, String str3, String str4, String str5, Double d) {
        this(f, f2, str, str2, l, str3, str4, str5, d, ByteString.EMPTY);
    }

    public Geo(Float f, Float f2, String str, String str2, Long l, String str3, String str4, String str5, Double d, ByteString byteString) {
        super(ADAPTER, byteString);
        this.lat = f;
        this.lon = f2;
        this.language = str;
        this.timeZone = str2;
        this.city_code = l;
        this.country = str3;
        this.region_code = str4;
        this.secondsFromGMT = str5;
        this.accuracy = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Geo)) {
            return false;
        }
        Geo geo = (Geo) obj;
        return unknownFields().equals(geo.unknownFields()) && Internal.equals(this.lat, geo.lat) && Internal.equals(this.lon, geo.lon) && Internal.equals(this.language, geo.language) && Internal.equals(this.timeZone, geo.timeZone) && Internal.equals(this.city_code, geo.city_code) && Internal.equals(this.country, geo.country) && Internal.equals(this.region_code, geo.region_code) && Internal.equals(this.secondsFromGMT, geo.secondsFromGMT) && Internal.equals(this.accuracy, geo.accuracy);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((unknownFields().hashCode() * 37) + (this.lat != null ? this.lat.hashCode() : 0)) * 37) + (this.lon != null ? this.lon.hashCode() : 0)) * 37) + (this.language != null ? this.language.hashCode() : 0)) * 37) + (this.timeZone != null ? this.timeZone.hashCode() : 0)) * 37) + (this.city_code != null ? this.city_code.hashCode() : 0)) * 37) + (this.country != null ? this.country.hashCode() : 0)) * 37) + (this.region_code != null ? this.region_code.hashCode() : 0)) * 37) + (this.secondsFromGMT != null ? this.secondsFromGMT.hashCode() : 0)) * 37) + (this.accuracy != null ? this.accuracy.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.lat = this.lat;
        builder.lon = this.lon;
        builder.language = this.language;
        builder.timeZone = this.timeZone;
        builder.city_code = this.city_code;
        builder.country = this.country;
        builder.region_code = this.region_code;
        builder.secondsFromGMT = this.secondsFromGMT;
        builder.accuracy = this.accuracy;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.lat != null) {
            sb.append(", lat=");
            sb.append(this.lat);
        }
        if (this.lon != null) {
            sb.append(", lon=");
            sb.append(this.lon);
        }
        if (this.language != null) {
            sb.append(", language=");
            sb.append(this.language);
        }
        if (this.timeZone != null) {
            sb.append(", timeZone=");
            sb.append(this.timeZone);
        }
        if (this.city_code != null) {
            sb.append(", city_code=");
            sb.append(this.city_code);
        }
        if (this.country != null) {
            sb.append(", country=");
            sb.append(this.country);
        }
        if (this.region_code != null) {
            sb.append(", region_code=");
            sb.append(this.region_code);
        }
        if (this.secondsFromGMT != null) {
            sb.append(", secondsFromGMT=");
            sb.append(this.secondsFromGMT);
        }
        if (this.accuracy != null) {
            sb.append(", accuracy=");
            sb.append(this.accuracy);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Geo{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
