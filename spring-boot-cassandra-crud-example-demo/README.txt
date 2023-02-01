1. Terminal:
---------------
<pre>
$ cd $CASSANDRA_HOME
$ pwd
$ C:\ilgili_yol\cassandra\cassandra-3.11.6
$ ./bin/cassandra -f
</pre>
 
TERMINAL 2
---------------
cd $CASSANDRA_HOME
cqlsh

CREATE KEYSPACE huseyin_db
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 2};

use huseyin_db;

DESCRIBE keyspaces;

CREATE TABLE tutorial(
   id timeuuid PRIMARY KEY,
   title text,
   description text,
   published boolean
);


CREATE CUSTOM INDEX idx_title ON mytestdb.tutorial (title) 
USING 'org.apache.cassandra.index.sasi.SASIIndex' 
WITH OPTIONS = {
'mode': 'CONTAINS', 
'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.NonTokenizingAnalyzer', 
'case_sensitive': 'false'};

SELECT * FROM tutorial;