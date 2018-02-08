package service;

import model.FilterPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringFilter {

    public List<String> doFilter(List<String> strings, FilterPattern filterPattern) {
        List<String> result = new ArrayList<>();

        for (String str : strings) {
            String preparedString = str;
            if (str.endsWith(";")) {
                preparedString = str.substring(0, str.length() - 1);
            }
            if (hasFilterPattern(preparedString, filterPattern)) {
                result.add(str);
            }
        }
        return result;
    }

    private boolean hasFilterPattern(String string, FilterPattern filterPattern) {
        final String[] words = string.split(" ");
        for (String word : words) {
            for (Pattern pattern : filterPattern.getPatterns()) {
                if (pattern.matcher(word).matches()) {
                    return true;
                }
            }
        }
        return false;
    }
}
