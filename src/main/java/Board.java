public class Board {

    private int[][] blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks;
    }

    // board dimension n
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int expectedValueForPosition = 1;
        int blocksCountOutOfPosition = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if(blocks[i][j] != 0 && blocks[i][j] != expectedValueForPosition){
                    blocksCountOutOfPosition++;
                }
                expectedValueForPosition++;
            }
        }
        return blocksCountOutOfPosition;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[i][j] != 0) {
                    int expi = (blocks[i][j] - 1) / dimension();
                    int expj = (blocks[i][j] - 1) % dimension();
                    int disi = i - expi;
                    int disj = j - expj;

                    count += Math.abs(disi) + Math.abs(disj);
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int copy[][] = new int[dimension()][dimension()];
        System.arraycopy(blocks, 0, copy, 0, dimension());
        if(copy[0][0] != 0 && copy[0][1] != 0){
            swap(copy, 0, 0, 0, 1);
        }else {
            swap(copy, 1, 0, 1, 1);
        }
        return new Board(copy);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if(null == y){
            return false;
        }
        if(y == this){
            return true;
        }
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if(((Board) y).blocks[i][j] != blocks[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
//    public Iterable<Board> neighbors() {
//    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension()).append("\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private void swap(int[][] blocks, int i1, int j1, int i2, int j2){
        int temp = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = temp;
    }


    // unit tests (not graded)
    public static void main(String[] args) {
    }
}
