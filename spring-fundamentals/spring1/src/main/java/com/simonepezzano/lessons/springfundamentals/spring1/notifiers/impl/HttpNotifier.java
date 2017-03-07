package com.simonepezzano.lessons.springfundamentals.spring1.notifiers.impl;

import com.simonepezzano.lessons.springfundamentals.spring1.notifiers.INotifier;
import org.apache.commons.io.IOUtils;
import org.springframework.core.NestedExceptionUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * This notifier will post the messages to httpbin.org, wait for its response and print it out
 * to the console.
 */
public class HttpNotifier implements INotifier {

    private static final String url = "https://httpbin.org/post";

    public void notifyMessage(String message) {
        System.out.println(send(message));

    }

    public void notifyCheckpoint() {
        System.out.println(send("Checkpoint " + new Date()));
    }

    public void notifyError(Exception e) {
        String errorMessage = NestedExceptionUtils.buildMessage("Error", e);
        System.out.println(send(errorMessage));
    }

    /**
     * Implementation of the HTTP POST
     * @param data the data to be sent
     */
    private String send(String data){
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("message="+data);
            writer.flush();
            InputStream stream = connection.getInputStream();
            /*
             * Here we're using IOUtils from Apache Commons IO because we're lazy
             */
            String response = IOUtils.toString(connection.getInputStream(),"UTF-8");
            writer.close();
            IOUtils.closeQuietly(stream);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
