package id.adeds.started.view

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import id.adeds.started.data.model.User
import id.adeds.started.databinding.ItemMainBinding

class MainAdapter(private val list: List<User>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) = binding.apply {
            avaUser.load(item.avatar_url){
                transformations(CircleCropTransformation())
            }
            nameUser.text = item.login
            root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.html_url)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }


}