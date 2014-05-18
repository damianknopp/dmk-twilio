package dmk.twilio.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;

import dmk.twilio.PhoneSmsService;
import dmk.twilio.PhoneSmsServiceImpl;
import dmk.twilio.TwilioRestClientProvider;
import dmk.twilio.TwilioRestClientProviderImpl;

@Configuration
public class TwilioConf {
	Logger logger = LoggerFactory.getLogger(TwilioConf.class);
	
	@Autowired
	protected String accountSid;
	@Autowired
	protected String authToken;
	@Autowired
	protected String fromPhoneNumber;
	
	@Bean
	public PhoneSmsService phoneSmsService() {
		PhoneSmsServiceImpl service = new PhoneSmsServiceImpl();
		service.setSmsFactory(smsFactory());
		return service;
	}

	@Bean
	public SmsFactory smsFactory() {
		TwilioRestClientProvider provider = twilioRestClientProvider();
		return provider.getSmsFactory();
	}
	
	@Bean
	public CallFactory callFactory() {
		TwilioRestClientProvider provider = twilioRestClientProvider();
		return provider.getCallFactory();
	}

	@Bean
	public TwilioRestClientProvider twilioRestClientProvider() {
		logger.info("accountSid " + accountSid);
		TwilioRestClientProvider provider = new TwilioRestClientProviderImpl();
		provider.setAccountSid(accountSid);
		provider.setAuthToken(authToken);
		provider.setPhoneNumber(fromPhoneNumber);
		return provider;
	}
}