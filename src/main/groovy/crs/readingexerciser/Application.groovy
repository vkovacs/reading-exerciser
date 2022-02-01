package crs.readingexerciser

import com.google.protobuf.ByteString
import crs.readingexerciser.text.TextService
import crs.readingexerciser.text.speech.TextToSpeechService
import javazoom.jl.player.Player

import javax.swing.*
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class Application {
    static TextService textService = new TextService("src/main/resources/Misi.txt")

    static void main(String[] args) {

        def word = textService.randomWord()

        JFrame frame = new JFrame("Olvasás Gyakoroltató")
        JLabel jLabel = new JLabel()
        jLabel.setFont(new Font("Serif", Font.PLAIN, 42))
        jLabel.text = word
        jLabel.setBounds(50, 20, 600, 50)
        jLabel.addMouseListener(new MouseAdapter()
        {
             void mouseClicked(MouseEvent e)
            {
                jLabel.text = jLabel.text == word.toUpperCase() ? word.toLowerCase() : word.toUpperCase()

            }
        })
        JButton readButton = new JButton("Olvass")
        readButton.setBounds(50, 90, 95, 30)
        readButton.addActionListener((e) -> {
            ByteString speech = TextToSpeechService.say(word)
            def stream = new ByteArrayInputStream(speech.toByteArray())
            Player playMP3 = new Player(stream)
            playMP3.play()
        })

        JButton nextButton = new JButton("Következő")
        nextButton.setBounds(200, 90, 95, 30)
        nextButton.addActionListener((e) -> {
            word = textService.randomWord()
            jLabel.setText(word)
        })


        JTextArea sayTextArea = new JTextArea()
        sayTextArea.setBounds(50,130,400,300)
        sayTextArea.setFont(new Font("Serif", Font.PLAIN, 18))
        sayTextArea.setWrapStyleWord(true)

        JButton sayButton = new JButton("Kimond")
        sayButton.setBounds(450, 130, 95, 30)
        sayButton.addActionListener((e) -> {
            if (!sayTextArea.text.isEmpty()) {
                ByteString speech = TextToSpeechService.say(sayTextArea.text)
                def stream = new ByteArrayInputStream(speech.toByteArray())
                Player playMP3 = new Player(stream)
                playMP3.play()
            }
        })

        frame.add(readButton)
        frame.add(jLabel)
        frame.add(nextButton)
        frame.add(sayTextArea)
        frame.add(sayButton)
        frame.setSize(600, 500)
        frame.setLayout(null)
        frame.setLocationRelativeTo(null)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true)
    }
}
