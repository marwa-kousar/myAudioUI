// Name : MARWA KOUSAR 
// Student ID : 501159935
package assignment2;

/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable<Song>// implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 
		
		super(title, year, id, type, audioFile, length); // I DID
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
		
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo() //  changed
	{
		super.printInfo();
		System.out.println( "Artist: " + artist + " Composer: " + composer + " Genre: " + genre);
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		super.setAudioFile(lyrics); // sets the audioFile from the superclass to the lyrics string 
		super.play(); // calls the play() method from the superclass
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{
		if (other instanceof Song) { // checks if the object other, is an instance of the Song class
			Song song = (Song) other; // makes the object other into a Song object
			return super.equals(song) && composer == song.composer && artist == song.artist; // checks if the audio content information and the composer and artist (objects of Song) are equal  
		}
		else { // if not an instance of the AudioBook class
			return false;
		}
		
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other) // implements the comparable interface for the Song class
	{
		int compare = this.getTitle().compareTo(other.getTitle()); // compares two song objects based on their titles
		
		if (compare < 0) { // current song title is less than the other song title
			return -1;
		}
		else if (compare > 0) { // current song title is greater than the other song title
			return 1;
		}
		return 0; // both are the same
	}
}
