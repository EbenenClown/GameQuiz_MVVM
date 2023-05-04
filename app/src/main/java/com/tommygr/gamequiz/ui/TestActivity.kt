package com.tommygr.gamequiz.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.ui.ui.theme.GameQuizTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestActivity : ComponentActivity() {
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel,this, "Android")
                }
            }
        }
        lifecycleScope.launch {
            viewModel.elements.collect {
                it.forEach {
                    Log.d("test123", "onCreate: ${it.id}")
                }

            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainScreenViewModel,context: Context, name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()
        }
    )
}