package eightpuzzle;

import agent.Action;
import agent.State;
import java.util.ArrayList;
import java.util.Arrays;

public class EightPuzzleState extends State implements Cloneable {

    private int[][] matrix;

    private int colunaCarro;  
    private int linhaCarro;  
    private ArrayList<Peca> pecas;
    private ArrayList<Posicao> notStartingPos;
    private boolean found = false;

    public EightPuzzleState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];
        this.pecas = new ArrayList<Peca>();
        this.notStartingPos = new ArrayList<Posicao>();
        notStartingPos.add(new Posicao(-1, -1));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                found = false;
                switch (matrix[i][j]) {
                    case 1:
                        pecas.add(new Peca(new Posicao(i, j), 1));
                        colunaCarro = j;
                        linhaCarro = i;
                        break;
                    case 2:
                        pecas.add(new Peca(new Posicao(i, j), 2));
                        break;
                    case 3:
                        pecas.add(new Peca(new Posicao(i, j), 3));
                        break;
                    case 4:
                        for (int k = 0; k < notStartingPos.size(); k++) {
                            if (notStartingPos.get(k).equals(new Posicao(i, j))) {
                                //System.out.println("Found a 4");
                                found = true;
                            }
                        }
                        if (!found) {
                            pecas.add(new Peca(new Posicao(i, j), 4));
                            notStartingPos.add(new Posicao(i, j + 1));
                        }

                        break;
                    case 5:
                        for (int k = 0; k < notStartingPos.size(); k++) {
                            if (notStartingPos.get(k).equals(new Posicao(i, j))) {
                                //System.out.println("Found a 5");
                                found = true;
                            }
                        }
                        if (!found) {
                            pecas.add(new Peca(new Posicao(i, j), 5));
                            notStartingPos.add(new Posicao(i + 1, j));
                        }

                        break;
                    case 6:
                        for (int k = 0; k < notStartingPos.size(); k++) {
                            if (notStartingPos.get(k).equals(new Posicao(i, j))) {
                                //System.out.println("Found a 6");
                                found = true;
                            }
                        }
                        if (!found) {
                            pecas.add(new Peca(new Posicao(i, j), 6));
                            notStartingPos.add(new Posicao(i, j + 1));
                            notStartingPos.add(new Posicao(i, j + 2));
                        }

                        break;
                    case 7:
                        for (int k = 0; k < notStartingPos.size(); k++) {
                            if (notStartingPos.get(k).equals(new Posicao(i, j))) {
                                //System.out.println("Found a 7");
                                found = true;
                            }
                        }
                        if (!found) {
                            pecas.add(new Peca(new Posicao(i, j), 7));
                            notStartingPos.add(new Posicao(i + 1, j));
                            notStartingPos.add(new Posicao(i + 2, j));
                        }
                        found = false;
                        break;
                    case 8:
                        for (int k = 0; k < notStartingPos.size(); k++) {
                            if (notStartingPos.get(k).equals(new Posicao(i, j))) {
                                //System.out.println("Found a 8");
                                found = true;
                            }
                        }
                        if (!found) {
                            pecas.add(new Peca(new Posicao(i, j), 8));
                            notStartingPos.add(new Posicao(i, j + 1));
                            notStartingPos.add(new Posicao(i, j + 2));
                            notStartingPos.add(new Posicao(i, j + 3));
                        }
                        found = false;
                        break;
                    case 9:
                        for (int k = 0; k < notStartingPos.size(); k++) {
                            if (notStartingPos.get(k).equals(new Posicao(i, j))) {
                                //System.out.println("Found a 9");
                                found = true;
                            }
                        }
                        if (!found) {
                            pecas.add(new Peca(new Posicao(i, j), 9));
                            notStartingPos.add(new Posicao(i + 1, j));
                            notStartingPos.add(new Posicao(i + 2, j));
                            notStartingPos.add(new Posicao(i + 3, j));
                        }
                        found = false;
                        break;
                }

            }
        }
        /*System.out.println(pecas);
        for (Peca p : pecas) {
            System.out.println(p.getLinha() + "," + p.getColuna());
        }
        System.out.println(toString());*/

    }

    public int getColunaCarro() {
        return colunaCarro;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    
    public boolean canMoveRight(Peca peca) {
        //System.out.println("Can Right(" + peca.getLinha() + "," + peca.getColuna() + ")");
        int linha = peca.getLinha();
        int coluna = peca.getColuna();
        switch (peca.getTipo()) {
            case 1:
                if (isValidPosition(linha, coluna + 1) && isValidMove(linha, coluna + 1, peca)) {
                    return true;
                }
                break;
            case 2:
                if (isValidPosition(linha, coluna + 1) && isValidMove(linha, coluna + 1, peca)) {
                    return true;
                }
                break;
            case 4:
                if (isValidPosition(linha, coluna + 2) && isValidMove(linha, coluna + 2, peca)) {
                    return true;
                }
                break;
            case 6:
                if (isValidPosition(linha, coluna + 3) && isValidMove(linha, coluna + 3, peca)) {
                    //System.out.println(isValidPosition(linha, coluna + 3) + "-------------------------------------------------------" + isValidMove(linha, coluna + 3) + matrix[linha][coluna+3]);
                    return true;
                }
                break;
            case 8:
                if (isValidPosition(linha, coluna + 4) && isValidMove(linha, coluna + 4, peca)) {
                    return true;
                }
        }
        return false;
    }

    public boolean canMoveLeft(Peca peca) {
        //System.out.println("Can Left(" + peca.getLinha() + "," + peca.getColuna() + ")");
        int linha = peca.getLinha();
        int coluna = peca.getColuna();
        return isValidPosition(linha, coluna - 1) && isValidMove(linha, coluna - 1, peca);
    }

    public boolean canMoveDown(Peca peca) {
        //System.out.println("Can Down(" + peca.getLinha() + "," + peca.getColuna() + ")");
        int linha = peca.getLinha();
        int coluna = peca.getColuna();
        switch (peca.getTipo()) {
            case 3:
                if (isValidPosition(linha + 1, coluna) && isValidMove(linha + 1, coluna, peca)) {
                    return true;
                }
                break;
            case 5:
                if (isValidPosition(linha + 2, coluna) && isValidMove(linha + 2, coluna, peca)) {
                    return true;
                }
                break;
            case 7:
                if (isValidPosition(linha + 3, coluna) && isValidMove(linha + 3, coluna, peca)) {
                    return true;
                }
                break;
            case 9:
                if (isValidPosition(linha + 4, coluna) && isValidMove(linha + 4, coluna, peca)) {
                    return true;
                }
        }
        return false;
    }

    public boolean canMoveUp(Peca peca) {
        //System.out.println("Can Up(" + peca.getLinha() + "," + peca.getColuna() + ")");
        int linha = peca.getLinha();
        int coluna = peca.getColuna();
        return isValidPosition(linha - 1, coluna) && isValidMove(linha - 1, coluna, peca);
    }

   
    public void moveUp(Peca peca) {
        //System.out.println("Moving Up(" + peca.getLinha() + "," + peca.getColuna() + ")");

        matrix[peca.getLinha() - 1][peca.getColuna()] = matrix[peca.getLinha()][peca.getColuna()];
        switch (peca.getTipo()) {
            case 3:         
                matrix[peca.getLinha()][peca.getColuna()] = 0;
                break;
            case 5:
                matrix[peca.getLinha() + 1][peca.getColuna()] = 0;
                break;
            case 7:
                matrix[peca.getLinha() + 2][peca.getColuna()] = 0;
                break;
            case 9:
                matrix[peca.getLinha() + 3][peca.getColuna()] = 0;
                break;
        }
        for (Peca p : pecas) {
            if (p.getLinha() == peca.getLinha() && p.getColuna() == peca.getColuna()) {
                p.getPosicao().setLinha(p.getPosicao().getLinha() - 1);
            }
        }

    }

    public void moveRight(Peca peca) {
        //System.out.println("Moving Right(" + peca.getLinha() + "," + peca.getColuna() + ")");
        matrix[peca.getLinha()][peca.getColuna()] = 0;

        switch (peca.getTipo()) {
            case 1:
                matrix[peca.getLinha()][peca.getColuna() + 1] = 1;
                colunaCarro++;
                break;
            case 2:
                matrix[peca.getLinha()][peca.getColuna() + 1] = 2;
                break;
            case 4:
                matrix[peca.getLinha()][peca.getColuna() + 2] = 4;
                break;
            case 6:
                matrix[peca.getLinha()][peca.getColuna() + 3] = 6;
                break;
            case 8:
                matrix[peca.getLinha()][peca.getColuna() + 4] = 8;
                break;
        }
        for (Peca p : pecas) {
            if (p.getLinha() == peca.getLinha() && p.getColuna() == peca.getColuna()) {
                p.getPosicao().setColuna(p.getPosicao().getColuna() + 1);
            }
        }
    }

    public void moveDown(Peca peca) {
        //System.out.println("Moving Down(" + peca.getLinha() + "," + peca.getColuna() + ")");
        matrix[peca.getLinha()][peca.getColuna()] = 0;

        switch (peca.getTipo()) {
            case 3:
                matrix[peca.getLinha() + 1][peca.getColuna()] = 3;
                break;
            case 5:
                matrix[peca.getLinha() + 2][peca.getColuna()] = 5;
                break;
            case 7:
                matrix[peca.getLinha() + 3][peca.getColuna()] = 7;
                break;
            case 9:
                matrix[peca.getLinha() + 4][peca.getColuna()] = 9;
                break;
        }
        for (Peca p : pecas) {
            if (p.getLinha() == peca.getLinha() && p.getColuna() == peca.getColuna()) {
                p.getPosicao().setLinha(p.getPosicao().getLinha() + 1);
            }
        }
    }

    public void moveLeft(Peca peca) {
        //System.out.println("Moving Left(" + peca.getLinha() + "," + peca.getColuna() + ")");
        matrix[peca.getLinha()][peca.getColuna() - 1] = matrix[peca.getLinha()][peca.getColuna()];
        switch (peca.getTipo()) {
            case 1:
            case 2:
                matrix[peca.getLinha()][peca.getColuna()] = 0;
                break;
            case 4:
                matrix[peca.getLinha()][peca.getColuna() + 1] = 0;
                break;
            case 6:
                matrix[peca.getLinha()][peca.getColuna() + 2] = 0;
                break;
            case 8:
                matrix[peca.getLinha()][peca.getColuna() + 3] = 0;
                break;
        }

        for (Peca p : pecas) {
            if (p.getLinha() == peca.getLinha() && p.getColuna() == peca.getColuna()) {
                p.getPosicao().setColuna(p.getPosicao().getColuna() - 1);
            }
        }
    }

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!" + "line:" + line + " column:" + column + "Matrix[" + matrix.length + "][" + matrix[0].length + "]");
        }
        return matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return (line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length);
    }

    public boolean isValidMove(int line, int column, Peca peca) {
        if(line==linhaCarro && column==5 && peca.getTipo()!=1){
            return false;
        } else {
            return matrix[line][column] == 0;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EightPuzzleState)) {
            return false;
        }

        EightPuzzleState o = (EightPuzzleState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public Object clone() {
        return new EightPuzzleState(matrix);
    }

    private transient ArrayList<EightPuzzleListener> listeners = new ArrayList<EightPuzzleListener>(3);

    public synchronized void removeListener(EightPuzzleListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(EightPuzzleListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(EightPuzzleEvent pe) {
        for (EightPuzzleListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }

    
    public Iterable<Peca> getListaPecas() {
        return pecas;
    }

    // HEURISTICAS ------------------------------------------------------------
    
    public double computeTileDistance() {
        double h = 0;
        h = 5 - colunaCarro;
        return h;
    }
    
    public double computeNumberOfPiecesInTheWay() {
       double h = 0;
       for (int j = colunaCarro + 1; j < matrix.length; j++) {
           if (matrix[linhaCarro][j] != 0) {
               h++;
           }
       }
       return h;      
    }
    
    public double computeNumberOfPiecesInFrontOfTheCar() {
        double h = 0;
        for (int i = colunaCarro + 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    h++;
                }
            }
        }
        return h;
    }
    
    public double computeSizeOfObjectsOnPath(){
        double h = 0;
       for (int j = colunaCarro + 1; j < matrix.length; j++) {
           switch (matrix[linhaCarro][j]) {
                case 2:
                    h++;
                    break;
                case 3:
                    h++;
                    break;
                case 4:
                    h=h+2;
                    break;
                case 5:
                    h=h+2;
                    break;
                case 6:
                    h=h+3;
                    break;
                case 7:
                    h=h+3;
                    break;
                case 8:
                    h=h+4;
                    break;
                case 9:
                    h=h+4;
                    break;
           }
       }
       return h;      
    }
    
    
    // heuristicas 
    /*
    public double computeNumberOfTilesRightSide(){
        double h = 0;
        for(int i = 0; i<matrix.length; i++){
            for(int j = matrix.length/2; j<matrix.length; j++){
                if (matrix[i][j] > 1) {
                    h++;
                }
            }
        }
        return h;
    }
    
    
    public double computeNumberOfTilesOpositeHeightOfForklift(){ //para usar com o right side
        double h = 0;
        int aux = matrix.length/2;
        if (linhaCarro <= matrix.length/2) {
            for(int i = 0; i <= aux; i++){
                for(int j = 0; j<matrix.length; j++){
                    if (matrix[i][j] > 1) {
                        h++;
                    }
                }
            }
        }else{
            for(int i = aux; i <= matrix.length; i++){
                for(int j = 0; j<matrix.length; j++){
                    if (matrix[i][j] > 1) {
                        h++;
                    }
                }
            }
        
        }
        return h;
    }
    */
    
    
}
