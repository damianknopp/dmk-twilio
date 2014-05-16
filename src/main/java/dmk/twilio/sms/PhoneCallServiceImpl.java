package dmk.twilio.sms;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;

public class PhoneCallServiceImpl implements PhoneCallService {
	Logger logger = LoggerFactory.getLogger(getClass());
	protected CallFactory factory;
	
	public PhoneCallServiceImpl() {
		super();
	}

	@Override
	public Optional<Call> makeCall(final String to, final String from, URL messageEndPoint) {
		Objects.requireNonNull(to);
		Objects.requireNonNull(from);
		Objects.requireNonNull(messageEndPoint);

		// Make a call
		final Map<String, String> callParams = new HashMap<String, String>();
		callParams.put("To", to);
		callParams.put("From", from); 
		callParams.put("Url", messageEndPoint.toString());
		
		Call call = null;
		try {
			call = factory.create(callParams);
			if(logger.isDebugEnabled()){
				logger.debug(call.getSid());
			}
		} catch (TwilioRestException e) {
			throw new RuntimeException(e);
		} 
		return Optional.of(call);
	}

	public void setCallFactory(CallFactory factory) {
		this.factory = factory;
	}
}
