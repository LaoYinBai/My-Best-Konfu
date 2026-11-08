using ByteDance.Union;
using UnityEngine;

public class DouyinAccountObject : AccountObject
{
	public override bool isGuest => LGSDKManager.Instance.IsVisitor();

	public int errno { get; private set; }

	public LGUser user { get; private set; }

	public override void start()
	{
		base.start();
		LGSDKManager.OnAccountLoginSuccess += LGSDKManager_OnAccountLoginSuccess;
		LGSDKManager.OnAccountLoginFailed += LGSDKManager_OnAccountLoginFailed;
	}

	public override void stop()
	{
		LGSDKManager.OnAccountLoginSuccess -= LGSDKManager_OnAccountLoginSuccess;
		LGSDKManager.OnAccountLoginFailed -= LGSDKManager_OnAccountLoginFailed;
	}

	protected override void timeout(object param)
	{
		base.timeout(param);
		base.status = AccountStatus.None;
	}

	protected override void update(object param)
	{
		//IL_002d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0042: Expected I4, but got Unknown
		base.update(param);
		if (base.status == AccountStatus.LoginSuccess)
		{
			loginSuccess(user.OpenID, user.NickName, (int)user.LoginType, user.IsRealNameVerified);
			user = null;
			base.status = AccountStatus.None;
		}
		else if (base.status == AccountStatus.LoginFailed)
		{
			if (errno == -1004)
			{
				loginCancel();
			}
			else if (errno == 20002)
			{
				accountAlreadyBind();
			}
			else
			{
				loginFailed(errno);
			}
			base.status = AccountStatus.None;
		}
	}

	public override void guestLogin()
	{
		if (base.status != AccountStatus.LoginRequest)
		{
			base.status = AccountStatus.LoginRequest;
			LGSDKManager.Instance.NormalLogin();
			base.guestLogin();
		}
	}

	public override void switchAccount()
	{
		if (base.status != AccountStatus.LoginRequest)
		{
			base.status = AccountStatus.LoginRequest;
			LGSDKManager.Instance.SwitchAccount();
			base.switchAccount();
		}
	}

	public override void bindAccount()
	{
		if (base.status != AccountStatus.LoginRequest)
		{
			base.status = AccountStatus.LoginRequest;
			LGSDKManager.Instance.VisitorAccountBind();
			base.bindAccount();
		}
	}

	private void LGSDKManager_OnAccountLoginFailed(int code, string msg)
	{
		Debug.Log((object)("LGSDKManager_OnAccountLoginFailed, errno: " + code + " errmsg: " + msg));
		errno = code;
		base.status = AccountStatus.LoginFailed;
		base.isLogin = false;
	}

	private void LGSDKManager_OnAccountLoginSuccess(LGUser obj)
	{
		Debug.Log((object)("LGSDKManager_OnAccountLoginSuccess, openId: " + obj.OpenID + " nickName: " + obj.NickName));
		user = obj;
		base.token = user.Token;
		base.status = AccountStatus.LoginSuccess;
		base.isLogin = obj.IsLogin;
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
