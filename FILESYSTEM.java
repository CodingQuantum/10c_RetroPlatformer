import java.io.*;

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
                data[i] = decrypt(bufferedReader.readLine());
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
                String encryptedText = encrypt(data[i]);
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
    
    //verschlüsselt den angegebenen Text
    String encrypt(String text)
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
    
    //entschlüsselt den angegebenen Text
    String decrypt(String text)
    {
        String decryptedText = "";
        char [] letters = text.toCharArray();
        for (int i = 0; i < letters.length; i++)
        {
            String index = String.valueOf(searchFor(letters[i], encryptionTable));
            if (index.equals("-1") == true)
            {
                System.out.println("Decryption failure - file has been changed");
                return "0";
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
}