package uz.sodiqjon.simpleretrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.restaurant_item.view.*
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.data.model.RestaurantsData

class RestaurantsAdapter :
    ListAdapter<RestaurantsData, RestaurantsAdapter.MyHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RestaurantsData>() {
            override fun areItemsTheSame(
                oldItem: RestaurantsData,
                newItem: RestaurantsData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RestaurantsData,
                newItem: RestaurantsData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsAdapter.MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.textViewId.text = item.id.toString()
        holder.itemView.textViewName.text = item.name
        holder.itemView.textViewType.text = item.type
        holder.itemView.textViewPhoneNumber.text = item.phone_number
    }
}