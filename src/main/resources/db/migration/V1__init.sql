CREATE TABLE nodes_statistics
(
    ip                VARCHAR(17),
    info_collected_on TIMESTAMPTZ,
    boot_node         BOOLEAN,
    miner             BOOLEAN,
    syncing           BOOLEAN,
    launched          TIMESTAMPTZ,
    version           VARCHAR(30),
    not_reachable     BOOLEAN,
    PRIMARY KEY (ip, info_collected_on)
);

CREATE TABLE collection_dates
(
    collection_date TIMESTAMPTZ
);

CREATE TABLE ip_info
(
    ip          VARCHAR(17),
    country     VARCHAR(50),
    countryCode VARCHAR(4),
    region      VARCHAR(50),
    regionName  VARCHAR(200),
    city        VARCHAR(100),
    zip         VARCHAR(15),
    lat         NUMERIC(14, 11),
    lon         NUMERIC(14, 11),
    timeZone    VARCHAR(100),
    isp         VARCHAR,
    org         VARCHAR,
    autonomous_system  VARCHAR,
    PRIMARY KEY (ip)
)
