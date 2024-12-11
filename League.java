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

/**
 * Author: Tichaona Zvidzayi
 * Date Last Modified: 27 December 2021
 * Description: A Java CLI application that calculates league results based on game outcomes.
 *              Win = 3 pts, Draw = 1 pt, Loss = 0 pts.
 * 
 * Compile using: javac League.java
 * Run using: java League <input_file>
 */
class League {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java League <input_file>");
            return;
        }

        Map<String, Integer> table = new HashMap<>();

        try (BufferedReader buf = new BufferedReader(new FileReader(args[0]))) {
            String game;
            while ((game = buf.readLine()) != null) {
                String[] result = parseResult(game);
                
                table.merge(result[0], Integer.parseInt(result[1]), Integer::sum);
                table.merge(result[2], Integer.parseInt(result[3]), Integer::sum);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            return;
        }

        // Sort table alphabetically by team name
        Map<String, Integer> sortedTable = new TreeMap<>(table);

        // Print league table sorted by points in descending order
        printLeagueTable(sortedTable);
    }

    private static String[] parseResult(String game) {
        // Parse each line to extract team names and scores
        String[] parts = game.split(",", 2);
        String teamA = parts[0].trim();
        String teamB = parts[1].trim();

        int scoreA = Integer.parseInt(teamA.substring(teamA.lastIndexOf(" ") + 1));
        int scoreB = Integer.parseInt(teamB.substring(teamB.lastIndexOf(" ") + 1));

        // Calculate points based on the game rules
        int pointsA = scoreA > scoreB ? 3 : scoreA == scoreB ? 1 : 0;
        int pointsB = scoreB > scoreA ? 3 : scoreA == scoreB ? 1 : 0;

        return new String[] {
            teamA.substring(0, teamA.lastIndexOf(" ")).trim(),  // Team A name
            String.valueOf(pointsA),                            // Team A points
            teamB.substring(0, teamB.lastIndexOf(" ")).trim(), // Team B name
            String.valueOf(pointsB)                             // Team B points
        };
    }

    private static void printLeagueTable(Map<String, Integer> table) {
        Map<String, Integer> sortedTable = table.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new
                ));

        int position = 0;
        int previousPoints = -1;

        for (Map.Entry<String, Integer> entry : sortedTable.entrySet()) {
            int points = entry.getValue();
            if (points != previousPoints) {
                position++;
            }

            System.out.printf("%d. %s, %d %s%n",
                    position,
                    entry.getKey(),
                    points,
                    points == 1 ? "pt" : "pts");

            previousPoints = points;
        }
    }
}
