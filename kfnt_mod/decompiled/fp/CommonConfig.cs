using UnityEngine;

public class CommonConfig
{
	public const string GooglePlayPackageName = "com.peakx.stickfight";

	public const string IOSBundleId = "com.peakx.stickfight";

	public const string AppleId = "1477022124";

	public const string AppName = "Œ“π¶∑ÚÃÿ≈£";

	public const string AppCompanyName = "PeakX Games";

	public const string BuglyAppIDForiOS = "11607dc32e";

	public const string BuglyAppIDForAndroid = "d494abc238";

	public const string BuglyAppKeyForiOS = "3ab073b9-2563-4c24-a8b6-51e17dd37bd7";

	public const string BuglyAppKeyForAndroid = "43612622-9715-4b2e-9cfe-2cdc9ef7bcc3";

	public static bool DataHandlerCanUseTimer = true;

	public static string StatTestUsedTimeKey = "testUsedTime001";

	public static bool ForceCloseTestTool = false;

	public static ClientEnv env = ClientEnv.Production;

	public static string InterUrl = "https://u.ohayoo.io/uyun/api/";

	public const PartnerType partnerType = PartnerType.DouYin;

	public const StatisticalPlatformType statType = StatisticalPlatformType.Adjust;

	public static bool canSkipGuide = false;

	public static bool canShowFPS = false;

	public static bool supportAutoLine = true;

	public static bool TestShowUI = true;

	public static bool TapticEngine = true;

	public static bool supportHotUpdater = false;

	public static string ServerUrl = string.Empty;

	public static string CdnUrl = string.Empty;

	public static string AvatarUrl = string.Empty;

	public static string ShareDownloadUrl = string.Empty;

	public static string WebSocketUrl = string.Empty;

	public static string Channel = "googleplay";

	public static string GameFacebookWebUrl = "https://www.facebook.com/MergeFoood/";

	public static string GameInstagramWebUrl = "https://www.instagram.com/westthegame/";

	public static string GameAutoSubUrl = "http://www.peakxgames.com/autosub.html";

	public static string GameTermsOfUseUrl = "http://www.peakxgames.com/termsofuse.html";

	public static string GamePrivacyPolicyUrl = "http://www.peakxgames.com/privacy.html";

	public static string BugReportUrl = "https://docs.qq.com/form/fill/DS0pWcE91QXBBemxU?_w_tencentdocx_form=1";

	public static string TestBugReportUrl = "https://docs.qq.com/form/fill/DS3ptaVNMU0x3V3B0";

	public static string AdviseReportUrl = "https://docs.qq.com/form/fill/DS3hLVWZZRlhIVEJD?_w_tencentdocx_form=1";

	public static string SupportEmail = "support@peakxgames.com";

	public const FilePathType pathType = FilePathType.StreamingAssets;

	public static string app_version = "0.2.0";

	public static string res_version = "0.1.0";

	public const string VersionFileName = "VersionFile.txt";

	public const string AppVersionKey = "AppVersion";

	public const string XXTEAWebKey = "Gameforyou";

	public const string XXTEAGameKey = "GfZ1WcEHZLjRxDt3";

	public const int TargetFrameRate = 60;

	public static int TimeZone = 0;

	public const string DeviceANDROID = "ANDROID";

	public const string DeviceIOS = "IOS";

	public static string Device = "ANDROID";

	public static string MobileRegionJson = "[\"Chinese\",\"China\"]";

	public const string AppUrl = "https://play.google.com/store/apps/details?id={0}";

	public const string ReviewUrl = "https://play.google.com/store/apps/details?id={0}";

	public static string GetIPInfoURL = "http://freegeoip.net/json/";

	public static string JsonConfigPath = "Assets/CloudBundle/JsonConfig/";

	public static string JsonConfigPathEditor = "Assets/Editor/ConvertTools/JSON/";

	public static string BytesConfigPath = "Assets/CloudBundle/BytesConfig/";

	public static string LuaConfigPath = "Assets/Editor/LuaSource/";

	public static void InitIOSServerUrl()
	{
		if (env == ClientEnv.Production)
		{
			ServerUrl = "https://iapmaster.peakxgames.com/json";
		}
		else if (env == ClientEnv.Testing)
		{
			ServerUrl = "https://iaptesting.peakxgames.com/json";
		}
		else if (env == ClientEnv.Preview)
		{
			ServerUrl = "https://iaptesting.peakxgames.com/json";
		}
		else
		{
			ServerUrl = "https://iaptesting.peakxgames.com/json";
		}
	}

	public static void InitAndroidServerUrl()
	{
		if (env == ClientEnv.Production)
		{
			ServerUrl = "https://iapmaster.peakxgames.com/json";
		}
		else if (env == ClientEnv.Testing)
		{
			ServerUrl = "https://iaptesting.peakxgames.com/json";
		}
		else if (env == ClientEnv.Preview)
		{
			ServerUrl = "https://iaptesting.peakxgames.com/json";
		}
		else
		{
			ServerUrl = "https://iaptesting.peakxgames.com/json";
		}
	}

	public static void InitServerUrl()
	{
		InitAndroidServerUrl();
	}

	public static bool IsProduction()
	{
		return env == ClientEnv.Production;
	}

	public static bool IsDev()
	{
		return env == ClientEnv.Dev;
	}

	public static bool IsTesting()
	{
		return env == ClientEnv.Testing;
	}

	public static bool IsPreview()
	{
		return env == ClientEnv.Preview;
	}

	public static bool IsCheckTestTool()
	{
		if (ForceCloseTestTool)
		{
			return false;
		}
		if (!IsProduction())
		{
			return !IsPreview();
		}
		return false;
	}

	public static string GetChannel()
	{
		_ = string.Empty;
		return $"GooglePlay-{env}";
	}

	public static string GetAppUrl()
	{
		return string.Format("https://play.google.com/store/apps/details?id={0}", "com.peakx.stickfight");
	}

	public static string GetReviewUrl()
	{
		return string.Format("https://play.google.com/store/apps/details?id={0}", "com.peakx.stickfight");
	}

	public static void OpenAppURL()
	{
		Application.OpenURL("https://ic.snssdk.com/game_channel/detail/94345951502");
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
