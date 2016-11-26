package retry;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netflix.astyanax.retry.RetryPolicy;
import io.dropwizard.jackson.Discoverable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface RetryPolicyFactory extends Discoverable {

    RetryPolicy build();
}
