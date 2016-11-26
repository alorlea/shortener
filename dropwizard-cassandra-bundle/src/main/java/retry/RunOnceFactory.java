package retry;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netflix.astyanax.retry.RetryPolicy;
import com.netflix.astyanax.retry.RunOnce;

@JsonTypeName("runOnce")
public class RunOnceFactory implements RetryPolicyFactory {

    @Override
    public RetryPolicy build() {
        return RunOnce.get();
    }

}
