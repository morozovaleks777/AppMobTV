package com.example.myapplicationmobtv.adapters


import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationmobtv.*

var seasonsNumber=0
var previousClickedItemPosition = -1
class ItemArrayAdapter     // Constructor of the class
  constructor (//All methods in this adapter are required for a bare minimum recyclerview adapter
    private val listItemLayout: Int,private val itemList: List<Parentitemseries>
) :
    RecyclerView.Adapter<ItemArrayAdapter.ViewHolder>() {


    // get the size of the list
    override fun getItemCount(): Int {
        return   itemList.size   // feed?.series?.get(serieId.getInt("key"))?.seasons!!.size  //itemList. size
    }

    // specify the row layout file and click for each row
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(listItemLayout, parent, false)
        return ViewHolder(view)
    }
    // load data in each row element
    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {


      //  val childItem = itemList[listPosition]
        if(listPosition<5){
     holder.item.setText("${feed?.series?.get(serieId.getInt("key"))?.seasons?.get(0)?.seasonNumber!!}")}
      else  holder.item.setText("${feed?.series?.get(serieId.getInt("key"))?.seasons?.get(1)?.seasonNumber!!}")

       holder.item2.setText("season" )
        holder.cardView.setCardBackgroundColor(0)

        holder.cardView.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {

                holder.cardView.setBackgroundColor(Color.DKGRAY)
            } else  holder.cardView.setBackgroundColor(0)

        })
        //if(previousClickedItemPosition==listPosition){holder.cardView.setBackgroundColor(Color.DKGRAY)}else holder.cardView.setBackgroundColor(0)
    }

    // Static inner class to initialize the views of rows
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var item: TextView
        var item2: TextView
        var cardView:CardView


        override fun onClick(view: View?) {
            Log.d("Tag", "onClick " + layoutPosition + " " + item.text)
             seasonsNumber=when{
                 layoutPosition<5 -> (feed?.series?.get(serieId.getInt("key"))?.seasons?.get(0)?.seasonNumber!!)-1
             else ->(feed?.series?.get(serieId.getInt("key"))?.seasons?.get(1)?.seasonNumber!!)-1}
            if(previousClickedItemPosition!=layoutPosition) {
                previousClickedItemPosition = layoutPosition

                    //bindingAdapter?.notifyItemChanged(previousClickedItemPosition)


                (view?.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(false)
                    .replace(R.id.container, SeriesFragment())
                    .addToBackStack(null)
                    .commit()

            }

        }




        init {
            itemView.setOnClickListener(this)
            item = itemView.findViewById(R.id.nameTextView)
            item2 = itemView.findViewById(R.id.contentTextView)
            cardView=itemView.findViewById(R.id.itemData)
        }
    }
}