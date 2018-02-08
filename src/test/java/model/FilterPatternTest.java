package model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class FilterPatternTest {

    @Test
    public void getParameter() throws Exception {
        //given
        final String[] filterArguments = {"abcd"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(Pattern.compile("abcd"));
        //when
        List<Pattern> actualFilterPatterns = new FilterPattern(filterArguments).getPatterns();
        //then
        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterTwoWords() throws Exception {
        //given
        final String[] filterArguments = {"abcd", "asdf"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(
                Pattern.compile("abcd"),
                Pattern.compile("asdf")
        );

        //when
        List<Pattern> actualFilterPatterns = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
             assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterThreeWords() throws Exception {
        //given
        final String[] filterArguments = {"abcd", "asdf", "qwert"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(
                Pattern.compile("abcd"),
                Pattern.compile("asdf"),
                Pattern.compile("qwert")
        );

        //when
        List<Pattern> actualFilterPatterns  = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterOneWordsAndOneValidRegexp() throws Exception {
        //given
        final String[] filterArguments = {"abcd", "^ab.+"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(
                Pattern.compile("abcd"),
                Pattern.compile("^ab.+")
        );

        //when
        List<Pattern> actualFilterPatterns  = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterOneWordsAndOneWrongRegexp() throws Exception {
        //given
        final String[] filterArguments = {"abcd", "*"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(
                Pattern.compile("abcd"),
                Pattern.compile("\\*")
        );

        //when
        List<Pattern> actualFilterPatterns  = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterWrongRegExp1() throws Exception {
        //given
        final String[] filterArguments = {"*"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(Pattern.compile("\\*"));

        //when
        List<Pattern> actualFilterPatterns = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterWrongRegExp2() throws Exception {
        //given
        final String[] filterArguments = {"*$"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(Pattern.compile("\\*\\$"));

        //when
        List<Pattern> actualFilterPatterns  = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterValidRegExp() throws Exception {
        //given
        final String[] filterArguments = {"^ab.+"};
        final List<Pattern> expectFilterPatterns = Arrays.asList(Pattern.compile("^ab.+"));

        //when
        List<Pattern> actualFilterPatterns  = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterEmpty() throws Exception {
        //given
        final String[] filterArguments = {""};
        final List<Pattern> expectFilterPatterns = Arrays.asList(Pattern.compile(""));

        //when
        List<Pattern> actualFilterPatterns = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }

    @Test
    public void getParameterNull() throws Exception {
        //given
        final String[] filterArguments = null;
        final List<Pattern> expectFilterPatterns = Arrays.asList(Pattern.compile(""));

        //when
        List<Pattern> actualFilterPatterns = new FilterPattern(filterArguments).getPatterns();

        //then
        assertEquals(expectFilterPatterns.size(), actualFilterPatterns.size());
        for (int i = 0; i < expectFilterPatterns.size(); i++) {
            assertEquals(expectFilterPatterns.get(i).pattern(), actualFilterPatterns.get(i).pattern());
        }
    }
}