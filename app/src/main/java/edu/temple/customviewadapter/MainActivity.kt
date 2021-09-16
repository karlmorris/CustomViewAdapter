package edu.temple.customviewadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val displayImageView = findViewById<ImageView>(R.id.displayImageView)

        // Creating an instance of our custom adapter and passing in context, along with a
        // collection of data elements
        spinner.adapter = CustomAdapter(this, getTestData())

        // Our OnItemSelectionListener works exactly the same as when we
        // work with a predefined adapter, except now we are retrieving ImageObjects
        // instead of Strings
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val imageObject = parent?.getItemAtPosition(position) as ImageObject
                displayImageView.setImageResource(imageObject.resourceId)
                descriptionTextView.text = imageObject.description
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

    // Test data for demonstration purposes
    // Using resource IDs for drawables along with text descriptions
    fun getTestData() : Array<ImageObject> {
        val imageObject = arrayOf(ImageObject("By the fireplace", R.drawable.fireplace)
            , ImageObject("Digging around", R.drawable.grass)
            , ImageObject("On the rug", R.drawable.rug)
            , ImageObject("Tuckered out", R.drawable.snoozing)
            , ImageObject("Sweater weather", R.drawable.sweater));

        return imageObject
    }
}