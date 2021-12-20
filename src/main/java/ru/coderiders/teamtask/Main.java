package ru.coderiders.teamtask;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import ru.coderiders.teamtask.threads.MediaThread;
import ru.coderiders.teamtask.threads.TagProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.coderiders.teamtask.rename.RenameClass.getNewFileName;


public class Main {
    public static void main(String[] args) throws InterruptedException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        String from = "https://ru.drivemusic.me/dl/Hu1UU3t9Qvh6EMAdLTCk8A/1639769791/download_music/2018/03/rick-astley-never-gonna-give-you-up.mp3";
        String to = "D:\\MyFolder\\cr_group2-develop\\src\\main\\java\\ru\\coderiders\\teamtask\\files\\";

        Path path = Paths.get(to);

        if (Files.exists(path) && Files.isDirectory(path) && path.isAbsolute()) {

            saveMp3(from, to);

        } else System.out.println("Путь не коректен");
    }

    public static File saveMp3(String from, String to) throws InterruptedException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        var thread = new MediaThread(from, to);
        String tempTitle = from.substring(from.lastIndexOf("/") + 1);
        thread.setName(tempTitle);
        thread.start();
        thread.join();
        File file = new File(to + tempTitle);

        while(!file.renameTo(new File(to + TagProvider.getTitle(to + tempTitle)))) {
            var newFileName =  getNewFileName(to + TagProvider.getTitle(to + tempTitle));
            if (file.renameTo(new File(newFileName)))
                break;
        }

        return new File(to);
    }
}
