package com.valorantwiki.valorantwikiapp.ui.components

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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valorantwiki.valorantwikiapp.data.model.Agent

@Composable
fun AgentItem(agent: Agent, onAgentClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
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
                    Text(text = agent.displayName, style = MaterialTheme.typography.titleLarge)
                }
                Text(
                    text = "Role: ${agent.role?.displayName ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(
                onClick = { onAgentClick() },
            ) {
                Icon(Icons.Default.Info, contentDescription = "Más información")
            }
        }
    }
}