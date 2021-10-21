# Aleo network peers info collector. 

Gathers and displays [aleo network](https://www.aleo.org/) blockchain peers information

## How it works:

There are several boot nodes, which are used to get information for other nodes upon boot in aleo network. The application asks this bootnodes to get
list of peers, then request each peer to get info and get another peers from those peers. It uses breadth first search algorithm to go over all peers.

## Access live instance:

* [Live](http://aleo-peers-info.web3brothers.tech)

## To run application locally:

```
https://github.com/web3brothers/aleo-network-info.git
```
Go to /docker folder and run:
`
docker-compose up
`

## Generate database tables mappings:

To regenerate jooq stubs, in case of database changes:

`mvn generate-sources -P generate-jooq-code`

## Build docker image :

`mvn -Pprod spring-boot:build-image`
