import java.util.*;

public class Playlist {

    Scanner stdin = new Scanner(System.in);

    final static int MAX_SONGS = 50; //Maximum amount of songs in playlist
    int numSongs = 0; // Initial amount of songs in playlist
    int nextEmptySpot = -1; // Variable to find next null in playlist
    int nextSpot; // Variable to find next spot
    boolean playlistFull = false; // Indication for full playlist
    SongRecord [] sr; // Creation of playlist

    public Playlist(){
        sr =  new SongRecord[MAX_SONGS];
    }

    /**
     * Clone the original playlist
     * @return a clone of the original playlist
     */
    public Playlist clone() {
        Playlist clone = new Playlist();
        for(int i = 0; i < MAX_SONGS; i ++){
            clone.addSong(sr[i],i+1);
        }
        return clone;
    }


    /**
     * Compares original playlist with another playlist
     * @param obj secondary playlist
     * @return true if playlists are equal, false if not
     */
    public boolean equals(Object obj) {
        if (obj instanceof Playlist) {
            Playlist playlistCompare = (Playlist) obj;
            for (int i = 0; i < numSongs; i++) {
                if (playlistCompare.getSongByPos(i) != null && !playlistCompare.getSongByPos(i).equals(sr[i])) {
                    return false;
                }
                if (playlistCompare.getArtistByPos(i) != null && !playlistCompare.getArtistByPos(i).equals(sr[i].getArtist())) {
                    return false;
                }
                if ( playlistCompare.getMinutesByPos(i) != -1 && playlistCompare.getMinutesByPos(i) != sr[i].getMinutes()) {
                    return false;
                }
                if(playlistCompare.getSecondsByPos(i) != -1 && playlistCompare.getSecondsByPos(i) != sr[i].getSeconds()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     *  To receive the amount of songs in the playlist
     * @return number of songs
     */
    public int size() {
        return numSongs;
    }

    /**
     * Adds a song into the playlist
     * @param Song Song to insert into playlist
     * @param pos position to insert song into
     * @throws IllegalArgumentException
     * When position inputted exceeds the maximum amount of songs
     * @throws  FullPlaylistException
     * When playlist is full
     */
    public void addSong(SongRecord Song, int pos){
        playlistFull = false;
        try { // Makes sure that position is valid.
            if (pos > MAX_SONGS) {
                throw new IllegalArgumentException("The position inserted is invalid.");
            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Position is out of range. Action is not performed.");
            return;
        }

        // Inserts song into playlist if position is empty
        if(sr[pos - 1] == null && pos <= MAX_SONGS) {
            sr[pos - 1] = Song;
            System.out.println("Song Added: " +Song.getTitle()+ " By " +Song.getArtist());
            numSongs++; // Adds counter to amount of songs
        }
        else {

            try { // Makes sure playlist is empty
                if (numSongs == MAX_SONGS) {
                    playlistFull = true;
                    throw new FullPlaylistException(("The playlist is full."));
                }
            } catch (FullPlaylistException e) {
                System.out.println("The current playlist is full.");
            }
            // Finds pivot location if position is not empty
            for (int i = pos - 1; i < MAX_SONGS; i++) {
                if (sr[i] == null) {
                    nextEmptySpot = i;
                    break;
                }
            }
            // Pivots songs to the right by one to fit new song into occupied position
            if (numSongs != MAX_SONGS) {
                for (int i = nextEmptySpot; i >= pos; i--) {
                    sr[i] = sr[i - 1];
                }
                sr[pos - 1] = Song;
                System.out.println("Song Added: " +Song.getTitle()+ " By " +Song.getArtist());
                numSongs++; // Adds counter to amount of songs
            }
        }

            simplifyPlaylist(); // Makes sure there is no gaps in playlist
        }

    /**
     * Removes a song from the playlist
     * @param pos position of song to remove
     * @throws IllegalArgumentException
     * When position inputted exceeds the maximum amount of songs
     */
    public void removeSong(int pos){

        try { // Makes sure that position is valid.
            if (pos > MAX_SONGS) {
                throw new IllegalArgumentException("The position inserted is invalid.");
            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Position is out of range. Action is not performed.");
            return;
        }

        if(sr[pos - 1] == null){ // Checks to confirm if song is at given position
            System.out.println("No song at position " +pos+ " to remove.");

        }
        else { // Removes song with given position
            sr[pos - 1] = null;
            System.out.println("Song removed at position " +pos);
            nextSpot = pos - 1;

            // Pivots all songs to the left to avoid gaps after removal
            for(int i = nextSpot; i < MAX_SONGS; i++){
                if(sr[i] != null){
                    sr[nextSpot] = sr[i];
                    sr[i] = null;
                    nextSpot++;
                }
            }

        }
        numSongs--; // Subtract counter to amount of songs
    }

    /**
     * Gets the song from given position
     * @param pos position of song to retrieve
     * @return Song from given position
     * @throws IllegalArgumentException
     * When position inputted exceeds the maximum amount of songs
     */
    public SongRecord getSong(int pos) {
        int backup = pos;

        try { // Makes sure that position is valid.
            if (pos > MAX_SONGS) {
                throw new IllegalArgumentException("The position inserted is invalid.");
            }
            if (sr[backup - 1] == null) { // Check to see if position has a song
                return null;
            } else {
                return sr[backup - 1];

            }
        }
        catch(IllegalArgumentException i){
            System.out.println("Position is out of range.");
        }
        return null;
    }

    /**
     * Displays all songs in playlist
     */
    public void printAllSongs() {
        heading();
        System.out.print(toString());
    }

    /**
     * Creates new playlist with all songs from given artist
     * @param originalList playlist to take songs from
     * @param artist artist whose songs that will be added to new playlist
     * @return playlist with all songs in original playlist from given artist
     */
    public static Playlist getSongByArtist(Playlist originalList, String artist){
        int newPlaylistPos = 1;
        Playlist newPlaylist = new Playlist(); // Creates new playlist

        for(int i = 1; i <= MAX_SONGS; i++) {
            if (originalList.getArtistByPos(i-1) != null
                    && originalList.getArtistByPos(i-1).equals(artist)) {
                newPlaylist.addSong(originalList.getSongByPos(i-1), newPlaylistPos);
                newPlaylistPos++;
            }
        }
        return newPlaylist;
    }

    /**
     * Create string that displays all songs from original playlist
     * @return information from every song
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < MAX_SONGS; i++) {
            if (sr[i] != null) {
                result +=  i+1 + "\t\t\t\t" + sr[i].toString() + "";
            }
        }
        return result;
    }

    /**
     *  Displays heading when needed to display song information
     */
    public void heading(){
        System.out.println("Song#\t\t\tTitle\t\t\tArtist\t\t\t\tLength");
        System.out.println("------------------------------------" +
                "----------------------------------------");
    }

    /**
     * Gets artist by position
     * @param pos position of artist desired to be received information on
     * @return artist from given position
     */
    public String getArtistByPos(int pos) {
        if(pos < numSongs){
            return sr[pos].getArtist();
        }
        return null;
    }

    /**
     * Gets song by position
     * @param pos position of song desired to be received information on
     * @return song from given position
     */
    public SongRecord getSongByPos(int pos) {
        if(pos < numSongs){
            return sr[pos];
        }
        return null;
    }

    /**
     * Removes gaps from playlist by pivoting to the left
     */
    public void simplifyPlaylist(){
        for(int i = 0; i < MAX_SONGS - 1; i++){
            if(sr[i] == null) // Checks which positions are empty
                for(int j = i+1; j < MAX_SONGS; j++){
                    if(sr[j] != null){
                        sr[i] = sr[j];
                        sr[j] = null;
                    }
                }
        }
    }

    /**
     * Gets minutes by position
     * @param pos position of song desired to be received information on
     * @return duration of song in minutes
     */
    public int getMinutesByPos(int pos){
        if(pos < numSongs){
            return sr[pos].getMinutes();
        }
        else{
            return -1;
            }
        }

    /**
     * Gets seconds of song by position
      * @param pos position of song desired to be received information on
     * @return duration of song in seconds
     */
    public int getSecondsByPos(int pos){
        if(pos < numSongs){
            return sr[pos].getSeconds();
        }
        else{
            return -1;
        }
    }

}