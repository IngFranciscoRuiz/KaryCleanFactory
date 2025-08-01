package com.fjrh.karycleanfactory.data.local.repository

import com.fjrh.karycleanfactory.data.local.dao.FormulaDao
import com.fjrh.karycleanfactory.data.local.entity.FormulaConIngredientes
import com.fjrh.karycleanfactory.data.local.entity.FormulaEntity
import com.fjrh.karycleanfactory.data.local.entity.IngredienteEntity
import com.fjrh.karycleanfactory.data.local.entity.IngredienteInventarioEntity
import com.fjrh.karycleanfactory.data.local.entity.HistorialProduccionEntity
import com.fjrh.karycleanfactory.domain.model.StockProducto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FormulaRepository @Inject constructor(
    private val formulaDao: FormulaDao
) {

    fun obtenerFormulasConIngredientes(): Flow<List<FormulaConIngredientes>> {
        return formulaDao.getAllFormulasConIngredientes()
    }

    suspend fun eliminarFormulaConIngredientes(formula: FormulaEntity) {
        formulaDao.deleteIngredientesByFormulaId(formula.id)
        formulaDao.deleteFormula(formula)
    }

    suspend fun eliminarIngredientesByFormulaId(formulaId: Long) {
        formulaDao.deleteIngredientesByFormulaId(formulaId)
    }

    suspend fun insertarFormulaConIngredientes(
        formula: FormulaEntity,
        ingredientes: List<IngredienteEntity>
    ) {
        formulaDao.insertarFormulaConIngredientes(formula, ingredientes)
    }

    suspend fun insertarHistorial(historial: HistorialProduccionEntity) {
        formulaDao.insertarHistorial(historial)
    }

    suspend fun insertarFormula(formula: FormulaEntity): Long {
        return formulaDao.insertFormula(formula)
    }

    fun getStockProductos(): Flow<List<StockProducto>> {
        return formulaDao.getStockProductos().map { list ->
            list.map { StockProducto(nombre = it.nombreFormula, stock = it.stock) }
        }
    }

    fun getIngredientesInventario(): Flow<List<IngredienteInventarioEntity>> {
        return formulaDao.getIngredientesInventario()
    }

    fun getHistorial(): Flow<List<HistorialProduccionEntity>> {
        return formulaDao.getHistorial()
    }

    suspend fun actualizarIngredienteInventario(ingrediente: IngredienteInventarioEntity) {
        formulaDao.actualizarIngredienteInventario(ingrediente)
    }
}
