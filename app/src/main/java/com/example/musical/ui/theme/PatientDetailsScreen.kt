import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Patient Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Gender") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Contact Number") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { /* Handle form submission */ },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Submit")
                }
            }
        }
    )
}
