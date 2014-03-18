package dmk.twilio.sms;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.SmsFactory;

public class TwilioRestClientProviderImpl implements TwilioRestClientProvider{
	protected String accountSid;
	protected String authToken;
	protected TwilioRestClient client;
	protected SmsFactory smsFactory;
	
	public TwilioRestClientProviderImpl(){
		super();
	}
	
	@Override
	public TwilioRestClientProvider setAccountSid(String accountSid) {
		this.accountSid = accountSid;
		return this;
	}

	@Override
	public TwilioRestClientProvider setAuthToken(String authToken) {
		this.authToken = authToken;
		return this;
	}

	@Override
	public SmsFactory getSmsFactory() {
		if(smsFactory != null){
			return smsFactory;
		}
		client = new TwilioRestClient(accountSid, authToken);
		smsFactory = client.getAccount().getSmsFactory();
		return smsFactory;
	}

}
