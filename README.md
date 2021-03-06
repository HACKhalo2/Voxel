![Voxel](/docs/images/Voxel-Logo.png?raw=true)

Voxel is a Minecraft inspired game made using Java and LWJGL3 library.

[![Build Status](https://travis-ci.org/Lux-Vacuos/Voxel.svg?branch=develop)](https://travis-ci.org/Lux-Vacuos/Voxel)


## Development

The main development are taken in the 'develop' branch, for stable code please use the 'master' branch.

## Project Layout

The project is divided into sub projects within these we find different parts of code, as the client, server, etc.

This is divided into.

### Client
This contains code that is used only for the client side, like the graphics engine.

### Server
This contains the code from the server version.

### Launcher
This contains the launcher used to download all the libraries and run the client.

### Universal
This contains code shared between client and server, in this case some parts of the netcode and API for mods.

### Ashley
This is a fork of libgdx/Ashley that was modified so it can run without all the libgdx library.

### Utils
This contains code that is used in all other sub projects, some code from libgdx and LWJGL2 was exported so the other projects do not have to implement the same code.
