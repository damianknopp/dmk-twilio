package dmk.twilio;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twilio.sdk.resource.instance.Call;

import dmk.twilio.PhoneCallService;
import dmk.twilio.sms.conf.TwilioTestConf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TwilioTestConf.class)
public class PhoneCallServiceImplTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected PhoneCallService service;
	@Autowired
	protected String smsTo;
	@Autowired
	protected String fromPhoneNumber;
	@Autowired
	protected String voiceUrl;

	@Before
	public void before(){
		
	}
	
	@After
	public void after(){
		
	}
	
	@Test
	public void sanity() throws MalformedURLException{
		logger.debug("sanity test");
		//local file paths do not work
//		Path path = FileSystems.getDefault().getPath("src/test/resources/sample-call.xml");
//		URL url = path.toUri().toURL();
		
		// only under twilio domain?
		URL url = new URL(voiceUrl);
		
//		URL url = new URL("http://demo.twilio.com/welcome/voice/");
		logger.debug("saying " + url.toString());
		logger.debug("to " + smsTo + " from " + fromPhoneNumber);

		Optional<Call> mesg = service.makeCall(smsTo, fromPhoneNumber, url);

		if(!mesg.isPresent()){
			fail("mesg is null!");
		}
		Call call = mesg.get();
		logger.debug(call.getFrom());
		logger.debug(call.getAnsweredBy());
		logger.debug(call.getDuration());
		logger.debug(call.getPrice());

	}
}
