// Name : MARWA KOUSAR 
// Student ID : 501159935
package assignment2;
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		for (int i = 0; i < contents.size(); i++) // iterates through contents list
		{
			int index = i + 1; // sets index to 1
			System.out.print("" + index + ". "); // formats as follows "1. "
			contents.get(i).printInfo(); // calls printInfo() method 
			System.out.println();	 // new line 
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for (int i = 0; i < contents.size(); i++) { 
			AudioContent content = contents.get(i);
			content.play(); // calls the play() method to play contents
			System.out.println();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if (index < 1 || index > contents.size())
		{
			System.out.print("Invalid Index");
		}
		
		contents.get(index-1).play();
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		if (other instanceof Playlist) { // checks if the object other, is an instance of the Playlist class
			Playlist playlist = (Playlist) other; // makes the object other into a Playlist object
			return super.equals(playlist) && title == playlist.title; // checks if the audio content information and the title (object of Playlist) are equal  
		}
		else { // if not an instance of the AudioBook class
			return false;
		}
		
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
