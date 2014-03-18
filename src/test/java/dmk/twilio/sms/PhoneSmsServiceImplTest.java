package dmk.twilio.sms;

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

import dmk.twilio.sms.conf.PhoneSmsTestConf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PhoneSmsTestConf.class)
public class PhoneSmsServiceImplTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected PhoneSmsService service;
	@Autowired
	protected String smsTo;
	@Autowired
	protected String smsFrom;

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
		Sms mesg = service.sendMessage(smsTo, smsFrom, body);
		System.out.println("sent " + mesg.getDateSent());
		System.out.println("to " + mesg.getTo());
		System.out.println("price: " + mesg.getPrice());
		
		logger.info("to = " + smsTo);
		logger.info("from = " + smsFrom);

	}
}
