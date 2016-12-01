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
        this.getSession(cassandraDB).execute(
            "INSERT INTO " + keyspace + "." + tableName + " (id, shortUrl, originalUrl)" +
            "VALUES (" + uuid + ",'" + shortUrl + "'," + "'" + originalUrl + "')" + ";");
    }


    public String getOriginalURL(String shortUrl){
        ResultSet results = this.getSession(cassandraDB).execute("SELECT * FROM " + keyspace + "." + tableName
            +" WHERE shortUrl = '" + shortUrl + "' ALLOW FILTERING;");

        Row row1 = results.one();
        String result = null;
        if(row1 != null) {
            result = row1.getString("originalUrl");
        }
        for (Row row : results) {
            System.out.println(String.format("%-30s\t%-20s",
                row.getString("shortUrl"),
                row.getString("originalUrl")));
        }
        return result;
    }

}
