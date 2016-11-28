package dao;

import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.ColumnList;
import com.netflix.astyanax.model.Rows;
import com.netflix.astyanax.serializers.StringSerializer;
import java.util.UUID;

/**
 * Created by alberto on 2016-11-28.
 */
public class UrlDAO {

    private Keyspace keyspace;
    private static final ColumnFamily<String, String> CF_URL_MAPPING =
        new ColumnFamily<String, String>(
            "url-mapping",
            StringSerializer.get(),
            StringSerializer.get()
        );

    public UrlDAO(Keyspace keyspace) {
        this.keyspace = keyspace;
    }

    public void insertUrlMapping(String shortUrl, String originalUrl){
        MutationBatch m = keyspace.prepareMutationBatch();
        String uuidToken = UUID.randomUUID().toString();

        m.withRow(CF_URL_MAPPING,"url-mappings")
            .putColumn("shorturl", shortUrl)
            .putColumn("url",originalUrl);

        try{
            OperationResult<Void> result = m.execute();
        }catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
}

