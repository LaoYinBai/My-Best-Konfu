using System.Collections.Generic;
using UnityEngine;

public class BuglyInit : MonoBehaviour
{
	private const string BuglyAppID = "YOUR APP ID GOES HERE";

	private void Awake()
	{
		BuglyAgent.ConfigDebugMode(enable: false);
		BuglyAgent.ConfigDefault(null, null, null, 0L);
		BuglyAgent.ConfigAutoReportLogLevel(LogSeverity.LogError);
		BuglyAgent.ConfigAutoQuitApplication(autoQuit: false);
		BuglyAgent.RegisterLogCallback(null);
		BuglyAgent.InitWithAppId("YOUR APP ID GOES HERE");
		BuglyAgent.EnableExceptionHandler();
		BuglyAgent.SetLogCallbackExtrasHandler(MyLogCallbackExtrasHandler);
		Object.Destroy((Object)(object)this);
	}

	private static Dictionary<string, string> MyLogCallbackExtrasHandler()
	{
		//IL_0064: Unknown result type (might be due to invalid IL or missing references)
		//IL_0069: Unknown result type (might be due to invalid IL or missing references)
		BuglyAgent.PrintLog(LogSeverity.Log, "extra handler");
		Dictionary<string, string> obj = new Dictionary<string, string>
		{
			{
				"ScreenSolution",
				$"{Screen.width}x{Screen.height}"
			},
			{
				"deviceModel",
				SystemInfo.deviceModel
			},
			{
				"deviceName",
				SystemInfo.deviceName
			}
		};
		DeviceType deviceType = SystemInfo.deviceType;
		obj.Add("deviceType", ((object)(DeviceType)(ref deviceType)).ToString());
		obj.Add("deviceUId", SystemInfo.deviceUniqueIdentifier);
		obj.Add("gDId", $"{SystemInfo.graphicsDeviceID}");
		obj.Add("gDName", SystemInfo.graphicsDeviceName);
		obj.Add("gDVdr", SystemInfo.graphicsDeviceVendor);
		obj.Add("gDVer", SystemInfo.graphicsDeviceVersion);
		obj.Add("gDVdrID", $"{SystemInfo.graphicsDeviceVendorID}");
		obj.Add("graphicsMemorySize", $"{SystemInfo.graphicsMemorySize}");
		obj.Add("systemMemorySize", $"{SystemInfo.systemMemorySize}");
		obj.Add("UnityVersion", Application.unityVersion);
		BuglyAgent.PrintLog(LogSeverity.LogInfo, "Package extra data");
		return obj;
	}
}
