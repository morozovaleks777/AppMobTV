package com.example.myapplicationmobtv.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationmobtv.*
import com.example.myapplicationmobtv.series_screen.SeriesFragment
import com.example.myapplicationmobtv.series_screen.SeriesViewModel


class ItemArrayAdapter2     // Constructor of the class
constructor(//All methods in this adapter are required for a bare minimum recyclerview adapter
    private val listItemLayout2: Int, private val seriesList: List<Series>
) :
    RecyclerView.Adapter<ItemArrayAdapter2.ViewHolder>() {

    val viewModel= SeriesViewModel()
    // get the size of the list
    override fun getItemCount(): Int {
        return      seriesList.get(viewModel.id).seasonList?.get(1)?.episodes?.size ?:0
    }

    // specify the row layout file and click for each row
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(listItemLayout2, parent, false)
        return ViewHolder(view)
    }

    val mDefaultCardImage = R.drawable.movie

    // load data in each row element

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val fragment = SeriesFragment()

        if (fragment.seasonNumber != null) {
            holder.item.setText(
                "${
                    seriesList.get(viewModel.id).seasonList?.get(fragment.seasonNumber as Int)?.episodes?.get(
                        listPosition
                    )?.title
                }"
            )
            holder.item2.setText(
                "${
                    seriesList.get(viewModel.id).seasonList?.get(fragment.seasonNumber as Int)?.episodes?.get(
                        listPosition
                    )?.shortDescription
                }"
            )
            Glide.with(holder.itemView.context)
                .load(
                    seriesList.get(viewModel.id).seasonList?.get(fragment.seasonNumber as Int)?.episodes?.get(
                        listPosition
                    )?.thumbnail
                )
                .centerCrop()
                .error(mDefaultCardImage)
                .into(holder.img)
            Log.d("Tag", "seasonNumber ${fragment.seasonNumber}")
            holder.cardView.setCardBackgroundColor(0)

            holder.cardView.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {

                    holder.cardView.setBackgroundResource(0)
                } else holder.cardView.setBackgroundResource(R.drawable.icon)

            })

        }else holder.cardView.isVisible=false
    }
    // Static inner class to initialize the views of rows
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var item: TextView
        var item2: TextView
        var img: ImageView
        var cardView: CardView
        override fun onClick(view: View?) {
            Log.d("Tag", "onClick " + layoutPosition + " " + item.text)

        }

        init {
            itemView.setOnClickListener(this)
            item = itemView.findViewById(R.id.nameTextView2)
            item2 = itemView.findViewById(R.id.contentTextView2)
            img = itemView.findViewById(R.id.imagePreview)
            cardView = itemView.findViewById(R.id.itemData2)
        }
    }
}