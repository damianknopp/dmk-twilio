package dmk.twilio.sms.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.sdk.resource.factory.SmsFactory;

import dmk.twilio.sms.PhoneSmsService;
import dmk.twilio.sms.PhoneSmsServiceImpl;
import dmk.twilio.sms.TwilioRestClientProvider;
import dmk.twilio.sms.TwilioRestClientProviderImpl;

@Configuration
public class TwilioSmsConf {
	Logger logger = LoggerFactory.getLogger(TwilioSmsConf.class);
	
	@Autowired
	protected String accountSid;
	@Autowired
	protected String authToken;
	
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
	public TwilioRestClientProvider twilioRestClientProvider() {
		logger.info("accountSid " + accountSid);
		TwilioRestClientProvider provider = new TwilioRestClientProviderImpl();
		provider.setAccountSid(accountSid);
		provider.setAuthToken(authToken);
		return provider;
	}
}