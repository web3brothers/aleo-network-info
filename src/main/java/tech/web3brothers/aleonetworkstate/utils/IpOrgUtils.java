package tech.web3brothers.aleonetworkstate.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class IpOrgUtils {

    private final static List<String> orgCorrectNames = List
            .of("Digital Ocean", "Google Cloud", "Linode", "Hetzner", "Oracle Cloud Infrastructure",
                    "Vultr", "Contabo", "Ikoula", "Myloc", "Vdsina", "OVH", "AWS");

    public static String normalizeOrganizationName(String original, String isp) {
        if (original == null) {
            return null;
        }

        if (StringUtils.isBlank(original) && StringUtils.isNotBlank(isp)) {
            return normalizeOrganizationName(isp, isp);
        }

        for (String orgCorrectName : orgCorrectNames) {
            if (StringUtils.containsIgnoreCase(original, orgCorrectName)
                    || StringUtils.containsIgnoreCase(original.replaceAll("\\s", ""),
                    orgCorrectName.replaceAll("\\s", ""))) {
                return orgCorrectName;
            }
        }
        return original;
    }

}
