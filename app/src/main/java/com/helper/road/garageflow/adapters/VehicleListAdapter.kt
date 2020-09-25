package com.helper.road.garageflow.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.helper.road.R
import com.helper.road.garageflow.localdb.entity.Vehicle
import com.helper.road.garageflow.localdb.entity.VehicleType

class VehicleListAdapter(private val vehicles: List<Vehicle>, context: Context, private val layoutRes:Int) : ArrayAdapter<Vehicle>(context,layoutRes,vehicles) {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null){
            view = inflater.inflate(layoutRes,null)
        }

        val vehicle = vehicles[position]

        val vehicleNameTextView = view?.findViewById<TextView>(R.id.vehicleNameTextView)
        val vehicleRunTextView = view?.findViewById<TextView>(R.id.vehicleRunTextView)

        vehicleNameTextView?.text = vehicle.Name
        vehicleRunTextView?.text = vehicle.CurrentRun.toString()

        val typeImageView = view?.findViewById<ImageView>(R.id.typeImageView)

        when(vehicle.type){
            VehicleType.MOTORCYCLE->typeImageView?.setImageResource(R.drawable.ic_motorcycle)
            VehicleType.ATV->typeImageView?.setImageResource(R.drawable.ic_atv)
            VehicleType.CAR->typeImageView?.setImageResource(R.drawable.ic_car)
        }

        return view!!
    }


    override fun getItem(position: Int): Vehicle? {
        return vehicles[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return vehicles.size
    }
}