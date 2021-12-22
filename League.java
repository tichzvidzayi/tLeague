
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


class League {

    
    public static void main(String[] args) throws IOException {
        
        BufferedReader buf; 
        try { 
            buf = new BufferedReader(new FileReader( args[0]));
            String game;
            while ((game = buf.readLine()) != null) {
              // System.out.println(game + "  :");
               AddResult(game);
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

    private static void AddResult(String game) {

        String[] res = game.split(",", 2);  //{ Lions 3, Sankes 3}
        String teamA = res[0];
        String teamB = res[1];

        Map<String, Integer> table = new HashMap<>();

		// storing the values
        table.put("Juventus FC", 1);
        table.put("Bayern Munich FC", 80);
		table.put("Norwich City FC", 38);
		table.put("Manchester United", 100);
		table.put("Chelsea FC", 78);
        printLeagueTable(table);
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