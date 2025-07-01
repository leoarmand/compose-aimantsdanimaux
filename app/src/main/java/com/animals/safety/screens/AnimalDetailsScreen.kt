package com.animals.safety.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.animals.safety.R
import com.animals.safety.data.Animal
import com.animals.safety.data.Breed
import com.animals.safety.ui.theme.AimantsDanimauxTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDetailsScreen(
  modifier: Modifier = Modifier,
  animal: Animal,
  onBackClick: () -> Unit,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(stringResource(id = R.string.details_fragment_label))
        },
        navigationIcon = {
          IconButton(onClick = {
            onBackClick()
          }) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = stringResource(id = R.string.contentDescription_go_back)
            )
          }
        }
      )
    },
  ) { contentPadding ->
    AnimalDetails(
      modifier = modifier.padding(contentPadding),
      animal = animal,
    )
  }
}

@Composable
private fun AnimalDetailsHeader(
  animalName: String,
  breed: Breed,
)
{
  Box (modifier = Modifier.fillMaxWidth()) {
    Image(
      modifier = Modifier.fillMaxWidth().aspectRatio(3f/2f),
      painter = painterResource(id = breed.cover),
      contentDescription = stringResource(id = R.string.contentDescription_avatar),
      contentScale = ContentScale.Crop
    )

    Text(
      modifier = Modifier
        .padding(start = 12.dp, bottom = 16.dp)
        .align(Alignment.BottomStart),
      text = animalName,
      style = MaterialTheme.typography.titleLarge,
      color = Color.White
    )
  }
}

@Composable
private fun IconCell(
  painter: Painter,
  contentDescription: String,
  text: String,
)
{
  Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
    Icon(
      modifier = Modifier
        .padding(bottom = 12.dp)
        .size(64.dp),
      painter = painter,
      contentDescription = contentDescription,
      tint = MaterialTheme.colorScheme.onSurface
    )
    Text(
      text = text,
      style = MaterialTheme.typography.bodyMedium,
      textAlign = TextAlign.Center,
      color = MaterialTheme.colorScheme.onSurface
    )
  }
}

@Composable
private fun AnimalDetails(
  modifier: Modifier = Modifier,
  animal: Animal,
)
{
  Column (modifier = modifier) {
    Row {
        AnimalDetailsHeader(animal.name, animal.breed)
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 48.dp),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconCell(
        painterResource(id = R.drawable.ic_age),
        stringResource(R.string.hint_age),
        stringResource(R.string.value_age, animal.age)
      )
      IconCell(
        painterResource(id = R.drawable.ic_weight),
        stringResource(R.string.hint_weight),
        stringResource(R.string.value_weight, animal.weight)
      )
    }

    Row(
      modifier = Modifier.fillMaxWidth()
      .padding(top = 48.dp),
      horizontalArrangement = Arrangement.Center
    ) {
      IconCell(
        painterResource(id = R.drawable.ic_height),
        stringResource(R.string.hint_height),
        stringResource(R.string.value_height, animal.height)
      )
    }
  }
}

@Preview(showBackground = true)
@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun AnimalDetailsPreview() {
  AimantsDanimauxTheme(dynamicColor = false) {
    AnimalDetails(
      animal = Animal(UUID.randomUUID(),"Milou", Breed.DOG, 6, 23.2f, 42.4f),
    )
  }
}
