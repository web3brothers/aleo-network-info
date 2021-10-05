# aleo network info collector

Gather and display aleo network blockchain peers information

# How it works:

There are several boot nodes, which are used to get information for other nodes upon boot in aleo netwokr. The application asks this bootnodes to get
list of peers, then request each peer to get info and get another peers from those peers. It uses Bread First Search algorithm to go over all peers.

###### Generate database tables mappings:

To regenerate jooq stubs, in case of database changes:

`mvn generate-sources -P generate-jooq-code`

###### Build docker image :

`mvn -Pprod spring-boot:build-image`


