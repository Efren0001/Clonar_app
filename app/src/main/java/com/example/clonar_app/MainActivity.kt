package com.example.clonar_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacebookApp()
        }
    }
}

val FacebookBlue = Color(0xFF1877F2)
val FacebookBackground = Color(0xFFF0F2F5)
val White = Color.White

@Composable
fun FacebookApp() {
    var selectedTab by remember { mutableStateOf(FacebookTab.Feed) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = FacebookBackground
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                FacebookTab.Feed -> FeedScreen()
                FacebookTab.Profile -> ProfileScreen()
                FacebookTab.Notifications -> NotificationsScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(selectedTab: FacebookTab, onTabSelected: (FacebookTab) -> Unit) {
    NavigationBar(containerColor = FacebookBlue) {
        FacebookTab.values().forEach { tab ->
            NavigationBarItem(
                icon = { Icon(imageVector = tab.icon, contentDescription = tab.name) },
                label = { Text(tab.name) },
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = White,
                    unselectedIconColor = White.copy(alpha = 0.6f)
                )
            )
        }
    }
}

enum class FacebookTab(val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Feed(Icons.Default.Home),
    Profile(Icons.Default.Person),
    Notifications(Icons.Default.Notifications)
}

@Composable
fun FeedScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Facebook",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = FacebookBlue,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, FacebookBlue),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        tint = FacebookBlue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Jimbo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Este es mi nuevo centro.", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.images),
                    contentDescription = "Imagen de publicación",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, FacebookBlue),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        tint = FacebookBlue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Juan", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Po esta weno el taco bell del nevada lokillo.", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            tint = FacebookBlue
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Efren", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text("efren@gmail.com", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Fecha de nacimiento: 15/02/2005", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Me encanta programar (a ratos)", fontSize = 16.sp)
    }
}

@Composable
fun NotificationsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val notifications = listOf(
            "Juan comentó tu publicación.",
            "María te envió una solicitud de amistad.",
            "Carlos reaccionó a tu foto."
        )
        notifications.forEach { notification ->
            ListItem(
                headlineContent = { Text(notification) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Icono de notificación",
                        tint = FacebookBlue
                    )
                },
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Divider()
        }
    }
}

@Composable
fun FacebookAppPreview() {
    FacebookApp()
}
