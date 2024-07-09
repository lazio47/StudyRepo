
public class Aviao {

    private int [] [] executive;
    private int [] [] turist;

    public Aviao(int [] [] executive, int [] [] turist) {
        this.executive = executive;
        this.turist = turist;
    }


    //getters and setters
    public int[][] getExecutive() {
        return executive;
    }

    public void setExecutive(int[][] executive) {
        this.executive = executive;
    }

    public int[][] getTurist() {
        return turist;
    }

    public void setTurist(int[][] turist) {
        this.turist = turist;
    }

    //capacity of each class
    public int getExecutiveSeats() {
        if (executive.length == 0) {
            return 0;
        }
        return executive.length * executive[0].length;
    }

    public int getTuristSeats() {
        return turist.length * turist[0].length;
    }

    // ocupied seats of each class
    public int getExecutiveOccuppiedSeats() {
        int occuppied = 0;
        for (int i = 0; i < executive.length; i++) {
            for (int j = 0; j < executive[i].length; j++) {
                if (executive[i][j] == 1) {
                    occuppied++;
                }
            }
        }
        return occuppied;
    }

    public int getTuristOccuppiedSeats() {
        int occuppied = 0;
        for (int i = 0; i < turist.length; i++) {
            for (int j = 0; j < turist[i].length; j++) {
                if (turist[i][j] == 1) {
                    occuppied++;
                }
            }
        }
        return occuppied;
    }

}
