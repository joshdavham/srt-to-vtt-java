package io.github.joshdavham.srtconverter;

import java.io.*;

public class SubtitleConverter {
    
    public static void srtToVtt(String srtPath, String vttPath) throws IOException
    {

        try (
            BufferedReader reader = new BufferedReader(new FileReader(srtPath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(vttPath));
        ) {

            writer.write("WEBVTT\n\n");

            String line;
            String previousNonNumericLine = null;
            while ((line = reader.readLine()) != null)
            {
                
                line = line.trim();

                if (line.matches("^\\d+$")) {
                    continue;
                }

                if (line.contains("-->"))
                {
                    line = line.replace(",", ".");
                }

                if (previousNonNumericLine != null) {
                    writer.write(previousNonNumericLine);
                    writer.newLine();
                }

                previousNonNumericLine = line;


            }

        // Write the final line (no trailing newline)
        if (previousNonNumericLine != null) {
            writer.write(previousNonNumericLine);
        }

        }

    }

}
