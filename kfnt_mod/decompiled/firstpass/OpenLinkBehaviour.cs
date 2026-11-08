using UnityEngine;
using UnityEngine.Events;
using UnityEngine.UI;

public class OpenLinkBehaviour : MonoBehaviour
{
	public string Url;

	private void Start()
	{
		//IL_0012: Unknown result type (might be due to invalid IL or missing references)
		//IL_001c: Expected O, but got Unknown
		((UnityEvent)((Component)this).GetComponent<Button>().onClick).AddListener(new UnityAction(Open));
	}

	public void Open()
	{
		Application.OpenURL(Url);
	}
}
