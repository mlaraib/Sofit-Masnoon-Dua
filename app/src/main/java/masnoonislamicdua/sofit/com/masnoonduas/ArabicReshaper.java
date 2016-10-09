package masnoonislamicdua.sofit.com.masnoonduas;

/**
 * Created by iamla on 10/4/2016.
 */
public class ArabicReshaper {
    public static char[][] ARABIC_GLPHIES;
    public static char DEFINED_CHARACTERS_ORGINAL_ALF;
    public static char DEFINED_CHARACTERS_ORGINAL_ALF_LOWER_HAMAZA;
    public static char DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_HAMAZA;
    public static char DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_MDD;
    public static char DEFINED_CHARACTERS_ORGINAL_LAM;
    public static char[] HARAKATE;
    public static char[][] LAM_ALEF_GLPHIES;
    private String _returnString;

    class DecomposedWord {
        int[] harakatesPositions;
        int[] lettersPositions;
        char[] stripedHarakates;
        char[] stripedRegularLetters;

        DecomposedWord(String unshapedWord) {
            int index;
            int wordLength = unshapedWord.length();
            int harakatesCount = 0;
            for (index = 0; index < wordLength; index++) {
                if (ArabicReshaper.this.isHaraka(unshapedWord.charAt(index))) {
                    harakatesCount++;
                }
            }
            this.harakatesPositions = new int[harakatesCount];
            this.stripedHarakates = new char[harakatesCount];
            this.lettersPositions = new int[(wordLength - harakatesCount)];
            this.stripedRegularLetters = new char[(wordLength - harakatesCount)];
            harakatesCount = 0;
            int letterCount = 0;
            for (index = 0; index < unshapedWord.length(); index++) {
                if (ArabicReshaper.this.isHaraka(unshapedWord.charAt(index))) {
                    this.harakatesPositions[harakatesCount] = index;
                    this.stripedHarakates[harakatesCount] = unshapedWord.charAt(index);
                    harakatesCount++;
                } else {
                    this.lettersPositions[letterCount] = index;
                    this.stripedRegularLetters[letterCount] = unshapedWord.charAt(index);
                    letterCount++;
                }
            }
        }

        String reconstructWord(String reshapedWord) {
            int index;
            char[] wordWithHarakates = new char[(reshapedWord.length() + this.stripedHarakates.length)];
            for (index = 0; index < this.lettersPositions.length; index++) {
                wordWithHarakates[this.lettersPositions[index]] = reshapedWord.charAt(index);
            }
            for (index = 0; index < this.harakatesPositions.length; index++) {
                wordWithHarakates[this.harakatesPositions[index]] = this.stripedHarakates[index];
            }
            return new String(wordWithHarakates);
        }
    }

    public String getReshapedWord() {
        return this._returnString;
    }

    static {
        DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_MDD = '\u0622';
        DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_HAMAZA = '\u0623';
        DEFINED_CHARACTERS_ORGINAL_ALF_LOWER_HAMAZA = '\u0625';
        DEFINED_CHARACTERS_ORGINAL_ALF = '\u0627';
        DEFINED_CHARACTERS_ORGINAL_LAM = '\u0644';
        LAM_ALEF_GLPHIES = new char[][]{new char[]{'\u3ba6', '\ufef6', '\ufef5'}, new char[]{'\u3ba7', '\ufef8', '\ufef7'}, new char[]{'\u0627', '\ufefc', '\ufefb'}, new char[]{'\u0625', '\ufefa', '\ufef9'}};
        HARAKATE = new char[]{'\u0600', '\u0601', '\u0602', '\u0603', '\u0606', '\u0607', '\u0608', '\u0609', '\u060a', '\u060b', '\u060d', '\u060e', '\u0610', '\u0611', '\u0612', '\u0613', '\u0614', '\u0615', '\u0616', '\u0617', '\u0618', '\u0619', '\u061a', '\u061b', '\u061e', '\u061f', '\u0621', '\u063b', '\u063c', '\u063d', '\u063e', '\u063f', '\u0640', '\u064b', '\u064c', '\u064d', '\u064e', '\u064f', '\u0650', '\u0651', '\u0652', '\u0653', '\u0654', '\u0655', '\u0656', '\u0657', '\u0658', '\u0659', '\u065a', '\u065b', '\u065c', '\u065d', '\u065e', '\u0660', '\u066a', '\u066b', '\u066c', '\u066f', '\u0670', '\u0672', '\u06d4', '\u06d5', '\u06d6', '\u06d7', '\u06d8', '\u06d9', '\u06da', '\u06db', '\u06dc', '\u06df', '\u06e0', '\u06e1', '\u06e2', '\u06e3', '\u06e4', '\u06e5', '\u06e6', '\u06e7', '\u06e8', '\u06e9', '\u06ea', '\u06eb', '\u06ec', '\u06ed', '\u06ee', '\u06ef', '\u06d6', '\u06d7', '\u06d8', '\u06d9', '\u06da', '\u06db', '\u06dc', '\u06dd', '\u06de', '\u06df', '\u06f0', '\u06fd', '\ufe70', '\ufe71', '\ufe72', '\ufe73', '\ufe74', '\ufe75', '\ufe76', '\ufe77', '\ufe78', '\ufe79', '\ufe7a', '\ufe7b', '\ufe7c', '\ufe7d', '\ufe7e', '\ufe7f', '\ufc5e', '\ufc5f', '\ufc60', '\ufc61', '\ufc62', '\ufc63'};
        ARABIC_GLPHIES = new char[][]{new char[]{'\u0622', '\ufe81', '\ufe81', '\ufe82', '\ufe82', '\u0002'}, new char[]{'\u0623', '\ufe83', '\ufe83', '\ufe84', '\ufe84', '\u0002'}, new char[]{'\u0624', '\ufe85', '\ufe85', '\ufe86', '\ufe86', '\u0002'}, new char[]{'\u0625', '\ufe87', '\ufe87', '\ufe88', '\ufe88', '\u0002'}, new char[]{'\u0626', '\ufe89', '\ufe8b', '\ufe8c', '\ufe8a', '\u0004'}, new char[]{'\u0627', '\u0627', '\u0627', '\ufe8e', '\ufe8e', '\u0002'}, new char[]{'\u0628', '\ufe8f', '\ufe91', '\ufe92', '\ufe90', '\u0004'}, new char[]{'\u0629', '\ufe93', '\ufe93', '\ufe94', '\ufe94', '\u0002'}, new char[]{'\u062a', '\ufe95', '\ufe97', '\ufe98', '\ufe96', '\u0004'}, new char[]{'\u062b', '\ufe99', '\ufe9b', '\ufe9c', '\ufe9a', '\u0004'}, new char[]{'\u062c', '\ufe9d', '\ufe9f', '\ufea0', '\ufe9e', '\u0004'}, new char[]{'\u062d', '\ufea1', '\ufea3', '\ufea4', '\ufea2', '\u0004'}, new char[]{'\u062e', '\ufea5', '\ufea7', '\ufea8', '\ufea6', '\u0004'}, new char[]{'\u062f', '\ufea9', '\ufea9', '\ufeaa', '\ufeaa', '\u0002'}, new char[]{'\u0630', '\ufeab', '\ufeab', '\ufeac', '\ufeac', '\u0002'}, new char[]{'\u0631', '\ufead', '\ufead', '\ufeae', '\ufeae', '\u0002'}, new char[]{'\u0632', '\ufeaf', '\ufeaf', '\ufeb0', '\ufeb0', '\u0002'}, new char[]{'\u0633', '\ufeb1', '\ufeb3', '\ufeb4', '\ufeb2', '\u0004'}, new char[]{'\u0634', '\ufeb5', '\ufeb7', '\ufeb8', '\ufeb6', '\u0004'}, new char[]{'\u0635', '\ufeb9', '\ufebb', '\ufebc', '\ufeba', '\u0004'}, new char[]{'\u0636', '\ufebd', '\ufebf', '\ufec0', '\ufebe', '\u0004'}, new char[]{'\u0637', '\ufec1', '\ufec3', '\ufec4', '\ufec2', '\u0004'}, new char[]{'\u0638', '\ufec5', '\ufec7', '\ufec8', '\ufec6', '\u0004'}, new char[]{'\u0639', '\ufec9', '\ufecb', '\ufecc', '\ufeca', '\u0004'}, new char[]{'\u063a', '\ufecd', '\ufecf', '\ufed0', '\ufece', '\u0004'}, new char[]{'\u0641', '\ufed1', '\ufed3', '\ufed4', '\ufed2', '\u0004'}, new char[]{'\u0642', '\ufed5', '\ufed7', '\ufed8', '\ufed6', '\u0004'}, new char[]{'\u0643', '\ufed9', '\ufedb', '\ufedc', '\ufeda', '\u0004'}, new char[]{'\u0644', '\ufedd', '\ufedf', '\ufee0', '\ufede', '\u0004'}, new char[]{'\u0645', '\ufee1', '\ufee3', '\ufee4', '\ufee2', '\u0004'}, new char[]{'\u0646', '\ufee5', '\ufee7', '\ufee8', '\ufee6', '\u0004'}, new char[]{'\u0647', '\ufee9', '\ufeeb', '\ufeec', '\ufeea', '\u0004'}, new char[]{'\u0648', '\ufeed', '\ufeed', '\ufeee', '\ufeee', '\u0002'}, new char[]{'\u0649', '\ufeef', '\ufeef', '\ufef0', '\ufef0', '\u0002'}, new char[]{'\u0671', '\u0671', '\u0671', '\ufb51', '\ufb51', '\u0002'}, new char[]{'\u064a', '\ufef1', '\ufef3', '\ufef4', '\ufef2', '\u0004'}, new char[]{'\u066e', '\ufbe4', '\ufbe8', '\ufbe9', '\ufbe5', '\u0004'}, new char[]{'\u0671', '\u0671', '\u0671', '\ufb51', '\ufb51', '\u0002'}, new char[]{'\u06aa', '\ufb8e', '\ufb90', '\ufb91', '\ufb8f', '\u0004'}, new char[]{'\u06c1', '\ufba6', '\ufba8', '\ufba9', '\ufba7', '\u0004'}, new char[]{'\u06e4', '\u06e4', '\u06e4', '\u06e4', '\ufeee', '\u0002'}};
    }

    private char getReshapedGlphy(char target, int location) {
        for (int n = 0; n < ARABIC_GLPHIES.length; n++) {
            if (ARABIC_GLPHIES[n][0] == target) {
                return ARABIC_GLPHIES[n][location];
            }
        }
        return target;
    }

    private int getGlphyType(char target) {
        for (int n = 0; n < ARABIC_GLPHIES.length; n++) {
            if (ARABIC_GLPHIES[n][0] == target) {
                return ARABIC_GLPHIES[n][5];
            }
        }
        return 2;
    }

    private boolean isHaraka(char target) {
        for (char c : HARAKATE) {
            if (c == target) {
                return true;
            }
        }
        return false;
    }

    private String replaceLamAlef(String unshapedWord) {
        int wordLength = unshapedWord.length();
        char[] wordLetters = new char[wordLength];
        unshapedWord.getChars(0, wordLength, wordLetters, 0);
        char letterBefore = '\u0000';
        int index = 0;
        while (index < wordLetters.length - 1) {
            if (!(isHaraka(wordLetters[index]) || DEFINED_CHARACTERS_ORGINAL_LAM == wordLetters[index])) {
                letterBefore = wordLetters[index];
            }
            if (DEFINED_CHARACTERS_ORGINAL_LAM == wordLetters[index]) {
                char candidateLam = wordLetters[index];
                int lamPosition = index;
                int harakaPosition = lamPosition + 1;
                while (harakaPosition < wordLetters.length && isHaraka(wordLetters[harakaPosition])) {
                    harakaPosition++;
                }
                if (harakaPosition < wordLetters.length) {
                    char lamAlef;
                    if (lamPosition <= 0 || getGlphyType(letterBefore) <= 2) {
                        lamAlef = getLamAlef(wordLetters[harakaPosition], candidateLam, true);
                    } else {
                        lamAlef = getLamAlef(wordLetters[harakaPosition], candidateLam, false);
                    }
                    if (lamAlef != '\u0000') {
                        wordLetters[lamPosition] = lamAlef;
                        wordLetters[harakaPosition] = ' ';
                    }
                }
            }
            index++;
        }
        return new String(wordLetters).replaceAll(" ", "").trim();
    }

    private char getLamAlef(char candidateAlef, char candidateLam, boolean isEndOfWord) {
        int shiftRate = 1;
        char reshapedLamAlef = '\u0000';
        if (isEndOfWord) {
            shiftRate = 1 + 1;
        }
        if (DEFINED_CHARACTERS_ORGINAL_LAM != candidateLam) {
            return '\u0000';
        }
        if (candidateAlef == DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_MDD) {
            reshapedLamAlef = LAM_ALEF_GLPHIES[0][shiftRate];
        }
        if (candidateAlef == DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_HAMAZA) {
            reshapedLamAlef = LAM_ALEF_GLPHIES[1][shiftRate];
        }
        if (candidateAlef == DEFINED_CHARACTERS_ORGINAL_ALF_LOWER_HAMAZA) {
            reshapedLamAlef = LAM_ALEF_GLPHIES[3][shiftRate];
        }
        if (candidateAlef == DEFINED_CHARACTERS_ORGINAL_ALF) {
            return LAM_ALEF_GLPHIES[2][shiftRate];
        }
        return reshapedLamAlef;
    }

    public ArabicReshaper(String unshapedWord) {
        this._returnString = "";
        DecomposedWord decomposedWord = new DecomposedWord(replaceLamAlef(unshapedWord));
        if (decomposedWord.stripedRegularLetters.length > 0) {
            this._returnString = reshapeIt(new String(decomposedWord.stripedRegularLetters));
        }
        this._returnString = decomposedWord.reconstructWord(this._returnString);
    }

    public String reshapeIt(String unshapedWord) {
        StringBuffer reshapedWord = new StringBuffer("");
        int wordLength = unshapedWord.length();
        char[] wordLetters = new char[wordLength];
        unshapedWord.getChars(0, wordLength, wordLetters, 0);
        reshapedWord.append(getReshapedGlphy(wordLetters[0], 2));
        for (int i = 1; i < wordLength - 1; i++) {
            if (getGlphyType(wordLetters[i - 1]) == 2) {
                reshapedWord.append(getReshapedGlphy(wordLetters[i], 2));
            } else {
                reshapedWord.append(getReshapedGlphy(wordLetters[i], 3));
            }
        }
        if (wordLength >= 2) {
            if (getGlphyType(wordLetters[wordLength - 2]) == 2) {
                reshapedWord.append(getReshapedGlphy(wordLetters[wordLength - 1], 1));
            } else {
                reshapedWord.append(getReshapedGlphy(wordLetters[wordLength - 1], 4));
            }
        }
        return reshapedWord.toString();
    }

}
