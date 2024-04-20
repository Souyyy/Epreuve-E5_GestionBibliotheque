
// Importe les classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class EMPRUNT extends JFrame {

    public static void main(String[] args) {
        // Crée une fenêtre principale
        JFrame window = new JFrame();
        window.setTitle("Livre"); // Définit le titre de la fenêtre
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
        JLabel titre = new JLabel("<html><u>Gestion des emprunts</u></html>");
        titre.setHorizontalAlignment(SwingConstants.CENTER);

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
        JButton empruntButton = new JButton("Emprunter un livre");
        JButton rendreButton = new JButton("Effectuer un retour");
        JButton listButton = new JButton("Liste des emprunts");

        // Code pour ajouter un emprunt
        empruntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Cacher le buttonPanel
                buttonPanel.setVisible(false);

                // Creer un nouveau panel pour la création d'un nouveau emprunt
                JPanel empruntLivrePanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.CENTER;

                // Title
                JLabel titre = new JLabel("<html><u>Emprunter un livre</u></html>");
                titre.setHorizontalAlignment(SwingConstants.CENTER);

                // Définit les paramètres GridBagConstraints pour le titre
                gbc.gridx = 0; // Colonne 0
                gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
                gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
                gbc.gridy++; // Passe à la ligne suivante
                gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
                empruntLivrePanel.add(titre, gbc); // Ajoute le titre au panneau central
                gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
                gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges
                gbc.gridy++; // Passe à la ligne suivante dans la grille

                // Input DATE EMPRUNT
                gbc.gridy++;
                JLabel label_livre = new JLabel("Choix du livre:");
                label_livre.setHorizontalAlignment(SwingConstants.LEFT);
                gbc.gridx = 0;
                gbc.gridy++;
                empruntLivrePanel.add(label_livre, gbc);
                // Ajouter les livres au JComboBox avec leur disponibilité
                JComboBox<String> livresComboBox = new JComboBox<>();
                livresComboBox.setPreferredSize(new Dimension(300, 25));
                gbc.gridx++;
                empruntLivrePanel.add(livresComboBox, gbc);

                // Label pour le choix de l'adhérent
                JLabel label_choix = new JLabel("Choix de l'adhérent:");
                label_choix.setHorizontalAlignment(SwingConstants.LEFT);
                gbc.gridx = 0;
                gbc.gridy++;
                empruntLivrePanel.add(label_choix, gbc);
                // Crée une liste déroulante avec les noms complets des adhérents
                JComboBox<String> adherentsComboBox = new JComboBox<>();
                adherentsComboBox.setPreferredSize(new Dimension(300, 25));
                gbc.gridx++;
                empruntLivrePanel.add(adherentsComboBox, gbc);

                // Input DATE EMPRUNT
                gbc.gridy++;
                JLabel label_emprunt = new JLabel("Date d'emprunt:");
                label_emprunt.setHorizontalAlignment(SwingConstants.LEFT);
                gbc.gridx = 0;
                gbc.gridy++;
                empruntLivrePanel.add(label_emprunt, gbc);
                JLabel label_today = new JLabel("Aujourd'hui");
                gbc.gridx++;
                empruntLivrePanel.add(label_today, gbc);

                // Input DATE RETOUR
                gbc.gridy++;
                JLabel label_date_retour = new JLabel("Date de retour:");
                label_date_retour.setHorizontalAlignment(SwingConstants.LEFT);
                gbc.gridx = 0;
                gbc.gridy++;
                empruntLivrePanel.add(label_date_retour, gbc);
                JLabel label_4week = new JLabel("Dans 4 semaines");
                gbc.gridx++;
                empruntLivrePanel.add(label_4week, gbc);

                // Input RETOUR
                gbc.insets = new Insets(30, 5, 5, 5);
                gbc.gridx = 0;
                gbc.gridy++;
                JButton Back_Button = new JButton("Retour");
                empruntLivrePanel.add(Back_Button, gbc);

                Back_Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonPanel.setVisible(true);
                        empruntLivrePanel.setVisible(false);
                        window.setContentPane(centerPanel);
                        window.revalidate();
                    }
                });

                // Créer une liste pour stocker les IDs des livres
                List<String> livresIDs = new ArrayList<>();

                // Créer une liste pour stocker les IDs des adherents
                List<String> adherentsIDs = new ArrayList<>();

                // Créer une liste pour stocker les dispo des livres
                List<String> livresDisponibilite = new ArrayList<>();

                // Créer une liste pour stocker les nom des livres
                List<String> livresTitre = new ArrayList<>();

                try {
                    // Établit la connexion à la base de données
                    Connection conn = DB.getConnection();

                    // Prépare la requête pour récuperer tout les détails des livres
                    String query_livre = "SELECT livre.isbn, livre.titre, " +
                            "CASE WHEN auteur.prenom IS NOT NULL THEN CONCAT(auteur.nom, ' ', auteur.prenom) " +
                            "ELSE auteur.nom END AS auteur, " +
                            "CASE WHEN emprunt.id_livre IS NOT NULL THEN 'Non disponible' ELSE 'Disponible' END AS disponibilite "
                            +
                            "FROM livre JOIN auteur ON livre.auteur = auteur.num " +
                            "LEFT JOIN emprunt ON livre.isbn = emprunt.id_livre";
                    PreparedStatement stmt = conn.prepareStatement(query_livre);

                    // Exécute la requête
                    ResultSet resultSet = stmt.executeQuery();

                    // Tant que le résultat de la requête contient des lignes de données
                    while (resultSet.next()) {
                        String isbn = resultSet.getString("isbn");
                        String titreLivre = resultSet.getString("titre");
                        String auteur = resultSet.getString("auteur");
                        String disponibilite = resultSet.getString("disponibilite");

                        // Ajouter au JComboBox
                        livresComboBox
                                .addItem('"' + titreLivre + '"' + " de " + '"' + auteur + '"' + " - " + disponibilite);

                        // Ajoute l'ID à la liste des IDs des livres
                        livresIDs.add(isbn);
                        livresTitre.add(titreLivre);
                        livresDisponibilite.add(disponibilite);
                    }

                    // Ajout des champs pour chaque INPUT lorsqu'on clique sur un livre
                    livresComboBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedIndex = livresComboBox.getSelectedIndex();
                            if (selectedIndex >= 0 && selectedIndex < livresIDs.size()) {
                                String livresID = livresIDs.get(selectedIndex);
                                int auteurIndex = livresIDs.indexOf(livresID);
                                if (auteurIndex != -1) {
                                    livresComboBox.setSelectedIndex(auteurIndex);
                                    System.out.println("ID du livre sélectionné : " + livresID);
                                } else {
                                    System.out.println("Le livre correspondant à l'ID n'a pas été trouvé.");
                                }

                            }
                        }
                    });

                    // Récupérer les adhérents depuis la base de données
                    String query_adherent = "SELECT * FROM adherent";
                    PreparedStatement pstmt = conn.prepareStatement(query_adherent);

                    // Exécute la requête
                    ResultSet res = pstmt.executeQuery();

                    // Tant que le résultat de la requête contient des lignes de données
                    while (res.next()) {
                        String id = res.getString("num");
                        String nom = res.getString("nom");
                        String prenom = res.getString("prenom");

                        // Ajouter le nom et prénom à la liste des adherents dans la JComboBox
                        adherentsComboBox.addItem(
                                nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase() + prenom.substring(1));

                        // Ajoute l'ID à la liste des IDs des adherents
                        adherentsIDs.add(id);
                    }

                    // Send l'input adhérent
                    adherentsComboBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedIndex = adherentsComboBox.getSelectedIndex();

                            // Assurez-vous que l'indice est valide
                            if (selectedIndex >= 0 && selectedIndex < adherentsIDs.size()) {
                                // Récupérez l'ID de l'auteur directement
                                String adherentsID = adherentsIDs.get(selectedIndex);
                                int adherentIndex = adherentsIDs.indexOf(adherentsID);
                                if (adherentIndex != -1) {
                                    adherentsComboBox.setSelectedIndex(adherentIndex);
                                    System.out.println("ID de l'adhérent sélectionné : " + adherentsID);
                                } else {
                                    System.out.println("L'adhérent qui correspondant à l'ID n'a pas été trouvé.");
                                }
                            }
                        }
                    });

                } catch (Exception exception) {
                    exception.printStackTrace();
                    // Gérer les exceptions ici
                }

                // Input SUBMIT
                JButton Submit_ModificationButton = new JButton("Emprunter le livre");
                gbc.gridx++;
                empruntLivrePanel.add(Submit_ModificationButton, gbc);

                // Bouton envoyer
                Submit_ModificationButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // Si l'id livre et id adhérent n'est pas vide
                        if (!livresIDs.isEmpty() && !adherentsIDs.isEmpty()) {

                            try {
                                // Établit la connexion à la base de données
                                Connection conn = DB.getConnection();

                                // Récupérer l'ID du livre sélectionné
                                int selectedIndex = livresComboBox.getSelectedIndex();
                                String ancienIDlivre = livresIDs.get(selectedIndex);
                                System.out.println(ancienIDlivre);

                                // Récupérer l'ID de l'adhérent sélectionné
                                int selectedIndexAdherent = adherentsComboBox.getSelectedIndex();
                                String ancienIdAdherent = adherentsIDs.get(selectedIndexAdherent);
                                System.out.println(ancienIdAdherent);

                                // Récupérer le titre sélectionné
                                int selectedIndexTitre = livresComboBox.getSelectedIndex();
                                String titreString = livresTitre.get(selectedIndexTitre);
                                System.out.println(titreString);

                                // Récupérer la disponibilité sélectionné
                                int selectedIndexDispo = livresComboBox.getSelectedIndex();
                                String dispoString = livresDisponibilite.get(selectedIndexDispo);
                                System.out.println(dispoString);

                                // Vérifier le nombre d'emprunts actuels de l'adhérent
                                String queryEmpruntsAdherent = "SELECT COUNT(*) AS nbEmprunts FROM emprunt WHERE id_adherent = ?";
                                PreparedStatement pstmtEmprunts = conn.prepareStatement(queryEmpruntsAdherent);
                                pstmtEmprunts.setString(1, ancienIdAdherent);
                                ResultSet resultSetEmprunts = pstmtEmprunts.executeQuery();

                                // Si il y a des resultats
                                if (resultSetEmprunts.next()) {
                                    int nbEmprunts = resultSetEmprunts.getInt("nbEmprunts");

                                    // Vérifier si l'adhérent a déjà emprunté 5 livres
                                    if (nbEmprunts >= 5) {
                                        JOptionPane.showMessageDialog(window,
                                                "L'adhérent a déjà emprunté le nombre maximal de livres (5).",
                                                "Limite d'emprunts atteinte", JOptionPane.ERROR_MESSAGE);
                                        return; // Sortir de la fonction en cas d'erreur
                                    } else {

                                        if (dispoString.equals("Disponible")) {
                                            // Exécuter la requête SQL pour effectuer l'emprunt
                                            String queryEmprunt = "INSERT INTO emprunt (id_adherent, id_livre, date_emprunt, date_retour) VALUES (?, ?, CURRENT_DATE, CURRENT_DATE + INTERVAL 4 WEEK)";
                                            PreparedStatement pstmt = conn.prepareStatement(queryEmprunt);
                                            pstmt.setString(1, ancienIdAdherent);
                                            pstmt.setString(2, ancienIDlivre);

                                            // Exécuter la requête
                                            int rowsAffected = pstmt.executeUpdate();

                                            // Vérifier si l'emprunt a été effectué avec succès
                                            if (rowsAffected > 0) {
                                                JOptionPane.showMessageDialog(window,
                                                        "Emprunt enregistré avec succès !\n Livre : " + titreString
                                                                + "\nAdhérent : "
                                                                + adherentsComboBox.getSelectedItem() +
                                                                "\nDate d'emprunt : Aujourd'hui\nDate de retour prévue : Dans 4 semaines");
                                            }
                                            buttonPanel.setVisible(true);
                                            empruntLivrePanel.setVisible(false);
                                            // Actualiser le contenu de la fenêtre principale
                                            window.setContentPane(centerPanel);
                                            window.revalidate();

                                        } else {
                                            // Afficher un message d'erreur si le livre n'est pas disponible
                                            JOptionPane.showMessageDialog(window,
                                                    "Le livre sélectionné n'est pas disponible.",
                                                    "Erreur de disponibilité", JOptionPane.ERROR_MESSAGE);
                                            return; // Sortir de la fonction en cas d'erreur
                                        }

                                    }
                                }

                            } catch (Exception exception) {
                                System.out.println("ERROR: " + exception);
                                JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            System.out.println("ERROR: Un champs ne peut pas être vide.");
                            JOptionPane.showMessageDialog(window, "Un champs ne peut pas être vide.", "Oups !",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                window.setContentPane(empruntLivrePanel);
            }
        });

        rendreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Cacher le buttonPanel
                buttonPanel.setVisible(false);

                // Creer un nouveau panel pour la création d'un nouveau auteur
                JPanel retourLivrePanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.CENTER;

                // Title
                JLabel titre = new JLabel("<html><u>Retour d'un livre</u></html>");
                titre.setHorizontalAlignment(SwingConstants.CENTER);

                // Définit les paramètres GridBagConstraints pour le titre
                gbc.gridx = 0; // Colonne 0
                gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
                gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
                gbc.gridy++; // Passe à la ligne suivante
                gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
                retourLivrePanel.add(titre, gbc); // Ajoute le titre au panneau central
                gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
                gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

                try {
                    // Etape 1: Connexion BDD
                    Connection conn = DB.getConnection();

                    // Etape 2: Créer la requête préparée pour récupérer les détails
                    String query_livre = "SELECT emprunt.id, livre.titre, " +
                            "CASE WHEN auteur.prenom IS NOT NULL THEN CONCAT(auteur.nom, ' ', auteur.prenom) " +
                            "ELSE auteur.nom END AS auteur, " +
                            "CONCAT(adherent.nom, ' ', adherent.prenom) AS emprunteur " +
                            "FROM emprunt " +
                            "LEFT JOIN livre ON emprunt.id_livre = livre.isbn " +
                            "LEFT JOIN auteur ON livre.auteur = auteur.num " +
                            "LEFT JOIN adherent ON emprunt.id_adherent = adherent.num";

                    PreparedStatement stmt = conn.prepareStatement(query_livre);

                    // Etape 3: Exécuter la requête
                    ResultSet resultSet = stmt.executeQuery();

                    // Label pour le choix du livre
                    JLabel label_choix = new JLabel("Choix du livre:");
                    label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
                    gbc.gridx = 0;
                    gbc.gridy++;
                    retourLivrePanel.add(label_choix, gbc);

                    JComboBox<String> livreComboBox = new JComboBox<>();
                    livreComboBox.setPreferredSize(new Dimension(400, 25));
                    gbc.gridx++;
                    retourLivrePanel.add(livreComboBox, gbc);
                    gbc.gridy++;

                    // Remplir la liste déroulante
                    while (resultSet.next()) {
                        String bookInfo = resultSet.getString("id") + " - " + '"' +
                                resultSet.getString("titre") + '"' + " de " + '"' +
                                resultSet.getString("auteur") + '"' + " emprunté par " + '"' +
                                resultSet.getString("emprunteur") + '"';
                        livreComboBox.addItem(bookInfo);
                    }

                    titre.setHorizontalAlignment(SwingConstants.CENTER); // Aligner le texte au centre
                    gbc.gridx = 0;
                    gbc.gridwidth = 4; // Étendre sur deux colonnes
                    gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
                    gbc.gridy++;
                    // Créer un JPanel pour les boutons
                    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    buttonsPanel.setOpaque(false); // Rendre le fond du JPanel transparent

                    // Input RETOUR
                    JButton Back_Button = new JButton("Retour");
                    buttonsPanel.add(Back_Button);

                    // Input SUBMIT
                    JButton Submit_ModificationButton = new JButton("Rendre le livre");
                    buttonsPanel.add(Submit_ModificationButton);
                    retourLivrePanel.add(buttonsPanel, gbc);

                    // Retour en arriere
                    Back_Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonPanel.setVisible(true);
                            // Cacher le panel de création de livre
                            retourLivrePanel.setVisible(false);
                            // Actualiser le contenu de la fenêtre principale
                            window.setContentPane(centerPanel);
                            window.revalidate();
                        }
                    });

                    Submit_ModificationButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Récupérer le livre sélectionné dans le JComboBox
                            String selectedBookInfo = (String) livreComboBox.getSelectedItem();
                            if (selectedBookInfo != null && !selectedBookInfo.isEmpty()) {
                                // Extraire l'ISBN du livre sélectionné (assumant un format spécifique)
                                String[] bookParts = selectedBookInfo.split(" - ");
                                String isbn = bookParts[0]; // ISBN est la première partie

                                // Supprimer le livre de la base de données
                                System.out.println("Valeur de isbn : " + isbn);
                                try {

                                    String deleteQuery = "DELETE FROM emprunt WHERE id = ?";
                                    PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                                    deleteStmt.setString(1, isbn);
                                    int rowsAffected = deleteStmt.executeUpdate();
                                    if (rowsAffected > 0) {
                                        System.out.println("Livre rendu avec succès.");
                                        // Mise à jour de l'interface utilisateur ou autres actions nécessaires

                                        JOptionPane.showMessageDialog(window,
                                                "Le livre sélectionné a été rendu, il est de nouveau disponible.",
                                                "Livre rendu avec succès.", JOptionPane.INFORMATION_MESSAGE);
                                        // Mettre à jour la liste déroulante des livres après le rendu
                                        livreComboBox.removeItem(selectedBookInfo);

                                    } else {
                                        System.out.println(isbn);
                                        System.out.println("Erreur lors de la suppression du livre.");
                                        JOptionPane.showMessageDialog(window, "Une erreur est survenue", "Attention",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                    deleteStmt.close();

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(window, "Aucun livre à rendre", "Attention",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                } catch (Exception exception) {
                    // Gérer l'exception selon vos besoins
                    exception.printStackTrace();
                }

                window.setContentPane(retourLivrePanel);
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Cacher le buttonPanel
                buttonPanel.setVisible(false);

                // Créer un nouveau panel pour afficher la liste des livres empruntés
                JPanel listLivrePanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL; // Remplissage horizontal pour le JTextField
                gbc.anchor = GridBagConstraints.CENTER; // Centrer le composant dans sa cellule

                try {

                    // Etape 1: Connexion BDD
                    Connection conn = DB.getConnection();

                    // Etape 2: Créer la requête préparée pour compter le nombre d'occurrences dans
                    // la table EMPRUNT
                    String countQuery = "SELECT COUNT(*) AS count FROM EMPRUNT";
                    PreparedStatement countStmt = conn.prepareStatement(countQuery);
                    ResultSet countResultSet = countStmt.executeQuery();
                    countResultSet.next(); // Accéder à la première ligne du résultat
                    int rowCount = countResultSet.getInt("count"); // Récupérer le nombre total d'occurrences

                    // Etape 2: Créer la requête préparée pour récupérer les détails des livres et
                    // leur disponibilité
                    String query = "SELECT EMPRUNT.id, EMPRUNT.id_livre, LIVRE.titre, ADHERENT.nom, ADHERENT.prenom, " +
                            "DATE_FORMAT(EMPRUNT.date_emprunt, '%d/%m/%Y') AS date_emprunt_formattee, " +
                            "DATE_FORMAT(EMPRUNT.date_retour, '%d/%m/%Y') AS date_retour_formattee " +
                            "FROM EMPRUNT " +
                            "INNER JOIN ADHERENT ON EMPRUNT.id_adherent = ADHERENT.num " +
                            "INNER JOIN LIVRE ON EMPRUNT.id_livre = LIVRE.ISBN";

                    PreparedStatement stmt = conn.prepareStatement(query);
                    // Etape 3: Exécuter la requête
                    ResultSet resultSet = stmt.executeQuery();
                    // Étape 4: Création des données pour le tableau

                    Object[][] data = new Object[rowCount][5]; // Adapter la taille du tableau selon vos besoins

                    int row = 0;
                    while (resultSet.next()) {
                        data[row][0] = resultSet.getInt("id");
                        data[row][1] = resultSet.getString("titre");
                        data[row][2] = resultSet.getString("nom") + " " + resultSet.getString("prenom");
                        data[row][3] = resultSet.getString("date_emprunt_formattee"); // Utiliser la colonne formattée
                        data[row][4] = resultSet.getString("date_retour_formattee"); // Utiliser la colonne formattée
                        row++;
                    }

                    // Étape 5: Création des titres de colonnes
                    String[] columnNames = { "Emprunt n°", "Livre", "Adherent Emprunteur", "Date Emprunt",
                            "Date Retour" };
                    // Étape 6: Création du tableau
                    JTable table = new JTable(data, columnNames);
                    table.setEnabled(false); // Désactive l'édition
                    JScrollPane scrollPane = new JScrollPane(table);

                    // Ajouter le titre au panel
                    JLabel titre = new JLabel("<html><u>Liste des emprunts</u></html>");
                    titre.setHorizontalAlignment(SwingConstants.CENTER); // Aligner le texte au centre
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 5; // Étendre sur cinq colonnes
                    gbc.insets = new Insets(30, 5, 30, 5); // Espacement entre les composants
                    listLivrePanel.add(titre, gbc);

                    // Ajouter le tableau au panel
                    gbc.gridy = 1;
                    gbc.gridwidth = 4; // Étendre sur cinq colonnes
                    gbc.fill = GridBagConstraints.BOTH; // Remplir horizontalement et verticalement le conteneur
                    gbc.weightx = 1; // Poids horizontal
                    gbc.weighty = 1; // Poids vertical
                    gbc.insets = new Insets(5, 50, 5, 50); // Espacement entre les composants
                    listLivrePanel.add(scrollPane, gbc);

                    // Input RETOUR
                    gbc.gridx = 0;
                    gbc.gridy++;
                    gbc.gridwidth = 4; // Largeur totale du tableau
                    gbc.anchor = GridBagConstraints.CENTER; // Centrer le composant horizontalement
                    gbc.fill = GridBagConstraints.NONE; // Ne pas étirer le bouton
                    gbc.insets = new Insets(5, 5, 5, 5);
                    JButton Back_Button = new JButton("Retour");
                    listLivrePanel.add(Back_Button, gbc);

                    // Retour en arriere
                    Back_Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonPanel.setVisible(true);
                            // Cacher le panel de création de livre
                            listLivrePanel.setVisible(false);
                            // Actualiser le contenu de la fenêtre principale
                            window.setContentPane(centerPanel);
                            window.revalidate();
                        }
                    });
                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex);
                    JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
                            JOptionPane.ERROR_MESSAGE);
                }

                window.setContentPane(listLivrePanel);
                listLivrePanel.setVisible(true);
            }
        });

        centerPanel.add(buttonPanel, gbc);
        // Add buttons to the button panel
        buttonPanel.add(empruntButton);
        buttonPanel.add(rendreButton);
        buttonPanel.add(listButton);

        window.setContentPane(centerPanel);
        window.setVisible(true);

    }
}
