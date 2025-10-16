package com.example.bususerapp.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bususerapp.HomeViewModel

data class busStatusResponse(
val bus_no : String ,
    val bus_status : Boolean ,
    val distance : Int
)

val busStatusList = listOf(
    busStatusResponse("UP20KT9655" , true , 7),
    busStatusResponse("UP20KT9425" , true ,  13) ,
    busStatusResponse("UP20KT9625" , true , 12),
    busStatusResponse("UP20KT9134" , true , 9 )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusDetailScreen( navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val busListStatusResponse by viewModel.busListStatus.collectAsState()
    Scaffold(
        topBar = { ProfileTopBar() },
        // Follow button at the bottom
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize().background(Color.White)
        ) {
            Spacer(Modifier.height(15.dp))
            Text(
                "Available",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                color  = Color.Black
            )
            LazyColumn {
                items(busStatusList) { bus ->
                    BusListSection(
                      bus.bus_no,
                        bus.distance.toString() + "KM",
                        bus.bus_status
                    )
                }
            }
            FollowButton()
        }
    }
}

@Composable
fun BusListSection(plateNumber: String, distance: String, isHighlighted: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = if (isHighlighted) CardDefaults.cardColors(containerColor = Color(0xFFD3F9D8)) else CardDefaults.cardColors()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(plateNumber, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black) // Plate number in black
                Text(distance, fontSize = 14.sp, color = Color.Black) // Distance in black
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Info",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun FollowButton() {
    Button(
        onClick = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10) ,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF008955),
            contentColor = Color.White
        )
    ) {
        Text("Follow")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().background(Color.White),
        title = { Text("Nearby") },
        actions = {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier.size(32.dp).background(Color.White)
            )
        }
    )
}


