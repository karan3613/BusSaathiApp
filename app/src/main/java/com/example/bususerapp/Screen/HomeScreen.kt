package com.example.bususerapp.Screen



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bususerapp.HomeViewModel
import com.example.bususerapp.ui.theme.ourGreen
import com.example.driverapp.Data.Models.BusStatusResponse
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewmodel : HomeViewModel = hiltViewModel()) {
    var isReady by remember { mutableStateOf(false) }
    val busResponse = viewmodel.busResponse.collectAsState()
    val busLocationResponse = viewmodel.busLocation.collectAsState()
    val startId by remember{ mutableStateOf(-1) }
    val endId by remember { mutableStateOf(-1) }
    var startDestination by remember { mutableStateOf("") }
    var endDestination by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val startDestinations by viewmodel.startDestinations.collectAsState()
    val endDestinations by viewmodel.endDestinations.collectAsState()
    var endExpanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }



    Column(
        modifier = Modifier.fillMaxSize().background(Color.White) ,
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Centered Text
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome, ",
                    fontSize = 24.sp
                )
                Text(
                    text = busResponse.value.busResponse?.driver_name ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Icon on the right
            Icon(
                imageVector = Icons.Default.Person, // Use your drawable resource
                contentDescription = "Profile Icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterEnd)
                    .background(Color(0xFF88E0C9), shape = MaterialTheme.shapes.small)
                    .padding(8.dp)
            )
        }
        ExposedDropdownMenuBox(
            expanded = expanded, onExpandedChange = { expanded = !expanded }
            , modifier =  Modifier.fillMaxWidth().focusRequester(focusRequester).onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    expanded = true
                }
            }.clickable { focusRequester.requestFocus() }) {
            TextField(
                value = startDestination,
                onValueChange = {
                    startDestination = it
                    viewmodel.get_area_start(startDestination) // Perform search on text change
                    expanded = true // Show dropdown when typing
                },
                label = { Text("Start Destination") },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                readOnly = false
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // Show filtered results in the dropdown

                startDestinations.busDestinationResponse?.forEach {area->
                    DropdownMenuItem(
                        text =
                        {
                            Text(text = area.area_name)
                        }, onClick = {
                            startDestination = area.area_name// Set selected area
                            expanded = false // Close the dropdown
                        })
                }
            }
        }

        ExposedDropdownMenuBox(expanded = endExpanded, onExpandedChange = { endExpanded = !endExpanded } ,
            modifier = Modifier.focusRequester(focusRequester).onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    expanded = true
                }
            }.clickable { focusRequester.requestFocus() }) {
            TextField(
                value = endDestination,
                onValueChange = {
                    endDestination = it
                    viewmodel.get_area_end(endDestination) // Perform search on text change
                    endExpanded = true // Show dropdown when typing
                },
                label = { Text("End Destination") },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                readOnly = false
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // Show filtered results in the dropdown
                if(endDestinations.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp)
                    )
                }else{
                    if(endDestinations.busDestinationResponse != null){
                        endDestinations.busDestinationResponse?.forEach { area ->
                            DropdownMenuItem(
                                text =
                                {
                                    Text(text = area.area_name)
                                }
                                , onClick = {
                                    startDestination = area.area_name// Set selected area
                                    expanded = false // Close the dropdown
                                })
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(60.dp))
        val singapore = LatLng(25.3439, 81.9093)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxWidth().height(500.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "London",
                snippet = "Marker in Big Ben"
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(30.dp))
        Button(
            modifier = Modifier.height(50.dp).width(250.dp),
            shape = RoundedCornerShape(5.dp),
            onClick = {
                isReady = !isReady
                viewmodel.get_buses(1 ,3)
                navController.navigate("BusDetailScreen")
            } ,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isReady) Color.Red else ourGreen ,
                contentColor = Color.White
            )
        ) {
                Text(
                    text = "Buses",
                    color = Color.White
                )
        }
    }


}


