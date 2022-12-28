package cc.thornbird.s3view

import android.util.Log
import android.net.Uri

import java.io.*

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.*

class S3Client {
	val TAG = "S3Upload"

	private var s3Client: AmazonS3 ?= null

	fun setEndpoint(AccessName: String = "minioadmin",KeyName: String = "minioadmin",url: String ="http://172.25.224.31:9000")
	{
		val credentials = BasicAWSCredentials(AccessName,KeyName)
		
		val clientConfiguration = ClientConfiguration()
		clientConfiguration.setSignerOverride("AWSS3V4SignerType")

		val Client = AmazonS3Client(credentials,clientConfiguration)
		Client.setEndpoint(url)
		s3Client=Client
	}

	fun listBuckets(): List<Bucket>
	{
		return s3Client!!.listBuckets()
	}

	fun listObjectsOfBucket(BucketName: String): ObjectListing
	{
		return s3Client!!.listObjects(BucketName)
	}

	fun Upload(uri: String){
		val bucketName = "testbucker"
		val keyName = "test.jpg"
		
		try{
			Log.i(TAG,"Uploading.")

			val file = File(uri)

			s3Client!!.putObject(PutObjectRequest(bucketName, keyName, file))
		} catch (ase: AmazonServiceException){
			Log.i(TAG,"Service Error Message: "+ ase.message)

		} catch (ace: AmazonClientException){
			Log.i(TAG,"Client Error Message: "+ ace.message)
		}
	}
}


