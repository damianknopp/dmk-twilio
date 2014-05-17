package dmk.twilio.sms;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;

public class PhoneSmsServiceImpl implements PhoneSmsService{
	Logger logger = LoggerFactory.getLogger(getClass());
	protected SmsFactory factory;
	
	public PhoneSmsServiceImpl(){
		super();
	}
	
	@Override
	public Optional<Sms> sendMessage(String to, String from, String body) {
		if(factory == null){
			throw new IllegalStateException("no SMS factory found! Cannot send any messages!");
		}
		
		Validate.notNull(to);
		Validate.notEmpty(to);
		Validate.notNull(from);
		Validate.notEmpty(from);
		Validate.notNull(body);
		Validate.notEmpty(body);
		Map<String, String> params = new HashMap<>();
		params.put("To", to);
		params.put("From", from);
		params.put("Body", body);
		Sms message = null;
		try{
			message = factory.create(params);
			if(logger.isInfoEnabled()){
				String price = message.getPrice();
				logger.info("Sent message, cost: " + price);
			}
		}catch(TwilioRestException e){
			throw new RuntimeException(e);
		}
		return Optional.ofNullable(message);
	}

	public void setSmsFactory(SmsFactory factory) {
		this.factory = factory;
	}
}
