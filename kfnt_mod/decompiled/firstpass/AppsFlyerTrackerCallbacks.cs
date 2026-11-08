using UnityEngine;

public class AppsFlyerTrackerCallbacks : MonoBehaviour
{
	private unsafe void Start()
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	private void Update()
	{
	}

	public unsafe void didReceiveConversionData(string conversionData)
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public unsafe void didReceiveConversionDataWithError(string error)
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public unsafe void didFinishValidateReceipt(string validateResult)
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public unsafe void didFinishValidateReceiptWithError(string error)
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public unsafe void onAppOpenAttribution(string validateResult)
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public unsafe void onAppOpenAttributionFailure(string error)
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public unsafe void onInAppBillingSuccess()
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public unsafe void onInAppBillingFailure(string error)
	{
		_ = *(uint*)(nint)/*Error near IL_0001: Stack underflow*/;
		/*Error near IL_0001: Invalid metadata token for ldtoken instruction.*/;
	}

	public void onInviteLinkGenerated(string link)
	{
		printCallback("AppsFlyerTrackerCallbacks:: generated userInviteLink " + link);
	}

	private void printCallback(string str)
	{
		Debug.Log((object)str);
	}
}
