package com.vxx0.aso.qr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            try {
                val barcodeEncoder = BarcodeEncoder()
                val bitmap = barcodeEncoder
                    .encodeBitmap(editText.text.toString(), BarcodeFormat.QR_CODE, 400, 400)
                imageView.setImageBitmap(bitmap)
            } catch (e: Exception) {

            }
        }
        button2.setOnClickListener {
            barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(listOf(BarcodeFormat.QR_CODE))
            barcodeView.decodeContinuous(object : BarcodeCallback {
                override fun barcodeResult(result: BarcodeResult) {
                    editText.setText(result.text)
                }

                override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>) {

                }
            })
        }
    }

    override fun onResume() {
        super.onResume()

        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()

        barcodeView.pause()
    }
}
