import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CanvasWindow extends JFrame {
    private final CanvasPanel canvasPanel;
    private final JSlider seed1Slider;
    private final JSlider seed2Slider;
    private final JSlider seed3Slider;
    private final JSlider distanceModifierSlider;

    public CanvasWindow() {
        // Set the title of the window
        setTitle("Terrain Generator");

        // Set the size of the window
        setSize(1200, 800);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a layered pane to manage the layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // Use absolute positioning
        layeredPane.setSize(getSize());

        // Create a custom JPanel for drawing
        TerrainGenerator terrainGenerator = new TerrainGenerator(1, 1, 1);
        canvasPanel = new CanvasPanel(terrainGenerator);

        // Set the size of the canvas panel to cover the entire layered pane
        canvasPanel.setSize(layeredPane.getSize());

        // Add the custom panel to the layered pane (lower layer)
        layeredPane.add(canvasPanel, JLayeredPane.DEFAULT_LAYER);

        // Add sliders to the layered pane (upper layer)
        seed1Slider = createSlider("Large Detail", 1, 333, (int) terrainGenerator.getSeed1());
        seed2Slider = createSlider("Medium Detail", 1, 666, (int) terrainGenerator.getSeed2());
        seed3Slider = createSlider("Fine Detail", 1, 1000, (int) terrainGenerator.getSeed3());
        distanceModifierSlider = createSlider("Islandness", 1, 250, (int) terrainGenerator.getDistanceModifier());

        distanceModifierSlider.setInverted(true);

        // Set the location of the sliders panel on the layered pane
        seed1Slider.setBounds(10, 10, 200, 50);
        seed2Slider.setBounds(10, 70, 200, 50);
        seed3Slider.setBounds(10, 130, 200, 50);
        distanceModifierSlider.setBounds(10, 190, 200, 50);

        layeredPane.add(seed1Slider, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(seed2Slider, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(seed3Slider, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(distanceModifierSlider, JLayeredPane.PALETTE_LAYER);

        // Set the layered pane as the content pane of the frame
        setContentPane(layeredPane);

        // Add change listeners to sliders
        seed1Slider.addChangeListener(new SliderChangeListener(terrainGenerator, 1));
        seed2Slider.addChangeListener(new SliderChangeListener(terrainGenerator, 2));
        seed3Slider.addChangeListener(new SliderChangeListener(terrainGenerator, 3));
        distanceModifierSlider.addChangeListener(new SliderChangeListener(terrainGenerator, 4));

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Add a key listener to generate new terrain when a key is pressed
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                canvasPanel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Make the window focusable to receive key events
        setFocusable(true);

        // Make the window visible
        setVisible(true);

        // Request focus so that the window can receive key events
        requestFocus();
    }

    private JSlider createSlider(String label, int min, int max, int value) {
        JSlider slider = new JSlider(min, max, value);
        slider.setBorder(BorderFactory.createTitledBorder(label));
        return slider;
    }

    private class SliderChangeListener implements ChangeListener {
        private final TerrainGenerator terrainGenerator;
        private final int sliderIndex;

        public SliderChangeListener(TerrainGenerator terrainGenerator, int sliderIndex) {
            this.terrainGenerator = terrainGenerator;
            this.sliderIndex = sliderIndex;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            long value = source.getValue();
            switch (sliderIndex) {
                case 1:
                    terrainGenerator.setSeed1(value);
                    break;
                case 2:
                    terrainGenerator.setSeed2(value);
                    break;
                case 3:
                    terrainGenerator.setSeed3(value);
                    break;
                case 4:
                    terrainGenerator.setDistanceModifier(value);
                    break;
            }
            canvasPanel.repaint();
        }
    }

    // Custom JPanel for drawing
    private static class CanvasPanel extends JPanel {
        private final TerrainGenerator terrainGenerator;

        public CanvasPanel(TerrainGenerator terrainGenerator) {
            this.terrainGenerator = terrainGenerator;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();

            // Generate terrain based on panel size
            int[][] terrain = terrainGenerator.generateTerrain(width, height);

            // Draw terrain
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    // Accessing array elements - check that x and y are within bounds
                    g.setColor(new Color(terrain[x][y]));
                    g.drawRect(x, y, 1, 1);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            // Set a preferred size for the CanvasPanel
            return new Dimension(800, 600); // Adjust the size as needed
        }
    }

    public static void main(String[] args) {
        // Create and run the application
        SwingUtilities.invokeLater(CanvasWindow::new);
    }
}
