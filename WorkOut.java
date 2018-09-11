package s3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.joda.time.field.FieldUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FileUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class WorkOut {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		final AmazonS3 s3=AmazonS3ClientBuilder.defaultClient();
		String bucket_name="pailord";
		try
		{	
			//Bucket Creation
			
			s3.createBucket(bucket_name);
			System.out.println("Bucket Created!!!");
		}
		catch(AmazonS3Exception e)
		{
			System.err.println(e.getErrorMessage());
		}
		String bucket_name1="pailord";
		String key_name="webpage.html";
		String file_path="F://resweb/webpage_assignment.html";
		
		try
		{	
			//Uploading to S3
			
			s3.putObject(bucket_name1,key_name, new File(file_path));
			System.out.println("Uploaded Successfully!!!");
			
			//Downloading from S3
			
			S3Object object=s3.getObject(new GetObjectRequest(bucket_name,"webpage.html"));
			S3ObjectInputStream oc=object.getObjectContent();
			IOUtils.copy(oc, new FileOutputStream("F://webpage.html"));
			System.out.println("Download Completed");
			
		}
		catch(AmazonServiceException e)
		{
			System.err.println(e.getErrorMessage());
		}
		
		//Listing the Buckets
		
		List<Bucket> buckets=s3.listBuckets();
		System.out.println("The list of Buckets are:");
		for(Bucket b:buckets)
			System.out.println("* "+b.getName());
		
		
		String bucket_name2="pailord";
		String object_name="webpage.html";
		try
		{	
			//Deleting a file from Bucket
			
			
			s3.deleteObject(bucket_name2,object_name);
			System.out.println("File Deleted!!!");
		}
		catch(AmazonServiceException e)
		{
			System.err.println(e.getErrorMessage());
		}
		String bucket_name3="pailord";
		try
		{	
			//Deleting Bucket
			
			
			s3.deleteBucket(bucket_name3);
			System.out.println("Bucket Deleted!!!");
		}
		catch(AmazonServiceException e)
		{
			System.err.println(e.getErrorMessage());
		}
	}

}
