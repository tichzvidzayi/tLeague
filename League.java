
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
        } finally {
            br.close();
        }
    }
}