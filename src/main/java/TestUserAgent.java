import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;


public class TestUserAgent {
	public static void main(String[] args)
	{
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse("Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; GT-I9500 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.0 QQ-Manager Mobile Safari/537.36");
		System.out.println(agent.getName() + " | " + agent.getOperatingSystem().getName() + " | " + agent.getDeviceCategory().getName());
		
	}
}
