
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.util.ElementScanner14;


class League {

    
    public static void main(String[] args) throws IOException {
        
       /* BufferedReader buf; 
       
        try { 
            buf = new BufferedReader(new FileReader( args[0]));

        }  catch(Exception e)
        {
        System.out.println( String.format(e.toString() + " \n Oops!! Please include a valid filename.\n " +
                                        "For example => java League input.txt"));      
        }
   */

        Map<String, Integer> table = new HashMap<>();
        BufferedReader buf = new BufferedReader(new FileReader( args[0]));
            String game;
            while ((game = buf.readLine()) != null) {
               // game = game.replaceAll("\\s", "");

               String []result = ParseResult(game); 
              
               table.computeIfPresent(result[0], (k, v) -> v + Integer.parseInt(result[1]));
               table.computeIfPresent(result[2], (k, v) -> v + Integer.parseInt(result[3]));
               
               table.computeIfAbsent(result[0], k -> Integer.parseInt(result[1]));
               table.computeIfAbsent(result[2], k -> Integer.parseInt(result[3]));


            }
            buf.close();


         /*   table.put("Juventus FC", 1);
           table.put("Bayern Munich FC", 80);
            table.put("Norwich City FC", 38);
            table.put("Manchester United", 100);
            table.put("Chelsea FC", 78);
   */
         printLeagueTable(table);
           
        
        
      
        //finally{ }
 
       


        
    }

    private static String[] ParseResult(String game) {


        String[] res = game.split(",", 2);  

String teamA = res[0].trim(); // { FC Lions99 4}
String teamB = res[1].trim();
//  int t1 = Integer.parseInt( res[0].split(" ", 2)[1] );

int scoreA = Integer.parseInt( teamA.substring( teamA.lastIndexOf(" ") + 1));
int scoreB = Integer.parseInt(teamB.substring( teamB.lastIndexOf(" ") + 1));
//System.out.println(scoreA + ":" + scoreB );
int pointsA =0, pointsB=0;
if ( scoreA > scoreB)
     pointsA=3;
else if(scoreA<scoreB)
    pointsB=3;
else
{
   pointsA=1;
   pointsB=1;
}


//String nameA = teamA.substring(0,  teamA.lastIndexOf(" ")+ 1);  
//String nameB = teamB.substring(0,  teamB.lastIndexOf(" ")+ 1);  
//System.out.println(nameA + ":" + nameB );

String [] TeamPts = { 
                     teamA.substring(0,  teamA.lastIndexOf(" ")+ 1),      //get teamA name
                     Integer.toString(pointsA),                           // get teamA points
                     teamB.substring(0,  teamB.lastIndexOf(" ")+ 1),       //teamB name
                     Integer.toString(pointsB)                            //teamB points
                    };

return TeamPts;

    }

   

    private static void printLeagueTable(Map<String, Integer> table) {
    
    	Map<String, Integer> lTable = table.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Entry.comparingByValue()))
			.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(),
					(entry1, entry2) -> entry2, LinkedHashMap::new));
         int position =1;
		for (Map.Entry<String, Integer> entry : lTable.entrySet()) {
			System.out.println(position++  + ". " + entry.getKey() + ", " +
                                           entry.getValue().toString() + 
                                       ( ( entry.getValue() == 1)? " pt" : " pts"));
		}
    


        /*
        1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts

        */
    }

}