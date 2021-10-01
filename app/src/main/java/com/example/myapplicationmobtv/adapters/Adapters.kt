package com.example.myapplicationmobtv



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import com.example.myapplicationmobtv.ui.main.MainFragment
import kotlinx.android.synthetic.main.item_data.view.*


private class ChildItemAdapter  // Constructor
constructor(private val ChildItemList: List<SomeData>) :
    RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ChildViewHolder {
        // Here we inflate the corresponding
        // layout of the child item
        val view: View = LayoutInflater
            .from(viewGroup.context)
            .inflate(
                R.layout.item_data,
                viewGroup, false
            )
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(
        childViewHolder: ChildViewHolder,
        position: Int
    ) {

        // Create an instance of the ChildItem
        // class for the given position
        val mDefaultCardImage = R.drawable.movie
        when (val childItem = ChildItemList[position]) {
            is Movie -> {
                childViewHolder.childItemTitle.text = childItem.title
                childViewHolder.childItemShortDescription.text = childItem.shortDescription

                Glide.with(childViewHolder.itemView.imagePreview)
                    .load(childItem.thumbnail)
                    .centerCrop()
                    .error(mDefaultCardImage)
                    .into(childViewHolder.img)

                childViewHolder.itemView.setOnClickListener {
                    Log.d("Tag", "click id = " + childViewHolder.bindingAdapterPosition)
                    Toast.makeText(
                        childViewHolder.itemView.context,
                        "Click Data ${childViewHolder.bindingAdapterPosition}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            is Serie -> {
                childViewHolder.childItemTitle.text = childItem.seasons.toString()
              //  Log.d("Tag"," childItem.seasones.toString()"+childItem.seasones.get(position).episodes[position].thumbnail)
                childViewHolder.childItemShortDescription.text = childItem.shortDescription
                Glide.with(childViewHolder.itemView.context)
                    .load(childItem.thumbnail)
                    .centerCrop()
                    .error(mDefaultCardImage)
                    .into(childViewHolder.img)

                childViewHolder.itemView.setOnClickListener {
                  view ->
                    Log.d("Tag", "click id = " + childViewHolder.bindingAdapterPosition)
                    Toast.makeText(
                        childViewHolder.itemView.context,
                        "Click OtherData ${childViewHolder.bindingAdapterPosition}",
                        Toast.LENGTH_SHORT
                    ).show()


                    val fragment = MainFragment()
                    val bundle = Bundle()

                    bundle.putInt(
                       //"Key", childViewHolder.layoutPosition,
                        "Key",childItem.id
                     //  "key", feed.toString()


                    )
                    fragment.arguments = bundle
                   // Log.d("Tag", "fragment.arguments " + fragment.arguments)


                    (view.context  as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .setReorderingAllowed(false)
                        .replace(R.id.container,SeriesFragment.newInstance())
                        .add<SeriesFragment>("Key",fragment.arguments)
                        .addToBackStack(null)
                        .commit()

                }
            }
        }

    }



    override fun getItemCount(): Int {

        // This method returns the number
        // of items we have added
        // in the ChildItemList
        // i.e. the number of instances
        // of the ChildItemList
        // that have been created
        return ChildItemList.size
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    inner class ChildViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var childItemTitle: TextView = itemView.findViewById(
            R.id.nameTextView
        )
        var childItemShortDescription: TextView = itemView.findViewById(
            R.id.contentTextView
        )
        var img: ImageView = itemView.findViewById(R.id.imagePreview)

    }




}

class ParentItemAdapter internal constructor(private val itemList: List<ParentItem>) :
    RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder>() {


    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private val viewPool = RecycledViewPool()
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ParentViewHolder {

        // Here we inflate the corresponding
        // layout of the parent item
        val view: View = LayoutInflater
            .from(viewGroup.context)
            .inflate(
                R.layout.parent_item,
                viewGroup, false
            )
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(
        parentViewHolder: ParentViewHolder,
        position: Int
    ) {

        // Create an instance of the ParentItem
        // class for the given position
        val parentItem = itemList[position]

        // For the created instance,
        // get the title and set it
        // as the text for the TextView
        parentViewHolder.parentItemTitle.text = parentItem.parentItemTitle
        parentViewHolder.parentItemTitle.setOnClickListener {

            Toast.makeText(
                parentViewHolder.childRecyclerView.context, "Click Data " +
                        "${parentViewHolder.bindingAdapterPosition} ", Toast.LENGTH_SHORT
            ).show()
        }

        // Create a layout manager
        // to assign a layout
        // to the RecyclerView.

        // Here we have assigned the layout
        // as LinearLayout with vertical orientation
        val layoutManager = LinearLayoutManager(
            parentViewHolder.childRecyclerView
                .context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method
        layoutManager.initialPrefetchItemCount = parentItem
            .childItemList
            .size

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        val childItemAdapter = ChildItemAdapter(parentItem.childItemList)
        parentViewHolder.childRecyclerView.layoutManager = layoutManager
        parentViewHolder.childRecyclerView.adapter = childItemAdapter
        parentViewHolder.childRecyclerView
            .setRecycledViewPool(viewPool)


    }

    // This method returns the number
    // of items we have added in the
    // ParentItemList i.e. the number
    // of instances we have created
    // of the ParentItemList
    override fun getItemCount(): Int {
        return itemList.size
    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    inner class ParentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val parentItemTitle: TextView = itemView
            .findViewById(
                R.id.parent_item_title
            )
        val childRecyclerView: RecyclerView = itemView
            .findViewById(
                R.id.child_recyclerview
            )

    }
}