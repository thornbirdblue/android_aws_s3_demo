package cc.thornbird.s3upload

import android.util.Log
import android.net.Uri

import java.io.*

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.service.s3.Amazon3
import com.amazonaws.service.s3.Amazon3Client
import com.amazonaws.service.s3.model.PutObjectRequest

class S3Client {
	val TAG = "S3Upload"
	private val bucketName = "testbucker"
	private val keyName = "test.jpg"

	private val credentials: AWSCredentials = BasicAWSCredentials("minioadmin","minioadmin")
	private val clientConfiguration = ClientConfiguration()
	private val s3Client: AmazonS3

	init {
		Log.i(TAG,"init\n")

		clientConfiguration.setSignerOverride("AWSS3V4SignerType")

		s3Client = AmazonS3Client(credentials,clientConfiguration)
		s3Client.setEndpoint("http://")
	}

	fun Upload(uri: String){
		try{
			Log.i(TAG,"Uploading.")

			val file = File(uri)

			s3Client.putObject(PutObjectRequest(bucketName, keyName, file))
		} catch (ase: AmazonServiceException){
			Log.i(TAG,"Service Error Message: "+ ase.message)

		} catch (ace: AmazonClientException){
			Log.i(TAG,"Client Error Message: "+ ace.message)
		}
	}
}


