using System;
using System.Collections.Generic;
using ByteDance.Union;
using UnityEngine;

public class AccountInfoManager : SingletonData<AccountInfoManager>
{
	private Dictionary<string, object> _accountData;

	private bool needRefreshAccount;

	public Dictionary<string, object> accountData
	{
		get
		{
			checkRefreshAccountData();
			return _accountData;
		}
	}

	public string appVersion => DictionaryConvert.stringValue<string, object>(accountData, "app_version", (string)null);

	public string channel => DictionaryConvert.stringValue<string, object>(accountData, "channel", (string)null);

	public string identifyCode => DictionaryConvert.stringValue<string, object>(accountData, "identify_code", (string)null);

	public string phoneNumber => DictionaryConvert.stringValue<string, object>(accountData, "mobile", (string)null);

	public string realName => DictionaryConvert.stringValue<string, object>(accountData, "identify_name", (string)null);

	public event Action accountInfoChangeEvent;

	protected override void OnInit()
	{
		needRefreshAccount = true;
	}

	private void checkRefreshAccountData()
	{
		if (needRefreshAccount)
		{
			Singeton<LGAccountService>.Instance.QueryAccountInfo((Action<Dictionary<string, object>>)onQueryAccountInfoSucc, (Action<int, string>)onQueryAccountInfoFail);
		}
	}

	private void onQueryAccountInfoSucc(Dictionary<string, object> info)
	{
		string text = JsonParse.SerializeObject((object)info);
		Debug.LogWarning((object)("查询账号信息成功\n" + text));
		_accountData = info;
		needRefreshAccount = false;
		this.accountInfoChangeEvent?.Invoke();
	}

	private void onQueryAccountInfoFail(int errorCode, string message)
	{
		Debug.LogWarning((object)$"查询账号信息失败:\nerrorCode:{errorCode} \nmessage:{message}");
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
