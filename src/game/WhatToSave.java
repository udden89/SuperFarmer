package game;

import animals.Animal;
import player.Player;
import store.Store;

import java.io.Serializable;
import java.util.ArrayList;

public class WhatToSave implements Serializable {

    // All the players of the game
    ArrayList<Player> players;

    //Start variables
    int gameRounds;
    int howManyPlayers;

    WhatToSave(){

        this.players = Game.players;
        this.gameRounds = Game.gameRounds;
        this.howManyPlayers = Game.howManyPlayers;

        System.out.println("Lyckades! constructor in whattosave");

    }

    public void loadGame(){

        Store.addAllItemsToStore();
        Animal.addAllAnimalTypes();

        Game.players = this.players;
        Game.gameRounds = this.gameRounds;
        Game.howManyPlayers = this.howManyPlayers;
        System.out.println("Lyckades komma in i loadgame i whattosave");
    }

}
