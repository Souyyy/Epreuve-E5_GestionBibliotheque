
// Importe les classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MAIN {
    public static void main(String[] args) {
        // Crée une fenêtre principale
        JFrame window = new JFrame();
        window.setTitle("Gestion de bibliothèque"); // Définit le titre de la fenêtre
        window.setSize(650, 500); // Définit la taille de la fenêtre
        window.setResizable(false); // Empêche le redimensionnement de la fenêtre
        window.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'application lorsque la fenêtre est fermée

        // Crée un panneau central avec une disposition en grille
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Titre
        JLabel titre = new JLabel("<html><u>Gestion de la bibliothèque</u></html>");
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        // Définit les paramètres GridBagConstraints pour le titre
        gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
        gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
        gbc.gridy++; // Passe à la ligne suivante
        gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
        centerPanel.add(titre, gbc); // Ajoute le titre au panneau central
        gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
        gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

        // Crée un panneau pour les boutons avec une disposition en grille
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton adherentButton = new JButton("Gestion des adhérents");
        JButton auteurButton = new JButton("Gestion des auteurs");
        JButton livreButton = new JButton("Gestion des livres");
        JButton empruntButton = new JButton("Gestion des emprunts");

        // Ajoute les boutons au panneau des boutons avec un alignement vertical
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        buttonPanel.add(adherentButton, gbc);
        gbc.gridy++;
        buttonPanel.add(auteurButton, gbc);
        gbc.gridy++;
        buttonPanel.add(livreButton, gbc);
        gbc.gridy++;
        buttonPanel.add(empruntButton, gbc);

        // Ajoutez un ActionListener au bouton pour ouvrir le programme ADHERENT.java
        adherentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ADHERENT.main(new String[0]); // Ouvre le programme ADHERENT
            }
        });

        // Ajoutez un ActionListener au bouton pour ouvrir le programme AUTEUR.java
        auteurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AUTEUR.main(new String[0]); // Ouvre le programme AUTEUR
            }
        });

        // Ajoutez un ActionListener au bouton pour ouvrir le programme LIVRE.java
        livreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LIVRE.main(new String[0]); // Ouvre le programme LIVRE
            }
        });

        // Ajoutez un ActionListener au bouton pour ouvrir le programme EMPRUNT.java
        empruntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMPRUNT.main(new String[0]); // Ouvre le programme EMPRUNT
            }
        });

        // Ajoute le panneau des boutons au panneau central
        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(buttonPanel, gbc);

        // Définit le panneau central comme contenu de la fenêtre et l'affiche
        window.setContentPane(centerPanel);
        window.setVisible(true);
    }
}
