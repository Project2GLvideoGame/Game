package game.IA.PathIA;

public class Map {
    int[][] map;

    public Map(int width, int height ) {
        this.map = new int[width][height];
    }

    public void addWall(int x, int y , int width, int height){
        for (int i = 0; i <width; i++) {
            for (int j = 0; j < height; j++) {
                this.map[x+i][y+j]= 0;
            }
        }
    }
    
}
