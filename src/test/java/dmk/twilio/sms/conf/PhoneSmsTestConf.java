package dmk.twilio.sms.conf;

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

import com.twilio.sdk.resource.factory.SmsFactory;

import dmk.twilio.sms.PhoneSmsService;
import dmk.twilio.sms.PhoneSmsServiceImpl;
import dmk.twilio.sms.TwilioRestClientProvider;
import dmk.twilio.sms.TwilioRestClientProviderImpl;

@Configuration
public class PhoneSmsTestConf {
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
	
	
	@Bean
	public String smsTo(){
		return this.to;
	}
	
	@Bean
	public String smsFrom(){
		return this.from;
	}
	
	@Bean
	public PhoneSmsService phoneSmsService(){
		PhoneSmsService service = new PhoneSmsServiceImpl();
		service.setSmsFactory(smsFactory());
		return service;
	}
	
	@Bean 
	public SmsFactory smsFactory(){
		TwilioRestClientProvider provider = twilioRestClientProvider();
		return provider.getSmsFactory();
	}
	
	@Bean
	public TwilioRestClientProvider twilioRestClientProvider(){
		logger.info("accountSid " + accountSid);
		TwilioRestClientProvider provider = new TwilioRestClientProviderImpl();
		provider.setAccountSid(accountSid);
		provider.setAuthToken(authToken);
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
