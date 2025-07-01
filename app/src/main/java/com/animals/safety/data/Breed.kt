package com.animals.safety.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.animals.safety.R

enum class Breed {
  CAT,
  COW,
  DOG,
  LAMA;
  
  @get:StringRes
  val translatedName: Int
    get() = when (this) {
      CAT -> R.string.breed_cat
      COW -> R.string.breed_cow
      DOG -> R.string.breed_dog
      LAMA -> R.string.breed_lama
    }
  
  @get:DrawableRes
  val cover: Int
    get() = when (this) {
      CAT -> R.drawable.img_cat
      COW -> R.drawable.img_cow
      DOG -> R.drawable.img_dog
      LAMA -> R.drawable.img_lama
    }

  @Composable
  fun getCapitalizedTranslatedName(): String {
    val animalTranslatedName = stringResource(id = translatedName)
    val capitalizedAnimalTranslatedName = if (animalTranslatedName.isNotEmpty()) {
      animalTranslatedName.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
      }
    } else {
      animalTranslatedName
    }
    return capitalizedAnimalTranslatedName
  }
}
