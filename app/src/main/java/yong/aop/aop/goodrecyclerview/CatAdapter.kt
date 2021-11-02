package yong.aop.aop.goodrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CatAdapter (val context: Context, var catModels: MutableList<CatModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private var actionLock = false

    class SubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name_tv: TextView
        init {
            this.name_tv = itemView.findViewById(R.id.name_tv) as TextView
        }
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name_tv: TextView
        internal var name_tv2: TextView
        init {
            this.name_tv = itemView.findViewById(R.id.name_tv) as TextView
            this.name_tv2 = itemView.findViewById(R.id.name_tv2) as TextView
        }
    }

    override fun getItemViewType(position: Int): Int {
        val type = catModels[position].type
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            CatModel.Maincat -> SubViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_row, parent, false))
            CatModel.Mainsub -> MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row, parent, false))
            else -> SubViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_row, parent, false))
        }
        return viewHolder
    }


    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val row = catModels[p1]

        when(row.type){
            CatModel.Maincat -> {
                (p0 as SubViewHolder).name_tv.setText(row.catbig.name)
                    if(p0.name_tv.visibility == View.GONE){
                        p0.name_tv.visibility = View.VISIBLE
                    }
                    p0.name_tv.setOnClickListener {

                            if (row.isExpanded) {
                                row.isExpanded = false
                                collapse(p1)
                            } else {
                                row.isExpanded = true
                                expand(p1)
                            }
                    }
            }
            CatModel.Mainsub -> {
                (p0 as MainViewHolder).name_tv.setText(row.catsmall.name)
                (p0).name_tv2.setText(row.catsmall.abc)

            }
        }
        p0.itemView.setOnClickListener {
            itemClickListener.onClick(it, p1)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun expand(position: Int) {
        var nextPosition = position
        val row = catModels[position]
        when (row.type) {
            CatModel.Maincat -> {
                for (state in row.catbig.catsmallList!!) {
                    catModels.add(++nextPosition, CatModel(CatModel.Mainsub, state))
                }
                notifyDataSetChanged()
            }
            CatModel.Mainsub -> {
                notifyDataSetChanged()
            }
        }
    }

    fun collapse(position: Int) {
        val row = catModels[position]
        val nextPosition = position + 1
        when (row.type) {
            CatModel.Maincat -> {
                outerloop@ while (true) {
                    if (nextPosition == catModels.size || catModels.get(nextPosition).type === CatModel.Maincat) {
                        break@outerloop
                    }
                    catModels.removeAt(nextPosition)
                }
                notifyDataSetChanged()
            }
            CatModel.Mainsub -> {
                outerloop@ while (true) {
                    if (nextPosition == catModels.size || catModels.get(nextPosition).type === CatModel.Maincat || catModels.get(nextPosition).type === CatModel.Mainsub
                    ) {
                        break@outerloop
                    }
                    catModels.removeAt(nextPosition)
                }
                notifyDataSetChanged()
            }
        }
    }
    override fun getItemCount() = catModels.size

}