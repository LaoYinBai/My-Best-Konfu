using System;
using System.Collections.Generic;
using FairyGUI;
using GamePurchasing;
using GameSDK;
using UnityEngine;

public sealed class UserBaseDataHandler : DataHandler<UserBaseDataHandler>
{
	private GameBaseUserData _baseData;

	private Action<bool> _resultCallback;

	public List<string> uploadArchiveModules = new List<string> { "chapter", "endless", "resource", "energy", "weaponChip", "acc", "weapon", "dailyChest" };

	public bool archiveChanged;

	private Dictionary<string, int> _syncCacheCount;

	private Dictionary<string, int> _syncingCacheCount;

	private Dictionary<string, string> _fullArchiveData;

	private Dictionary<string, string> _archiveData;

	private ArchiveStatus _archiveStatus;

	private LoginStatus _status;

	public GameBaseUserConfig configRoot { get; private set; }

	public GameBaseUserData GameBaseUserData => _baseData;

	public bool registerOpen { get; private set; }

	public string accountType { get; private set; }

	public AccountObject accountObject { get; private set; }

	public ArchiveGetHttpResultObject accountResultObj { get; private set; }

	public string accountToken => accountObject.token;

	public AccountLoginAction loginAction { get; private set; }

	public string accountId
	{
		get
		{
			if (_baseData == null)
			{
				return string.Empty;
			}
			return _baseData.bindData.accountId;
		}
	}

	public bool hasBindAccount
	{
		get
		{
			if (_baseData != null && _baseData.hasBindAccount)
			{
				return !accountObject.isGuest;
			}
			return false;
		}
	}

	public string userToken { get; private set; }

	public bool hasLogin
	{
		get
		{
			if (!string.IsNullOrEmpty(userToken))
			{
				return !string.IsNullOrEmpty(accountId);
			}
			return false;
		}
	}

	public bool hasAccountLogin
	{
		get
		{
			if (!string.IsNullOrEmpty(accountToken))
			{
				return !string.IsNullOrEmpty(accountId);
			}
			return false;
		}
	}

	public string deviceId { get; private set; }

	public string imei { get; private set; }

	public string idfa { get; private set; }

	public string idfv { get; private set; }

	public AccountLoginStatus accountStatus { get; private set; }

	public AccountData accountData { get; private set; }

	public bool isForceLogin { get; private set; }

	public Action<bool> forceLoginCallback { get; private set; }

	public bool isForceAccountLogin { get; private set; }

	public Action<bool> forceAccountLoginCallback { get; private set; }

	public ArchiveStatus archiveStatus
	{
		get
		{
			return _archiveStatus;
		}
		set
		{
			//IL_0062: Unknown result type (might be due to invalid IL or missing references)
			//IL_006c: Expected O, but got Unknown
			//IL_00c5: Unknown result type (might be due to invalid IL or missing references)
			//IL_00cf: Expected O, but got Unknown
			if (_archiveStatus == value)
			{
				return;
			}
			_archiveStatus = value;
			if (_archiveStatus != ArchiveStatus.Prepare)
			{
				if (_archiveStatus == ArchiveStatus.Get)
				{
					archiveGetObject.request();
				}
				else if (_archiveStatus == ArchiveStatus.GetFailed)
				{
					SingletonData<TimerManager>.Instance.Add(DataHandler<RequestDataHandler>.Instance.configRoot.retry.login, 1, new TimerCallback(retryGetArchive));
				}
				else if (_archiveStatus != ArchiveStatus.Conflict)
				{
					if (_archiveStatus == ArchiveStatus.FullArchive)
					{
						Dictionary<string, string> archive = fullArchiveData();
						archivePutObject.request(archive);
					}
					else if (_archiveStatus == ArchiveStatus.FullArchiveFailed)
					{
						SingletonData<TimerManager>.Instance.Add(DataHandler<RequestDataHandler>.Instance.configRoot.retry.login, 1, new TimerCallback(retryFullArchive));
					}
				}
			}
			this.archiveStatusChangedEvent?.Invoke();
		}
	}

	public ArchiveGetHttpObject archiveGetObject { get; private set; }

	public ArchivePutHttpObject archivePutObject { get; private set; }

	public ArchivePatchHttpObject archivePatchObject { get; private set; }

	public LoginStatus status
	{
		get
		{
			return _status;
		}
		set
		{
			//IL_0031: Unknown result type (might be due to invalid IL or missing references)
			//IL_003b: Expected O, but got Unknown
			//IL_00fa: Unknown result type (might be due to invalid IL or missing references)
			//IL_0104: Expected O, but got Unknown
			//IL_011b: Unknown result type (might be due to invalid IL or missing references)
			//IL_0125: Expected O, but got Unknown
			//IL_00b6: Unknown result type (might be due to invalid IL or missing references)
			//IL_00c0: Expected O, but got Unknown
			//IL_0143: Unknown result type (might be due to invalid IL or missing references)
			//IL_014d: Expected O, but got Unknown
			if (_status == value)
			{
				return;
			}
			_status = value;
			if (_status == LoginStatus.LoginPrepare)
			{
				userToken = string.Empty;
				SingletonData<TimerManager>.Instance.Remove(new TimerCallback(checkLoginStatus));
				archiveStatus = ArchiveStatus.Prepare;
			}
			else if (_status == LoginStatus.Login)
			{
				archiveStatus = ArchiveStatus.Prepare;
				requestServerLogin();
			}
			else if (_status == LoginStatus.LoginSuccess)
			{
				archiveStatus = ArchiveStatus.Prepare;
				archiveStatus = ArchiveStatus.Get;
				if (_baseData.status != 1 && !string.IsNullOrEmpty(accountId))
				{
					SingletonData<TimerManager>.Instance.Add(DataHandler<RequestDataHandler>.Instance.configRoot.interval.getLogin, 0, new TimerCallback(checkLoginStatus));
				}
				Global.Instance.requestFlags();
			}
			else if (_status == LoginStatus.LoginFailed)
			{
				SingletonData<TimerManager>.Instance.Add(DataHandler<RequestDataHandler>.Instance.configRoot.retry.login, 1, new TimerCallback(retryLogin));
			}
			else if (_status == LoginStatus.Offline)
			{
				SingletonData<TimerManager>.Instance.Remove(new TimerCallback(checkLoginStatus));
				archiveStatus = ArchiveStatus.Prepare;
			}
			else if (_status == LoginStatus.ForceOffline)
			{
				SingletonData<TimerManager>.Instance.Remove(new TimerCallback(checkLoginStatus));
				archiveStatus = ArchiveStatus.Prepare;
				this.accountForceOfflineEvent?.Invoke();
			}
			this.loginStatusChangedEvent?.Invoke();
		}
	}

	public LoginGetHttpObject loginGetObject { get; private set; }

	public LoginHttpObject loginObject { get; private set; }

	public RegisterStatus registerStatus { get; private set; }

	public event OnCustomAction accountLoginOfflineEvent;

	public event OnCustomAction accountForceOfflineEvent;

	public event OnCustomAction loginEndEvent;

	public event OnAccountLoginSuccess accountLoginSuccessEvent;

	public event OnCustomAction accountAlreadyBindEvent;

	public event OnCustomAction accountLoginFailedEvent;

	public event OnCustomAction accountLoginCancelEvent;

	public event OnArchiveConflictAction archiveConflictEvent;

	public event OnCustomAction archiveStatusChangedEvent;

	public event OnCustomAction loginStatusChangedEvent;

	~UserBaseDataHandler()
	{
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(retryLogin));
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(checkLoginStatus));
	}

	protected override void OnInit()
	{
		//IL_0026: Unknown result type (might be due to invalid IL or missing references)
		_tableName = "userBase";
		configRoot = new GameBaseUserConfig();
		deviceId = Common.GenerateGlobalUniqueIdentifier();
		try
		{
			AndroidJavaObject val = ((AndroidJavaObject)new AndroidJavaClass("com.unity3d.player.UnityPlayer")).GetStatic<AndroidJavaObject>("currentActivity").Call<AndroidJavaObject>("getSystemService", new object[1] { "phone" });
			string text = val.Call<string>("getImei", new object[1] { 0 });
			string text2 = val.Call<string>("getImei", new object[1] { 1 });
			if (text != null && text.Length > 0)
			{
				imei = text;
			}
			else if (text2 != null && text2.Length > 0)
			{
				imei = text2;
			}
			else
			{
				imei = "0";
			}
		}
		catch (Exception)
		{
			imei = SystemInfo.deviceUniqueIdentifier;
		}
		Debug.Log((object)("imei.get:" + imei));
		Debug.Log((object)("imei:" + imei));
		Debug.Log((object)("idfa:" + idfa));
		Debug.Log((object)("idfv:" + idfv));
		Debug.Log((object)("this.deviceId:" + deviceId));
		initLogin();
		initArchive();
		initAccount();
		startLogin();
		startArchive();
		startAccount();
		initPush();
		startPush();
	}

	protected override void OnLoadUserData()
	{
		base.OnLoadUserData();
		_baseData = new GameBaseUserData();
		_baseData.reloadData(_userData, configRoot);
	}

	public override void RemoveDelegate()
	{
		stopLogin();
		stopArchive();
		stopAccount();
		stopPush();
	}

	public override void SaveCacheData()
	{
		_userData = _baseData.toCacheData();
		base.SaveCacheData();
	}

	public void loginStart()
	{
		DataHandlerUtils.SetHashValue(string.Empty);
		ReloadUserData();
		status = LoginStatus.LoginPrepare;
		if (_baseData.hasInitData)
		{
			login();
			loginEnd();
		}
		else
		{
			registerStatus = RegisterStatus.Register;
			accountLogin();
		}
	}

	private void login()
	{
		registerOpen = false;
		_baseData.login();
		SaveCacheData();
	}

	private void loginEnd()
	{
		Debug.Log((object)"game loginEnd, call account login");
		accountLogin();
		afterLoginEnd();
	}

	private void afterLoginEnd()
	{
		SingletonData<IAPManager>.Instance.SetApplicationUserName(GameBaseUserData.userId);
		ChannelManager.Instance.TrackingRetentionEvent(GameBaseUserData.userId);
		DataHandlerUtils.SetHashValue(GameBaseUserData.userId);
		this.loginEndEvent?.Invoke();
	}

	private void beginSwitchAccount()
	{
		_baseData.beginSwitch();
		SaveCacheData();
	}

	private void endSwitchAccount()
	{
		_baseData.endSwitch();
		SaveCacheData();
	}

	public void updateSaveTime()
	{
		_baseData.updateSaveTime();
		SaveCacheData();
	}

	public void updateArchiveTime()
	{
		_baseData.updateArchiveTime();
		SaveCacheData();
	}

	private void initAccount()
	{
		accountType = "openid";
		accountObject = new DouyinAccountObject();
		accountStatus = AccountLoginStatus.None;
		loginAction = AccountLoginAction.None;
	}

	private void startAccount()
	{
		accountObject.start();
		accountObject.loginSuccessEvent += AccountObject_LoginSuccessEvent;
		accountObject.loginFailedEvent += AccountObject_LoginFailedEvent;
		accountObject.alreadyBindEvent += AccountObject_AlreadyBindEvent;
		accountObject.loginCancelEvent += AccountObject_LoginCancelEvent;
	}

	private void stopAccount()
	{
		accountObject.stop();
		accountObject.loginSuccessEvent -= AccountObject_LoginSuccessEvent;
		accountObject.loginFailedEvent -= AccountObject_LoginFailedEvent;
		accountObject.alreadyBindEvent -= AccountObject_AlreadyBindEvent;
		accountObject.loginCancelEvent -= AccountObject_LoginCancelEvent;
	}

	public void forceLogin(Action<bool> callback)
	{
		isForceLogin = true;
		forceLoginCallback = callback;
		if (hasLogin)
		{
			triggerForceLoginCallback();
		}
		else if (string.IsNullOrEmpty(accountId))
		{
			status = LoginStatus.LoginPrepare;
			accountLogin();
		}
		else if (string.IsNullOrEmpty(userToken))
		{
			status = LoginStatus.LoginPrepare;
			status = LoginStatus.Login;
		}
	}

	public void forceAccountLogin(Action<bool> callback)
	{
		isForceAccountLogin = true;
		forceAccountLoginCallback = callback;
		if (hasAccountLogin)
		{
			triggerForceAccountLoginCallback();
			return;
		}
		status = LoginStatus.LoginPrepare;
		accountLogin();
	}

	private void triggerForceLoginCallback()
	{
		forceLoginCallback?.Invoke(hasLogin);
		forceLoginCallback = null;
		isForceLogin = false;
	}

	private void triggerForceAccountLoginCallback()
	{
		forceAccountLoginCallback?.Invoke(hasAccountLogin);
		forceAccountLoginCallback = null;
		isForceAccountLogin = false;
	}

	public void accountLogin()
	{
		if (accountStatus != AccountLoginStatus.Login)
		{
			loginAction = AccountLoginAction.GuestLogin;
			accountStatus = AccountLoginStatus.Login;
			if (accountData != null)
			{
				accountLoginSuccess();
			}
			else
			{
				accountObject.guestLogin();
			}
		}
	}

	public void bindAccount()
	{
		if (accountStatus != AccountLoginStatus.Login)
		{
			loginAction = AccountLoginAction.Bind;
			accountStatus = AccountLoginStatus.Login;
			accountData = null;
			accountObject.bindAccount();
		}
	}

	public void switchAccount()
	{
		if (accountStatus != AccountLoginStatus.Login)
		{
			loginAction = AccountLoginAction.Switch;
			accountStatus = AccountLoginStatus.Login;
			accountData = null;
			accountObject.switchAccount();
		}
	}

	private void AccountObject_LoginFailedEvent(int errno)
	{
		if (loginAction == AccountLoginAction.GuestLogin)
		{
			accountObject.guestLogin();
			return;
		}
		this.accountLoginFailedEvent?.Invoke();
		accountLoginFailed();
	}

	private void AccountObject_AlreadyBindEvent()
	{
		if (loginAction == AccountLoginAction.GuestLogin)
		{
			accountObject.guestLogin();
			return;
		}
		this.accountAlreadyBindEvent?.Invoke();
		accountLoginFailed();
	}

	private void AccountObject_LoginCancelEvent()
	{
		if (loginAction == AccountLoginAction.GuestLogin)
		{
			accountObject.guestLogin();
			return;
		}
		this.accountLoginCancelEvent?.Invoke();
		accountLoginFailed();
	}

	private void AccountObject_LoginSuccessEvent(string accountId, string accountName, int subType, bool isRealNameVerified)
	{
		int accountSubType = GameBaseUserData.bindData.accountSubType;
		accountData = new AccountData();
		accountData.accountId = accountId;
		accountData.accountName = accountName;
		accountData.subType = subType;
		accountData.isRealNameVerified = isRealNameVerified;
		Debug.Log((object)("AccountObject_LoginSuccessEvent: accountId: " + accountId + "  accountName: " + accountName + " loginType: " + subType + " isRealNameVerified: " + isRealNameVerified.ToString()));
		accountLoginSuccess();
		this.accountLoginSuccessEvent?.Invoke(isRealNameVerified);
		if (loginAction == AccountLoginAction.Bind)
		{
			TransferManager.gt_account_binding(subType);
		}
		else if (loginAction == AccountLoginAction.Switch)
		{
			TransferManager.gt_account_change(accountSubType, subType);
		}
	}

	private void accountLoginSuccess()
	{
		if (accountStatus != AccountLoginStatus.Login)
		{
			return;
		}
		accountStatus = AccountLoginStatus.Success;
		if (registerStatus != RegisterStatus.Register)
		{
			if (accountId != accountData.accountId)
			{
				beginSwitchAccount();
				archiveStatus = ArchiveStatus.Prepare;
			}
			_baseData.updateAccount(accountData.accountId, accountData.accountName, accountType, accountData.subType, accountData.isRealNameVerified);
			SaveCacheData();
		}
		status = LoginStatus.Login;
		triggerForceAccountLoginCallback();
	}

	private void accountLoginFailed()
	{
		if (accountStatus == AccountLoginStatus.Login)
		{
			accountStatus = AccountLoginStatus.Failed;
			if (registerStatus == RegisterStatus.Register)
			{
				register(switchAcct: true);
			}
			else
			{
				status = LoginStatus.Login;
			}
			triggerForceAccountLoginCallback();
		}
	}

	private void initArchive()
	{
		_fullArchiveData = new Dictionary<string, string>();
		_archiveData = new Dictionary<string, string>();
		_syncCacheCount = new Dictionary<string, int>();
		_syncingCacheCount = new Dictionary<string, int>();
		archiveGetObject = new ArchiveGetHttpObject();
		archivePutObject = new ArchivePutHttpObject();
		archivePatchObject = new ArchivePatchHttpObject();
		archiveGetObject.initialize();
		archivePutObject.initialize();
		archivePatchObject.initialize();
	}

	private void startArchive()
	{
		archiveGetObject.requestSuccEvent += ArchiveGetObject_RequestSuccEvent;
		archiveGetObject.requestFailEvent += ArchiveGetObject_RequestFailEvent;
		archivePutObject.requestSuccEvent += ArchivePutObject_RequestSuccEvent;
		archivePutObject.requestFailEvent += ArchivePutObject_RequestFailEvent;
		archivePatchObject.requestSuccEvent += ArchivePatchObject_RequestSuccEvent;
		archivePatchObject.requestFailEvent += ArchivePatchObject_RequestFailEvent;
		archiveChanged = false;
	}

	private void stopArchive()
	{
		archiveGetObject.requestSuccEvent -= ArchiveGetObject_RequestSuccEvent;
		archiveGetObject.requestFailEvent -= ArchiveGetObject_RequestFailEvent;
		archivePutObject.requestSuccEvent -= ArchivePutObject_RequestSuccEvent;
		archivePutObject.requestFailEvent -= ArchivePutObject_RequestFailEvent;
		archivePatchObject.requestSuccEvent -= ArchivePatchObject_RequestSuccEvent;
		archivePatchObject.requestFailEvent -= ArchivePatchObject_RequestFailEvent;
		this.accountLoginSuccessEvent = null;
		this.accountLoginFailedEvent = null;
		this.accountAlreadyBindEvent = null;
		this.accountLoginCancelEvent = null;
		this.archiveConflictEvent = null;
		this.archiveStatusChangedEvent = null;
	}

	private void ArchiveGetObject_RequestFailEvent(HttpObject<ArchiveGetHttpResultObject> obj)
	{
		if (archiveStatus != ArchiveStatus.Get)
		{
			return;
		}
		if (obj.resultObj.status == 80007 || obj.resultObj.status == 80008)
		{
			if (registerStatus == RegisterStatus.Register)
			{
				register();
			}
			else if (_baseData.status == 1)
			{
				syncLocalArchive();
			}
			else
			{
				archiveStatus = ArchiveStatus.FullArchive;
			}
		}
		else if (registerStatus == RegisterStatus.Register)
		{
			register(switchAcct: true);
		}
		else
		{
			archiveStatus = ArchiveStatus.GetFailed;
		}
	}

	private void ArchiveGetObject_RequestSuccEvent(HttpObject<ArchiveGetHttpResultObject> obj)
	{
		if (archiveStatus != ArchiveStatus.Get)
		{
			return;
		}
		if (registerStatus == RegisterStatus.Register)
		{
			registerStatus = RegisterStatus.Over;
			replaceArchive(obj.resultObj);
			loginStart();
			return;
		}
		if (obj.resultObj.getResult.userId != _baseData.userId)
		{
			accountResultObj = obj.resultObj;
			archiveStatus = ArchiveStatus.Conflict;
			this.archiveConflictEvent?.Invoke(obj.resultObj);
			return;
		}
		if (_baseData.status == 1)
		{
			endSwitchAccount();
			status = LoginStatus.LoginPrepare;
			status = LoginStatus.Login;
			return;
		}
		if (_baseData.updateTime < obj.resultObj.getResult.baseUserData.updateTime)
		{
			replaceArchive(obj.resultObj, "account");
		}
		archiveStatus = ArchiveStatus.FullArchive;
	}

	private void ArchivePutObject_RequestFailEvent(HttpObject<ArchivePutHttpResultObject> obj)
	{
		if (archiveStatus == ArchiveStatus.FullArchive)
		{
			archiveStatus = ArchiveStatus.FullArchiveFailed;
			SafeResultCallback();
		}
	}

	private void ArchivePutObject_RequestSuccEvent(HttpObject<ArchivePutHttpResultObject> obj)
	{
		updateArchiveTime();
		if (archiveStatus == ArchiveStatus.FullArchive)
		{
			archiveStatus = ArchiveStatus.DeltaArchive;
			SafeResultCallback();
		}
	}

	private void ArchivePatchObject_RequestFailEvent(HttpObject<ArchivePatchHttpResultObject> obj)
	{
		DictionaryConvert.addEntriesFromDictionary<string, int>(_syncCacheCount, _syncingCacheCount);
	}

	private void ArchivePatchObject_RequestSuccEvent(HttpObject<ArchivePatchHttpResultObject> obj)
	{
		updateArchiveTime();
		_syncingCacheCount.Clear();
		archiveChanged = false;
	}

	public void replaceArchive(ArchiveGetHttpResultObject resultObject)
	{
		DataHandlerUtils.SetHashValue(string.Empty);
		Dictionary<string, IDataHandler> moduleDatas = ConfigHandler<ModuleManager>.Instance.moduleDatas;
		Dictionary<string, string> dictionary = DictionaryConvert.copy<string, string>(resultObject.getResult.archive);
		foreach (KeyValuePair<string, IDataHandler> item in moduleDatas)
		{
			string userDataTable = DictionaryConvert.stringValue<string, string>(dictionary, item.Key, string.Empty);
			item.Value.SetUserDataTable(userDataTable);
			dictionary.Remove(item.Key);
		}
		foreach (KeyValuePair<string, string> item2 in dictionary)
		{
			DataHandlerUtils.SetUserDataTableByString(DataSaveType.DB, item2.Key, item2.Value);
			DataHandlerUtils.SetUserDataTableByString(DataSaveType.PlayerPref, item2.Key, item2.Value);
		}
		PlayerPrefsUtils.ForceSave();
		ConfigHandler<ModuleManager>.Instance.ReloadGameData();
		SingletonData<SettingManager>.Instance.CleanCacheData();
		syncSwitchAccountCount();
	}

	public void syncSwitchAccountCount()
	{
		DataHandler<AccountDataHandler>.Instance.addSwitchCount();
		PlayerPrefsUtils.ForceSave();
	}

	public void replaceArchive(ArchiveGetHttpResultObject resultObject, string key)
	{
		Dictionary<string, IDataHandler> moduleDatas = ConfigHandler<ModuleManager>.Instance.moduleDatas;
		IDataHandler value = null;
		if (moduleDatas.TryGetValue(key, out value))
		{
			string userDataTable = DictionaryConvert.stringValue<string, string>(resultObject.getResult.archive, key, string.Empty);
			value.SetUserDataTable(userDataTable);
		}
		PlayerPrefsUtils.ForceSave();
		value?.ReloadUserData();
	}

	public void afterReplaceArchive()
	{
		if (registerStatus != RegisterStatus.Register)
		{
			if (_baseData.status == 1)
			{
				endSwitchAccount();
			}
			status = LoginStatus.LoginPrepare;
		}
		DataHandlerUtils.SetHashValue(GameBaseUserData.userId);
	}

	public void addSyncCount(string module)
	{
		if (uploadArchiveModules.Contains(module))
		{
			archiveChanged = true;
		}
		_syncCacheCount[module] = 1;
	}

	private void retryGetArchive(object param)
	{
		if (archiveStatus == ArchiveStatus.GetFailed)
		{
			archiveStatus = ArchiveStatus.Get;
		}
	}

	private void retryFullArchive(object param)
	{
		if (archiveStatus == ArchiveStatus.FullArchiveFailed)
		{
			archiveStatus = ArchiveStatus.FullArchive;
		}
	}

	private Dictionary<string, string> fullArchiveData()
	{
		_fullArchiveData.Clear();
		foreach (KeyValuePair<string, IDataHandler> moduleData in ConfigHandler<ModuleManager>.Instance.moduleDatas)
		{
			string userDataTable = moduleData.Value.GetUserDataTable();
			if (userDataTable != null)
			{
				_fullArchiveData[moduleData.Key] = userDataTable;
			}
		}
		return _fullArchiveData;
	}

	private Dictionary<string, string> deltaArchiveData()
	{
		_archiveData.Clear();
		_syncingCacheCount.Clear();
		DictionaryConvert.addEntriesFromDictionary<string, int>(_syncingCacheCount, _syncCacheCount);
		_syncCacheCount.Clear();
		foreach (KeyValuePair<string, int> item in _syncingCacheCount)
		{
			string userDataTable = ConfigHandler<ModuleManager>.Instance.moduleDatas[item.Key].GetUserDataTable();
			if (userDataTable != null)
			{
				_archiveData[item.Key] = userDataTable;
			}
		}
		return _archiveData;
	}

	public void syncLocalArchive()
	{
		if (registerStatus != RegisterStatus.Register)
		{
			endSwitchAccount();
			status = LoginStatus.LoginPrepare;
			status = LoginStatus.Login;
		}
	}

	public void syncArchive(Action<bool> callback)
	{
		_resultCallback = callback;
		if (status != LoginStatus.LoginSuccess)
		{
			SafeResultCallback();
		}
		else
		{
			archiveStatus = ArchiveStatus.FullArchive;
		}
	}

	private void SafeResultCallback()
	{
		try
		{
			if (_resultCallback != null)
			{
				_resultCallback(archiveStatus == ArchiveStatus.DeltaArchive);
				_resultCallback = null;
			}
		}
		catch (Exception ex)
		{
			Debug.LogWarning((object)("ArchiveCallback: error > " + ex.Message));
		}
	}

	public void syncDeltaArchive()
	{
		if (archiveStatus == ArchiveStatus.DeltaArchive && archiveChanged)
		{
			Debug.Log((object)"try syncDeltaArchive");
			Dictionary<string, string> dictionary = deltaArchiveData();
			if (dictionary.Count != 0)
			{
				Debug.Log((object)"begin syncDeltaArchive");
				archivePatchObject.request(dictionary);
			}
		}
	}

	private void initLogin()
	{
		userToken = string.Empty;
		loginGetObject = new LoginGetHttpObject();
		loginObject = new LoginHttpObject();
		loginObject.retryCount = 3;
		loginGetObject.initialize();
		loginObject.initialize();
	}

	private void startLogin()
	{
		DataHandler<RequestDataHandler>.Instance.onUpdateConfigEvent += Instance_OnUpdateConfigEvent;
		loginGetObject.requestSuccEvent += LoginGetObject_RequestSuccEvent;
		loginGetObject.requestFailEvent += LoginGetObject_RequestFailEvent;
		loginObject.requestSuccEvent += LoginObject_RequestSuccEvent;
		loginObject.requestFailEvent += LoginObject_RequestFailEvent;
	}

	private void stopLogin()
	{
		DataHandler<RequestDataHandler>.Instance.onUpdateConfigEvent -= Instance_OnUpdateConfigEvent;
		loginGetObject.requestSuccEvent -= LoginGetObject_RequestSuccEvent;
		loginGetObject.requestFailEvent -= LoginGetObject_RequestFailEvent;
		loginObject.requestSuccEvent -= LoginObject_RequestSuccEvent;
		loginObject.requestFailEvent -= LoginObject_RequestFailEvent;
		this.loginEndEvent = null;
		this.accountLoginOfflineEvent = null;
		this.accountForceOfflineEvent = null;
	}

	private void Instance_OnUpdateConfigEvent()
	{
		//IL_0030: Unknown result type (might be due to invalid IL or missing references)
		//IL_003a: Expected O, but got Unknown
		//IL_005c: Unknown result type (might be due to invalid IL or missing references)
		//IL_0066: Expected O, but got Unknown
		if (_status == LoginStatus.LoginSuccess && _baseData.status != 1 && !string.IsNullOrEmpty(accountId))
		{
			SingletonData<TimerManager>.Instance.Remove(new TimerCallback(checkLoginStatus));
			SingletonData<TimerManager>.Instance.Add(DataHandler<RequestDataHandler>.Instance.configRoot.interval.getLogin, 0, new TimerCallback(checkLoginStatus));
		}
	}

	private void retryLogin(object param = null)
	{
		if (status == LoginStatus.LoginFailed)
		{
			status = LoginStatus.Login;
		}
	}

	private void requestServerLogin()
	{
		if (registerStatus == RegisterStatus.Register)
		{
			loginObject.requestWithUserId(null, accountData.accountId);
		}
		else if (_baseData.status == 1)
		{
			loginObject.requestWithUserId(null);
		}
		else
		{
			loginObject.request();
		}
	}

	private void checkLoginStatus(object param = null)
	{
		loginGetObject.request();
	}

	private void LoginObject_RequestFailEvent(HttpObject<LoginHttpResultObject> obj)
	{
		Debug.Log((object)("LoginObject_RequestFailEvent, status: " + obj.resultObj.status + " userId: " + _baseData.userId + " accountId: " + accountId + " deviceId: " + deviceId));
		if (status == LoginStatus.Login)
		{
			if (obj.resultObj.status == 80003)
			{
				Debug.LogError((object)("设备到达上限，设备id: " + deviceId));
				status = LoginStatus.ForceOffline;
			}
			else
			{
				status = LoginStatus.LoginFailed;
			}
			if (registerStatus == RegisterStatus.Register)
			{
				register(switchAcct: true);
			}
			triggerForceLoginCallback();
		}
	}

	private void LoginObject_RequestSuccEvent(HttpObject<LoginHttpResultObject> obj)
	{
		Debug.Log((object)("LoginObject_RequestSuccEvent, userId: " + _baseData.userId + " accountId: " + accountId + " deviceId: " + deviceId));
		if (status == LoginStatus.Login)
		{
			userToken = obj.resultObj.userToken;
			status = LoginStatus.LoginSuccess;
			triggerForceLoginCallback();
		}
	}

	private void LoginGetObject_RequestFailEvent(HttpObject<LoginGetHttpResultObject> obj)
	{
		if (status == LoginStatus.LoginSuccess)
		{
			if (obj.resultObj.status == 80001)
			{
				status = LoginStatus.Offline;
				this.accountLoginOfflineEvent?.Invoke();
			}
			else if (obj.resultObj.status == 80002)
			{
				status = LoginStatus.Offline;
				requestServerLogin();
			}
		}
	}

	private void LoginGetObject_RequestSuccEvent(HttpObject<LoginGetHttpResultObject> obj)
	{
	}

	private void initPush()
	{
	}

	private void startPush()
	{
		LGSDKManager.OnDidFinishStartEvent += PushSDKManager_OnDidFinishStartEvent;
	}

	private void stopPush()
	{
		LGSDKManager.OnDidFinishStartEvent -= PushSDKManager_OnDidFinishStartEvent;
	}

	private void PushSDKManager_OnDidFinishStartEvent(string obj)
	{
		updatePushId(obj);
	}

	private void updatePushId(string pushId)
	{
		if (!string.IsNullOrEmpty(pushId) && _baseData != null)
		{
			_baseData.updatePushId(pushId);
			SaveCacheData();
		}
	}

	private void register(bool switchAcct = false)
	{
		registerOpen = true;
		ConfigHandler<ModuleManager>.Instance.DropDB();
		ConfigHandler<ModuleManager>.Instance.ReloadGameData();
		_baseData.reloadDataByRegister();
		if (!switchAcct && accountData != null && accountId != accountData.accountId)
		{
			Debug.Log((object)("save account info: " + accountData.accountId + " accountName: " + accountData.accountName));
			_baseData.updateAccount(accountData.accountId, accountData.accountName, accountType, accountData.subType, accountData.isRealNameVerified);
		}
		if (!CommonConfig.IsProduction())
		{
			Common.Log("register registerData " + JsonParse.SerializeObject((object)_baseData.cacheData));
		}
		SaveCacheData();
		registerEnd();
	}

	private void registerEnd()
	{
		Debug.Log((object)"game registerEnd, call login");
		registerStatus = RegisterStatus.Over;
		status = LoginStatus.LoginPrepare;
		status = LoginStatus.Login;
		afterLoginEnd();
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
