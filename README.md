# SQS
Sample to write batches to Amazon SQS

Adjust the parameters in the Java code as needed. This program does not use any command line args.   

To use this you'll need to have credentials available for the AWS API code.  There are two methods to provide the configuration information and your credentials - one is to set shell environment variables. 

The other way is to use a file containing your credentials. I usually use this method.  For this method, 
create a directory .aws in your home directory. Then create a file `config` which contains your region: 

    [default]
    region = us-west-2

Create another file: `credentials` it's contents should look like this: 

    [default]
    aws_access_key_id = (key)
    aws_secret_access_key = (secret)
    
Build and run the program, then you should be able to verify the records are in the queue with the AWS SQS console. 

    git clone http://github.com/rcprcp/SQS.git
    cd SQS 
    mvn clean package
    cd target
    java -jar SQS-0.5-SNAPSHOT-jar-with-dependencies.jar
    
Enjoy!
    