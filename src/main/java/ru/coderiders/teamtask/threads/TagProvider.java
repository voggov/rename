package ru.coderiders.teamtask.threads;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import java.io.File;
import java.io.IOException;

public class TagProvider {

    public static String getTitle(String absolutePath) throws TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {

        MP3File mp3File = new MP3File(absolutePath);
        ID3v1Tag id3v1Tag = null;
        String artist;
        String title;
        if (mp3File.hasID3v1Tag()) {
            id3v1Tag = mp3File.getID3v1Tag();
        }

        if (id3v1Tag.getTitle().equals(null) || id3v1Tag.getArtist().equals(null)){
            artist = "No artist";
            title = "No title";
        }else {
            artist = id3v1Tag.getArtist().toString();
            title = id3v1Tag.getTitle().toString();
        }

        String name = artist.substring(1,artist.length()-1) + " - " + title.substring(1,title.length()-1);

        return name + ".mp3";
    }

    public byte[] getImage(String absolutePath) throws TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        MP3File mp3file = new MP3File(new File(absolutePath));
        AbstractID3v2Tag tag = mp3file.getID3v2Tag();
        AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
        FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
        return body.getImageData();
    }
}
