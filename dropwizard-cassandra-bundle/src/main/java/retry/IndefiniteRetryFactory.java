package retry;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netflix.astyanax.retry.IndefiniteRetry;
import com.netflix.astyanax.retry.RetryPolicy;

@JsonTypeName("indefiniteRetry")
public class IndefiniteRetryFactory implements RetryPolicyFactory {
    @Override
    public RetryPolicy build() {
        return new IndefiniteRetry();
    }
}
