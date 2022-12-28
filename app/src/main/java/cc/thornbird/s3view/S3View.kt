package cc.thornbird.s3view

import android.os.Bundle
import android.os.Build
import android.os.StrictMode
import android.os.Parcelable

import android.app.Activity
import android.content.Intent
import android.util.Log

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class S3View : Activity() {
	    val TAG = "S3View"
	    val s3 = S3Client()

	    override fun onCreate(savedInstanceState: Bundle?) {
	        super.onCreate(savedInstanceState)

		setContentView(R.layout.s3view_activity)
	    }
}


