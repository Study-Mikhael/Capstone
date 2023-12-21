package com.example.terrestrial.ml

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class TFLiteModel(context: Context, modelPath: String) {

    private val interpreter: Interpreter

    init {
        val modelFile = context.assets.openFd(modelPath)
        val fileDescriptor = modelFile.fileDescriptor
        val startOffset = modelFile.startOffset
        val declaredLength = modelFile.declaredLength

        val modelByteBuffer = FileInputStream(fileDescriptor).channel
            .map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)

        interpreter = Interpreter(modelByteBuffer)
    }

    fun runInference(inputs: IntArray): FloatArray {
        val inputBuffer = ByteBuffer.allocateDirect(inputs.size * 4)
        inputBuffer.order(ByteOrder.nativeOrder())

        for (input in inputs) {
            inputBuffer.putInt(input)
        }

        val outputBuffer = ByteBuffer.allocateDirect(4 * NUM_CLASSES)
        outputBuffer.order(ByteOrder.nativeOrder())

        interpreter.run(inputBuffer, outputBuffer)

        val outputArray = FloatArray(NUM_CLASSES)
        outputBuffer.rewind()
        outputBuffer.asFloatBuffer().get(outputArray)

        return outputArray
    }

    companion object {
        private const val NUM_CLASSES = 3 // Adjust based on the number of output classes
    }
}
