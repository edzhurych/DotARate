package com.ez.dotarate.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `heroes` (`hero_id` INTEGER PRIMARY KEY NOT NULL, `games` INTEGER NOT NULL, `last_played` INTEGER NOT NULL, `win` INTEGER NOT NULL)")
    }
}

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `user` (`id` INTEGER PRIMARY KEY, `name` TEXT NOT NULL, `avatarUrl` TEXT NOT NULL, `wins` INTEGER NOT NULL, `losses` INTEGER NOT NULL, `rankId` INTEGER NOT NULL)")
    }
}

val MIGRATION_3_4: Migration = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the new table
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `heroes_new` (`hero_id` INTEGER NOT NULL, `games` INTEGER NOT NULL, `last_played` INTEGER NOT NULL, `win` INTEGER NOT NULL, PRIMARY KEY (hero_id, games))")
        // Copy the data
        database.execSQL(
            "INSERT INTO `heroes_new` (hero_id, games, last_played, win) SELECT hero_id, games, last_played, win FROM heroes")
        // Remove the old table
        database.execSQL("DROP TABLE heroes")
        // Change the table name to the correct one
        database.execSQL("ALTER TABLE heroes_new RENAME TO heroes")
    }
}