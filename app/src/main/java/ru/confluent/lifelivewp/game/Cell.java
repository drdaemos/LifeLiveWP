package ru.confluent.lifelivewp.game;

import java.util.Random;

/**
 * Created by Евгений on 30.03.2015.
 */
public class Cell {
    public static Random rand = new Random();
    private boolean dead;
    public Cell() {
        dead = true;
    }

    public Cell(boolean random) {
        dead = random ? rand.nextBoolean() : true;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isAlive() {
        return !dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if(isDead() == other.isDead()){
            return true;
        }
        return false;
    }
}
