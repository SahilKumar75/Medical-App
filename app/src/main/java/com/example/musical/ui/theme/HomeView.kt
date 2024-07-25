package com.example.musical.ui.theme

import PatientDetailsScreen
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musical.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Medical Appointment Booking") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AppointmentCard(navController = navController)

                Spacer(modifier = Modifier.height(16.dp))

                SearchBar(searchQuery = searchQuery, onQueryChange = { searchQuery = it })

                Spacer(modifier = Modifier.height(16.dp))

                CircleButtonRow()
            }
        }
    )
}

@Composable
fun AppointmentCard(navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .clickable {
                navController.navigate("patient_details")
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF00ABFF))
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.your_image), // Replace with your image resource
                    contentDescription = "Doctor Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Doctor",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = "Surgeon",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun SearchBar(searchQuery: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun CircleButtonRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        CircleIconButton(icon = Icons.Filled.Home, contentDescription = "Home")
        CircleIconButton(icon = Icons.Filled.Search, contentDescription = "Search")
        CircleIconButton(icon = Icons.Filled.Notifications, contentDescription = "Notifications")
        CircleIconButton(icon = Icons.Filled.Settings, contentDescription = "Settings")
    }
}

@Composable
fun CircleIconButton(icon: ImageVector, contentDescription: String?) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .clickable { /* Handle click action */ }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
}


@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController = navController)
        }
        composable("patient_details") {
            PatientDetailsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}


