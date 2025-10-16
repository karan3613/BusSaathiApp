package com.example.bususerapp.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bususerapp.Constants.Session
import com.example.bususerapp.HomeViewModel
import com.example.bususerapp.Models.UserRegisterResponse
import com.example.bususerapp.ui.theme.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController, viewmodel : LoginViewModel  = hiltViewModel()) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var Occupation by remember { mutableStateOf("") }
    var Age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(true) }
    val registerState = viewmodel.userResponse.collectAsState()
    val isUsernameSaved by viewmodel.isUsernameSaved

    LaunchedEffect(isUsernameSaved) {
        if (isUsernameSaved) {
            Session.USER_ID = registerState.value.userIdResponse?.user_id ?: -1
            navController.navigate("main_graph") {
                popUpTo("auth_graph") {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Sign Up",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle
        Text(
            text = "with your credentials",
            fontSize = 16.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // First and Last Name Fields
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Firstname", color = Color.Gray) },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Lastname",color = Color.Gray) },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = Occupation,
                onValueChange = { Occupation = it },
                label = { Text("Occupation", color = Color.Gray) },
                modifier = Modifier.weight(3f),

                )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = Age,
                onValueChange = { Age = it },
                label = { Text("Age",color = Color.Gray) },
                modifier = Modifier.weight(1f)
            )
        }


        // Bus Number Field


        Spacer(modifier = Modifier.height(16.dp))

        // Conductor Name Field
        OutlinedTextField(
            value = email,
            onValueChange = { email  = it },
            label = { Text("email",color = Color.Gray) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Conductor Number Field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("username",color = Color.Gray) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password",color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Terms and conditions checkbox and links
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle, contentDescription = "",
                tint = Color(0xFF008955),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "By signing up, you agree to the ",
                color = Color.Gray,
                modifier=Modifier.padding(start = 8.dp)
            )
            Text(
                text = "Terms and services",
                color = Color(0xFF008955), // Green for clickable text
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Sign Up Button
        Button(
            onClick = {
                viewmodel.register_bus(UserRegisterResponse(username = username  , email = email , password = password , age =  Age.toInt() , occupation = Occupation , firstname = firstName , lastname = lastName))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF008955) // Green color
            )
        ) {
            Text(text = "Sign Up", color = Color.White)
        }
    }
}
