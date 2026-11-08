using System.Collections.Generic;
using UnityEngine;

public class GameBaseUserData : UnitSimpleData<GameBaseUserConfig>
{
	public const string DefaultAvatarId = "1";

	public string userId { get; protected set; }

	public string avatar { get; protected set; }

	public int lastLoginTime { get; protected set; }

	public int updateTime { get; private set; }

	public int archiveTime { get; private set; }

	public string env { get; private set; }

	public string country { get; private set; }

	public string showName { get; private set; }

	public bool modifiedShowName { get; private set; }

	public double usedTime { get; private set; }

	public double testUsedTime { get; private set; }

	public int registerTime { get; private set; }

	public bool isRealNameVerified { get; private set; }

	public bool levelBindNoticed { get; private set; }

	public bool endlessBindNoticed { get; private set; }

	public int status { get; private set; }

	public string pushId { get; private set; }

	public UserBindData bindData { get; private set; }

	public string initShowName => string.Format(Common.LocalizedStringForKey("Á·ÎäÆæ²Å{0}"), IntConvert.toCCRandomIndex(999) + 9999);

	public bool hasInitData => !string.IsNullOrEmpty(userId);

	public bool hasBindAccount => bindData.hasBind;

	protected override void reloadDataByConfig()
	{
		base.reloadDataByConfig();
		userId = string.Empty;
		loadBindData(null, "bind", new UserBindConfig());
	}

	protected override void reloadDataByData(Dictionary<string, object> parameters)
	{
		base.reloadDataByData(parameters);
		userId = DictionaryConvert.stringValue<string, object>(parameters, "userId", string.Empty);
		pushId = DictionaryConvert.stringValue<string, object>(parameters, "pushId", string.Empty);
		avatar = DictionaryConvert.stringValue<string, object>(parameters, "avatar", "1");
		lastLoginTime = DictionaryConvert.intValue<string, object>(parameters, "lastLoginTime", NTPManger.Instance.time);
		updateTime = DictionaryConvert.intValue<string, object>(parameters, "updateTime", NTPManger.Instance.time);
		archiveTime = DictionaryConvert.intValue<string, object>(parameters, "archiveTime", 0);
		env = DictionaryConvert.stringValue<string, object>(parameters, "env", ((object)(ClientEnv)(ref CommonConfig.env)).ToString());
		country = DictionaryConvert.stringValue<string, object>(parameters, "country", (string)null);
		showName = DictionaryConvert.stringValue<string, object>(parameters, "showName", initShowName);
		modifiedShowName = DictionaryConvert.boolValue<string, object>(parameters, "modifiedShowName", false);
		usedTime = DictionaryConvert.doubleValue<string, object>(parameters, "usedTime", 0.0);
		string text = StringConvert.SHA1(CommonConfig.StatTestUsedTimeKey + Application.version);
		testUsedTime = DictionaryConvert.doubleValue<string, object>(parameters, text, 0.0);
		registerTime = DictionaryConvert.intValue<string, object>(parameters, "registerTime", 0);
		status = DictionaryConvert.intValue<string, object>(parameters, "status", 0);
		isRealNameVerified = DictionaryConvert.boolValue<string, object>(parameters, "isRealNameVerified", false);
		endlessBindNoticed = DictionaryConvert.boolValue<string, object>(parameters, "endlessBindNoticed", false);
		levelBindNoticed = DictionaryConvert.boolValue<string, object>(parameters, "levelBindNoticed", false);
		loadBindData(parameters, "bind", new UserBindConfig());
	}

	public void reloadDataByRegister()
	{
		reloadDataOverByRegister();
		updateValue();
	}

	public void reloadDataOverByRegister()
	{
		userId = generateUserId();
		pushId = LGSDKManager.Instance.getPushId();
		avatar = "1";
		lastLoginTime = NTPManger.Instance.time;
		updateTime = NTPManger.Instance.time;
		archiveTime = 0;
		env = ((object)(ClientEnv)(ref CommonConfig.env)).ToString();
		country = country;
		showName = initShowName;
		modifiedShowName = false;
		usedTime = 0.0;
		testUsedTime = 0.0;
		registerTime = NTPManger.Instance.time;
		status = 0;
		isRealNameVerified = false;
		endlessBindNoticed = false;
		levelBindNoticed = false;
	}

	protected override void updateValue()
	{
		base.updateValue();
		Debug.Log((object)("GameBaseUserData updateValue: " + userId));
	}

	public void login()
	{
		lastLoginTime = NTPManger.Instance.time;
	}

	public void updateAccount(string accountId, string accountName, string accountType, int subType, bool isRealNameVerified)
	{
		bindData.updateAccount(accountId, accountName, accountType, subType);
		this.isRealNameVerified = isRealNameVerified;
	}

	public void updateAvatar(int avatarId)
	{
		avatar = avatarId.ToString();
	}

	public void updateShowName(string showName)
	{
		this.showName = showName;
		modifiedShowName = true;
	}

	public void updateUsedTime(double time)
	{
		usedTime += time;
		testUsedTime += time;
	}

	public void updateSaveTime()
	{
		if (NTPManger.Instance.isEffective)
		{
			updateTime = NTPManger.Instance.time;
		}
	}

	public void updateArchiveTime()
	{
		archiveTime = NTPManger.Instance.time;
	}

	public void updateEndlessBind()
	{
		endlessBindNoticed = true;
	}

	public void updateLevelBind()
	{
		levelBindNoticed = true;
	}

	public void updatePushId(string pushId)
	{
		this.pushId = pushId;
	}

	private string generateUserId()
	{
		return StringConvert.MD5(Common.GenerateGlobalUniqueIdentifier() + NTPManger.Instance.time + IntConvert.toCCRandomIndex(999999));
	}

	private void loadBindData(Dictionary<string, object> parameters, string key, UserBindConfig cc)
	{
		Dictionary<string, object> parameters2 = DictionaryConvert.dictionaryValue<string, object>(parameters, key, (Dictionary<string, object>)null);
		bindData = new UserBindData();
		bindData.reloadData(parameters2, cc);
		cacheUserData(key);
	}

	public void beginSwitch()
	{
		status = 1;
	}

	public void endSwitch()
	{
		status = 0;
	}

	public void cacheUserData(string key)
	{
		cacheData[key] = bindData.toCacheData();
	}

	public override Dictionary<string, object> toCacheData()
	{
		cacheData["userId"] = userId;
		cacheData["pushId"] = pushId;
		cacheData["avatar"] = avatar;
		cacheData["lastLoginTime"] = lastLoginTime;
		cacheData["updateTime"] = updateTime;
		cacheData["archiveTime"] = archiveTime;
		cacheData["env"] = env;
		cacheData["country"] = country;
		cacheData["showName"] = showName;
		cacheData["modifiedShowName"] = modifiedShowName;
		cacheData["usedTime"] = usedTime;
		string key = StringConvert.SHA1(CommonConfig.StatTestUsedTimeKey + Application.version);
		cacheData[key] = testUsedTime;
		cacheData["registerTime"] = registerTime;
		cacheData["status"] = status;
		cacheData["isRealNameVerified"] = isRealNameVerified;
		cacheData["endlessBindNoticed"] = endlessBindNoticed;
		cacheData["levelBindNoticed"] = levelBindNoticed;
		bindData.toCacheData();
		return base.toCacheData();
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
