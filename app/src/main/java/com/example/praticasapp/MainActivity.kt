package com.example.praticasapp

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.praticasapp.ui.CityDialog
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.praticasapp.ui.HomePage
import com.example.praticasapp.ui.nav.BottomNavBar
import com.example.praticasapp.ui.nav.BottomNavItem
import com.example.praticasapp.ui.nav.MainNavHost
import com.example.praticasapp.ui.theme.PraticasAPPTheme
@OptIn(ExperimentalMaterial3Api::class)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: MainViewModel by viewModels()
            var showDialog by remember { mutableStateOf(false) }
            val navController = rememberNavController()
            PraticasAPPTheme {
                if (showDialog) {
                    CityDialog(
                        onDismiss = { showDialog = false },
                        onConfirm = { city ->
                            if (city.isNotBlank()) viewModel.add(city)
                            showDialog = false
                        }
                    )
                }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Bem-vindo/a!")
                            },
                            actions = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                        contentDescription = "Sair"
                                    )
                                }
                            }
                        )
                    },

                    bottomBar = {
                        val items = listOf(
                            BottomNavItem.HomeButton,
                            BottomNavItem.ListButton,
                            BottomNavItem.MapButton
                        )

                        BottomNavBar(
                            navController = navController,
                            items = items
                        )
                    },

                    floatingActionButton = {
                        FloatingActionButton(onClick = { showDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Adicionar"
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        MainNavHost(navController = navController,
                            viewModel = viewModel)
                    }
                }
            }
        }
    }
}