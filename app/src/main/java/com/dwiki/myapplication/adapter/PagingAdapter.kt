package com.dwiki.myapplication.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwiki.myapplication.databinding.RowItemBinding
import com.dwiki.myapplication.response.DataItem
import com.dwiki.myapplication.ui.SecondActivity

class PagingAdapter: PagingDataAdapter<DataItem, PagingAdapter.MyViewHolder>(DIFF_CALLBACK) {


    class MyViewHolder(private val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem){
            binding.apply {
                nameUser.text = "${user.firstName} ${user.lastName}"
                emailUser.text = user.email

                Glide.with(itemView)
                    .load(user.avatar)
                    .into(imgUser)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null){
            holder.bind(user)
            val fullName = "${user.firstName} ${user.lastName}"
            holder.itemView.setOnClickListener{
                toSecondScreen(holder.itemView, fullName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    private fun toSecondScreen(itemView: View, name: String){
        val intent = Intent (itemView.context, SecondActivity::class.java)
        intent.putExtra("selected user", name)
        itemView.context.startActivity(intent)
    }

    companion object{

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}