using FairyGUI;
using UnityEngine;

public class AccountObject
{
	public AccountStatus status { get; protected set; }

	public bool isLogin { get; protected set; }

	public string token { get; protected set; }

	public virtual bool isGuest => false;

	public event OnLoginSuccessAction loginSuccessEvent;

	public event OnLoginFailed loginFailedEvent;

	public event OnCustomAction alreadyBindEvent;

	public event OnCustomAction loginCancelEvent;

	public virtual void start()
	{
		//IL_0026: Unknown result type (might be due to invalid IL or missing references)
		//IL_0030: Expected O, but got Unknown
		token = string.Empty;
		isLogin = false;
		status = AccountStatus.None;
		SingletonData<TimerManager>.Instance.AddUpdate(new TimerCallback(update));
	}

	public virtual void stop()
	{
		//IL_000d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0017: Expected O, but got Unknown
		//IL_0024: Unknown result type (might be due to invalid IL or missing references)
		//IL_002e: Expected O, but got Unknown
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(update));
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(timeout));
		status = AccountStatus.None;
	}

	protected virtual void timeout(object param)
	{
		loginFailed(-1);
	}

	public virtual void guestLogin()
	{
		//IL_0013: Unknown result type (might be due to invalid IL or missing references)
		//IL_001d: Expected O, but got Unknown
		SingletonData<TimerManager>.Instance.Add(30f, 1, new TimerCallback(timeout));
	}

	public virtual void switchAccount()
	{
	}

	public virtual void bindAccount()
	{
	}

	protected virtual void update(object param)
	{
	}

	public void loginSuccess(string accountId, string accountName, int loginType, bool isRealNameVerified)
	{
		//IL_000d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0017: Expected O, but got Unknown
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(timeout));
		Debug.Log((object)("loginSuccess: accountId: " + accountId + " accountName: " + accountName + " loginType: " + loginType + " isRealNameVerified: " + isRealNameVerified.ToString()));
		this.loginSuccessEvent?.Invoke(accountId, accountName, loginType, isRealNameVerified);
	}

	public void loginFailed(int errno)
	{
		//IL_000d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0017: Expected O, but got Unknown
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(timeout));
		Debug.Log((object)("loginFailed: " + errno));
		this.loginFailedEvent?.Invoke(errno);
	}

	public void accountAlreadyBind()
	{
		//IL_000d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0017: Expected O, but got Unknown
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(timeout));
		Debug.Log((object)"accountAlreadyBind");
		this.alreadyBindEvent?.Invoke();
	}

	public void loginCancel()
	{
		//IL_000d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0017: Expected O, but got Unknown
		SingletonData<TimerManager>.Instance.Remove(new TimerCallback(timeout));
		Debug.Log((object)"loginCancel");
		this.loginCancelEvent?.Invoke();
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
