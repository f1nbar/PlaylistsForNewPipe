

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="">
    <img src="icon.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">PlaylistsForNewPipe</h3>

  <p align="center">
    Extract playlists from exported NewPipe database files.
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About](#about-the-project)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
*  [Usage](#usage)
* [Acknowledgements](#acknowledgements)

<!-- ABOUT THE PROJECT -->
## About

[![Screen Shot][product-screenshot]](https://example.com)

A simple Java Swing tool to extract your playlists from an exported NewPipe database and format them into a direct link to a Youtube or Invidio.us playlist. 

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites
* [Java](https://www.java.com/en/download/) installed on your machine
* [NewPipe](https://newpipe.schabi.org/) installed on an Android device
### Installation

1. Download the latest jar file from the [releases](https://github.com/f1nbar/PlaylistsForNewPipe/releases) section 
2. Double click to run (if on linux or Mac OS you may need to make jar executable first using chmod) or
```sh
java -jar PlaylistsForNewPipe.jar
```

<!-- USAGE EXAMPLES -->
## Usage
1. First we need to create a database to pull our playlists from. To do this open NewPipe and navigate to Settings->Content->Export database.
2. Transfer the NewPipeData-*******_******.zip file to your computer and extract to a location of your choice.
3. Run the PlaylistsForNewPipe.jar file, choose your playlist and format.
4. Enjoy your newly exported playlist! Press reset to export again or choose a new playlist. 

N.B At time of release there are intermittent issues with links to invidio.us playlists being blocked. This isn't a fault of the tool to my knowledge. 

<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc)
* [NewPipe](https://newpipe.schabi.org/)
* [Invidious](https://www.invidio.us/)
* [Logo](https://www.instagram.com/photoshop4hire_/)






[product-screenshot]: images/screenshot.png
