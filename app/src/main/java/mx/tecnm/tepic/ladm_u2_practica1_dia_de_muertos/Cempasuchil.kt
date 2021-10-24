package mx.tecnm.tepic.ladm_u2_practica1_dia_de_muertos

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Cempasuchil {
    var x = 0f
    var y = 0f
    var tam = 0f

    init {
        x = (Math.random()*2400).toFloat()
        y = ((Math.random()*2000)*-1).toFloat()
        tam = ((Math.random()*4)+9).toFloat()
    }

    fun moverCemp(){
        y += tam
        if(y>1000)
            y = -100f
    }

    fun pintarCopo(c: Canvas){
        val p = Paint()
        p.color = Color.rgb(255, 200, 0)
        c.drawCircle(x,y,tam,p)
    }
}