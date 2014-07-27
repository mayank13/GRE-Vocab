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
import java.util.StringTokenizer;

import com.wordnik.client.api.WordApi;
import com.wordnik.client.common.ApiException;
import com.wordnik.client.model.Definition;
import com.wordnik.client.model.Example;

public class App {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Pass your API key as an argument");
			System.exit(0);
		}
		String key = args[0];

		List<String> wordList = new ArrayList<String>();
		// Intial try to make a batch request to Word Nik API by Looping

		// Difficult High Frequency words
		
		String str= "abstain ;ascetic ;austere ;austere ;banality ;brazen ;concede ;concede ;conspicuous ;contingent ;contrive ;culpability ;deferential ;derive ;dictatorial ;diffident ;dilatory ;disaffected ;duress ;engender ;erudite ;eschew ;fastidious ;forthcoming ;fortuitous ;frivolous ;gainsay ;gall ;gall ;glut ;glut ;gossamer ;harangue ;ignominious ;impertinent ;impudent ;inexorable ;intransigent ;lambast ;languid ;largess ;mendacity ;mitigate ;mundane ;nonplussed ;nuance ;obscure ;pejorative ;perfidy ;placate ;poignant ;polemic ;posit ;predilection ;profligate ;propitious ;provincial ;qualify ;querulous ;rebuke ;reconcile ;recondite ;reproach ;repudiate ;rescind ;reticent ;sanction ;specious ;specious ;subsume ;subsume ;sullen ;superfluous ;superfluous ;treacherous ;treacherous";
		
		StringTokenizer tokens = new StringTokenizer(str, ";");
		
		System.out.println("Number of Tokens --->>"+tokens.countTokens());
		while(tokens.hasMoreElements()){
			wordList.add(tokens.nextToken().trim());
		}

	/*	wordList.add("abase");
		wordList.add("vehement");
		wordList.add("prodigy");
		wordList.add("prodigal");
		wordList.add("azure");*/

		try {
			WordApi api = new WordApi();
			
			api.getInvoker().addDefaultHeader("api_key", key);

			for (int i = 0; i < 5; i++) {
				List<Definition> definitions = api.getDefinitions(wordList.get(i), // word
						"", // only get definitions which are "nouns"
						"", // use wiktionary
						3, // fetch only 3 results max
						"true", // return related words
						"true", // fetch the canonical version of this word (Cat
								// =>
								// cat)
						"false" // return XML mark-up in response
				);
				
			Example topExample = api.getTopExample(wordList.get(i).toString(), "false");
				for (Definition def : definitions) {
					System.out.println("ord-->>"+wordList.get(i));
					System.out.println(def.getText());
					System.out.println(topExample.getText());
					System.out.println("------------------------");
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
}