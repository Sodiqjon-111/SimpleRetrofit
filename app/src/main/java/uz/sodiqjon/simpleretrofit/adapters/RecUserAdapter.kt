package uz.sodiqjon.simpleretrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_item_users.view.*
import uz.sodiqjon.simpleretrofit.R
import uz.sodiqjon.simpleretrofit.data.model.UserModel

class RecUserAdapter : RecyclerView.Adapter<RecUserAdapter.MyHolder>() {

    private val items: MutableList<UserModel> = mutableListOf()

    class MyHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecUserAdapter.MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.rec_item_users,parent,false))
    }

    override fun onBindViewHolder(holder: RecUserAdapter.MyHolder, position: Int) {
        val item = items[position]
        holder.itemView.textView.text = item.title
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list:List<UserModel>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()

    }
}