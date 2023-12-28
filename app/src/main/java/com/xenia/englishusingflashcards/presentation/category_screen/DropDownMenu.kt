package com.xenia.englishusingflashcards.presentation.category_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val levelsList = listOf(
    "Уровень A1",
    "Уровень A2",
    "Уровень B1",
    "Уровень B2",
    "Уровень C1",
    "Уровень C2",
)

@Composable
fun DropDownMenu() {
    var levelName : String by remember { mutableStateOf(levelsList[0]) }
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier
            .clickable {
                expanded = !expanded
            }) {
            Text(modifier = Modifier.padding(top = 7.dp),
                text = levelName,
                fontSize = 20.sp)
            Icon(modifier = Modifier.padding(top = 13.dp),
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                levelsList.forEach { level ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        levelName = level
                    }, text = { Text(text = level, style = MaterialTheme.typography.bodyLarge) })
                }
            }
        }
    }
}