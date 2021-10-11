package tech.web3brothers.aleonetworkstate.api;

public class Api {
    public static final String BASE = "/api/v1";
    public static class Public {
        public static final String NODES_SUMMARY = BASE + "/network-summary";
        public static final String NODES = BASE + "/nodes";
        public static final String ACTUAL_NODE_INFO = BASE + "/node/{ip}/actual-info";
    }
    public static class Private {

    }
}
