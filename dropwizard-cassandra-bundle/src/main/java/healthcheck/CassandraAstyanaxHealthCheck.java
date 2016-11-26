package healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.TokenRange;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by alberto on 2016-11-26.
 */
public class CassandraAstyanaxHealthCheck extends HealthCheck{
    AstyanaxContext<Keyspace> context;

    public CassandraAstyanaxHealthCheck(AstyanaxContext<Keyspace> context) {
        this.context = context;
    }

    @Override
    protected Result check() {
        List<TokenRange> tokenRangeList;

        try {
            tokenRangeList = context.getClient().describeRing();
        } catch (Exception ex) {
            return Result.unhealthy("Unable to Connect to Cassandra", ex);
        }

        return Result.healthy(StringUtils.join(tokenRangeList, ", "));
    }
}
