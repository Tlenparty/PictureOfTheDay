package com.geekbrains.pictureoftheday.span

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.BulletSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.ActivitySpanBinding

class SpanActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setSomeText()
        // spanOne()
        // spanTwo()
        // spanThree()
        spanFour()
    }

    private fun setSomeText() {
        binding.textViewSpan.text = "SomeText"
    }

    private fun spanOne() {
        val text = "My text <ul><li>bullet one</li><bullet two</li></ul> <a href=''>Click me</a>"
        binding.textViewSpan.text = Html.fromHtml(text)
    }

    private fun spanTwo() {
        val spannable = SpannableString("My text \nbullet one\nbullet two")
        applicationContext.resources.getColor(R.color.black)

        spannable.setSpan(
            BulletSpan(8, ContextCompat.getColor(baseContext, R.color.teal_700)),
            9,
            10,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            BulletSpan(8, ContextCompat.getColor(baseContext, R.color.teal_700)),
            20,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE // включение границ
        )

        spannable.setSpan(
            ForegroundColorSpan( // цвет шрифта
                ContextCompat.getColor(
                    baseContext,
                    R.color.design_default_color_secondary
                )
            ),
            9,
            15,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            BackgroundColorSpan( // фон шрифта
                ContextCompat.getColor(baseContext, android.R.color.holo_red_dark)
            ),
            20,
            26,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        SpannableStringBuilder()
        binding.textViewSpan.text = spannable
    }

    private fun spanThree() {
        val spannable = SpannableString("Text styling")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(baseContext, R.color.colorAccent)),
            0,
            4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textViewSpan.text = spannable
    }

    // clickableSpan
    private fun spanFour() {
        val spannable = SpannableString("Android is a SoftWare stack")
        val clickableSpan: ClickableSpan =
            object : ClickableSpan() { // обьяление анонимного обьекта, наследущего ClickableSpan()
                override fun onClick(widget: View) {
                    Toast.makeText(baseContext, "Clicked", Toast.LENGTH_LONG).show()
                }

                // Включение нижнего подчеркивания
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
        spannable.setSpan(clickableSpan, 22, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textViewSpan.text = spannable
        binding.textViewSpan.movementMethod = LinkMovementMethod.getInstance()
        binding.textViewSpan.highlightColor = Color.TRANSPARENT // чтобы не было выделения
    }
}