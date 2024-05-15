/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.maps.model.LatLng;
import java.util.List;
import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JpaUtil;
import dao.MediumDao;
import dao.ProfilAstralDao;
import java.util.ArrayList;
import java.util.Date;
import util.Message;
import modele.Client;
import modele.Consultation;
import modele.Employe;
import modele.Medium;
import modele.ProfilAstral;
import util.AstroNetApi;
import util.GeoNetApi;

/**
 *
 * @author alexandre armbruster, harold martin
 */
public class Service {
    
    /*inscrireClient(Client client) 
	Fonction: inscrit la base de données un utilisateur et créer son profil astral + l'inscrit dans la database + envoie un mail au client sur la réussite ou non de son inscription
        Input: client (Client) 
	Return: Boolean, retourne Vrai si le compte a ete cree retourne Faux si pendant la création il y a eu une erreur 
    */
    public Boolean inscrireClient(Client client) {
        Boolean verif;
        ClientDao clientdao = new ClientDao();
        EmployeDao employeDao = new EmployeDao();
        ProfilAstralDao profilastraldao = new ProfilAstralDao();
        try {
            JpaUtil.creerContextePersistance();
            
            if ((employeDao.findMail(client.getMail()) == null) && (employeDao.findTel(client.getTel()) == null)){
                // Au cas ou un numéro de téléphone ou un eMail est le même qu'un employé
                
                JpaUtil.ouvrirTransaction();

                AstroNetApi astroApi = new AstroNetApi(); // On cherche le signe astral du client
                List<String> profil = astroApi.getProfil(client.getPrenom(), client.getDateNaissance());
                String signeZodiaque = profil.get(0);
                String signeChinois = profil.get(1);
                String couleur = profil.get(2);
                String animal = profil.get(3);

                ProfilAstral astral = new ProfilAstral(signeZodiaque, signeChinois, couleur, animal);
                client.setProfilAstral(astral); // On le set au client avant de l'enregistrer

                profilastraldao.create(astral);

                clientdao.create(client);

                JpaUtil.validerTransaction();
                Message.envoyerMail("contact@predict.if", client.getMail(), "Bienvenue chez PREDICT'IF", "Bonjour "+client.getNom()+", nous vous confirmons votre inscription au service PREDICT'IF. Rendez-vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums");
                verif = true;
            }
            else {
                Message.envoyerMail("contact@predict.if", client.getMail(), "Echec de l'inscription chez PREDICT'IF", "Bonjour "+client.getNom()+", votre inscription au service PREDICT'IF a malencontreusement échoué...\nMerci de recommencer ultérieurement.");
                verif = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Message.envoyerMail("contact@predict.if", client.getMail(), "Echec de l'inscription chez PREDICT'IF", "Bonjour "+client.getNom()+", votre inscription au service PREDICT'IF a malencontreusement échoué...\nMerci de recommencer ultérieurement.");
            JpaUtil.annulerTransaction();
            verif = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return verif;
    }
    

    /*authentifierClient(String mail, String motDePasse)
	Fonction: cherche dans la base de données un utilisateur avec le mail et le mot de passe pris en input 
        Input: email (String), mot de passe (String) 
	Return: Client, retourne le Client si il a pu être authentifié, sinon il retourne null

    */
    public Client authentifierClient(String mail, String motDePasse) {
        Client client = null;
        ClientDao clientdao = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            client = clientdao.findByMail(mail); // On cherche l'objet client qui a la même adresse mail
            if (!motDePasse.equals(client.getMotDePasse())) { // Si ils ont le même mot de passe
                client = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            client = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }
    
    
    /*inscrireEmploye(Employe employe)
	Fonction: inscrit la base de données un employé
	Input: employe (Employe)
	Return: Boolean, retourne Vrai si le compte a ete cree retourne Faux si pendant la création il y a eu une erreur
    Sert uniquement pour créer des employés pour initialiser la base*/
    public Boolean inscrireEmploye(Employe employe) {
        Boolean verif;
        EmployeDao employedao = new EmployeDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            employedao.create(employe); // Met dans la base de données directement l'employé
            
            JpaUtil.validerTransaction();
            verif = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            verif = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return verif;
    }
    
    /*authentifierEmploye(String mail, String motDePasse) 
	Fonction: cherche dans la base de données un employé avec le mail et le mot de passe pris en input 
	Input: email (String), mot de passe (String) 
	Return: Employe, retourne l’Employe si il a pu être authentifié, sinon il retourne null
    */
    public Employe authentifierEmploye(String mail, String motDePasse) {
        Employe employe = null;
        EmployeDao employedao = new EmployeDao();
        try {
            JpaUtil.creerContextePersistance();
            employe = employedao.findMail(mail); // On cherche l'objet employe qui a la même adresse mail
            if (!motDePasse.equals(employe.getMotDePasse())) { // Si ils ont le même mot de passe
                employe = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            employe = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return employe;
    }
    
    /*rechercherClientParId(Long id)
	Fonction: cherche dans la base de données un utilisateur avec l'id pris en input
	Input: id (Long)
	Return: Client, retourne le Client si il a pu être trouvé, sinon il retourne null
    */ 
    public Client rechercherClientParId(Long id) {
        ClientDao clientDAO = new ClientDao();
        Client c;
        try {
            JpaUtil.creerContextePersistance();
            c = clientDAO.findById(id); // On cherche le client dans la BD qui a le même id
        } catch (Exception ex) {
            ex.printStackTrace();
            c = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
    
    /*rechercherEmployeParId(Long id)
	Fonction: cherche dans la base de données un employé avec l'id pris en input
	Input: id (Long)
	Return: Employe, retourne l’Employe si il a pu être trouvé, sinon il retourne null
    */
    public Employe rechercherEmployeParId(Long id) {
        EmployeDao employeDAO = new EmployeDao();
        Employe e;
        try {
            JpaUtil.creerContextePersistance();
            e = employeDAO.findById(id); // On cherche l'employe dans la BD qui a le même id
        } catch (Exception ex) {
            ex.printStackTrace();
            e = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return e;
    }
    
    /*rechercherConsultationParId(Long id)
	Fonction: cherche dans la base de données une consultation avec l'id pris en input
	Input: id (Long)
	Return: Consultation, retourne la Consultation si elle a pu être trouvée, sinon elle retourne null
    */
    public Consultation rechercherConsultationParId(Long id) {
        ConsultationDao consultationDao = new ConsultationDao();
        Consultation c;
        try {
            JpaUtil.creerContextePersistance();
            c = consultationDao.findById(id); // On cherche la consultation dans la BD qui a le même id
        } catch (Exception ex) {
            ex.printStackTrace();
            c = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
   
    /*ajouterMedium(Medium medium)
	Fonction: ajoute un medium dans la base de données
	Input: medium (Medium)
	Return: Boolean, retourne Vrai si le medium a été ajouté, sinon il retourne Faux
    Sert uniquement pour créer des employés pour initialiser la base*/
    public Boolean ajouterMedium(Medium medium) {
        Boolean verif;
        MediumDao mediumdao = new MediumDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            mediumdao.create(medium); // Met dans la BD directement le medium
            
            JpaUtil.validerTransaction();
            verif = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            verif = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return verif;
    }
    
    
    /*rechercherMediumParNom(String name)
	Fonction: cherche dans la base de données un medium avec le nom pris en input
	Input: name (String)
	Return: Medium, retourne le Medium si il a pu être trouvé, sinon il retourne null
    */
    public Medium rechercherMediumParNom(String name) {
        MediumDao mediumDAO = new MediumDao();
        Medium medium;
        try {
            JpaUtil.creerContextePersistance();
            medium = mediumDAO.findByName(name); // On cherche le medium dans la BD qui a le même nom
        } catch (Exception ex) {
            ex.printStackTrace();
            medium = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return medium;
    }
    
   
    /*ajouterConsultation (Client client, Medium medium) 
    Cette fonction est appelée quand l'utilisateur sélectionne un médium
	Fonction: Cherche un employé qui est disponible avec le même genre que le médium choisi par l'utilisateur et qui a le nombre le plus faible de consultations
	Input: client (Client), medium (Medium)
	Return: Consultation - Retourne la Consultation si un employé a pu être trouvé pour assurer la consultation sinon retourne null 
    Si succès -> envoie notification téléphone à l’employé qui assurera la consultation 
    */ 
    public Consultation ajouterConsultation(Client client, Medium medium) {
        JpaUtil.creerContextePersistance();
        Consultation consultation = null;
        ClientDao clientdao = new ClientDao();
        if (!clientdao.findById(client.getId()).getEnConsultation()){ // Rafraichie le client et regerde si il est en consultation
            //Si il n'est pas en consultation
            
            ConsultationDao consultationdao = new ConsultationDao();
            MediumDao mediumdao = new MediumDao();
            EmployeDao employedao = new EmployeDao();
            Employe employe = employedao.findDisponibleByGenre(medium.getGenre());
            
            if (employe != null){ // Si il y a un employe libre pour cette consultation
                try {
                    JpaUtil.ouvrirTransaction();

                    // potentiellement à changer (format)
                    Date date = new Date();

                    consultation = new Consultation(date ,medium ,client ,employe);

                    consultationdao.create(consultation);

                    medium.addConsultation(consultation); // On ajoute à l'historique de consultation du médium la consultation
                    mediumdao.update(medium);

                    client.addConsultation(consultation); // On ajoute à l'historique de consultation du client la consultation
                    client.setEnConsultation(true); // Le client est maintenant en consultation
                    clientdao.update(client);

                    employe.addConsultation(consultation); // On ajoute à l'historique de consultation de l'employe la consultation
                    employe.setDisponibilite(false); // L'employe est maintenant non disponible
                    employedao.update(employe);
                    
                    String sexe=client.getGenre(); // Pour déterminer le genre du client
                    if (sexe.equals("Homme")){
                        sexe = "M.";
                    }
                    else if (sexe.equals("Femme"))
                        sexe = "Mme.";
                    else { // Dans le cas où il peut être indéterminé
                        sexe = "";
                    }
                    
                    Message.envoyerNotification(employe.getTel(),"Bonjour "+employe.getPrenom()+". Consultation requise pour "+sexe+" "+ client.getPrenom()+" "+client.getNom()+". Medium à incarner: "+medium.getDenomination());
                    JpaUtil.validerTransaction();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JpaUtil.annulerTransaction();
                    consultation = null;
                }
            }
        }
        JpaUtil.fermerContextePersistance();
        return consultation;
    }
    
    
    
    /*rechercherConsultationEnCoursClient(long id_client)
	Fonction: cherche dans la base de données une consultation en cours pour un client donné
	Input: id_client (long)
	Return: Consultation, retourne la Consultation si elle a pu être trouvée, sinon elle retourne null
    */
    public Consultation rechercherConsultationEnCoursClient(long id_client) {
        ConsultationDao consultationdao = new ConsultationDao();
        ClientDao clientdao = new ClientDao();
        Consultation consultation;
        try{
            JpaUtil.creerContextePersistance();
            
            consultation = consultationdao.findByClient(clientdao.findById(id_client)); // Recherche une potentiel consultation en cours de l'employe par son id
        }
        catch (Exception ex) {
            ex.printStackTrace();
            consultation = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }
    
    
    /*rechercherConsultationEnCoursEmploye(long id_employe)
	Fonction: cherche dans la base de données une consultation en cours pour l'id d'un employé donné
	Input: id_employe (long)
	Return: Consultation, retourne la Consultation si elle a pu être trouvée, sinon elle retourne null
    */
    public Consultation rechercherConsultationEnCoursEmploye(long id_employe) {
        ConsultationDao consultationdao = new ConsultationDao();
        EmployeDao employedao = new EmployeDao();
        Consultation consultation;
        try{
            JpaUtil.creerContextePersistance();
            
            consultation = consultationdao.findByEmp(employedao.findById(id_employe)); // Recherche une potentiel consultation en cours de l'employe par son id
        }
        catch (Exception ex) {
            ex.printStackTrace();
            consultation = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }
    
    
    /*etrePret(Consultation consultation)
    Cette fonction est appelée quand l'employé appuie sur le bouton prêt de sa consultation
	Fonction: Envoie une notification téléphone au client pour lui dire que le médium est prêt à le recevoir 
	Input: consultation (Consultation) 
	Return: Boolean - Retourne Vrai si la notif est bien envoyé sinon retourne Faux
    */
    public Boolean etrePret(Consultation consultation){
        Boolean verif = false;
        ConsultationDao consultationdao = new ConsultationDao();
        try{
            JpaUtil.creerContextePersistance();
            consultation = consultationdao.findById(consultation.getId()); // Actualiser la consulation
            if (!consultation.getValidation()){ // Vérification que la consultation est en cours
                
                Client client = consultation.getClient();
                Medium medium = consultation.getMedium();
                Employe employe=consultation.getEmploye();

                Message.envoyerNotification(client.getTel(), "Bonjour "+client.getNom()+". J'ai bien reçu votre demande de consultation du "+consultation.getDate()+". Vous pouvez dès à présent me contacter au "+employe.getTel()+". A tout de suite ! Médiumiquement vôtre, "+medium.getDenomination());
                verif = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            verif = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return verif;
    }
    
    
    /*finConsultation(Consultation consultation)
    Cette fonction est appelée quand l'employé après avoir raccroché et valide la fin de consultation
	Fonction: met à jour dans la base de données la validation de la consultation + rend l'employé et le client disponible pour une autre consultation + met à jour le nombre de consultation de l’employé et du medium
	Input: consultation (Consultation)
	Return: Boolean - Retourne Vrai si les informations ont bien été mis à jour sinon retourne Faux
    */
    public Boolean finConsultation(Consultation consultation) {
        Boolean verif = false;
        ConsultationDao consultationdao = new ConsultationDao();
        EmployeDao employedao = new EmployeDao();
        MediumDao mediumdao = new MediumDao();
        ClientDao clientdao = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            consultation = consultationdao.findById(consultation.getId()); // Rafraichir la consultation
            if (!consultation.getValidation()){ // Vérification que la consultation est en cours
                JpaUtil.ouvrirTransaction();
                

                consultation.setValidation(true); // Valider consultation
                consultationdao.update(consultation);

                Employe employe = consultation.getEmploye(); // Mettre disponible l'employé et faire +1 au nombre de consultation de l'employé
                employe.setDisponibilite(true);
                employe.setNombreConsultations(employe.getNombreConsultations() + 1);
                employedao.update(employe);

                Medium medium = consultation.getMedium(); // Faire +1 au nombre de consultation du median
                medium.setNombreConsultations(medium.getNombreConsultations() + 1);
                mediumdao.update(medium);

                Client client = consultation.getClient(); // Mettre son état d'en consultation à false
                client.setEnConsultation(false);
                clientdao.update(client);

                JpaUtil.validerTransaction();
                verif = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            verif = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return verif;
    }
    
    
    /*voirMedium()
    Cette fonction est appelée pour afficher la liste des médiums sur la page liste des mediums et à l'initialisation de la page d'accueil des clients pour le carroussel
        Fonction: Recherche tous les mediums du site
        Input: Pas d'entree
        Return: list<Medium>
    */
    public List<Medium> voirMedium(){
        List<Medium> listMedium;
        MediumDao mediumdao = new MediumDao();
        try {
            JpaUtil.creerContextePersistance();
            
            listMedium = mediumdao.findAll(); // Récupère tous les médiums de la BD
            
        } catch (Exception ex) {
            ex.printStackTrace();
            listMedium = new ArrayList<>();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listMedium;
    }
    
    
    /*Top5Medium()
    Cette fonction est appelée à l’initialisation de la page d’accueil de l’employé
	Fonction: Recherche le top 5 des médiums ayant le plus de consultations ( En cas d’égalité, entre plusieurs employés qui font partie du Top 5, elle donne les premiers dans l’ordre alphabétique)
	Input: Pas d’entrées
	Return: List<Medium>
    */
    public List<Medium> top5Medium(){
        List<Medium> listMedium;
        MediumDao mediumdao = new MediumDao();
        try {
            JpaUtil.creerContextePersistance();
            
            listMedium = mediumdao.findtop5(); // Récupère les 5 premiers médiums de la BD trié par le nombre de consultation effectué
            
        } catch (Exception ex) {
            ex.printStackTrace();
            listMedium = new ArrayList<>();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listMedium;
    }
    
    /*obtenirPredictions(Client client, int noteAmour, int noteSante, int noteTravail)
    Cette fonction permet à l’employé d’obtenir une prédiction grâce à AstroNetApi lorsqu’il est en cours de consultation.
	Fonction: Retourne les prédictions sous forme d’une liste de chaînes de caractères 
	Input: client (Client), niveauAmour (int), niveauSante (int), niveauTravail (int)
	Return: List<String>
    */
    public List<String> obtenirPredictions(Client client, int noteAmour, int noteSante, int noteTravail) { // je ne sais c'est la quel la meilleur
        List<String> prediction;
        AstroNetApi astroApi = new AstroNetApi();
        try {
            
            ProfilAstral profilAstral = client.getProfilAstral(); // Récupère les informations pour faire une prediction
            String couleur = profilAstral.getCouleur();
            String animal = profilAstral.getAnimal();
            prediction = astroApi.getPredictions(couleur, animal, noteAmour, noteSante, noteTravail);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            prediction = new ArrayList<>();
        }
        return prediction;
    }
    
    
    /*voirCommentaires()
    Cette fonction permet à l’employé de voir tous les commentaires sur toutes les consultations.
	Fonction: Retourne une liste de tous les commentaires
	Input: Pas d’entrées
	Return: List<String> s’il y a des commentaires, null sinon
    */
     public List<String> voirCommentaires(){
        List<String> listCommentaires;
        ConsultationDao consultationdao=new ConsultationDao();
        try {
            JpaUtil.creerContextePersistance();
            
            listCommentaires = consultationdao.findAllCommentaires(); // Récupère tous les commentaires de la BD
            
        } catch (Exception ex) {
            ex.printStackTrace();
            listCommentaires = new ArrayList<>();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listCommentaires;
    }
    
     
    /*voirClientsMap() 
	Fonction: Fetch dans la database tous les clients 
	Input: None 
	Return: List<LatLng>, 
	Return : la liste de toutes les latitudes et longitudes des clients
    */ 
    public List<LatLng> voirClientsMap(){
        List<LatLng> listLatLngs = new ArrayList<>();
        List<String> listAdresse;
        ClientDao clientdao=new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            
            listAdresse = clientdao.findAllAddress(); // Récupère toutes les adresses des clients de la BD
            
            for (int i=0; i< listAdresse.size(); ++i){
                listLatLngs.add(GeoNetApi.getLatLng(listAdresse.get(i))); // Transforme l'adresse en coordonnées latitude/longitude
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            listLatLngs = new ArrayList<>();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listLatLngs;
    }
    
    
    /*validerCommentaire(Consultation consultation, String commentaire)
    Cette fonction est appelée lorsque l’employé clique sur le bouton Valider le commentaire
	Fonction: Met à jour l’attribut commentaire de la consultation
	Input: consultation (Consultation)
	Return: Boolean - Retourne Vrai si la modification s’est réalisé dans le base de données sinon retourne Faux
    */
    public Boolean validerCommentaire(Consultation consultation, String commentaire){
        Boolean verif;
        ConsultationDao consultationDao = new ConsultationDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            consultation = consultationDao.findById(consultation.getId()); // Rafraichir la consultation
            consultation.setCommentaire(commentaire); // Set le commentaire
            consultationDao.update(consultation);
            
            verif=true;
            JpaUtil.validerTransaction();
        }catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            verif=false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return verif;
    }
    
    
    /*voirClientsParEmp(Employe emp)
    Cette fonction peut etre appele pour les statistiques 
        Fonction: recherche tous les clients qui ont une consultation avec l'employe en parametre
        Input: emp (Employe)
        Return: List<Client>
    */
    public List<Client> voirClientsParEmp(Employe emp){
        List<Client> listClients = new ArrayList<>();
        ClientDao clientdao=new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            
            listClients = clientdao.findClientsbyIdEmp(emp); // Récupère les différents clients qui ont eu l'emplyé en consultation
            
        } catch (Exception ex) {
            ex.printStackTrace();
            listClients = new ArrayList<>();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listClients;
    }
    
}
