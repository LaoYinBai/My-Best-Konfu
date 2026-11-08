using FairyGUI;
using GameLib.NotificationCenter;
using LoadingUI;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LoadingWindow : Window
{
	public const string LoadingCloseWindowNotification = "LoadingCloseWindowNotification";

	private SceneName _sceneName;

	private BaseUISource _uiSource;

	private bool _canClose;

	private string _closeName;

	private bool _isBang;

	private LoadingNode _mainNode;

	public string PackageName { get; set; }

	public string ResourceName { get; set; }

	public LoadingWindow()
	{
		//IL_0008: Unknown result type (might be due to invalid IL or missing references)
		_sceneName = (SceneName)2;
		_uiSource = new BaseUISource(true, "LoadingUI", "CommonUI");
		((Window)this).AddUISource((IUISource)(object)_uiSource);
		PackageName = "LoadingUI";
		ResourceName = "LoadingNode";
	}

	public override void Dispose()
	{
		//IL_004b: Unknown result type (might be due to invalid IL or missing references)
		//IL_0055: Expected O, but got Unknown
		SceneManager.sceneLoaded -= OnSceneLoaded;
		SceneManager.sceneUnloaded -= OnSceneUnloaded;
		SceneManager.activeSceneChanged -= OnActiveSceneChanged;
		_uiSource.RemovePackage();
		((GObject)this).onRemovedFromStage.Remove(new EventCallback0(OnRemovedFromStageEvent));
		NotificationCenter.Default.RemoveRegister("LoadingCloseWindowNotification", this);
		((Window)this).Dispose();
	}

	protected override void OnInit()
	{
		//IL_0013: Unknown result type (might be due to invalid IL or missing references)
		//IL_001d: Expected O, but got Unknown
		//IL_00c3: Unknown result type (might be due to invalid IL or missing references)
		//IL_0077: Unknown result type (might be due to invalid IL or missing references)
		//IL_0086: Unknown result type (might be due to invalid IL or missing references)
		//IL_00ac: Unknown result type (might be due to invalid IL or missing references)
		((Window)this).OnInit();
		((GObject)this).onRemovedFromStage.Add(new EventCallback0(OnRemovedFromStageEvent));
		((GObject)GRoot.inst.modalLayer).alpha = 0.3f;
		((Window)this).modal = true;
		((Window)this).contentPane = UIPackage.CreateObject(PackageName, ResourceName).asCom;
		_isBang = Global.Instance.isBang;
		if (_isBang)
		{
			((GObject)((Window)this).contentPane).SetSize(((GObject)GRoot.inst).size.x, ((GObject)GRoot.inst).size.y - ScreenParams.IphoneXTopHeight - ScreenParams.IphoneXBottomHeight);
			((GObject)this).xy = new Vector2(0f, ScreenParams.IphoneXTopHeight);
		}
		else
		{
			((GObject)((Window)this).contentPane).size = ((GObject)GRoot.inst).size;
		}
		_mainNode = ((Window)this).contentPane as LoadingNode;
		SceneManager.sceneLoaded += OnSceneLoaded;
		SceneManager.sceneUnloaded += OnSceneUnloaded;
		SceneManager.activeSceneChanged += OnActiveSceneChanged;
		NotificationCenter.Default.Register("LoadingCloseWindowNotification", this, delegate
		{
			//IL_0017: Unknown result type (might be due to invalid IL or missing references)
			if (_canClose && _closeName != null && _closeName == ObjectConvert.toString((object)_sceneName, (string)null))
			{
				ClosePopupWindow();
			}
		});
	}

	public void OnRemovedFromStageEvent()
	{
	}

	private void ClosePopupWindow()
	{
		CloseAndDispose();
	}

	private void OnSceneLoaded(Scene scene, LoadSceneMode mode)
	{
		//IL_0007: Unknown result type (might be due to invalid IL or missing references)
		//IL_0015: Unknown result type (might be due to invalid IL or missing references)
		//IL_001e: Unknown result type (might be due to invalid IL or missing references)
		//IL_0032: Unknown result type (might be due to invalid IL or missing references)
		SceneName val = (SceneName)((Scene)(ref scene)).buildIndex;
		Debug.LogFormat("SceneLoaded buildIndex {0} mode {1}", new object[2] { val, mode });
		_canClose = true;
		_closeName = ObjectConvert.toString((object)val, (string)null);
	}

	private void OnSceneUnloaded(Scene scene)
	{
		//IL_0014: Unknown result type (might be due to invalid IL or missing references)
		object[] array = new object[1];
		SceneName val = (SceneName)((Scene)(ref scene)).buildIndex;
		array[0] = ((object)(SceneName)(ref val)).ToString();
		Debug.LogFormat("SceneUnloaded {0}", array);
	}

	private void OnActiveSceneChanged(Scene previousScene, Scene scene)
	{
		//IL_0007: Unknown result type (might be due to invalid IL or missing references)
		//IL_001c: Unknown result type (might be due to invalid IL or missing references)
		//IL_002d: Unknown result type (might be due to invalid IL or missing references)
		SceneName val = (SceneName)((Scene)(ref scene)).buildIndex;
		object[] array = new object[2];
		SceneName val2 = (SceneName)((Scene)(ref previousScene)).buildIndex;
		array[0] = ((object)(SceneName)(ref val2)).ToString();
		array[1] = val;
		Debug.LogFormat("ActiveSceneChanged from {0} to {1}", array);
	}

	protected override void DoShowAnimation()
	{
		((Window)this).OnShown();
	}

	protected override void DoHideAnimation()
	{
		CloseLoadingWindow();
	}

	private void CloseLoadingWindow()
	{
		Debug.Log((object)"CloseLoadingWindow");
		CloseAndDispose();
		SingletonData<PopupManager>.Instance.CloseLoadingWindow();
	}

	private void CloseAndDispose()
	{
		((GObject)this).root.HideWindowImmediately((Window)(object)this, true);
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
