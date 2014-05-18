package dmk.twilio;

import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;

public interface TwilioRestClientProvider {

	public TwilioRestClientProvider setAccountSid(final String accountSid);
	public TwilioRestClientProvider setAuthToken(final String authToken);
	public TwilioRestClientProvider setPhoneNumber(final String phone);
	public SmsFactory getSmsFactory();
	public CallFactory getCallFactory();

}