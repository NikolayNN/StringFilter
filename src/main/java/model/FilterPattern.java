package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FilterPattern {

    private List<Pattern> patterns;

    public FilterPattern(String[] filterArguments) {
        this.patterns = createRegExpPatterns(filterArguments);
    }

    private List<Pattern> createRegExpPatterns(String[] filterArguments) {

        List<Pattern> result = new ArrayList<>();

        if (filterArguments == null) {
            result.add(Pattern.compile(""));
            return result;
        }

        for (String arg : filterArguments) {
            try {
                result.add(Pattern.compile(arg));
            } catch (PatternSyntaxException ex) {
                result.add(Pattern.compile(shieldSymbols(arg)));
            }
        }
        return result;
    }

    private String shieldSymbols(String parameter) {
        final String shield = "\\";
        StringBuilder builder = new StringBuilder();
        builder.append(shield);
        for (int i = 0; i < parameter.length(); i += 1) {
            if (i != 0) {
                builder.append(shield);
            }
            builder.append(parameter.substring(i, i + 1));
        }
        return builder.toString();
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }
}
