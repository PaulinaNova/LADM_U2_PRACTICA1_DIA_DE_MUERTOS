package mx.tecnm.tepic.ladm_u2_practica1_dia_de_muertos

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.View

class Lienzo (act:MainActivity):View(act){
    val puntero = act
    var t1 = BitmapFactory.decodeResource(puntero.resources,R.drawable.t1)
    var t2 = BitmapFactory.decodeResource(puntero.resources,R.drawable.t2)
    var t3 = BitmapFactory.decodeResource(puntero.resources,R.drawable.t3)
    var t4 = BitmapFactory.decodeResource(puntero.resources,R.drawable.t4)
    var alebrije = BitmapFactory.decodeResource(puntero.resources,R.drawable.alebrije)
    var catrina = BitmapFactory.decodeResource(puntero.resources,R.drawable.catrina)
    val hiloCemp = MovimientoCempasuchil(this)
    var posx = 100f
    var posy = 1000f
    var cambiar = true
    val moverCatrina = object : CountDownTimer(2000,80){
        override fun onTick(p0: Long) {
            posx += 10
            if(posx > 2200)
                posx = 0f
            if(posy>=900 && cambiar){
                posy+=5
                if(posy==1100f)
                    cambiar = false
            }
            if(posy<=1100 && cambiar == false){
                posy-=5
                if(posy==900f)
                    cambiar = true
            }
            invalidate()
        }

        override fun onFinish() {
            start()
        }

    }
    init{
        hiloCemp.start()
        moverCatrina.start()
    }
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        var p = Paint()
        c.drawColor(Color.rgb(77, 6, 153))
        //Luna
        p.color = Color.WHITE
        c.drawCircle(1200f,250f,140f,p)
        //Montaña izquierda
        p.color = Color.BLACK
        c.drawOval(-100f,1000f,1900f,1800f,p)
        //Montaña derecha
        p.color = Color.BLACK
        p.style = Paint.Style.FILL
        c.drawOval(1000f,900f,3000f,1800f,p)
        //Nube1
        p.color = Color.rgb(226, 199, 255)
        c.drawOval(200f,100f,700f,300f,p)
        p.color = Color.rgb(226, 199, 255)
        c.drawOval(400f,0f,1000f,300f,p)
        p.color = Color.rgb(226, 199, 255)
        c.drawOval(700f,100f,1200f,300f,p)
        //Arbol
        p.color = Color.BLACK
        p.style = Paint.Style.FILL
        c.drawRect(2200f,600f,2500f,1000f,p)
        p.color = Color.BLACK
        c.drawCircle(2350f,400f,300f,p)
        p.color = Color.BLACK
        c.drawCircle(2150f,500f,150f,p)
        p.color = Color.BLACK
        c.drawCircle(2100f,400f,130f,p)
        p.color = Color.BLACK
        c.drawCircle(2150f,250f,130f,p)
        p.color = Color.BLACK
        c.drawCircle(2300f,150f,130f,p)
        p.color = Color.BLACK
        c.drawCircle(2200f,580f,130f,p)
        //Nube2
        p.color = Color.rgb(226, 199, 255)
        c.drawOval(1200f,200f,1700f,400f,p)
        p.color = Color.rgb(226, 199, 255)
        c.drawOval(1400f,100f,2000f,400f,p)
        p.color = Color.rgb(226, 199, 255)
        c.drawOval(1700f,200f,2200f,400f,p)
        //Tumbas
        c.drawBitmap(t1,200f,1000f,p)
        c.drawBitmap(t2,100f,1200f,p)
        c.drawBitmap(t3,450f,1000f,p)
        c.drawBitmap(t4,350f,1100f,p)
        c.drawBitmap(t1,800f,1000f,p)
        c.drawBitmap(t2,600f,1200f,p)
        c.drawBitmap(t3,750f,1100f,p)
        c.drawBitmap(t4,900f,1200f,p)
        c.drawBitmap(t1,1300f,1000f,p)
        c.drawBitmap(t2,1200f,1200f,p)
        c.drawBitmap(t3,1550f,1000f,p)
        c.drawBitmap(t4,1450f,1100f,p)
        c.drawBitmap(t1,1900f,900f,p)
        c.drawBitmap(t2,1700f,1200f,p)
        c.drawBitmap(t3,1850f,1100f,p)
        c.drawBitmap(t4,2000f,1200f,p)
        c.drawBitmap(t3,1150f,1000f,p)
        c.drawBitmap(alebrije,1800f,900f,p)
        c.drawBitmap(catrina,posx,posy,p)
        //Cempasuchil
        (0..99).forEach{
            hiloCemp.cemp[it].pintarCopo(c)
        }
    }
}

class MovimientoCempasuchil(p:Lienzo):Thread(){
    val principal = p
    val cemp = ArrayList<Cempasuchil>()
    init {
        (1..500).forEach{
            val c = Cempasuchil()
            cemp.add(c)
            val n = Cempasuchil()
        }
    }

    override fun run() {
        super.run()
        while(true){
            (0..99).forEach{
                cemp[it].moverCemp()
            }
            principal.puntero.runOnUiThread{
                principal.invalidate()
            }
            sleep(80)
        }
    }
}