package com.epochgames.epoch.entities;

import com.epochgames.epoch.GameManager;

public class Ship {

    public GameManager.Ships shipMake;
    public boolean isPirate;

    public Ship(GameManager.Ships shipMake, boolean isPirate) {
        this.shipMake = shipMake;
        this.isPirate = isPirate;
    }
}
