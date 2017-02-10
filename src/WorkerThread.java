import java.io.*;
import java.util.*;
import java.security.*;
import java.lang.*;

public class WorkerThread extends Thread {

    ArrayList<String> dict = new ArrayList<String>();
    ArrayList<String> cleanOutput = new ArrayList<String>();
    HashMap<String, String> pwds = new HashMap<String, String>();
    String[] numbersArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    String[] lalphabet = {"a", "b", "e", "g", "i", "l", "o", "q", "r", "s", "t", "z"};
    String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String[] l33t = {"@", "8", "3", "6", "1", "1", "0", "9", "2", "5", "7", "2"};
    String[] symbolArray = {"!", "\u00A3", "$", "%", "^", "&", "*", "(", ")", ",", "<", ">", ".", "/", "?", ";", ":", "'", "@", "#", "~", "]", "}", "{", "[", "=", "+", "_", "-"};
    String command;

    public WorkerThread(String str, ArrayList<String> dict, HashMap<String, String> pwds) {
        super(str);
        this.command = str;
        this.dict = dict;
        this.pwds = pwds;
    }

    /**************************************************
    ****************NOTRANS METHODS********************
    **************************************************/
    //This thread hashed each dictionary entry before 
    //checking them against hashed password list directly. 
    //No transformation was done on the dictionary.
    public void noTrans(){
        for (int i = 0; i < dict.size(); i++) {
            hashTest(dict.get(i));
        }
    }

    /**************************************************
    ****************NUMBER AND SYMBOL METHODS**********
    **************************************************/
    //Adds numbers to the end of the word and tests.
    public void numbersEnd(String string){
        for(int i = 0; i < dict.size(); i++){
            hashTest(dict.get(i)+string);
        }
    }

    //Adds numbers the begining of the word and tests.
    public void numbersBeg(String string){
        for(int i = 0; i < dict.size(); i++){
            hashTest(string+dict.get(i));
        }
    }

    //Adds numbers to both the end and the begining of the word and tests.
    public void numbersBoth(String string){
        for(int i = 0; i < dict.size(); i++){
            hashTest(string+dict.get(i)+string);
        }
    }

    //Adds a symbol to the begining of the word then tests.
    public void symbolF(){
        for(int j = 0; j<dict.size(); j++){
            for(int i = 0; i < symbolArray.length-1; i++){
                hashTest(dict.get(j)+symbolArray[i]);
            }
        }
    }

    //Adds a symbol to the begining and end of the word then tests.
    //Also tests with word encapsulated in brackets.
    public void symbolBoth(){
        for(int j = 0; j<dict.size(); j++){
            for(int i = 0; i < symbolArray.length-1; i++){
                hashTest(symbolArray[i]+dict.get(j)+symbolArray[i]);
                hashTest(symbolArray[7]+dict.get(j)+symbolArray[8]);
            }
        }
    }

    //Adds a symbol to the End of the word then tests.
    public void symbolB(){
        for(int j = 0; j<dict.size(); j++){
            for(int i = 0; i < symbolArray.length-1; i++){
                hashTest(symbolArray[i]+dict.get(j));
            }
        }
    }

    //************** NUMBERS *************//
    //Adds numbers to front only, end only and both front and end.
    //Each whether one two three... etc tests all possible 
    //combinations invoking methods numbersBoth() numbersEnd() 
    //or numbersBeg().
    //***** ONE NUMBER *****//

     public void oneNumberEnd(){
        for(int i = 0; i < numbersArray.length; i++){
            numbersEnd(numbersArray[i]);
        }
    }

    public void oneNumberBoth(){
        for(int i = 0; i < numbersArray.length; i++){
            numbersBoth(numbersArray[i]);
        }
    }

    public void oneNumberBeg(){
        for(int i = 0; i < numbersArray.length; i++){
            numbersBeg(numbersArray[i]);
        }
    }

    public void oneNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            hashTest(numbersArray[i]);
        }
    }

    //***** TWO NUMBER *****//

    public void twoNumberEnd(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                numbersEnd(numbersArray[i]+numbersArray[x]);
            }
        }
    }

    public void twoNumberBoth(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                numbersEnd(numbersArray[i]+numbersArray[x]);
            }
        }
    }

    public void twoNumberBeg(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                numbersBeg(numbersArray[i]+numbersArray[x]);
            }
        }
    }

    public void twoNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                hashTest(numbersArray[i]+numbersArray[x]);
            }
        }
    }

    //***** THREE NUMBER *****//

    public void threeNumberEnd(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    numbersEnd(numbersArray[i]+numbersArray[x]+numbersArray[y]);
                }
            }
        }
    }

    public void threeNumberBoth(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    numbersEnd(numbersArray[i]+numbersArray[x]+numbersArray[y]);
                }
            }
        }
    }

    public void threeNumberBeg(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    numbersBeg(numbersArray[i]+numbersArray[x]+numbersArray[y]);
                }
            }
        }
    }

    public void threeNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]);
                }
            }
        }
    }

    //***** FOUR NUMBER *****//

    public void fourNumberEnd(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        numbersEnd(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]);
                    }
                }
            }
        }
    }

    public void fourNumberBoth(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        numbersEnd(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]);
                    }
                }
            }
        }
    }

    public void fourNumberBeg(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        numbersBeg(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]);
                    }
                }
            }
        }
    }

    public void fourNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]);
                    }
                }
            }
        }
    }

    //***** FIVE NUMBER *****//

    public void fiveNumberEnd(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            numbersEnd(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]);
                        }
                    }
                }
            }
        }
    }

    public void fiveNumberBoth(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            numbersEnd(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]);
                        }
                    }
                }
            }
        }
    }

    public void fiveNumberBeg(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            numbersBeg(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]);
                        }
                    }
                }
            }
        }
    }

    public void fiveNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]);
                        }
                    }
                }
            }
        }
    }

    public void sixNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            for(int b = 0; b < numbersArray.length; b++){
                                hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]+numbersArray[b]);
                            }
                        }
                    }
                }
            }
        }
    }

    public void sevenNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            for(int b = 0; b < numbersArray.length; b++){
                                for(int c = 0; c < numbersArray.length; c++){
                                    hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]+numbersArray[b]+numbersArray[c]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void eightNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            for(int b = 0; b < numbersArray.length; b++){
                                for(int c = 0; c < numbersArray.length; c++){
                                    for(int d = 0; d < numbersArray.length; d++){
                                        hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]+numbersArray[b]+numbersArray[c]+numbersArray[d]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void nineNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            for(int b = 0; b < numbersArray.length; b++){
                                for(int c = 0; c < numbersArray.length; c++){
                                    for(int d = 0; d < numbersArray.length; d++){
                                        for(int e = 0; e < numbersArray.length; e++){
                                            hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]+numbersArray[b]+numbersArray[c]+numbersArray[d]+numbersArray[e]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void tenNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            for(int b = 0; b < numbersArray.length; b++){
                                for(int c = 0; c < numbersArray.length; c++){
                                    for(int d = 0; d < numbersArray.length; d++){
                                        for(int e = 0; e < numbersArray.length; e++){
                                            for(int f = 0; f < numbersArray.length; f++){
                                                hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]+numbersArray[b]+numbersArray[c]+numbersArray[d]+numbersArray[e]+numbersArray[f]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void elevenNumberSolo(){
        for(int i = 0; i < numbersArray.length; i++){
            for(int x = 0; x < numbersArray.length; x++){
                for(int y = 0; y < numbersArray.length; y++){
                    for(int z = 0; z < numbersArray.length; z++){
                        for(int a = 0; a < numbersArray.length; a++){
                            for(int b = 0; b < numbersArray.length; b++){
                                for(int c = 0; c < numbersArray.length; c++){
                                    for(int d = 0; d < numbersArray.length; d++){
                                        for(int e = 0; e < numbersArray.length; e++){
                                            for(int f = 0; f < numbersArray.length; f++){
                                                for(int g = 0; g < numbersArray.length; g++){
                                                    hashTest(numbersArray[i]+numbersArray[x]+numbersArray[y]+numbersArray[z]+numbersArray[a]+numbersArray[b]+numbersArray[c]+numbersArray[d]+numbersArray[e]+numbersArray[f]+numbersArray[g]);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**************************************************
    ******************L33T METHODS*********************
    **************************************************/
    //changes every possible character in a word that can be changed to l33t characters
    //changes one symbol in the word, iterating through all possible changes
    //changes two symbols in the word iterating through all possible changes
    //changed three symbols in the word iterating through all possible changes

    public void l33t(){

        //ALL L33T
        for (int i = 0; i < dict.size(); i++) {

            String tempString = dict.get(i);
            String[] tempArray = tempString.split("");
            
            for(int x = 0; x<tempArray.length; x++){
                for(int y = 0; y < lalphabet.length; y++){
                    if (tempArray[x].equals(lalphabet[y])){
                        tempArray[x] = l33t[y];
                    }

                }
            }
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.delete(0, tempArray.length);

            for (int f = 0; f < tempArray.length; f++) {
                strBuilder.append(tempArray[f]);
            }

            String stringToTest = strBuilder.toString();
            hashTest(stringToTest);
        }

        for(int i = 0; i < lalphabet.length; i++){
            l33tSearch(i);
        }

    }

    public void l33tSearch(int index){
        for (int i = 0; i < dict.size(); i++) {

            String tempString = dict.get(i);
            String[] tempArray = tempString.split("");
            int count = 0;

            for(int x = 0; x<tempArray.length; x++){
                if (tempArray[x].equals(lalphabet[index])){

                    tempString = tempArray[x];
                    tempArray[x] = l33t[index];
                    count++;
                    if(count>1){
                        tempArray[x-1] = tempString;
                    }

                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.delete(0, tempArray.length);

                    for (int f = 0; f < tempArray.length; f++) {
                        strBuilder.append(tempArray[f]);
                    }

                    String stringToTest = strBuilder.toString();
                    hashTest(stringToTest);
                    for(int s = 0; s < lalphabet.length; s++){
                        l33tSearchTwo(stringToTest, s);
                    }
                }
                count=0;
            }

        }

    }

    public void l33tSearchTwo(String l33tWord, int index){

            String[] tempArray = l33tWord.split("");

            for(int x = 0; x<tempArray.length; x++){
                if (tempArray[x].equals(lalphabet[index])){

                    tempArray[x] = l33t[index];
                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.delete(0, tempArray.length);

                    for (int f = 0; f < tempArray.length; f++) {
                        strBuilder.append(tempArray[f]);
                    }

                    String stringToTest = strBuilder.toString();
                    hashTest(stringToTest);
                    for(int s = 0; s < lalphabet.length; s++){
                        l33tSearchThree(stringToTest, s);
                    }
                }
        }

    }

    public void l33tSearchThree(String l33tWord, int index){

            String[] tempArray = l33tWord.split("");

            for(int x = 0; x<tempArray.length; x++){
                if (tempArray[x].equals(lalphabet[index])){

                    tempArray[x] = l33t[index];
                    StringBuilder strBuilder = new StringBuilder();
                    strBuilder.delete(0, tempArray.length);

                    for (int f = 0; f < tempArray.length; f++) {
                        strBuilder.append(tempArray[f]);
                    }

                    String stringToTest = strBuilder.toString();
                    hashTest(stringToTest);
                }
        }

    }




    /**************************************************
    ******************CAPITALS METHODS*****************
    **************************************************/

    //changes the case of each letter of the word to a capital
    //changes the case of two letters in the word to capitals
    //changes all the letters in the word to capital and hashed it

    public void capitals(){
        for (int i = 0; i < dict.size(); i++) {
            String tempString = dict.get(i);
            String[] tempArray = tempString.split("");
            StringBuilder strBuilder = new StringBuilder();
            String stringToTest = "null";
            //CAPITALS ONE AT A TIME One oNe onE
            //For the each letter of word i
            for (int wordLetter = 0; wordLetter < tempArray.length; wordLetter ++){
                if(Arrays.asList(alphabet).contains(tempArray[wordLetter])){
                    tempArray[wordLetter] = tempArray[wordLetter].substring(0, 1).toUpperCase();

                for (int f = 0; f < tempArray.length; f++) {
                    strBuilder.append(tempArray[f]);
                }

                stringToTest = strBuilder.toString();
                strBuilder.delete(0, tempArray.length);
                hashTest(stringToTest);
                tempArray[wordLetter] = tempArray[wordLetter].substring(0, 1).toLowerCase();
                }
            }

            

            for (int firstLetter = 0; firstLetter < tempArray.length; firstLetter ++){
                if(Arrays.asList(alphabet).contains(tempArray[firstLetter])){

                        tempArray[firstLetter] = tempArray[firstLetter].substring(0, 1).toUpperCase();
                }
                for (int secondLetter = firstLetter+1; secondLetter < tempArray.length; secondLetter ++){
                    if(Arrays.asList(alphabet).contains(tempArray[secondLetter])){
                        tempArray[secondLetter] = tempArray[secondLetter].substring(0, 1).toUpperCase();

                        strBuilder.delete(0, tempArray.length);
                        for (int f = 0; f < tempArray.length; f++) {
                            strBuilder.append(tempArray[f]);
                        }

                        stringToTest = strBuilder.toString();
                        hashTest(stringToTest);

                        tempArray[secondLetter] = tempArray[secondLetter].substring(0, 1).toLowerCase();
                    }
                }
                tempArray[firstLetter] = tempArray[firstLetter].substring(0, 1).toLowerCase();
            }

            //ALL CAPITALS
            for (int wordLetter = 0; wordLetter < tempArray.length; wordLetter ++){
                if(Arrays.asList(alphabet).contains(tempArray[wordLetter])){
                    tempArray[wordLetter] = tempArray[wordLetter].substring(0, 1).toUpperCase();
                    for (int f = 0; f < tempArray.length; f++) {
                        strBuilder.append(tempArray[f]);
                    }
                    stringToTest = strBuilder.toString();
                    strBuilder.delete(0, tempArray.length);
                }
            }
            hashTest(stringToTest);
        }
    }

    /**************************************************
    ******************COMBINE METHODS******************
    **************************************************/
    //Combines all the words, each method does a quarter.
    public void combineTwoWordsOne(){
        for(int i = 0; i < dict.size()/4; i++){
            for(int j = 0; j < dict.size(); j++){
                hashTest(dict.get(i)+dict.get(j));
            }
        }
    }

    //Combines same word.
    public void combineSame(){
        for(int i = 0; i < dict.size(); i++){
            hashTest(dict.get(i)+dict.get(i));
        }
    }

    public void combineTwoWordsTwo(){
        for(int i = dict.size()/4; i < (dict.size()/4)*2; i++){
            for(int j = 0; j < dict.size(); j++){
                hashTest(dict.get(i)+dict.get(j));
            }
        }
    }

    public void combineTwoWordsThree(){
        for(int i = (dict.size()/4)*2; i < (dict.size()/4)*3; i++){
            for(int j = 0; j < dict.size(); j++){
                hashTest(dict.get(i)+dict.get(j));
            }
        }
    }

    public void combineTwoWordsFour(){
        for(int i = (dict.size()/4)*3; i < dict.size(); i++){
            for(int j = 0; j < dict.size(); j++){
                hashTest(dict.get(i)+dict.get(j));
            }
        }
    }

    public void combineThreeWords(){
        for(int i = 0; i < dict.size(); i++){
            for(int j = 0; j < dict.size(); j++){
                for(int g = 0; g < dict.size(); g++){
                    hashTest(dict.get(i)+dict.get(j)+dict.get(g));
                }
            }
        }
    }

    /**************************************************
    ******************COMBINATION  METHODS*************
    **************************************************/

    //Add capitals and number to end.

    public void capitalOneNumber(){
        for (int l = 0; l < dict.size(); l++) {
            String tempString = dict.get(l);
            String[] tempArray = tempString.split("");
            StringBuilder strBuilder = new StringBuilder();
            for (int wordLetter = 0; wordLetter < tempArray.length; wordLetter ++){
                if(Arrays.asList(alphabet).contains(tempArray[wordLetter])){
                    tempArray[wordLetter] = tempArray[wordLetter].substring(0, 1).toUpperCase();

                for (int f = 0; f < tempArray.length; f++) {
                    strBuilder.append(tempArray[f]);
                }

                String stringToTest = strBuilder.toString();
                strBuilder.delete(0, tempArray.length);
                for(int i = 0; i < numbersArray.length; i++){
                    hashTest(stringToTest + numbersArray[i]);
                    hashTest(stringToTest + numbersArray[i] + numbersArray[i]);
                }
                tempArray[wordLetter] = tempArray[wordLetter].substring(0, 1).toLowerCase();
                }
            }
        }

    }


    /**************************************************
    ******************HASH TEST METHOD*****************
    **************************************************/
    //Converts to hash, tests against pwds list
    //Writes to file.
    public void hashTest(String string) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(string.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int n = 0; n < hash.length; n++) {
                String hex = Integer.toHexString(0xff & hash[n]);

                if (hex.length() == 1)
                    hexString.append('0');
                    hexString.append(hex);
                }

                String test = hexString.toString();

                if (pwds.containsKey(test) == true){
                    if(pwds.get(test) != null){
                        System.out.println(pwds.get(test) + ":" + string);
                        FileWriter fw = new FileWriter("output.txt",true);
                        if(pwds.get(test) != null){
                            fw.write(pwds.get(test) + ":" + string + "\n");
                            fw.close();
                        }
                        pwds.remove(test);
                    }
                }


        } catch (Exception e){
            e.printStackTrace();
        }
    }



    /**************************************************
    ********************RUN METHOD*********************
    **************************************************/

    public void run() {

        if(command.equals("noTrans")){

            noTrans();

        } else if(command.equals("l33t")){

            l33t();

        } else if(command.equals("oneNumberEnd")){

            oneNumberEnd();
            
        } else if(command.equals("oneNumberBeg")){

            oneNumberBeg();
            
        } else if(command.equals("oneNumberBoth")){

            oneNumberBoth();
            
        } else if(command.equals("oneNumberSolo")){

            oneNumberSolo();
            
        } else if(command.equals("twoNumberEnd")){

            twoNumberEnd();
            
        } else if(command.equals("twoNumberBeg")){

            twoNumberBeg();
            
        } else if(command.equals("twoNumberBoth")){

            twoNumberBoth();
            
        } else if(command.equals("twoNumberSolo")){

            twoNumberSolo();
            
        } else if(command.equals("threeNumberEnd")){

            threeNumberEnd();
            
        } else if(command.equals("threeNumberBeg")){

            threeNumberBeg();
            
        } else if(command.equals("threeNumberBoth")){

            threeNumberBoth();
            
        } else if(command.equals("threeNumberSolo")){

            threeNumberSolo();
            
        } else if(command.equals("fourNumberEnd")){

            fourNumberEnd();
            
        } else if(command.equals("fourNumberBeg")){

            fourNumberBeg();
            
        } else if(command.equals("fourNumberBoth")){

            fourNumberBoth();
            
        } else if(command.equals("fourNumberSolo")){

            fourNumberSolo();
            
        } else if(command.equals("fiveNumbersEnd")){

            fiveNumberEnd();
            
        } else if(command.equals("fiveNumberBeg")){

            fiveNumberBeg();
            
        } else if(command.equals("fiveNumberBoth")){

            fiveNumberBoth();
            
        } else if(command.equals("fiveNumberSolo")){

            fiveNumberSolo();
            
        } else if(command.equals("sixNumberSolo")){

            sixNumberSolo();
            
        } else if(command.equals("sevenNumberSolo")){

            sevenNumberSolo();
            
        } else if(command.equals("eightNumberSolo")){

            eightNumberSolo();
            
        } else if(command.equals("nineNumberSolo")){

            nineNumberSolo();
            
        } else if(command.equals("tenNumberSolo")){

            tenNumberSolo();
            
        } else if(command.equals("elevenNumberSolo")){

            elevenNumberSolo();
            
        } else if(command.equals("capitals")){

            capitals();
            
        } else if(command.equals("combineTwoWordsOne")){

            combineTwoWordsOne();
            
        } else if(command.equals("combineTwoWordsTwo")){

            combineTwoWordsTwo();
            
        } else if(command.equals("combineTwoWordsThree")){

            combineTwoWordsThree();
            
        } else if(command.equals("combineTwoWordsFour")){

            combineTwoWordsFour();
            
        } else if(command.equals("combineThreeWords")){

            combineThreeWords();
            
        } else if(command.equals("combineSame")){

            combineSame();
            
        }else if(command.equals("symbols")){

            symbolF();
            symbolB();
            
        }else if(command.equals("symbolBoth")){

            symbolBoth();
        }else if(command.equals("capitalNumber")){

            capitalOneNumber();
        }

    }


}