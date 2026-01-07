package birthdaycard;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CartaoGUI extends JFrame {

    private JLabel mensagem;
    private JLabel imagem;
    private JButton botao;

    public CartaoGUI() {
        setTitle("🎂 Cartão de Aniversário 🎉");
        setSize(350, 175);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(255, 240, 245));

        mensagem = new JLabel("Clique para abrir seu cartão 💌", SwingConstants.CENTER);
        mensagem.setFont(new Font("Arial", Font.BOLD, 20));
        mensagem.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        imagem = new JLabel("", SwingConstants.CENTER);

        botao = new JButton("Abrir Cartão 🎁");
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.addActionListener(e -> abrirCartao());

        painel.add(mensagem, BorderLayout.NORTH);
        painel.add(imagem, BorderLayout.CENTER);
        painel.add(botao, BorderLayout.SOUTH);

        add(painel);
        setVisible(true);
    }

    private void abrirCartao() {
        mensagem.setText(
                "<html>  🎉 Parabéns!!! 🎂<br><br>" +
                        "Isso deu muito trabalho,<br>" +
                        "mas por você valeu a pena! 💖<br><br>" +
                        "Beijo grande, amo você!</html>"
        );

        // Aumenta a janela depois de abrir
        setSize(600, 550);

        // Carregar imagem corretamente do resources
        URL imgUrl = getClass().getClassLoader().getResource("images/bolo.png");
        imagem.setIcon(new ImageIcon(imgUrl));

        // Tocar som corretamente do resources
        tocarSom("song/parabens.wav");

        botao.setEnabled(false);
    }

    private void tocarSom(String caminho) {
        try {
            URL url = getClass().getClassLoader().getResource(caminho);

            if (url == null) {
                throw new RuntimeException("Arquivo não encontrado: " + caminho);
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao tocar o som 😢\n" + e.getMessage());
        }
    }
}
