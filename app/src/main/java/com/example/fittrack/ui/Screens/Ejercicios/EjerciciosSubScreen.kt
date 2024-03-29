package com.example.fittrack.ui.Screens.Ejercicios


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.fittrack.Data.Entities.Ejercicio
import com.example.fittrack.R
import com.example.fittrack.ui.ViewModels.MainViewModel


@Composable
fun EjercicoDialog(
    mainViewModel: MainViewModel
){
    Dialog(onDismissRequest = {mainViewModel.OpenCloseEjercicioForm(false)}) {
        // Draw a rectangle shape with rounded corners inside the dialog

        var nombreEjercico by remember { mutableStateOf("") }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,

            ) {
                Column (
                    modifier = Modifier
                    .padding(15.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.exercise),
                        fontSize = 20.sp,
                    )
                    OutlinedTextField(
                        value = nombreEjercico,
                        onValueChange = { nombreEjercico = it},

                        label = ({ Text(text = stringResource(id = R.string.name))}),

                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = { mainViewModel.OpenCloseEjercicioForm(false) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(stringResource(id = R.string.dimiss))
                    }
                    Button(
                        onClick = {
                            if (nombreEjercico.length > 2){
                                mainViewModel.submitEjercio(Ejercicio(nombre = nombreEjercico))
                            }
                             },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(stringResource(id = R.string.confirm))
                    }
                }
            }
        }
    }
}



@Composable
fun ListaEjercicios(
    mainViewModel: MainViewModel
){
    val ejercicios by mainViewModel.ejercicios.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.padding(15.dp)
    ){
        items(items = ejercicios, key = {it.nombre}){ item ->
            ItemEjercico(mainViewModel = mainViewModel, ejercicio = item)
        }
    }
}

@Composable
fun ItemEjercico(
    mainViewModel: MainViewModel,
    ejercicio: Ejercicio
){
    Card (
        modifier = Modifier
            .padding(bottom = 7.dp)
            .fillMaxWidth()
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .weight(1f) // Toma el espacio restante
            ) {
                Text(
                    text = ejercicio.nombre,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }

            // Otro elemento en la fila que se alinea a la derecha
            Box() {
                IconButton(
                    onClick = {
                              mainViewModel.deleteEjercicio(ejercicio)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(MaterialTheme.shapes.small)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}





