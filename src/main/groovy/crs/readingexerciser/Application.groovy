package crs.readingexerciser

import com.google.protobuf.ByteString
import crs.readingexerciser.text.TextService
import crs.readingexerciser.text.speech.TextToSpeechService
import javazoom.jl.player.Player

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class Application {
    static void main(String[] args) {
        TextService textService = new TextService("src/main/resources/Misi.txt")
        def word = textService.randomWord()

        JFrame f = new JFrame("Button Example")
        final JTextField tf = new JTextField()
        tf.setFont(new Font("Serif", Font.PLAIN, 22))
        tf.setBounds(50, 50, 350, 50)
        JButton b = new JButton("Olvass")
        b.setBounds(50, 120, 95, 30)
        b.addActionListener(new ActionListener() {
            void actionPerformed(ActionEvent e) {
                ByteString speech = TextToSpeechService.say(word)
                def stream = new ByteArrayInputStream(speech.toByteArray())
                Player playMP3 = new Player(stream)
                playMP3.play()
                word = textService.randomWord()
                tf.setText(word)
            }
        })
        f.add(b)
        f.add(tf)
        f.setSize(400, 400)
        f.setLayout(null)
        f.setVisible(true)

        tf.text = word
    }
}
