package projet_java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Table User (id, nom, email)
class User {
    private int id;
    private String nom;
    private String email;

    public User(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return id + "," + nom + "," + email;
    }
}

// Table App
class App {
    private int id;
    private String mon_app;

    public App(int id, String mon_app) {
        this.id = id;
        this.mon_app = mon_app;
    }

    @Override
    public String toString() {
        return id + "," + mon_app;
    }
}

// Table Connexion
class Connexion {
    private int user_id;
    private int date_connexion;

    public Connexion(int user_id, int date_connexion) {
        this.user_id = user_id;
        this.date_connexion = date_connexion;
    }

    @Override
    public String toString() {
        return user_id + "," + date_connexion;
    }
}

// Table GestionUser
class GestionUser {
    private int id;
    private String role;

    public GestionUser(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return id + "," + role;
    }
}

public class UserManager {
    private List<User> users = new ArrayList<>();
    private List<App> apps = new ArrayList<>();
    private List<Connexion> connexions = new ArrayList<>();
    private List<GestionUser> gestionUsers = new ArrayList<>();

    // Ajouter un utilisateur
    public void addUser(int id, String nom, String email) {
        users.add(new User(id, nom, email));
        System.out.println("Utilisateur ajouté : " + nom);
    }

    // Ajouter une application
    public void addApp(int id, String mon_app) {
        apps.add(new App(id, mon_app));
        System.out.println("Application ajoutée : " + mon_app);
    }

    // Ajouter une connexion
    public void addConnexion(int user_id, int id) {
        connexions.add(new Connexion(user_id, id));
        System.out.println("Connexion ajoutée : User ID " + user_id + " à id " + id);
    }

    // Ajouter un gestionnaire d'utilisateur
    public void addGestionUser(int user_id, String role) {
        gestionUsers.add(new GestionUser(user_id, role));
        System.out.println("Gestion d'utilisateur ajoutée : User ID " + user_id + " avec rôle " + role);
    }

    // Lister tous les utilisateurs
    public void listUsers() {
        System.out.println("Liste des utilisateurs :");
        for (User user : users) {
            System.out.println(user);
        }
    }

    // Supprimer un utilisateur par son ID
    public void deleteUserById(int id) {
        users.removeIf(user -> user.getId() == id);
        System.out.println("Utilisateur supprimé avec l'id : " + id);
    }

    // Modifier un utilisateur par son ID
    public void updateUserById(int id, String newNom, String newEmail) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                users.add(new User(id, newNom, newEmail));
                System.out.println("Utilisateur mis à jour : " + newNom);
                break;
            }
        }
    }

    // Rechercher un utilisateur par son nom
    public void searchUserByNom(String nom) {
        for (User user : users) {
            if (user.getNom().equalsIgnoreCase(nom)) {
                System.out.println("Utilisateur trouvé : " + user);
                return;
            }
        }
        System.out.println("Aucun utilisateur trouvé avec ce nom.");
    }

    // Générer le CSV des 4 tables
    public void generateCsv() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("java.csv"))) {
            writer.write("user_table: id,nom,email\n");
            for (User user : users) {
                writer.write(user + "\n");
            }
            writer.write("\napp_table: id,appName\n");
            for (App app : apps) {
                writer.write(app + "\n");
            }
            writer.write("\nconnexion_table: userId,appId\n");
            for (Connexion connexion : connexions) {
                writer.write(connexion + "\n");
            }
            writer.write("\ngestion_user_table: userId,role\n");
            for (GestionUser gestionUser : gestionUsers) {
                writer.write(gestionUser + "\n");
            }

            System.out.println("CSV généré avec succès : java.csv");
        } catch (IOException e) {
            System.out.println("Erreur lors de la génération du CSV.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        // Ajouter des utilisateurs
        userManager.addUser(1, "Fanta", "Fanta@example.com");
        userManager.addUser(2, "Bob", "bob@example.com");
        userManager.addUser(3, "Charlie", "charlie@example.com");

        // Ajouter des applications
        userManager.addApp(1, "App1");
        userManager.addApp(2, "App2");

        // Ajouter des connexions entre utilisateurs et applications
        userManager.addConnexion(1, 1);
        userManager.addConnexion(2, 2);

        // Ajouter des gestionnaires d'utilisateurs
        userManager.addGestionUser(1, "Admin");
        userManager.addGestionUser(2, "User");

        // Liste des utilisateurs
        System.out.println("\nListe des utilisateurs : ");
        userManager.listUsers();

        // Recherche d'un utilisateur par nom
        System.out.println("\nRecherche d'utilisateur par nom : ");
        userManager.searchUserByNom("Bob");

        // Modifier un utilisateur
        System.out.println("\nModification de l'utilisateur Bob : ");
        userManager.updateUserById(2, "BobUpdated", "bobupdated@example.com");

        // Supprimer un utilisateur
        System.out.println("\nSuppression de l'utilisateur avec l'ID 1 : ");
        userManager.deleteUserById(1);

        // Liste mise à jour des utilisateurs
        System.out.println("\nListe des utilisateurs après modifications : ");
        userManager.listUsers();

        // Générer le fichier CSV
        userManager.generateCsv();
    }
}
