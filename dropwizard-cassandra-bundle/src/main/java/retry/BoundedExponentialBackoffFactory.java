package retry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netflix.astyanax.retry.BoundedExponentialBackoff;
import com.netflix.astyanax.retry.RetryPolicy;

@JsonTypeName("boundedExponentialBackoff")
public class BoundedExponentialBackoffFactory implements RetryPolicyFactory {

    @JsonProperty
    long baseSleepTimeMs;

    @JsonProperty
    long maxSleepTimeMs;

    @JsonProperty
    int max;

    @Override
    public RetryPolicy build() {
        return new BoundedExponentialBackoff(baseSleepTimeMs, maxSleepTimeMs, max);
    }
}
