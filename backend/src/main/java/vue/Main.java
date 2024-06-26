package vue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.maps.model.LatLng;
import dao.JpaUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.Astrologue;
import modele.Cartomancien;
import modele.Client;
import modele.Consultation;
import modele.Employe;
import modele.Medium;
import modele.Spirit;
import service.Service;
import service.Service_init;
import util.Saisie;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        
        Boolean demo = true; // false -> fonction test, true -> menu pour la demo
        
        if (demo){
            // Toutes les définitions
            Long id;
            Boolean continuer = true;
            String choix;
            String nom;
            String prenom;
            String mail;
            String motDePasse;
            String tel;
            String adressePostale;
            Date dateNaissance = null;
            Boolean genre;
            String sexe;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Client client = null;
            Client client2 = null;
            Client client3 = null;
            Employe employe = null;
            Medium medium = null;
            Medium medium2 = null;
            List<Medium> listmedium = new ArrayList<>();
            String commentaire;
            List<String> listCommentaire = new ArrayList<>();
            List<Consultation> listConsultation = new ArrayList<>();
            List<LatLng> listLatLng = new ArrayList<>();
            List<Client> listclient = new ArrayList<>();
            Integer noteAmour;
            Integer noteSante;
            Integer noteTravail;
            Consultation consultation = null;
            Consultation consultation2 = null;
            Service service = new Service();
            
            Service_init.chargerEmployes();
            Service_init.chargerMediums();
                
            System.out.println("\n/!\\ Certains tests doivent être effectué avant d'autres /!\\");
            System.out.println("Pour éviter tout problème executez les tests à la suite (sauf le 3)");
            
            while (continuer){
                System.out.println("\nMenu demo de DASI :\n");
                System.out.println("1: Création client");
                System.out.println("2: Authentification client");
                System.out.println("3: Déconnecter Client (pour main)");
                System.out.println("4: Voir tous les mediums");
                System.out.println("5: Demander une consultation (côté client)");
                System.out.println("6: Rechercher une consultation en cours via l'id client/employé");
                System.out.println("7: Obtenir les infos du client");
                System.out.println("8: Consulter son profil astral");
                System.out.println("9: Obtenir l'historique des consultations d'un client");
                System.out.println("10: Etre Pret pour la consultation (côté employe)");
                System.out.println("11: Obtenir les predictions sur le client (côté employé)");
                System.out.println("12: Mettre fin à la consultation (côté employe)");
                System.out.println("13: Saisir un commentaire pour la consultation (côté employe)");
                System.out.println("14: Consulter tous les commentaires (côté employe)");
                System.out.println("15: Obtenir les coordonnées gps des clients (côté employe)");
                System.out.println("16: Obtenir le le top 5 des médiums (côté employe)");
                System.out.println("17: Rechercher objet médium par son nom");
                System.out.println("18: Obtenir la liste des clients qui ont reçu une consultation d'un employe");
                
                
                System.out.println("\nTESTS Bugs :");
                System.out.println("19: Inscrire client avec la même adresse mail/numéro de tel");
                System.out.println("20: Consulter alors qu'il est en consultation");
                System.out.println("21: Authentifier avec mauvais adresse mail/mdp");
                System.out.println("22: Consulter alors qu'il n'y a pas d'employé disponible");
                System.out.println("23: Terminer consultation déjà terminé");
                System.out.println("24: Mettre prêt d'une consultation déjà terminé");
                System.out.println("25: Chercher un médium par un faux nom");
                
                
                System.out.println("\n30: Arrêter demo :\n");

                choix = Saisie.lireChaine("Veuillez choisir : ");
                System.out.println("");
                
                switch(choix){

                    case "1": // Création d'un client
                        if (client == null){
                            nom = "Victor";
                            prenom = "Hugo";
                            sexe = "Homme";
                            mail = "vhugo@paris.fr";
                            motDePasse = "mdp1234";
                            tel = "0011223344";
                            adressePostale = "Paris";

                            try{
                                dateNaissance = simpleDateFormat.parse("10/10/2000");
                            } catch (Exception ex) {
                                System.out.println("\nERREUR !!!!!!!!!!\n");
                            }
                            
                            
                            client = new Client(nom, prenom, sexe, mail, motDePasse, tel, adressePostale, dateNaissance);
                            System.out.println("Réussite de l'inscription : " + service.inscrireClient(client));
                            client = null;
                            
                            
                            
                            // Inscription d'un deuxième client (pour des tests plus tard)
                            nom = "unNom";
                            prenom = "unPrenom";
                            sexe = "Homme";
                            mail = "mail@paris.fr";
                            motDePasse = "unmdp";
                            tel = "9911223344";
                            adressePostale = "Lyon";
                            client2 = new Client(nom, prenom, sexe, mail, motDePasse, tel, adressePostale, dateNaissance);
                            service.inscrireClient(client2);
                            
                            
                        }
                        else{
                            System.out.println("Vous êtes déjà connectés !");
                        }
                        break;

                    case "2": // Authentifier un client
                        if (client == null){
                            mail = "vhugo@paris.fr";
                            motDePasse = "mdp1234";

                            client = service.authentifierClient(mail, motDePasse);
                            if (client == null){
                                System.out.println("Vos informations ne sont pas correctes");
                            }
                            else{
                                System.out.println("Client connecté : " + client);
                            }
                        }
                        else{
                            System.out.println("Vous êtes déjà connectés !");
                        }
                        break;

                    case "3":
                        if (client == null){
                            System.out.println("Vous n'êtes pas connectés !");
                        }
                        else{
                            client = null;
                            System.out.println("Vous venez d'être déconnectés !");
                        }
                        break;
                        
                    case "4": // Afficher la liste des mediums (+ charger la liste des médiums)
                        listmedium = service.voirMedium();
                        for (int i=0; i< listmedium.size(); ++i){
                            System.out.println("Médium "+i+" :" + listmedium.get(i));
                        }
                        break;
                        
                    case "5": // Ajouter une consultation (avec comparaison des attributs avant et après la fin de la consultation)
                        if (client != null){
                            medium = listmedium.get(0);
                            
                            consultation = service.ajouterConsultation(client, medium);
                            
                            System.out.println("Consultation obtenue : " + consultation);
                            
                            System.out.println("Avant la fin de consultation :");
                            System.out.println("Attribut 'enConsultation' du client : " + client.getEnConsultation());
                            System.out.println("Attribut 'disponibilite' de l'employe : " + consultation.getEmploye().getDisponibilite());
                            System.out.println("Attribut 'nombreConsultations' de l'employe : " + consultation.getEmploye().getNombreConsultations());
                            System.out.println("Attribut 'nombreConsultations' du médium : " + consultation.getMedium().getNombreConsultations());
                        }
                        else{
                            System.out.println("Vous êtes déconnectés !");
                        }
                        break;
                        
                    case "6": // Recherche une possible consultation en cours pour un employé
                        // ATTENTION IL FAUT FAIRE LE CAS 5 AVANT (IL FAUT QU'IL Y AIT UNE CONSULTATION EN COURS)
                        
                        // On va trouver une consultation
                        id = client.getId();
                        consultation = service.rechercherConsultationEnCoursClient(id);
                        System.out.println("Consultation qui est en cours pour le client d'id = "+id+" : " + consultation); // Il y a une consultation
                        
                        // On ne va pas trouver une consultation
                        id = client2.getId();
                        consultation2 = service.rechercherConsultationEnCoursClient(id);
                        System.out.println("Consultation qui est en cours pour le client d'id = "+id+" : " + consultation2); // Il y n'a pas de consultation
                        
                        // On va trouver une consultation
                        id = consultation.getEmploye().getId();
                        consultation = service.rechercherConsultationEnCoursEmploye(id);
                        System.out.println("Consultation qui est en cours pour l'employe d'id = "+id+" : " + consultation); // Il y a une consultation
                        
                        // On ne va pas trouver une consultation
                        id = (long) 1;
                        consultation2 = service.rechercherConsultationEnCoursEmploye(id);
                        System.out.println("Consultation qui est en cours pour l'employe d'id = "+id+" : " + consultation2); // Il y n'a pas de consultation
                        break;
                        
                    case "7": // Affiche les informations du client qui demande la consultation
                        
                        client = consultation.getClient();
                        
                        System.out.println("Nom = " + client.getNom());
                        System.out.println("Prenom = " + client.getPrenom());
                        System.out.println("Mail = " + client.getMail());
                        System.out.println("Tel = " + client.getTel());
                        System.out.println("AdressePostale = " + client.getAdressePostale());
                        
                        break;
                        
                    case "8": // Affiche le profil astral du client qui demande la consultation
                        System.out.println("Profil astral = " + client.getProfilAstral());
                        
                        break;
                        
                    case "9": // Obtient la liste des consultation d'un client (et les affichent)
                        
                        listConsultation = client.getConsultations();
                        for (int i=0; i< listConsultation.size(); ++i){
                            System.out.println("Consultation "+i+" : " + listConsultation.get(i));
                        }
                        break;
                        
                    case "10": // Met prêt pour commencer la consultation (et affiche le résultat de la requête)
                        
                        System.out.println("Réussite de l'envoie du message : " +  service.etrePret(consultation));
                        
                        break;
                        
                    case "11": // Obtient la prédiction avec les notes d'amour, de santé et de travail (et l'affiche)
                        
                        noteAmour = 1;
                        noteSante = 2;
                        noteTravail = 3;
                        client = consultation.getClient();
                        System.out.println("Résultat de la prédiction : " + service.obtenirPredictions(client, noteAmour, noteSante, noteTravail));
                        break;
                        
                    case "12": // Termine une consultation (avec comparaison des attributs avant et après la fin de la consultation)
                        
                        System.out.println("Réussite de fin de consultation :" +  service.finConsultation(consultation));
                        
                        consultation = service.rechercherConsultationParId(consultation.getId()); // Raffraichir la consultation
                        System.out.println("Consultation :" +  consultation);
                        
                        System.out.println("Aprés la fin de consultation :");
                        System.out.println("Attribut 'enConsultation' du client : " + consultation.getClient().getEnConsultation());
                        System.out.println("Attribut 'disponibilite' de l'employe : " + consultation.getEmploye().getDisponibilite());
                        System.out.println("Attribut 'nombreConsultations' de l'employe : " + consultation.getEmploye().getNombreConsultations());
                        System.out.println("Attribut 'nombreConsultations' du médium : " + consultation.getMedium().getNombreConsultations());
                        
                        break;
                        
                    case "13": // Ajoute un commentaire à la consultation précédente (et affiche le résultat)
                        
                        commentaire = "Ceci est un commentaire";
                        
                        System.out.println("Réussite de la mise du commentaire : " +  service.validerCommentaire(consultation, commentaire));
                        consultation = service.rechercherConsultationParId(consultation.getId()); // Raffraichir la consultation
                        System.out.println("Consultation : " + consultation);
                        break;
                        
                    case "14": // Obtient la liste de tous les commentaire non nul (et les affichent)
                        
                        listCommentaire = service.voirCommentaires();
                        for (int i=0; i< listCommentaire.size(); ++i){
                            System.out.println("Commentaire "+i+" :" + listCommentaire.get(i));
                        }
                        break;
                        
                    case "15": // Obtient la liste des coordonées GPS de tous les client pour la carte (et les affichent)
                        
                        listLatLng = service.voirClientsMap();
                        for (int i=0; i< listLatLng.size(); ++i){
                            System.out.println("Latitude Longitude "+i+" : " + listLatLng.get(i));
                        }
                        break;
                        
                    case "16": // Obtient le top 5 des médiums les plus consultés (et les affichent)
                        
                        listmedium = service.top5Medium();
                        for (int i=0; i< listmedium.size(); ++i){
                            System.out.println("Médium n°"+(i+1)+" : " + listmedium.get(i));
                        }
                        break;
                        
                    case "17": // Cherche un médium par sa dénomination (ici on cherche lui-même)
                        
                        System.out.println("Médium avant recherche : " + medium);
                        
                        nom = medium.getDenomination();
                        medium = service.rechercherMediumParNom(nom);
                        
                        System.out.println("Médium trouvé aprés    : " + medium);
                        break;
                        
                    case "18": // Cherche la liste des clients qui ont fait une consultation avec l'employe
                        
                        employe = consultation.getEmploye();
                        listclient = service.voirClientsParEmp(employe);
                        for (int i=0; i< listclient.size(); ++i){
                            System.out.println("Client "+i+" : " + listclient.get(i));
                        }
                        break;
                        
                    case "19": // Test d'erreur avec inscription (même email/tel)
                        nom = "Nom";
                        prenom = "Victor";
                        sexe = "Homme";
                        mail = "vhugo@paris.fr";
                        motDePasse = "mdp1234";
                        tel = "0011223355";
                        adressePostale = "Paris";
                        
                        try{
                            dateNaissance = simpleDateFormat.parse("10/10/2000");
                        } catch (Exception ex) {
                            System.out.println("\nERREUR\n");
                            client = null;
                        }  
                        
                        // Avec une adresse mail déjà prise
                        client2 = new Client(nom, prenom, sexe, mail, motDePasse, tel, adressePostale, dateNaissance);
                        System.out.println("Réussite de l'inscription : " + service.inscrireClient(client));
                        
                        
                        mail = "mail@paris.fr";
                        tel = "0011223344";
                        
                        // Avec un numéro de téléphone déjà pris
                        client2 = new Client(nom, prenom, sexe, mail, motDePasse, tel, adressePostale, dateNaissance);
                        System.out.println("Réussite de l'inscription : " + service.inscrireClient(client));
                        
                        
                        mail = "johan.woip@google.fr";
                        tel = "0011223355";
                        
                        // Avec une adresse mail déjà prise par un employé
                        client2 = new Client(nom, prenom, sexe, mail, motDePasse, tel, adressePostale, dateNaissance);
                        System.out.println("Réussite de l'inscription : " + service.inscrireClient(client));
                        
                        
                        mail = "mail@paris.fr";
                        tel = "0647125934";
                        
                        // Avec un numéro de téléphone déjà pris par un employé
                        client2 = new Client(nom, prenom, sexe, mail, motDePasse, tel, adressePostale, dateNaissance);
                        System.out.println("Réussite de l'inscription : " + service.inscrireClient(client));
                        
                        break;
                        
                    case "20": // Client qui reconsulte alors qu'il est en consultation
                        if (client != null){
                            medium = listmedium.get(0);
                            
                            System.out.println("Consultation obtenue : " + (consultation = service.ajouterConsultation(client, medium)));
                            
                            System.out.println("Consultation obtenue : " + (consultation2 = service.ajouterConsultation(client, medium)));
                            service.finConsultation(consultation);
                        }
                        else{
                            System.out.println("Vous êtes déconnectés !");
                        }
                        break;
                        
                    case "21": // Authentifier avec le mauvais eMail/mdp

                        client2 = service.authentifierClient("vhugo@paris.fr", "mdptrucbidule");
                        System.out.println("Ceci doit être null : " + client2);

                        client2 = service.authentifierClient("v@paris.fr", "mdp1234");
                        System.out.println("Ceci doit être null : " + client2);
                        
                        break;
                        
                    case "22": // Demander consultation alors qu'il n'y a pas d'employe libre
                        client = service.authentifierClient("vhugo@paris.fr", "mdp1234");
                        medium = listmedium.get(0);
                        
                        mail = "mail@paris.fr";
                        motDePasse = "unmdp";
                        client2 = service.authentifierClient(mail, motDePasse);
                        
                        consultation = service.ajouterConsultation(client, medium);
                        System.out.println("Consultation obtenue : " + consultation);
                            
                        consultation2 = service.ajouterConsultation(client2, medium);
                        System.out.println("Consultation obtenue : " + consultation2);
                        
                        service.finConsultation(consultation);
                        
                        break;
                        
                    case "23": // Terminer une consultation déjà terminé
                        
                        System.out.println("Résultat obtenue : " + service.finConsultation(consultation));
                        
                        break;
                        
                    case "24": // Mettre prêt à une consultation fini
                        
                        System.out.println("Résultat obtenue : " + service.etrePret(consultation));
                        
                        break;
                        
                    case "25": // Cherche un médium par une fausse dénomination
                        
                        nom = "unFauxNom";
                        medium2 = service.rechercherMediumParNom(nom);
                        
                        System.out.println("Médium trouvé : " + medium2);
                        break;
                        
                    case "30":
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix incorrect");
                        break;
                }
                
            }
            
        }
        else {
        
        
        
        // LES TEST DOIVENT ÊTRE EFFECTUÉ DANS L'ORDRE
        
        //test1(); // TEST Création dans base de donnée d'un client et obtenir sa prédiction + obtenir latitued et longitudes
        
        //test2(); // TEST Authentification d'un client : (Réussi, Mauvais Email, Mauvais MDP)
        
        //test3(); // TEST Création dans base de donnée d'un employé
        
        //test4(); // TEST Authentification d'un employé : (Réussi, Mauvais Email, Mauvais MDP)
        
        //test5(); // TEST Création des différents types de médians
        
        //test6(); // TEST Création d'une consultation + recherche de la dernière consultation d'un employé (ayant et n'ayant pas de consultation en cours) et envoyer être prêt
        
        //test7(); // TEST Validation de la consultation de test6 + mettre commentaire + voir commentaires
        
        //test8(); // TEST Affiche les mediums dans la base de données mis par le test5 + le top 5
        
        test9(); // TEST Test l'attribut enConsultation de la classe client
        

        
        
        }
        JpaUtil.fermerFabriquePersistance();
    }

    public static Client test1() {
        System.out.println("\nDEBUT DU TEST 1\n");
        Service temp = new Service();
        Date dateNaissance = null;
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1995");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Client c = new Client("pas", "d'idée", "Homme", "qqchose@paris.fr", "mdp", "tel 00", "Lyon", dateNaissance);
        temp.inscrireClient(c);
        c = new Client("Victor", "Hugo", "Homme","vhugo@paris.fr", "mdptrucbidule", "00 11 22 33 44", "Paris", dateNaissance);
        temp.inscrireClient(c);
        System.out.println(c);
        
        System.out.println("Prédiction random :");
        System.out.println("Résultat" + temp.obtenirPredictions(c, 1, 2, 3));
        
        List<LatLng> listLatLng = temp.voirClientsMap();
        for (int i=0; i< listLatLng.size(); ++i){
            System.out.println("Résultat :" + listLatLng.get(i) + "\n");
            
        }
        
        
        System.out.println("\nFIN DU TEST 1\n");
        return c;
    }
    
    public static void test2() {
        System.out.println("\nDEBUT DU TEST 2\n");
        
        test1(); // Pour créer l'utilisateur
        
        Client c;
        Service temp = new Service();
        c = temp.authentifierClient("vhugo@paris.fr", "mdptrucbidule");
        System.out.println("Ceci doit être un client :\n" + c);
        
        c = temp.authentifierClient("v@paris.fr", "mdptrucbidule");
        System.out.println("Ceci doit être null :\n" + c);
        
        c = temp.authentifierClient("vhugo@paris.fr", "mdp");
        System.out.println("Ceci doit être null :\n" + c);
        
        System.out.println("\nFIN DU TEST 2\n");

    }
    
    
    public static void test3() {
        System.out.println("\nDEBUT DU TEST 3\n");
        Date dateNaissance = null;
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1995");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Employe e = new Employe("Victoro", "Hugoro",Boolean.TRUE , "vhugoro@lyon.fr", "mdptrucbidule", "00 11 22 33 44", "Lyon", dateNaissance);
        Service temp = new Service();

        System.out.println(temp.inscrireEmploye(e));
        
        System.out.println(e);
        System.out.println("\nFIN DU TEST 3\n");

    }
    
    public static void test4() {
        System.out.println("\nDEBUT DU TEST 4\n");
        
        test3(); // Pour créer l'utilisateur
        
        Employe e;
        Service temp = new Service();
        e = temp.authentifierEmploye("vhugoro@lyon.fr", "mdptrucbidule");
        System.out.println("Ceci doit être un employé :\n" + e);
        
        e = temp.authentifierEmploye("vro@paris.fr", "mdptrucbidule");
        System.out.println("Ceci doit être null :\n" + e);
        
        e = temp.authentifierEmploye("vhugoro@lyon.fr", "mdp");
        System.out.println("Ceci doit être null :\n" + e);
        
        System.out.println("\nFIN DU TEST 4\n");

    }
    
    public static void test5() {
        System.out.println("\nDEBUT DU TEST 5\n");
        
        Medium m;
        Service temp = new Service();
        
        m = new Astrologue("formationA", 2024, "denominationA", true, "presentationA");
        m.setNombreConsultations(1);
        System.out.println("Résultat :" + temp.ajouterMedium(m) + "\n");
        
        m = new Cartomancien("denominationC", false, "presentationC");
        System.out.println("Résultat :" + temp.ajouterMedium(m) + "\n");
        
        List<String> supports = new ArrayList<>();
        supports.add("support1");
        supports.add("support2");
        m = new Spirit(supports, "denominationS", true, "presentationS");
        m.setNombreConsultations(3);
        System.out.println("Résultat :" + temp.ajouterMedium(m) + "\n");
        
        System.out.println("\nFIN DU TEST 5\n");

    }
    
    public static Consultation test6() {
        System.out.println("\nDEBUT DU TEST 6\n");
        
        Date dateNaissance = null;
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1995");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Client c = new Client("Victor", "Hugo", "Homme", "vhugo@paris.fr", "mdptrucbidule", "00 11 22 33 44", "Paris", dateNaissance);
        Service temp = new Service();
        temp.inscrireClient(c);
        
        
        Medium m = new Astrologue("formationA", 2024, "denominationA", true, "presentationA");
        temp.ajouterMedium(m);
        
        
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1990");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Employe e = new Employe("Victoro", "Hugoro" ,true , "vhugoro@lyon.fr", "mdptrucbidule", "00 11 22 33 44", "Lyon", dateNaissance);
        temp.inscrireEmploye(e);
        
        Employe e1 = new Employe("a", "b" ,false , "ab@lyon.fr", "abcd", "00 11 22 33 55", "Lyon", dateNaissance);
        temp.inscrireEmploye(e1);
        
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        dateNaissance = simpleDateFormat.parse("10/12/2000 10:25:11");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Consultation consultation = temp.ajouterConsultation(c, m);
        System.out.println("Résultat :" + consultation);
        System.out.println("Récupération de la consultion via l'id de l'employé :");
        System.out.println("Résultat :" + temp.rechercherConsultationEnCoursEmploye(consultation.getEmploye().getId()));
        System.out.println("Récupération de la consultion inexistante en cours via l'id de l'employé :");
        System.out.println("Résultat :" + temp.rechercherConsultationEnCoursEmploye(e1.getId()));
        System.out.println("Les employés");
        System.out.println("Résultat :" + consultation.getEmploye());
        System.out.println("Résultat :" + e1);
        System.out.println("L'employé est prêt :");
        System.out.println("Résultat :" + temp.etrePret(consultation));
        
        System.out.println("\nFIN DU TEST 6\n");
        
        return consultation;
    }
    
    public static void test7() {
        System.out.println("\nDEBUT DU TEST 7\n");
        Service temp = new Service();
        List<String> listCom = temp.voirCommentaires();
        System.out.println("Commentaires non existants :");
        System.out.print("Résultats :");
        for (int i=0; i< listCom.size(); ++i){
            System.out.println("Résultat :" + listCom.get(i) + "\n");
        }
        
        Consultation c = test6();
        System.out.println("Résultat :" + temp.finConsultation(c) + "\n");
        temp.validerCommentaire(c, "ceci est un commentaire");
        
        listCom = temp.voirCommentaires();
        System.out.println("Commentaires existants :");
        System.out.print("Résultats :");
        for (int i=0; i< listCom.size(); ++i){
            System.out.println("Résultat :" + listCom.get(i) + "\n");
        }
        System.out.println("\nFIN DU TEST 7\n");

    }
    
    public static void test8() {
        System.out.println("\nDEBUT DU TEST 8\n");
        test5();
        
        Service temp = new Service();
        List<Medium> listMedium = temp.voirMedium();
        for (int i=0; i< listMedium.size(); ++i){
            System.out.println("Résultat :" + listMedium.get(i) + "\n");
            
        }
        
        listMedium = temp.top5Medium();
        for (int i=0; i< listMedium.size(); ++i){
            System.out.println("Résultat :" + listMedium.get(i) + "\n");
            
        }
        System.out.println("\nFIN DU TEST 8\n");

    }
    
    public static void test9() {
        System.out.println("\nDEBUT DU TEST 9\n");
        
        Date dateNaissance = null;
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1995");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Client c = new Client("Victor", "Hugo", "Homme", "vhugo@paris.fr", "mdptrucbidule", "00 11 22 33 44", "Paris", dateNaissance);
        Service temp = new Service();
        temp.inscrireClient(c);
        
        
        Medium m = new Astrologue("formationA", 2024, "denominationA", true, "presentationA");
        temp.ajouterMedium(m);
        
        
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1990");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Employe e = new Employe("Victoro", "Hugoro" ,true , "vhugoro@lyon.fr", "mdptrucbidule", "00 11 22 33 44", "Lyon", dateNaissance);
        temp.inscrireEmploye(e);
        e = new Employe("a", "b" ,true , "ab@lyon.fr", "mdptrucbidule", "00 11 22 33 55", "Lyon", dateNaissance);
        temp.inscrireEmploye(e);
        
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        dateNaissance = simpleDateFormat.parse("10/12/2000 10:25:11");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Consultation consultation1 = temp.ajouterConsultation(c, m);
        System.out.println("Résultat :" + consultation1);
        
        Consultation consultation2 = temp.ajouterConsultation(c, m);
        System.out.println("Test de reconsultation :" + consultation2);
        
        System.out.println("Fin de consultation :" + temp.finConsultation(consultation1));
        
        consultation1 = temp.ajouterConsultation(c, m);
        System.out.println("Reprise de consultation :" + consultation1);
        
        
        
        System.out.println("\nFIN DU TEST 9\n");
    }

}



/*
public static Consultation test6() {
        System.out.println("\nDEBUT DU TEST 6\n");
        
        Date dateNaissance = null;
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1995");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Client c = new Client("Victor", "Hugo", "vhugo@paris.fr", "mdptrucbidule", "00 11 22 33 44", "Paris", dateNaissance);
        Service temp = new Service();
        temp.inscrireClient(c);
        
        
        Medium m = new Astrologue("formationA", 2024, "denominationA", true, "presentationA");
        temp.ajouterMedium(m);
        
        
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateNaissance = simpleDateFormat.parse("10/12/1990");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Employe e = new Employe("Victoro", "Hugoro",Boolean.TRUE , "vhugoro@lyon.fr", "mdptrucbidule", "00 11 22 33 44", "Lyon", dateNaissance);
        temp.inscrireEmploye(e);
        
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        dateNaissance = simpleDateFormat.parse("10/12/2000 10:25:11");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Consultation consultation = new Consultation(dateNaissance, false, m, c, e);
        System.out.println("Résultat :" + temp.ajouterConsultation(consultation) + "\n");
        
        System.out.println("\nFIN DU TEST 6\n");
        
        return consultation;
    }
*/