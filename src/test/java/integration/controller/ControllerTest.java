package integration.controller;

import controller.Controller;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;
    private final String LINE_SEPARATOR = System.lineSeparator();

    @Before
    public void setUp() throws Exception {
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void start() throws Exception {
        //given
        in.add("abcdf;");
        in.add("abcd abc;");
        in.add("bcd adbc abc;");
        in.add("ghij abcd acdf;");
        in.add("ab ac ad;");
        in.add(LINE_SEPARATOR);

        //when
        new Controller().start(new String[]{"abcd"});

        //then
        String expected = "Filter arguments: [abcd]" + LINE_SEPARATOR +
                "Input strings. Input empty string for continue." + LINE_SEPARATOR +
                "abcd abc;" + LINE_SEPARATOR +
                "ghij abcd acdf;" + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void start2() throws Exception {

        //given
        in.add("abcdf;");
        in.add("abcd abc;");
        in.add("bcd adbc abc;");
        in.add("ghij abcd acdf;");
        in.add("ab ac ad;");
        in.add(LINE_SEPARATOR);

        //when
        new Controller().start(new String[]{"abcd", "abc"});

        //then
        String expected = "Filter arguments: [abcd, abc]" + LINE_SEPARATOR +
                "Input strings. Input empty string for continue." + LINE_SEPARATOR +
                "abcd abc;" + LINE_SEPARATOR +
                "bcd adbc abc;" + LINE_SEPARATOR +
                "ghij abcd acdf;" + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void start3() throws Exception {

        //given
        in.add("abcdf;");
        in.add("abcd abc;");
        in.add("bcd adbc abc;");
        in.add("ghij abcd acdf;");
        in.add("ab ac ad;");
        in.add(LINE_SEPARATOR);

        //when
        new Controller().start(new String[]{"^ab.+"});

        //then
        String expected = "Filter arguments: [^ab.+]" + LINE_SEPARATOR +
                "Input strings. Input empty string for continue." + LINE_SEPARATOR +
                "abcdf;" + LINE_SEPARATOR +
                "abcd abc;" + LINE_SEPARATOR +
                "bcd adbc abc;" + LINE_SEPARATOR +
                "ghij abcd acdf;" + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void start4() throws Exception {

        //given
        in.add("abcdf;");
        in.add("abcd abc;");
        in.add("bcd adbc abc;");
        in.add("ghij abcd acdf;");
        in.add("ab ac ad;");
        in.add(LINE_SEPARATOR);

        //when
        new Controller().start(new String[]{"pqrst"});

        //then
        String expected = "Filter arguments: [pqrst]" + LINE_SEPARATOR +
                "Input strings. Input empty string for continue." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void start5() throws Exception {

        //given
        in.add("abcdf");
        in.add("abcd abc");
        in.add("bcd adbc abc");
        in.add("ghij abcd acdf");
        in.add("ab ac ad");
        in.add(LINE_SEPARATOR);

        //when
        new Controller().start(new String[]{"abcd", "abc"});

        //then
        String expected = "Filter arguments: [abcd, abc]" + LINE_SEPARATOR +
                "Input strings. Input empty string for continue." + LINE_SEPARATOR +
                "abcd abc" + LINE_SEPARATOR +
                "bcd adbc abc" + LINE_SEPARATOR +
                "ghij abcd acdf" + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void start6() throws Exception {

        //given
        in.add("abcdf;");
        in.add("abcd abc");
        in.add("bcd adbc abc;");
        in.add("ghij abcd acdf");
        in.add("ab ac ad;");
        in.add(LINE_SEPARATOR);

        //when
        new Controller().start(new String[]{"abcd", "abc"});

        //then
        String expected = "Filter arguments: [abcd, abc]" + LINE_SEPARATOR +
                "Input strings. Input empty string for continue." + LINE_SEPARATOR +
                "abcd abc" + LINE_SEPARATOR +
                "bcd adbc abc;" + LINE_SEPARATOR +
                "ghij abcd acdf" + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }

    @Test
    public void start7() throws Exception {

        //given
        in.add(LINE_SEPARATOR);

        //when
        new Controller().start(new String[]{"abcd", "abc"});

        //then
        String expected = "Filter arguments: [abcd, abc]" + LINE_SEPARATOR +
                "Input strings. Input empty string for continue." + LINE_SEPARATOR;

        assertEquals(expected, getData());
    }


    public String getData() {
        String result;
        try {
            result = new String(out.toByteArray(), "UTF-8");
            out.reset();
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
        return result;
    }
}