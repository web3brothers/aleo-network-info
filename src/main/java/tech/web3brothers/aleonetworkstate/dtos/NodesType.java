package tech.web3brothers.aleonetworkstate.dtos;

public enum NodesType {
    ALL("Not defined"),
    MINERS("Client"),
    FULL_NODES("Client"),
    BOOT_NODES("SyncProvider");

    private String nodeTypeName;

    NodesType(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
    }

    public String getNodeTypeName() {
        return nodeTypeName;
    }
}
