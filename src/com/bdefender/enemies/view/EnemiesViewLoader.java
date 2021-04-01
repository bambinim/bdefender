package com.bdefender.enemies.view;

import com.bdefender.enemies.EnemyBase;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EnemiesViewLoader {

    public static HashMap<EnemyBase, Optional<Image>> GetEnemiesImages(List<EnemyBase> enemies){
        HashMap<EnemyBase, Optional<Image>> enemiesImages = new HashMap<>();
        for (EnemyBase enemy : enemies) {
            Optional<Image> enemyImage;
            try {
                enemyImage =  Optional.of(new Image(new FileInputStream(String.format("res/enemies/%d/enemy.png", enemy.getTypeId())), 100, 100, false, false));
            } catch (Exception e){
                enemyImage = Optional.empty();
            }
            enemiesImages.put(enemy, enemyImage);
        }
        return  enemiesImages;
    }

}
