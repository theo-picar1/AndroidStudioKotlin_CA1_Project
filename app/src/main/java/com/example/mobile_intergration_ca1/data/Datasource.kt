package com.example.mobile_intergration_ca1.data

import Affirmation
import com.example.mobile_intergration_ca1.R

class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf(
            Affirmation(R.string.australia_title, R.string.australia_desc, R.drawable.australia_img),
            Affirmation(R.string.austria_title, R.string.austria_desc, R.drawable.austria_img),
            Affirmation(R.string.greece_title, R.string.greece_desc, R.drawable.greece_img),
            Affirmation(R.string.iceland_title, R.string.iceland_desc, R.drawable.iceland_img),
            Affirmation(R.string.italy_title, R.string.italy_desc, R.drawable.italy_img),
            Affirmation(R.string.new_zealand_title, R.string.new_zealand_desc, R.drawable.new_zealand_img),
            Affirmation(R.string.norway_title, R.string.norway_desc, R.drawable.norway_img),
            Affirmation(R.string.spain_title, R.string.spain_desc, R.drawable.spain_img),
            Affirmation(R.string.switzerland_title, R.string.switzerland_desc, R.drawable.switzerland_img),
            Affirmation(R.string.thailand_title, R.string.thailand_desc, R.drawable.thailand_img)
        )
    }
}