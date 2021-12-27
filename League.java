
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
/*
        Author : Tichaona Zvidzayi
        Date Last Modified  27 December 2021
        Description: A Java CLI application that calculates League results according to results
            Win = 3pts, Lose = 0pts, Draw =1

        Compile using :   javac League.java
        Run           :   java League <input file>


                      :   java league moreinput.txt
*/

class League {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> table = new HashMap<>();
        BufferedReader buf; 
        String game;
        // The try catches exceptions such as IndexOutofRange, IOExceptions etc and print the error
        try {  
            buf = new BufferedReader(new FileReader( args[0]));   
            while ((game = buf.readLine()) != null)
             {
               String []result = ParseResult(game); 
               // Computes the points is already present 
               table.computeIfPresent(result[0], (k, v) -> v + Integer.parseInt(result[1]));
               table.computeIfPresent(result[2], (k, v) -> v + Integer.parseInt(result[3]));
               
               // Creates a new team and adds name and points
               table.computeIfAbsent(result[0], k -> Integer.parseInt(result[1]));
               table.computeIfAbsent(result[2], k -> Integer.parseInt(result[3]));
            }
            // Closes the input file
            buf.close();  

        }  catch(Exception e)
        {
            // Catches and prints the error 
           // System.out.println(e);      
        }
          // Prints the league table

      Map<String, Integer> treeMap = new TreeMap<String, Integer>(table);
     //Modified to alphabetical sort the table

          
         printLeagueTable(treeMap);      
    }

private static String[] ParseResult(String game) {

 // Parse each line of the file to get the team name and scores   
 String[] res = game.split(",", 2);  
 String teamA = res[0].trim(); 
 String teamB = res[1].trim();
 int scoreA = Integer.parseInt( teamA.substring( teamA.lastIndexOf(" ") + 1));
 int scoreB = Integer.parseInt(teamB.substring( teamB.lastIndexOf(" ") + 1));
/* Calculates the points according to the game rules 
   win =3, lose =0, draw =0
*/
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
String [] TeamPts = { 
                     teamA.substring(0,  teamA.lastIndexOf(" ")+ 1),      //get teamA name
                     Integer.toString(pointsA),                           // get teamA points
                     teamB.substring(0,  teamB.lastIndexOf(" ")+ 1),       //teamB name
                     Integer.toString(pointsB)                            //teamB points
                    };
// Returns the team names and calculated points as a string array
return TeamPts;

}

private static void printLeagueTable(Map<String, Integer> table) {
   
    Map<String, Integer> lTable = table.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Entry.comparingByValue()))
			.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(),
					(entry1, entry2) -> entry2, LinkedHashMap::new));
// Sorts the League table in descending order according to values (points)              
    int position =0, previous = 0;

// Prints the elements of the table according to the game rules e.g. same point ==same position
	for (Map.Entry<String, Integer> entry : lTable.entrySet()) {

          position = previous == entry.getValue() ? position : ++position ;
           
			System.out.println(position  + ". " + entry.getKey() + ", " +
                                           entry.getValue().toString() + 
                                      ( ( entry.getValue() == 1)? " pt" : " pts"));

            previous = entry.getValue();
  
		}
    }

}