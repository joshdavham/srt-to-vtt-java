package io.github.joshdavham.srtconverter;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class SubtitleConverterTest {

    private static final Path TEST_DIR = Paths.get("src/test/resources/test-files");
    private static final Path INPUT_SRT = TEST_DIR.resolve("test_input.srt");
    private static final Path OUTPUT_VTT = TEST_DIR.resolve("test_output.vtt");

    @Test
    public void testSrtToVttConversion() throws IOException {
        SubtitleConverter.srtToVtt(INPUT_SRT.toString(), OUTPUT_VTT.toString());

        assertTrue(Files.exists(OUTPUT_VTT));

        String result = Files.readString(OUTPUT_VTT);
        assertTrue(result.startsWith("WEBVTT"));
        assertFalse(result.contains("\n1\n")); // no numeric line
        assertTrue(result.contains("00:00:22.230 --> 00:00:24.606")); // time conversion
        assertTrue(result.contains("This is the first subtitle."));
    }
}
