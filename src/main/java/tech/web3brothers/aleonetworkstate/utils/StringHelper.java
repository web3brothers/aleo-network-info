package tech.web3brothers.aleonetworkstate.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringHelper {

    /**
     * Split comma separated string.
     *
     * @param value comma separated string
     * @return list of {@link String}
     */
    public static List<String> splitString(String value) {
        return Arrays.stream(value.split(",")).map(String::trim).collect(Collectors.toList());
    }

}
