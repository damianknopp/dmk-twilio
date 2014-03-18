package dmk.twilio.sms;

import com.twilio.sdk.resource.factory.SmsFactory;

public interface TwilioRestClientProvider {

	public TwilioRestClientProvider setAccountSid(final String accountSid);
	public TwilioRestClientProvider setAuthToken(final String authToken);
	public SmsFactory getSmsFactory();

}