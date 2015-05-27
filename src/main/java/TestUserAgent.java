import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;


public class TestUserAgent {
	public static void main(String[] args)
	{
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2342.2 Safari/537.36");
		System.out.println(agent.getName() + " | " + agent.getOperatingSystem().getName() + " | " + agent.getDeviceCategory().getName());
		
	}
}
