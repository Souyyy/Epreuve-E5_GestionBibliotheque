
// Importe les classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ADHERENT extends JFrame {

  public static void main(String[] args) {
    // Crée une fenêtre principale
    JFrame window = new JFrame();
    window.setTitle("Adhérent"); // Définit le titre de la fenêtre
    window.setSize(650, 500); // Définit la taille de la fenêtre
    window.setResizable(false); // Empêche le redimensionnement de la fenêtre
    window.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
    window.setLayout(new GridBagLayout()); // Définit la disposition de la fenêtre comme une grille

    // Crée un panneau central avec une disposition en grille
    JPanel centerPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;

    // Title
    JLabel titre = new JLabel("<html><u>Gestion des adhérents</u></html>"); // Crée un JLabel avec le titre
    titre.setHorizontalAlignment(SwingConstants.CENTER); // Aligner le texte au centre

    // Définit les paramètres GridBagConstraints pour le titre
    gbc.gridx = 0; // Colonne 0
    gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
    gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
    gbc.gridy++; // Passe à la ligne suivante
    gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
    centerPanel.add(titre, gbc); // Ajoute le titre au panneau central
    gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
    gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges
    gbc.gridy++; // Passe à la ligne suivante dans la grille

    // Crée un panneau pour les boutons avec une disposition en grille
    JPanel buttonPanel = new JPanel();
    JButton addButton = new JButton("Crée un adhérent");
    JButton modifyButton = new JButton("Modifier un adhérent");
    JButton deleteButton = new JButton("Supprimer un adhérent");

    // Code pour ajouter un adhérent
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        // Cacher le buttonPanel
        buttonPanel.setVisible(false);

        // Creer un nouveau panel pour la création d'un nouveau adhérent
        JPanel addAdherentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel titre = new JLabel("<html><u>Crée un adhérent</u></html>");
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        // Définit les paramètres GridBagConstraints pour le titre
        gbc.gridx = 0; // Colonne 0
        gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
        gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
        gbc.gridy++; // Passe à la ligne suivante
        gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
        addAdherentPanel.add(titre, gbc); // Ajoute le titre au panneau central
        gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
        gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges
        gbc.gridy++; // Passe à la ligne suivante dans la grille

        // Input NOM
        gbc.gridy++;
        JLabel label_nom = new JLabel("Nom de l'adhérent:"); // Crée un JLabel pour le champ de saisie
        label_nom.setHorizontalAlignment(SwingConstants.LEFT); // Aligne le texte à gauche
        addAdherentPanel.add(label_nom, gbc); // Ajoute le label
        JTextField nomText = new JTextField(); // Crée un champ de texte pour la saisie
        nomText.setPreferredSize(new Dimension(200, 25)); // Définit la taille préférée du champ de texte
        gbc.gridx++; // Passe à la colonne suivante dans la grille
        addAdherentPanel.add(nomText, gbc);

        // Input PRENOM
        gbc.gridy++;
        JLabel label_prebom = new JLabel("Prénom de l'adhérent:"); // Crée un JLabel pour le champ de saisie
        label_prebom.setHorizontalAlignment(SwingConstants.LEFT); // Aligne le texte à gauche
        gbc.gridx = 0; // Réinitialise la colonne à 0
        gbc.gridy++; // Passe à la ligne suivante dans la grille
        addAdherentPanel.add(label_prebom, gbc); // Ajoute le label
        JTextField prenomText = new JTextField(); // Crée un champ de texte pour la saisie
        prenomText.setPreferredSize(new Dimension(200, 25)); // Définit la taille préférée du champ de texte
        gbc.gridx++; // Passe à la colonne suivante dans la grille
        addAdherentPanel.add(prenomText, gbc); // Ajoute le champ de texte

        // Input EMAIL
        JLabel label_email = new JLabel("Email de l'adhérent:"); // Crée un JLabel pour le champ de saisie
        label_email.setHorizontalAlignment(SwingConstants.LEFT); // Aligne le texte à gauche
        gbc.gridx = 0; // Réinitialise la colonne à 0
        gbc.gridy++; // Passe à la ligne suivante dans la grille
        addAdherentPanel.add(label_email, gbc); // Ajoute le label
        JTextField mailText = new JTextField(); // Crée un champ de texte pour la saisie
        mailText.setPreferredSize(new Dimension(200, 25)); // Définit la taille préférée du champ de texte
        gbc.gridx++; // Passe à la colonne suivante dans la grille
        addAdherentPanel.add(mailText, gbc); // Ajoute le champ de texte

        // Input RETOUR
        gbc.insets = new Insets(30, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy++;
        JButton Back_Button = new JButton("Retour");
        addAdherentPanel.add(Back_Button, gbc);
        // Ajoute un ActionListener au bouton "Retour" pour gérer l'événement de clic
        Back_Button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            buttonPanel.setVisible(true); // Rend le panel des boutons visible
            addAdherentPanel.setVisible(false); // Cache le panel de création d'adhérent
            window.setContentPane(centerPanel); // Actualise le contenu de la fenêtre principale avec le panneau central
            window.revalidate(); // Rend la fenêtre valide et prête à être affichée
          }
        });

        // Input SUBMIT
        JButton Submit_CreateButton = new JButton("Créer un adhérent");
        gbc.gridx++;
        addAdherentPanel.add(Submit_CreateButton, gbc);
        // Ajoute un ActionListener au bouton pour gérer l'événement de clic
        Submit_CreateButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // Récupère les valeurs saisies dans les champs de texte
            String nom = nomText.getText().toLowerCase();
            String prenom = prenomText.getText().toLowerCase();
            String mail = mailText.getText().toLowerCase();

            // Vérifie si les champs ne sont pas vides et si l'email est valide
            if (!nom.isEmpty() && !nom.trim().isEmpty() && !prenom.isEmpty() && !prenom.trim().isEmpty()
                && !mail.isEmpty() && !mail.trim().isEmpty() && isValidEmail(mail)) {

              // Formate la première lettre en majuscule
              nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
              prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);

              try {
                // Établit la connexion à la base de données
                Connection conn = DB.getConnection();

                // Prépare la requête pour vérifier si l'adhérent ou l'email existent déjà
                String query = "SELECT * FROM adherent WHERE (nom=? AND prenom=?) OR email=?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, nom);
                pstmt.setString(2, prenom);
                pstmt.setString(3, mail);

                // Exécute la requête
                ResultSet res = pstmt.executeQuery();

                // Vérifie s'il y a des résultats
                if (!res.next()) {
                  // insère un nouvel adhérent dans la base de données
                  query = "INSERT INTO adherent (nom, prenom, email) VALUES (?, ?, ?)";
                  pstmt = conn.prepareStatement(query);
                  pstmt.setString(1, nom);
                  pstmt.setString(2, prenom);
                  pstmt.setString(3, mail);
                  // Exécute la requête d'insertion
                  pstmt.executeUpdate();
                  System.out.println("SUCCESS: L'adhérent a été ajouté avec succès.");
                  JOptionPane.showMessageDialog(window, "L'adhérent a été ajouté avec succès.", "Super !",
                      JOptionPane.INFORMATION_MESSAGE);
                } else {
                  // Affiche un message d'erreur si l'adhérent ou l'email existent déjà
                  System.out.println("ERROR: L'adhérent existe déjà ou l'email et déjà associé.");
                  JOptionPane.showMessageDialog(window, "L'adhérent existe déjà ou l'email et déjà associé.", "Oups !",
                      JOptionPane.ERROR_MESSAGE);
                }
              } catch (Exception exception) {
                System.out.println("ERROR: " + exception);
                JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !", JOptionPane.ERROR_MESSAGE);
              }
            } else {
              System.out.println("ERROR: Un champ ne peut pas être vide, et l'adresse e-mail doit être valide.");
              JOptionPane.showMessageDialog(window,
                  "Un champ ne peut pas être vide, et l'adresse e-mail doit être valide.", "Oups !",
                  JOptionPane.ERROR_MESSAGE);
            }
          }

          private boolean isValidEmail(String email) {
            // Validation basique de l'adresse e-mail
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            return email.matches(emailRegex);
          }

        });

        // Définit le panneau comme contenu de la fenêtre et l'affiche
        window.setContentPane(addAdherentPanel);
      }
    });

    // Code pour modifier un adhérent
    modifyButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        // Cacher le buttonPanel
        buttonPanel.setVisible(false);

        // Creer un nouveau panel pour la création d'un nouveau adherent
        JPanel EditAdherentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel titre = new JLabel("<html><u>Modifier un adhérent</u></html>");
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        // Définit les paramètres GridBagConstraints pour le titre
        gbc.gridx = 0; // Colonne 0
        gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
        gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
        gbc.gridy++; // Passe à la ligne suivante
        gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
        EditAdherentPanel.add(titre, gbc); // Ajoute le titre au panneau central
        gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
        gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

        // Crée un label pour indiquer le choix de l'adhérent
        JLabel label_choix = new JLabel("Choix de l'adhérent:");
        label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à gauche
        gbc.gridx = 0;
        gbc.gridy++;
        EditAdherentPanel.add(label_choix, gbc);
        // Crée une liste déroulante avec les noms complets des adhérents
        JComboBox<String> adherentsComboBox = new JComboBox<>();
        adherentsComboBox.setPreferredSize(new Dimension(200, 25));
        gbc.gridx++;
        EditAdherentPanel.add(adherentsComboBox, gbc);

        // Input NOM
        JLabel label_nom = new JLabel("Nom de l'adhérent:");
        label_nom.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy++;
        EditAdherentPanel.add(label_nom, gbc);
        JTextField nomText = new JTextField();
        nomText.setPreferredSize(new Dimension(200, 25));
        gbc.gridx++;
        EditAdherentPanel.add(nomText, gbc);

        // Input PRENOM
        JLabel label_prenom = new JLabel("Prénom de l'adhérent:");
        label_prenom.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy++;
        EditAdherentPanel.add(label_prenom, gbc);
        JTextField prenomText = new JTextField();
        prenomText.setPreferredSize(new Dimension(200, 25));
        gbc.gridx++;
        EditAdherentPanel.add(prenomText, gbc);

        // Input MAIL
        JLabel label_mail = new JLabel("Mail de l'adhérent:");
        label_mail.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy++;
        EditAdherentPanel.add(label_mail, gbc);
        JTextField mailText = new JTextField();
        mailText.setPreferredSize(new Dimension(200, 25));
        gbc.gridx++;
        EditAdherentPanel.add(mailText, gbc);

        // Input RETOUR
        gbc.insets = new Insets(30, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy++;
        JButton Back_Button = new JButton("Retour");
        EditAdherentPanel.add(Back_Button, gbc);

        Back_Button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            buttonPanel.setVisible(true);
            EditAdherentPanel.setVisible(false);
            window.setContentPane(centerPanel);
            window.revalidate();
          }
        });

        // Input SUBMIT
        JButton Submit_ModificationButton = new JButton("Modifier un adhérent");
        gbc.gridx++;
        EditAdherentPanel.add(Submit_ModificationButton, gbc);

        // Créer une liste pour stocker les IDs des adhérents
        List<String> adherentsIDs = new ArrayList<>();

        // Ajoute un ActionListener au bouton pour gérer l'événement de clic
        Submit_ModificationButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // Récupère les valeurs saisies dans les champs de texte
            String nom = nomText.getText().toLowerCase();
            String prenom = prenomText.getText().toLowerCase();
            String mail = mailText.getText().toLowerCase();

            // Vérifie si les champs ne sont pas vides et si l'email est valide
            if (!nom.isEmpty() && !nom.trim().isEmpty() && !prenom.isEmpty() && !prenom.trim().isEmpty()
                && !mail.isEmpty() && !mail.trim().isEmpty() && isValidEmail(mail)) {

              // Formate la première lettre en majuscule
              nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
              prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);

              try {
                // Établit la connexion à la base de données
                Connection conn = DB.getConnection();

                // Récupérer l'ID de l'adhérent sélectionné
                int selectedIndex = adherentsComboBox.getSelectedIndex();
                String ancienID = adherentsIDs.get(selectedIndex);
                System.out.println(ancienID);

                // Vérifier que les infos n'appartiennent pas déjà à un autre adhérent
                String querySelect = "SELECT * FROM adherent WHERE (nom=? AND prenom=? OR email=?) AND num != ?";
                PreparedStatement pstmtSelect = conn.prepareStatement(querySelect);
                pstmtSelect.setString(1, nom);
                pstmtSelect.setString(2, prenom);
                pstmtSelect.setString(3, mail);
                pstmtSelect.setString(4, ancienID);
                ResultSet resSelect = pstmtSelect.executeQuery();

                if (!resSelect.next()) {

                  // Les informations n'existent pas ou sont les mêmes que l'ancien
                  String queryUpdate = "UPDATE adherent SET nom=?, prenom=?, email=? WHERE num=?";
                  PreparedStatement pstmtUpdate = conn.prepareStatement(queryUpdate);
                  pstmtUpdate.setString(1, nom);
                  pstmtUpdate.setString(2, prenom);
                  pstmtUpdate.setString(3, mail);
                  pstmtUpdate.setString(4, ancienID);
                  pstmtUpdate.executeUpdate();
                  System.out.println(
                      "SUCCESS: L'adhérent a été modifié avec succès, pour voir la modification, merci de recharger la page.");
                  JOptionPane.showMessageDialog(window,
                      "L'adhérent a été modifié avec succès, pour voir la modification, merci de recharger la page.",
                      "Super !",
                      JOptionPane.INFORMATION_MESSAGE);
                } else {
                  System.out.println(
                      "ERROR: La modification correspond déjà à un adhérent. Impossible de modifier l'adhérent.");
                  JOptionPane.showMessageDialog(window,
                      "La modification correspond déjà à un adhérent. Impossible de modifier l'adhérent.", "Oups !",
                      JOptionPane.ERROR_MESSAGE);
                }
              } catch (Exception exception) {
                System.out.println("ERROR: " + exception);
                JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !", JOptionPane.ERROR_MESSAGE);
              }
            } else {
              System.out.println("ERROR: Un champ ne peut pas être vide, et l'adresse e-mail doit être valide.");
              JOptionPane.showMessageDialog(window,
                  "Un champ ne peut pas être vide, et l'adresse e-mail doit être valide.", "Oups !",
                  JOptionPane.ERROR_MESSAGE);
            }
          }

          private boolean isValidEmail(String email) {
            // Validation basique de l'adresse e-mail
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            return email.matches(emailRegex);
          }

        });

        try {
          // Établit la connexion à la base de données
          Connection conn = DB.getConnection();

          // Prépare la requête pour récuperer tout les adhérents
          String query = "SELECT * FROM adherent";
          PreparedStatement pstmt = conn.prepareStatement(query);

          // Exécute la requête
          ResultSet res = pstmt.executeQuery();

          // Créer une liste pour stocker les emails des adhérents
          List<String> adherentsEmails = new ArrayList<>();

          // Tant que le résultat de la requête contient des lignes de données
          while (res.next()) {
            // Récupère les informations sur l'adhérent depuis le résultat de la requête
            String id = res.getString("num");
            String nom = res.getString("nom");
            String prenom = res.getString("prenom");
            String email = res.getString("email");

            // Ajouter le nom et prénom à la liste des adhérents
            adherentsComboBox
                .addItem(nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase() + prenom.substring(1));

            // Ajouter l'ID à la liste des IDs des adhérents
            adherentsIDs.add(id);
            adherentsEmails.add(email);
          }

          // Écouteur d'événements pour la liste déroulante des adhérents
          adherentsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              // Récupérer l'indice de l'élément sélectionné dans la liste déroulante
              int selectedIndex = adherentsComboBox.getSelectedIndex();
              // Vérifier si un élément est sélectionné
              if (selectedIndex != -1) {
                // Récupérer l'email associé à l'adhérent sélectionné
                String email = adherentsEmails.get(selectedIndex);

                // Récupérer les informations de l'adhérent sélectionné dans le tableau
                String selectedAdherent = (String) adherentsComboBox.getSelectedItem();
                String[] selectedAdherentInfo = selectedAdherent.split(" ");
                String nom = selectedAdherentInfo[0];
                String prenom = selectedAdherentInfo[1];

                // Remplir les champs de texte avec les informations de l'adhérent sélectionné
                nomText.setText(nom); // Nom
                prenomText.setText(prenom); // Prénom
                mailText.setText(email); // Email
              }
            }
          });

        } catch (Exception exception) {
          System.out.println("ERROR: " + exception);
          JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !", JOptionPane.ERROR_MESSAGE);
        }

        // Définit le panneau comme contenu de la fenêtre et l'affiche
        window.setContentPane(EditAdherentPanel);

      }

    });

    // Code pour supprimer un adhérent
    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        // Cacher le buttonPanel
        buttonPanel.setVisible(false);

        // Creer un nouveau panel pour la création d'un nouveau adherent
        JPanel DeleteAdherentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel titre = new JLabel("<html><u>Supprimer un adhérent</u></html>");
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        // Définit les paramètres GridBagConstraints pour le titre
        gbc.gridx = 0; // Colonne 0
        gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
        gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
        gbc.gridy++; // Passe à la ligne suivante
        gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
        DeleteAdherentPanel.add(titre, gbc); // Ajoute le titre au panneau central
        gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
        gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

        // Selection
        JLabel label_choix = new JLabel("Choix de l'adhérent:");
        label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
        gbc.gridx = 0;
        gbc.gridy++;
        DeleteAdherentPanel.add(label_choix, gbc);
        // Crée une liste déroulante avec les noms complets des adhérents
        JComboBox<String> adherentsComboBox = new JComboBox<>();
        adherentsComboBox.setPreferredSize(new Dimension(200, 25));
        gbc.gridx++;
        DeleteAdherentPanel.add(adherentsComboBox, gbc);

        // Input RETOUR
        gbc.insets = new Insets(30, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy++;
        JButton Back_Button = new JButton("Retour");
        DeleteAdherentPanel.add(Back_Button, gbc);

        Back_Button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            buttonPanel.setVisible(true);
            DeleteAdherentPanel.setVisible(false);
            window.setContentPane(centerPanel);
            window.revalidate();
          }
        });

        // Input SUPPR
        JButton Delete_ModificationButton = new JButton("Supprimer un adhérent");
        gbc.gridx++;
        DeleteAdherentPanel.add(Delete_ModificationButton, gbc);

        // Créer une liste pour stocker les IDs des adhérents
        List<String> adherentsIDs = new ArrayList<>();

        // Ajoute un ActionListener au bouton pour gérer l'événement de clic
        Delete_ModificationButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

            try {
              // Établit la connexion à la base de données
              Connection conn = DB.getConnection();

              if (!adherentsIDs.isEmpty()) {

                // Récupérer l'ID de l'adhérent sélectionné
                int selectedIndex = adherentsComboBox.getSelectedIndex();
                String ancienID = adherentsIDs.get(selectedIndex);
                System.out.println(ancienID);

                // Vérifier si l'adhérent existe dans la table
                String queryCheck = "SELECT * FROM adherent WHERE num=?";
                PreparedStatement pstmtCheck = conn.prepareStatement(queryCheck);
                pstmtCheck.setString(1, ancienID);
                ResultSet resCheck = pstmtCheck.executeQuery();

                if (resCheck.next()) {
                  // L'adhérent existe dans la table
                  String queryCheckEmprunt = "SELECT COUNT(*) AS emprunt_count FROM emprunt WHERE id_adherent=?";
                  PreparedStatement pstmtCheckEmprunt = conn.prepareStatement(queryCheckEmprunt);
                  pstmtCheckEmprunt.setString(1, ancienID);
                  ResultSet resCheckEmprunt = pstmtCheckEmprunt.executeQuery();

                  if (resCheckEmprunt.next()) {
                    int countEmprunt = resCheckEmprunt.getInt("emprunt_count");
                    if (countEmprunt == 0) {
                      // L'adhérent n'a pas de livres en cours d'emprunt
                      String queryDelete = "DELETE FROM adherent WHERE num=?";
                      PreparedStatement pstmtDelete = conn.prepareStatement(queryDelete);
                      pstmtDelete.setString(1, ancienID);
                      pstmtDelete.executeUpdate();
                      // Supprimer l'élément de la liste adherentsIDs
                      adherentsIDs.remove(selectedIndex);
                      adherentsComboBox.removeItemAt(selectedIndex);
                      System.out.println("SUCCESS: L'adhérent a été supprimé avec succès.");
                      JOptionPane.showMessageDialog(window, "L'adhérent a été supprimé avec succès.", "Super !",
                          JOptionPane.INFORMATION_MESSAGE);
                      window.getContentPane().revalidate();
                      window.getContentPane().repaint();
                    } else {
                      // L'adhérent a des livres en cours d'emprunt
                      System.out
                          .println("ERROR: L'adhérent a des livres en cours d'emprunt. Impossible de le supprimer.");
                      JOptionPane.showMessageDialog(window,
                          "L'adhérent a des livres en cours d'emprunt. Impossible de le supprimer.", "Oups !",
                          JOptionPane.ERROR_MESSAGE);
                    }
                  } else {
                    // Erreur lors de la vérification des livres en cours d'emprunt
                    System.out.println("ERROR: Erreur lors de la vérification des livres en cours d'emprunt.");
                    JOptionPane.showMessageDialog(window,
                        "Erreur lors de la vérification des livres en cours d'emprunt.", "Oups !",
                        JOptionPane.ERROR_MESSAGE);
                  }
                } else {
                  // L'adhérent n'existe pas dans la table, afficher un message d'erreur
                  System.out.println(
                      "ERROR: L'adhérent sélectionné n'existe pas. Aucune suppression effectuée.");
                  JOptionPane.showMessageDialog(window,
                      "L'adhérent sélectionné n'existe pas. Aucune suppression effectuée.", "Oups !",
                      JOptionPane.ERROR_MESSAGE);
                }

              } else {
                System.out.println(
                    "ERROR: Aucun adhérent sélectionné. Aucune suppression effectuée.");
                JOptionPane.showMessageDialog(window,
                    "Aucun adhérent sélectionné. Aucune suppression effectuée.", "Oups !",
                    JOptionPane.ERROR_MESSAGE);
              }

            } catch (Exception exception) {
              System.out.println("ERROR: " + exception);
              JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !", JOptionPane.ERROR_MESSAGE);
            }

          }
        });

        try {
          // Établit la connexion à la base de données
          Connection conn = DB.getConnection();

          // Prépare la requête pour récuperer tout les adhérents
          String query = "SELECT * FROM adherent";
          PreparedStatement pstmt = conn.prepareStatement(query);

          // Exécute la requête
          ResultSet res = pstmt.executeQuery();

          // Tant que le résultat de la requête contient des lignes de données
          while (res.next()) {
            // Récupère les informations sur l'adhérent depuis le résultat de la requête
            String id = res.getString("num");
            String nom = res.getString("nom");
            String prenom = res.getString("prenom");

            // Ajoute le nom et prénom à la liste des adhérents
            adherentsComboBox
                .addItem(nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase() + prenom.substring(1));

            // Ajoute l'ID à la liste des IDs des adhérents
            adherentsIDs.add(id);
          }

        } catch (Exception exception) {
          System.out.println("ERROR: " + exception);
          JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !", JOptionPane.ERROR_MESSAGE);
        }

        // Définit le panneau comme contenu de la fenêtre et l'affiche
        window.setContentPane(DeleteAdherentPanel);

      }

    });

    // Ajoute le panneau des boutons au panneau central
    centerPanel.add(buttonPanel, gbc);
    buttonPanel.add(addButton);
    buttonPanel.add(modifyButton);
    buttonPanel.add(deleteButton);

    // Définit le panneau central comme contenu de la fenêtre et l'affiche
    window.setContentPane(centerPanel);
    window.setVisible(true);

  }

}