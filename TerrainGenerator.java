import java.awt.*;

// TerrainGenerator class using OpenSimplex2S
public class TerrainGenerator {
    private long seed1;
    private long seed2;
    private long seed3;

    private long distanceModifier;

    public TerrainGenerator(long seed1, long seed2, long seed3) {
        this.seed1 = seed1;
        this.seed2 = seed2;
        this.seed3 = seed3;
        this.distanceModifier = 230;
    }

    public double getDistanceModifier() {
        return distanceModifier;
    }

    public void setDistanceModifier(long distanceModifier) {
        this.distanceModifier = distanceModifier;
    }

    public long getSeed1() {
        return seed1;
    }

    public void setSeed1(long seed1) {
        this.seed1 = seed1;
    }

    public long getSeed2() {
        return seed2;
    }

    public void setSeed2(long seed2) {
        this.seed2 = seed2;
    }

    public long getSeed3() {
        return seed3;
    }

    public void setSeed3(long seed3) {
        this.seed3 = seed3;
    }

    public int[][] generateTerrain(int width, int height) {
        int[][] terrain = new int[width][height];

        double scale = 0.0075;
        double center_x = width / 2.0;
        double center_y = height / 2.0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Calculate distance from the center
                double distanceToCenter = Math.abs(x - center_x) + Math.abs(y - center_y);

                // Combine values from three noise layers
                double value1 = OpenSimplex2S.noise2(seed1, x * scale, y * scale) / 1.25 + 0.5;
                double value2 = OpenSimplex2S.noise2(seed2, x * scale, y * scale) / 2 + 0.5;
                double value3 = OpenSimplex2S.noise2(seed3, x * scale, y * scale) / 2.25 + 0.5;

                // Combine the values to create a more realistic elevation map
                double combinedValue = (value1 + value2 + value3) / 3.0 - distanceToCenter * distanceToCenter / (distanceModifier * width);

                terrain[x][y] = getColor(combinedValue);
            }
        }

        return terrain;
    }


    private int getColor(double value) {
        if (value < 0.2) {
            return new Color(1, 92, 157).getRGB(); // Deep Ocean
        } else if (value < 0.3) {
            return new Color(0, 119, 182).getRGB(); // Ocean
        } else if (value < 0.4) {
            return new Color(189, 224, 254).getRGB(); // Shallow Ocean
        } else if (value < 0.48) {
            return new Color(255, 255, 204).getRGB(); // Sand
        } else if (value < 0.6) {
            return new Color(221, 229, 182).getRGB(); // Light-Grass
        } else {
            return new Color(173, 193, 120).getRGB(); // Grass
        }
    }
}
