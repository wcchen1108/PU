package tw.edu.pu.csim.mis2b.wcchen.pu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, View.OnTouchListener{
    lateinit var gDetector: GestureDetector
    var PictureNo:Int = 0  //目前顯示第幾張圖
    var TotalPictures:Int = 5 //總共幾張圖片(假設僅顯示pu0-pu4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        img.setOnTouchListener(this)
        var res:Int = -1
        var countDrawables:Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier("pu" + countDrawables.toString(),
                "drawable", getPackageName());
        }
        TotalPictures = countDrawables
    }
    /*
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
     */
    fun ShowPicture() {
        /*
        when (PictureNo) {
            0 -> img.setImageResource(R.drawable.pu0)
            1 -> img.setImageResource(R.drawable.pu1)
            2 -> img.setImageResource(R.drawable.pu2)
            3 -> img.setImageResource(R.drawable.pu3)
        }
        */
        txv.text = PictureNo.toString()
        var res:Int = getResources().getIdentifier("pu" + PictureNo.toString(),
            "drawable", getPackageName())
        img.setImageResource(res)
    }
    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
    override fun onDown(e: MotionEvent?): Boolean {
        txv.text="按下"
        return true
    }
    override fun onShowPress(e: MotionEvent?) {
        txv.text = "按下後無後續動作"
    }
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        PictureNo = 0
        ShowPicture()
        return true
    }
    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        txv.text = "拖曳\nx1y1: " +  e1?.getX().toString() + ", " + e1?.getY().toString() +
                "\nx2y2: " + e2?.getX().toString() + ", " + e2?.getY().toString()
        return true
    }
    override fun onLongPress(e: MotionEvent?) {
        PictureNo = TotalPictures - 1
        ShowPicture()
    }
    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1!!.getX() < e2!!.getX()){  //向右快滑
            PictureNo++
            if (PictureNo == TotalPictures) {PictureNo = 0}
        }
        else{     //向左快滑
            PictureNo--;
            if (PictureNo < 0) {PictureNo = TotalPictures - 1 }
        }
        ShowPicture()
        return true
    }
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        return true
    }
    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        txv.text = "連續點兩下"
        return true
    }
}