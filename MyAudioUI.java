// Name : MARWA KOUSAR 
// Student ID : 501159935

package assignment2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args) 
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your library
		AudioContentStore store = new AudioContentStore();
		
		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				library.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				library.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				library.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				library.listAllPlaylists(); 
			}
			else if (action.equalsIgnoreCase("DOWNLOAD")) // download contents 
			{
				int fromIndex = 0; // initialize fromIndex 
				int toIndex = 0; // initialize toIndex 
				
				System.out.print("From Store Content #: ");
				if (scanner.hasNextInt())
				{
					fromIndex = scanner.nextInt();
					scanner.nextLine(); // asks user to input fromIndex 
				}
				
				System.out.print("To Store Content #: ");
				if (scanner.hasNextInt())
				{
					toIndex = scanner.nextInt();
					scanner.nextLine(); // asks user to input toIndex 
				}
				
				for (int i = fromIndex; i <= toIndex; i++) { // iterates through the fromIndex to the toIndex
					AudioContent content = store.getContent(i); // get contents of index 
					if (content == null) {
						System.out.println("Content Not Found in Store"); // index is not found in the contents
					}
					
					else {
						try{
							library.download(content); // tries to download content, and print message 
							System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");
						}
						catch (ContentDownloadedException ex) { // prints error if content already downloaded
							System.out.println(ex.getMessage()); 
						}
					}
					
				}
									
			}
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;

				System.out.print("Song Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				// consume the nl character since nextInt() does not
					scanner.nextLine(); 
				}
				try{
					library.playSong(index);
					System.out.println();
				}
				catch (AudioContentNotFound ex) {
					System.out.println(ex.getMessage()); 
				}		
			}
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					library.printAudioBookTOC(index); // tries to print audioBook contents, calls method 
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}		
			}
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				int chapter = 0;
				System.out.print("Chapter: ");
				if (scanner.hasNextInt())
				{
					chapter = scanner.nextInt();
					scanner.nextLine();
				}
				
				try{
					library.playAudioBook(index, chapter); // tries to play AudioBooks
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}	
				catch (ChapterNotFound e) { // if chapter of audiobook not found. prints error message 
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				try{
					library.playPlaylist(title);// tries to play playlist
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}	
			}
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
				int index = 0;
        
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				System.out.print("Content Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					library.playPlaylist(title, index); // tries to play playlist
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}	
			}
			// Delete a song from the library and any play lists it belongs to
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int songNum = 0;

				System.out.print("Library Song #: ");
				if (scanner.hasNextInt())
				{
					songNum = scanner.nextInt();
					scanner.nextLine();
				}
				try{
					library.deleteSong(songNum); // tries to delete song 
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}	
			}
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				try{
					library.makePlaylist(title); // tries to make playlist 
					System.out.println();
				}
				catch (ContentDownloadedException ex) { // content already downloaded, prints error message
					System.out.println(ex.getMessage()); 
				}	
			}
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					title = scanner.nextLine();

				try{
					library.printPlaylist(title); // tries to print playlist 
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}	
			}
			// Add content from library (via index) to a playlist
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				int contentIndex = 0;
				String contentType = "";
				String playlist = "";
        
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
        
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				if (scanner.hasNextLine())
					contentType = scanner.nextLine();
				
				System.out.print("Library Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				
				try{
					library.addContentToPlaylist(contentType, contentIndex, playlist); // tries to add content to playlist 
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}	
			}
			// Delete content from play list
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				int contentIndex = 0;
				String playlist = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
				
				System.out.print("Playlist Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				try{
					library.delContentFromPlaylist(contentIndex, playlist); // tries to delete content from playlist 
					System.out.println();
				}
				catch (AudioContentNotFound ex) { // if content not found, prints error message 
					System.out.println(ex.getMessage()); 
				}		
				catch (ContentNotInPlaylist e) { // if content not in playlist, prints error message 
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				library.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				library.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				library.sortSongsByLength();
			}
			else if (action.equalsIgnoreCase("SEARCH")) // search by title
			{
				String title = "";
        
				System.out.print("Title: ");
				if (scanner.hasNextLine()) {
					title = scanner.nextLine(); // makes user enter a title
				}
				ArrayList<Integer> matches = store.searchByTitle(title); // calls the searchByTitle() method from the audioContentStore class
                if (matches.size() > 0) { // if there is at least 1 match
                    for (int index : matches) {  // iterates through each match index 
                        System.out.print(index + ". "); // prints index 
                        store.getContent(index).printInfo();  // prints content info of specified index 
                        System.out.println();  // new line 
                    }
                } else {
                    System.out.println("No matches for " + title); // if there are no matches, prints message
                }
				
			}
				
			else if (action.equalsIgnoreCase("SEARCHA")) // search by artist
			{
				String artist = "";
	        
				System.out.print("Artist: ");
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine(); // makes user enter an artist
				}
				ArrayList<Integer> matches = store.searchByArtist(artist);  // calls the searchByArtist() method from the audioContentStore class
                if (matches.size() > 0) { // if there is at least 1 match
                    for (int index : matches) {  // iterates through each match index 
                        System.out.print(index + ". "); // prints index 
                        store.getContent(index).printInfo(); // prints content info of specified index 
                        System.out.println();  // new line 
                    }	
                }
				else {
					System.out.println("No matches for " + artist); // if there are no matches, prints message
				}
			}
			
			else if (action.equalsIgnoreCase("SEARCHG")) // search by genre 
			{
				String genre = "";
        
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				if (scanner.hasNextLine()) {
					genre = scanner.nextLine();  // makes user enter a genre 
				}
				ArrayList<Integer> matches = store.searchByGenre(genre);  // calls the searchByGenre() method from the audioContentStore class
                if (matches.size() > 0) { // if there is at least 1 match
                    for (int index : matches) { // iterates through each match index 
                        System.out.print(index + ". ");  // prints index 
                        store.getContent(index).printInfo(); // prints content info of specified index 
                        System.out.println(); // new line 
                    }	
                }
				else {
					System.out.println("No matches for " + genre); // if there are no matches, prints message
				}
			}
			
			else if (action.equalsIgnoreCase("DOWNLOADA")) // download by artist
			{
				String artist = "";
        
				System.out.print("Artist Name: ");
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine(); // makes user enter an artist
				}
		
				ArrayList<Integer> matches = store.searchByArtist(artist); // calls the searchByArtist() method from the audioContentStore class
                if (matches.size() > 0) { // if there is at least 1 match
                    for (int index : matches) { // iterates through each match index 
                    	AudioContent content = store.getContent(index); // gets content for each match 
                    	if (content == null) {
                    		System.out.println("Content Not Found in Store"); // content does not exist for that index 
                    	}
					
                    	else {
                    		try{
                    			library.download(content); // tries to download the content 
                    			System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");
                    		}
                    		catch (ContentDownloadedException ex) { // if already downloaded, prints error message 
                    			System.out.println(ex.getMessage()); 
                    		}
                    	}
					
                    }
                }
				
			}
			else if (action.equalsIgnoreCase("DOWNLOADG")) // download by genre 
			{
				String genre = "";
        
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				if (scanner.hasNextLine()) { // makes user enter a genre 
					genre = scanner.nextLine();
				}
				ArrayList<Integer> matches = store.searchByGenre(genre); // calls the searchByGenre() method from the audioContentStore class
                if (matches.size() > 0) { // if there is at least 1 match
                    for (int index : matches) { // iterates through each match index 
                    	AudioContent content = store.getContent(index); // gets content for each match 
                    	if (content == null) { 
                    		System.out.println("Content Not Found in Store"); // content does not exist for that index 
                    	}
                    	else {
                    		try{
                    			library.download(content); // tries to download the content 
                    			System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");
                    		}
                    		catch (ContentDownloadedException ex) { // if already downloaded, prints error message 
                    			System.out.println(ex.getMessage()); 
                    		}
                    	}
					
                    }
                }
				
			}
			
			System.out.print("\n>");
		}
	}
}
