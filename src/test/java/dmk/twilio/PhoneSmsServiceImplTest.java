package dmk.twilio;

import static org.junit.Assert.fail;

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

import com.twilio.sdk.resource.instance.Sms;

import dmk.twilio.PhoneSmsService;
import dmk.twilio.conf.TwilioTestConf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TwilioTestConf.class)
public class PhoneSmsServiceImplTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected PhoneSmsService service;
	@Autowired
	protected String smsTo;
	@Autowired
	protected String fromPhoneNumber;

	@Before
	public void before(){
		
	}
	
	@After
	public void after(){
		
	}
	
	@Test
	public void sanity(){
		logger.debug("sanity test");
		String body = "Test message.";
		Optional<Sms> mesg = service.sendMessage(smsTo, fromPhoneNumber, body);

		if(!mesg.isPresent()){
			fail("mesg is null!");
		}
		Sms sms = mesg.get();
		System.out.println("sent " + sms.getDateSent());
		System.out.println("to " + sms.getTo());
		System.out.println("price: " + sms.getPrice());
		
		logger.info("to = " + smsTo);
		logger.info("from = " + fromPhoneNumber);

	}
}
