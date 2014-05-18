package dmk.twilio.conf;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;

import dmk.twilio.PhoneCallService;
import dmk.twilio.PhoneCallServiceImpl;
import dmk.twilio.PhoneSmsService;
import dmk.twilio.PhoneSmsServiceImpl;
import dmk.twilio.TwilioRestClientProvider;
import dmk.twilio.TwilioRestClientProviderImpl;

@Configuration
public class TwilioTestConf {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	final static Resource[] locations = { 
			new ClassPathResource("twilio-sms.properties") 
			};

	@Value("${dmk.phone.twilio.sms.authtoken}")
	protected String authToken;
	protected String accountSid;
	
	@Value("${dmk.phone.twilio.sms.accountsid}")
	public void setAccountSid(String s){
		logger.info("account sid =" + accountSid);
		this.accountSid = s;
	}
	
	@Value("${dmk.phone.twilio.sms.test.to}")
	protected String to;
	@Value("${dmk.phone.twilio.sms.test.from}")
	protected String from;
	@Value("${dmk.phone.twilio.test.voiceUrl}")
	protected String voiceUrl;
	
	@Bean
	public String smsTo(){
		return this.to;
	}
	
	@Bean
	public String fromPhoneNumber(){
		return this.from;
	}
	
	@Bean
	public String voiceUrl(){
		return this.voiceUrl;
	}
	
	@Bean
	public PhoneSmsService phoneSmsService(){
		PhoneSmsServiceImpl service = new PhoneSmsServiceImpl();
		service.setSmsFactory(smsFactory());
		return service;
	}

	@Bean
	public PhoneCallService phoneCallService(){
		PhoneCallServiceImpl service = new PhoneCallServiceImpl();
		service.setCallFactory(callFactory());
		return service;
	}

	
	@Bean 
	public SmsFactory smsFactory(){
		TwilioRestClientProvider provider = twilioRestClientProvider();
		return provider.getSmsFactory();
	}

	@Bean 
	public CallFactory callFactory(){
		TwilioRestClientProvider provider = twilioRestClientProvider();
		return provider.getCallFactory();
	}

	@Bean
	public TwilioRestClientProvider twilioRestClientProvider(){
		logger.info("accountSid " + accountSid);
		TwilioRestClientProvider provider = new TwilioRestClientProviderImpl();
		provider.setAccountSid(accountSid);
		provider.setAuthToken(authToken);
		provider.setPhoneNumber(from);
		return provider;
	}
	
	@Bean
	public static EnvironmentStringPBEConfig environmentStringPBEConfig() {
		EnvironmentStringPBEConfig tmp = new EnvironmentStringPBEConfig();
		tmp.setAlgorithm("PBEWithMD5AndDES");
		String pass = "twilio-sms-salt";
		tmp.setPasswordCharArray(pass.toCharArray());
		pass = null;
		return tmp;
	}

	@Bean
	public static StandardPBEStringEncryptor configurationEncryptor() {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		standardPBEStringEncryptor.setConfig(environmentStringPBEConfig());
		return standardPBEStringEncryptor;
	}

	@Bean
	public static EncryptablePropertyPlaceholderConfigurer encryptablePropertyPlaceholderConfigurer() {
		EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(
				configurationEncryptor());
		configurer.setLocations(locations);
		return configurer;
	}

}
