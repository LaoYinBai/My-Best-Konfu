using System;

namespace ByteDance.Union;

public class LGSDKCore
{
	public const string SDK_VERSION = "2.3.3.3";

	public static LGAppLogService AppLogService => Singeton<LGAppLogService>.Instance;

	public static LGGameReportService GameReportService => Singeton<LGGameReportService>.Instance;

	public static LGDebugService DebugService => Singeton<LGDebugService>.Instance;

	[Obsolete("Use Init(Action<LGInitSuccessInfo> successCallBack, Action<int, string> failCallBack) replace", true)]
	public static void Init(Action<LGInitSuccessInfo> initCallback)
	{
		Init(initCallback, null);
	}

	public static void Init(Action<LGInitSuccessInfo> successCallBack, Action<int, string> failCallBack)
	{
		ReflectionHelper.InvokeStaticMethod("ByteDance.Union.LGSDKDevKit", "Init");
		ReflectionHelper.InvokeSingletonMethod("ByteDance.Union.LGAccountService", "Init");
		Singeton<LGInitService>.Instance.Init(successCallBack, failCallBack);
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
