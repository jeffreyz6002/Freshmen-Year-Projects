//Name : Junhui Zhang
//ID Number : 112895310
//Recitation Number : R30

import java.util.*;
public class SongRecord  {

    Scanner stdin = new Scanner(System.in);

    private String artist; // The artist of the song
    private String title; // Title of the song
    private int minutes; // Length of song in minutes
    private int seconds; // Length of song in seconds

    /**
     * Constructor
     * Creates a song using artist, title, minutes and seconds
     * @param artist , artitst for the song
     * @param title , title of the song
     * @param minutes , length of song in minutes
     * @param seconds , length of song in seconds
     * @throws IllegalArgumentException
     * When minutes is less than zero
     * @throws IllegalArgumentException
     * When seconds is below 0 or above 59
     */

    public SongRecord(String artist, String title, int minutes, int seconds){
        try{
            if(minutes < 0){
                throw new IllegalArgumentException("The amount of minutes entered is invalid.");
            }
        }
        catch( IllegalArgumentException i){
            System.out.print("Input is invalid. Please enter minutes again: ");
            minutes = stdin.nextInt();
        }

        try {
            if (seconds < 0 || seconds > 59) {
                throw new IllegalArgumentException("The amount of seconds entered is invalid.");
            }
        }
        catch(IllegalArgumentException i){
            System.out.print("Input is invalid. Please enter seconds again: ");
            seconds = stdin.nextInt();
        }

        this.artist = artist;
        this.title = title;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Gets the artist
     * @return artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Gets the title
     * @return title of song
     */
    public String getTitle(){
        return title;
    }

    /**
     * Gets the length of song in minutes
     * @return length of song in minutes
     */
    public int getMinutes(){
        return  minutes;
    }

    /**
     * Gets the length of song in seconds
     * @return length of song in seconds
     */
    public int getSeconds(){
        return seconds;
    }

    /**
     * Sets the artist for song
     * @param artist of the song
     */
    public void setArtist(String artist){
        this.artist = artist;
    }

    /**
     * Sets the title of the song
     * @param title of the song
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Sets the length of song in minutes
     * @param minutes, duration of song in seconds
     */
    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    /**
     * Sets the length of song in seconds
     * @param seconds, duration of song in seconds
     */
    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    /**
     * Puts the title, artist, minutes and seconds of song in string
     * @return information of Song
     */
    public String toString(){
        return  String.format("%-15s\t%-15s\t\t%d"+":" +"%02d%n", getTitle(), getArtist(),getMinutes(),getSeconds());
    }
}