package com.fjrh.karycleanfactory.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fjrh.karycleanfactory.data.local.entity.IngredienteInventarioEntity

@Composable
fun TarjetaIngrediente(
    ingrediente: IngredienteInventarioEntity,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val colorSemaforo = when {
        ingrediente.cantidadDisponible <= 0 -> Color(0xFFE57373) // rojo - sin stock
        ingrediente.cantidadDisponible < 10 -> Color(0xFFFFB74D) // naranja - stock bajo
        ingrediente.cantidadDisponible < 50 -> Color(0xFFFFF176) // amarillo - stock medio
        else -> Color(0xFF81C784) // verde - stock bueno
    }
    
    val textoStock = when {
        ingrediente.cantidadDisponible <= 0 -> "SIN STOCK"
        ingrediente.cantidadDisponible < 10 -> "STOCK BAJO"
        ingrediente.cantidadDisponible < 50 -> "STOCK MEDIO"
        else -> "STOCK OK"
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Encabezado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ingrediente",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Unidad",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Cantidad",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Costo",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Proveedor",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Nivel",
                    modifier = Modifier.weight(0.5f),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Datos
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = ingrediente.nombre, modifier = Modifier.weight(1f))
                Text(text = ingrediente.unidad, modifier = Modifier.weight(1f))
                Text(text = ingrediente.cantidadDisponible.toString(), modifier = Modifier.weight(1f))
                Text(text = "$${String.format("%.2f", ingrediente.costoPorUnidad)}", modifier = Modifier.weight(1f))
                Text(text = ingrediente.proveedor.toString(), modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.weight(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(colorSemaforo, CircleShape)
                    )
                    Text(
                        text = textoStock,
                        style = MaterialTheme.typography.caption,
                        color = colorSemaforo,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }
            }
        }
    }
}
