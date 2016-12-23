FROM cassandra:3.9

ENV CASSANDRA_START_RPC=true \
     MAX_HEAP_SIZE=384m \
     HEAP_NEWSIZE=64m \
     CASSANDRA_KEYSPACES="url-mappings"

EXPOSE 7000 7001 7199 9042 9160

# Redefine /cassandra as data directory so migrations persist
ADD ./scripts/*.cql ./scripts/setup /
RUN sed -i 's_/var/lib/cassandra/_/cassandra/_g' /etc/cassandra/cassandra.yaml \
 && mkdir /cassandra \
 && chmod 777 /cassandra \
 && ./setup \
 && chown -R cassandra:cassandra /cassandra \
 && nodetool flush

VOLUME /cassandra