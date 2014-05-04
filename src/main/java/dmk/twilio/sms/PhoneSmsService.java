package dmk.twilio.sms;

import com.twilio.sdk.resource.instance.Sms;


public interface PhoneSmsService {

	public Sms sendMessage(String to, String from, String body);
}
