using FairyGUI;
using LoadingUI;
using UnityEngine;

public class LoadingController : MonoBehaviour
{
	private void Awake()
	{
	}

	private void Start()
	{
		//IL_002b: Unknown result type (might be due to invalid IL or missing references)
		//IL_0030: Unknown result type (might be due to invalid IL or missing references)
		Application.targetFrameRate = 60;
		SingletonData<PopupManager>.Instance.clearStartOption();
		SettingManager.SetDefaultFont();
		UIConfig.modalLayerColor = new Color(0f, 0f, 0f, 1f);
		UIConfig.touchScrollSensitivity = 80;
		UIConfig.touchDragSensitivity = 0;
		InitLoading();
	}

	private void InitLoading()
	{
		Common.PropertyTestLog("Profile LoadingController InitLoading ");
		LoadingUIBinder.BindAll();
		((Window)new LoadingWindow()).Show();
		Common.PropertyTestLog("Profile LoadingController ShowWindow LoadingWindow ");
	}

	private void OnDestroy()
	{
		destroyTrashMan();
	}

	private void destroyTrashMan()
	{
		if (!((Object)(object)TrashMan.instance == (Object)null))
		{
			Object.Destroy((Object)(object)((Component)TrashMan.instance).gameObject);
			TrashMan.instance = null;
		}
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
