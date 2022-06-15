package uz.silence.smspoems.PoemAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.silence.smspoems.CLASS.Poem
import uz.silence.smspoems.Cache.MySharedPreference
import uz.silence.smspoems.R

class poemAdapter(
    var context: Context,
    var poemList: ArrayList<Poem>,
    var onItemCLickListener: OnItemCLickListener
) : RecyclerView.Adapter<poemAdapter.Vh>() {

    inner class Vh(var itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(poem: Poem, position: Int) {

            MySharedPreference.init(context)

            itemView.setOnClickListener {

                onItemCLickListener.onItemClick(poem)


            }

            itemView.findViewById<ImageView>(R.id.heartTitleId).setOnClickListener {

                onItemCLickListener.onItemLikeClick(poem, position)
                itemView.findViewById<ImageView>(R.id.heartTitleId)
                    .setImageResource(R.drawable.ic_like)
            }

            itemView.findViewById<TextView>(R.id.titleListId).text = poem.littleTitle
            itemView.findViewById<TextView>(R.id.textTitleId).text = poem.textPoem


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(poemList[position], position)
    }

    override fun getItemCount(): Int {
        return poemList.size
    }

    interface OnItemCLickListener {

        fun onItemClick(poem: Poem)
        fun onItemLikeClick(poem: Poem, position: Int)

    }

}