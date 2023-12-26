# Deserted-Island-Generator
Generate your very own deserted island!

![Screenshot 2023-12-19 203157](https://github.com/maxk7/Deserted-Island-Generator/assets/43018603/2d181c3c-6f8c-4419-8104-af039bd1933c)

Based on four user inputs (large detail, medium detail, fine detail, and size), Deserted Island Generator uses [Open Simplex's](https://github.com/lmas/opensimplex) noise to create unique terrain resembling a deserted island!

## Using Perlin noise to generate terrain

Perlin noise is a procedural texture mapping technique developed by Ken Perlin in the 1980s. It is widely used in computer graphics and simulation to generate natural-looking patterns and terrains. The key properties of Perlin noise, such as smooth transitions and coherent structures, make it an ideal choice for terrain generation in video games and simulations.

In this project, Perlin noise is employed to generate heightmaps, which are then used to define the elevation and features of the terrain. By combining multiple layers of Perlin noise with different frequencies and amplitudes, the project creates a rich and detailed terrain landscape that resembles a small island.
