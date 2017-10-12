package com.testcode.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class WebUtils {

    public static final String WEB_ADDRESS = "https://pastebin.com/raw/mnfFcrrW";
    //position of the file could be a better one!
    public static final String DOWNLOAD_PATH = "src/main/resources/document.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

    /**
     * This method is used to download at first the txt file containing the
     * time zones.
     */
    public static void getDocument() {
        try {
            final URL website = new URL(WEB_ADDRESS);
            final ReadableByteChannel channel = Channels.newChannel(website.openStream());
            final FileOutputStream stream = new FileOutputStream(DOWNLOAD_PATH);

            stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
            stream.close();
        } catch (Exception e) {
            LOGGER.error("Impossible to download text file!");
        }
    }
}
