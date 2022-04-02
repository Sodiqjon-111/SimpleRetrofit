package uz.sodiqjon.simpleretrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rec_item_image.view.*
import kotlinx.android.synthetic.main.rec_item_users.view.*
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.data.model.ImageModel
import uz.sodiqjon.simpleretrofit.data.model.UserModel

class RecImageAdapter : RecyclerView.Adapter<RecImageAdapter.MyHolder>() {

    private val items: MutableList<ImageModel> = mutableListOf()

    class MyHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecImageAdapter.MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.rec_item_image,parent,false))
    }

    override fun onBindViewHolder(holder: RecImageAdapter.MyHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context)
            .load(item.url).into(holder.itemView.imageViewItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list:List<ImageModel>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()

    }
}