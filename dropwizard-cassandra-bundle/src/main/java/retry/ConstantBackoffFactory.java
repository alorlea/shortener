package retry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netflix.astyanax.retry.ConstantBackoff;
import com.netflix.astyanax.retry.RetryPolicy;

@JsonTypeName("constantBackoff")
public class ConstantBackoffFactory implements RetryPolicyFactory {

    @JsonProperty
    int sleepTimeMs;

    @JsonProperty
    int max;

    @Override
    public RetryPolicy build() {
        return new ConstantBackoff(sleepTimeMs, max);
    }
}
