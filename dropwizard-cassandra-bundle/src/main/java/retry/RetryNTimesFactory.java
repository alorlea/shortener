package retry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netflix.astyanax.retry.RetryNTimes;
import com.netflix.astyanax.retry.RetryPolicy;

@JsonTypeName("retryNTimes")
public class RetryNTimesFactory implements RetryPolicyFactory {

    @JsonProperty
    int maxAttemptCount;

    @Override
    public RetryPolicy build() {
        return new RetryNTimes(maxAttemptCount);
    }
}
