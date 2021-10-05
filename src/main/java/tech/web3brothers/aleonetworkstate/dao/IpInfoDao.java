package tech.web3brothers.aleonetworkstate.dao;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import tech.web3brothers.aleonetworkstate.dtos.IpInfoDto;

import java.math.BigDecimal;

import static generated.jooq.model.Tables.IP_INFO;

@Repository
@RequiredArgsConstructor
public class IpInfoDao {

    private final DSLContext dslContext;

    public void insertIpInfo(IpInfoDto dto) {
        dslContext.insertInto(IP_INFO,
                IP_INFO.IP,
                IP_INFO.COUNTRY,
                IP_INFO.COUNTRYCODE,
                IP_INFO.REGION,
                IP_INFO.REGIONNAME,
                IP_INFO.CITY,
                IP_INFO.ZIP,
                IP_INFO.LAT,
                IP_INFO.LON,
                IP_INFO.TIMEZONE,
                IP_INFO.ISP,
                IP_INFO.ORG,
                IP_INFO.AUTONOMOUS_SYSTEM)
                .values(dto.getQuery(),
                        dto.getCountry(),
                        dto.getCountryCode(),
                        dto.getRegion(),
                        dto.getRegionName(),
                        dto.getCity(),
                        dto.getZip(),
                        dto.getLat() != null ? BigDecimal.valueOf(dto.getLat()) : null,
                        dto.getLon() != null ? BigDecimal.valueOf(dto.getLon()) : null,
                        dto.getTimeZone(),
                        dto.getIsp(),
                        dto.getOrg(),
                        dto.getAs())
                .execute();
    }
}
