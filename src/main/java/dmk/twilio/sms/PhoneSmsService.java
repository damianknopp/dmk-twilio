package dmk.twilio.sms;

import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;


public interface PhoneSmsService {

	public Sms sendMessage(String to, String from, String body);
	public void setSmsFactory(SmsFactory factory);
}
