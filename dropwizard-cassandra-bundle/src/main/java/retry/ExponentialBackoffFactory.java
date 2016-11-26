package retry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netflix.astyanax.retry.ExponentialBackoff;
import com.netflix.astyanax.retry.RetryPolicy;

@JsonTypeName("exponentialBackoff")
public class ExponentialBackoffFactory implements RetryPolicyFactory {

    @JsonProperty
    long baseSleepTimeMs;

    @JsonProperty
    int maxAttempts;

    @Override
    public RetryPolicy build() {
        return new ExponentialBackoff(baseSleepTimeMs, maxAttempts);
    }
}
