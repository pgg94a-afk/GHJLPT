package com.gg.ghjnpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gg.ghjnpt.ui.screens.grammar.GrammarMemorizeScreen
import com.gg.ghjnpt.ui.screens.main.MainScreen
import com.gg.ghjnpt.ui.screens.memorize.MemorizeScreen
import com.gg.ghjnpt.ui.screens.quiz.QuizScreen
import com.gg.ghjnpt.ui.screens.conjunction.ConjunctionMemorizeScreen
import com.gg.ghjnpt.ui.screens.keigo.KeigoMemorizeScreen
import com.gg.ghjnpt.ui.theme.GHJNPTTheme

class MainActivity : ComponentActivity() {

    private val quizViewModel = QuizViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GHJNPTTheme {
                AppNavigation(quizViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: QuizViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, viewModel)
        }
        composable("quiz") {
            QuizScreen(navController, viewModel)
        }
        composable("memorize") {
            MemorizeScreen(navController, viewModel)
        }
        composable("grammarMemorize") {
            GrammarMemorizeScreen(navController, viewModel)
        }
        composable("conjunctionMemorize") {
            ConjunctionMemorizeScreen(navController, viewModel)
        }
        composable("keigoMemorize") {
            KeigoMemorizeScreen(navController, viewModel)
        }
    }
}
