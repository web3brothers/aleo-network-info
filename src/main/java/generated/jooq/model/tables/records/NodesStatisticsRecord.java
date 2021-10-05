/*
 * This file is generated by jOOQ.
 */
package generated.jooq.model.tables.records;


import generated.jooq.model.tables.NodesStatistics;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NodesStatisticsRecord extends UpdatableRecordImpl<NodesStatisticsRecord> implements Record9<String, OffsetDateTime, Boolean, Boolean, Boolean, OffsetDateTime, String, Boolean, Double> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.nodes_statistics.ip</code>.
     */
    public NodesStatisticsRecord setIp(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.ip</code>.
     */
    public String getIp() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.nodes_statistics.info_collected_on</code>.
     */
    public NodesStatisticsRecord setInfoCollectedOn(OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.info_collected_on</code>.
     */
    public OffsetDateTime getInfoCollectedOn() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>public.nodes_statistics.boot_node</code>.
     */
    public NodesStatisticsRecord setBootNode(Boolean value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.boot_node</code>.
     */
    public Boolean getBootNode() {
        return (Boolean) get(2);
    }

    /**
     * Setter for <code>public.nodes_statistics.miner</code>.
     */
    public NodesStatisticsRecord setMiner(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.miner</code>.
     */
    public Boolean getMiner() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>public.nodes_statistics.syncing</code>.
     */
    public NodesStatisticsRecord setSyncing(Boolean value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.syncing</code>.
     */
    public Boolean getSyncing() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>public.nodes_statistics.launched</code>.
     */
    public NodesStatisticsRecord setLaunched(OffsetDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.launched</code>.
     */
    public OffsetDateTime getLaunched() {
        return (OffsetDateTime) get(5);
    }

    /**
     * Setter for <code>public.nodes_statistics.version</code>.
     */
    public NodesStatisticsRecord setVersion(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.version</code>.
     */
    public String getVersion() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.nodes_statistics.not_reachable</code>.
     */
    public NodesStatisticsRecord setNotReachable(Boolean value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.not_reachable</code>.
     */
    public Boolean getNotReachable() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>public.nodes_statistics.block_count</code>.
     */
    public NodesStatisticsRecord setBlockCount(Double value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.nodes_statistics.block_count</code>.
     */
    public Double getBlockCount() {
        return (Double) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, OffsetDateTime> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<String, OffsetDateTime, Boolean, Boolean, Boolean, OffsetDateTime, String, Boolean, Double> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<String, OffsetDateTime, Boolean, Boolean, Boolean, OffsetDateTime, String, Boolean, Double> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return NodesStatistics.NODES_STATISTICS.IP;
    }

    @Override
    public Field<OffsetDateTime> field2() {
        return NodesStatistics.NODES_STATISTICS.INFO_COLLECTED_ON;
    }

    @Override
    public Field<Boolean> field3() {
        return NodesStatistics.NODES_STATISTICS.BOOT_NODE;
    }

    @Override
    public Field<Boolean> field4() {
        return NodesStatistics.NODES_STATISTICS.MINER;
    }

    @Override
    public Field<Boolean> field5() {
        return NodesStatistics.NODES_STATISTICS.SYNCING;
    }

    @Override
    public Field<OffsetDateTime> field6() {
        return NodesStatistics.NODES_STATISTICS.LAUNCHED;
    }

    @Override
    public Field<String> field7() {
        return NodesStatistics.NODES_STATISTICS.VERSION;
    }

    @Override
    public Field<Boolean> field8() {
        return NodesStatistics.NODES_STATISTICS.NOT_REACHABLE;
    }

    @Override
    public Field<Double> field9() {
        return NodesStatistics.NODES_STATISTICS.BLOCK_COUNT;
    }

    @Override
    public String component1() {
        return getIp();
    }

    @Override
    public OffsetDateTime component2() {
        return getInfoCollectedOn();
    }

    @Override
    public Boolean component3() {
        return getBootNode();
    }

    @Override
    public Boolean component4() {
        return getMiner();
    }

    @Override
    public Boolean component5() {
        return getSyncing();
    }

    @Override
    public OffsetDateTime component6() {
        return getLaunched();
    }

    @Override
    public String component7() {
        return getVersion();
    }

    @Override
    public Boolean component8() {
        return getNotReachable();
    }

    @Override
    public Double component9() {
        return getBlockCount();
    }

    @Override
    public String value1() {
        return getIp();
    }

    @Override
    public OffsetDateTime value2() {
        return getInfoCollectedOn();
    }

    @Override
    public Boolean value3() {
        return getBootNode();
    }

    @Override
    public Boolean value4() {
        return getMiner();
    }

    @Override
    public Boolean value5() {
        return getSyncing();
    }

    @Override
    public OffsetDateTime value6() {
        return getLaunched();
    }

    @Override
    public String value7() {
        return getVersion();
    }

    @Override
    public Boolean value8() {
        return getNotReachable();
    }

    @Override
    public Double value9() {
        return getBlockCount();
    }

    @Override
    public NodesStatisticsRecord value1(String value) {
        setIp(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value2(OffsetDateTime value) {
        setInfoCollectedOn(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value3(Boolean value) {
        setBootNode(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value4(Boolean value) {
        setMiner(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value5(Boolean value) {
        setSyncing(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value6(OffsetDateTime value) {
        setLaunched(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value7(String value) {
        setVersion(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value8(Boolean value) {
        setNotReachable(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord value9(Double value) {
        setBlockCount(value);
        return this;
    }

    @Override
    public NodesStatisticsRecord values(String value1, OffsetDateTime value2, Boolean value3, Boolean value4, Boolean value5, OffsetDateTime value6, String value7, Boolean value8, Double value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NodesStatisticsRecord
     */
    public NodesStatisticsRecord() {
        super(NodesStatistics.NODES_STATISTICS);
    }

    /**
     * Create a detached, initialised NodesStatisticsRecord
     */
    public NodesStatisticsRecord(String ip, OffsetDateTime infoCollectedOn, Boolean bootNode, Boolean miner, Boolean syncing, OffsetDateTime launched, String version, Boolean notReachable, Double blockCount) {
        super(NodesStatistics.NODES_STATISTICS);

        setIp(ip);
        setInfoCollectedOn(infoCollectedOn);
        setBootNode(bootNode);
        setMiner(miner);
        setSyncing(syncing);
        setLaunched(launched);
        setVersion(version);
        setNotReachable(notReachable);
        setBlockCount(blockCount);
    }
}