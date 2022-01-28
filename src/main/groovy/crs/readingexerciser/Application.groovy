package crs.readingexerciser

import com.google.protobuf.ByteString
import crs.readingexerciser.text.TextService
import crs.readingexerciser.text.speech.TextToSpeechService
import javazoom.jl.player.Player

class Application {
    static void main(String[] args) {

        TextService textService = new TextService("src/main/resources/Peterke.txt")

        def word = textService.randomWord()
        println word
        System.in.newReader().readLine()

        ByteString speech = TextToSpeechService.say(word)

        def stream = new ByteArrayInputStream(speech.toByteArray())
        Player playMP3 = new Player(stream)
        playMP3.play();
    }
}
