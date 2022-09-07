package com.favedish.ui.restaurantdetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.favedish.ui.R
import com.favedish.ui.restaurantdetails.adapter.DishesAdapter.DishViewHolder
import com.favedish.ui.restaurantdetails.adapter.DishesAdapter.OnClickListener.DoNothing
import com.favedish.ui.restaurantdetails.model.DishUiModel

class DishesAdapter : Adapter<DishViewHolder>() {
    var onDishClickListener: OnClickListener = DoNothing

    private val delegateOnClickListener = DelegateOnClickListener()

    private val dishes: MutableList<DishUiModel> = mutableListOf()

    fun setDishes(dishes: List<DishUiModel>) {
        if (dishes != this.dishes) {
            if (this.dishes.isNotEmpty()) {
                val lastItemCount = this.dishes.size
                this.dishes.clear()
                notifyItemRangeRemoved(0, lastItemCount)
            }
            this.dishes.addAll(dishes)
            notifyItemRangeInserted(0, dishes.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
            .let { view -> DishViewHolder(delegateOnClickListener, view) }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(dishes[position])
    }

    override fun getItemCount() = dishes.size

    class DishViewHolder(
        private val onClickListener: OnClickListener,
        itemView: View
    ) : ViewHolder(itemView) {
        private val nameField: TextView by lazy { itemView.findViewById(R.id.dish_dish_title) }

        fun bind(dish: DishUiModel) {
            itemView.setOnClickListener {
                onClickListener.onDishClick(dish.id)
            }
            nameField.text = dish.name
        }
    }

    inner class DelegateOnClickListener : OnClickListener {
        override fun onDishClick(dishId: String) {
            onDishClickListener.onDishClick(dishId)
        }
    }

    interface OnClickListener {
        fun onDishClick(dishId: String)

        object DoNothing : OnClickListener {
            override fun onDishClick(dishId: String) = Unit
        }
    }
}
