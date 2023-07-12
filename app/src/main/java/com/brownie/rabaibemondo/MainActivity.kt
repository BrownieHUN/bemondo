package com.brownie.rabaibemondo

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brownie.rabaibemondo.data.DataSource
import com.brownie.rabaibemondo.data.DataSource.szignal
import com.brownie.rabaibemondo.data.DataSource.vonatnem
import com.brownie.rabaibemondo.data.OutputList
import com.brownie.rabaibemondo.data.OutputList.output
import com.brownie.rabaibemondo.model.Audio
import com.brownie.rabaibemondo.ui.theme.RábaiBemondóTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RábaiBemondóTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()

                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column() {
        Row() {
            Column(Modifier.weight(1f)) {
                OptionCard("Szignál", szignal)
            }
            Column(Modifier.weight(1f)) {
                OptionCard("Vonatnem", vonatnem)
            }
        }
        playButton(LocalContext.current)
    }

}

@Composable
fun OptionCard(
    name: String,
    type: List<Audio>,
    onSelectionChanged: (String) -> Unit = {}
) {

    var selectedValue by remember { mutableStateOf("") }

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(
                start = 12.dp,
                top = 12.dp
                )
        )
        LazyColumn() {
            items(type) {audio ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.selectable(
                        selected = selectedValue == audio.name,
                        onClick = {
                            selectedValue = audio.name
                            onSelectionChanged(audio.name)
                        }
                    )
                ) {
                    RadioButton(
                        selected = selectedValue == audio.name,
                        onClick = {
                            selectedValue = audio.name
                            onSelectionChanged(audio.name)
                        }
                    )
                    Text(
                        text = audio.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        Text(text = selectedValue)
    }
}

@Composable
fun playButton(context: Context) {

    val mp: MediaPlayer = MediaPlayer.create(context, output[0])

    FloatingActionButton(
        onClick = { mp.start() },
        modifier = Modifier
            .size(56.dp)
            .padding(16.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RábaiBemondóTheme {
        MainScreen()
    }
}