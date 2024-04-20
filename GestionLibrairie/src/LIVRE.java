
// Importe les classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class LIVRE extends JFrame {
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
		JLabel titre = new JLabel("<html><u>Gestion des livres</u></html>");
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
		JButton addButton = new JButton("Ajouter un livre");
		JButton modifyButton = new JButton("Modifier un livre");
		JButton deleteButton = new JButton("Supprimer un livre");

		// Code pour ajouter un livre
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Cacher le buttonPanel
				buttonPanel.setVisible(false);

				// Creer un nouveau panel pour la création d'un nouveau auteur
				JPanel addLivrePanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.CENTER;

				// Title
				JLabel titre = new JLabel("<html><u>Ajouter un livre</u></html>");
				titre.setHorizontalAlignment(SwingConstants.CENTER);

				// Définit les paramètres GridBagConstraints pour le titre
				gbc.gridx = 0; // Colonne 0
				gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
				gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
				gbc.gridy++; // Passe à la ligne suivante
				gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
				addLivrePanel.add(titre, gbc); // Ajoute le titre au panneau central
				gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
				gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges
				gbc.gridy++; // Passe à la ligne suivante dans la grille

				// Input TITRE
				gbc.gridy++;
				JLabel label_titre = new JLabel("Titre du livre:"); // Crée un JLabel pour le champ de saisie
				label_titre.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à gauche
				addLivrePanel.add(label_titre, gbc); // Ajoute le label
				JTextField titreText = new JTextField(); // Crée un champ de texte pour la saisie
				titreText.setPreferredSize(new Dimension(200, 25)); // Définit la taille préférée du champ de texte
				gbc.gridx++; // Passe à la colonne suivante dans la grille
				addLivrePanel.add(titreText, gbc);

				// Input PRIX
				gbc.gridy++;
				JLabel label_prix = new JLabel("Prix du livre:"); // Crée un JLabel pour le champ de saisie
				label_prix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à gauche
				gbc.gridx = 0;
				gbc.gridy++;
				addLivrePanel.add(label_prix, gbc); // Ajoute le label
				JTextField prixText = new JTextField(); // Crée un champ de texte pour la saisie
				prixText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++; // Passe à la colonne suivante dans la grille
				addLivrePanel.add(prixText, gbc);

				// Crée un label pour indiquer le choix de livre
				JLabel label_choix = new JLabel("Auteur du livre:");
				label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à gauche
				gbc.gridx = 0;
				gbc.gridy++;
				addLivrePanel.add(label_choix, gbc);
				// Crée une liste déroulante avec les noms complets des auteurs
				JComboBox<String> auteurComboBox = new JComboBox<>();
				auteurComboBox.setPreferredSize(new Dimension(200, 25));
				gbc.gridx++;
				addLivrePanel.add(auteurComboBox, gbc);

				// Input RETOUR
				gbc.insets = new Insets(30, 5, 5, 5);
				gbc.gridx = 0;
				gbc.gridy++;
				JButton Back_Button = new JButton("Retour");
				addLivrePanel.add(Back_Button, gbc);
				Back_Button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonPanel.setVisible(true);
						// Cacher le panel de création de livre
						addLivrePanel.setVisible(false);
						// Actualiser le contenu de la fenêtre principale
						window.setContentPane(centerPanel);
						window.revalidate();
					}
				});

				// Input SUBMIT
				JButton Submit_ModificationButton = new JButton("Ajouter un livre");
				gbc.gridx++;
				addLivrePanel.add(Submit_ModificationButton, gbc);

				// Créer une liste pour stocker les IDs des auteurs
				List<String> auteursIDs = new ArrayList<>();

				// Ajoute un ActionListener au bouton pour gérer l'événement de clic
				Submit_ModificationButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Récupérer les valeurs saisies dans les champs de texte
						String titre = titreText.getText();
						String prix = prixText.getText();

						// Verifier si le titre et le prix ne sont pas vide
						if (!titre.isEmpty() && !titre.trim().isEmpty() && !prix.isEmpty() && !prix.trim().isEmpty()) {

							try {
								// Établit la connexion à la base de données
								Connection conn = DB.getConnection();

								// Récupérer l'ID de l'auteurs sélectionné
								int selectedIndex = auteurComboBox.getSelectedIndex();
								String ancienID = auteursIDs.get(selectedIndex);
								System.out.println(ancienID);

								// Vérifier si le livre existe dans la table "livre"
								String query = "SELECT * FROM livre WHERE titre=? AND auteur = ?";
								PreparedStatement pstmt = conn.prepareStatement(query);
								pstmt.setString(1, titre);
								pstmt.setString(2, ancienID);

								// Exécute la requête
								ResultSet resCheck = pstmt.executeQuery();

								if (!resCheck.next()) {
									// Le livre n'existe pas, il est ajouté à la base de données
									query = "INSERT INTO livre (titre, prix, auteur) VALUES (?, ?, ?)";
									pstmt = conn.prepareStatement(query);
									pstmt.setString(1, titre);
									pstmt.setString(2, prix);
									pstmt.setString(3, ancienID);
									pstmt.executeUpdate();
									System.out.println("SUCCESS: Le livre a été ajouté avec succès.");
									JOptionPane.showMessageDialog(window, "Le livre a été ajouté avec succès.",
											"Super !", JOptionPane.INFORMATION_MESSAGE);

								} else {
									System.out.println("ERROR: Le livre existe déjà.");
									JOptionPane.showMessageDialog(window, "Le livre existe déjà.", "Oups !",
											JOptionPane.ERROR_MESSAGE);
								}

							} catch (Exception exception) {
								System.out.println("ERROR: " + exception);
								JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							System.out.println("ERROR: Un champ ne peut pas être vide.");
							JOptionPane.showMessageDialog(window, "Un champ ne peut pas être vide.", "Oups !",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});

				try {
					// Établit la connexion à la base de données
					Connection conn = DB.getConnection();

					// Prépare la requête pour récuperer tout les auteurs
					String query = "SELECT * FROM auteur";
					PreparedStatement pstmt = conn.prepareStatement(query);

					// Exécute la requête
					ResultSet res = pstmt.executeQuery();

					// Tant que le résultat de la requête contient des lignes de données
					while (res.next()) {
						String id = res.getString("num");
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");

						// Ajoute le nom et prénom à la liste des auteurs
						if (prenom != null && !prenom.isEmpty()) {
							// Ajouter le nom et prénom à la liste des auteurs dans la JComboBox
							auteurComboBox.addItem(nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase()
									+ prenom.substring(1));
						} else if (prenom == null) {
							// Ajouter le nom à la liste des auteurs dans la JComboBox
							auteurComboBox.addItem(nom.toUpperCase());
						} else {

						}

						// Ajoute l'ID à la liste des IDs des auteurs
						auteursIDs.add(id);
					}

				} catch (Exception exception) {
					System.out.println("ERROR: " + exception);
					JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
							JOptionPane.ERROR_MESSAGE);
				}

				// Définit le panneau comme contenu de la fenêtre et l'affiche
				window.setContentPane(addLivrePanel);

			}
		});

		// Code pour modifier un livre
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Cacher le buttonPanel
				buttonPanel.setVisible(false);

				// Creer un nouveau panel pour la création d'un nouveau auteur
				JPanel editLivrePanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.CENTER;

				// Title
				JLabel titre = new JLabel("<html><u>Modifier un livre</u></html>");
				titre.setHorizontalAlignment(SwingConstants.CENTER);

				// Définit les paramètres GridBagConstraints pour le titre
				gbc.gridx = 0; // Colonne 0
				gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
				gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
				gbc.gridy++; // Passe à la ligne suivante
				gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
				editLivrePanel.add(titre, gbc); // Ajoute le titre au panneau central
				gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
				gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

				// Crée un label pour indiquer le choix de livre
				JLabel label_choix = new JLabel("Choix du livre:");
				label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à gauche
				gbc.gridx = 0;
				gbc.gridy++;
				editLivrePanel.add(label_choix, gbc);
				// Affiche le titre du livre dans la liste deroulante
				JComboBox<String> livreComboBox = new JComboBox<>();
				livreComboBox.setPreferredSize(new Dimension(200, 25));
				gbc.gridx++;
				editLivrePanel.add(livreComboBox, gbc);

				// Input TITRE
				JLabel label_titre = new JLabel("Titre du livre:");
				label_titre.setHorizontalAlignment(SwingConstants.LEFT);
				gbc.gridx = 0;
				gbc.gridy++;
				editLivrePanel.add(label_titre, gbc);
				JTextField titreText = new JTextField();
				titreText.setPreferredSize(new Dimension(200, 25));
				gbc.gridx++;
				editLivrePanel.add(titreText, gbc);

				// Input PRIX
				gbc.gridy++;
				JLabel label_prix = new JLabel("Prix du livre:");
				label_prix.setHorizontalAlignment(SwingConstants.LEFT);
				gbc.gridx = 0;
				gbc.gridy++;
				editLivrePanel.add(label_prix, gbc);
				JTextField prixText = new JTextField();
				prixText.setPreferredSize(new Dimension(200, 25));
				gbc.gridx++;
				editLivrePanel.add(prixText, gbc);

				// Input AUTEUR
				gbc.gridy++;
				JLabel label_auteur = new JLabel("Choix de l'auteur:");
				label_auteur.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				editLivrePanel.add(label_auteur, gbc);
				// Affiche le titre et le nom et prenom de l'auteur dans la liste deroulante
				JComboBox<String> auteurComboBox = new JComboBox<>();
				auteurComboBox.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++;
				editLivrePanel.add(auteurComboBox, gbc);

				// Input RETOUR
				gbc.insets = new Insets(30, 5, 5, 5);
				gbc.gridx = 0;
				gbc.gridy++;
				JButton Back_Button = new JButton("Retour");
				editLivrePanel.add(Back_Button, gbc);
				Back_Button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonPanel.setVisible(true);
						editLivrePanel.setVisible(false);
						window.setContentPane(centerPanel);
						window.revalidate();
					}
				});

				// Créer une liste pour stocker les IDs des livres
				List<String> livresIDs = new ArrayList<>();

				// Créer une liste pour stocker les IDs des auteurs
				List<String> auteursIDs = new ArrayList<>();

				// Créer une liste pour stocker les prix des livres
				List<String> livresPrix = new ArrayList<>();

				// Créez une liste pour stocker les ID des auteurs non associés à un livre
				List<String> auteursNonAssociesIDs = new ArrayList<>();

				try {
					// Établit la connexion à la base de données
					Connection conn = DB.getConnection();

					// Prépare la requête pour récuperer tout les livre
					String query_stmt = "SELECT *, auteur.nom, auteur.prenom FROM livre JOIN auteur ON livre.auteur = auteur.num";
					PreparedStatement pstmt = conn.prepareStatement(query_stmt);

					// Exécute la requête
					ResultSet res = pstmt.executeQuery();

					// Tant que le résultat de la requête contient des lignes de données
					while (res.next()) {
						// Récupère les informations sur le livre depuis le résultat de la requête
						String isbn = res.getString("isbn");
						String titre_livre = res.getString("titre");
						String prix = res.getString("prix");
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						String id = res.getString("auteur");

						// Ajoute le nom et prénom à la liste des auteurs
						if (prenom != null && !prenom.isEmpty()) {
							// Ajouter le nom et prénom à la liste des auteurs dans la JComboBox
							livreComboBox.addItem(
									titre_livre + " - " + nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase()
											+ prenom.substring(1));
						} else if (prenom == null) {
							// Ajouter le nom à la liste des auteurs dans la JComboBox
							livreComboBox.addItem(titre_livre + " - " + nom.toUpperCase());
						} else {

						}

						// Ajoute l'ID à la liste des IDs des livres
						livresIDs.add(isbn);

						// Ajoute l'ID à la liste des IDs des auteurs
						auteursIDs.add(id);

						// Ajoute le prix à la liste des prix des livres
						livresPrix.add(prix);
					}

					// Ajout des champs pour chaque INPUT lorsqu'on clique sur un livre
					livreComboBox.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent event) {
							int selectedIndex = livreComboBox.getSelectedIndex();
							if (selectedIndex >= 0 && selectedIndex < livresIDs.size()) {
								String auteurID = auteursIDs.get(selectedIndex);
								int auteurIndex = auteursIDs.indexOf(auteurID);
								if (auteurIndex != -1) {
									auteurComboBox.setSelectedIndex(auteurIndex);
								} else {
									System.out.println("L'auteur correspondant à l'ID n'a pas été trouvé.");
								}

								String selectedLivre = (String) livreComboBox.getSelectedItem();
								String[] selectedLivreInfo = selectedLivre.split(" - ");
								String titre_livre = selectedLivreInfo[0];

								titreText.setText(titre_livre);

								String prix = livresPrix.get(selectedIndex);
								prixText.setText(prix);
							}
						}
					});
					// Prépare la requête pour récuperer tout les livre
					String query_stmt1 = "SELECT * FROM auteur";
					PreparedStatement pstmt1 = conn.prepareStatement(query_stmt1);

					// Exécute la requête
					ResultSet res1 = pstmt1.executeQuery();

					// Étape 4: Vérifier s'il y a des résultats
					while (res1.next()) {
						String id = res1.getString("num");
						String nom = res1.getString("nom");
						String prenom = res1.getString("prenom");

						// Ajoute le nom et prénom à la liste des auteurs
						if (prenom != null && !prenom.isEmpty()) {
							// Ajouter le nom et prénom à la liste des auteurs dans la JComboBox
							auteurComboBox
									.addItem(id + " " + nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase()
											+ prenom.substring(1));
						} else if (prenom == null) {
							// Ajouter le nom à la liste des auteurs dans la JComboBox
							auteurComboBox.addItem(id + " " + nom.toUpperCase());
						} else {

						}

						// Ajoute l'ID à la liste des IDs des auteurs + les infos
						auteursNonAssociesIDs.add(id);
					}

					// Gérons l'action lorsque l'utilisateur sélectionne un auteur dans la JComboBox
					// des auteurs
					auteurComboBox.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int selectedIndex = auteurComboBox.getSelectedIndex();
							if (selectedIndex >= 0 && selectedIndex < auteursNonAssociesIDs.size()) {
								String auteursID = auteursNonAssociesIDs.get(selectedIndex);
								int auteurIndex = auteursNonAssociesIDs.indexOf(auteursID);
								if (auteurIndex != -1) {
									auteurComboBox.setSelectedIndex(auteurIndex);
									System.out.println("ID de l'adhérent sélectionné : " + auteursID);
								} else {
									System.out.println("L'adhérent qui correspondant à l'ID n'a pas été trouvé.");
								}
							}
						}
					});

				} catch (Exception exception) {
					System.out.println("ERROR: " + exception);
					JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
							JOptionPane.ERROR_MESSAGE);
				}

				// Input SUBMIT
				JButton Submit_ModificationButton = new JButton("Modifier le livre");
				gbc.gridx++;
				editLivrePanel.add(Submit_ModificationButton, gbc);

				// Bouton envoyer
				Submit_ModificationButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Récupérer les valeurs saisies dans les champs de texte
						String titre = titreText.getText();
						String prix = prixText.getText();

						// Si la liste des auteurs n'est pas vide
						if (!auteursIDs.isEmpty()) {

							if (!titre.isEmpty() && !titre.trim().isEmpty() && !prix.isEmpty()
									&& !prix.trim().isEmpty()) {

								try {
									// Établit la connexion à la base de données
									Connection conn = DB.getConnection();

									// Récupérer l'ID du livre sélectionné
									int selectedIndex = livreComboBox.getSelectedIndex();
									String ancienIDlivre = livresIDs.get(selectedIndex);
									System.out.println(ancienIDlivre);

									// Récupérer l'ID de l'auteur sélectionné
									int selectedIndexAuteur = auteurComboBox.getSelectedIndex();
									String ancienIDauteur = auteursNonAssociesIDs.get(selectedIndexAuteur);
									System.out.println(ancienIDauteur);

									// Vérifier que les infos n'appartiennent pas déjà à un autre livre
									String checkDuplicateQuery = "SELECT * FROM livre WHERE titre=? AND auteur=? AND isbn !=?";
									PreparedStatement checkDuplicateStmt = conn.prepareStatement(checkDuplicateQuery);
									checkDuplicateStmt.setString(1, titre);
									checkDuplicateStmt.setString(2, ancienIDauteur);
									checkDuplicateStmt.setString(3, ancienIDlivre);
									ResultSet duplicateBook = checkDuplicateStmt.executeQuery();

									if (!duplicateBook.next()) {
										// Les informations n'existent pas ou sont les mêmes que l'ancien
										String updateQuery = "UPDATE livre SET titre=?, prix=?, auteur=? WHERE isbn=?";
										PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
										updateStmt.setString(1, titre);
										updateStmt.setString(2, prix);
										updateStmt.setString(3, ancienIDauteur);
										updateStmt.setString(4, ancienIDlivre);
										updateStmt.executeUpdate();
										System.out.println(
												"SUCCESS: Le livre a été modifié avec succès, merci de recharger la page.");
										JOptionPane.showMessageDialog(window,
												"Le livre a été modifié avec succès, merci de recharger la page.",
												"Super !",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										System.out.println(
												"ERROR: Un autre livre avec le même titre et le même auteur existe déjà.");
										JOptionPane.showMessageDialog(window,
												"Un autre livre avec le même titre et le même auteur existe déjà.",
												"Oups !",
												JOptionPane.ERROR_MESSAGE);
									}
								} catch (Exception exception) {
									System.out.println("ERROR: " + exception);
									JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								System.out.println(
										"ERROR: Un champ ne peut pas être vide.");
								JOptionPane.showMessageDialog(window,
										"Un champ ne peut pas être vide.", "Oups !",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							System.out.println("ERROR: Aucun auteur disponible.");
							JOptionPane.showMessageDialog(window, "Aucun auteur disponible.", "Oups !",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});

				window.setContentPane(editLivrePanel);

			}
		});

		// Code pour supprimer un livre
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Cacher le buttonPanel
				buttonPanel.setVisible(false);

				// Creer un nouveau panel pour la création d'un nouveau auteur
				JPanel deleteLivrePanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.CENTER;

				// Title
				JLabel titre = new JLabel("<html><u>Supprimer un livre</u></html>");
				titre.setHorizontalAlignment(SwingConstants.CENTER);

				// Définit les paramètres GridBagConstraints pour le titre
				gbc.gridx = 0; // Colonne 0
				gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
				gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
				gbc.gridy++; // Passe à la ligne suivante
				gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
				deleteLivrePanel.add(titre, gbc); // Ajoute le titre au panneau central
				gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
				gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

				JLabel label_choix = new JLabel("Choix du livre:");
				label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				deleteLivrePanel.add(label_choix, gbc);
				// Crée une liste déroulante avec les noms complets des livres
				JComboBox<String> livreComboBox = new JComboBox<>();
				livreComboBox.setPreferredSize(new Dimension(200, 25));
				gbc.gridx++;
				deleteLivrePanel.add(livreComboBox, gbc);

				// Input RETOUR
				gbc.insets = new Insets(30, 5, 5, 5);
				gbc.gridx = 0;
				gbc.gridy++;
				JButton Back_Button = new JButton("Retour");
				deleteLivrePanel.add(Back_Button, gbc);

				Back_Button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonPanel.setVisible(true);
						deleteLivrePanel.setVisible(false);
						window.setContentPane(centerPanel);
						window.revalidate();
					}
				});

				// Input SUPPR
				JButton Submit_DeleteButton = new JButton("Supprimer le livre");
				gbc.gridx++;
				deleteLivrePanel.add(Submit_DeleteButton, gbc);

				// Créer une liste pour stocker les IDs des livres
				List<String> livresIDs = new ArrayList<>();

				// Bouton envoyer
				Submit_DeleteButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							// Établit la connexion à la base de données
							Connection conn = DB.getConnection();

							if (!livresIDs.isEmpty()) {

								// Récupérer l'ID du livre sélectionné
								int selectedIndex = livreComboBox.getSelectedIndex();
								String ancienID = livresIDs.get(selectedIndex);
								System.out.println(ancienID);

								// Vérifier si le livre existe dans la table
								String queryCheck = "SELECT * FROM livre WHERE isbn = ?";
								PreparedStatement pstmtCheck = conn.prepareStatement(queryCheck);
								pstmtCheck.setString(1, ancienID);
								ResultSet resCheck = pstmtCheck.executeQuery();

								if (resCheck.next()) {
									// Le livre existe dans la table
									String queryCheckEmprunt = "SELECT COUNT(*) AS emprunt_count FROM emprunt WHERE id_livre=?";
									PreparedStatement pstmtCheckEmprunt = conn.prepareStatement(queryCheckEmprunt);
									pstmtCheckEmprunt.setString(1, ancienID);
									ResultSet resCheckEmprunt = pstmtCheckEmprunt.executeQuery();

									if (resCheckEmprunt.next()) {
										int countEmprunt = resCheckEmprunt.getInt("emprunt_count");
										if (countEmprunt == 0) {
											// Le livre n'est pas en cours d'emprunt
											String queryDelete = "DELETE FROM livre WHERE isbn=?";
											PreparedStatement pstmtDelete = conn.prepareStatement(queryDelete);
											pstmtDelete.setString(1, ancienID);
											pstmtDelete.executeUpdate();
											livresIDs.remove(selectedIndex);
											livreComboBox.removeItemAt(selectedIndex);
											System.out.println("SUCCESS: Le livre a été supprimé avec succès.");
											JOptionPane.showMessageDialog(window,
													"Le livre a été supprimé avec succès.",
													"Super !",
													JOptionPane.INFORMATION_MESSAGE);
											window.getContentPane().revalidate();
											window.getContentPane().repaint();
										} else {
											// Le livres est en cours d'emprunt
											System.out
													.println(
															"ERROR: Le livre est en cours d'emprunt. Impossible de le supprimer.");
											JOptionPane.showMessageDialog(window,
													"Le livre est en cours d'emprunt. Impossible de le supprimer.",
													"Oups !",
													JOptionPane.ERROR_MESSAGE);
										}
									} else {
										// Erreur lors de la vérification des livres en cours d'emprunt
										System.out.println(
												"ERROR: Erreur lors de la vérification des livres en cours d'emprunt.");
										JOptionPane.showMessageDialog(window,
												"Erreur lors de la vérification des livres en cours d'emprunt.",
												"Oups !",
												JOptionPane.ERROR_MESSAGE);
									}
								} else {
									// Le livre n'existe pas dans la table, afficher un message d'erreur
									System.out.println(
											"ERROR: Le livre sélectionné n'existe pas. Aucune suppression effectuée.");
									JOptionPane.showMessageDialog(window,
											"Le livre sélectionné n'existe pas. Aucune suppression effectuée.",
											"Oups !",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								System.out.println(
										"ERROR: Aucun livre sélectionné. Aucune suppression effectuée.");
								JOptionPane.showMessageDialog(window,
										"Aucun livre sélectionné. Aucune suppression effectuée.", "Oups !",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception exception) {
							System.out.println("ERROR: " + exception);
							JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});

				try {
					// Établit la connexion à la base de données
					Connection conn = DB.getConnection();

					// Prépare la requête pour récuperer tout les livres
					String query = "SELECT livre.isbn, livre.titre, livre.auteur, auteur.nom, auteur.prenom FROM livre JOIN auteur ON livre.auteur = auteur.num";
					PreparedStatement pstmt = conn.prepareStatement(query);

					// Exécute la requête
					ResultSet res = pstmt.executeQuery();

					// Tant que le résultat de la requête contient des lignes de données
					while (res.next()) {
						String isbn = res.getString("isbn");
						String titre_livre = res.getString("titre");
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");

						// Ajoute le nom et prénom à la liste des livres
						if (prenom != null && !prenom.isEmpty()) {
							// Ajouter le nom et prénom à la liste des livres dans la JComboBox
							livreComboBox.addItem(
									titre_livre + " - " + nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase()
											+ prenom.substring(1));
						} else if (prenom == null) {
							// Ajouter le nom à la liste des livres dans la JComboBox
							livreComboBox.addItem(titre_livre + " - " + nom.toUpperCase());
						} else {

						}

						// Ajoute l'ID à la liste des IDs des livres
						livresIDs.add(isbn);
					}

				} catch (Exception exception) {
					System.out.println("ERROR: " + exception);
					JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
							JOptionPane.ERROR_MESSAGE);
				}

				// Définit le panneau comme contenu de la fenêtre et l'affiche
				window.setContentPane(deleteLivrePanel);

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
