package com.grovelet.masnoon.dua2017.masnoondua2017;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iamla on 10/4/2016.
 */
public class ArabicUtilities {
    private static final String FONTS_LOCATION_PATH = "fonts/DejaVuSans.ttf";
    static Typeface face;

    private static boolean isArabicCharacter(char target) {
        for (char[] cArr : ArabicReshaper.ARABIC_GLPHIES) {
            if (cArr[0] == target) {
                return true;
            }
        }
        for (char c : ArabicReshaper.HARAKATE) {
            if (c == target) {
                return true;
            }
        }
        return false;
    }

    private static String[] getWords(String sentence) {
        if (sentence != null) {
            return sentence.split("\\s");
        }
        return new String[0];
    }

    public static boolean hasArabicLetters(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (isArabicCharacter(word.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isArabicWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!isArabicCharacter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String[] getWordsFromMixedWord(String word) {
        List<String> finalWords = new ArrayList();
        String tempWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (isArabicCharacter(word.charAt(i))) {
                if (tempWord.equals("") || isArabicWord(tempWord)) {
                    tempWord = new StringBuilder(String.valueOf(tempWord)).append(word.charAt(i)).toString();
                } else {
                    finalWords.add(tempWord);
                    tempWord = String.valueOf(word.charAt(i));
                }
            } else if (tempWord.equals("") || !isArabicWord(tempWord)) {
                tempWord = new StringBuilder(String.valueOf(tempWord)).append(word.charAt(i)).toString();
            } else {
                finalWords.add(tempWord);
                tempWord = String.valueOf(word.charAt(i));
            }
        }
        if (!tempWord.equals("")) {
            finalWords.add(tempWord);
        }
        return (String[]) finalWords.toArray(new String[finalWords.size()]);
    }

    public static String reshape(String allText) {
        if (allText == null) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        String[] sentences = allText.split("\n");
        for (int i = 0; i < sentences.length; i++) {
            result.append(reshapeSentence(sentences[i]));
            if (i < sentences.length - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static String reshapeSentence(String sentence) {
        String[] words = getWords(sentence);
        StringBuffer reshapedText = new StringBuffer("");
        for (int i = 0; i < words.length; i++) {
            if (!hasArabicLetters(words[i])) {
                reshapedText.append(words[i]);
            } else if (isArabicWord(words[i])) {
                reshapedText.append(new ArabicReshaper(words[i]).getReshapedWord());
            } else {
                String[] mixedWords = getWordsFromMixedWord(words[i]);
                for (String arabicReshaper : mixedWords) {
                    reshapedText.append(new ArabicReshaper(arabicReshaper).getReshapedWord());
                }
            }
            if (i < words.length - 1) {
                reshapedText.append(" ");
            }
        }
        return reshapedText.toString();
    }

    public static TextView getArabicEnabledTextView(Context context, TextView targetTextView) {
        if (face == null) {
            face = Typeface.createFromAsset(context.getAssets(), FONTS_LOCATION_PATH);
        }
        targetTextView.setTypeface(face);
        targetTextView.setGravity(5);
        return targetTextView;
    }
}

