package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.LevelLoadInterface;
import fr.enssat.BoulderDash.interfaces.SubscriberInterface;
//le niveau se charge ici
//a partir du fichier
//la vue connais le modele
//le controlleur va modifier le model en fonction de l'utilisateur
//le modele previens la vue qu'il y a eu des modifs
public class LevelModel implements LevelLoadInterface, SubscriberInterface {

    private String name = "";
    private int id = -1;

    public LevelModel() {
    	
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    protected void setId(int id) {
        this.id = id;
    }
}