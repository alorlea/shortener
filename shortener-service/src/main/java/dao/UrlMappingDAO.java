package dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.UUID;

/**
 * Created by alberto on 2016-12-01.
 */
public class UrlMappingDAO {
    private String tableName;
    private String keyspace;

    private Cluster cassandraDB;

    public UrlMappingDAO(String tableName, String keyspace, Cluster cassandraDB) {
        this.tableName = tableName;
        this.keyspace = keyspace;
        this.cassandraDB = cassandraDB;
    }


    private Session getSession(Cluster cassandraDB){
        return cassandraDB.connect();
    }

    public void putNewEncodedURL(String shortUrl, String originalUrl){
        String uuid = UUID.randomUUID().toString();
        Session session = getSession(cassandraDB);

        session.execute(
            "INSERT INTO " + keyspace + "." + tableName + " (id, shortUrl, originalUrl)" +
            "VALUES (" + uuid + ",'" + shortUrl + "'," + "'" + originalUrl + "')" + ";");

        session.close();
    }


    public String getOriginalURL(String shortUrl){
        Session session = getSession(cassandraDB);

        ResultSet results = session.execute("SELECT * FROM " + keyspace + "." + tableName
            +" WHERE shortUrl = '" + shortUrl + "' ALLOW FILTERING;");

        Row row1 = results.one();
        String result = null;
        if(row1 != null) {
            result = row1.getString("originalUrl");
        }
        session.close();
        return result;
    }

}
