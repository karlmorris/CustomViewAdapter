package edu.temple.customviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class CustomAdapter (val _context: Context, _imageObjects: Array<ImageObject>) : BaseAdapter() {

    val imageObjects = _imageObjects;
    val inflater = LayoutInflater.from(_context)

    override fun getCount(): Int {
        return imageObjects.size
    }

    override fun getItem(position: Int): Any {
        return imageObjects.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        var layout : View
        var descriptionTextView: TextView
        var imageView: ImageView


        // The Recycle Pattern: If a previously used View is provided then reuse it
        // Otherwise create (or inflate) a new one
        if (convertView is ConstraintLayout)
            layout = convertView
        else
            layout = inflater.inflate(R.layout.spinner_layout, null)

        // Regardless of if the layout is created or reused, we need to access the views
        // found inside
        imageView = layout.findViewById(R.id.imageView)
        descriptionTextView = layout.findViewById(R.id.descriptionTextView)

        // And now we can set the views based on the ImageObject specified
        // by the 'position' parameter
        imageView.setImageResource(imageObjects[position].resourceId)
        descriptionTextView.text = imageObjects[position].description

        // Dim the view slightly
        return layout.apply { alpha = .2f };
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Instead of repeating much of what we did in getView()
        // we can reuse it to get a view that we can then modify as needed
        // for our Spinner's drop-down view - Here we increase the opacity
        // of the item when selecting an item
        return getView(position, convertView, parent).apply { alpha = 1f }
    }
}