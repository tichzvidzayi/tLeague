
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class League {
    public static void main(String[] args) throws IOException {
        
        BufferedReader buf; 
        try { 
            buf = new BufferedReader(new FileReader( args[0]));
            String line;
            while ((line = buf.readLine()) != null) {
               System.out.println(line + "  :");
            }
            buf.close();
           
        } 
        
        catch(Exception e)
        {
        System.out.println( String.format(e.toString() + " \n Oops!! Please include a valid filename.\n " +
                                        "For example => java League input.txt"));      
        }
        //finally{ }
 
         
        
    }
}