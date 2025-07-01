package com.animals.safety.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.animals.safety.R
import java.io.Serializable
import java.util.UUID

data class Animal(
  val id: UUID,
  val name: String,
  val breed: Breed,
  val age: Int,
  val weight: Float,
  val height: Float
) : Serializable {
  @Composable
  fun getInformationText(): String {
      return stringResource(R.string.information, breed.getCapitalizedTranslatedName(), age, weight, height)
  }
}
