package ru.confluent.lifelivewp.game;

import android.graphics.Color;

import rajawali.Object3D;
import rajawali.materials.Material;
import rajawali.primitives.Plane;
import rajawali.scene.RajawaliScene;

/**
 * Created by Евгений on 30.03.2015.
 */
public class Scene {
    private Field gameField;
    private Object3D[][] cellModels;
    private RajawaliScene scene;
    final int DEAD_COLOR = Color.argb(255, 22, 28, 71);
    final int ALIVE_COLOR = Color.argb(255, 33, 144, 158);
    public Scene(RajawaliScene scene, int width, int height){
        this.scene = scene;
        gameField = new Field(width,height,true);
        cellModels = new Object3D[width][height];
        init();
    }

    public void init(){
        Plane sample = new Plane(1f,1f,1,1);
        Material material = new Material();
        sample.setMaterial(material);
        sample.setRenderChildrenAsBatch(true);
        scene.addChild(sample);

        Cell[][] cells = gameField.getCells();
        for(int row = 0; row < gameField.getRows(); row++){
            for(int col = 0; col < gameField.getColumns(); col++){
                Object3D model = initModel(row, col, cells[row][col].isAlive(), sample);
                cellModels[row][col] = model;
            }
        }
    }
    public void update(){
        gameField.updateCells();
        Cell[][] cells = gameField.getCells();
        for(int row = 0; row < gameField.getRows(); row++){
            for(int col = 0; col < gameField.getColumns(); col++){
                cellModels[row][col].setColor(cells[row][col].isAlive() ? ALIVE_COLOR : DEAD_COLOR);
            }
        }
    }

    private Object3D initModel(int x, int y, boolean isAlive, Plane sample){
        Object3D plane = sample.clone();
        plane.setColor(isAlive ? ALIVE_COLOR : DEAD_COLOR);
        plane.setX(x);
        plane.setY(y);
        scene.addChild(plane);
        return plane;
    }


}
