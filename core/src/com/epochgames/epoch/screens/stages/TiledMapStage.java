package com.epochgames.epoch.screens.stages;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.epochgames.epoch.GameManager;
import com.epochgames.epoch.entities.TileMapActor;
import com.epochgames.epoch.screens.inputListeners.TiledMapClickListener;
import com.epochgames.epoch.util.hexlib.HexSatelliteData;
import com.epochgames.epoch.util.hexlib.Point;
import org.codetome.hexameter.core.api.Hexagon;
import org.codetome.hexameter.core.api.HexagonalGrid;

public class TiledMapStage extends Stage {
    public TiledMap tiledMap;
    public HexagonalGrid<HexSatelliteData> hexGrid;

    public TiledMapStage(TiledMap tiledMap, HexagonalGrid hexGrid) {
        this.tiledMap = tiledMap;
        this.hexGrid = hexGrid;

        for(MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer(tiledLayer);
        }
    }

    private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
        /*for (int x = 0; x < tiledLayer.getWidth(); x++) {
            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                Point center = hexGrid.getHexagon(new OffsetCoord(x, y)).getHexCenter();
                TileMapActor actor = new TileMapActor(tiledMap, tiledLayer, cell, center);
                addActor(actor);
                EventListener eventListener = new TiledMapClickListener(actor, GameManager.getInstance().game);
                actor.addListener(eventListener);
            }
        }*/
        int i = 0;
        for(Hexagon<HexSatelliteData> hexagon : hexGrid.getHexagons()) {
            TiledMapTileLayer.Cell cell = tiledLayer.getCell(hexagon.getGridX(), hexagon.getGridY());
            Point centerCoords = new Point((int)hexagon.getCenterX(), (int)hexagon.getCenterY());
            TileMapActor actor = new TileMapActor(tiledMap, tiledLayer, cell, centerCoords);
            addActor(actor);
            EventListener eventListener = new TiledMapClickListener(actor, GameManager.getInstance().game);
            actor.addListener(eventListener);
        }
        System.out.println(i + " hexagons in hexgrid");
    }
}
