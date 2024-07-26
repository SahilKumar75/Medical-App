package com.example.musical.ui.theme

import PatientDetailsScreen
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Home(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(

        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ImageCarousel()

                Spacer(modifier = Modifier.height(16.dp))

                SearchBar(searchQuery = searchQuery, onQueryChange = { searchQuery = it })

                Spacer(modifier = Modifier.height(16.dp))

                CircleButtonRow()

                Spacer(modifier = Modifier.height(16.dp))

                AppointmentCard(navController = navController)
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
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF4894fe))
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.your_image1), // Replace with your image resource
                        contentDescription = "Doctor Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Dr. Daksh Dhaka",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF)
                        )
                    )
                    Text(
                        text = "Heart Surgeon",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFFFFFFF)
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("patient_details")
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Book Appointment",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFF094999), CircleShape)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Book Appointment",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFFFFFFFF)
                    ),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchQuery: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        label = { Text("Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        },
        shape = RoundedCornerShape(24.dp), // Adjust the value for desired roundness
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray,
            containerColor = MaterialTheme.colorScheme.surface
        ),
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
        CircleIconButton(icon = Icons.Filled.LocalHospital, contentDescription = "Hospital")
        CircleIconButton(icon = Icons.Filled.MedicalServices, contentDescription = "Medical Services")
        CircleIconButton(icon = Icons.Filled.Medication, contentDescription = "Medication")
        CircleIconButton(icon = Icons.Filled.HealthAndSafety, contentDescription = "Health and Safety")
    }
}

@Composable
fun CircleIconButton(icon: ImageVector, contentDescription: String?) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(2.dp, Color(0xFF006eff), CircleShape)
            .background(Color.White)
            .clickable { /* Handle click action */ }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color(0xFF006eff)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel() {
    val pagerState = rememberPagerState()
    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    )

    LaunchedEffect(pagerState) {
        while (true) {
            yield()
            delay(3000) // 3 seconds delay
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % images.size)
        }
    }

    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .clickable { /* Handle click action */ },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
        ) {
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Image $page",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
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
