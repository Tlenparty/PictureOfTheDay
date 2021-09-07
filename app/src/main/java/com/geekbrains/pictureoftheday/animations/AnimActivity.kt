package com.geekbrains.pictureoftheday.animations

import android.content.Context
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.transition.*
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.transition.addListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ArcMotion
import androidx.transition.Explode
import androidx.transition.Fade
import androidx.transition.PathMotion
import androidx.transition.Transition
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.ActivityAnimBinding


class MyPath : PathMotion() {
    override fun getPath(startX: Float, startY: Float, endX: Float, endY: Float): Path {
        return Path().apply {
            cubicTo(
                startX,
                startY,
                50f,
                100f,
                endX,
                endY
            )
        }
    }

}

class AnimActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimBinding
    var bool = true

    override fun onCreate(savedInstanceState: Bundle?) = with(binding) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rv.adapter = Adapter()
        //   rv.layoutMandger = GridLayoutManager(this, 3)
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? = with(binding) {
/*        // Меняем видимость tv
        // Виды анимации Slide(Gravity.End), ChangeBounce, Fade, AutoTransition, TransitionSet
        TransitionManager.beginDelayedTransition(cont)
        btn.setOnClickListener {
            tv.visibility = if (bool) View.GONE else View.VISIBLE
            bool = !bool
        }*/

        // Масштабируем картинку
        img.setOnClickListener {
            bool = !bool
            TransitionManager.beginDelayedTransition(
                cont,TransitionSet()
                    .addTransition(ChangeBounds()) // Когда у вью меняются размеры или расположение
                    .addTransition(ChangeImageTransform()) // позволяет анимировать scale у вью
            )
        }
        // меняем layout параметры картинки
        val params: ViewGroup.LayoutParams = img.layoutParams
        params.height =
            if (bool) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
        img.apply{
            layoutParams = params
            scaleType =
                if (bool) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }

        // Изменение положения кнопки
        btn.setOnClickListener {
            val changeBounds = ChangeBounds()
            //changeBounds.setPathMotion(ArcMotion())
            changeBounds.duration = 1000
            TransitionManager.beginDelayedTransition(
                cont,changeBounds
            )

            bool = !bool
            val params = btn.layoutParams as FrameLayout.LayoutParams
            params.gravity =
                if (bool) Gravity.END or Gravity.BOTTOM else Gravity.START or Gravity.TOP
            btn.layoutParams = params
        }


        return super.onCreateView(parent, name, context, attrs)
    }

    // анимация взрыва
    /*private fun explode(clickedView: View) = with(binding){
        val viewRect = Rect() // Прямоугольник
        clickedView.getGlobalVisibleRect(viewRect)
        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.excludeTarget(clickedView, true)
        val set = TransitionSet() // дает возможность соеденить несколько анимаций
            .addTransition(explode)
             .addTransition(Fade().addTarget(clickedView))
          .addListener(object:TransitionListenerAdapter(){
               override fun onTransitionEnd(transition: android.transition.Transition?) {
                   transition.removeListener(this)
               }
           })
        TransitionManager.beginDelayedTransition(rv, set)
        rv.adapter = null
    }*/


    inner class Adapter : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animations_explode_recycler_view_item, parent, false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                //explode(it)
            }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}


