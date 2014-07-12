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

import com.wordnik.client.api.*;
import com.wordnik.client.model.*;

import com.wordnik.client.common.ApiException;

import java.util.List;

public class App {
  public static void main(String[] args) {
    if(args.length == 0) {
      System.out.println("Pass your API key as an argument");
      System.exit(0);
    }
    String key = args[0];

    try {
      WordApi api = new WordApi();
      api.getInvoker().addDefaultHeader("api_key", key);
      List<Definition> definitions = api.getDefinitions(
        "Cat",         //  word
        "noun",        //  only get definitions which are "nouns"
        "wiktionary",  //  use wiktionary
        3,             //  fetch only 3 results max
        "true",        //  return related words
        "true",        //  fetch the canonical version of this word (Cat => cat)
        "false"        //  return XML mark-up in response
      );

      for(Definition def : definitions) {
        System.out.print(def);
      }
    }
    catch (ApiException e) {
      e.printStackTrace();
    }
  }  
}