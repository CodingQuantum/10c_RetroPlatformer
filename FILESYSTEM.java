import java.io.*;
import java.util.Random;

public class FILESYSTEM {
    
    String [] data;
    private final String fileName = "data.txt";
    private final String [] encryptionTable = {"@", "€", "#", "*", "~", "/", "^", "+", "-", "°"};
    
    //liest einmal alle Daten aus der Datei aus
    FILESYSTEM()
    {
        data = new String [1];
        readFile();
    }
    
    //liest alle Werte aus der Datei aus und speichert diese im Daten-array
    void readFile()
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < data.length; i++)
            {
                data[i] = decryptNumber(decryptText(bufferedReader.readLine()));
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex)
        {
            System.out.println(
                "Error at reading file '" 
                + fileName + "'");                  
        }
    }
    
    //übernimmt die aktuellen Werte des Daten-Arrays in die Datei
    void writeFile()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < data.length; i++)
            {
                String encryptedText = encryptToText(encryptToNumber(data[i]));
                bufferedWriter.write(encryptedText);
                bufferedWriter.newLine();
            }
            bufferedWriter.write("Do not change this file!");
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println(
                "Error at writing to file '"
                + fileName + "'");
        }
    }
    
    //verschlüsselt die Zahl auf Basis zufälliger Werte, die in der verschlüsselten Zahl mit enthalten sind
    String encryptToNumber(String text)
    {
        int number = Integer.parseInt(text);
        
        int plus = random(1001);
        int times = random(101);
        String plusString = String.valueOf(plus);
        String timesString = String.valueOf(times);
        if (plus < 10) {plusString = "00" + plusString;}
        else if (plus < 100) {plusString = "0" + plusString;}
        if (times < 10) {timesString = "00" + timesString;}
        else if (times < 100) {timesString = "0" + timesString;}
        
        int encryptedNumberInt = number * times + plus;
        String encryptedNumber = plusString + timesString + String.valueOf(encryptedNumberInt);
        return encryptedNumber;
    }
    
    //wandelt Ziffern in Sonderzeichen um
    String encryptToText(String text)
    {
        String encryptedText = "";
        char [] letters = text.toCharArray();
        for (int i = 0; i < letters.length; i++)
        {
            int index = Integer.parseInt(String.valueOf(letters[i]));
            String encryptedLetter = encryptionTable[index];
            encryptedText += encryptedLetter;
        }
        return encryptedText;
    }
    
    //entschlüsselt die Zahl auf Basis der in der Zahl angegebenen Werte
    String decryptNumber(String text)
    {
        char [] l = text.toCharArray();
        int plus = Integer.parseInt(String.valueOf(l[0]) + String.valueOf(l[1]) + String.valueOf(l[2]));
        int times = Integer.parseInt(String.valueOf(l[3]) + String.valueOf(l[4]) + String.valueOf(l[5]));
        String numberString = "";
        for (int i = 6; i < l.length; i++) {numberString += l[i];}
        int number = (Integer.parseInt(numberString) - plus) / times;
        numberString = String.valueOf(number);
        
        return numberString;
    }
    
    //wandelt Sonderzeichen in Ziffern um
    String decryptText(String text)
    {
        String decryptedText = "";
        char [] letters = text.toCharArray();
        for (int i = 0; i < letters.length; i++)
        {
            String index = String.valueOf(searchFor(letters[i], encryptionTable));
            if (index.equals("-1") == true)
            {
                System.out.println("Decryption failure - file has been changed");
                return "0000010";
            }
            decryptedText += index;
        }
        return decryptedText;
    }
    
    //durchsucht das angegebende Array nach einem bestimmten Buchstaben
    int searchFor(char letter, String [] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            String letterString = String.valueOf(letter);
            if (array[i].equals(letterString) == true)
            {
                return i;
            }
        }
        return -1;
    }
    
    //erzeugt eine Zufallszahl zwischen 0 (eingeschlossen) und dem angegebenen Bereich (ausgeschlossen)
    int random(int range)
    {
        Random generator = new Random();
        int i = generator.nextInt(range);
        return i;
    }
}