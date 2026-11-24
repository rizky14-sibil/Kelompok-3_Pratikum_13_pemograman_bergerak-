package com.example.kamus

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KataDao_Impl(
  __db: RoomDatabase,
) : KataDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfKata: EntityInsertAdapter<Kata>

  private val __deleteAdapterOfKata: EntityDeleteOrUpdateAdapter<Kata>
  init {
    this.__db = __db
    this.__insertAdapterOfKata = object : EntityInsertAdapter<Kata>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `kata` (`id`,`indonesia`,`inggris`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Kata) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.indonesia)
        statement.bindText(3, entity.inggris)
      }
    }
    this.__deleteAdapterOfKata = object : EntityDeleteOrUpdateAdapter<Kata>() {
      protected override fun createQuery(): String = "DELETE FROM `kata` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Kata) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override suspend fun tambah(kata: Kata): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfKata.insert(_connection, kata)
  }

  public override suspend fun hapus(kata: Kata): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfKata.handle(_connection, kata)
  }

  public override fun ambilSemua(): Flow<List<Kata>> {
    val _sql: String = "SELECT * FROM kata ORDER BY indonesia ASC"
    return createFlow(__db, false, arrayOf("kata")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfIndonesia: Int = getColumnIndexOrThrow(_stmt, "indonesia")
        val _columnIndexOfInggris: Int = getColumnIndexOrThrow(_stmt, "inggris")
        val _result: MutableList<Kata> = mutableListOf()
        while (_stmt.step()) {
          val _item: Kata
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpIndonesia: String
          _tmpIndonesia = _stmt.getText(_columnIndexOfIndonesia)
          val _tmpInggris: String
          _tmpInggris = _stmt.getText(_columnIndexOfInggris)
          _item = Kata(_tmpId,_tmpIndonesia,_tmpInggris)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun cari(kunci: String): List<Kata> {
    val _sql: String = "SELECT * FROM kata WHERE indonesia LIKE ? ORDER BY indonesia ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, kunci)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfIndonesia: Int = getColumnIndexOrThrow(_stmt, "indonesia")
        val _columnIndexOfInggris: Int = getColumnIndexOrThrow(_stmt, "inggris")
        val _result: MutableList<Kata> = mutableListOf()
        while (_stmt.step()) {
          val _item: Kata
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpIndonesia: String
          _tmpIndonesia = _stmt.getText(_columnIndexOfIndonesia)
          val _tmpInggris: String
          _tmpInggris = _stmt.getText(_columnIndexOfInggris)
          _item = Kata(_tmpId,_tmpIndonesia,_tmpInggris)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
