package com.animals.safety.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animals.safety.R
import com.animals.safety.data.Animal
import com.animals.safety.data.AnimalData
import com.animals.safety.data.Breed
import com.animals.safety.ui.theme.AimantsDanimauxTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  animals: List<Animal> = AnimalData.animals,
  onAnimalClick: (Animal) -> Unit = {},
  onFABClick: () -> Unit = {},
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(stringResource(id = R.string.home_fragment_label))
        }
      )
    },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          onFABClick()
        }
      ) {
        Icon(
          imageVector = Icons.Filled.Add,
          contentDescription = stringResource(id = R.string.contentDescription_add_animal)
        )
      }
    }
  ) { contentPadding ->
    HomeList(
      modifier = modifier
          .padding(contentPadding),
      animals = animals,
      onAnimalClick = onAnimalClick
    )
  }
}

@Composable
private fun HomeList(
  modifier: Modifier = Modifier,
  animals: List<Animal>,
  onAnimalClick: (Animal) -> Unit,
) {
  LazyColumn(modifier) {
    items(animals) { animal ->
      HomeCell(
        animal = animal,
        onAnimalClick = onAnimalClick
      )
      HorizontalDivider()
    }
  }
}

@Composable
private fun HomeCell(
  animal: Animal,
  onAnimalClick: (Animal) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onAnimalClick(animal)
            }
            .padding(vertical = 24.dp),
    ) {
        Column (modifier = Modifier.padding(start = 16.dp)) {
            Image(
                modifier = Modifier
                .size(68.dp),
                painter = painterResource(id = animal.breed.cover),
                contentDescription = stringResource(R.string.contentDescription_avatar)
            )
        }
        Column (modifier = Modifier.padding(start = 16.dp)
            .widthIn(max = 284.dp)) {
            Text(
                text = animal.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = animal.getInformationText(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeCellPreview() {
  AimantsDanimauxTheme(dynamicColor = false) {
    HomeCell(
      animal = Animal(UUID.randomUUID(),"Milou", Breed.DOG, 6, 23.2f, 42.4f),
      onAnimalClick = {}
    )
  }
}
