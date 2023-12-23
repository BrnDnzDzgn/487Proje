package com.example.a487_project.Classes //Ahmet
import android.view.View
import android.widget.ImageView
import com.example.a487_project.CustomAdapters.ClothingListAdapther
import kotlin.random.Random
import com.example.a487_project.Activities.FashionRoomActivity


class Look { // içinde tüm clothing items olacak, gösterilme ve beğenme counteri, lookName, id is unique, theme,
    //search in the database, group by theme, sort by liked counter, try to keep the shown counter the same
//bütün hepsi databasedes


    val lookClothings : ArrayList<ClothingItemKami> = ArrayList()

    fun addItem(item: ClothingItemKami) {
        lookClothings.add(item)
    }

    fun removeItem(item: ClothingItemKami) {
        lookClothings.remove(item)
    }

    fun clearList() {
        lookClothings.clear()
    }

    fun randomizeLook(){
        val random = Random
        val topName = "top" + random.nextInt(1,6)
        val top = ClothingItemKami(6, "Default", topName, "Top")
        addItem(top)

        val bottomName = "bottom" + random.nextInt(1,6)
        val bottom = ClothingItemKami(5, "Default", bottomName, "Bottom")
        addItem(bottom)

        val shoesName = "shoes" + random.nextInt(1,6)
        val shoes = ClothingItemKami(3, "Default", shoesName, "Shoes")
        addItem(shoes)

        val hairName = "hair" + random.nextInt(1,6)
        val hair = ClothingItemKami(1, "Default", hairName, "Hair front")
        addItem(hair)

        val acsName = "acs" + random.nextInt(1,10)
        val acs = ClothingItemKami(7, "Default", acsName, "Accessory")
        addItem(acs)
    }

    fun setLookToBody(holder: View){
        for(item in lookClothings){
            val resourceId = holder.resources.getIdentifier(
                item.imageName, "drawable", holder.context.packageName
            )

            val imageViewId = "layer" + item.layer
            val imageView: ImageView? = holder.rootView.findViewById(
                holder.resources.getIdentifier(imageViewId, "id", holder.context.packageName)

            )

            imageView?.setImageResource(resourceId)
        }
    }



}