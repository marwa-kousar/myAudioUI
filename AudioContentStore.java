// Name : MARWA KOUSAR 
// Student ID : 501159935

package assignment2;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.*;

//Simulation of audio content in an online store
//The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>(); // initializes an empty arraylist for the contents
			contents = readAudioContents("store.txt"); // calls the method readAudioContents and passes the parameter 
	      
		}
			
			
			private ArrayList<AudioContent> readAudioContents(String fileName) {
			    
				ArrayList<AudioContent> contents = new ArrayList<AudioContent>();
			
				try {
					
					File file = new File(fileName); // creates a new file with the parameter fileName
					Scanner scanner = new Scanner(file); // new scanner object to read from the file
					
					while (scanner.hasNextLine()) {  // loops through the file reading each line
						String type = scanner.nextLine(); // initializes the first line, which indicates the type
						
						if (type.equals("SONG")) { // if the first line is the keyword SONG
							String id = scanner.nextLine(); // The 2nd – 8th lines contain the strings/ints id, title, year, length, artist, composer, genre
							String title = scanner.nextLine();
							int year = Integer.parseInt(scanner.nextLine());
							int length = Integer.parseInt(scanner.nextLine());
							String artist = scanner.nextLine();
							String composer = scanner.nextLine();
							Song.Genre genre  = Song.Genre.valueOf(scanner.nextLine());
							int numLines = Integer.parseInt(scanner.nextLine()); // the 9th line contains the number of lines of lyrics
	 	                
							String lyrics = ""; // empty string to store the lyrics
							for (int i = 0; i < numLines; i++) { // iterates each lyric line to add to the string
								lyrics += scanner.nextLine() + "\n"; // actual lyric lines after the number of lines of lyrics
							}
	                    
	                    
							contents.add(new Song(title, year, id, type, lyrics, length, artist, composer, genre, lyrics)); // adds a new Song object to the contents arraylist with its parameters
						}
						
						else if (type.equals("AUDIOBOOK")) { // if the first line is the keyword AUDIOBOOK
							String id = scanner.nextLine(); //  The 2nd – 7th lines contain the strings/ints id, title, year, length, author, narrator
							String title = scanner.nextLine();
							int year = Integer.parseInt(scanner.nextLine());
							int length = Integer.parseInt(scanner.nextLine());
							String author = scanner.nextLine();
							String narrator = scanner.nextLine();
							int numChapters = Integer.parseInt(scanner.nextLine()); // The 8th line contains the number of chapters
	 	                
				
							ArrayList<String> chapterTitles = new ArrayList<String>(); // new chapterTitles arraylist to store the chapter titles
							ArrayList<String> chapter = new ArrayList<String>(); // new chapter arraylist to store the chapter
	                    
							for (int i = 0; i < numChapters; i++) { // iterates through each chapter title line to add to the arraylist
								chapterTitles.add(scanner.nextLine()); // the number of chapters is followed by the chapter titles 
							}
							
							int numLines = Integer.parseInt(scanner.nextLine()); // after the chapter titles is a line containing the number of lines of a chapter
							String text = ""; // initialize empty string to store chapter text
							for (int j = 0; j < numLines; j++) { // iterates through each chapter line to add to the string
								text += scanner.nextLine() + "\n"; // after the number of lines of a chapter, is followed by the chapter lines
							}
	                        
							chapter.add(text); // adds the string text to the arraylist chapter
							
							contents.add(new AudioBook(title, year, id, type, "", length, author, narrator, chapterTitles, chapter)); // adds a new AudioBook object to the contents arraylist with its parameters
						}
					}
					scanner.close(); // closes the scanner object
				}
				catch (IOException e) { // if the text file is not found, prints an error message indicating that
					System.out.println(fileName + " not found");
					System.exit(1);
				}
				
				return contents; // returns the arraylist of contents
			}
		
		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print(index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		public ArrayList<Integer> searchByTitle(String title) { // method that searches audiocontent by title
			HashMap<String, Integer> titleMap = new HashMap<String, Integer>(); // a new hashmap that maps each title to its index in the contents arraylist
			
		    for (int i = 0; i < contents.size(); i++) { // iterates through the contents arraylist 
		        AudioContent content = contents.get(i);
		        titleMap.put(content.getTitle(), i + 1); // adds each element to the titleMap
		    }
		    
		    ArrayList<Integer> matches = new ArrayList<Integer>(); // new arraylist to store the matching indices
		    if (titleMap.containsKey(title)) { // checks if the map contains the title
		        matches.add(titleMap.get(title)); // if it does, then adds the index to the matches arraylist
		    }
		    return matches; // returns the matches arraylist
		}
		
		public ArrayList<Integer> searchByArtist(String artist) { // method that searches audiocontent by artist
			HashMap<String, ArrayList<Integer>> artistMap = new HashMap<String, ArrayList<Integer>>(); // a new hashmap that maps each artist to its index in the contents arraylist
			
		    for (int i = 0; i < contents.size(); i++) { // iterates through the contents arraylist 
		        AudioContent content = contents.get(i);
		        if (content instanceof Song) { // if the content is of object Song 
		            Song song = (Song) content;
		            String songArtist = song.getArtist(); // gets artist
		            if (!artistMap.containsKey(songArtist)) { // if the map does not contain the artist 
		                artistMap.put(songArtist, new ArrayList<Integer>()); // then it will have another one with an empty list 
		            }
		            artistMap.get(songArtist).add(i + 1); // if the map does contain the artist, it adds the artist and its index to the map
		        }
		        else if (content instanceof AudioBook) { // if the content is of object AudioBook
		            AudioBook audiobook = (AudioBook) content;
		            String audiobookAuthor = audiobook.getAuthor(); // gets author
		            if (!artistMap.containsKey(audiobookAuthor)) { // if the map does not contain the author
		                artistMap.put(audiobookAuthor, new ArrayList<Integer>()); // then it will have another one with an empty list 
		            }
		            artistMap.get(audiobookAuthor).add(i + 1); // if the map does contain the author, it adds the author and its index to the map
		        }
		    }
		    
		    ArrayList<Integer> matches = new ArrayList<Integer>(); // new arraylist to store the matching indices
		    if (artistMap.containsKey(artist)) { // checks if the map contains the artist
		        matches.addAll(artistMap.get(artist)); // if it does, then adds the index to the matches arraylist
		    }
		    return matches; // returns the matches arraylist
		}
		
		public ArrayList<Integer> searchByGenre(String genre) { // method that searches audiocontent by genre
			HashMap<String, ArrayList<Integer>> genreMap = new HashMap<String, ArrayList<Integer>>(); // a new hashmap that maps each genre to its index in the contents arraylist
			
		    for (int i = 0; i < contents.size(); i++) { // iterates through the contents arraylist 
		        AudioContent content = contents.get(i);
		        if (content instanceof Song) { // if the content is of object Song 
		            Song song = (Song) content;
		            Song.Genre songGenre = song.getGenre(); // gets genre
		            String genreStr = songGenre.toString(); // converts enum type to String
		            if (!genreMap.containsKey(genreStr)) { // if the map does not contain the genre
		                genreMap.put(genreStr, new ArrayList<Integer>()); // then it will have another one with an empty list 
		            }
		            genreMap.get(genreStr).add(i + 1); // if the map does contain the artist, it adds the genre and its index to the map
		        }
		    }
		    
		    ArrayList<Integer> matches = new ArrayList<Integer>(); // new arraylist to store the matching indices
		    if (genreMap.containsKey(genre)) { // checks if the map contains the genre
		        matches.addAll(genreMap.get(genre)); // if it does, then adds the index to the matches arraylist
		    }
		    return matches; // returns the matches arraylist
		}
		
		
}
		
		
