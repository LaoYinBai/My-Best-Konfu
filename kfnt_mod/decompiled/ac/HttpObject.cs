using System;
using System.Collections.Generic;
using System.Text;
using BestHTTP;
using BestHTTP.Forms;
using UnityEngine;

public class HttpObject<T> where T : HttpResultObject, new()
{
	public string tokenSecert = "75a4eca0427dfae64cd0e5bbfe95005d";

	public int retryCount;

	public int curRetryCount;

	public bool active;

	public virtual string url => "";

	public string headUrl { get; protected set; }

	public string urlString { get; protected set; }

	public virtual string tag => "";

	public virtual bool isQuery => true;

	public virtual HTTPMethods httpMethods => HTTPMethods.Post;

	public virtual HTTPFormUsage httpFormUsage => HTTPFormUsage.Automatic;

	public int customId => 1080001;

	public int appId => 10001;

	public virtual int timeOut => 60;

	public virtual int connectTimeOut => 20;

	public virtual bool supportUserToken => false;

	public Dictionary<string, object> postDatas { get; private set; }

	public Dictionary<string, object> baseInfo { get; private set; }

	public Dictionary<string, object> query { get; private set; }

	public T resultObj { get; private set; }

	public string warnResultString { get; protected set; }

	public string responseText { get; protected set; }

	public string userId { get; private set; }

	public event OnHttpObjectAction<HttpObject<T>> requestSuccEvent;

	public event OnHttpObjectAction<HttpObject<T>> requestFailEvent;

	public virtual void initialize(bool sandbox = false)
	{
		headUrl = ((CommonConfig.IsProduction() && !sandbox) ? "https://u.ohayoo.cn/uyun/api/" : "https://testu.ohayoo.cn/uyun/api/");
		resultObj = new T();
		resultObj.initialize();
		active = true;
		warnResultString = string.Empty;
		responseText = string.Empty;
		urlString = url;
		postDatas = new Dictionary<string, object>();
		initializeInfo();
	}

	protected virtual void initializeInfo()
	{
		if (httpMethods == HTTPMethods.Post || httpMethods == HTTPMethods.Put || httpMethods == HTTPMethods.Patch)
		{
			baseInfo = new Dictionary<string, object>();
			postDatas["baseInfo"] = baseInfo;
			if (isQuery)
			{
				query = new Dictionary<string, object>();
				postDatas["query"] = query;
			}
		}
		else
		{
			_ = httpMethods;
		}
		Debug.LogWarning((object)GetType().Name);
	}

	public virtual void requestInfo(string userId, string accId = null)
	{
		this.userId = userId;
		string text = accId ?? DataHandler<UserBaseDataHandler>.Instance.accountId;
		if (httpMethods == HTTPMethods.Post || httpMethods == HTTPMethods.Put || httpMethods == HTTPMethods.Patch)
		{
			baseInfo.Clear();
			baseInfo["customer_id"] = customId;
			baseInfo["appid"] = appId;
			baseInfo["device_id"] = DataHandler<UserBaseDataHandler>.Instance.deviceId;
			baseInfo["imei"] = DataHandler<UserBaseDataHandler>.Instance.imei;
			baseInfo["os"] = "android";
			baseInfo["version"] = Application.version;
			if (!string.IsNullOrEmpty(userId))
			{
				baseInfo["userid"] = userId;
			}
			if (!string.IsNullOrEmpty(text))
			{
				baseInfo["account_id"] = text;
				baseInfo["account_type"] = DataHandler<UserBaseDataHandler>.Instance.accountType;
			}
		}
		else if (httpMethods == HTTPMethods.Get)
		{
			urlString = url;
			urlString += "?";
			urlString += $"customer_id={customId}";
			urlString += $"&appid={appId}";
			urlString += $"&device_id={DataHandler<UserBaseDataHandler>.Instance.deviceId}";
			urlString += $"&imei={DataHandler<UserBaseDataHandler>.Instance.imei}";
			urlString += string.Format("&os={0}", "android");
			urlString += $"&version={Application.version}";
			if (!string.IsNullOrEmpty(userId))
			{
				urlString += $"&userid={userId}";
			}
			if (!string.IsNullOrEmpty(text))
			{
				urlString += $"&account_id={text}";
				urlString += $"&account_type={DataHandler<UserBaseDataHandler>.Instance.accountType}";
			}
		}
	}

	public virtual void request(string accId = null)
	{
		requestWithUserId(DataHandler<UserBaseDataHandler>.Instance.GameBaseUserData.userId, accId);
	}

	public void requestWithUserId(string userId, string accId = null)
	{
		curRetryCount = 0;
		resultObj.initialize();
		requestInfo(userId, accId);
		genToken();
		post();
	}

	public virtual string[] getTokenFields()
	{
		return null;
	}

	public virtual void genToken()
	{
		string[] tokenFields = getTokenFields();
		string value = genTokenBySecret(tokenFields, tokenSecert);
		if (!string.IsNullOrEmpty(value))
		{
			baseInfo["token"] = value;
		}
	}

	public string genTokenBySecret(string[] fields, string secert)
	{
		if (fields == null || fields.Length == 0)
		{
			return null;
		}
		if (secert.Length < fields.Length * 4)
		{
			Debug.LogError((object)("len of secert is too short: " + secert.Length + " fields.Length: " + fields.Length));
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.Append(secert.Substring(0, 2));
		for (int i = 0; i < fields.Length; i++)
		{
			stringBuilder.Append(fields[i] ?? string.Empty);
			if (i == fields.Length - 1)
			{
				stringBuilder.Append(secert.Substring(i * 4 + 2, 2));
				break;
			}
			stringBuilder.Append(secert.Substring(i * 4 + 2, 4));
		}
		return StringConvert.MD5(stringBuilder.ToString());
	}

	protected virtual void post()
	{
		bool num = !string.IsNullOrEmpty(DataHandler<UserBaseDataHandler>.Instance.userToken) && supportUserToken;
		Uri uri = new Uri(urlString);
		Debug.Log((object)("post:" + uri));
		HTTPRequest hTTPRequest = new HTTPRequest(uri, httpMethods, onPostFinished);
		hTTPRequest.FormUsage = httpFormUsage;
		hTTPRequest.RedirectCount = 3;
		hTTPRequest.AddHeader("Authorization", "Bearer a1a0f45047fa00da4df39e85d735a67e");
		hTTPRequest.AddHeader("Content-Type", "application/json");
		if (num)
		{
			hTTPRequest.AddHeader("UserToken", DataHandler<UserBaseDataHandler>.Instance.userToken);
		}
		if (httpMethods != 0)
		{
			string text = JsonParse.SerializeObject((object)postDatas);
			Debug.Log((object)(tag + " post param: " + text));
			byte[] bytes = Encoding.UTF8.GetBytes(text);
			hTTPRequest.RawData = bytes;
		}
		hTTPRequest.Tag = tag;
		hTTPRequest.RedirectCount = 3;
		hTTPRequest.ConnectTimeout = TimeSpan.FromSeconds(connectTimeOut);
		hTTPRequest.Timeout = TimeSpan.FromSeconds(timeOut);
		hTTPRequest.Send();
	}

	public virtual void onPostFinished(HTTPRequest request, HTTPResponse response)
	{
		if (response != null && response.StatusCode == 200)
		{
			string text = (responseText = response.DataAsText);
			Debug.Log((object)(tag + "onPostFinished.text:" + text));
			if (text == null || text.Length == 0)
			{
				warnResultString = tag + ".onPostFinished: text is null";
				Debug.LogWarning((object)warnResultString);
				finishFail();
				return;
			}
			Dictionary<string, object> dictionary = JsonParse.DeserializeObject<Dictionary<string, object>>(text);
			if (dictionary == null)
			{
				warnResultString = tag + "DeserializeObject text failed:" + text;
				Debug.LogWarning((object)warnResultString);
				finishFail();
				return;
			}
			resultObj.reload(dictionary);
			verifyResult();
			if (resultObj.status == 0)
			{
				finishSucc();
				return;
			}
			warnResultString = tag + ".resultObj.StatusCode:" + resultObj.status;
			Debug.LogWarning((object)warnResultString);
			finishFail();
		}
		else
		{
			if (response != null)
			{
				warnResultString = tag + ".response.StatusCode:" + response.StatusCode;
				Debug.LogWarning((object)warnResultString);
			}
			finishFail();
		}
	}

	public virtual string[] getResultTokenFields()
	{
		return null;
	}

	protected virtual void verifyResult()
	{
		if (resultObj.status == 0)
		{
			string[] resultTokenFields = getResultTokenFields();
			string text = genTokenBySecret(resultTokenFields, tokenSecert);
			if (text != null && (resultObj.token == null || resultObj.token != text))
			{
				resultObj.markAsError();
			}
		}
	}

	protected virtual void finishSucc()
	{
		this.requestSuccEvent?.Invoke(this);
	}

	protected virtual void finishFail()
	{
		if (resultObj.hasReload || curRetryCount >= retryCount)
		{
			this.requestFailEvent?.Invoke(this);
			curRetryCount = 0;
		}
		else
		{
			curRetryCount++;
			post();
		}
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
