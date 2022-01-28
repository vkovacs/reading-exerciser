package crs.readingexerciser

import javazoom.jl.player.Player

class Application {
    static void main(String[] args) {
        FileInputStream fis = new FileInputStream("output.mp3");
        Player playMP3 = new Player(fis);
        playMP3.play();
    }
}
