
// Importe les classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AUTEUR extends JFrame {

	public static void main(String[] args) {
		// Crée une fenêtre principale
		JFrame window = new JFrame();
		window.setTitle("Auteur"); // Définit le titre de la fenêtre
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
		JLabel titre = new JLabel("<html><u>Gestion des auteurs</u></html>"); // Crée un JLabel avec le titre
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
		JButton createButton = new JButton("Créer un auteur");
		JButton modifyButton = new JButton("Modifier un auteur");
		JButton deleteButton = new JButton("Supprimer un auteur");

		// Code pour ajouter un auteur
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Cacher le buttonPanel
				buttonPanel.setVisible(false);

				// Creer un nouveau panel pour la création d'un nouveau auteur
				JPanel createAuteurPanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.CENTER;

				// Title
				JLabel titre = new JLabel("<html><u>Création d'un auteur</u></html>");
				titre.setHorizontalAlignment(SwingConstants.CENTER);

				// Définit les paramètres GridBagConstraints pour le titre
				gbc.gridx = 0; // Colonne 0
				gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
				gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
				gbc.gridy++; // Passe à la ligne suivante
				gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
				createAuteurPanel.add(titre, gbc); // Ajoute le titre au panneau central
				gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
				gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges
				gbc.gridy++; // Passe à la ligne suivante dans la grille

				// Input NOM
				gbc.gridy++;
				JLabel label_nom = new JLabel("Nom de l'auteur:"); // Crée un JLabel pour le champ de saisie
				label_nom.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à gauche
				createAuteurPanel.add(label_nom, gbc); // Ajoute le label
				JTextField nomText = new JTextField(); // Crée un champ de texte pour la saisie
				nomText.setPreferredSize(new Dimension(200, 25)); // Définit la taille préférée du champ de texte
				gbc.gridx++; // Passe à la colonne suivante dans la grille
				createAuteurPanel.add(nomText, gbc); // Ajoute le champ de texte

				// Input PRENOM
				JLabel label_prenom = new JLabel("Prénom de l'auteur:"); // Crée un JLabel pour le champ de saisie
				label_prenom.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0; // Réinitialise la colonne à 0
				gbc.gridy++; // Passe à la ligne suivante dans la grille
				createAuteurPanel.add(label_prenom, gbc); // Ajoute le label
				JTextField prenomText = new JTextField(); // Crée un champ de texte pour la saisie
				prenomText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++; // Passe à la colonne suivante dans la grille
				createAuteurPanel.add(prenomText, gbc); // Ajoute le champ de texte

				// Input DATE NAISSANCE
				JLabel label_date = new JLabel("Date de Naissance (jj/mm/aaaa):"); // Crée un JLabel pour le champ
				label_date.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0; // Réinitialise la colonne à 0
				gbc.gridy++; // Passe à la ligne suivante dans la grille
				createAuteurPanel.add(label_date, gbc); // Ajoute le label
				SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
				JFormattedTextField dateText = new JFormattedTextField(inputFormat);
				dateText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++; // Passe à la colonne suivante dans la grille
				createAuteurPanel.add(dateText, gbc); // Ajoute le champ de texte

				// Input DESCRIPTION
				JLabel label_description = new JLabel("Description de l'auteur:"); // Crée un JLabel pour le champ
				label_description.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0; // Réinitialise la colonne à 0
				gbc.gridy++; // Passe à la ligne suivante dans la grille
				createAuteurPanel.add(label_description, gbc); // Ajoute le label
				JTextField descriptionText = new JTextField(); // Crée un champ de texte pour la saisie
				descriptionText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++; // Passe à la colonne suivante dans la grille
				createAuteurPanel.add(descriptionText, gbc); // Ajoute le champ de texte

				// Input RETOUR
				gbc.insets = new Insets(30, 5, 5, 5);
				gbc.gridx = 0;
				gbc.gridy++;
				JButton Back_Button = new JButton("Retour");
				createAuteurPanel.add(Back_Button, gbc);
				// Ajoute un ActionListener au bouton "Retour" pour gérer l'événement de clic
				Back_Button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonPanel.setVisible(true); // Rend le panel des boutons visible
						createAuteurPanel.setVisible(false); // Cache le panel de création d'adhérent
						window.setContentPane(centerPanel); // Actualise le contenu de la fenêtre principale
						window.revalidate(); // Rend la fenêtre valide et prête à être affichée
					}
				});

				// Input SUBMIT
				JButton Submit_CreateButton = new JButton("Créer un auteur");
				gbc.gridx++;
				createAuteurPanel.add(Submit_CreateButton, gbc);
				// Ajoute un ActionListener au bouton pour gérer l'événement de clic
				Submit_CreateButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Récupérer les valeurs saisies dans les champs de texte
						String nom = nomText.getText();
						nom = nom.replace(" ", "_");
						String prenom = prenomText.getText();
						prenom = prenom.replace(" ", "_");
						String date_naissance_str = dateText.getText();
						String description = descriptionText.getText();
						String date_naissance_db = null;

						// Vérifie si le champs nom (obligatoire) n'est pas vides et si il est valide
						if (!nom.isEmpty() && !nom.trim().isEmpty()) {
							// Vérifie si le prénom est vide
							if (prenom.isEmpty()) {
								prenom = null;
							}

							// Vérifie si la date de naissance est vide
							if (date_naissance_str.isEmpty()) {
								date_naissance_str = null;
							} else {
								// Convertir la date de naissance dans le bon format
								SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
								SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

								try {
									Date date_naissance = inputFormat.parse(date_naissance_str);
									date_naissance_db = outputFormat.format(date_naissance);

								} catch (ParseException ex) {
									ex.printStackTrace();
									// Afficher un message d'erreur ou gérer l'erreur d'une autre manière
									System.out.println("ERROR: Format de date invalide.");
									JOptionPane.showMessageDialog(window, "Format de date invalide", "Oups !",
											JOptionPane.ERROR_MESSAGE);
								}
							}
							// Vérifie si la description est vide
							if (description.isEmpty()) {
								description = null;
							}

							try {
								// Établit la connexion à la base de données
								Connection conn = DB.getConnection();

								if (prenom == null) {
									// Prépare la requête pour vérifier si le nom existe
									String query = "SELECT * FROM auteur WHERE nom=?";
									PreparedStatement pstmt = conn.prepareStatement(query);
									pstmt.setString(1, nom);

									// Exécute la requête
									ResultSet res = pstmt.executeQuery();

									// Vérifie s'il y a des résultats
									if (res.next()) {
										// L'utilisateur existe déjà
										System.out.println("ERROR: L'auteur existe déjà.");
										JOptionPane.showMessageDialog(window, "L'auteur existe déjà.", "Oups !",
												JOptionPane.ERROR_MESSAGE);
									} else {
										// L'utilisateur n'existe pas, vous pouvez l'ajouter à la base de données
										query = "INSERT INTO auteur (nom, prenom, date_naissance, description) VALUES (?, ?, ?, ?)";
										pstmt = conn.prepareStatement(query);
										pstmt.setString(1, nom);
										pstmt.setString(2, prenom);
										pstmt.setString(3, date_naissance_db);
										pstmt.setString(4, description);
										// Exécute la requête
										pstmt.executeUpdate();
										System.out.println("SUCCESS: L'auteur a été ajouté avec succès.");
										JOptionPane.showMessageDialog(window, "L'auteur a été ajouté avec succès.",
												"Super !",
												JOptionPane.INFORMATION_MESSAGE);
									}
								} else {

									// Prépare la requête pour vérifier si l'auteur existe
									String query = "SELECT * FROM auteur WHERE nom=? AND prenom=?";
									PreparedStatement pstmt = conn.prepareStatement(query);
									pstmt.setString(1, nom);
									pstmt.setString(2, prenom);

									// Exécute la requête
									ResultSet res = pstmt.executeQuery();

									// Vérifie s'il y a des résultats
									if (res.next()) {
										// L'utilisateur existe déjà
										System.out.println("ERROR: L'auteur existe déjà.");
										JOptionPane.showMessageDialog(window, "L'auteur existe déjà.", "Oups !",
												JOptionPane.ERROR_MESSAGE);
									} else {
										// L'utilisateur n'existe pas, vous pouvez l'ajouter à la base de données
										query = "INSERT INTO auteur (nom, prenom, date_naissance, description) VALUES (?, ?, ?, ?)";
										pstmt = conn.prepareStatement(query);
										pstmt.setString(1, nom);
										pstmt.setString(2, prenom);
										pstmt.setString(3, date_naissance_db);
										pstmt.setString(4, description);
										// Exécute la requête
										pstmt.executeUpdate();
										System.out.println("SUCCESS: L'auteur a été ajouté avec succès.");
										JOptionPane.showMessageDialog(window, "L'auteur a été ajouté avec succès.",
												"Super !",
												JOptionPane.INFORMATION_MESSAGE);
									}
								}

							} catch (Exception exception) {
								System.out.println("ERROR: " + exception);
								JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							System.out.println("ERROR: Le nom de l'auteur ne peut pas être vide !");
							JOptionPane.showMessageDialog(window, "Le nom de l'auteur ne peut pas être vide !",
									"Oups !",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});

				// Définit le panneau comme contenu de la fenêtre et l'affiche
				window.setContentPane(createAuteurPanel);
			}
		});

		// Code pour modifier un auteur
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Cacher le buttonPanel
				buttonPanel.setVisible(false);

				// Créer un nouveau panel pour la création d'un nouveau auteur
				JPanel ModifyAuteurPanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.CENTER;

				// Title
				JLabel titre = new JLabel("<html><u>Modifier un auteur</u></html>");
				titre.setHorizontalAlignment(SwingConstants.CENTER);

				// Définit les paramètres GridBagConstraints pour le titre
				gbc.gridx = 0; // Colonne 0
				gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
				gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
				gbc.gridy++; // Passe à la ligne suivante
				gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
				ModifyAuteurPanel.add(titre, gbc); // Ajoute le titre au panneau central
				gbc.insets = new Insets(75, 5, 10, 5); // Espacement entre les composants
				gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
				gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

				// Crée un label pour indiquer le choix de l'auteur
				JLabel label_choix = new JLabel("Choix de l'auteur:");
				label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				ModifyAuteurPanel.add(label_choix, gbc);
				// Crée une liste déroulante avec les noms complets des auteurs
				JComboBox<String> auteurComboBox = new JComboBox<>();
				auteurComboBox.setPreferredSize(new Dimension(200, 25));
				gbc.gridx++;
				ModifyAuteurPanel.add(auteurComboBox, gbc);

				// Input NOM
				JLabel label_nom = new JLabel("Nom de l'auteur:");
				label_nom.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				ModifyAuteurPanel.add(label_nom, gbc);
				JTextField nomText = new JTextField();
				nomText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++;
				ModifyAuteurPanel.add(nomText, gbc);

				// Input PRENOM
				JLabel label_prenom = new JLabel("Prénom de l'auteur:");
				label_prenom.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				ModifyAuteurPanel.add(label_prenom, gbc);
				JTextField prenomText = new JTextField();
				prenomText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++;
				ModifyAuteurPanel.add(prenomText, gbc);

				// Input DATE NAISSANCE
				JLabel label_date = new JLabel("Date de Naissance (jj/mm/aaaa):");
				label_date.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				ModifyAuteurPanel.add(label_date, gbc);
				SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
				JFormattedTextField dateText = new JFormattedTextField(inputFormat);
				dateText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++;
				ModifyAuteurPanel.add(dateText, gbc);

				// Input DESCRIPTION
				JLabel label_description = new JLabel("Description de l'auteur:");
				label_description.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				ModifyAuteurPanel.add(label_description, gbc);
				JTextField descriptionText = new JTextField();
				descriptionText.setPreferredSize(new Dimension(200, 25)); // Définir une taille standard
				gbc.gridx++;
				ModifyAuteurPanel.add(descriptionText, gbc);

				// Input RETOUR
				gbc.insets = new Insets(30, 5, 5, 5);
				gbc.gridx = 0;
				gbc.gridy++;
				JButton Back_Button = new JButton("Retour");
				ModifyAuteurPanel.add(Back_Button, gbc);

				Back_Button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonPanel.setVisible(true);
						// Cacher le panel de création d'auteur
						ModifyAuteurPanel.setVisible(false);
						// Actualiser le contenu de la fenêtre principale
						window.setContentPane(centerPanel);
						window.revalidate();
					}
				});

				// Input SUBMIT
				JButton Submit_ModificationButton = new JButton("Modifier un auteur");
				gbc.gridx++;
				ModifyAuteurPanel.add(Submit_ModificationButton, gbc);

				// Créer une liste pour stocker les IDs des auteurs
				List<String> AuteursIDs = new ArrayList<>();

				// Ajoute un ActionListener au bouton pour gérer l'événement de clic
				Submit_ModificationButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Récupérer les valeurs saisies dans les champs de texte
						String nom = nomText.getText();
						nom = nom.replace(" ", "_"); // Remplace les espaces par des underscores
						String prenom = prenomText.getText();
						prenom = prenom.replace(" ", "_"); // Remplace les espaces par des underscores
						String date_naissance_str = dateText.getText();
						String description = descriptionText.getText();
						String date_naissance_db = null;

						if (!AuteursIDs.isEmpty()) {

							// Vérifie si le nom n'est pas vide et s'il ne contient que des espaces
							if (!nom.isEmpty() && !nom.trim().isEmpty()) {
								if (prenom.isEmpty()) {
									prenom = null; // Si le prénom est vide, le mettre à null
								}

								if (date_naissance_str.isEmpty()) {
									date_naissance_str = null; // Si la date de naissance est vide, la mettre à null
								} else {
									// Convertir la date de naissance dans le bon format
									SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
									SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

									try {
										Date date_naissance = inputFormat.parse(date_naissance_str);
										date_naissance_db = outputFormat.format(date_naissance);

									} catch (Exception ex) {
										// Afficher un message d'erreur si le format de date est invalide
										System.out.println("ERROR: Format de date invalide");
										JOptionPane.showMessageDialog(window, "Format de date invalide !",
												"Oups !", JOptionPane.ERROR_MESSAGE);
									}
								}
								// Si la description est vide, la mettre à null
								if (description.isEmpty()) {
									description = null;
								}

								try {
									// Établit la connexion à la base de données
									Connection conn = DB.getConnection();

									// Récupérer l'ID de l'adhérent sélectionné
									int selectedIndex = auteurComboBox.getSelectedIndex();
									String ancienID = AuteursIDs.get(selectedIndex);
									System.out.println(ancienID);

									if (prenom == null) {
										// Vérifier si le nouveau nom existe déjà (si différent de l'ancien nom)
										String querySelect = "SELECT * FROM auteur WHERE nom=? AND num != ?";
										PreparedStatement pstmtSelect = conn.prepareStatement(querySelect);
										pstmtSelect.setString(1, nom);
										pstmtSelect.setString(2, ancienID);
										ResultSet resSelect = pstmtSelect.executeQuery();

										if (!resSelect.next()) {
											// Le nouveau nom n'existe pas ou est le même que l'ancien
											String queryUpdate = "UPDATE auteur SET nom=?, prenom=?, date_naissance=?, description=? WHERE num=?";
											PreparedStatement pstmtUpdate = conn.prepareStatement(queryUpdate);
											pstmtUpdate.setString(1, nom);
											pstmtUpdate.setString(2, prenom);
											pstmtUpdate.setString(3, date_naissance_db);
											pstmtUpdate.setString(4, description);
											pstmtUpdate.setString(5, ancienID);

											pstmtUpdate.executeUpdate();

											// Afficher un message de succès si la modification a été effectuée avec
											// succès
											System.out.println(
													"SUCCESS: L'auteur a été modifié avec succès, pour voir la modification, merci de recharger la page.");
											JOptionPane.showMessageDialog(window,
													"L'auteur a été modifié avec succès, pour voir la modification, merci de recharger la page.",
													"Super !", JOptionPane.INFORMATION_MESSAGE);

										} else {
											// Afficher un message d'erreur si le nouveau nom existe déjà
											System.out.println(
													"Le nouveau nom existe déjà. Impossible de modifier l'auteur.");
											JOptionPane.showMessageDialog(window,
													"Le nouveau nom existe déjà. Impossible de modifier l'auteur.",
													"Oups !", JOptionPane.ERROR_MESSAGE);
										}

									} else {
										// Vérifier si le nouveau nom et prenom existe déjà (si différent de l'ancien
										// nom)
										String querySelect = "SELECT * FROM auteur WHERE nom=? AND prenom =? AND num != ?";
										PreparedStatement pstmtSelect = conn.prepareStatement(querySelect);
										pstmtSelect.setString(1, nom);
										pstmtSelect.setString(2, prenom);
										pstmtSelect.setString(3, ancienID);
										ResultSet resSelect = pstmtSelect.executeQuery();
										if (!resSelect.next()) {
											// Le nouveau nom n'existe pas ou est le même que l'ancien
											String queryUpdate = "UPDATE auteur SET nom=?, prenom=?, date_naissance=?, description=? WHERE num=?";
											PreparedStatement pstmtUpdate = conn.prepareStatement(queryUpdate);
											pstmtUpdate.setString(1, nom);
											pstmtUpdate.setString(2, prenom);
											pstmtUpdate.setString(3, date_naissance_db);
											pstmtUpdate.setString(4, description);
											pstmtUpdate.setString(5, ancienID);

											pstmtUpdate.executeUpdate();

											// Afficher un message de succès si la modification a été effectuée avec
											// succès
											System.out.println(
													"SUCCESS: L'auteur a été modifié avec succès, pour voir la modification, merci de recharger la page.");
											JOptionPane.showMessageDialog(window,
													"L'auteur a été modifié avec succès, pour voir la modification, merci de recharger la page.",
													"Super !", JOptionPane.INFORMATION_MESSAGE);

										} else {
											// Afficher un message d'erreur si le nouveau nom existe déjà
											System.out
													.println("L'auteur' existe déjà. Impossible de modifier l'auteur.");
											JOptionPane.showMessageDialog(window,
													"L'auteur existe déjà. Impossible de modifier l'auteur.",
													"Oups !", JOptionPane.ERROR_MESSAGE);
										}
									}

								} catch (Exception exception) {
									// Afficher un message d'erreur en cas d'exception
									System.out.println("ERROR: " + exception);
									JOptionPane.showMessageDialog(window, "Une erreur est survenue.", "Oups !",
											JOptionPane.ERROR_MESSAGE);
								}

							} else {
								// Afficher un message d'erreur si le nom est vide ou ne contient des espaces
								System.out.println("ERROR: Le nom de l'auteur ne peut pas être vide !");
								JOptionPane.showMessageDialog(window, "Le nom de l'auteur ne peut pas être vide !",
										"Oups !", JOptionPane.ERROR_MESSAGE);
							}

						} else {
							// L'adhérent n'existe pas dans la table, afficher un message d'erreur
							System.out.println(
									"ERROR: L'auteur sélectionné n'existe pas. Aucune suppression effectuée.");
							JOptionPane.showMessageDialog(window,
									"L'auteur sélectionné n'existe pas. Aucune suppression effectuée.", "Oups !",
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

					// Créer une liste pour stocker les date de naissance des auteurs
					List<String> auteursNaissance = new ArrayList<>();

					// Créer une liste pour stocker les descriptions des auteurs
					List<String> auteurDescription = new ArrayList<>();

					// Tant que le résultat de la requête contient des lignes de données
					while (res.next()) {
						// Récupère les informations sur l'adhérent depuis le résultat de la requête
						String id = res.getString("num");
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						String dateNaissance = res.getString("date_naissance");
						String description = res.getString("description");

						if (prenom != null && !prenom.isEmpty()) {
							// Ajouter le nom et prénom à la liste des auteurs dans la JComboBox
							auteurComboBox.addItem(nom.toUpperCase() + " " + prenom.substring(0, 1).toUpperCase()
									+ prenom.substring(1));
						} else if (prenom == null) {
							// Ajouter le nom à la liste des auteurs dans la JComboBox
							auteurComboBox.addItem(nom.toUpperCase());
						} else {

						}

						// Ajouter l'ID à la liste des IDs des auteurs
						AuteursIDs.add(id);
						auteursNaissance.add(dateNaissance);
						auteurDescription.add(description);
					}

					// Écouteur d'événements pour la liste déroulante
					auteurComboBox.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Récupérer l'indice de l'élément sélectionné dans la liste déroulante
							int selectedIndex = auteurComboBox.getSelectedIndex();
							// Vérifier si un élément est sélectionné
							if (selectedIndex != -1) {
								// Récupérer les info associé à l'auteur sélectionné
								String dateNaissance = auteursNaissance.get(selectedIndex);
								String description = auteurDescription.get(selectedIndex);

								// Récupérer les informations de l'auteur sélectionné dans le tableau
								String selectedAuteur = (String) auteurComboBox.getSelectedItem();
								String[] selectedAuteurInfo = selectedAuteur.split(" ");
								String nom = selectedAuteurInfo[0];
								String prenom = "";
								if (selectedAuteurInfo.length > 1) {
									prenom = selectedAuteurInfo[1];
								}

								// Remplir les champs de texte avec les informations de l'auteur sélectionné
								nomText.setText(nom); // Nom
								prenomText.setText(prenom != null ? prenom : ""); // Prénom

								if (dateNaissance != null) {
									try {
										// Convertir la date de naissance dans le bon format
										SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
										java.util.Date parsedDate = dateFormat.parse(dateNaissance);
										SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
										dateText.setText(newDateFormat.format(parsedDate));
									} catch (ParseException exception) {
										// Afficher un message d'erreur en cas d'échec de conversion de la date
										System.err
												.println("Erreur de conversion de la date : " + exception.getMessage());
									}
								} else {
									dateText.setText(dateNaissance); // Afficher la date telle quelle en cas d'échec
								}
								descriptionText.setText(dateNaissance);
								descriptionText.setText(description);
							}
						}
					});

				} catch (Exception exception) {
					System.out.println(exception);
				}

				// Définit le panneau central comme contenu de la fenêtre et l'affiche
				window.setContentPane(ModifyAuteurPanel);
			}
		});

		// Code pour supprimer un auteur
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Cacher le buttonPanel
				buttonPanel.setVisible(false);

				// Créer un nouveau panel pour la création d'un nouveau auteur
				JPanel DeleteAuteurPanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.CENTER;

				// Title
				JLabel titre = new JLabel("<html><u>Supprimer un auteur</u></html>");
				titre.setHorizontalAlignment(SwingConstants.CENTER);

				// Définit les paramètres GridBagConstraints pour le titre
				gbc.gridx = 0; // Colonne 0
				gbc.gridwidth = 4; // Étend le titre sur 4 colonnes
				gbc.fill = GridBagConstraints.NONE; // Annuler le remplissage horizontal
				gbc.gridy++; // Passe à la ligne suivante
				gbc.insets = new Insets(5, 5, 30, 5); // Marge supplémentaire en bas
				DeleteAuteurPanel.add(titre, gbc); // Ajoute le titre au panneau central
				gbc.gridwidth = 1; // Réinitialise le nombre de colonnes
				gbc.insets = new Insets(5, 5, 5, 5); // Réinitialise les marges

				JLabel label_choix = new JLabel("Choix de l'auteur:");
				label_choix.setHorizontalAlignment(SwingConstants.LEFT); // Aligner à droite
				gbc.gridx = 0;
				gbc.gridy++;
				DeleteAuteurPanel.add(label_choix, gbc);
				JComboBox<String> auteurComboBox = new JComboBox<>();
				auteurComboBox.setPreferredSize(new Dimension(200, 25));
				gbc.gridx++;
				DeleteAuteurPanel.add(auteurComboBox, gbc);

				// Input RETOUR
				gbc.insets = new Insets(30, 5, 5, 5);
				gbc.gridx = 0;
				gbc.gridy++;
				JButton Back_Button = new JButton("Retour");
				DeleteAuteurPanel.add(Back_Button, gbc);
				Back_Button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonPanel.setVisible(true);
						// Cacher le panel de création d'auteur
						DeleteAuteurPanel.setVisible(false);
						// Actualiser le contenu de la fenêtre principale
						window.setContentPane(centerPanel);
						window.revalidate();
					}
				});

				// Input SUPPR
				JButton Delete_ModificationButton = new JButton("Supprimer un auteur");
				gbc.gridx++;
				DeleteAuteurPanel.add(Delete_ModificationButton, gbc);

				// Créer une liste pour stocker les IDs des auteurs
				List<String> auteursIDs = new ArrayList<>();

				// Ajoute un ActionListener au bouton pour gérer l'événement de clic
				Delete_ModificationButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							// Établit la connexion à la base de données
							Connection conn = DB.getConnection();

							if (!auteursIDs.isEmpty()) {

								// Récupérer l'ID de l'auteur sélectionné
								int selectedIndex = auteurComboBox.getSelectedIndex();
								String ancienID = auteursIDs.get(selectedIndex);
								System.out.println(ancienID);

								String queryCheck = "SELECT * FROM auteur WHERE num=?";
								PreparedStatement pstmtCheck = conn.prepareStatement(queryCheck);
								pstmtCheck.setString(1, ancienID);
								ResultSet resCheck = pstmtCheck.executeQuery();

								if (resCheck.next()) {
									// L'auteur existe, on peut le supprimer
									String queryDelete = "DELETE FROM auteur WHERE num=?";
									PreparedStatement pstmtDelete = conn.prepareStatement(queryDelete);
									pstmtDelete.setString(1, ancienID);
									pstmtDelete.executeUpdate();
									auteursIDs.remove(selectedIndex);
									auteurComboBox.removeItemAt(selectedIndex);
									System.out.println("SUCCESS: L'auteur a été supprimé avec succès.");
									JOptionPane.showMessageDialog(window, "L'auteur a été supprimé avec succès.",
											"Super !",
											JOptionPane.INFORMATION_MESSAGE);
									window.getContentPane().revalidate();
									window.getContentPane().repaint();
								} else {
									// L'auteur n'existe pas, afficher un message d'erreur
									System.out
											.println(
													"ERROR: L'auteur sélectionné n'existe pas. Aucune suppression effectuée.");
									JOptionPane.showMessageDialog(window,
											"L'auteur sélectionné n'existe pas. Aucune suppression effectuée.",
											"Oups !",
											JOptionPane.ERROR_MESSAGE);
								}

							} else {
								System.out.println(
										"ERROR: Aucun auteur sélectionné. Aucune suppression effectuée.");
								JOptionPane.showMessageDialog(window,
										"Aucun auteur sélectionné. Aucune suppression effectuée.", "Oups !",
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
				window.setContentPane(DeleteAuteurPanel);

			}
		});

		// Ajoute le panneau des boutons au panneau central
		centerPanel.add(buttonPanel, gbc);
		buttonPanel.add(createButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(deleteButton);

		// Définit le panneau central comme contenu de la fenêtre et l'affiche
		window.setContentPane(centerPanel);
		window.setVisible(true);
	}
}
