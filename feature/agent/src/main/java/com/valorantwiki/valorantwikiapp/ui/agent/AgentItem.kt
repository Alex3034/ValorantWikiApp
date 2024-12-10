package com.valorantwiki.valorantwikiapp.ui.agent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valorantwiki.valorantwikiapp.domain.agent.entities.Agent

@Composable
fun AgentItem(agent: Agent, onAgentClick: () -> Unit) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onAgentClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0f)
            ) {
                AsyncImage(
                    model = agent.displayIcon,
                    contentDescription = "Icono de ${agent.displayName}",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(64.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = agent.displayName,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge)
                }
            }
            IconButton(
                onClick = { onAgentClick() },
            ) {
                Icon(Icons.Default.Info,
                    tint = Color.White,
                    contentDescription = "Más información")
            }
        }
    }
}