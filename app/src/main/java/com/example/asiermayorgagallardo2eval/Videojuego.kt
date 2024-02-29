package com.example.asiermayorgagallardo2eval

import android.os.Parcel
import android.os.Parcelable

class Videojuego(
    private var nombre: String?,
    private var valoracion: Float,
    private var empresa: String?,
    private var anio: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readInt()
    )

    fun getNombre() = nombre
    fun getValoracion() = valoracion
    fun getEmpresa() = empresa
    fun getAnio() = anio

    fun setNombre(nombreP: String) {
        nombre = nombreP
    }

    fun setValoracion(valoracionP: Float) {
        valoracion = valoracionP
    }

    fun setEmpresa(empresaP: String) {
        empresa = empresaP
    }

    fun setAnio(anioP: Int) {
        anio = anioP
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeFloat(valoracion)
        parcel.writeString(empresa)
        parcel.writeInt(anio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Videojuego> {
        override fun createFromParcel(parcel: Parcel): Videojuego {
            return Videojuego(parcel)
        }

        override fun newArray(size: Int): Array<Videojuego?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Nombre: $nombre  -  Valoración: $valoracion\n" +
                "Empresa: $empresa  -  Anio: $anio"
    }
}