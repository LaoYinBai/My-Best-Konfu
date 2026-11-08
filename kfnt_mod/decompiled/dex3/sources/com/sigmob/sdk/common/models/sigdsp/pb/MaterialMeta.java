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
public final class MaterialMeta extends AndroidMessage<MaterialMeta, Builder> {
    public static final String DEFAULT_APP_NAME = "";
    public static final String DEFAULT_BUTTON_TEXT = "";
    public static final String DEFAULT_DEEPLINK_URL = "";
    public static final String DEFAULT_DESC = "";
    public static final String DEFAULT_ENDCARD_MD5 = "";
    public static final String DEFAULT_ENDCARD_URL = "";
    public static final String DEFAULT_HTML_SRC = "";
    public static final String DEFAULT_HTML_URL = "";
    public static final String DEFAULT_ICON_URL = "";
    public static final String DEFAULT_IMAGE_MD5 = "";
    public static final String DEFAULT_IMAGE_SRC = "";
    public static final String DEFAULT_LANDING_PAGE = "";
    public static final String DEFAULT_TITLE = "";
    public static final String DEFAULT_VIDEO_MD5 = "";
    public static final String DEFAULT_VIDEO_URL = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.AdPrivacy#ADAPTER", tag = 36)
    public final AdPrivacy ad_privacy;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 34)
    public final String app_name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 35)
    public final String button_text;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 16)
    public final Integer click_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BYTES", tag = 33)
    public final ByteString closecard_html_snippet;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.CompanionEndcard#ADAPTER", tag = 18)
    public final CompanionEndcard companion;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer creative_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String deeplink_url;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 28)
    public final String desc;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 32)
    public final Boolean disable_auto_deeplink;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 23)
    public final Boolean enable_collapse_tool_bar;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String endcard_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String endcard_url;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 17)
    public final Boolean has_companion_endcard;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BYTES", tag = 7)
    public final ByteString html_snippet;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 38)
    public final String html_src;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 22)
    public final String html_url;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 29)
    public final String icon_url;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String image_md5;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Size#ADAPTER", tag = 13)
    public final Size image_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String image_src;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 15)
    public final Integer image_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer interaction_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String landing_page;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.ResponseNativeAd#ADAPTER", tag = 39)
    public final ResponseNativeAd native_ad;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 24)
    public final Integer open_market_mode;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 25)
    public final Integer play_mode;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 30)
    public final Float score;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 26)
    public final Integer sub_interaction_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 31)
    public final Integer template_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 20)
    public final Integer template_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 27)
    public final String title;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 37)
    public final Integer video_byte_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 5)
    public final Integer video_duration;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String video_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 21)
    public final Integer video_reciprocal_millisecond;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Size#ADAPTER", tag = 6)
    public final Size video_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String video_url;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.WebEventHandle#ADAPTER", tag = 19)
    public final WebEventHandle web_event_handle;
    public static final ProtoAdapter<MaterialMeta> ADAPTER = new ProtoAdapter_MaterialMeta();
    public static final Parcelable.Creator<MaterialMeta> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CREATIVE_TYPE = 0;
    public static final Integer DEFAULT_INTERACTION_TYPE = 0;
    public static final Integer DEFAULT_VIDEO_DURATION = 0;
    public static final ByteString DEFAULT_HTML_SNIPPET = ByteString.EMPTY;
    public static final Integer DEFAULT_IMAGE_TYPE = 0;
    public static final Integer DEFAULT_CLICK_TYPE = 0;
    public static final Boolean DEFAULT_HAS_COMPANION_ENDCARD = false;
    public static final Integer DEFAULT_TEMPLATE_TYPE = 0;
    public static final Integer DEFAULT_VIDEO_RECIPROCAL_MILLISECOND = 0;
    public static final Boolean DEFAULT_ENABLE_COLLAPSE_TOOL_BAR = false;
    public static final Integer DEFAULT_OPEN_MARKET_MODE = 0;
    public static final Integer DEFAULT_PLAY_MODE = 0;
    public static final Integer DEFAULT_SUB_INTERACTION_TYPE = 0;
    public static final Float DEFAULT_SCORE = Float.valueOf(0.0f);
    public static final Integer DEFAULT_TEMPLATE_ID = 0;
    public static final Boolean DEFAULT_DISABLE_AUTO_DEEPLINK = false;
    public static final ByteString DEFAULT_CLOSECARD_HTML_SNIPPET = ByteString.EMPTY;
    public static final Integer DEFAULT_VIDEO_BYTE_SIZE = 0;

    public static final class Builder extends Message.Builder<MaterialMeta, Builder> {
        public AdPrivacy ad_privacy;
        public CompanionEndcard companion;
        public String html_src;
        public Size image_size;
        public ResponseNativeAd native_ad;
        public Integer video_byte_size;
        public Size video_size;
        public WebEventHandle web_event_handle;
        public Integer creative_type = MaterialMeta.DEFAULT_CREATIVE_TYPE;
        public Integer interaction_type = MaterialMeta.DEFAULT_INTERACTION_TYPE;
        public String landing_page = "";
        public String video_url = "";
        public Integer video_duration = MaterialMeta.DEFAULT_VIDEO_DURATION;
        public ByteString html_snippet = MaterialMeta.DEFAULT_HTML_SNIPPET;
        public String endcard_url = "";
        public String video_md5 = "";
        public String endcard_md5 = "";
        public String deeplink_url = "";
        public String image_src = "";
        public String image_md5 = "";
        public Integer image_type = MaterialMeta.DEFAULT_IMAGE_TYPE;
        public Integer click_type = MaterialMeta.DEFAULT_CLICK_TYPE;
        public Boolean has_companion_endcard = MaterialMeta.DEFAULT_HAS_COMPANION_ENDCARD;
        public Integer template_type = MaterialMeta.DEFAULT_TEMPLATE_TYPE;
        public Integer video_reciprocal_millisecond = MaterialMeta.DEFAULT_VIDEO_RECIPROCAL_MILLISECOND;
        public String html_url = "";
        public Boolean enable_collapse_tool_bar = MaterialMeta.DEFAULT_ENABLE_COLLAPSE_TOOL_BAR;
        public Integer open_market_mode = MaterialMeta.DEFAULT_OPEN_MARKET_MODE;
        public Integer play_mode = MaterialMeta.DEFAULT_PLAY_MODE;
        public Integer sub_interaction_type = MaterialMeta.DEFAULT_SUB_INTERACTION_TYPE;
        public String title = "";
        public String desc = "";
        public String icon_url = "";
        public Float score = MaterialMeta.DEFAULT_SCORE;
        public Integer template_id = MaterialMeta.DEFAULT_TEMPLATE_ID;
        public Boolean disable_auto_deeplink = MaterialMeta.DEFAULT_DISABLE_AUTO_DEEPLINK;
        public ByteString closecard_html_snippet = MaterialMeta.DEFAULT_CLOSECARD_HTML_SNIPPET;
        public String app_name = "";
        public String button_text = "";

        public Builder ad_privacy(AdPrivacy adPrivacy) {
            this.ad_privacy = adPrivacy;
            return this;
        }

        public Builder app_name(String str) {
            this.app_name = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public MaterialMeta build() {
            return new MaterialMeta(this.creative_type, this.interaction_type, this.landing_page, this.video_url, this.video_duration, this.video_size, this.html_snippet, this.endcard_url, this.video_md5, this.endcard_md5, this.deeplink_url, this.image_src, this.image_size, this.image_md5, this.image_type, this.click_type, this.has_companion_endcard, this.companion, this.web_event_handle, this.template_type, this.video_reciprocal_millisecond, this.html_url, this.enable_collapse_tool_bar, this.open_market_mode, this.play_mode, this.sub_interaction_type, this.title, this.desc, this.icon_url, this.score, this.template_id, this.disable_auto_deeplink, this.closecard_html_snippet, this.app_name, this.button_text, this.ad_privacy, this.video_byte_size, this.html_src, this.native_ad, super.buildUnknownFields());
        }

        public Builder button_text(String str) {
            this.button_text = str;
            return this;
        }

        public Builder click_type(Integer num) {
            this.click_type = num;
            return this;
        }

        public Builder closecard_html_snippet(ByteString byteString) {
            this.closecard_html_snippet = byteString;
            return this;
        }

        public Builder companion(CompanionEndcard companionEndcard) {
            this.companion = companionEndcard;
            return this;
        }

        public Builder creative_type(Integer num) {
            this.creative_type = num;
            return this;
        }

        public Builder deeplink_url(String str) {
            this.deeplink_url = str;
            return this;
        }

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public Builder disable_auto_deeplink(Boolean bool) {
            this.disable_auto_deeplink = bool;
            return this;
        }

        public Builder enable_collapse_tool_bar(Boolean bool) {
            this.enable_collapse_tool_bar = bool;
            return this;
        }

        public Builder endcard_md5(String str) {
            this.endcard_md5 = str;
            return this;
        }

        public Builder endcard_url(String str) {
            this.endcard_url = str;
            return this;
        }

        public Builder has_companion_endcard(Boolean bool) {
            this.has_companion_endcard = bool;
            return this;
        }

        public Builder html_snippet(ByteString byteString) {
            this.html_snippet = byteString;
            return this;
        }

        public Builder html_src(String str) {
            this.html_src = str;
            return this;
        }

        public Builder html_url(String str) {
            this.html_url = str;
            return this;
        }

        public Builder icon_url(String str) {
            this.icon_url = str;
            return this;
        }

        public Builder image_md5(String str) {
            this.image_md5 = str;
            return this;
        }

        public Builder image_size(Size size) {
            this.image_size = size;
            return this;
        }

        public Builder image_src(String str) {
            this.image_src = str;
            return this;
        }

        public Builder image_type(Integer num) {
            this.image_type = num;
            return this;
        }

        public Builder interaction_type(Integer num) {
            this.interaction_type = num;
            return this;
        }

        public Builder landing_page(String str) {
            this.landing_page = str;
            return this;
        }

        public Builder native_ad(ResponseNativeAd responseNativeAd) {
            this.native_ad = responseNativeAd;
            return this;
        }

        public Builder open_market_mode(Integer num) {
            this.open_market_mode = num;
            return this;
        }

        public Builder play_mode(Integer num) {
            this.play_mode = num;
            return this;
        }

        public Builder score(Float f) {
            this.score = f;
            return this;
        }

        public Builder sub_interaction_type(Integer num) {
            this.sub_interaction_type = num;
            return this;
        }

        public Builder template_id(Integer num) {
            this.template_id = num;
            return this;
        }

        public Builder template_type(Integer num) {
            this.template_type = num;
            return this;
        }

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder video_byte_size(Integer num) {
            this.video_byte_size = num;
            return this;
        }

        public Builder video_duration(Integer num) {
            this.video_duration = num;
            return this;
        }

        public Builder video_md5(String str) {
            this.video_md5 = str;
            return this;
        }

        public Builder video_reciprocal_millisecond(Integer num) {
            this.video_reciprocal_millisecond = num;
            return this;
        }

        public Builder video_size(Size size) {
            this.video_size = size;
            return this;
        }

        public Builder video_url(String str) {
            this.video_url = str;
            return this;
        }

        public Builder web_event_handle(WebEventHandle webEventHandle) {
            this.web_event_handle = webEventHandle;
            return this;
        }
    }

    private static final class ProtoAdapter_MaterialMeta extends ProtoAdapter<MaterialMeta> {
        public ProtoAdapter_MaterialMeta() {
            super(FieldEncoding.LENGTH_DELIMITED, MaterialMeta.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public MaterialMeta decode(ProtoReader protoReader) {
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
                        builder.creative_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.interaction_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.landing_page(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.video_url(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.video_duration(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 6:
                        builder.video_size(Size.ADAPTER.decode(protoReader));
                        break;
                    case 7:
                        builder.html_snippet(ProtoAdapter.BYTES.decode(protoReader));
                        break;
                    case 8:
                        builder.endcard_url(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 9:
                        builder.video_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 10:
                        builder.endcard_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 11:
                        builder.deeplink_url(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 12:
                        builder.image_src(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 13:
                        builder.image_size(Size.ADAPTER.decode(protoReader));
                        break;
                    case 14:
                        builder.image_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 15:
                        builder.image_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 16:
                        builder.click_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 17:
                        builder.has_companion_endcard(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 18:
                        builder.companion(CompanionEndcard.ADAPTER.decode(protoReader));
                        break;
                    case 19:
                        builder.web_event_handle(WebEventHandle.ADAPTER.decode(protoReader));
                        break;
                    case 20:
                        builder.template_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 21:
                        builder.video_reciprocal_millisecond(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 22:
                        builder.html_url(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 23:
                        builder.enable_collapse_tool_bar(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 24:
                        builder.open_market_mode(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 25:
                        builder.play_mode(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 26:
                        builder.sub_interaction_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 27:
                        builder.title(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 28:
                        builder.desc(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 29:
                        builder.icon_url(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 30:
                        builder.score(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 31:
                        builder.template_id(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 32:
                        builder.disable_auto_deeplink(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 33:
                        builder.closecard_html_snippet(ProtoAdapter.BYTES.decode(protoReader));
                        break;
                    case 34:
                        builder.app_name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 35:
                        builder.button_text(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 36:
                        builder.ad_privacy(AdPrivacy.ADAPTER.decode(protoReader));
                        break;
                    case 37:
                        builder.video_byte_size(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 38:
                        builder.html_src(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 39:
                        builder.native_ad(ResponseNativeAd.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, MaterialMeta materialMeta) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, materialMeta.creative_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, materialMeta.interaction_type);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, materialMeta.landing_page);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, materialMeta.video_url);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 5, materialMeta.video_duration);
            Size.ADAPTER.encodeWithTag(protoWriter, 6, materialMeta.video_size);
            ProtoAdapter.BYTES.encodeWithTag(protoWriter, 7, materialMeta.html_snippet);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, materialMeta.endcard_url);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, materialMeta.video_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, materialMeta.endcard_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, materialMeta.deeplink_url);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, materialMeta.image_src);
            Size.ADAPTER.encodeWithTag(protoWriter, 13, materialMeta.image_size);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, materialMeta.image_md5);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 15, materialMeta.image_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 16, materialMeta.click_type);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 17, materialMeta.has_companion_endcard);
            CompanionEndcard.ADAPTER.encodeWithTag(protoWriter, 18, materialMeta.companion);
            WebEventHandle.ADAPTER.encodeWithTag(protoWriter, 19, materialMeta.web_event_handle);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 20, materialMeta.template_type);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 21, materialMeta.video_reciprocal_millisecond);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 22, materialMeta.html_url);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 23, materialMeta.enable_collapse_tool_bar);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 24, materialMeta.open_market_mode);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 25, materialMeta.play_mode);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 26, materialMeta.sub_interaction_type);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 27, materialMeta.title);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 28, materialMeta.desc);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 29, materialMeta.icon_url);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 30, materialMeta.score);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 31, materialMeta.template_id);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 32, materialMeta.disable_auto_deeplink);
            ProtoAdapter.BYTES.encodeWithTag(protoWriter, 33, materialMeta.closecard_html_snippet);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 34, materialMeta.app_name);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 35, materialMeta.button_text);
            AdPrivacy.ADAPTER.encodeWithTag(protoWriter, 36, materialMeta.ad_privacy);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 37, materialMeta.video_byte_size);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 38, materialMeta.html_src);
            ResponseNativeAd.ADAPTER.encodeWithTag(protoWriter, 39, materialMeta.native_ad);
            protoWriter.writeBytes(materialMeta.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(MaterialMeta materialMeta) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, materialMeta.creative_type) + ProtoAdapter.UINT32.encodedSizeWithTag(2, materialMeta.interaction_type) + ProtoAdapter.STRING.encodedSizeWithTag(3, materialMeta.landing_page) + ProtoAdapter.STRING.encodedSizeWithTag(4, materialMeta.video_url) + ProtoAdapter.UINT32.encodedSizeWithTag(5, materialMeta.video_duration) + Size.ADAPTER.encodedSizeWithTag(6, materialMeta.video_size) + ProtoAdapter.BYTES.encodedSizeWithTag(7, materialMeta.html_snippet) + ProtoAdapter.STRING.encodedSizeWithTag(8, materialMeta.endcard_url) + ProtoAdapter.STRING.encodedSizeWithTag(9, materialMeta.video_md5) + ProtoAdapter.STRING.encodedSizeWithTag(10, materialMeta.endcard_md5) + ProtoAdapter.STRING.encodedSizeWithTag(11, materialMeta.deeplink_url) + ProtoAdapter.STRING.encodedSizeWithTag(12, materialMeta.image_src) + Size.ADAPTER.encodedSizeWithTag(13, materialMeta.image_size) + ProtoAdapter.STRING.encodedSizeWithTag(14, materialMeta.image_md5) + ProtoAdapter.UINT32.encodedSizeWithTag(15, materialMeta.image_type) + ProtoAdapter.UINT32.encodedSizeWithTag(16, materialMeta.click_type) + ProtoAdapter.BOOL.encodedSizeWithTag(17, materialMeta.has_companion_endcard) + CompanionEndcard.ADAPTER.encodedSizeWithTag(18, materialMeta.companion) + WebEventHandle.ADAPTER.encodedSizeWithTag(19, materialMeta.web_event_handle) + ProtoAdapter.UINT32.encodedSizeWithTag(20, materialMeta.template_type) + ProtoAdapter.INT32.encodedSizeWithTag(21, materialMeta.video_reciprocal_millisecond) + ProtoAdapter.STRING.encodedSizeWithTag(22, materialMeta.html_url) + ProtoAdapter.BOOL.encodedSizeWithTag(23, materialMeta.enable_collapse_tool_bar) + ProtoAdapter.UINT32.encodedSizeWithTag(24, materialMeta.open_market_mode) + ProtoAdapter.UINT32.encodedSizeWithTag(25, materialMeta.play_mode) + ProtoAdapter.UINT32.encodedSizeWithTag(26, materialMeta.sub_interaction_type) + ProtoAdapter.STRING.encodedSizeWithTag(27, materialMeta.title) + ProtoAdapter.STRING.encodedSizeWithTag(28, materialMeta.desc) + ProtoAdapter.STRING.encodedSizeWithTag(29, materialMeta.icon_url) + ProtoAdapter.FLOAT.encodedSizeWithTag(30, materialMeta.score) + ProtoAdapter.UINT32.encodedSizeWithTag(31, materialMeta.template_id) + ProtoAdapter.BOOL.encodedSizeWithTag(32, materialMeta.disable_auto_deeplink) + ProtoAdapter.BYTES.encodedSizeWithTag(33, materialMeta.closecard_html_snippet) + ProtoAdapter.STRING.encodedSizeWithTag(34, materialMeta.app_name) + ProtoAdapter.STRING.encodedSizeWithTag(35, materialMeta.button_text) + AdPrivacy.ADAPTER.encodedSizeWithTag(36, materialMeta.ad_privacy) + ProtoAdapter.UINT32.encodedSizeWithTag(37, materialMeta.video_byte_size) + ProtoAdapter.STRING.encodedSizeWithTag(38, materialMeta.html_src) + ResponseNativeAd.ADAPTER.encodedSizeWithTag(39, materialMeta.native_ad) + materialMeta.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public MaterialMeta redact(MaterialMeta materialMeta) {
            Builder builderNewBuilder = materialMeta.newBuilder();
            if (builderNewBuilder.video_size != null) {
                builderNewBuilder.video_size = Size.ADAPTER.redact(builderNewBuilder.video_size);
            }
            if (builderNewBuilder.image_size != null) {
                builderNewBuilder.image_size = Size.ADAPTER.redact(builderNewBuilder.image_size);
            }
            if (builderNewBuilder.companion != null) {
                builderNewBuilder.companion = CompanionEndcard.ADAPTER.redact(builderNewBuilder.companion);
            }
            if (builderNewBuilder.web_event_handle != null) {
                builderNewBuilder.web_event_handle = WebEventHandle.ADAPTER.redact(builderNewBuilder.web_event_handle);
            }
            if (builderNewBuilder.ad_privacy != null) {
                builderNewBuilder.ad_privacy = AdPrivacy.ADAPTER.redact(builderNewBuilder.ad_privacy);
            }
            if (builderNewBuilder.native_ad != null) {
                builderNewBuilder.native_ad = ResponseNativeAd.ADAPTER.redact(builderNewBuilder.native_ad);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public MaterialMeta(Integer num, Integer num2, String str, String str2, Integer num3, Size size, ByteString byteString, String str3, String str4, String str5, String str6, String str7, Size size2, String str8, Integer num4, Integer num5, Boolean bool, CompanionEndcard companionEndcard, WebEventHandle webEventHandle, Integer num6, Integer num7, String str9, Boolean bool2, Integer num8, Integer num9, Integer num10, String str10, String str11, String str12, Float f, Integer num11, Boolean bool3, ByteString byteString2, String str13, String str14, AdPrivacy adPrivacy, Integer num12, String str15, ResponseNativeAd responseNativeAd) {
        this(num, num2, str, str2, num3, size, byteString, str3, str4, str5, str6, str7, size2, str8, num4, num5, bool, companionEndcard, webEventHandle, num6, num7, str9, bool2, num8, num9, num10, str10, str11, str12, f, num11, bool3, byteString2, str13, str14, adPrivacy, num12, str15, responseNativeAd, ByteString.EMPTY);
    }

    public MaterialMeta(Integer num, Integer num2, String str, String str2, Integer num3, Size size, ByteString byteString, String str3, String str4, String str5, String str6, String str7, Size size2, String str8, Integer num4, Integer num5, Boolean bool, CompanionEndcard companionEndcard, WebEventHandle webEventHandle, Integer num6, Integer num7, String str9, Boolean bool2, Integer num8, Integer num9, Integer num10, String str10, String str11, String str12, Float f, Integer num11, Boolean bool3, ByteString byteString2, String str13, String str14, AdPrivacy adPrivacy, Integer num12, String str15, ResponseNativeAd responseNativeAd, ByteString byteString3) {
        super(ADAPTER, byteString3);
        this.creative_type = num;
        this.interaction_type = num2;
        this.landing_page = str;
        this.video_url = str2;
        this.video_duration = num3;
        this.video_size = size;
        this.html_snippet = byteString;
        this.endcard_url = str3;
        this.video_md5 = str4;
        this.endcard_md5 = str5;
        this.deeplink_url = str6;
        this.image_src = str7;
        this.image_size = size2;
        this.image_md5 = str8;
        this.image_type = num4;
        this.click_type = num5;
        this.has_companion_endcard = bool;
        this.companion = companionEndcard;
        this.web_event_handle = webEventHandle;
        this.template_type = num6;
        this.video_reciprocal_millisecond = num7;
        this.html_url = str9;
        this.enable_collapse_tool_bar = bool2;
        this.open_market_mode = num8;
        this.play_mode = num9;
        this.sub_interaction_type = num10;
        this.title = str10;
        this.desc = str11;
        this.icon_url = str12;
        this.score = f;
        this.template_id = num11;
        this.disable_auto_deeplink = bool3;
        this.closecard_html_snippet = byteString2;
        this.app_name = str13;
        this.button_text = str14;
        this.ad_privacy = adPrivacy;
        this.video_byte_size = num12;
        this.html_src = str15;
        this.native_ad = responseNativeAd;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MaterialMeta)) {
            return false;
        }
        MaterialMeta materialMeta = (MaterialMeta) obj;
        return unknownFields().equals(materialMeta.unknownFields()) && Internal.equals(this.creative_type, materialMeta.creative_type) && Internal.equals(this.interaction_type, materialMeta.interaction_type) && Internal.equals(this.landing_page, materialMeta.landing_page) && Internal.equals(this.video_url, materialMeta.video_url) && Internal.equals(this.video_duration, materialMeta.video_duration) && Internal.equals(this.video_size, materialMeta.video_size) && Internal.equals(this.html_snippet, materialMeta.html_snippet) && Internal.equals(this.endcard_url, materialMeta.endcard_url) && Internal.equals(this.video_md5, materialMeta.video_md5) && Internal.equals(this.endcard_md5, materialMeta.endcard_md5) && Internal.equals(this.deeplink_url, materialMeta.deeplink_url) && Internal.equals(this.image_src, materialMeta.image_src) && Internal.equals(this.image_size, materialMeta.image_size) && Internal.equals(this.image_md5, materialMeta.image_md5) && Internal.equals(this.image_type, materialMeta.image_type) && Internal.equals(this.click_type, materialMeta.click_type) && Internal.equals(this.has_companion_endcard, materialMeta.has_companion_endcard) && Internal.equals(this.companion, materialMeta.companion) && Internal.equals(this.web_event_handle, materialMeta.web_event_handle) && Internal.equals(this.template_type, materialMeta.template_type) && Internal.equals(this.video_reciprocal_millisecond, materialMeta.video_reciprocal_millisecond) && Internal.equals(this.html_url, materialMeta.html_url) && Internal.equals(this.enable_collapse_tool_bar, materialMeta.enable_collapse_tool_bar) && Internal.equals(this.open_market_mode, materialMeta.open_market_mode) && Internal.equals(this.play_mode, materialMeta.play_mode) && Internal.equals(this.sub_interaction_type, materialMeta.sub_interaction_type) && Internal.equals(this.title, materialMeta.title) && Internal.equals(this.desc, materialMeta.desc) && Internal.equals(this.icon_url, materialMeta.icon_url) && Internal.equals(this.score, materialMeta.score) && Internal.equals(this.template_id, materialMeta.template_id) && Internal.equals(this.disable_auto_deeplink, materialMeta.disable_auto_deeplink) && Internal.equals(this.closecard_html_snippet, materialMeta.closecard_html_snippet) && Internal.equals(this.app_name, materialMeta.app_name) && Internal.equals(this.button_text, materialMeta.button_text) && Internal.equals(this.ad_privacy, materialMeta.ad_privacy) && Internal.equals(this.video_byte_size, materialMeta.video_byte_size) && Internal.equals(this.html_src, materialMeta.html_src) && Internal.equals(this.native_ad, materialMeta.native_ad);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.creative_type != null ? this.creative_type.hashCode() : 0)) * 37) + (this.interaction_type != null ? this.interaction_type.hashCode() : 0)) * 37) + (this.landing_page != null ? this.landing_page.hashCode() : 0)) * 37) + (this.video_url != null ? this.video_url.hashCode() : 0)) * 37) + (this.video_duration != null ? this.video_duration.hashCode() : 0)) * 37) + (this.video_size != null ? this.video_size.hashCode() : 0)) * 37) + (this.html_snippet != null ? this.html_snippet.hashCode() : 0)) * 37) + (this.endcard_url != null ? this.endcard_url.hashCode() : 0)) * 37) + (this.video_md5 != null ? this.video_md5.hashCode() : 0)) * 37) + (this.endcard_md5 != null ? this.endcard_md5.hashCode() : 0)) * 37) + (this.deeplink_url != null ? this.deeplink_url.hashCode() : 0)) * 37) + (this.image_src != null ? this.image_src.hashCode() : 0)) * 37) + (this.image_size != null ? this.image_size.hashCode() : 0)) * 37) + (this.image_md5 != null ? this.image_md5.hashCode() : 0)) * 37) + (this.image_type != null ? this.image_type.hashCode() : 0)) * 37) + (this.click_type != null ? this.click_type.hashCode() : 0)) * 37) + (this.has_companion_endcard != null ? this.has_companion_endcard.hashCode() : 0)) * 37) + (this.companion != null ? this.companion.hashCode() : 0)) * 37) + (this.web_event_handle != null ? this.web_event_handle.hashCode() : 0)) * 37) + (this.template_type != null ? this.template_type.hashCode() : 0)) * 37) + (this.video_reciprocal_millisecond != null ? this.video_reciprocal_millisecond.hashCode() : 0)) * 37) + (this.html_url != null ? this.html_url.hashCode() : 0)) * 37) + (this.enable_collapse_tool_bar != null ? this.enable_collapse_tool_bar.hashCode() : 0)) * 37) + (this.open_market_mode != null ? this.open_market_mode.hashCode() : 0)) * 37) + (this.play_mode != null ? this.play_mode.hashCode() : 0)) * 37) + (this.sub_interaction_type != null ? this.sub_interaction_type.hashCode() : 0)) * 37) + (this.title != null ? this.title.hashCode() : 0)) * 37) + (this.desc != null ? this.desc.hashCode() : 0)) * 37) + (this.icon_url != null ? this.icon_url.hashCode() : 0)) * 37) + (this.score != null ? this.score.hashCode() : 0)) * 37) + (this.template_id != null ? this.template_id.hashCode() : 0)) * 37) + (this.disable_auto_deeplink != null ? this.disable_auto_deeplink.hashCode() : 0)) * 37) + (this.closecard_html_snippet != null ? this.closecard_html_snippet.hashCode() : 0)) * 37) + (this.app_name != null ? this.app_name.hashCode() : 0)) * 37) + (this.button_text != null ? this.button_text.hashCode() : 0)) * 37) + (this.ad_privacy != null ? this.ad_privacy.hashCode() : 0)) * 37) + (this.video_byte_size != null ? this.video_byte_size.hashCode() : 0)) * 37) + (this.html_src != null ? this.html_src.hashCode() : 0)) * 37) + (this.native_ad != null ? this.native_ad.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.creative_type = this.creative_type;
        builder.interaction_type = this.interaction_type;
        builder.landing_page = this.landing_page;
        builder.video_url = this.video_url;
        builder.video_duration = this.video_duration;
        builder.video_size = this.video_size;
        builder.html_snippet = this.html_snippet;
        builder.endcard_url = this.endcard_url;
        builder.video_md5 = this.video_md5;
        builder.endcard_md5 = this.endcard_md5;
        builder.deeplink_url = this.deeplink_url;
        builder.image_src = this.image_src;
        builder.image_size = this.image_size;
        builder.image_md5 = this.image_md5;
        builder.image_type = this.image_type;
        builder.click_type = this.click_type;
        builder.has_companion_endcard = this.has_companion_endcard;
        builder.companion = this.companion;
        builder.web_event_handle = this.web_event_handle;
        builder.template_type = this.template_type;
        builder.video_reciprocal_millisecond = this.video_reciprocal_millisecond;
        builder.html_url = this.html_url;
        builder.enable_collapse_tool_bar = this.enable_collapse_tool_bar;
        builder.open_market_mode = this.open_market_mode;
        builder.play_mode = this.play_mode;
        builder.sub_interaction_type = this.sub_interaction_type;
        builder.title = this.title;
        builder.desc = this.desc;
        builder.icon_url = this.icon_url;
        builder.score = this.score;
        builder.template_id = this.template_id;
        builder.disable_auto_deeplink = this.disable_auto_deeplink;
        builder.closecard_html_snippet = this.closecard_html_snippet;
        builder.app_name = this.app_name;
        builder.button_text = this.button_text;
        builder.ad_privacy = this.ad_privacy;
        builder.video_byte_size = this.video_byte_size;
        builder.html_src = this.html_src;
        builder.native_ad = this.native_ad;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.creative_type != null) {
            sb.append(", creative_type=");
            sb.append(this.creative_type);
        }
        if (this.interaction_type != null) {
            sb.append(", interaction_type=");
            sb.append(this.interaction_type);
        }
        if (this.landing_page != null) {
            sb.append(", landing_page=");
            sb.append(this.landing_page);
        }
        if (this.video_url != null) {
            sb.append(", video_url=");
            sb.append(this.video_url);
        }
        if (this.video_duration != null) {
            sb.append(", video_duration=");
            sb.append(this.video_duration);
        }
        if (this.video_size != null) {
            sb.append(", video_size=");
            sb.append(this.video_size);
        }
        if (this.html_snippet != null) {
            sb.append(", html_snippet=");
            sb.append(this.html_snippet);
        }
        if (this.endcard_url != null) {
            sb.append(", endcard_url=");
            sb.append(this.endcard_url);
        }
        if (this.video_md5 != null) {
            sb.append(", video_md5=");
            sb.append(this.video_md5);
        }
        if (this.endcard_md5 != null) {
            sb.append(", endcard_md5=");
            sb.append(this.endcard_md5);
        }
        if (this.deeplink_url != null) {
            sb.append(", deeplink_url=");
            sb.append(this.deeplink_url);
        }
        if (this.image_src != null) {
            sb.append(", image_src=");
            sb.append(this.image_src);
        }
        if (this.image_size != null) {
            sb.append(", image_size=");
            sb.append(this.image_size);
        }
        if (this.image_md5 != null) {
            sb.append(", image_md5=");
            sb.append(this.image_md5);
        }
        if (this.image_type != null) {
            sb.append(", image_type=");
            sb.append(this.image_type);
        }
        if (this.click_type != null) {
            sb.append(", click_type=");
            sb.append(this.click_type);
        }
        if (this.has_companion_endcard != null) {
            sb.append(", has_companion_endcard=");
            sb.append(this.has_companion_endcard);
        }
        if (this.companion != null) {
            sb.append(", companion=");
            sb.append(this.companion);
        }
        if (this.web_event_handle != null) {
            sb.append(", web_event_handle=");
            sb.append(this.web_event_handle);
        }
        if (this.template_type != null) {
            sb.append(", template_type=");
            sb.append(this.template_type);
        }
        if (this.video_reciprocal_millisecond != null) {
            sb.append(", video_reciprocal_millisecond=");
            sb.append(this.video_reciprocal_millisecond);
        }
        if (this.html_url != null) {
            sb.append(", html_url=");
            sb.append(this.html_url);
        }
        if (this.enable_collapse_tool_bar != null) {
            sb.append(", enable_collapse_tool_bar=");
            sb.append(this.enable_collapse_tool_bar);
        }
        if (this.open_market_mode != null) {
            sb.append(", open_market_mode=");
            sb.append(this.open_market_mode);
        }
        if (this.play_mode != null) {
            sb.append(", play_mode=");
            sb.append(this.play_mode);
        }
        if (this.sub_interaction_type != null) {
            sb.append(", sub_interaction_type=");
            sb.append(this.sub_interaction_type);
        }
        if (this.title != null) {
            sb.append(", title=");
            sb.append(this.title);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        if (this.icon_url != null) {
            sb.append(", icon_url=");
            sb.append(this.icon_url);
        }
        if (this.score != null) {
            sb.append(", score=");
            sb.append(this.score);
        }
        if (this.template_id != null) {
            sb.append(", template_id=");
            sb.append(this.template_id);
        }
        if (this.disable_auto_deeplink != null) {
            sb.append(", disable_auto_deeplink=");
            sb.append(this.disable_auto_deeplink);
        }
        if (this.closecard_html_snippet != null) {
            sb.append(", closecard_html_snippet=");
            sb.append(this.closecard_html_snippet);
        }
        if (this.app_name != null) {
            sb.append(", app_name=");
            sb.append(this.app_name);
        }
        if (this.button_text != null) {
            sb.append(", button_text=");
            sb.append(this.button_text);
        }
        if (this.ad_privacy != null) {
            sb.append(", ad_privacy=");
            sb.append(this.ad_privacy);
        }
        if (this.video_byte_size != null) {
            sb.append(", video_byte_size=");
            sb.append(this.video_byte_size);
        }
        if (this.html_src != null) {
            sb.append(", html_src=");
            sb.append(this.html_src);
        }
        if (this.native_ad != null) {
            sb.append(", native_ad=");
            sb.append(this.native_ad);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "MaterialMeta{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
