package com.ender.games.epoch.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.ender.games.epoch.GAME_MANAGER
import com.ender.games.epoch.Ships
import com.ender.games.epoch.entities.components.PhysicsComponent
import com.ender.games.epoch.entities.components.PlayerComponent
import com.ender.games.epoch.entities.components.RenderComponent
import com.ender.games.epoch.items.Inventory
import com.ender.games.epoch.ship.Ship
import com.ender.games.epoch.ship.weapons.LightBlaster
import com.ender.games.epoch.util.ASSET_MANAGER
import com.ender.games.epoch.util.Spritesheets


object Player: Entity() {

    val inventory = Inventory

    val ship = Ship(Ships.HEXACRON, this)
    private val world = GAME_MANAGER.game!!.inGameScreen.world
    private val body = world.createBody(BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        position.set(0f, 0f)
        angularDamping = 0.2f
    }).apply {
        createFixture(FixtureDef().apply {
            shape = CircleShape().apply {
                radius = 3f
            }
            density = 0.5f
        })
    }

    init {
        add(RenderComponent().apply {
            alpha = 1f
            region = ASSET_MANAGER.get(Spritesheets.SHIPS).findRegion(ship.ar)
            z = 0
        })

        add(PhysicsComponent().apply {
            body = Player.body
        })

        add(PlayerComponent())

        ship.affixWeapon(LightBlaster(ship), 0)
    }
}