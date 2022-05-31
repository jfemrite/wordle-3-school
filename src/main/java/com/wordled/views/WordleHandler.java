package com.wordled.views;

/**
 *Wordled - Runs a Text Based Wordle and returns a Score int
 *@author Drew Regnier(Edits By Ben Peroutka)
 *5/19/2022
 *@version 0.5
 *changes 2 things
 */
import java.io.*;
import java.util.*;
import com.wordled.views.MainView;
public class WordleHandler
{

    /**
     * Scanner in - Reads files
     */
    private Scanner in;
    /**
     * Holds words
     */
    private static String[] words = new String[12947];
    /**
     * The Grid of the Wordle
     */
    private String[][] grid = new String[6][5];
    /**
     * the number of guess that have been made
     */
    private int guessNumber;
    /**
     * the word that must be guessed
     */
    private static String wordToGuess;
    /**
     * is the wordle solved
     */
    private boolean solved;
    /**
     * Creates the wordle Class Function
     */
    public WordleHandler()
    {
        loadWords();
        wordToGuess = getNewWord();
    }

    /**
     * Loads words from wordlist.txt into an array
     */
    private void loadWords()
    {
        try
        {
            int i = 0;
            in = new Scanner(new File("wordlist.txt"));
            while(in.hasNext())
            {
                words[i] = in.nextLine();
                i++;
            }

        }
        catch (IOException i)
        {
            System.out.println("Error: " + i.getMessage());

        }
    }


    /**
     * !!
     * method printArray - Prints array of strings, one string per line.
     * @param arr - an Array containing any number of Strings
     */
    private void printArray(String[] arr)
    {
        for(String word : arr)
        {
            System.out.println(word);
        }
    }

    /**
     * gets the score of the game
     * @return a score based on the number of guesses used
     * 1 guess = 20 points
     * 2 guesses = 15 points
     * 3 guesses = 10 points
     * 4 guesses = 5 points
     * 5 guesses = 3 points
     * 6 guesses = 1 points
     * 6 guesses wrong = 0 points
     */
    public int calcScore(int line)
    {
        if(line == 1) { return 30; }
        else if(line == 2) { return 20; }
        else if(line == 3) { return 15; }
        else if(line == 4) { return 10; }
        else if(line == 5) { return 6; }
        else if(line == 6) { return 3; }
        else { return 0; }
    }


    /**
     * checks to see if a user guess is valid
     * @param guess a 5 letter String
     * @return either the String or null if its not a word
     */
    public static boolean checkUserGuess(String guess)
    {
        boolean isWord = false;
        for(int i = 0; i < 12947; i++)
        {
            if(guess.toLowerCase().equals(words[i]))
            {
                isWord=true;
            }
        }
        if(!isWord)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public static void submitWord(int line, String guess){
        for(int i = 0; i < 5; i++)
        {
            char temp = guess.charAt(i);
            if(guess.indexOf(temp)>-1)
            {
                MainView.setResult(line, 1, temp, guess.charAt(i));
            }
            if(guess.indexOf(temp)==wordToGuess.indexOf(temp))
            {
                MainView.setResult(line, 2, temp, guess.charAt(i));
            }
            MainView.setResult(line, 0, temp, guess.charAt(i));
        }
    }


    /**
     * getNewWord gets a random 5 letter word form wordlist
     * @return a random word from the wordlist
     */
    private String getNewWord()
    {
        int temp = (int)(Math.random()*12947);
        return words[temp];
    }



    public boolean isSolved()
    {
        return solved;
    }
}


