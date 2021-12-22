
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class League {
    public static void main(String[] args) throws IOException {
        

        BufferedReader br = new BufferedReader(new FileReader( args[0]));
        try {
            String line;
            while ((line = br.readLine()) != null) {
               System.out.println(line + "  :");
            }
            br.close();
        } 
        
        catch(FileNotFoundException e)
        {
            System.out.println(" Oops, you have to include the file name gor args[0] \n e.g, java League input.txt");      
        }
        
 
         
        
    }
}