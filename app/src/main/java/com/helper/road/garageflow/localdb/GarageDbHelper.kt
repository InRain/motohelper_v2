package com.helper.road.garageflow.localdb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.helper.road.garageflow.localdb.entity.VehicleType


class GarageDbHelper(private val ctx: Context) : SQLiteOpenHelper(ctx, DB_MYGARAGE, null, 1),
    BaseColumns {

    companion object {
        const val DB_MYGARAGE = "mygarage.db"

        const val VEHICLES_TABLE_NAME = "vehicle"

        const val VEHICLE_NAME = "name"
        const val VEHICLE_INITIAL_RUN = "initial_run"
        const val VEHICLE_CURRENT_RUN = "current_run"
        const val VEHICLE_PHOTO_PATH = "photo_path"
        const val VEHICLE_TYPE = "vehicle_type"

        const val SERVICE_OPERATION_TABLE_NAME = "service_operation"

        const val SO_VEHICLE_ID = "vehicle_id"
        const val SO_DESCRIPTION = "description"
        const val SO_RUN = "run"
        const val SO_COST = "cost"
        const val SO_DATE = "date"

        const val VEHICLE_TYPE_TABLE_NAME = "vehicle_type"

        const val VT_TYPE_NAME = "type_name"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $VEHICLES_TABLE_NAME (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$VEHICLE_NAME TEXT NOT NULL," +
                    "$VEHICLE_INITIAL_RUN INTEGER NOT NULL," +
                    "$VEHICLE_CURRENT_RUN INTEGER NOT NULL," +
                    "$VEHICLE_PHOTO_PATH TEXT," +
                    "$VEHICLE_TYPE INT NOT NULL," +
                    "FOREIGN KEY ($VEHICLE_TYPE) REFERENCES $VEHICLE_TYPE_TABLE_NAME(id))"
        )

        db?.execSQL(
            "CREATE TABLE $SERVICE_OPERATION_TABLE_NAME (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$SO_VEHICLE_ID INTEGER NOT NULL," +
                    "$SO_DESCRIPTION TEXT NOT NULL," +
                    "$SO_RUN INTEGER NOT NULL," +
                    "$SO_DATE DATE NOT NULL,"+
                    "$SO_COST REAL NOT NULL," +
                    "FOREIGN KEY ($SO_VEHICLE_ID) REFERENCES $VEHICLES_TABLE_NAME(id))"
        )

        db?.execSQL(
            "CREATE TABLE $VEHICLE_TYPE_TABLE_NAME (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$VT_TYPE_NAME TEXT NOT NULL)"
        )

        for(type in VehicleType.values()){
            db?.execSQL(
                "INSERT INTO $VEHICLE_TYPE_TABLE_NAME VALUES ('${type.ordinal}','${type.name}')"
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}