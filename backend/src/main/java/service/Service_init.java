/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import dao.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.*;

public class Service_init {
    
    public static Boolean chargerEmployes() {
        boolean result = false;

        EmployeDao employeDAO = new EmployeDao();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = null;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            File file = new File("src\\main\\java\\service\\Employee.csv");
            
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");
                dateNaissance = simpleDateFormat.parse(elements[7]);
                Employe employe = new Employe(elements[0], elements[1], elements[2].equals("true"), elements[3], elements[4], elements[5], elements[6], dateNaissance);
                
                employeDAO.create(employe);
            }
            reader.close();
            
            JpaUtil.validerTransaction();
            result = true;

        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
    
    public static Boolean chargerMediums() {
        boolean result = false;
        MediumDao mediumDAO = new MediumDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            //String formation, Integer promotion, String denomination, Boolean genre, String presentation)
            Astrologue astrologue = new Astrologue("Institut supérieur d'astrologie",2003,"Mr M",false,"Je suis Mr M.");
            Astrologue astrologue2 = new Astrologue("Institut supérieur d'astrologie",2004,"Tran",false,"Je suis Tran");
            
            Cartomancien cartomancien = new Cartomancien("Celia",true,"Je suis Celia");  
            Cartomancien cartomancien2 = new Cartomancien("Fana",true,"Je suis Fana");
            
            List<String> myList = new ArrayList();
            myList.add("Boule de cristal");
            List<String> myList2 = new ArrayList();
            myList2.add("Verre de cristal");
        
            Spirit spirite = new Spirit(myList,"Irma",true,"Je suis Irma");
            Spirit spirite2 = new Spirit(myList2,"Serena",true,"Je suis Serena");

            
            mediumDAO.create(astrologue);
            mediumDAO.create(astrologue2);
            mediumDAO.create(spirite);
            mediumDAO.create(spirite2);
            mediumDAO.create(cartomancien);
            mediumDAO.create(cartomancien2);
            JpaUtil.validerTransaction();
            result = true;

        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            System.out.println(e);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
}