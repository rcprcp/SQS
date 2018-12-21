# SQS
Sample to write batches to Amazon SQS

To use this you'll need to have credentials available for the AWS API code.  

There are two methods - one is to set shell environment variables. 

The other way is to use a file containing your credentials. I usually use this method.  For this method, 
create a directory .aws in your home directory. Then create a file `config` which contains your region: 

    [default]
    region = us-west-2

Create another file: `credentials` it's contents should look like this: 

> [default]
> aws_access_key_id = (key)
> aws_secret_access_key = (secret)
