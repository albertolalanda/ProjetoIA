package eightpuzzle;

import agent.Agent;
import java.io.File;
import java.io.IOException;

public class EightPuzzleAgent extends Agent<EightPuzzleState>{
    
    protected EightPuzzleState initialEnvironment;    
    
    public EightPuzzleAgent(EightPuzzleState environemt) {
        super(environemt);
        initialEnvironment = (EightPuzzleState) environemt.clone();
        heuristics.add(new HeuristicNull());
        heuristics.add(new HeuristicTileDistance());
        heuristics.add(new HeuristicNumberOfPiecesInTheWay());
        heuristics.add(new HeuristicSizeOfObjectsInTheWay());
        heuristics.add(new HeuristicNumberOfTilesInFrontOfCar());
        heuristics.add(new HeuristicTileDistanceAndNumberOfPiecesInTheWay());
        heuristic = heuristics.get(0);
    }
            
    public EightPuzzleState resetEnvironment(){
        environment = (EightPuzzleState) initialEnvironment.clone();
        return environment;
    }
                 
    public EightPuzzleState readInitialStateFromFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);
        
        int tableSize = scanner.nextInt();
        scanner.nextLine();
        int[][] matrix = new int [tableSize][tableSize];
        
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                matrix[i][j] = scanner.nextInt();
            }
            scanner.nextLine();
        }
        initialEnvironment = new EightPuzzleState(matrix);
        resetEnvironment();
        return environment;
    }
}
