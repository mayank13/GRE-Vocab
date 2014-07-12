package com.mayank.vocab.wordnik;

/**
 * Hello world!
 *
 */
/*public class App 
 {
 public static void main( String[] args )
 {
 System.out.println( "Hello World!" );
 }
 }*/

import java.util.ArrayList;
import java.util.List;

import com.wordnik.client.api.WordApi;
import com.wordnik.client.common.ApiException;
import com.wordnik.client.model.Definition;

public class App {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Pass your API key as an argument");
			System.exit(0);
		}
		String key = args[0];

		List<String> wordList = new ArrayList<String>();
		// Intial try to make a batch request to Word Nik API by Looping

		wordList.add("abase");
		wordList.add("vehement");
		wordList.add("prodigy");
		wordList.add("prodigal");
		wordList.add("azure");

		try {
			WordApi api = new WordApi();
			api.getInvoker().addDefaultHeader("api_key", key);

			for (int i = 0; i < wordList.size(); i++) {
				List<Definition> definitions = api.getDefinitions(wordList.get(i), // word
						"", // only get definitions which are "nouns"
						"wiktionary", // use wiktionary
						3, // fetch only 3 results max
						"true", // return related words
						"true", // fetch the canonical version of this word (Cat
								// =>
								// cat)
						"false" // return XML mark-up in response
				);

				for (Definition def : definitions) {
					System.out.print(def);
					System.out.println("------------------------");
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
}