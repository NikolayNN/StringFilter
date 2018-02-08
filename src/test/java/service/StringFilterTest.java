package service;

import model.FilterPattern;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringFilterTest {

    private StringFilter stringFilter;

    @Before
    public void setUp() throws Exception {
        stringFilter = new StringFilter();
    }

    @Test
    public void doFilter() throws Exception {
        //given
        final String[] filterArguments = {"abcd"};
        final List<String> inputedStrings = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd adbc abc;",
                "ghij abcd acdf;",
                "ab ac ad;"
        );
        final FilterPattern filterPattern = new FilterPattern(filterArguments);

        final List<String> expectedResult = Arrays.asList("abcd abc;", "ghij abcd acdf;");

        //when
        List<String> actualResult = stringFilter.doFilter(inputedStrings, filterPattern);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void doFilterTest2() throws Exception {
        //given
        final String[] filterArguments = {"abcd", "abc"};
        final List<String> inputedStrings = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd adbc abc;",
                "ghij abcd acdf;",
                "ab ac ad;"
        );
        final FilterPattern filterPattern = new FilterPattern(filterArguments);

        final List<String> expectedResult = Arrays.asList("abcd abc;", "bcd adbc abc;", "ghij abcd acdf;");

        //when
        List<String> actualResult = stringFilter.doFilter(inputedStrings, filterPattern);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void doFilterTest3() throws Exception {
        //given
        final String[] filterArguments = {"^ab.+"};
        final List<String> inputedStrings = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd adbc abc;",
                "ghij abcd acdf;",
                "ab ac ad;"
        );
        final FilterPattern filterPattern = new FilterPattern(filterArguments);

        final List<String> expectedResult = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd adbc abc;",
                "ghij abcd acdf;"
        );

        //when
        List<String> actualResult = stringFilter.doFilter(inputedStrings, filterPattern);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void doFilterTest4() throws Exception {
        //given
        final String[] filterArguments = {"pqrst"};
        final List<String> inputedStrings = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd adbc abc;",
                "ghij abcd acdf;",
                "ab ac ad;"
        );
        final FilterPattern filterPattern = new FilterPattern(filterArguments);

        final List<String> expectedResult = Collections.emptyList();

        //when
        List<String> actualResult = stringFilter.doFilter(inputedStrings, filterPattern);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void doFilterTest5() throws Exception {
        //given
        final String[] filterArguments = {"*"};
        final List<String> inputedStrings = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd adbc abc;",
                "ghij abcd acdf;",
                "ab ac ad;"
        );

        final FilterPattern filterPattern = new FilterPattern(filterArguments);

        final List<String> expectedResult = Collections.emptyList();

        //when
        List<String> actualResult = stringFilter.doFilter(inputedStrings, filterPattern);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void doFilterTest6() throws Exception {
        //given
        final String[] filterArguments = {"*"};
        final List<String> inputedStrings = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd * abc;",
                "ghij abcd acdf;",
                "ab ac ad;"
        );

        final FilterPattern filterPattern = new FilterPattern(filterArguments);

        final List<String> expectedResult = Collections.singletonList("bcd * abc;");

        //when
        List<String> actualResult = stringFilter.doFilter(inputedStrings, filterPattern);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void doFilterTest7() throws Exception {
        //given
        final String[] filterArguments = {"*$"};
        final List<String> inputedStrings = Arrays.asList(
                "abcdf;",
                "abcd abc;",
                "bcd *$ abc;",
                "ghij abcd acdf;",
                "ab ac ad;"
        );

        final FilterPattern filterPattern = new FilterPattern(filterArguments);

        final List<String> expectedResult = Collections.singletonList("bcd *$ abc;");

        //when
        List<String> actualResult = stringFilter.doFilter(inputedStrings, filterPattern);

        //then
        assertEquals(expectedResult, actualResult);
    }
}