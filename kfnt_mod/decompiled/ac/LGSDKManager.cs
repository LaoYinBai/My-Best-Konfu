using System;
using System.Collections.Generic;
using ByteDance.Union;
using UnityEngine;

public class LGSDKManager : MonoBehaviour
{
	[Serializable]
	public class AntiaddictionInfo
	{
		public LGAntiAddictionLimitType limitType;

		public int canPlayTime;
	}

	public static string UserId = "stickfight";

	public LGAbsRewardVideoAd rewardAd;

	public CertWindowType popupCertWindowType;

	public int popupRemainTime;

	public CertWindowType handlerCertWindowType;

	public bool verifyPaused;

	private List<RealNameVerifiedActionObject> _realNameVerifiedActionObjects = new List<RealNameVerifiedActionObject>();

	public static LGSDKManager Instance { get; private set; }

	public bool initialized { get; private set; }

	public string androidChannel { get; private set; }

	public static LGMediationAdService mediationAdService => Singeton<LGMediationAdService>.Instance;

	public AdsStatus status { get; private set; }

	public string adSlot { get; private set; }

	public bool playSuccess { get; private set; }

	public IAPData iapData { get; private set; }

	public static event Action<string, string> OnRewardedVideoCachedEvent;

	public static event Action<string, string> OnRewardedVideoLoadedEvent;

	public static event Action<string, string> OnRewardedVideoFailedEvent;

	public static event Action<string, string> OnRewardedVideoShownEvent;

	public static event Action<string, string> OnRewardedVideoClickedEvent;

	public static event Action<string, string> OnRewardedVideoFailedToPlayEvent;

	public static event Action<string, string> OnRewardedVideoReceivedRewardEvent;

	public static event Action<string, string> OnRewardedVideoClosedEvent;

	public static event Action<string, string> OnViewRenderSuccEvent;

	public static event Action<string, int, string> OnViewRenderFailEvent;

	public static event Action<string, string> OnRewardVerifyFail;

	public static event Action<LGUser> OnAccountLoginSuccess;

	public static event Action<int, string> OnAccountLoginFailed;

	public static event Action<IAPData, int, string> OnPayResult;

	public static event Action OnInitSuccess;

	public static event RealNameVerifiedSuccAction realNameVerifiedSuccAction;

	public static event RealNameVerifiedFailAction realNameVerifiedFailAction;

	public static event Action getDeviceRealNameVerifiedSuccAction;

	public static event Action getDeviceRealNameVerifiedFailAction;

	public static event Action<string> OnDidFinishStartEvent;

	public static void InitManager()
	{
		//IL_001a: Unknown result type (might be due to invalid IL or missing references)
		Type typeFromHandle = typeof(LGSDKManager);
		LGSDKManager component = new GameObject("UnityLGSDKPlugin", new Type[1] { typeFromHandle }).GetComponent<LGSDKManager>();
		if ((Object)(object)Instance != (Object)(object)component)
		{
			Debug.LogWarning((object)("It looks like you have the " + typeFromHandle.Name + " on a GameObject in your scene. Please remove the script from your scene."));
		}
	}

	private void Awake()
	{
		if ((Object)(object)Instance == (Object)null)
		{
			Instance = this;
			Object.DontDestroyOnLoad((Object)(object)((Component)this).gameObject);
			((Object)((Component)this).gameObject).hideFlags = (HideFlags)1;
			startSdk();
			startAd();
			startAccount();
			startAnti();
			startRealName();
			startPush();
		}
		else
		{
			Object.Destroy((Object)(object)this);
		}
	}

	private void OnDestroy()
	{
		if ((Object)(object)Instance == (Object)(object)this)
		{
			stopPush();
			stopRealName();
			stopAnti();
			stopAccount();
			stopAd();
			stopSdk();
			Instance = null;
		}
	}

	private void startSdk()
	{
		string text = "cczs";
		androidChannel = text;
		initialized = false;
		LGSDKCore.Init((Action<LGInitSuccessInfo>)InitSuccess, (Action<int, string>)InitFail);
	}

	private static void InitSuccess(LGInitSuccessInfo lgInitSuccessInfo)
	{
		Debug.LogWarningFormat("LightGameSdk init success", Array.Empty<object>());
		Debug.LogWarningFormat("deviceID: {0}, installID: {1}, ssID: {2}, userUniqueID: {3}", new object[4] { lgInitSuccessInfo.DeviceID, lgInitSuccessInfo.InstallID, lgInitSuccessInfo.SsID, lgInitSuccessInfo.UserUniqueID });
		Instance.sdkInitSuccess();
	}

	public void sdkInitSuccess()
	{
		startAbTest();
	}

	public void InitFail(int failCode, string failMsg)
	{
	}

	private void stopSdk()
	{
	}

	private void Start()
	{
	}

	public void AccountOnLoginSuccess(LGUser user)
	{
		LGSDKManager.OnAccountLoginSuccess?.Invoke(user);
	}

	public void AccountOnLoginFailed(int code, string msg)
	{
		LGSDKManager.OnAccountLoginFailed?.Invoke(code, msg);
	}

	private void Update()
	{
		updateVerify();
	}

	public void startAbTest()
	{
		string ssid = LGSDKCore.AppLogService.GetSsid();
		Debug.LogWarningFormat("start abtest, ssid: {0}", new object[1] { ssid });
		Debug.LogWarning((object)("startAbTest skip: " + Global.Instance.abtest.skip));
		initialized = true;
		LGSDKManager.OnInitSuccess?.Invoke();
	}

	public void startAd()
	{
		rewardAd = null;
		adSlot = null;
	}

	public void stopAd()
	{
	}

	private void onRequestRewardedVideo(string adSlot)
	{
		//IL_006b: Unknown result type (might be due to invalid IL or missing references)
		//IL_0071: Expected O, but got Unknown
		if (status == AdsStatus.Request)
		{
			RewardedVideoFailedEvent(adSlot, "loadfailed");
			return;
		}
		this.adSlot = adSlot;
		LGAbsRewardVideoAd val = LGSDKAd.MediationAdService.CreateRewardVideoAd();
		val.OnLoadError = onRewardAdError;
		val.OnRewardAdLoaded = onRewardAdLoad;
		val.OnRewardAdCached = onRewardAdCached;
		onReceiveRewardedVideo(this.adSlot, val);
		LGRewardVideoAdRequestParams val2 = new LGRewardVideoAdRequestParams();
		((LGBaseAdRequestParams)val2).android_AdId = adSlot;
		((LGBaseAdRequestParams)val2).videoPlayOrientation = 2;
		val2.userID = UserId;
		val2.rewardName = "resourceGem";
		val2.rewardAmount = 5;
		val2.mediaExtra = "media_extra";
		val.LoadRewardAd(val2);
		TransferManager.gt_ad_request(adSlot);
		status = AdsStatus.Request;
		Debug.Log((object)("OnRewardVideoAd onRequestRewardedVideo: " + adSlot));
	}

	private void onShowRewardedVideo(string adSlot, string adScene)
	{
		if (rewardAd == null)
		{
			Debug.Log((object)("OnRewardVideoAd onShowRewardedVideo no ads: " + adSlot));
			return;
		}
		this.adSlot = adSlot;
		playSuccess = false;
		Debug.Log((object)("头条广告日志 adScene " + adScene));
		rewardAd.ShowRewardAd();
		Debug.Log((object)("OnRewardVideoAd onShowRewardedVideo: " + adSlot));
	}

	public void RewardedVideoLoaded(string adSlot, LGAbsRewardVideoAd ad)
	{
		if (ad == null)
		{
			Debug.LogError((object)"RewardedVideoLoaded is null");
			RewardedVideoFailed(this.adSlot);
			return;
		}
		Debug.Log((object)("RewardedVideoLoaded: " + this.adSlot));
		status = AdsStatus.Success;
		RegisterRewardVideoAdInteractionListener(ad);
		RewardedVideoLoadedEvent(adSlot, "loaded");
		TransferManager.gt_ad_send(this.adSlot, "20000");
	}

	private void RegisterRewardVideoAdInteractionListener(LGAbsRewardVideoAd ad)
	{
		ad.OnAdShow_Interaction = onRewardAdShow;
		ad.OnAdClose_Interaction = onRewardAdClosed;
		ad.OnVideoError_Interaction = onVideoError;
		ad.OnRewardAdVerify_Interaction = onRewardVerify;
		ad.OnAdClick_Interaction = onRewardClick;
		ad.OnAdVideoDidSkip = Android_onSkippedVideo;
		ad.OnVideoComplete_Interaction = Android_onVideoComplete;
	}

	public void RewardedVideoFailed(string adSlot)
	{
		status = AdsStatus.Failed;
		onReceiveRewardedVideo(adSlot, null);
		RewardedVideoFailedEvent(adSlot, "loadfailed");
	}

	private bool onHasAdAvailable(string adSlot)
	{
		if (rewardAd != null)
		{
			return rewardAd.IsReady();
		}
		return false;
	}

	private void onReceiveRewardedVideo(string adSlot, LGAbsRewardVideoAd rewardVideo)
	{
		if (rewardAd != null)
		{
			onDisposeRewardedVideo(adSlot);
		}
		rewardAd = rewardVideo;
	}

	public void onDisposeRewardedVideo(string adSlot)
	{
		if (rewardAd != null)
		{
			Debug.Log((object)("OnRewardVideoAd onDisposeRewardedVideo: " + adSlot));
			rewardAd.Dispose();
			rewardAd = null;
		}
	}

	public bool HasAdAvailable(string adSlot)
	{
		bool flag = false;
		return onHasAdAvailable(adSlot);
	}

	public void RequestRewardedVideo(string adSlot)
	{
		onRequestRewardedVideo(adSlot);
	}

	public void ShowRewardedVideo(string adSlot, string adScene)
	{
		onShowRewardedVideo(adSlot, adScene);
	}

	public void RewardedVideoCachedEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoCachedEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoLoadedEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoLoadedEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoFailedEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoFailedEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoFailedToPlayEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoFailedToPlayEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoReceivedRewardEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoReceivedRewardEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoShownEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoShownEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoClosedEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoClosedEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoClickedEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardedVideoClickedEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoOnViewRenderSuccEvent(string adSlot, string argsJson)
	{
		LGSDKManager.OnViewRenderSuccEvent?.Invoke(adSlot, argsJson);
	}

	public void RewardedVideoOnViewRenderFailEvent(string adSlot, int code, string message)
	{
		LGSDKManager.OnViewRenderFailEvent?.Invoke(adSlot, code, message);
	}

	public void RewardedVideoOnRewardVerifyFail(string adSlot, string argsJson)
	{
		LGSDKManager.OnRewardVerifyFail?.Invoke(adSlot, argsJson);
	}

	private void startAnti()
	{
		LGAbsRealName.GlobalAntiAddictionCallback = getAntiAddictionInfo;
	}

	private void stopAnti()
	{
		LGAbsRealName.GlobalAntiAddictionCallback = null;
	}

	public void getAntiAddictionInfo(LGAntiAddictionResult result)
	{
		Log.D($"TriggerAntiAddiction 是否已经自动弹窗：{result.AutoPopup}" + $"-- CanPlayTime : {0}");
		if (result.AutoPopup)
		{
			Debug.Log((object)"防沉迷弹窗已自动弹出，游戏可关闭应用");
			AntiAddictionInLimitDuration();
		}
	}

	public void AntiAddictionTimeUsed()
	{
		popupCertWindowType = CertWindowType.QuitByTimeUsed;
	}

	public void AntiAddictionInLimitDuration()
	{
		popupCertWindowType = CertWindowType.QuitByInLimitDuration;
	}

	public void AntiAddictionGuestRecreate()
	{
		popupCertWindowType = CertWindowType.QuitByGuestRecreate;
	}

	public void AntiAddictionGuestExperenceTimeUsed()
	{
		popupCertWindowType = CertWindowType.QuitByGuestExperenceTimeUsed;
	}

	public void AntiAddictionPreReminder(int canPlayTime)
	{
	}

	public void JumpToCommunity()
	{
		string url = (CommonConfig.IsProduction() ? "https://u.ohayoo.cn/uapp/front/sfcommunity?from=2" : "https://testu.ohayoo.cn/uapp/front/sfcommunity?from=2");
		StartBrowser(url);
	}

	public void StartBrowser(string url)
	{
		LGSDKDevKit.DevKitBaseService.Android_RegisterDeepLinkInnerCallBack((Action<string>)Android_RegisterDeepLinkInnerCallBack);
		LGSDKDevKit.DevKitBaseService.Android_StartBrowser(url);
	}

	private void Android_RegisterDeepLinkInnerCallBack(string message)
	{
		Log.D("收到H5消息" + message);
	}

	public void Pay(IAPData iapData)
	{
		Debug.Log((object)("pay :" + iapData.payload));
		this.iapData = iapData;
		LGOrderInfoForPay val = null;
		val = GetAndroidOrderInfo();
		LGAbsPay.PayResultCallback = PayResultCallback;
		LGSDKPay.PayService.Pay(Singeton<LGPayService>.Instance.GenerateOrderInfo(val));
	}

	public void PayResultCallback(int code, string msg)
	{
		Debug.Log((object)("pay onPayResult:" + code + ",errMsg:" + msg));
		LGSDKManager.OnPayResult?.Invoke(iapData, code, msg);
	}

	private LGOrderInfoForPay GetiOSOrderInfo()
	{
		//IL_0000: Unknown result type (might be due to invalid IL or missing references)
		//IL_000b: Unknown result type (might be due to invalid IL or missing references)
		//IL_0013: Expected O, but got Unknown
		LGOrderInfoForPay val = new LGOrderInfoForPay();
		string sign_type = "MD5";
		val.sign_type = sign_type;
		return val;
	}

	private LGOrderInfoForPay GetAndroidOrderInfo()
	{
		//IL_0000: Unknown result type (might be due to invalid IL or missing references)
		//IL_000b: Unknown result type (might be due to invalid IL or missing references)
		//IL_0013: Expected O, but got Unknown
		LGOrderInfoForPay val = new LGOrderInfoForPay();
		string sign_type = "MD5";
		val.sign_type = sign_type;
		return val;
	}

	private void onRewardAdLoad()
	{
		Debug.Log((object)("onRewardAdLoad: " + adSlot));
		RewardedVideoLoaded(adSlot, rewardAd);
	}

	private void onRewardAdCached()
	{
		Debug.Log((object)("OnRewardVideoAdCached: " + adSlot));
		RewardedVideoCachedEvent(adSlot, "cached");
	}

	private void onRewardAdError(int code, string message)
	{
		Debug.LogWarning((object)("OnRewardVideoAdError: " + message + ", code:" + code + " adSlot: " + adSlot));
		RewardedVideoFailed(adSlot);
		TransferManager.gt_ad_send(adSlot, code.ToString());
	}

	private void onRewardAdShow()
	{
		Debug.Log((object)("OnRewardVideoAd show: " + adSlot));
		RewardedVideoShownEvent(adSlot, "shown");
	}

	private void onRewardAdClosed()
	{
		Debug.Log((object)("OnRewardVideoAd close: " + adSlot));
		if (playSuccess)
		{
			playSuccess = false;
			RewardedVideoReceivedRewardEvent(adSlot, "rewarded");
		}
		onDisposeRewardedVideo(adSlot);
		RewardedVideoClosedEvent(adSlot, "closed");
	}

	private void onVideoError()
	{
		Debug.LogWarning((object)("onVideoError: adSlot: " + adSlot));
		onDisposeRewardedVideo(adSlot);
		RewardedVideoFailedToPlayEvent(adSlot, "failedToPlay");
	}

	private void onRewardVerify(bool valid, float amount, string reward)
	{
		playSuccess = true;
	}

	private void onRewardClick()
	{
		Debug.Log((object)("OnRewardVideoAd bar click: " + adSlot));
		RewardedVideoClickedEvent(adSlot, "click");
	}

	private void Android_onSkippedVideo()
	{
	}

	private void Android_onVideoComplete()
	{
		Debug.Log((object)("OnRewardVideoAd complete: " + adSlot));
		playSuccess = true;
	}

	public void startAccount()
	{
		LGAbsAccount.GlobalLoginSuccessCallback = LoginSuccessCallback;
		LGAbsAccount.GlobalLoginFailCallback = LoginFailCallback;
	}

	public void stopAccount()
	{
		LGAbsAccount.GlobalLoginSuccessCallback = null;
		LGAbsAccount.GlobalLoginFailCallback = null;
	}

	private void LoginSuccessCallback(LGUser user, LGLoginInvokeMode mode)
	{
		//IL_0000: Unknown result type (might be due to invalid IL or missing references)
		//IL_0002: Invalid comparison between Unknown and I4
		//IL_00c5: Unknown result type (might be due to invalid IL or missing references)
		if ((int)mode == 2 && DataHandler<UserDefaultsDataHandler>.Instance.GetUserDataString("BindLoginData") == null)
		{
			DateTime now = DateTime.Now;
			string value = $"{now.Year}-{now.Month}-{now.Day}";
			DataHandler<UserDefaultsDataHandler>.Instance.SetUserData("BindLoginData", value);
		}
		Debug.Log((object)string.Concat("登录成功---openID:", user.OpenID, "---token:", user.Token, ",", user.NickName, ",", user.Avatar, ",", user.Mobile, ",", user.LoginType, ",", user.IsLogin.ToString()));
		AccountOnLoginSuccess(user);
	}

	private void LoginFailCallback(int code, string msg, LGLoginInvokeMode mode)
	{
		Debug.Log((object)("登录失败 code:" + code + ",errmsg:" + msg));
		AccountOnLoginFailed(code, msg);
	}

	public void NormalLogin()
	{
		Debug.Log((object)"LGSDKManager NormalLogin");
		LGSDKAccount.AccountService.NormalLogin();
	}

	public void SwitchAccount()
	{
		Debug.Log((object)"LGSDKManager SwitchAccount");
		LGSDKAccount.AccountService.SwitchAccount();
	}

	public void VisitorAccountBind()
	{
		Debug.Log((object)"LGSDKManager VisitorAccountBind");
		LGSDKAccount.AccountService.VisitorBindAccount();
	}

	public bool IsVisitor()
	{
		bool result = LGSDKAccount.AccountService.IsVisitor();
		Debug.Log((object)("isVisitor:" + result));
		return result;
	}

	private void startRealName()
	{
		LGAbsRealName.GlobalRealNameFailCallback = RealNameVerifiedFail;
		LGAbsRealName.GlobalRealNameSuccessCallback = RealNameVerifiedSucc;
	}

	private void stopRealName()
	{
		LGAbsRealName.GlobalRealNameFailCallback = null;
		LGAbsRealName.GlobalRealNameSuccessCallback = null;
	}

	public void RealNameVerified()
	{
		LGSDKDevKit.RealNameService.RealNameAuth();
	}

	public void GetDeviceRealNameVerifiedInfo()
	{
		LGSDKDevKit.RealNameService.CheckDeviceRealName((Action<bool, bool>)DeviceRealNameVerifiedInfoSucc, (Action<int, string>)DeviceRealNameVerifiedInfoFail);
	}

	private void RealNameVerifiedSucc(bool isAdult, LGRealNameRewardInfo info)
	{
		Debug.Log((object)("RealNameVerified onSuc isAdult:" + isAdult));
		RealNameVerifiedActionObject realNameVerifiedActionObject = new RealNameVerifiedActionObject();
		realNameVerifiedActionObject.type = 0;
		realNameVerifiedActionObject.isAdult = isAdult;
		realNameVerifiedActionObject.source = "normal";
		_realNameVerifiedActionObjects.Add(realNameVerifiedActionObject);
	}

	public void RealNameVerifiedFail(int errno, string msg)
	{
		Debug.Log((object)("RealNameVerified OnFail:" + errno + ",errMsg:" + msg));
		RealNameVerifiedActionObject realNameVerifiedActionObject = new RealNameVerifiedActionObject();
		realNameVerifiedActionObject.type = 1;
		realNameVerifiedActionObject.errno = errno;
		realNameVerifiedActionObject.source = "normal";
		_realNameVerifiedActionObjects.Add(realNameVerifiedActionObject);
	}

	public void DeviceRealNameVerifiedInfoSucc(bool isRealNameVerified, bool isAdult)
	{
		Debug.Log((object)("get device Verified onSuc isRealNameVerified:" + isRealNameVerified + "---isAdult:" + isAdult));
		if (isRealNameVerified)
		{
			RealNameVerifiedActionObject realNameVerifiedActionObject = new RealNameVerifiedActionObject();
			realNameVerifiedActionObject.type = 2;
			realNameVerifiedActionObject.source = "device";
			_realNameVerifiedActionObjects.Add(realNameVerifiedActionObject);
		}
		else
		{
			RealNameVerifiedActionObject realNameVerifiedActionObject2 = new RealNameVerifiedActionObject();
			realNameVerifiedActionObject2.type = 3;
			realNameVerifiedActionObject2.source = "device";
			_realNameVerifiedActionObjects.Add(realNameVerifiedActionObject2);
		}
	}

	public void DeviceRealNameVerifiedInfoFail(int errno, string msg)
	{
		Debug.LogError((object)("get device Verified info OnFail:" + errno + ",errmsg:" + msg));
		RealNameVerifiedActionObject realNameVerifiedActionObject = new RealNameVerifiedActionObject();
		realNameVerifiedActionObject.type = 3;
		realNameVerifiedActionObject.source = "device";
		_realNameVerifiedActionObjects.Add(realNameVerifiedActionObject);
	}

	private void updateVerify()
	{
		if (_realNameVerifiedActionObjects.Count == 0)
		{
			return;
		}
		foreach (RealNameVerifiedActionObject realNameVerifiedActionObject in _realNameVerifiedActionObjects)
		{
			if (realNameVerifiedActionObject.type == 0)
			{
				LGSDKManager.realNameVerifiedSuccAction?.Invoke(realNameVerifiedActionObject.isAdult);
			}
			else if (realNameVerifiedActionObject.type == 1)
			{
				LGSDKManager.realNameVerifiedFailAction?.Invoke();
			}
			else if (realNameVerifiedActionObject.type == 2)
			{
				LGSDKManager.getDeviceRealNameVerifiedSuccAction?.Invoke();
			}
			else if (realNameVerifiedActionObject.type == 3)
			{
				LGSDKManager.getDeviceRealNameVerifiedFailAction?.Invoke();
			}
		}
		_realNameVerifiedActionObjects.Clear();
	}

	private void startPush()
	{
	}

	private void stopPush()
	{
	}

	public string getPushId()
	{
		return string.Empty;
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
