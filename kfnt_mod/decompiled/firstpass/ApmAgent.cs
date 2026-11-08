using System;
using System.Diagnostics;
using System.Reflection;
using System.Text;
using System.Text.RegularExpressions;
using ByteDance.Union;
using UnityEngine;

public sealed class ApmAgent
{
	public delegate void LogCallbackDelegate(string condition, string stackTrace, LogType type);

	private static bool _isInitialized;

	private static bool _uncaughtAutoReportOnce;

	private static bool _autoQuitApplicationAfterReport;

	public static bool IsInitialized => _isInitialized;

	private static bool AutoQuitApplicationAfterReport => _autoQuitApplicationAfterReport;

	private static event LogCallbackDelegate _LogCallbackEventHandler;

	public static void Init()
	{
		if (IsInitialized)
		{
			Log.D("ApmAgent has already been initialized.");
		}
		else
		{
			_RegisterExceptionHandler();
		}
	}

	private static void _RegisterExceptionHandler()
	{
		//IL_0007: Unknown result type (might be due to invalid IL or missing references)
		//IL_0011: Expected O, but got Unknown
		//IL_0018: Unknown result type (might be due to invalid IL or missing references)
		//IL_0022: Expected O, but got Unknown
		try
		{
			Application.RegisterLogCallback(new LogCallback(_OnLogCallbackHandler));
			Application.RegisterLogCallbackThreaded(new LogCallback(_OnLogCallbackHandler));
			AppDomain.CurrentDomain.UnhandledException += _OnUncaughtExceptionHandler;
			_isInitialized = true;
			Log.D("Register the log callback in Unity " + Application.unityVersion);
		}
		catch
		{
		}
	}

	private static void _OnLogCallbackHandler(string condition, string stackTrace, LogType type)
	{
		//IL_000e: Unknown result type (might be due to invalid IL or missing references)
		//IL_001c: Unknown result type (might be due to invalid IL or missing references)
		//IL_001e: Invalid comparison between Unknown and I4
		//IL_0029: Unknown result type (might be due to invalid IL or missing references)
		if (ApmAgent._LogCallbackEventHandler != null)
		{
			ApmAgent._LogCallbackEventHandler(condition, stackTrace, type);
		}
		if (IsInitialized && (int)type == 4 && !_uncaughtAutoReportOnce)
		{
			_HandleException(type, null, condition, stackTrace, uncaught: true);
		}
	}

	private static void _OnUncaughtExceptionHandler(object sender, UnhandledExceptionEventArgs eventArgs)
	{
		if (eventArgs == null || eventArgs.ExceptionObject == null)
		{
			return;
		}
		try
		{
			if (typeof(Exception) != eventArgs.ExceptionObject.GetType())
			{
				return;
			}
		}
		catch
		{
			return;
		}
		if (IsInitialized && !_uncaughtAutoReportOnce)
		{
			_HandleException((Exception)eventArgs.ExceptionObject, null, uncaught: true);
		}
	}

	private static void _HandleException(Exception e, string message, bool uncaught)
	{
		if (e == null || !IsInitialized)
		{
			return;
		}
		string name = e.GetType().Name;
		string text = e.Message;
		if (!string.IsNullOrEmpty(message))
		{
			text = $"{text}{Environment.NewLine}***{message}";
		}
		StringBuilder stringBuilder = new StringBuilder("");
		StackTrace stackTrace = new StackTrace(e, fNeedFileInfo: true);
		int frameCount = stackTrace.FrameCount;
		for (int i = 0; i < frameCount; i++)
		{
			StackFrame frame = stackTrace.GetFrame(i);
			stringBuilder.AppendFormat("{0}.{1}", frame.GetMethod().DeclaringType.Name, frame.GetMethod().Name);
			ParameterInfo[] parameters = frame.GetMethod().GetParameters();
			if (parameters == null || parameters.Length == 0)
			{
				stringBuilder.Append(" () ");
			}
			else
			{
				stringBuilder.Append(" (");
				int num = parameters.Length;
				ParameterInfo parameterInfo = null;
				for (int j = 0; j < num; j++)
				{
					parameterInfo = parameters[j];
					stringBuilder.AppendFormat("{0} {1}", parameterInfo.ParameterType.Name, parameterInfo.Name);
					if (j != num - 1)
					{
						stringBuilder.Append(", ");
					}
				}
				parameterInfo = null;
				stringBuilder.Append(") ");
			}
			string fileName = frame.GetFileName();
			if (!string.IsNullOrEmpty(fileName) && !fileName.ToLower().Equals("unknown"))
			{
				fileName = fileName.Replace("\\", "/");
				int num2 = fileName.ToLower().IndexOf("/assets/");
				if (num2 < 0)
				{
					num2 = fileName.ToLower().IndexOf("assets/");
				}
				if (num2 > 0)
				{
					fileName = fileName.Substring(num2);
				}
				stringBuilder.AppendFormat("(at {0}:{1})", fileName, frame.GetFileLineNumber());
			}
			stringBuilder.AppendLine();
		}
		_reportException(uncaught, name, text, stringBuilder.ToString());
	}

	private static void _HandleException(LogType logLevel, string name, string message, string stackTrace, bool uncaught)
	{
		//IL_0008: Unknown result type (might be due to invalid IL or missing references)
		//IL_000a: Invalid comparison between Unknown and I4
		//IL_000c: Unknown result type (might be due to invalid IL or missing references)
		//IL_000e: Invalid comparison between Unknown and I4
		//IL_0010: Unknown result type (might be due to invalid IL or missing references)
		//IL_0012: Invalid comparison between Unknown and I4
		//IL_0025: Unknown result type (might be due to invalid IL or missing references)
		//IL_0026: Invalid comparison between I4 and Unknown
		//IL_008e: Unknown result type (might be due to invalid IL or missing references)
		if (!IsInitialized || (int)logLevel == 3 || (int)logLevel == 2 || (int)logLevel == 1)
		{
			return;
		}
		string text = null;
		string text2 = null;
		if (!string.IsNullOrEmpty(message))
		{
			try
			{
				if (4 == (int)logLevel && message.Contains("Exception"))
				{
					Match match = new Regex("^(?<errorType>\\S+):\\s*(?<errorMessage>.*)", RegexOptions.Singleline).Match(message);
					if (match.Success)
					{
						text = match.Groups["errorType"].Value.Trim();
						text2 = match.Groups["errorMessage"].Value.Trim();
					}
				}
				else if ((int)logLevel == 0 && message.StartsWith("Unhandled Exception:"))
				{
					Match match2 = new Regex("^Unhandled\\s+Exception:\\s*(?<exceptionName>\\S+):\\s*(?<exceptionDetail>.*)", RegexOptions.Singleline).Match(message);
					if (match2.Success)
					{
						string text3 = match2.Groups["exceptionName"].Value.Trim();
						string text4 = match2.Groups["exceptionDetail"].Value.Trim();
						int num = text3.LastIndexOf(".");
						text = ((num <= 0 || num == text3.Length) ? text3 : text3.Substring(num + 1));
						int num2 = text4.IndexOf(" at ");
						if (num2 > 0)
						{
							text2 = text4.Substring(0, num2);
							string text5 = text4.Substring(num2 + 3).Replace(" at ", "\n").Replace("in <filename unknown>:0", "")
								.Replace("[0x00000]", "");
							stackTrace = $"{stackTrace}\n{text5.Trim()}";
						}
						else
						{
							text2 = text4;
						}
						if (text.Equals("LuaScriptException") && text4.Contains(".lua") && text4.Contains("stack traceback:"))
						{
							num2 = text4.IndexOf("stack traceback:");
							if (num2 > 0)
							{
								text2 = text4.Substring(0, num2);
								string text6 = text4.Substring(num2 + 16).Replace(" [", " \n[");
								stackTrace = $"{stackTrace}\n{text6.Trim()}";
							}
						}
					}
				}
			}
			catch
			{
			}
			if (string.IsNullOrEmpty(text2))
			{
				text2 = message;
			}
		}
		if (string.IsNullOrEmpty(name))
		{
			if (string.IsNullOrEmpty(text))
			{
				text = $"Unity{((object)(LogType)(ref logLevel)).ToString()}";
			}
		}
		else
		{
			text = name;
		}
		_reportException(uncaught, text, text2, stackTrace);
	}

	private static void _reportException(bool uncaught, string name, string reason, string stackTrace)
	{
		if (string.IsNullOrEmpty(name))
		{
			return;
		}
		if (string.IsNullOrEmpty(stackTrace))
		{
			stackTrace = StackTraceUtility.ExtractStackTrace();
		}
		if (string.IsNullOrEmpty(stackTrace))
		{
			stackTrace = "Empty";
		}
		else
		{
			try
			{
				string[] array = stackTrace.Split(new char[1] { '\n' });
				if (array != null && array.Length != 0)
				{
					StringBuilder stringBuilder = new StringBuilder();
					string text = null;
					int num = array.Length;
					for (int i = 0; i < num; i++)
					{
						text = array[i];
						if (string.IsNullOrEmpty(text) || string.IsNullOrEmpty(text.Trim()))
						{
							continue;
						}
						text = text.Trim();
						if (!text.StartsWith("System.Collections.Generic.") && !text.StartsWith("ShimEnumerator") && !text.Contains("..ctor"))
						{
							int num2 = text.ToLower().IndexOf("(at");
							int num3 = text.ToLower().IndexOf("/assets/");
							if (num2 > 0 && num3 > 0)
							{
								stringBuilder.AppendFormat("{0}(at {1}", text.Substring(0, num2).Replace(":", "."), text.Substring(num3));
							}
							else
							{
								stringBuilder.Append(text.Replace(":", "."));
							}
							stringBuilder.AppendLine();
						}
					}
					stackTrace = stringBuilder.ToString();
				}
			}
			catch
			{
			}
		}
		_uncaughtAutoReportOnce = uncaught && _autoQuitApplicationAfterReport;
		string stackTrace2 = name + "\n" + stackTrace;
		if (uncaught)
		{
			_ = _autoQuitApplicationAfterReport;
		}
		else
			_ = 0;
		Singeton<LGApmService>.Instance.ReportException(stackTrace2);
	}

	public static void RegisterLogCallback(LogCallbackDelegate handler)
	{
		if (handler != null)
		{
			_LogCallbackEventHandler += handler;
		}
	}

	public static void UnregisterLogCallback(LogCallbackDelegate handler)
	{
		if (handler != null)
		{
			_LogCallbackEventHandler -= handler;
		}
	}

	private static void AutoQuitApplication(bool autoQuit)
	{
		_autoQuitApplicationAfterReport = autoQuit;
	}
}
