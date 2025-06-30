package com.josh.srtconverter;

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
            while ((line = reader.readLine()) != null)
            {
                
                line = line.trim();

                if (line.contains("^\\d+$")) {
                    continue;
                }

                if (line.contains("-->"))
                {
                    line = line.replace(",", ",");
                }

                writer.write(line);
                writer.newLine();


            }



        }

    }

}
