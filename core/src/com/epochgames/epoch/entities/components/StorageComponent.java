package com.epochgames.epoch.entities.components;

import com.badlogic.ashley.core.Component;
import org.hexworks.mixite.core.api.CubeCoordinate;

public class StorageComponent implements Component {
    public CubeCoordinate cubeCoordinate = CubeCoordinate.fromCoordinates(0, 0);
}
