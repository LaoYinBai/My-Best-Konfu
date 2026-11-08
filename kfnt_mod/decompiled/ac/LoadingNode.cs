using System;
using System.Collections;
using System.Collections.Generic;
using CommonUI;
using FairyGUI;
using FairyGUI.Utils;
using GameSDK;
using UnityEngine;

namespace LoadingUI;

public class LoadingNode : GComponent
{
	private TypingEffect _typingEffect;

	private LoadingStep _loadingStep;

	private LoadingStepStatus _loadingStatus;

	private SimpleAlertWindow _simpleAlertWindow;

	private float _typingEffectDelay;

	private float _curTypingEffectDelay;

	public const string DeleteVersionCode = "0.0.0";

	public const string hasAgreePrivacyPolicyKey = "hasAgreePrivacyPolicy";

	public LoadingAdapterNode adapterNode;

	public const string URL = "ui://qzw832siqqb0x";

	protected LoadingStep LoadingStep
	{
		get
		{
			return _loadingStep;
		}
		set
		{
			_loadingStep = value;
		}
	}

	protected LoadingStepStatus LoadingStatus
	{
		get
		{
			return _loadingStatus;
		}
		set
		{
			_loadingStatus = value;
		}
	}

	public event Action onStepFinishAction;

	public override void ConstructFromResource()
	{
		//IL_0023: Unknown result type (might be due to invalid IL or missing references)
		//IL_002d: Expected O, but got Unknown
		((GComponent)this).ConstructFromResource();
		Awake();
		((GObject)adapterNode.cadpaIconBtn).onClick.Add(new EventCallback0(clickCadpaBtnEvent));
		onStepFinishAction += closeWindowAction;
	}

	public override void Dispose()
	{
		//IL_0029: Unknown result type (might be due to invalid IL or missing references)
		//IL_0033: Expected O, but got Unknown
		onStepFinishAction -= closeWindowAction;
		((GObject)adapterNode.cadpaIconBtn).onClick.Remove(new EventCallback0(clickCadpaBtnEvent));
		onGObjectDestroy();
		((GComponent)this).Dispose();
	}

	private void Awake()
	{
		//IL_0024: Unknown result type (might be due to invalid IL or missing references)
		//IL_002e: Expected O, but got Unknown
		Common.PropertyTestLog("Profile LoadingNode Awake ");
		LoadingStatus = LoadingStepStatus.LoadingStepBegin;
		LoadingStep = LoadingStep.StepNone;
		Timers.inst.AddUpdate(new TimerCallback(Update));
		Global.Instance.OnPause += OnApplicationPause;
		DataHandler<UserBaseDataHandler>.Instance.loginEndEvent += LoginEndEvent;
		Global.Instance.addLoadingTips(adapterNode);
		Global.Instance.ntpHttpObject.requestSuccEvent += NtpHttpObject_RequestSuccEvent;
		Global.Instance.ntpHttpObject.requestFailEvent += NtpHttpObject_RequestFailEvent;
		Global.Instance.sysConfigGetObject.requestSuccEvent += SysConfigGetObject_RequestSuccEvent;
		Global.Instance.sysConfigGetObject.requestFailEvent += SysConfigGetObject_RequestFailEvent;
		Global.Instance.changeAdControlEvent += Instance_ChangeAdControlEvent;
		LGSDKManager.OnInitSuccess += LGSDKManager_OnInitSuccess;
		adapterNode.hadNumber.selectedIndex = 0;
	}

	private void LGSDKManager_OnInitSuccess()
	{
		if (_loadingStep == LoadingStep.StepInitSDK && LGSDKManager.Instance.initialized)
		{
			StepComplete();
		}
	}

	private void LoginEndEvent()
	{
		BuglyAgent.SetUserId(DataHandler<UserBaseDataHandler>.Instance.GameBaseUserData.userId);
		if (_loadingStep == LoadingStep.StepLogin)
		{
			StepComplete();
		}
	}

	private void NtpHttpObject_RequestSuccEvent(HttpObject<NTPResultObject> obj)
	{
		if (_loadingStep == LoadingStep.StepNTP)
		{
			StepComplete();
		}
		Common.PropertyTestLog("Profile NtpHttpObject_RequestSuccEvent Start ");
	}

	private void NtpHttpObject_RequestFailEvent(HttpObject<NTPResultObject> obj)
	{
		if (_loadingStep == LoadingStep.StepNTP)
		{
			StepComplete();
		}
		Common.PropertyTestLog("Profile NtpHttpObject_RequestFailEvent Start ");
	}

	private void SysConfigGetObject_RequestFailEvent(HttpObject<SysConfigGetResultObject> obj)
	{
		if (_loadingStep == LoadingStep.StepSysConfig)
		{
			StepComplete();
		}
	}

	private void SysConfigGetObject_RequestSuccEvent(HttpObject<SysConfigGetResultObject> obj)
	{
		if (_loadingStep == LoadingStep.StepSysConfig)
		{
			adapterNode.hadNumber.selectedIndex = 0;
			StepComplete();
		}
	}

	private void Instance_ChangeAdControlEvent()
	{
		if (_loadingStep == LoadingStep.StepCheckAd)
		{
			StepComplete();
		}
		Common.PropertyTestLog("Profile Instance_ChangeAdControlEvent Start ");
	}

	private void onGObjectDestroy()
	{
		//IL_00c5: Unknown result type (might be due to invalid IL or missing references)
		//IL_00cf: Expected O, but got Unknown
		LGSDKManager.OnInitSuccess -= LGSDKManager_OnInitSuccess;
		Global.Instance.ntpHttpObject.requestSuccEvent -= NtpHttpObject_RequestSuccEvent;
		Global.Instance.ntpHttpObject.requestFailEvent -= NtpHttpObject_RequestFailEvent;
		Global.Instance.sysConfigGetObject.requestSuccEvent -= SysConfigGetObject_RequestSuccEvent;
		Global.Instance.sysConfigGetObject.requestFailEvent -= SysConfigGetObject_RequestFailEvent;
		Global.Instance.changeAdControlEvent -= Instance_ChangeAdControlEvent;
		Global.Instance.removeLoadingTips(adapterNode);
		DataHandler<UserBaseDataHandler>.Instance.loginEndEvent -= LoginEndEvent;
		Timers.inst.Remove(new TimerCallback(Update));
		Global.Instance.OnPause -= OnApplicationPause;
	}

	protected override void OnUpdate()
	{
		((GComponent)this).OnUpdate();
	}

	private void CheckGuide()
	{
		if (!CommonConfig.canSkipGuide)
		{
			StepComplete();
			return;
		}
		bool flag = Common.BoolFromUserDefaults("SkipGuide");
		if (!DataHandler<GuideDataHandler>.Instance.CheckAllGuideComplete() && !flag)
		{
			SimpleAlertWindow window = new SimpleAlertWindow("测试跳过引导", "是否要跳过所有引导!", delegate
			{
				Common.SetBoolToUserDefaults(value: true, "SkipGuide");
				DataHandler<GuideDataHandler>.Instance.TestCompleteAllGuide();
				StepComplete();
			}, delegate
			{
				Common.SetBoolToUserDefaults(value: true, "SkipGuide");
				StepComplete();
			});
			SingletonData<PopupManager>.Instance.ShowWindow((Window)(object)window);
		}
		else
		{
			StepComplete();
		}
	}

	private void CheckLoadingDialogue()
	{
		//IL_0049: Unknown result type (might be due to invalid IL or missing references)
		//IL_0053: Expected O, but got Unknown
		//IL_0070: Unknown result type (might be due to invalid IL or missing references)
		//IL_007a: Expected O, but got Unknown
		if (!DataHandler<UserBaseDataHandler>.Instance.registerOpen)
		{
			StepComplete();
			return;
		}
		closeWindowAction();
		((GObject)adapterNode.tipsLabel1).text = Common.LocalizedStringForKey("loadingdialoguedesc");
		((GObject)adapterNode).onClick.Add(new EventCallback1(SkipDialoguedescEvent));
		adapterNode.hadTipsStatus.selectedIndex = 1;
		_typingEffect = new TypingEffect(adapterNode.tipsLabel1);
		float num = 0.1f;
		_typingEffect.Start();
		_typingEffect.PrintAll(num);
		_typingEffectDelay = (float)((GObject)adapterNode.tipsLabel1).text.Length * num + 1f;
		_curTypingEffectDelay = 0f;
	}

	private void SkipDialoguedescEvent(EventContext eventContext)
	{
		//IL_001f: Unknown result type (might be due to invalid IL or missing references)
		//IL_0029: Expected O, but got Unknown
		if (eventContext.inputEvent.isDoubleClick)
		{
			((GObject)adapterNode).onClick.Remove(new EventCallback1(SkipDialoguedescEvent));
			if (_typingEffect != null)
			{
				_typingEffect.Cancel();
				((GObject)adapterNode.tipsLabel1).text = Common.LocalizedStringForKey("loadingdialoguedesc");
				_curTypingEffectDelay = 0f;
				_typingEffectDelay = 0.15f;
			}
		}
	}

	private void OnApplicationPause(bool pauseStatus)
	{
		if (pauseStatus)
		{
			((GObject)this).touchable = true;
			Common.Log("激活按钮");
		}
	}

	private void clickCadpaBtnEvent()
	{
		string title = "适龄提示";
		string content = "    本游戏是一款有趣的动作冒险类游戏，适用于年满12周岁及以上的用户，建议未成年人在家长监护下使用游戏产品。\n    本游戏以架空武侠为背景，围绕主人公阿宅从江湖小虾米成长为大侠的剧情展开，游戏玩法基于简单的肢体操作，鼓励培养玩家成长概念及勇气，游戏中没有基于文字和语音的陌生人社交系统。\n    本游戏中有用户实名认证系统，认证为未成年人的用户将接受以下管理：游戏中无收费内容；未成年人用户仅可在周五、周六、周日和法定节假日的20时至21时进入游戏。\n    本游戏以武侠文化为主题，游戏有传承中华传统文化，提升用户思维判断能力和反应速度的社会价值。";
		_simpleAlertWindow = new SimpleAlertWindow(title, content, SimpleAlertWindowType.NoBtn, null, null, "好吧");
		SingletonData<PopupManager>.Instance.ShowWindow((Window)(object)_simpleAlertWindow);
		((GTextField)_simpleAlertWindow.mainNode.adapterNode.contentNode.contentLabel).align = (AlignType)0;
	}

	private void closeWindowAction()
	{
		_simpleAlertWindow?.CloseBtnEvent();
		_simpleAlertWindow = null;
	}

	private void StepComplete()
	{
		LoadingStatus = LoadingStepStatus.LoadingStepComplete;
		if (Common.StringFromUserDefaults("userHashId") != string.Empty)
		{
			((GObject)adapterNode.userIdLabel).text = Common.StringFromUserDefaults("userHashId");
		}
	}

	private void StepIn()
	{
		LoadingStatus = LoadingStepStatus.LoadingStepBegin;
		LoadingStep = _loadingStep + 1;
	}

	private void DoLoadingStep()
	{
		Common.PropertyTestLog("Profile LoadingNode DoLoadingStep step: " + _loadingStep);
		switch (_loadingStep)
		{
		case LoadingStep.StepNone:
			StepComplete();
			break;
		case LoadingStep.StepInitSDK:
			InitSDK();
			break;
		case LoadingStep.StepLoadInitAsset:
			LoadInitAsset();
			break;
		case LoadingStep.StepShowLabel:
			ShowLabel();
			break;
		case LoadingStep.StepCheckLocalData:
			CheckLocalData();
			break;
		case LoadingStep.StepCheckEncryptData:
			CheckEncryptData();
			break;
		case LoadingStep.StepLogin:
			UserLogin();
			break;
		case LoadingStep.StepSysConfig:
			GetSysConfig();
			break;
		case LoadingStep.StepNTP:
			GetNtpTime();
			break;
		case LoadingStep.StepCheckAd:
			GetControlAd();
			break;
		case LoadingStep.StepCheckGuide:
			CheckGuide();
			break;
		case LoadingStep.StepCheckLoadingDialogue:
			CheckLoadingDialogue();
			break;
		case LoadingStep.StepInitScene:
			StepComplete();
			break;
		case LoadingStep.StepReplaceScene:
			ReplaceGameScene();
			break;
		case LoadingStep.StepFinish:
			StepFinish();
			break;
		}
	}

	private void InitSDK()
	{
		LGSDKManager.InitManager();
		CommonConfig.InitServerUrl();
		Common.PropertyTestLog("Profile InitSDK");
		Resources.UnloadUnusedAssets();
		Common.PropertyTestLog("Profile UnloadUnusedAssets ");
		SingletonData<SettingManager>.Instance.Awake();
		Common.PropertyTestLog("Profile SettingManager Awake");
		ChannelManager.Instance.InitStatSDK();
		Common.PropertyTestLog("Profile ChannelManager InitStatSDK ");
		TrashMan component = ((Component)Object.Instantiate<Transform>(SingletonData<FileUtils>.Instance.LoadFromFile<Transform>("TrashManObject.prefab"))).gameObject.GetComponent<TrashMan>();
		Common.PropertyTestLog("Profile trashMan " + component);
		SingletonData<SettingManager>.Instance.Start();
		Common.PropertyTestLog("Profile SettingManager Start ");
		Global.Instance.resetMultiTouch();
		Global.Instance.SetFullScreenSaScale();
		Global.Instance.adapterIPhoneX();
		if (LGSDKManager.Instance.initialized)
		{
			StepComplete();
		}
	}

	private void LoadInitAsset()
	{
		SingletonData<SettingManager>.Instance.AddCommonPackage();
		Common.PropertyTestLog("Profile AddCommonPackage Start ");
		Global.Instance.AddCommonUI();
		Common.PropertyTestLog("Profile AddCommonUI Start ");
		StepComplete();
	}

	private void ShowLabel()
	{
		((GObject)adapterNode.versionLabel).text = $"v{Application.version}";
		changeTipsLabel();
		Common.PropertyTestLog("Profile LoadingNode changeTipsLabel ");
		StepComplete();
	}

	private void CheckLocalData()
	{
		((MonoBehaviour)CoroutineUtils.Instance).StartCoroutine(CorReloadConfig());
	}

	protected IEnumerator CorReloadConfig()
	{
		ConfigHandler<ModuleManager>.Instance.LoadConfig();
		Common.PropertyTestLog("Profile ModuleManager LoadConfig Start ");
		while (!ConfigHandler<ModuleManager>.Instance.configLoaded)
		{
			yield return null;
		}
		List<IConfigHandler> configHandlers = DictionaryConvert.getValuesList<string, IConfigHandler>(ConfigHandler<ModuleManager>.Instance.moduleConfigs);
		int count = configHandlers.Count;
		int index = 0;
		while (index < count)
		{
			IConfigHandler item = configHandlers[index];
			index++;
			Debug.Log((object)("item loadConfig: " + item));
			item.LoadConfig();
			Common.PropertyTestLog("Profile ModuleManager item loadConfig Start " + item);
			while (!item.configLoaded)
			{
				yield return null;
			}
		}
		while (!ChannelManager.isStatSDKStarted)
		{
			yield return null;
		}
		StepComplete();
	}

	private void CheckEncryptData()
	{
		string text = "complete";
		if (!PlayerPrefs.HasKey(text))
		{
			Debug.Log((object)"start encrypt data");
			foreach (KeyValuePair<string, IDataHandler> moduleData in ConfigHandler<ModuleManager>.Instance.moduleDatas)
			{
				moduleData.Value.EncryptData(encrypt: true);
				Common.PropertyTestLog("Profile ModuleManager EncryptData Start ");
			}
			PlayerPrefs.SetString(text, "FAUc1Cp5WXK5q7oA");
			PlayerPrefsUtils.Save();
		}
		StepComplete();
	}

	private void UserLogin()
	{
		DataHandler<UserBaseDataHandler>.Instance.loginStart();
		Global.Instance.startAccount();
		Common.PropertyTestLog("Profile LoginStart Start ");
		AdsManager instance = SingletonData<AdsManager>.Instance;
		Debug.Log((object)("adsManager init" + ObjectConvert.toString((object)instance, (string)null)));
		TransferManager.gt_log_in(SingletonData<PopupManager>.Instance.startSource);
		if (LGSDKManager.Instance.androidChannel == "plane" && !Common.BoolFromUserDefaults("planeStats"))
		{
			new StatsHttpObject().addPostData("1477022124");
		}
	}

	private void GetSysConfig()
	{
		Global.Instance.sysConfigGetObject.initialize();
		Global.Instance.sysConfigGetObject.request();
	}

	private void GetNtpTime()
	{
		Global.Instance.ntpHttpObject.initialize();
		NTPManger.Instance.synchronizeTime();
		Common.PropertyTestLog("Profile GetNtpTime Start ");
	}

	private void GetControlAd()
	{
		Global.Instance.controlAdObject.initialize();
		Global.Instance.requestControl();
		Common.PropertyTestLog("Profile GetControlAd Start ");
	}

	private void ReplaceGameScene()
	{
		Global.Instance.LoadNewScene((SceneName)2);
	}

	private void StepFinish()
	{
		this.onStepFinishAction?.Invoke();
	}

	private void Update(object param)
	{
		if (_loadingStatus == LoadingStepStatus.LoadingStepComplete)
		{
			StepIn();
		}
		else if (_loadingStatus == LoadingStepStatus.LoadingStepBegin)
		{
			LoadingStatus = LoadingStepStatus.LoadingStepWaiting;
			DoLoadingStep();
		}
		if (_typingEffect != null)
		{
			_curTypingEffectDelay += Time.deltaTime;
			if (_curTypingEffectDelay >= _typingEffectDelay)
			{
				_typingEffect.Cancel();
				_typingEffect = null;
				StepComplete();
			}
		}
	}

	private void changeTipsLabel()
	{
	}

	public static LoadingNode CreateInstance()
	{
		return (LoadingNode)(object)UIPackage.CreateObject("LoadingUI", "LoadingNode");
	}

	public override void ConstructFromXML(XML xml)
	{
		((GComponent)this).ConstructFromXML(xml);
		adapterNode = (LoadingAdapterNode)(object)((GComponent)this).GetChildAt(0);
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
