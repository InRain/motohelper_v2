import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.helper.road.R
import com.helper.road.garageflow.localdb.entity.VehicleType

class VehicleTypeAdapter(ctx: Context, layoutRes: Int) :
    ArrayAdapter<VehicleType>(ctx, layoutRes, VehicleType.values()) {
    private val inflater: LayoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row = inflater.inflate(R.layout.vehicle_type_row, parent, false)
        val label = row.findViewById<TextView>(R.id.typeName)
        val icon = row.findViewById<ImageView>(R.id.typeIcon)

        when (VehicleType.values()[position]) {
            VehicleType.ATV -> {
                label.setText(R.string.atv)
                icon.setImageResource(R.drawable.ic_atv)
            }
            VehicleType.CAR -> {
                label.setText(R.string.car)
                icon.setImageResource(R.drawable.ic_car)
            }
            VehicleType.MOTORCYCLE -> {
                label.setText(R.string.motorcycle)
                icon.setImageResource(R.drawable.ic_motorcycle)
            }
        }
        return row
    }
}