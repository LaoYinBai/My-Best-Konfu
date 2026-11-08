using BestHTTP;

public class LoginHttpObject : HttpObject<LoginHttpResultObject>
{
	public override string url => base.headUrl + "login/user";

	public override string tag => "postLogin";

	public override HTTPMethods httpMethods => HTTPMethods.Post;

	public override string[] getTokenFields()
	{
		return new string[7]
		{
			DictionaryConvert.stringValue<string, object>(base.baseInfo, "appid", string.Empty),
			DictionaryConvert.stringValue<string, object>(base.baseInfo, "device_id", string.Empty),
			DictionaryConvert.stringValue<string, object>(base.baseInfo, "os", string.Empty),
			DictionaryConvert.stringValue<string, object>(base.baseInfo, "account_id", string.Empty),
			DictionaryConvert.stringValue<string, object>(base.baseInfo, "account_type", string.Empty),
			DictionaryConvert.stringValue<string, object>(base.baseInfo, "version", string.Empty),
			DictionaryConvert.stringValue<string, object>(base.baseInfo, "userid", string.Empty)
		};
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
