package dictionaryDisk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dictionaryDude {

	private static void insert(TrieContainer start, String word, String meaning) {
		word = word.trim();		//Trim for any spaces
		for (int j = 0; j < word.length(); j++) {
			char character = word.charAt(j);
			// In series, check the position of character,
			// if it is already filled then check the series of filled Tries
			// object.
			// if it is not filled then create new TrieContainer object and
			// place it at correct position, and check
			// if it is end of the word, then mark isEnd = true or else false;
			if (start.childrens[character - 97] != null) {
				if (word.length() - 1 == j) {
					// if word is found till last character, then mark the end
					// as true.
					start.childrens[character - 97].isEnd = true;
				}
				start = start.childrens[character - 97];
			} else {
				TrieContainer trie = new TrieContainer();
				trie.isEnd = (word.length() - 1 == j ? true : false);
				// direct link to meaning
				trie.meaning = meaning; // if stored meanings search will be
										// O(word.length)
				trie.word = word; // if stored it's easy to print directly

				start.childrens[character - 97] = trie;
				start = start.childrens[character - 97];
			}
		}
	}

	private static String search(TrieContainer start, String word) {
		// boolean isFound = true;
		String meaning = "";
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			// if at character position TrieContainer object is present then
			// character is found and
			// start looking for next character is word.
			if (start.childrens[character - 97] != null) {
				if (word.length() - 1 != i) {
					start = start.childrens[character - 97];
				} else {
					if (start.childrens[character - 97].isEnd) { // !
						// isFound = true; //false
						meaning = start.meaning;
					}
				}
			} else {
				// isFound = false;
				break;
			}
		}
		return meaning;
	}

	private static void displayAllWords(TrieContainer start, String toPrint) {
		if (start == null) {
			return;
		}
		if (start.isEnd) {
			System.out.println(toPrint);
			// System.out.println(start.word); if word is stored in the tries
		}
		for (int i = 0; i < start.childrens.length; i++) {
			TrieContainer t = start.childrens[i];
			if (t != null) {
				// System.out.print(toPrint + ", ");
				displayAllWords(t, toPrint + (char) (97 + i));
			}
		}
	}

	private static void buildDictionary(String file, TrieContainer trieList) throws IOException {
		String word = "";
		String meaning = "";
		String currentLine = "";
		BufferedReader buffer = null;
		buffer = new BufferedReader(new FileReader(file));
		int line = 0;
		while (((currentLine = buffer.readLine()) != null) && line <= 23202) {
			if (currentLine.indexOf("_") > 0) {
				String arr[] = currentLine.split("_");
				word = arr[0];
				meaning = arr[1];
				// if(word.equalsIgnoreCase("ses"))
				// System.out.println(meaning);
				insert(trieList, word.toLowerCase(), meaning);
				// System.out.println(currentLine.indexOf("_"));
			}
			++line;
		}
		buffer.close();
	}

	public static void main(String[] args) throws IOException {
		TrieContainer trieList = new TrieContainer();

		String file = "dictionary.txt";
		buildDictionary(file, trieList);

		displayAllWords(trieList, "");

		System.out.println(search(trieList, "agile"));
	}

}
