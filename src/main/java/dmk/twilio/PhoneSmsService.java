package dmk.twilio;

import java.util.Optional;

import com.twilio.sdk.resource.instance.Sms;


public interface PhoneSmsService {

	public Optional<Sms> sendMessage(String to, String from, String body);
}
