import java.util.*;
public class PlaylistOperations {
    public static void main(String [] args){
        Scanner stdin = new Scanner(System.in);

        Playlist playlist = new Playlist(); // Creates original playlist
        Playlist newPlaylist; // Creates secondary playlist for individual artist
        SongRecord newSong; // Creates object for songs
        SongRecord song;

        String input = "T"; // Variable to hold input
        String title; // Variables for basic information of songs
        String artist;
        int mins;
        int secs;
        int pos;


        while(!input.equalsIgnoreCase("Q")){
            System.out.println("\nA) Add Song"); // Display all setting options
            System.out.println("B) Print Songs by Artist");
            System.out.println("G) Get Song");
            System.out.println("R) Remove Song");
            System.out.println("P) Print All Songs");
            System.out.println("S) Size");
            System.out.println("Q) Quit\n");

            System.out.print("Select a menu option: "); // In Taking Input
            input = stdin.next();
            stdin.nextLine();

            System.out.println();

            // Intakes information and adds song to playlist
            if(input.equalsIgnoreCase("A")){
                System.out.print("Enter the song title: ");
                title = stdin.nextLine();
                System.out.print("Enter the song artist: ");
                artist = stdin.nextLine();
                System.out.print("Enter the song length (minutes): ");
                mins = stdin.nextInt();
                System.out.print("Enter the song length (seconds): ");
                secs = stdin.nextInt();
                System.out.print("Enter the position: ");
                pos = stdin.nextInt();

                newSong  = new SongRecord(artist,title,mins,secs);
                playlist.addSong(newSong, pos);

            }
            // Creates new playlist for inputted artist
            else if ( input.equalsIgnoreCase("B")){
                System.out.print("Enter the artist: ");
                artist = stdin.nextLine();

                newPlaylist = playlist.getSongByArtist(playlist, artist);
                newPlaylist.printAllSongs(); // Displays new playlist

            }
            // Gets song with inputted position
            else if ( input.equalsIgnoreCase("G")){
                System.out.print("Enter the position: ");
                pos = stdin.nextInt();

                song = playlist.getSong(pos);

                if(song == null){ // Checks if position is empty
                    System.out.println("No song at position " +pos+ " to obtain.");
                }
                else { // Displaying desired song
                    playlist.heading();
                    System.out.printf(pos + "\t\t\t\t" + song.toString());
                }
            }
            // Removes song with inputted position
            else if ( input.equalsIgnoreCase("R")){
                System.out.print("Enter the position: ");
                pos = stdin.nextInt();

                playlist.removeSong(pos);

            }
            // Prints all songs from original playlist
            else if ( input.equalsIgnoreCase("P")){
                if(playlist.size() != 0) { // Checks if playlist has songs
                    playlist.printAllSongs();
                }
                else{
                    System.out.println("The playlist is empty.");
                }
            }
            // Displays size of original playlist
            else if ( input.equalsIgnoreCase("S")){
                System.out.println("There are currently " +playlist.size()
                        + " song(s) in the current playlist.");
            }
            // Caution of input is not one of the options
            else if( !input.equalsIgnoreCase("Q")){
                System.out.println("Your input is not one of the options.");
            }

        }
        System.out.println("Program terminating normally..."); // Ends Program
    }
}