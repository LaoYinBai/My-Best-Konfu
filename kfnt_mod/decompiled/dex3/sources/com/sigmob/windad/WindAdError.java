package com.sigmob.windad;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum WindAdError {
    ERROR_AD_LOAD_FAIL_PLAYING(610011, "广告正在播放中，请在广告关闭后再播放"),
    ERROR_AD_LOAD_FAIL_LOADING(610012, "广告正在加载中，请稍后再加载"),
    ERROR_AD_LOAD_FAIL_INTERVAL(610013, "广告加载频繁，请稍后在试"),
    ERROR_REQUEST_SIGMOB_NOT_SUPPORT_REGION(500402, "该地区无sigmob广告渠道服务"),
    ERROR_REQUEST_APP_IS_CLOSED(500420, "请求的app已经关闭广告服务"),
    ERROR_REQUEST_NEED_DEVICE_INFO(500422, "请求参数缺少设备信息"),
    ERROR_REQUEST_NEED_DEVICE_ID_INFO(500424, "缺少设备id相关信息"),
    ERROR_REQUEST_NEED_AD_SLOTS_INFO(500428, " 缺少广告位信息"),
    ERROR_INVALID_ADSLOT_ID(500430, " 错误的广告位信息"),
    REQUEST_AD_SLOT_NOT_EXISTS(500432, "广告位不存在，或者appid与广告位不匹配"),
    REQUEST_AD_SLOT_IS_CLOSED(500433, "广告位不存在或是已关闭"),
    REQUEST_OS_TYPE_NOT_MATCH_APP_TYPE(500435, "设备的操作系统类型，与请求的app的系统类型不匹配"),
    REQUEST_AD_SLOT_NOT_MATCH__AD_TYPE(500436, "广告单元id与请求的广告类型不匹配"),
    ERROR_REQUEST_NO_SUCH_APP(500473, "请求的app不存在"),
    ERROR_SIGMOB_PLACEMNT_ID_IS_EMPTY(500479, "广告位id为空"),
    ERROR_SIGMOB_AD_COUNT_IS_EMPTY(630000, "请求的广告数量为空"),
    ERROR_SIGMOB_BID_TOKEN_IS_EMPTY(600300, "BidToken is empty"),
    ERROR_REQUEST_NO_DEVICE_ID(500482, "无法获取设备ID"),
    ERROR_REQUEST_APP_NOT_SET_STRATEGY(500700, "app未设置聚合策略"),
    ERROR_REQUEST_APP_NOT_SET_CHANNEL(500701, "app未开通任何广告渠道"),
    RTB_SIG_DSP_NO_ADS_ERROR(200000, "广告无填充"),
    ERROR_SIGMOB_NETWORK(600100, "网络错误"),
    ERROR_SIGMOB_REQUEST(600101, "广告请求出错"),
    ERROR_SIGMOB_NOT_FOUD_ADAPTER(600102, "未找到该渠道的适配器"),
    ERROR_SIGMOB_STRATEGY_EMPTY(600103, "配置的策略为空"),
    ERROR_SIGMOB_FILE_DOWNLOAD(600104, "文件下载错误"),
    ERROR_SIGMOB_AD_TIME_OUT(600105, "下载广告超时"),
    ERROR_SIGMOB_AD_ADAPTER_ERROR(600112, "渠道适配器版本不兼容"),
    ERROR_SIGMOB_NOT_INIT(600900, "SDK未初始化"),
    ERROR_SIGMOB_PLACEMENTID_EMPTY(600901, "广告位为空"),
    ERROR_SIGMOB_STRATEGY(600902, "策略请求失败"),
    ERROR_SIGMOB_INSTALL_FAIL(600903, "安装失败"),
    ERROR_SIGMOB_AD_DB_INSERT(600904, "插入数据库失败"),
    ERROR_SIGMOB_GDPR_DENIED(600905, "User GDPR Consent Status is denied"),
    ERROR_SIGMOB_INIT_FAIL(600906, "SDK 初始化失败"),
    ERROR_OTHER(600999, ""),
    ERROR_SIGMOB_AD_PLAY(610002, "激励视频播放出错"),
    ERROR_SIGMOB_NOT_READY(610003, "广告未准备好"),
    ERROR_SIGMOB_INFORMATION_LOSE(610004, "server下发的广告信息缺失关键信息"),
    ERROR_SIGMOB_FILE_MD5(610005, "下载的文件校验md5出错"),
    ERROR_SIGMOB_AD_PLAY_CHECK_FAIL(610006, "激励视频播接口检查出错（广告过期或者未ready)"),
    ERROR_SIGMOB_AD_PLAY_HAS_PLAYING(610007, "激励视频播接口检查出错（激励视频当前有广告正在播放中)"),
    ERROR_SIGMOB_SPLASH_TIMEOUT(620001, "开屏广告加载超时"),
    ERROR_SIGMOB_SPLASH_UNSUPPORT_ORIENTATION(620002, "开屏广告不支持当前方向"),
    ERROR_SIGMOB_SPLASH_UNSUPPORT_RESOURCE(620003, "开屏广告不支持的资源类型"),
    ERROR_SPLASH_ADBLOCK(620900, "AD BLOCK"),
    ERROR_AD_ERROR(0, "AD ERROR");

    private final int a;
    private String message;

    WindAdError(int i, String str) {
        this.a = i;
        this.message = str;
    }

    public static WindAdError getWindAdError(int i) {
        for (WindAdError windAdError : values()) {
            if (windAdError.getErrorCode() == i) {
                return windAdError;
            }
        }
        return null;
    }

    public int getErrorCode() {
        return this.a;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.format("{ \"error_code\":%d, \"message\": %s }", Integer.valueOf(this.a), this.message);
    }
}
