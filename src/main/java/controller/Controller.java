package controller;

import model.FilterPattern;
import service.StringFilter;
import view.Console;
import view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    public void start(String[] filterArguments) {
        View view = new Console();
        view.write("Filter arguments: " + Arrays.toString(filterArguments));
        view.write("Input strings. Input empty string for continue.");

        List<String> inputStrings = new ArrayList<>();
        String input;
        while (!(input = view.read()).trim().equals("")) {
            inputStrings.add(input);
        }

        FilterPattern filterPattern = new FilterPattern(filterArguments);

        StringFilter stringFilter = new StringFilter();
        List<String> result = stringFilter.doFilter(inputStrings, filterPattern);
        result.stream().forEach(s -> view.write(s));
    }
}
