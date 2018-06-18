package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Archivos {
    public static String leer(String fileDirectory){
        String content = "";
        BufferedReader entry = null;
        File file = new File(fileDirectory);
        try{
            entry = new BufferedReader(new FileReader(file));
            String text;
            while((text = entry.readLine()) != null){
                if (content.equals("")){
                    content = text;
                }else{
                    content = content.concat("\r\n" + text);
                }
            }
        }catch(IOException exception){
            
        }
        finally{
            if (entry != null){
                try {
                    entry.close();
                } catch (IOException exception) {

                }
            }
        }
        return content;
    }
}
