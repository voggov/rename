import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.coderiders.teamtask.threads.MediaThread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    private static final String IN_FILE_TXT = "src/main/java/ru/ci_group2/lab/files/input.txt";
    private static final String NO_FILE_TXT = "";

    private List<MediaThread> getThreadList(String file) throws IOException {

        List<MediaThread> threadList = new ArrayList<>();
        BufferedReader inFile = new BufferedReader(new FileReader(file));
            String urlAndPath;
            while ((urlAndPath = inFile.readLine()) != null) {
                String[] data = urlAndPath.split(" ");
                var thread = new MediaThread(data[0], data[1]);
                thread.setName(data[0].substring(data[0].lastIndexOf("/") + 1));
                threadList.add(thread);
            }
        return threadList;
    }

    @Test
    @DisplayName("Чтение файла")
    public void downloadFromFile () throws IOException {

        assertEquals("rick-astley-never-gonna-give-you-up.mp3",  getThreadList(IN_FILE_TXT).get(1).getName());
        assertEquals("src/main/java/ru/ci_group2/lab/files/", getThreadList(IN_FILE_TXT).get(1).getPath());
    }

    @Test
    @DisplayName("Чтение не существующего файла ")
    public void downloadFromNoFile () {

        assertThrows(FileNotFoundException.class, ()-> getThreadList(NO_FILE_TXT));
    }

    @Test
    @DisplayName("Чтение null файла ")
    public void downloadFromNullFile () {

        assertThrows(NullPointerException.class, ()-> getThreadList(null));
    }
}
