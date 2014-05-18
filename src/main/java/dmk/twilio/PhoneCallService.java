package dmk.twilio;

import java.net.URL;
import java.util.Optional;

import com.twilio.sdk.resource.instance.Call;


public interface PhoneCallService {
	public Optional<Call> makeCall(String to, String from, URL url);
}
