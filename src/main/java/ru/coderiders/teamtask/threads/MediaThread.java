package ru.coderiders.teamtask.threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class MediaThread extends Thread {
    private final String from;//ссылка на файл
    private final String to;//адрес для загрузки

    public MediaThread(String url, String path) {
        this.from = url;
        this.to = path;
    }

    @Override
    public void run() {
        try {
            downloadUsingNIO(from, to + getName());
            System.out.println("Файл " + getName() + " скачан успешно");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void downloadUsingNIO(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(file);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }

    public String getPath() {
        return to;
    }
}