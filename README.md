# Deserted-Island-Generator
Generate your very own deserted island!

![Screenshot 2023-12-19 203157](https://github.com/maxk7/Deserted-Island-Generator/assets/43018603/2d181c3c-6f8c-4419-8104-af039bd1933c)

Based on four user inputs (large detail, medium detail, fine detail, and size), Deserted Island Generator uses [Open Simplex's](https://github.com/lmas/opensimplex) noise to create unique terrain resembling a deserted island!

## Using Perlin noise to generate terrain

Perlin noise is a procedural texture mapping technique developed by Ken Perlin in the 1980s. It is widely used in computer graphics and simulation to generate natural-looking patterns and terrains. The key properties of Perlin noise, such as smooth transitions and coherent structures, make it an ideal choice for terrain generation in video games and simulations.

In this project, Perlin noise is employed to generate heightmaps, which are then used to define the elevation and features of the terrain. By combining multiple layers of Perlin noise with different frequencies and amplitudes, the project creates a rich and detailed terrain landscape that resembles a small island.

### Terrain Generation Process

1. **Initialization**: Initialize the terrain grid and set the initial parameters.

2. **Perlin Noise Generation**: Generate Perlin noise maps for various terrain features, such as elevation, moisture, and temperature.

3. **Terrain Mapping**: Map the Perlin noise values to terrain attributes, such as height, slope, and texture.

4. **Post-processing**: Apply additional effects and optimizations to enhance the visual quality and realism of the terrain.

5. **Rendering**: Render the generated terrain using graphics libraries and display it to the user.

### Installation

```bash
git clone https://github.com/your-username/deserted-island-terrain-generator.git
```

2. Navigate to the project directory:

```bash
cd deserted-island-terrain-generator
```

3. Compile and run the project:

```bash
javac CanvasWindow.java OpenSimplexS2.java TerrainGenerator.java
java CanvasWindow
```

## Customization

The project provides various parameters that can be adjusted to customize the generated terrain. These parameters include:

- **Terrain Size**: Adjust the dimensions of the terrain grid.
- **Noise Scale**: Modify the scale of the Perlin noise patterns.
- **Feature Intensity**: Control the intensity of terrain features, such as hills, valleys, and beaches.
- **Visual Effects**: Enable or disable additional visual effects, such as gradual tide simulation.
