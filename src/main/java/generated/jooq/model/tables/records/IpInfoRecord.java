/*
 * This file is generated by jOOQ.
 */
package generated.jooq.model.tables.records;


import generated.jooq.model.tables.IpInfo;

import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IpInfoRecord extends UpdatableRecordImpl<IpInfoRecord> implements Record13<String, String, String, String, String, String, String, BigDecimal, BigDecimal, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.ip_info.ip</code>.
     */
    public IpInfoRecord setIp(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.ip</code>.
     */
    public String getIp() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.ip_info.country</code>.
     */
    public IpInfoRecord setCountry(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.country</code>.
     */
    public String getCountry() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.ip_info.countrycode</code>.
     */
    public IpInfoRecord setCountrycode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.countrycode</code>.
     */
    public String getCountrycode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.ip_info.region</code>.
     */
    public IpInfoRecord setRegion(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.region</code>.
     */
    public String getRegion() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.ip_info.regionname</code>.
     */
    public IpInfoRecord setRegionname(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.regionname</code>.
     */
    public String getRegionname() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.ip_info.city</code>.
     */
    public IpInfoRecord setCity(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.city</code>.
     */
    public String getCity() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.ip_info.zip</code>.
     */
    public IpInfoRecord setZip(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.zip</code>.
     */
    public String getZip() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.ip_info.lat</code>.
     */
    public IpInfoRecord setLat(BigDecimal value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.lat</code>.
     */
    public BigDecimal getLat() {
        return (BigDecimal) get(7);
    }

    /**
     * Setter for <code>public.ip_info.lon</code>.
     */
    public IpInfoRecord setLon(BigDecimal value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.lon</code>.
     */
    public BigDecimal getLon() {
        return (BigDecimal) get(8);
    }

    /**
     * Setter for <code>public.ip_info.timezone</code>.
     */
    public IpInfoRecord setTimezone(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.timezone</code>.
     */
    public String getTimezone() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.ip_info.isp</code>.
     */
    public IpInfoRecord setIsp(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.isp</code>.
     */
    public String getIsp() {
        return (String) get(10);
    }

    /**
     * Setter for <code>public.ip_info.org</code>.
     */
    public IpInfoRecord setOrg(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.org</code>.
     */
    public String getOrg() {
        return (String) get(11);
    }

    /**
     * Setter for <code>public.ip_info.autonomous_system</code>.
     */
    public IpInfoRecord setAutonomousSystem(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>public.ip_info.autonomous_system</code>.
     */
    public String getAutonomousSystem() {
        return (String) get(12);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row13<String, String, String, String, String, String, String, BigDecimal, BigDecimal, String, String, String, String> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    @Override
    public Row13<String, String, String, String, String, String, String, BigDecimal, BigDecimal, String, String, String, String> valuesRow() {
        return (Row13) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return IpInfo.IP_INFO.IP;
    }

    @Override
    public Field<String> field2() {
        return IpInfo.IP_INFO.COUNTRY;
    }

    @Override
    public Field<String> field3() {
        return IpInfo.IP_INFO.COUNTRYCODE;
    }

    @Override
    public Field<String> field4() {
        return IpInfo.IP_INFO.REGION;
    }

    @Override
    public Field<String> field5() {
        return IpInfo.IP_INFO.REGIONNAME;
    }

    @Override
    public Field<String> field6() {
        return IpInfo.IP_INFO.CITY;
    }

    @Override
    public Field<String> field7() {
        return IpInfo.IP_INFO.ZIP;
    }

    @Override
    public Field<BigDecimal> field8() {
        return IpInfo.IP_INFO.LAT;
    }

    @Override
    public Field<BigDecimal> field9() {
        return IpInfo.IP_INFO.LON;
    }

    @Override
    public Field<String> field10() {
        return IpInfo.IP_INFO.TIMEZONE;
    }

    @Override
    public Field<String> field11() {
        return IpInfo.IP_INFO.ISP;
    }

    @Override
    public Field<String> field12() {
        return IpInfo.IP_INFO.ORG;
    }

    @Override
    public Field<String> field13() {
        return IpInfo.IP_INFO.AUTONOMOUS_SYSTEM;
    }

    @Override
    public String component1() {
        return getIp();
    }

    @Override
    public String component2() {
        return getCountry();
    }

    @Override
    public String component3() {
        return getCountrycode();
    }

    @Override
    public String component4() {
        return getRegion();
    }

    @Override
    public String component5() {
        return getRegionname();
    }

    @Override
    public String component6() {
        return getCity();
    }

    @Override
    public String component7() {
        return getZip();
    }

    @Override
    public BigDecimal component8() {
        return getLat();
    }

    @Override
    public BigDecimal component9() {
        return getLon();
    }

    @Override
    public String component10() {
        return getTimezone();
    }

    @Override
    public String component11() {
        return getIsp();
    }

    @Override
    public String component12() {
        return getOrg();
    }

    @Override
    public String component13() {
        return getAutonomousSystem();
    }

    @Override
    public String value1() {
        return getIp();
    }

    @Override
    public String value2() {
        return getCountry();
    }

    @Override
    public String value3() {
        return getCountrycode();
    }

    @Override
    public String value4() {
        return getRegion();
    }

    @Override
    public String value5() {
        return getRegionname();
    }

    @Override
    public String value6() {
        return getCity();
    }

    @Override
    public String value7() {
        return getZip();
    }

    @Override
    public BigDecimal value8() {
        return getLat();
    }

    @Override
    public BigDecimal value9() {
        return getLon();
    }

    @Override
    public String value10() {
        return getTimezone();
    }

    @Override
    public String value11() {
        return getIsp();
    }

    @Override
    public String value12() {
        return getOrg();
    }

    @Override
    public String value13() {
        return getAutonomousSystem();
    }

    @Override
    public IpInfoRecord value1(String value) {
        setIp(value);
        return this;
    }

    @Override
    public IpInfoRecord value2(String value) {
        setCountry(value);
        return this;
    }

    @Override
    public IpInfoRecord value3(String value) {
        setCountrycode(value);
        return this;
    }

    @Override
    public IpInfoRecord value4(String value) {
        setRegion(value);
        return this;
    }

    @Override
    public IpInfoRecord value5(String value) {
        setRegionname(value);
        return this;
    }

    @Override
    public IpInfoRecord value6(String value) {
        setCity(value);
        return this;
    }

    @Override
    public IpInfoRecord value7(String value) {
        setZip(value);
        return this;
    }

    @Override
    public IpInfoRecord value8(BigDecimal value) {
        setLat(value);
        return this;
    }

    @Override
    public IpInfoRecord value9(BigDecimal value) {
        setLon(value);
        return this;
    }

    @Override
    public IpInfoRecord value10(String value) {
        setTimezone(value);
        return this;
    }

    @Override
    public IpInfoRecord value11(String value) {
        setIsp(value);
        return this;
    }

    @Override
    public IpInfoRecord value12(String value) {
        setOrg(value);
        return this;
    }

    @Override
    public IpInfoRecord value13(String value) {
        setAutonomousSystem(value);
        return this;
    }

    @Override
    public IpInfoRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, BigDecimal value8, BigDecimal value9, String value10, String value11, String value12, String value13) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached IpInfoRecord
     */
    public IpInfoRecord() {
        super(IpInfo.IP_INFO);
    }

    /**
     * Create a detached, initialised IpInfoRecord
     */
    public IpInfoRecord(String ip, String country, String countrycode, String region, String regionname, String city, String zip, BigDecimal lat, BigDecimal lon, String timezone, String isp, String org, String autonomousSystem) {
        super(IpInfo.IP_INFO);

        setIp(ip);
        setCountry(country);
        setCountrycode(countrycode);
        setRegion(region);
        setRegionname(regionname);
        setCity(city);
        setZip(zip);
        setLat(lat);
        setLon(lon);
        setTimezone(timezone);
        setIsp(isp);
        setOrg(org);
        setAutonomousSystem(autonomousSystem);
    }
}