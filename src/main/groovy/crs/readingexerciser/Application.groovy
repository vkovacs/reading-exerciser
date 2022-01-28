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

        JFrame f = new JFrame("Olvasás Gyakoroltató")
        JLabel jLabel = new JLabel()
        jLabel.setFont(new Font("Serif", Font.PLAIN, 42))
        jLabel.text = word
        jLabel.setBounds(50, 20, 600, 50)
        JButton readButton = new JButton("Olvass")
        readButton.setBounds(50, 90, 95, 30)
        readButton.addActionListener(new ActionListener() {
            void actionPerformed(ActionEvent e) {
                ByteString speech = TextToSpeechService.say(word)
                def stream = new ByteArrayInputStream(speech.toByteArray())
                Player playMP3 = new Player(stream)
                playMP3.play()

            }
        })
        JButton nextButton = new JButton("Következő")
        nextButton.setBounds(200, 90, 95, 30)
        nextButton.addActionListener(new ActionListener() {
            void actionPerformed(ActionEvent e) {
                word = textService.randomWord()
                jLabel.setText(word)
            }
        })
        f.add(readButton)
        f.add(jLabel)
        f.add(nextButton)
        f.setSize(600, 180)
        f.setLayout(null)
        f.setLocationRelativeTo(null)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true)
    }
}
