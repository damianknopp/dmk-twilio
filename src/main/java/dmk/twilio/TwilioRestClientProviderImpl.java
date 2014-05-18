package dmk.twilio;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;

public class TwilioRestClientProviderImpl implements TwilioRestClientProvider{
	
	protected String accountSid;
	protected String authToken;
	protected String phoneNumber;
	
	protected TwilioRestClient client;
	protected SmsFactory smsFactory;
	protected CallFactory callFactory;
	
	public TwilioRestClientProviderImpl(){
		super();
	}

	protected void init(){
		client = new TwilioRestClient(accountSid, authToken);
		smsFactory = client.getAccount().getSmsFactory();
		callFactory = client.getAccount().getCallFactory();
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
		if(smsFactory == null){
			init();
		}
		return smsFactory;
	}
	
	public CallFactory getCallFactory(){
		if(callFactory == null){
			init();
		}
		return callFactory;
	}

	@Override
	public TwilioRestClientProvider setPhoneNumber(String phone) {
		this.phoneNumber = phone;
		return this;
	}
}