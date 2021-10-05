package tech.web3brothers.aleonetworkstate.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IpOrgUtilsTest {

    @Test
    void normalizeOrganizationNameForCompositeName() {
        String actual = IpOrgUtils.normalizeOrganizationName("DigitalOcean, LLC", "Something");
        assertThat(actual).isEqualTo("Digital Ocean");
    }

    @Test
    void normalizeOrganizationNameForSingleName() {
        String actual = IpOrgUtils.normalizeOrganizationName("Hetzner Online GmbH", "Something");
        assertThat(actual).isEqualTo("Hetzner");
    }

    @Test
    void normalizeOrganizationNameIfOrgAndIspNamesAreBlank() {
        String actual = IpOrgUtils.normalizeOrganizationName("", "");
        assertThat(actual).isEqualTo("");
    }

    @Test
    void normalizeOrganizationNameIfOrgIsBlankButIspNameIsNotBlank() {
        String actual = IpOrgUtils.normalizeOrganizationName("", "Hetzner Online GmbH");
        assertThat(actual).isEqualTo("Hetzner");
    }
}