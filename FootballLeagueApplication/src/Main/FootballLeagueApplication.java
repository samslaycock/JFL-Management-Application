/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Connection.DBConnection;
import GUI.MenuForm;

/**
 *
 * @author blao
 */
public class FootballLeagueApplication {

    /*
     * Launches the menu for the JFL management progrram
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DBConnection db = new DBConnection();
        db.connectDatabase("jflDB");
        
        MenuForm menu = new MenuForm();
        menu.setVisible(true);
    }
    
}
