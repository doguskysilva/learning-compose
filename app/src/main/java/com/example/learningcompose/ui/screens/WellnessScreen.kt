package com.example.learningcompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learningcompose.data.WellnessTask
import com.example.learningcompose.data.WellnessViewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    var showScreenWithList by rememberSaveable { mutableStateOf(true) }

    Column() {
        Switch(
            checked = showScreenWithList,
            modifier = Modifier.padding(16.dp),
            onCheckedChange = { newValue -> showScreenWithList = newValue }
        )
        if (showScreenWithList) {
            WaterCounterWithList(modifier, wellnessViewModel)
        } else {
            WaterCounterWithStates(modifier, wellnessViewModel)
        }
    }
}

@Composable
fun WaterCounterWithStates(modifier: Modifier = Modifier, wellnessViewModel: WellnessViewModel) {
    val count: MutableState<Int> = rememberSaveable { mutableStateOf(0) }
    var count2 by rememberSaveable { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "You've had ${count.value} glasses (val).",
                modifier = modifier.padding(16.dp)
            )
            Text(
                text = "You've had $count2 glasses (var).",
                modifier = modifier.padding(16.dp)
            )

            Button(
                onClick = { count.value++ },
                Modifier.padding(16.dp),
                enabled = count.value < 5
            ) {
                Text("Increment by value")
            }

            Button(onClick = { count2++ }, Modifier.padding(16.dp)) {
                Text("Increment directly")
            }
        }
    }
}

@Composable
fun WaterCounterWithList(modifier: Modifier = Modifier, wellnessViewModel: WellnessViewModel) {
    Column() {
        StatefulCounter(modifier = modifier)
        WellnessTaskList(
            tasks = wellnessViewModel.tasks,
            onClosedTask = { task -> wellnessViewModel.removeTask(task) },
            onCheckedTask = { task, checked -> wellnessViewModel.changedTaskChecked(task, checked) }
        )
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var waterCount by rememberSaveable { mutableStateOf(1) }
    var juiceCount by rememberSaveable { mutableStateOf(1) }

    Row {
        StatelessCounter(waterCount, { waterCount++ })
        StatelessCounter(juiceCount, { juiceCount++ })
    }
}

@Composable
fun WellnessTaskList(
    tasks: List<WellnessTask>,
    onClosedTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
) {
    LazyColumn(modifier = modifier, state = rememberLazyListState()) {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onClosedTask(task) },
                modifier = modifier,
            )
        }
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}