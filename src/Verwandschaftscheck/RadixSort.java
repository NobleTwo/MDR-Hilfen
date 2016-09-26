package Verwandschaftscheck;
import java.lang.*;
import java.util.*;

class RadixSort{
  public Vector<String> sort(Vector<String> words){
    String[] wordsAsArray = new String[words.size()];
    for(int i=0; i<wordsAsArray.length; i++){
      wordsAsArray[i] = words.get(i);
    }
    wordsAsArray = sort(wordsAsArray);
    
    words = new Vector<String>();
    for(int i=0; i<wordsAsArray.length; i++){
      words.add(wordsAsArray[i]);
    }
    return words;
  }
  
  public String[] sort(String[] words){
    int maxlength = 0;
    for(int i=0; i<words.length; i++){
      if(words[i].length()>maxlength)
      maxlength = words[i].length();
    }
    
    for(int i=0; i<=maxlength; i++){
      Vector[] ascii = new Vector[257];
      for(int j=0; j<ascii.length; j++){
        ascii[j] = new Vector();
      }
      for(int j=0; j<words.length; j++){
        if(words[j].length()-i<=0){
          ascii[0].add(words[j]);
        }
        else{
          //System.out.println((int)words[j].charAt(words[j].length()-1-i));
          char current = words[j].toLowerCase().charAt(words[j].length()-1-i);
          ascii[(int)current+1].add(words[j]);
        }
      }
      int count = 0;
      for(int j=0; j<ascii.length-1; j++){
        while(!ascii[j].isEmpty()){
          words[count] = (String) ascii[j].firstElement();
          ascii[j].remove(0);
          //System.out.println(j+"\t"+count+"\t"+words[count]);
          count++;
        }
      }
      //System.out.println();
      //print(words);
    }
    return words;  
  }
  
  public void print(String[] test){
    for(int i=0; i<test.length; i++){
      System.out.println(test[i]);
    }
  }
  
  public static void main(String[] args){
    RadixSort rs = new RadixSort();
    String[] test = new String[] {"Zuaha", "9b7", "9b6", "aaa", "AAb", "AAA", "zuaha"};
    test = rs.sort(test);
    rs.print(test);
  }
}